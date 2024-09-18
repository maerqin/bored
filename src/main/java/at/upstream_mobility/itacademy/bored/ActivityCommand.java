package at.upstream_mobility.itacademy.bored;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ActivityCommand {

    private final BoredApiClient boredApiClient;

    public ActivityCommand(BoredApiClient boredApiClient) {
        this.boredApiClient = boredApiClient;
    }

    /**
     * Retrieves an activity. Optionally filters by type.
     *
     * @param type The type of activity (optional).
     * @return The activity description.
     */
    @ShellMethod("Get an activity.")
    public String get(
            @ShellOption(defaultValue = "", valueProvider = TypeValueProvider.class) String type
    ) {
        return boredApiClient.getActivity(type);
    }
}