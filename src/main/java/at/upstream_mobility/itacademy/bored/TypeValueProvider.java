package at.upstream_mobility.itacademy.bored;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.shell.CompletionProposal;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeValueProvider implements ValueProvider {

    private static final List<String> TYPES = Arrays.asList(
            "education", "recreational", "social", "diy", "charity",
            "cooking", "relaxation", "music", "busywork"
    );

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        return TYPES.stream()
                .map(CompletionProposal::new)
                .collect(Collectors.toList());
    }
}
