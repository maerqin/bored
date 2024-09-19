package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.Test;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.command.CommandOption;
import org.springframework.shell.command.CommandRegistration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class TypeValueProviderTest {

    @Test
    public void testCompleteWithNoPrefix() {
        TypeValueProvider valueProvider = new TypeValueProvider();

        List<String> words = Arrays.asList("");
        int wordIndex = 0;
        int position = 0;

        // Create mock instances of CommandRegistration and CommandOption
        CommandRegistration commandRegistration = mock(CommandRegistration.class);
        CommandOption commandOption = mock(CommandOption.class);

        CompletionContext completionContext = new CompletionContext(
                words,
                wordIndex,
                position,
                commandRegistration,
                commandOption
        );

        List<CompletionProposal> proposals = valueProvider.complete(completionContext);

        assertNotNull(proposals, "Proposals should not be null");
        assertFalse(proposals.isEmpty(), "Proposals should not be empty");
        assertEquals(9, proposals.size(), "There should be 9 proposals");
        assertTrue(proposals.stream().anyMatch(p -> p.value().equals("education")), "Should contain 'education'");
    }

    @Test
    public void testCompleteWithValidPrefix() {
        TypeValueProvider valueProvider = new TypeValueProvider();

        List<String> words = Arrays.asList("ed");
        int wordIndex = 0;
        int position = 2;

        // Create mock instances of CommandRegistration and CommandOption
        CommandRegistration commandRegistration = mock(CommandRegistration.class);
        CommandOption commandOption = mock(CommandOption.class);

        CompletionContext completionContext = new CompletionContext(
                words,
                wordIndex,
                position,
                commandRegistration,
                commandOption
        );

        List<CompletionProposal> proposals = valueProvider.complete(completionContext);

        assertNotNull(proposals, "Proposals should not be null");
        assertFalse(proposals.isEmpty(), "Proposals should not be empty");
        assertEquals(1, proposals.size(), "There should be 1 proposal");
        assertEquals("education", proposals.get(0).value(), "Proposal should be 'education'");
    }

    @Test
    public void testCompleteWithInvalidPrefix() {
        TypeValueProvider valueProvider = new TypeValueProvider();

        List<String> words = Arrays.asList("x");
        int wordIndex = 0;
        int position = 1;

        // Create mock instances of CommandRegistration and CommandOption
        CommandRegistration commandRegistration = mock(CommandRegistration.class);
        CommandOption commandOption = mock(CommandOption.class);

        CompletionContext completionContext = new CompletionContext(
                words,
                wordIndex,
                position,
                commandRegistration,
                commandOption
        );

        List<CompletionProposal> proposals = valueProvider.complete(completionContext);

        assertNotNull(proposals, "Proposals should not be null");
        assertTrue(proposals.isEmpty(), "Proposals should be empty for invalid prefix");
    }
}

