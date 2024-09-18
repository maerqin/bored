package at.upstream_mobility.itacademy.bored;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.model.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BoredApiClient {

    private static final Logger logger = LoggerFactory.getLogger(BoredApiClient.class);

    private final DefaultApi apiInstance;

    public BoredApiClient() {
        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080");
        this.apiInstance = new DefaultApi(client);
    }

    /**
     * Retrieves an activity from the Bored API with an optional type filter.
     *
     * @param type The type of activity to filter by.
     * @return The activity description.
     */
    public String getActivity(String type) {
        try {
            Activity activity = apiInstance.apiActivityGet(type);
            if (activity != null && activity.getActivity() != null) {
                return activity.getActivity();
            } else {
                logger.warn("No activity found for type: {}", type);
                return "No activity found!";
            }
        } catch (ApiException e) {
            logger.error("Error retrieving activity", e);
            return "Error retrieving activity: " + e.getMessage();
        }
    }
}

