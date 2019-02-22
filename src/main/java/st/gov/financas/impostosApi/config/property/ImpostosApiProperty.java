/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Impostos
 */
@ConfigurationProperties("imposto")
public class ImpostosApiProperty {

    private String origenPermitida = "http://localhost:8081";

    private final Seguranca seguranca = new Seguranca();

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOrigenPermitida() {
        return origenPermitida;
    }

    public void setOrigenPermitida(String origenPermitida) {
        this.origenPermitida = origenPermitida;
    }
    

    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}
