
package hu.unideb.inf.controller;


import hu.unideb.inf.model.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class FXMLStudentsSceneController implements Initializable {
    VeradoDAO aDAO = new JpaVeradoDAO();


    Korhaz korhaz = new Korhaz();
    static List<Korhaz> korhazLista = new ArrayList<>();
    static List<Verado> veradoLista= new ArrayList<>();
    int MaxMennyiseg;
    int MinMennyiseg;


    ////////////////////////////////////////Függvények////////////////////////////////////////
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


    public static List<Korhaz> GetKorhaz() throws SQLException {


        ConnectionDB cn = new ConnectionDB();
        Connection cn1 = cn.fileconnection();


        String sql = "SELECT * FROM KORHAZ";
        Statement s = cn1.createStatement();
        ResultSet r = s.executeQuery(sql);

        while (r.next())
        {

            Korhaz kz = new Korhaz();
            kz.setId(r.getInt("Id"));
            kz.setNev(r.getString("nev"));
            kz.setJuttatas(r.getBoolean("juttatas"));
            kz.setIdo(r.getString("ido"));
            kz.setHelyszin(r.getString("helyszin"));
            korhazLista.add(kz);
        }

        return korhazLista;
    }


    public static List<Verado> GetVerado() throws SQLException {


        ConnectionDB cn = new ConnectionDB();
        Connection cn1 = cn.fileconnection();


        String sql = "SELECT * FROM VERADO";
        Statement s = cn1.createStatement();
        ResultSet r = s.executeQuery(sql);

        while (r.next())
        {
            Verado vr = new Verado();
            vr.setId(r.getInt("id"));
            vr.setNev(r.getString("nev"));
            vr.setMennyiseg(r.getInt("mennyiseg"));
            vr.setVercsoport(r.getString("vercsoport"));
            vr.setKorhazID(r.getInt("korhazid"));

            veradoLista.add(vr);
        }

        return veradoLista;
    }


    public void VPUpdateTableView(){
        int i = 0;
        VeradoPontTabla.getItems().clear();
        VeradoPontAzonositoOszlop.setCellValueFactory(new PropertyValueFactory<>("Id"));
        VeradoPontNeveOszlop.setCellValueFactory(new PropertyValueFactory<>("helyszin"));
        VeradoPontNyitvatartasOszlop.setCellValueFactory(new PropertyValueFactory<>("ido"));
        VeradoPontJuttatasOszlop.setCellValueFactory(new PropertyValueFactory<>("juttatas"));
        VeradoPontHelyeOszlop.setCellValueFactory(new PropertyValueFactory<>("nev"));

        try {
            ConnectionDB cn = new ConnectionDB();
            Connection cn1 = cn.fileconnection();

            String sql = "SELECT * FROM KORHAZ";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while (i < korhazLista.size())
            {
                VeradoPontTabla.refresh();

                Korhaz korhazseged = korhazLista.get(i);

                korhazObservableList.add(korhazseged);
                i++;
            }
            FilteredList<Korhaz> filterData = new FilteredList<>(korhazObservableList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            VeradoPontKeresoField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(korhaz -> {
                    // If filter text is empty, display all persons.

                    /*if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }else {*/

                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (korhaz.getNev().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //nev
                        } else if (korhaz.getHelyszin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //vercsoport
                        } else if (String.valueOf(korhaz.getIdo()).indexOf(lowerCaseFilter) != -1) {
                            return true; //mennyiseg
                        }else if (String.valueOf(korhaz.getJuttatas()).indexOf(lowerCaseFilter)!=-1){
                            return true;
                        }
                        else
                            return false; // Does not match.
                    //}
                });


            });
            // 3. Wrap the FilteredList in a SortedList.
            //SortedList<Verado> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            //sortedData.comparatorProperty().bind(VeradoTabla.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            //VeradoTabla.setItems(sortedData);
            VeradoPontTabla.setItems(korhazObservableList);
            //VeradoPontTabla.getItems().clear();
        }catch (Exception e){

        }
    }
    public void VPUpdateTabeleView(){
        int i = 0;
        VeradoPontTabla.getItems().clear();
        VeradoPontAzonositoOszlop.setCellValueFactory(new PropertyValueFactory<>("Id"));
        VeradoPontNeveOszlop.setCellValueFactory(new PropertyValueFactory<>("helyszin"));
        VeradoPontNyitvatartasOszlop.setCellValueFactory(new PropertyValueFactory<>("ido"));
        VeradoPontJuttatasOszlop.setCellValueFactory(new PropertyValueFactory<>("juttatas"));
        VeradoPontHelyeOszlop.setCellValueFactory(new PropertyValueFactory<>("nev"));

            try {
                ConnectionDB cn = new ConnectionDB();
                Connection cn1 = cn.fileconnection();

                String sql = "SELECT * FROM KORHAZ";
                Statement s = cn1.createStatement();
                ResultSet r = s.executeQuery(sql);
                if(VeradoPontKeresoField.getText().equals("")) {
                    while (i < korhazLista.size()) {
                        VeradoPontTabla.refresh();

                        Korhaz korhazseged = korhazLista.get(i);

                        korhazObservableList.add(korhazseged);
                        i++;
                    }
                    VeradoPontTabla.setItems(korhazObservableList);
                }
                else {
                    FilteredList<Korhaz> filterData = new FilteredList<>(korhazObservableList, b -> true);

                    // 2. Set the filter Predicate whenever the filter changes.
                    VeradoPontKeresoField.textProperty().addListener((observable, oldValue, newValue) -> {
                        filterData.setPredicate(korhaz -> {
                            // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }else {

                            // Compare first name and last name of every person with filter text.
                            String lowerCaseFilter = newValue.toLowerCase();

                            if (korhaz.getNev().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true; //nev
                            } else if (korhaz.getHelyszin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true; //vercsoport
                            } else if (String.valueOf(korhaz.getIdo()).indexOf(lowerCaseFilter) != -1) {
                                return true; //mennyiseg
                            }else if (String.valueOf(korhaz.getJuttatas()).indexOf(lowerCaseFilter)!=-1){
                                return true;
                            }
                            else
                                return false; // Does not match.
                            }
                        });


                    });
                    // 3. Wrap the FilteredList in a SortedList.
                    SortedList<Korhaz> sortedData = new SortedList<>(filterData);

                    // 4. Bind the SortedList comparator to the TableView comparator.
                    // 	  Otherwise, sorting the TableView would have no effect.
                    sortedData.comparatorProperty().bind(VeradoPontTabla.comparatorProperty());

                    // 5. Add sorted (and filtered) data to the table.
                    VeradoPontTabla.setItems(sortedData);
                    /*while(i<filterData.size()){
                        VeradoPontTabla.refresh();
                        Korhaz korhazseged2=filterData.get(i);
                        korhazObservableList.add(korhazseged2);

                    }
                    VeradoPontTabla.setItems(korhazObservableList);*/
                }
            }catch (Exception e){

            }



        }
        /*try {
            ConnectionDB cn = new ConnectionDB();
            Connection cn1 = cn.fileconnection();

            String sql = "SELECT * FROM KORHAZ";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while (i < korhazLista.size())
            {
                VeradoPontTabla.refresh();

                Korhaz korhazseged = korhazLista.get(i);

                korhazObservableList.add(korhazseged);
                i++;
            }
            VeradoPontTabla.setItems(korhazObservableList);
        }catch (Exception e){

        }*/







    //private final ObservableList<Verado> dataList = FXCollections.observableArrayList();

    public void VUpdateTabeleView(){
        int i = 0;
        //VeradoTabla.getItems().clear();
        VeradoTabla.getItems().clear();
        AzonositoOszlop.setCellValueFactory(new PropertyValueFactory<>("id"));
        NevOszlop.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));
        VercsoportOszlop.setCellValueFactory(new PropertyValueFactory<>("nev"));
        MennyisegOszlop.setCellValueFactory(new PropertyValueFactory<>("vercsoport"));
        KorhazIDOszlop.setCellValueFactory(new PropertyValueFactory<>("korhazID"));





        try {
            ConnectionDB cn = new ConnectionDB();
            Connection cn1 = cn.fileconnection();

            String sql = "SELECT * FROM VERADO";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while (i < veradoLista.size())
            {
                VeradoTabla.refresh();

                Verado veradoseged = veradoLista.get(i);

                veradoObservableList.add(veradoseged);
                i++;
            }
            //VeradoTabla.setItems(veradoObservableList);
            FilteredList<Verado> filteredData = new FilteredList<>(veradoObservableList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            VeradoKeresoField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(verado -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }else {

                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (verado.getNev().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //nev
                        } else if (verado.getVercsoport().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //vercsoport
                        } else if (String.valueOf(verado.getMennyiseg()).indexOf(lowerCaseFilter) != -1)
                            return true; //mennyiseg
                        else
                            return false; // Does not match.
                    }
                });


            });

            SetBloodCounters();

            //veradoObservableList.add((Verado) veradoObservableList);
            VeradoTabla.setItems(veradoObservableList);

        }catch (Exception e){

        }



    }

    public void VUpdateTableView(){
        int i = 0;
        VeradoTabla.getItems().clear();
        AzonositoOszlop.setCellValueFactory(new PropertyValueFactory<>("id"));
        NevOszlop.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));
        VercsoportOszlop.setCellValueFactory(new PropertyValueFactory<>("nev"));
        MennyisegOszlop.setCellValueFactory(new PropertyValueFactory<>("vercsoport"));
        KorhazIDOszlop.setCellValueFactory(new PropertyValueFactory<>("korhazID"));





        try {
            ConnectionDB cn = new ConnectionDB();
            Connection cn1 = cn.fileconnection();

            String sql = "SELECT * FROM VERADO";
            Statement s = cn1.createStatement();
            ResultSet r = s.executeQuery(sql);

            while (i < veradoLista.size())
            {
                VeradoTabla.refresh();

                Verado veradoseged = veradoLista.get(i);

                veradoObservableList.add(veradoseged);
                i++;
            }
            //VeradoTabla.setItems(veradoObservableList);
            FilteredList<Verado> filteredData = new FilteredList<>(veradoObservableList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            VeradoKeresoField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(verado -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }else {

                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (verado.getNev().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //nev
                        } else if (verado.getVercsoport().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; //vercsoport
                        } else if (String.valueOf(verado.getMennyiseg()).indexOf(lowerCaseFilter) != -1)
                            return true; //mennyiseg
                        else
                            return false; // Does not match.
                    }
                });


            });
            // 3. Wrap the FilteredList in a SortedList.
            //SortedList<Verado> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            //sortedData.comparatorProperty().bind(VeradoTabla.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            //VeradoTabla.setItems(sortedData);
            SetBloodCounters();
            VeradoTabla.setItems(veradoObservableList);
        }catch (Exception e){

        }



    }

    public void SetBloodCounters(){
        int AP = 0,
                AM = 0,
                BP = 0,
                BM = 0,
                ABP = 0,
                ABM =0,
                OP = 0,
                OM = 0;

        for (int i = 0; i < veradoLista.size(); i++) {
            if (veradoLista.get(i).getVercsoport().equals("A+")) AP += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("A-")) AM += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("B+")) BP += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("B-")) BM += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("AB+")) ABP += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("AB-")) ABM += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("0+")) OP += veradoLista.get(i).getMennyiseg();
            if (veradoLista.get(i).getVercsoport().equals("0-")) OM += veradoLista.get(i).getMennyiseg();
        }
            APlusCounter.setText(String.valueOf(AP));
            AMCounter.setText(String.valueOf(AM));
            BPCounter.setText(String.valueOf(BP));
            BMCounter.setText(String.valueOf(BM));
            ABPCounter.setText(String.valueOf(ABP));
            ABMCounter.setText(String.valueOf(ABM));
            OPCounter.setText(String.valueOf(OP));
            OMCounter.setText(String.valueOf(OM));

    }
    ////////////////////////////////////////FÜGGVÉNYEK VÉGE////////////////////////////////////////



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
    private TableColumn<Verado, Integer> KorhazIDOszlop;

    @FXML
    private TableColumn<Verado, String> MennyisegOszlop;

    @FXML
    private TableColumn<Verado, String> NevOszlop;

    @FXML
    private TableColumn<Verado, String> VercsoportOszlop;
    ObservableList<Verado> veradoObservableList = FXCollections.observableArrayList();

