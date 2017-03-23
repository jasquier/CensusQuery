package squier.john.pp.template;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * Creates and returns URL's for the census api.
 */
@Configuration
public class URLTemplate {

    private final String key = "b315c1e1cc2b89d6770000ab9d29c68f45162522";
    private String URL = "http://api.census.gov/data/2010/sf1?key=" + key + "&get=";
    private String stateDE = "&for=state:08";
    private Map<String, String> optionsMap;

    public URLTemplate() {
        optionsMap = new HashMap<>();
        buildOptionsMap();
    }

    public String generateURL(String[] options) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        for ( int i = 0; i < options.length; i++ ) {
            sb.append(optionsMap.get(options[i]));
            if ( i != options.length-1 ) {
                sb.append(",");
            }
        }
        sb.append(stateDE);
        return sb.toString();
    }

    private void buildOptionsMap() {
        optionsMap.put("whiteMale29", "PCT012A032");
        optionsMap.put("whiteFemale29", "PCT012A136");
    }
}
