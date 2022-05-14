package hu.unideb.inf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Korhaz {

    @Id
    @GeneratedValue
    private int Id;
    private String nev;
    private String ido;
    private boolean juttatas;
    private String helyszin;

    public boolean getJuttatas() {
        return juttatas;
    }

    public void setJuttatas(boolean juttatas) {
        this.juttatas = juttatas;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "korhaz")
    private List<Verado> veradok=new ArrayList<>();

    public int getId() {
        return Id;
    }

    public String getNev() {
        return nev;
    }

    public String getIdo() {
        return ido;
    }

    public List<Verado> getVeradok() {
        return veradok;
    }



    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public void setVeradok(List<Verado> veradok) {
        this.veradok = veradok;
    }
}
