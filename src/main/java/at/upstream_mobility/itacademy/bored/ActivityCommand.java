package at.upstream_mobility.itacademy.bored;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Shell commands for interacting with the Bored API.
 *
 * <p>This component defines command-line commands for retrieving activities.</p>
 */
@ShellComponent
public class ActivityCommand {

    private final BoredApiClient boredApiClient;

    public ActivityCommand(BoredApiClient boredApiClient) {
        this.boredApiClient = boredApiClient;
    }

    /**
     * Retrieves an activity from the Bored API.
     *
     * <p>This command fetches an activity, optionally filtered by the specified type.</p>
     *
     * @param type the type of activity to filter by (optional)
     * @return the description of the activity
     */
    @ShellMethod("Get an activity.")
    public String get(
            @ShellOption(defaultValue = "", valueProvider = TypeValueProvider.class) String type
    ) {
        String result;
        try {
            result = boredApiClient.getActivity(type);
            if (result == null) {
                result = "Error retrieving activity: Result is null";
            }
        } catch (Exception e) {
            result = "Error retrieving activity: " + e.getMessage();
        }
        return result;
    }
}