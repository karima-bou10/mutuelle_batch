package org.example.mutuelle_batch.policy;

import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomRetryPolicy extends SimpleRetryPolicy {
//Un RetryPolicy vous permet de définir une logique de nouvelle tentative pour des exceptions spécifiques

    public CustomRetryPolicy() {
        // Définissez le nombre de tentatives et les exceptions à gérer
        super(3, Collections.singletonMap(IllegalStateException.class, true));
    }
}
