package squier.john.pp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import squier.john.pp.model.CensusResponse;
import squier.john.pp.templates.URLTemplate;

import java.util.Map;

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
    private CensusResponse censusResponse;

    public CensusController(RestTemplate restTemplate, URLTemplate urlTemplate, CensusResponse censusResponse) {
        this.restTemplate = restTemplate;
        this.urlTemplate = urlTemplate;
        this.censusResponse = censusResponse;
    }

    @RequestMapping(value = "wm29de", method = RequestMethod.GET)
    public Map getWM29DE() {
        String[] options = {"whiteMale29"};

        ResponseEntity<ArrayNode> response = restTemplate.exchange(urlTemplate.generateURL(options), HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayNode>(){});

        censusResponse.setResponse(response.getBody());
        return censusResponse.getFrontEndResponse();
    }

    @RequestMapping(value = "wmwf29de", method = RequestMethod.GET)
    public Map getWMWF29DE() {
        String[] options = {"whiteMale29", "whiteFemale29"};

        ResponseEntity<ArrayNode> response = restTemplate.exchange(urlTemplate.generateURL(options), HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayNode>() {});

        censusResponse.setResponse(response.getBody());
        return censusResponse.getFrontEndResponse();
    }
}
