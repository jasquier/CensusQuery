package squier.john.pp.templates;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * This class currently (3/23) exists because i'm not sure where to put the restTemplate method.
 */
@Configuration
public class RestTemplateGen {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
