/*package hu.unideb.inf.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVeradoDAO implements VeradoDAO{

    private List<Verado> veradok;

    public FileVeradoDAO(){
        try(FileInputStream fis = new FileInputStream("veradok.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);){
            veradok = (List<Verado>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            veradok = new ArrayList<>();
        }
    }

    private void serialize(){
        try(FileOutputStream fos = new FileOutputStream("nimals.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(veradok);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveVerado(Verado a) {
        if(!veradok.contains(a))veradok.add(a);
        serialize();

    }

    @Override
    public void deleteVerado(Verado a) {
        veradok.remove(a);
        serialize();
    }

    @Override
    public void updateVerado(Verado a) {
        veradok.remove(a);
        veradok.add(a);
        serialize();
    }

    @Override
    public List<Verado> getVeradok() {
        return veradok;
    }



    @Override
    public void close() throws Exception {
        serialize();
    }
}
*/