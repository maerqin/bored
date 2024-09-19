package at.upstream_mobility.itacademy.bored;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(ActivityCommand.class)
public class ActivityCommandTest {

    @MockBean
    private BoredApiClient boredApiClient;

    @Autowired
    private ActivityCommand activityCommand;

    @Test
    public void testGetCommandWithType() {
        when(boredApiClient.getActivity("education")).thenReturn("Learn a new programming language");

        String result = activityCommand.get("education");
        assertEquals("Learn a new programming language", result);
    }

    @Test
    public void testGetCommandWithoutType() {
        when(boredApiClient.getActivity(null)).thenReturn("Take a walk in the park");

        String result = activityCommand.get(null);
        assertEquals("Take a walk in the park", result);
    }

    @Test
    public void testGetCommandWithInvalidType() {
        when(boredApiClient.getActivity("invalidType")).thenReturn("No activity found!");

        String result = activityCommand.get("invalidType");
        assertEquals("No activity found!", result);
    }

    @Test
    public void testGetCommandWithApiException() {
        when(boredApiClient.getActivity("education")).thenThrow(new RuntimeException("API error"));

        String result = activityCommand.get("education");
        assertTrue(result.contains("Error retrieving activity"), "Unexpected result message");
    }
}