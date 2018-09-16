package org.aivan.cloud1.objekti;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableResourceServer
@ComponentScan("org.aivan.cloud1.service_common,org.aivan.cloud1.objekti")
public class ObjektiApplication {

  static Log log = LogFactory.getLog(ObjektiApplication.class);
  
	public static void main(String[] args) {
		SpringApplication.run(ObjektiApplication.class, args);
	}
	
//	@Autowired
//	private MethodSecurityExpressionHandler expressionHandler;
	
//	public void setExpressionHandler(MethodSecurityExpressionHandler expressionHandler) {
//	  System.out.println("I got expression handler: "+expressionHandler.getClass());
//    this.expressionHandler = expressionHandler;
//    
//   // this.expressionHandler.setPer
//  }
	
	
//  @Bean
//  public MethodSecurityExpressionHandler 
//    defaultMethodSecurityExpressionHandler() {
//      DefaultMethodSecurityExpressionHandler expressionHandler
//        = new DefaultMethodSecurityExpressionHandler();
//      AclPermissionEvaluator permissionEvaluator 
//        = new AclPermissionEvaluator(aclService());
//      expressionHandler.setPermissionEvaluator(permissionEvaluator);
//      return expressionHandler;
//  }
  

  
}
