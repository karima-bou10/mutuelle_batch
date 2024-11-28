package org.example.mutuelle_batch.policy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

@Component
public class CustomSkipPolicy implements SkipPolicy {
//Un SkipPolicy permet de spécifier quelles exceptions peuvent être ignorées et combien de fois
    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException{
        // Autorisez le skip pour une exception spécifique et limitez le nombre d'itérations
        if (t instanceof IllegalArgumentException && skipCount < 5) {
            return true; // Skip autorisé
        }
        return false; // Pas de skip
    }
}
