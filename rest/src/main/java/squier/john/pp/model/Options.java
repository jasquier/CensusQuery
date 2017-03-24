package squier.john.pp.model;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John A. Squier
 * @date 3/24/17.
 *
 * Class to store the various filthy optionses allowed in census api requests
 *
 * TODO decide if this class should extend Map and not contain a field of type Map.
 * TODO add more options
 */
@Configuration
public class Options {

    private Map<String, String> availableOptions;

    public Options() {
        availableOptions = new HashMap<>();
        populateOptionsMap();
    }

    public Map<String, String> getAvailableOptions() {
        return availableOptions;
    }

    private void populateOptionsMap() {
        availableOptions.put("PCT012A032", "whiteMale29");
        availableOptions.put("PCT012A136", "whiteFemale29");
    }
}
