package com.divinedragon.authserver.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String GRANT_TYPE_PASSWORD = "password";

    private static final String REFRESH_TOKEN = "refresh_token";

    private static final String SCOPE_READ = "read";

    private static final String SCOPE_WRITE = "write";

    private static final String TRUST = "trust";

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = (int) TimeUnit.HOURS.toSeconds(1);

    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = (int) TimeUnit.HOURS.toSeconds(6);

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final AccessTokenConverter accessTokenConverter;

    @Autowired
    public AuthorizationServerConfig(final AuthenticationManager authenticationManager, final TokenStore tokenStore,
            final AccessTokenConverter accessTokenConverter, final PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        //F-
        configurer.inMemory()
                      .withClient("client-id")
                      .secret(passwordEncoder.encode("secret"))
                      .authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN)
                      .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                      .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                      .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        //F+
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //F-
        endpoints.tokenStore(tokenStore)
                 .authenticationManager(authenticationManager)
                 .accessTokenConverter(accessTokenConverter)
        ;
        //F+
    }
}
