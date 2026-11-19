package pe.udh.gestionacademica.util;

import java.util.concurrent.atomic.AtomicInteger;

public class GeneradorId {

    private final AtomicInteger siguiente;

    public GeneradorId(int inicio) {
        this.siguiente = new AtomicInteger(inicio);
    }

    public int siguiente() {
        return siguiente.getAndIncrement();
    }
}
