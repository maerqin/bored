package at.upstream_mobility.itacademy.bored;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.AssertionErrors;

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
}