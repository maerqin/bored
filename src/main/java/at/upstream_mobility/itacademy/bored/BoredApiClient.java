package at.upstream_mobility.itacademy.bored;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
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
        client.setBasePath("https://bored.api.lewagon.com");
        this.apiInstance = new DefaultApi(client);
    }

    public BoredApiClient(DefaultApi apiInstance) {
        this.apiInstance = apiInstance;
    }

    /**
     * Retrieves an activity from the Bored API, optionally filtered by type.
     *
     * @param type the type of activity to filter by; if empty or null, retrieves a random activity
     * @return the description of the activity, or an error message if retrieval fails
     */
    public String getActivity(String type) {
        try {
            ApiResponse<Activity> response = apiInstance.apiActivityGetWithHttpInfo(type);
            if (response.getStatusCode() == 200) {
                Activity activity = response.getData();
                if (activity != null && activity.getActivity() != null) {
                    return activity.getActivity();
                } else {
                    logger.warn("No activity found for type: {}", type);
                    return "No activity found!";
                }
            } else {
                logger.warn("Received non-200 response: {}", response.getStatusCode());
                return "Error retrieving activity: Received status code " + response.getStatusCode();
            }
        } catch (ApiException e) {
            logger.error("Error retrieving activity", e);
            return "Error retrieving activity: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Error retrieving activity", e);
            return "Error retrieving activity: API is unavailable";
        }
    }

}

