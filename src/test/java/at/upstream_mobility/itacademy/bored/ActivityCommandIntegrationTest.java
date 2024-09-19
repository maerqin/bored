package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// Additional imports
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "spring.shell.command.script.enabled=false"
})
@ExtendWith(RequireApiExtension.class)
public class ActivityCommandIntegrationTest {

    @Autowired
    private ActivityCommand activityCommand;

    @MockBean
    private BoredApiClient boredApiClient;

    @Test
    public void testGetCommandWithoutType() {
        String result = activityCommand.get("");
        assertNotNull(result, "Result is null");
        assertFalse(result.isEmpty(), "Result is empty");
    }

    @Test
    public void testGetCommandWithType() {
        String result = activityCommand.get("education");
        assertNotNull(result, "Result is null");
        assertFalse(result.isEmpty(), "Result is empty");
    }

    @Test
    public void testGetCommandWithInvalidType() {
        String result = activityCommand.get("invalidType");
        assertNotNull(result, "Result is null");
        assertTrue(result.contains("No activity found") || result.contains("Error retrieving activity"),
                "Unexpected result message: " + result);
    }

    @Test
    public void testGetCommandWhenApiUnavailable() {
        when(boredApiClient.getActivity("")).thenThrow(new RuntimeException("API is unavailable"));

        String result = activityCommand.get("");
        assertTrue(result.contains("Error retrieving activity"), "Unexpected result message");
    }
}