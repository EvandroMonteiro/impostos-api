/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import st.gov.financas.impostosApi.config.token.CustomTokenEnhancer;

/**
 *
 * @author Impostos
 */
@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    //Configuração do cliente. Quem o usuario esta usando. Autorizar
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//colar o cliente memoria
                .withClient("angular")//nome do cliente
                .secret("@ngul@r0")//senha do cliente
                .scopes("read", "write")//Scopo do cliente. Permite por exemplo limitar o cliente
                .authorizedGrantTypes("password", "refresh_token")//
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(3600 * 24)
                .and()
                .withClient("mobile")//nome do cliente
                .secret("m0b1l30")//senha do cliente
                .scopes("read")//Scopo do cliente. Permite por exemplo limitar o cliente
                .authorizedGrantTypes("password", "refresh_token")//
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(3600 * 24);

    }

    //
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //token com mais detalhes, melhorados, encrementados
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                //                .accessTokenConverter(accessTokenConverter())
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("algaworks");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    //cara costumezido que vai incrementar nosso token
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
