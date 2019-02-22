package st.gov.financas.impostosApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import st.gov.financas.impostosApi.config.property.ImpostosApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ImpostosApiProperty.class)
public class ImpostosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImpostosApiApplication.class, args);
    }

}
