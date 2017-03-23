package squier.john.pp;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

@SpringBootApplication
public class CensusQueryApplication {

    private static final Logger log = LoggerFactory.getLogger(CensusQueryApplication.class);
    private final String wMTwentyNineDEURL = "http://api.census.gov/data/2010/sf1?key=b315c1e1cc2b89d6770000ab9d29c68f45162522&get=PCT012A029&for=state:08";

	public static void main(String[] args) {
		SpringApplication.run(CensusQueryApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            ResponseEntity<ArrayNode> entity = restTemplate.exchange(wMTwentyNineDEURL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ArrayNode>(){});
            System.out.println(entity.getBody().toString());//////////////////////////////////////////////////////////////////////
        };
    }
}
