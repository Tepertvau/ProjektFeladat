package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Verado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nev;
    private String vercsoport;
    private int mennyiseg;

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public String getVercsoport() {
        return vercsoport;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setVercsoport(String vercsoport) {
        this.vercsoport = vercsoport;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
}
