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


    public Korhaz() {
    }
    public Korhaz(String helyszin, String ido, boolean juttatas, String nev) {

        this.helyszin=helyszin;
        this.ido=ido;
        this.juttatas=juttatas;
        this.nev=nev;
    }

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

    public void setId(int id) {
        this.Id = id;
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

    @Override
    public String toString() {
        return this.Id + " " + this.nev + " "+ this.ido+ " "+ this.juttatas+ " "+ this.helyszin;
    }
}