package hu.unideb.inf;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.unideb.inf.model.*;
import org.h2.tools.Server;

public class Application {
// picsafasz és alma
    public static void main(String[] args) throws SQLException {
        startDatabase();

        try(VeradoDAO aDAO = new JpaVeradoDAO();){
            Verado a=new Verado();
            a.setNev("Minta Bela");
            a.setMennyiseg(200);
            a.setVercsoport("+A");
            aDAO.saveVerado(a);


                Korhaz korhaz = new Korhaz();
                korhaz.setNev("KisPistNagyKorhaza");
                korhaz.setIdo("8:25");
                korhaz.getVeradok().add(a);
                aDAO.saveKorhaz(korhaz);


        }catch(Exception e){
            e.printStackTrace();
        }



    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
