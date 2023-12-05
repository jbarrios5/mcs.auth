package py.com.jmbr.mcs.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    //configuracion general

    @Bean("authRestTemplate")
    public RestTemplate buildRestTemplate(){

        return new RestTemplate();
    }


}
