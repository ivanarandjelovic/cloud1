INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('webapp', '{noop}webapppwd', 'test_scope',
    'authorization_code,refresh_token,password,client_credentials', null, null, 36000, 36000, null, true);
    
insert into users(username, password, enabled) values ('aivan','{noop}aivan',true);
 
INSERT INTO authorities(username, authority) values ('aivan','USER');
