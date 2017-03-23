package squier.john.pp.model;

import java.util.List;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * Domain class for data from the consumed U.S. census api.
 */
public class Query {

    private List<List<String>> response;
//    private List<String> header;
//    private List<String> body;

    public Query() {
    }

    public List<List<String>> getResponse() {
        return response;
    }

    public void setResponse(List<List<String>> response) {
        this.response = response;
    }

//    public List<String> getHeader() {
//        return header;
//    }
//
//    public void setHeader(List<String> header) {
//        this.header = header;
//    }
//
//    public List<String> getBody() {
//        return body;
//    }
//
//    public void setBody(List<String> body) {
//        this.body = body;
//    }
}
