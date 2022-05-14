package hu.unideb.inf.model;

import java.util.List;

public interface VeradoDAO extends AutoCloseable {

    public void saveVerado(Verado a);

    public void deleteVerado(Verado a);
    public void updateVerado(Verado a);
    public List<Verado> getVeradok();
    public default void saveKorhaz(Korhaz korhaz){
        throw new UnsupportedOperationException();
    }

}