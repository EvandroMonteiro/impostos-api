/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import st.gov.financas.impostosApi.config.property.ImpostosApiProperty;

/**
 *
 * @author Impostos
 */
// Interface responsavel pela interceptação da requisção antes de ser escrita de volta
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    @Autowired
    ImpostosApiProperty impostosApiProperty;

    @Override
    public boolean supports(MethodParameter mp, Class<? extends HttpMessageConverter<?>> type) {
        return mp.getMethod().getName().equals("postAccessToken"); //Permite que seja intercetado apenas quando estivesmos a trabalhar com o post
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter mp,
            MediaType mt, Class<? extends HttpMessageConverter<?>> type,
            ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        String refreshToken = body.getRefreshToken().getValue();

        adicionarRefreshTokenNoCookie(refreshToken, req, resp);
        removerRefreshTokenDoBody(token);//Remover o token para não aparecer no cordo da requisição
        return body;
    }

    private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(impostosApiProperty.getSeguranca().isEnableHttps());
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }

    private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

}
