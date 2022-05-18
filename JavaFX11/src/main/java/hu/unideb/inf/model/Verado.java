package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Verado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nev;
    private String vercsoport;
    private int mennyiseg;
    private int KorhazID;

    public int getKorhazID() {
        return KorhazID;
    }

    public void setKorhazID(int korhazID) {
        KorhazID = korhazID;
    }
    //ArrayList<Verado> veradolista = new ArrayList<Verado>();

    public Verado() {
    }


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

    @Override
    public String toString() {
        return this.nev + " " + this.vercsoport + " "+ this.mennyiseg;
    }
}