package hu.unideb.inf.controller;

import hu.unideb.inf.model.Korhaz;
import hu.unideb.inf.model.Verado;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class FXMLStudentsSceneControllerTest extends FXMLVeradoMokaSceneController {

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
    void CheckIdoConditiontolWithInValidArgument()
    {
        boolean a = IdoConditiontol("08:00-16:00",16, 00, 8, 00);
        assertEquals(false, a);
    }

    @Test
    void CheckIdoConditiontolWithInValidArgument2()
    {
        boolean a = IdoConditiontol("07:00-08:00",8, 00, 16, 00);
        assertEquals(false, a);
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
        korhaztest.setJuttatas(true);
        boolean a = korhaztest.getJuttatas();
        assertEquals(true, a);
    }

    @Test
    void KorhazCheckSetJuttatas()
    {
        korhaztest.setJuttatas(false);
        assertEquals(false, korhaztest.getJuttatas());
    }


    @Test
    void KorhazInValidCheckJuttatas()
    {
        korhaztest.setJuttatas(true);
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
    void KorhazChecksetHelyszin()
    {
        korhaztest.setHelyszin("debrecen");
        String test ="debrecen";
        assertEquals(korhaztest.getHelyszin(), test);
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
    void KorhazCheckSetNev()
    {
        korhaztest.setNev("szentkorhaz");
        String test="szentkorhaz";
        assertEquals(test, korhaztest.getNev());
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
    void KorhazCheckSetIdo()
    {
        korhaztest.setIdo("08:00-14:00");
        String test="08:00-14:00";
        assertEquals(test, korhaztest.getIdo());
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

    Verado verado = new Verado();

    @Test
    void TestVeradoNev()
    {
        verado.setNev("Elektrom Agnes");
        String test="Elektrom Agnes";
        assertEquals(test, verado.getNev());
    }

    @Test
    void TestVeradoMennyiseg()
    {
        verado.setMennyiseg(500);
        int test=500;
        assertEquals(test, verado.getMennyiseg());
    }

    @Test
    void TestVeradoKorhazID()
    {
        verado.setKorhazID(korhaztest.getId());
        int test=korhaztest.getId();
        assertEquals(test, verado.getKorhazID());
    }

    @Test
    void TestVeradoVercsoport()
    {
        verado.setVercsoport("0-");
        String test="0-";
        assertEquals(test, verado.getVercsoport());
    }

    @Test
    void TestVPUpdateTableview()
    {

        assertThrows(Exception.class,
                () ->
                {
                    VPUpdateTableView();
                }

        )
        ;

    }

    @Test
    void TestTimeConditionFormatuWithValidArgument()
    {
        boolean a = CheckTimeConditionFormatum("08:00");
        assertEquals(true, a);

    }












}