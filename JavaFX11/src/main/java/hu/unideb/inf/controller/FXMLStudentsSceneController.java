
package hu.unideb.inf.controller;


import hu.unideb.inf.model.*;
import java.net.URL;
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


public class FXMLStudentsSceneController implements Initializable {
    VeradoDAO aDAO = new JpaVeradoDAO();
    Verado s = new Verado();
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
        s.setNev(VeradoNevField.getText());
        s.setVercsoport(VeradoVercsoportField.getText());
        s.setMennyiseg(Integer.parseInt(VeradoMennyisegField.getText()));

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
    void VeradoPontHozzaadButtonPushed(ActionEvent event) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Korhaz korhaz = new Korhaz();
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
        /*entityManager.getTransaction().begin();
        entityManager.persist(korhaz);
        entityManager.getTransaction().commit();*/
        korhaz.getVeradok().add(s);
        aDAO.saveKorhaz(korhaz);
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
