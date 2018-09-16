package org.aivan.cloud1.service_common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {
	@Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
	  
	  OAuth2MethodSecurityExpressionHandler oAuth2MethodSecurityExpressionHandler = new OAuth2MethodSecurityExpressionHandler();
	 
	  AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());
	  oAuth2MethodSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
	  
	  return oAuth2MethodSecurityExpressionHandler;
    }
	
  @Bean 
  public JdbcMutableAclService aclService() { 
      return new JdbcMutableAclService(
        dataSource, lookupStrategy(), aclCache()); 
  }
  
  @Autowired
  DataSource dataSource;
   
  @Bean
  public AclAuthorizationStrategy aclAuthorizationStrategy() {
      return new AclAuthorizationStrategyImpl(
        new SimpleGrantedAuthority("ADMIN"));
  }
   
  @Bean
  public PermissionGrantingStrategy permissionGrantingStrategy() {
      return new DefaultPermissionGrantingStrategy(
        new ConsoleAuditLogger());
  }
   
  @Bean
  public EhCacheBasedAclCache aclCache() {
      return new EhCacheBasedAclCache(
        aclEhCacheFactoryBean().getObject(), 
        permissionGrantingStrategy(), 
        aclAuthorizationStrategy()
      );
  }
   
  @Bean
  public EhCacheFactoryBean aclEhCacheFactoryBean() {
      EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
      ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
      ehCacheFactoryBean.setCacheName("aclCache");
      return ehCacheFactoryBean;
  }
   
  @Bean
  public EhCacheManagerFactoryBean aclCacheManager() {
      return new EhCacheManagerFactoryBean();
  }
   
  @Bean
  public LookupStrategy lookupStrategy() { 
      return new BasicLookupStrategy(
        dataSource, 
        aclCache(), 
        aclAuthorizationStrategy(), 
        new ConsoleAuditLogger()
      ); 
  }
  
}
