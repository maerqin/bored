package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Additional imports
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "spring.shell.command.script.enabled=false"
})
@ExtendWith(RequireApiExtension.class)
public class ActivityCommandIntegrationTest {

    @Autowired
    private ActivityCommand activityCommand;

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
}