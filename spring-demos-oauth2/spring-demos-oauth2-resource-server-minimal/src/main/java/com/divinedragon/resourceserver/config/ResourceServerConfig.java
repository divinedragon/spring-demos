package com.divinedragon.resourceserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-id";

    private final TokenStore tokenStore;

    @Autowired
    public ResourceServerConfig(final AccessTokenConverter accessTokenConverter, final TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true).tokenStore(tokenStore);
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        //F-
        http
            .anonymous().disable()
            .authorizeRequests()
                .antMatchers("/users/**").access("hasRole('ADMIN')")
            .and()
            .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler())
        ;
        //F+
    }
}
