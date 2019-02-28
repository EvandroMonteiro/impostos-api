/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.config.token;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import st.gov.financas.impostosApi.security.util.UsuarioSistema;

/**
 *
 * @author barro
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        //authentication.getPrincipal nos da o usuario logado
        UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();

        //criar o mapa para passar os atributos que queremos para o token
        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("nome", usuarioSistema.getUsuario().getNome());
        addInfo.put("email", usuarioSistema.getUsuario().getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }

}
