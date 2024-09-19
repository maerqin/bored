package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.model.Activity;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BoredApiClientTest {
    private BoredApiClient boredApiClient;
    private DefaultApi mockApiInstance;

    @BeforeEach
    public void setUp() {
        mockApiInstance = mock(DefaultApi.class);
        boredApiClient = new BoredApiClient(mockApiInstance);
    }

    @Test
    public void testConstructor() {
        BoredApiClient client = new BoredApiClient();
        assertNotNull(client, "BoredApiClient instance should not be null");
    }

    @Test
    public void testGetActivitySuccess() throws ApiException {
        Activity activity = new Activity();
        activity.setActivity("Go for a run");

        ApiResponse<Activity> apiResponse = new ApiResponse<>(200, null, activity);

        when(mockApiInstance.apiActivityGetWithHttpInfo("")).thenReturn(apiResponse);

        String result = boredApiClient.getActivity("");
        assertEquals("Go for a run", result);
    }

    @Test
    public void testGetActivitySuccessWithType() throws ApiException {
        Activity activity = new Activity();
        activity.setActivity("Learn to play a guitar");

        ApiResponse<Activity> apiResponse = new ApiResponse<>(200, null, activity);

        when(mockApiInstance.apiActivityGetWithHttpInfo("education")).thenReturn(apiResponse);

        String result = boredApiClient.getActivity("education");
        assertEquals("Learn to play a guitar", result);
    }

    @Test
    public void testGetActivityNoActivityFound() throws ApiException {
        Activity activity = new Activity(); // activity.getActivity() will be null

        ApiResponse<Activity> apiResponse = new ApiResponse<>(200, null, activity);

        when(mockApiInstance.apiActivityGetWithHttpInfo("invalidType")).thenReturn(apiResponse);

        String result = boredApiClient.getActivity("invalidType");
        assertEquals("No activity found!", result);
    }

    @Test
    public void testGetActivityApiException() throws ApiException {
        when(mockApiInstance.apiActivityGetWithHttpInfo(anyString()))
                .thenThrow(new ApiException("API error"));

        String result = boredApiClient.getActivity("education");
        assertTrue(result.contains("Error retrieving activity"), "Expected error message");
    }

    @Test
    public void testGetActivityGeneralException() throws ApiException {
        when(mockApiInstance.apiActivityGetWithHttpInfo(anyString()))
                .thenThrow(new RuntimeException("Connection error"));

        String result = boredApiClient.getActivity("");
        assertTrue(result.contains("Error retrieving activity"), "Expected error message");
    }

    @Test
    public void testGetActivityNon200Response() throws ApiException {
        // Create a response with a 404 status code
        ApiResponse<Activity> apiResponse = new ApiResponse<>(404, null, null);

        // Configure the mock to return this response
        when(mockApiInstance.apiActivityGetWithHttpInfo(anyString())).thenReturn(apiResponse);

        String result = boredApiClient.getActivity("anyType");

        // Verify that the correct error message is returned
        assertEquals("Error retrieving activity: Received status code 404", result);
    }

    @Test
    public void testGetActivityNullActivity() throws ApiException {
        // Simulate a successful API response with null data
        ApiResponse<Activity> apiResponse = new ApiResponse<>(200, null, null);

        // Configure the mock to return this response
        when(mockApiInstance.apiActivityGetWithHttpInfo(anyString())).thenReturn(apiResponse);

        String result = boredApiClient.getActivity("anyType");

        // Verify that the correct message is returned
        assertEquals("No activity found!", result);
    }
}