//VERADO TABLA VEGE


    //VERADO BUTTONOK

    @FXML
    void VeradoHozzaadButtonPushed(ActionEvent event) throws SQLException {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        String[] VercsoportTomb = {"A+", "A-","B+","B-","AB+","AB-","0+","0-"};


        Boolean mezoures = false;
        Boolean vercsoportcontain = false;


        if(VeradoMennyisegField.getText().equals("") ||
                VeradoNevField.getText().equals("") ||
                VeradoVercsoportField.getText().equals("") ||
                korhazHozzaField.getText().equals(""))
        {
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

        if (CheckIfConChar(VeradoMennyisegField.getText()) == false &&
                mezoures == false &&
                vercsoportcontain == true &&
                CheckIfConNumb(VeradoNevField.getText()) == false) {

            Verado s = new Verado();
            s.setNev(VeradoNevField.getText());
            s.setVercsoport(VeradoVercsoportField.getText());
            s.setMennyiseg(Integer.parseInt(VeradoMennyisegField.getText()));

            for (int i = 0; i < korhazLista.size(); i++)
            {
                System.out.println(korhazHozzaField.getText());
                if(korhazLista.get(i).getNev().equals(korhazHozzaField.getText()))
                {
                    s.setKorhazID(korhazLista.get(i).getId());
                    System.out.println(s.getKorhazID());
                }
            }

            veradoLista.add(s);
            aDAO.saveVerado(s);

            InformPopUpWindow("Sikeres adatfelvétel!");

            if(korhazHozzaField.getText().equals("")||korhazHozzaField.getText().isEmpty()==true){
                VUpdateTableView();
            }
            else{
                VUpdateTabeleView();
            }
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
    void VeradoTorolButtonPushed(ActionEvent event) throws SQLException {
        ConnectionDB cn = new ConnectionDB();
        Connection cn1 = cn.fileconnection();
        ObservableList<Verado> allVerado,singleVerado;
        allVerado = VeradoTabla.getItems();

        singleVerado = VeradoTabla.getSelectionModel().getSelectedItems();
        singleVerado.forEach(allVerado::remove);
        System.out.println(VeradoTabla.getSelectionModel().getSelectedItem());
        int ID = VeradoTabla.getSelectionModel().getSelectedItem().getId()+1;
        PreparedStatement statement = cn1.prepareStatement("DELETE FROM VERADO WHERE ID ="+ID+";");

        statement.execute();
        veradoLista.clear();
        try {
            veradoLista = GetVerado();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        VUpdateTabeleView();
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
    private TableView<Korhaz> VeradoPontTabla;
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
    ObservableList<Korhaz> korhazObservableList = FXCollections.observableArrayList();

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
    private Text APlusCounter;
    @FXML
    private Text AMCounter;
    @FXML
    private Text BPCounter;
    @FXML
    private Text BMCounter;
    @FXML
    private Text ABPCounter;
    @FXML
    private Text ABMCounter;
    @FXML
    private Text OPCounter;
    @FXML
    private Text OMCounter;





    @FXML
    private Button VeradoPontHButton;

    @FXML
    private Button VeradoPontHButton1;

    @FXML
    private  TextField korhazHozzaField;



    @FXML
    void VeradoPontHozzaadButtonPushed(ActionEvent event) throws SQLException {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Boolean seged=null;

        Boolean mezoureskorhaz = false;
        Boolean nyitv = false;
        Boolean juttatascheck = false;
        if(VeradoPontNeveField.getText().equals("")||
            VeradoPontJuttatasField.getText().equals("")||
                VeradoPontNyitvatartasField.getText().equals("")||
                VeradoPontHelyField.getText().equals("")
        ){
            WarningPopUpWindow("Hiba! Ne hagyj ures mezot!");
            mezoureskorhaz=true;

        }
        else{
            /*if(VeradoPontJuttatasField.getText()!="true"&&VeradoPontJuttatasField.getText()!="false"){
                WarningPopUpWindow("Kérlek true vagy false értéket adj meg juttatásnak!");
                juttatascheck=true;
            }*/

            String[] split=VeradoPontNyitvatartasField.getText().split(":");
            if(VeradoPontNyitvatartasField.getText().length()==11) {
                if (VeradoPontNyitvatartasField.getText().charAt(2) == ':' && VeradoPontNyitvatartasField.getText().charAt(5) == '-' && VeradoPontNyitvatartasField.getText().charAt(8) == ':') {
                    nyitv = true;
                }
                else{
                    WarningPopUpWindow("Ügyelj arra hogy megfelelő formátumban add meg a nyitvatartást! (óó:pp-óó:pp");
                }
            }else{
                WarningPopUpWindow("Ügyelj arra hogy megfelelő formátumban add meg a nyitvatartást! (óó:pp-óó:pp");
            }

        }
        if(mezoureskorhaz==false&&nyitv==true) {

            if (!VeradoPontJuttatasField.getText().equals("")) {

                if (VeradoPontJuttatasField.getText().equals("true")) {
                    seged = true;
                } else if (VeradoPontJuttatasField.getText().equals("false")) {
                    seged = false;
                }

                korhaz.setJuttatas(seged);
            }

            Korhaz korhaz = new Korhaz();
            korhaz.setNev(VeradoPontNeveField.getText());
            korhaz.setIdo(VeradoPontNyitvatartasField.getText());
            korhaz.setJuttatas(seged);
            korhaz.setHelyszin(VeradoPontHelyField.getText());

            korhazLista.add(korhaz);

            aDAO.saveKorhaz(korhaz);
            InformPopUpWindow("Sikeres adatátvitel!");

            //VPUpdateTableView();


            VPUpdateTableView();

        }
    }


    @FXML
    void VeradoPontKeresButtonPushed(ActionEvent event) {

    }

    @FXML
    void VeradoPontTorolButtonPushed(ActionEvent event) throws SQLException {
        ConnectionDB cn = new ConnectionDB();
        Connection cn1 = cn.fileconnection();
        ObservableList<Korhaz> allKorhaz,singleKorhaz;
        allKorhaz = VeradoPontTabla.getItems();
        singleKorhaz = VeradoPontTabla.getSelectionModel().getSelectedItems();
        singleKorhaz.forEach(allKorhaz::remove);

        int ID = VeradoPontTabla.getSelectionModel().getSelectedItem().getId()+1;
        PreparedStatement statement = cn1.prepareStatement("DELETE FROM KORHAZ WHERE ID ="+ID+";");

        statement.execute();
        korhazLista.clear();
        try {
            korhazLista = GetKorhaz();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        VPUpdateTableView();
    }

//VERADOPONT GOMBOK VEGE


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            veradoLista = GetVerado();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VUpdateTabeleView();

        try {
            korhazLista = GetKorhaz();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VPUpdateTableView();

    }
    }


