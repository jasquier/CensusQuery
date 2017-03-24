package squier.john.pp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * Class to hold the response from a census api call
 */
@Configuration
public class CensusResponse {

    private ArrayNode response;
    private JsonNode header;
    private JsonNode[] body;
    private Map<String, String> frontEndResponse;
    private Options options;

    public CensusResponse(Options options) {
        frontEndResponse = new HashMap<>();
        this.options = options;
    }

    public ArrayNode getResponse() {
        return response;
    }

    public Map<String, String> getFrontEndResponse() {
        buildFrontEndResponseFromCensusResponse();
        return frontEndResponse;
    }

    public void setResponse(ArrayNode response) {
        this.response = response;
    }

    private void buildFrontEndResponseFromCensusResponse() {
        header = response.get(0);
        body = new JsonNode[response.size() - 1];
        for ( int i = 0; i < body.length; i++ ) {
            body[i] = response.get(i + 1);
        }

        for ( int i = 0; i < body.length; i++ ) {
            String state = header.get(header.size() - 1).asText();
            String stateNum = body[i].get(body.length).asText();
            int stateNumStringAsInt = Integer.parseInt(stateNum);
            frontEndResponse.put(state, USStates.values()[stateNumStringAsInt].toString());

            String option = header.get(0).asText();
            String optionInReadableForm = options.getAvailableOptions().get(option);
            String optionValue = body[i].get(0).asText(); // this is hardcoded!
            frontEndResponse.put(optionInReadableForm, optionValue);
        }
    }
}
