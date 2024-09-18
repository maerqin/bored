package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

public class RequireApiExtension implements BeforeAllCallback {

    private static boolean apiAvailable;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!apiAvailable) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getForObject("http://localhost:8080/api/activity", String.class);
                apiAvailable = true;
            } catch (RestClientException e) {
                apiAvailable = false;
            }
        }

        if (!apiAvailable) {
            throw new RuntimeException("Bored API is not available");
        }
    }
}
