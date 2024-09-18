package at.upstream_mobility.itacademy.bored;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BoredApiClient {

    private static final Logger logger = LoggerFactory.getLogger(BoredApiClient.class);

    private static final String API_URL = "http://localhost:8080/api/activity";

    private final RestTemplate restTemplate;

    public BoredApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves an activity from the Bored API with an optional type filter.
     *
     * @param type The type of activity to filter by.
     * @return The activity description.
     */
    public String getActivity(String type) {
        String url = API_URL;
        if (type != null && !type.isEmpty()) {
            url += "?type=" + type;
        }

        try {
            Activity activity = restTemplate.getForObject(url, Activity.class);
            if (activity != null && activity.getActivity() != null) {
                return activity.getActivity();
            } else {
                logger.warn("No activity found for type: {}", type);
                return "No activity found!";
            }
        } catch (Exception e) {
            logger.error("Error retrieving activity", e);
            return "Error retrieving activity: " + e.getMessage();
        }
    }
}

