package hu.unideb.inf.controller;

import hu.unideb.inf.model.Korhaz;
import hu.unideb.inf.model.Verado;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class FXMLStudentsSceneControllerTest extends FXMLStudentsSceneController {

    @Test
    void checkIfConCharwithValidArgument() {
        boolean a = CheckIfConChar("alma");
        assertEquals(true, a);
    }

    @Test
    void checkIfConCharwithInValidArgument() {
        boolean a = CheckIfConChar("");
        assertEquals(false, a);
    }

    @Test
    void CheckIfConNumbwithValidArgument()
    {
        boolean a = CheckIfConNumb("1");
        assertEquals(true, a);
    }
    @Test
    void CheckIfConNumbwithInValidArgument()
    {
        boolean a = CheckIfConNumb("a");
        assertEquals(false, a);
    }

    @Test
    void CheckIdoConditiontolWithValidArgument()
    {
        boolean a = IdoConditiontol("08:00-16:00",8, 00, 16, 00);
        assertEquals(true, a);
    }

    @Test
    void CheckTimeFormatumWithValidArgument()
    {
        boolean a =CheckTimeFormatum("08:00-16:00");
        assertEquals(true, a);
    }

    Korhaz korhaztest = new Korhaz("Kenya","08:00-16:00", true, "Kenya kft");

    @Test
    void KorhazValidCheckJuttatas()
    {
        boolean a = korhaztest.getJuttatas();
        assertEquals(true, a);
    }

    @Test
    void KorhazInValidCheckJuttatas()
    {
        boolean a = korhaztest.getJuttatas();
        assertEquals(false, !a);
    }

    @Test
    void KorhazValidCheckHelyszin()
    {

        boolean a = korhaztest.getHelyszin().getClass().equals(String.class);
        assertEquals(true, a);
    }

    @Test
    void KorhazInValidCheckHelyszin()
    {

        boolean a = korhaztest.getHelyszin().getClass().equals(Integer.class);
        assertEquals(false, a);
    }

    @Test
    void KorhazValidCheckNev()
    {

        boolean a = korhaztest.getNev().getClass().equals(String.class);
        assertEquals(true, a);
    }

    @Test
    void KorhazInValidCheckNev()
    {

        boolean a = korhaztest.getNev().getClass().equals(Integer.class);
        assertEquals(false, a);
    }

    @Test
    void KorhazValidCheckIdo()
    {

        boolean a = korhaztest.getIdo().getClass().equals(String.class);
        assertEquals(true, a);
    }

    @Test
    void KorhazInValidCheckIdo()
    {

        boolean a = korhaztest.getIdo().getClass().equals(Integer.class);
        assertEquals(false, a);
    }

    @Test
    void KorhazValidCheckID()
    {
        int id = korhaztest.getId();
        boolean a;
        if (id == (int)id)
        {
            a = true;
            assertEquals(true, a);
        }
        else
        {
            a = false;
            assertEquals(true, a);
        }


    }

    @Test
    void KorhazInValidCheckID()
    {
        int id = korhaztest.getId();
        boolean a;
        if (!(id == (int)id))
        {
            a = false;
            assertEquals(true, a);
        }
        else
        {
            a = true;
            assertEquals(true, a);
        }


    }





}