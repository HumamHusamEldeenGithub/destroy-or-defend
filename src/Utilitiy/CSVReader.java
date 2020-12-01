package Utilitiy;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    FileReader fr;
    BufferedReader reader;

    public CSVReader(String path){
        try {
            fr = new FileReader(path);
            reader = new BufferedReader(fr);
        }
        catch (Exception e){
            System.err.println("Error opening the file");
        }
    }

    public String[] ReadLine(){
        String[] info = null;
        try {
            info = reader.readLine().split(",");
        }
        catch (Exception e){

        }
        finally {
            return info;
        }
    }

    public void Close(){
        try {
            reader.close();
            fr.close();
        }
        catch (Exception e){
        }

    }
}
