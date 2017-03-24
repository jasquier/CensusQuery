package squier.john.pp.model;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John A. Squier
 * @date 3/24/17.
 */
public class CensusResponseSpec {

    CensusResponse censusResponse;
    ArrayNode input, header, body;
    List<JsonNode> children;
    JsonNodeFactory factory;


    @Before
    public void setup() {
        censusResponse = new CensusResponse(new Options());

        factory = new JsonNodeFactory(false);

        header = new ArrayNode(factory);
        header.add("PCT012A032");
        header.add("state");

        body = new ArrayNode(factory);
        body.add("30428");
        body.add("10");

        children = new ArrayList<>();
        children.add(header);
        children.add(body);

        input = new ArrayNode(factory, children);
    }

    @Test
    public void setAndGetResponseTest() {
        // simple mock
        ArrayNode expected = new ResponseEntity<ArrayNode>(HttpStatus.ACCEPTED).getBody();

        censusResponse.setResponse(expected);
        ArrayNode actual = censusResponse.getResponse();

        assertEquals("I expect the returned ArrayNode to be the body of the mocked ResponseEntity",
                expected, actual);
    }

    @Test
    public void buildFrontEndResponseFromCensusResponseTest() {
        censusResponse.setResponse(input);
        Map<String, String> expected = new HashMap<>();
        expected.put("state", "DELAWARE");
        expected.put("whiteMale29", "30428");

        Map<String, String> actual = censusResponse.getFrontEndResponse();

        assertEquals("I expect the Map returned from getFrontEndResponse() to be equal to expected",
                expected, actual);
    }
}
