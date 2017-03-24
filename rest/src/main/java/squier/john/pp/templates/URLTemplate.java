package squier.john.pp.templates;

import org.springframework.context.annotation.Configuration;
import squier.john.pp.model.Options;

import java.util.Map;
import java.util.Set;

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
    private String stateDE = "&for=state:10";
    private Options optionsMap;

    public URLTemplate() {
        optionsMap = new Options();
    }

    public String generateURL(String[] options) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        for ( int i = 0; i < options.length; i++ ) {
            sb.append(getOptionFromReadableOption(options));
            if ( i != options.length-1 ) {
                sb.append(",");
            }
        }
        sb.append(stateDE);
        return sb.toString();
    }

    private String getOptionFromReadableOption(String[] options) {
        for (String s : options) {
            for ( Map.Entry<String, String> e : optionsMap.getAvailableOptions().entrySet() ) {
                if (e.getValue().equals(s)) {
                    return e.getKey();
                }
            }
        }
        return "KEY NOT FOUND";
    }
}
