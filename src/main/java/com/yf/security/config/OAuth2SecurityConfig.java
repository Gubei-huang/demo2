package com.yf.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

@EnableAuthorizationServer
@Configuration
public class OAuth2SecurityConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager manager;
    @Resource
    UserDetailsService service;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("web")
                .secret(encoder.encode("123456"))
                .autoApprove(false)
                .scopes("user")
                .redirectUris("http://localhost:8080/sso/index.html")
                .authorizedGrantTypes("client_credentials", "password", "implicit", "authorization_code", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(encoder)
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .userDetailsService(service)
                .authenticationManager(manager);
    }
}
