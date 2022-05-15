
package hu.unideb.inf.controller;


import hu.unideb.inf.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.bytebuddy.pool.TypePool;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class FXMLStudentsSceneController implements Initializable {
    VeradoDAO aDAO = new JpaVeradoDAO();
    Korhaz help=new Korhaz();

    Korhaz korhaz = new Korhaz();
    List<Korhaz> korhazLista = new ArrayList<>();
    List<Verado> veradoLista= new ArrayList<>();
    int MaxMennyiseg;
    int MinMennyiseg;
    public Boolean CheckIfConNumb(String s){


        for (int i = 0; i < s.length(); i++){

            if( Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    public Boolean CheckIfConChar(String s){


        for (int i = 0; i < s.length(); i++){

            if(!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    public void InformPopUpWindow(String s){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, s);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
    public void WarningPopUpWindow(String s){
        Alert alert = new Alert(Alert.AlertType.WARNING, s);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }


////////////////////////////////////////Veradas Tab////////////////////////////////////////


//BUTTON INICIALIZALAS

    @FXML
    private Button myButton;
    @FXML
    private Button myTorolButton;
    @FXML
    private Button myKeresButton;

    //BUTTON INICIALIZALAS


   //VERADO TABLA

   @FXML
   private TableView<Verado> VeradoTabla;

    @FXML
   private TableColumn<Verado, Integer> AzonositoOszlop;

    @FXML
    private TableColumn<Korhaz, Integer> KorhazIDOszlop;

    @FXML
    private TableColumn<Verado, String> MennyisegOszlop;

    @FXML
    private TableColumn<Verado, String> NevOszlop;

    @FXML
    private TableColumn<Verado, String> VercsoportOszlop;

//VERADO TABLA VEGE

    //VERADO BUTTONOK
    /*@FXML
    void VeradoHozzaadButtonPushed(ActionEvent event) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        String[] VercsoportTomb = {"A+", "A-","B+","B-","AB+","AB-","O+","O-"};
    //Verado s = new Verado();

    Boolean mezoures = false;
    Boolean vercsoportcontain = false;


       if(VeradoMennyisegField.getText().equals("") || VeradoNevField.getText().equals("") || VeradoVercsoportField.getText().equals("")){
           WarningPopUpWindow("Hiba! Ne hagyj üres mezőt.");
           mezoures = true;
       }
       else {
           if (CheckIfConChar(VeradoMennyisegField.getText())){
              WarningPopUpWindow("Hiba! Csak számot adj meg mennyiségnek.");

           }

           for (int i = 0; i < VercsoportTomb.length; i++) {
               if (VercsoportTomb[i].equals(VeradoVercsoportField.getText())) {
                   vercsoportcontain = true;
                   break;
               }
           }
           if (vercsoportcontain == false) {
              WarningPopUpWindow("Hiba! Nem megfelelő vércsoport formátum.");
           }

           for (int i = 0; i < VeradoNevField.getText().length(); i++) {

               if (CheckIfConNumb(VeradoNevField.getText())) {
                 WarningPopUpWindow("Hiba! Rossz név formátum.");
                   break;
               }

           }
       }

    if (CheckIfConChar(VeradoMennyisegField.getText()) == false && mezoures == false && vercsoportcontain == true && CheckIfConNumb(VeradoNevField.getText()) == false) {
        //Verado s = new Verado(VeradoNevField.getText(), VeradoVercsoportField.getText(), Integer.parseInt(VeradoMennyisegField.getText()));
        Verado s = new Verado();
        s.setNev(VeradoNevField.getText());
        s.setVercsoport(VeradoVercsoportField.getText());
        s.setMennyiseg(Integer.parseInt(VeradoMennyisegField.getText()));
        veradoLista.add(s);


        /*entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();*/
       /* aDAO.saveVerado(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sikeres Adatfelvétel!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }


    }*/

    @FXML
    void VeradoKeresButtonPushed(ActionEvent event) {
        if(!VeradoMennyisegMin.getText().equals("") && !VeradoMennyisegMax.getText().equals("")) {
            if (CheckIfConChar(VeradoMennyisegMin.getText()) || CheckIfConChar(VeradoMennyisegMax.getText())) {
                WarningPopUpWindow("Hiba! Csak számot adj meg mennyiségnek.");
            }
            else
            {
                MaxMennyiseg = Integer.parseInt(VeradoMennyisegMax.getText());
                MinMennyiseg = Integer.parseInt(VeradoMennyisegMin.getText());
            }
        }
        else if(VeradoMennyisegMin.getText().equals("") && !VeradoMennyisegMax.getText().equals("")) {
            if (CheckIfConChar(VeradoMennyisegMax.getText())) {
                WarningPopUpWindow("Hiba! Csak számot adj meg mennyiségnek.");
            }
            else
            {
             MaxMennyiseg = Integer.parseInt(VeradoMennyisegMax.getText());
            }
        }
        else if(!VeradoMennyisegMin.getText().equals("") && VeradoMennyisegMax.getText().equals("")) {
            if (CheckIfConChar(VeradoMennyisegMin.getText()) || CheckIfConChar(VeradoMennyisegMax.getText())) {
                WarningPopUpWindow("Hiba! Csak számot adj meg mennyiségnek.");
            }
            else{
                MinMennyiseg = Integer.parseInt(VeradoMennyisegMin.getText());
            }
        }



        }


    @FXML
    void VeradoTorolButtonPushed(ActionEvent event) {

    }
//VERADO BUTTONOK VEGE


    //VERCSOPORT BOOLEAN
    @FXML
    void SetVercsoportABMinusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportABPlusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportAMinusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportAPlusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportBMinusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportBPlusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportOMinusz(ActionEvent event) {

    }

    @FXML
    void SetVercsoportOPlusz(ActionEvent event) {

    }

//VERCSOPORT BOOLEAN VEGE


    //VER MENNYISEG
    @FXML
    private TextField VeradoMennyisegMax;

    @FXML
    private TextField VeradoMennyisegMin;
    //VER MENNYISEG VEGE


//VERADO TEXTFIELD
    @FXML
    private TextField VeradoNevField;
    @FXML
    private TextField VeradoMennyisegField;
    @FXML
    private TextField VeradoKeresoField;
    @FXML
    private TextField VeradoVercsoportField;


////////////////////////////////////////Verado pont Tab///////////////////////////////////////



//VERADOPONT TABLA

    @FXML
    private TableView<?> VeradoPontTabla;
    @FXML
    private TableColumn<Korhaz, Integer> VeradoPontAzonositoOszlop;
    @FXML
    private TableColumn<Korhaz, String> VeradoPontNyitvatartasOszlop;
    @FXML
    private TableColumn<Korhaz, String> VeradoPontNeveOszlop;
    @FXML
    private TableColumn<Korhaz, String> VeradoPontHelyeOszlop;
    @FXML
    private TableColumn<Korhaz, Boolean> VeradoPontJuttatasOszlop;

    //VERADOPONT TABLA VEGE



    //VERADOPONT TEXTFIELD

    @FXML
    private TextField VeradoPontNyitvatartasField;
    @FXML
    private TextField VeradoPontHelyField;
    @FXML
    private TextField VeradoPontKeresoField;
    @FXML
    private TextField VeradoPontJuttatasField;
    @FXML
    private TextField VeradoPontNeveField;

    //VERADOPONT TEXTFIELD VEGE



    //NYITVATARTAS

    @FXML
    private TextField VeradoPontNyitvatartasMinField;
    @FXML
    private TextField VeradoPontNyitvatartasMaxField;

    //NYITVATARTAS



    //VERADOPONT BUTTON
    @FXML
    private Button VeradoPontHButton;

    @FXML
    private Button VeradoPontHButton1;

    @FXML
    private TextField korhazHozzaField;


    /*public static void beolvas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
//Select all the record from student table
            Query query = em.createQuery("SELECT st FROM Verado st");
            List lst = query.getResultList();
            Iterator it = lst.iterator();
            while (it.hasNext()){
                Verado beolvasas = (Verado) it.next();
                Korhaz beolvaso = (Korhaz) it.next();
                System.out.print("Id:"+beolvasas.getId());
                System.out.print(" mennyiseg:"+beolvasas.getMennyiseg());
                System.out.print(" nev:"+beolvasas.getNev());
                System.out.print(" vercsoport:"+beolvasas.getVercsoport());

            }
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            em.close();
        }
    }*/



    @FXML
    void VeradoPontHozzaadButtonPushed(ActionEvent event) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Korhaz beolvaso;
        /*List<Verado> beolvasott1=null;
        List<Korhaz> beolvasott2=null;
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("Select st FROM Verado st");
            List Ist = query.getResultList();
            Iterator it = Ist.iterator();
            while(it.hasNext()){
                Verado beolvasas= (Verado) it.next();
                beolvasott1= (List<Verado>) beolvasas;
                beolvaso= (Korhaz) it.next();
                beolvasott2= (List<Korhaz>) beolvaso;
                for (int i=0; i<korhazLista.size();i++){
                    if(korhazLista.get(i).getNev().equals(korhazHozzaField.getText())){
                        help=korhazLista.get(i);
                    }

                }
                for (int i=0; i<((List<Korhaz>) beolvaso).size();i++){
                    if(beolvaso.getNev().equals(korhazHozzaField.getText())){
                        help=beolvaso;
                    }
                }
            }
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            entityManager.close();
        }*/
        Korhaz korhaz = new Korhaz();
        List<Korhaz> korhazLista = new ArrayList<>();
        korhaz.setNev(VeradoPontNeveField.getText());
        korhaz.setIdo(VeradoPontNyitvatartasField.getText());
        if(!VeradoPontJuttatasField.getText().equals("")) {
            Boolean seged=null;
            if (VeradoPontJuttatasField.getText().equals("true")) {
                seged = true;
            } else if (VeradoPontJuttatasField.getText().equals("false")) {
                seged = false;
            }

            korhaz.setJuttatas(seged);
        }
        korhaz.setHelyszin(VeradoPontHelyField.getText());
        korhazLista.add(korhaz);
        /*entityManager.getTransaction().begin();
        entityManager.persist(korhaz);
        entityManager.getTransaction().commit();*/
        //System.out.println(korhazLista);
        /*for(Korhaz korhazseged : korhazLista){
            System.out.println(korhazseged.getNev());
            if(korhazseged.getNev().equals(korhazHozzaField.getText())){
                help=korhazseged;
                System.out.println(korhazseged);

            }
        }*/
        for (int i=0; i<korhazLista.size();i++){
            if(korhazLista.get(i).getNev().equals(korhazHozzaField.getText())){
                help=korhazLista.get(i);
            }
        }
        System.out.println(help.getNev());
        System.out.println(korhazHozzaField.getText());
        //korhaz.getVeradok().add(s);
        aDAO.saveKorhaz(korhaz);
    }
    @FXML
    void VeradoHozzaadButtonPushed(ActionEvent event) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        String[] VercsoportTomb = {"A+", "A-","B+","B-","AB+","AB-","O+","O-"};
        //Verado s = new Verado();

        Boolean mezoures = false;
        Boolean vercsoportcontain = false;


        if(VeradoMennyisegField.getText().equals("") || VeradoNevField.getText().equals("") || VeradoVercsoportField.getText().equals("")){
            WarningPopUpWindow("Hiba! Ne hagyj üres mezőt.");
            mezoures = true;
        }
        else {
            if (CheckIfConChar(VeradoMennyisegField.getText())){
                WarningPopUpWindow("Hiba! Csak számot adj meg mennyiségnek.");

            }

            for (int i = 0; i < VercsoportTomb.length; i++) {
                if (VercsoportTomb[i].equals(VeradoVercsoportField.getText())) {
                    vercsoportcontain = true;
                    break;
                }
            }
            if (vercsoportcontain == false) {
                WarningPopUpWindow("Hiba! Nem megfelelő vércsoport formátum.");
            }

            for (int i = 0; i < VeradoNevField.getText().length(); i++) {

                if (CheckIfConNumb(VeradoNevField.getText())) {
                    WarningPopUpWindow("Hiba! Rossz név formátum.");
                    break;
                }

            }
        }

        if (CheckIfConChar(VeradoMennyisegField.getText()) == false && mezoures == false && vercsoportcontain == true && CheckIfConNumb(VeradoNevField.getText()) == false) {
            //Verado s = new Verado(VeradoNevField.getText(), VeradoVercsoportField.getText(), Integer.parseInt(VeradoMennyisegField.getText()));
            Verado s = new Verado();
            s.setNev(VeradoNevField.getText());
            s.setVercsoport(VeradoVercsoportField.getText());
            s.setMennyiseg(Integer.parseInt(VeradoMennyisegField.getText()));
            veradoLista.add(s);

            /*for(Korhaz korhazseged:korhazLista){
                if(korhazseged.getNev().equals(korhazHozzaField.getText())){
                    help.getVeradok().add(s);
                    System.out.println(korhazseged);

                }
            }*/
            help.getVeradok().add(s);



        /*entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();*/
            aDAO.saveVerado(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sikeres Adatfelvétel!");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }


    }










    @FXML
    void VeradoPontKeresButtonPushed(ActionEvent event) {

    }

    @FXML
    void VeradoPontTorolButtonPushed(ActionEvent event) {

    }

//VERADOPONT GOMBOK VEGE



    //TELEPULES BOOLEAN

    @FXML
    void SetZahonyBoolean(ActionEvent event) {

    }
    @FXML
    void SetBudapestBoolean(ActionEvent event) {

    }

    @FXML
    void SetDebrecenBoolean(ActionEvent event) {

    }

    @FXML
    void SetGyorBoolean(ActionEvent event) {

    }

    @FXML
    void SetKiskunhalasBoolean(ActionEvent event) {

    }

    @FXML
    void SetMiskolcBoolean(ActionEvent event) {

    }

    @FXML
    void SetNyíiregyhazaBoolean(ActionEvent event) {

    }

    @FXML
    void SetPecsBoolean(ActionEvent event) {

    }

    @FXML
    void SetSiofokBoolean(ActionEvent event) {

    }

    @FXML
    void SetSzegedBoolean(ActionEvent event) {

    }

    @FXML
    void SetSzolnokBoolean(ActionEvent event) {

    }

    @FXML
    void SetUjfehertoBoolean(ActionEvent event) {

    }

//TELEPULESEK BOOLEAN VEGE



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
