package squier.john.pp.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import squier.john.pp.template.URLTemplate;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * TODO create an options class that can be de-serialized from front end data
 */
@RestController
@Configuration
@RequestMapping(value = "/api/v1")
public class CensusController {

    private RestTemplate restTemplate;
    private URLTemplate urlTemplate;

    public CensusController(RestTemplate restTemplate, URLTemplate urlTemplate) {
        this.restTemplate = restTemplate;
        this.urlTemplate = urlTemplate;
    }

    @RequestMapping(value = "wm29de", method = RequestMethod.GET)
    public ArrayNode getWM29DE() {
        String[] options = {"whiteMale29"};

        ResponseEntity<ArrayNode> response = restTemplate.exchange(urlTemplate.generateURL(options), HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayNode>(){});

        return response.getBody();
    }
}
