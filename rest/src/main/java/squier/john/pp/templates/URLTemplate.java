package squier.john.pp.templates;

import org.springframework.context.annotation.Configuration;
import squier.john.pp.model.Options;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author John A. Squier
 * @date 3/23/17.
 *
 * Creates and returns URL's for the census api.
 *
 * TODO add handling for the US State input from the front end HTTP GET request
 */
@Configuration
public class URLTemplate {

    private final String key = readApiKeyFromFile();
    private String baseURL = "http://api.census.gov/data/2010/sf1?key=" + key + "&get=";
    private String stateDE = "&for=state:10";
    private Options optionsMap;
    public URLTemplate() {
        optionsMap = new Options();
    }

    public String generateURL(String[] options, String state) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseURL);
        for ( int i = 0; i < options.length; i++ ) {
            sb.append(getOptionFromReadableOption(options[i]));
            if ( i != options.length-1 ) {
                sb.append(",");
            }
        }
        sb.append(stateDE);
        return sb.toString();
    }

    private String getOptionFromReadableOption(String option) {
            for ( Map.Entry<String, String> e : optionsMap.getAvailableOptions().entrySet() ) {
                if (e.getValue().equals(option)) {
                    return e.getKey();
                }
            }
        return "KEY NOT FOUND";
    }

    private String readApiKeyFromFile() {
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get("src", "main", "resources", "static", "apiKey");
        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(path, charset) ) {
            line = reader.readLine();
        } catch ( IOException e ) {
            System.err.format("IOException: %s%n", e);
        }
        return line;
    }
}
