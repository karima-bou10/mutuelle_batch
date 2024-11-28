package org.example.mutuelle_batch.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSkipListener implements SkipListener<Object, Object> {
//Un SkipListener permet de suivre les éléments qui ont été ignorés
    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println("Erreur ignorée lors de la lecture : " + t.getMessage());
    }
    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        System.out.println("Erreur ignorée lors de l'écriture de l'élément : " + item + ", Erreur : " + t.getMessage());
    }
    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        System.out.println("Erreur ignorée lors du traitement de l'élément : " + item + ", Erreur : " + t.getMessage());
    }
}
