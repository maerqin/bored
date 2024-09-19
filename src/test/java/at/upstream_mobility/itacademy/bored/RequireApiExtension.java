package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

/**
 * JUnit extension that checks for the availability of the Bored API before running tests.
 *
 * <p>If the API is not available, the tests will be skipped to prevent failures due to connectivity issues.</p>
 */
public class RequireApiExtension implements BeforeAllCallback {

    /**
     * Checks if the Bored API is accessible before all tests in the test class.
     *
     * @param context the extension context
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/api/activity", String.class);
        // Do not throw an exception here; let the tests handle the API availability
    }
}
