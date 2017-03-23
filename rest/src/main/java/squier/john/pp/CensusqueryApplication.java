package squier.john.pp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import squier.john.pp.model.Query;

@SpringBootApplication
public class CensusqueryApplication {

    private static final Logger log = LoggerFactory.getLogger(CensusqueryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CensusqueryApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ApplicationRunner run(RestTemplate restTemplate) throws Exception {
        return (ApplicationArguments args) -> {
            Query query = restTemplate.getForObject(
                    "http://api.census.gov/data/2010/sf1?key=b315c1e1cc2b89d6770000ab9d29c68f45162522&get=PCT012A029&for=state:08",
                    Query.class);
            log.info(query.toString());
        };
    }
}
