package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatosJugadores {
    String nombreArchivo = "Puntajes.csv";

    private String getPath(){
        File miDir = new File (".");
        String dir="", path, separador = System.getProperty("file.separator");
        try {
            dir= miDir.getCanonicalPath();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();

        assert a != null;
        for (String s : a) {
            if (s.equals("src")) {
                esDesarrollo = true;
                break;
            }
        }

        if ( !esDesarrollo ){
            path = dir+separador+  nombreArchivo;
        } else {
            path = dir+separador+"src"+separador+  nombreArchivo;
        }
        return path;
    }

    public List<Jugador> leerJugadores() {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        try {
            File myObj = new File(getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                jugadores.add(new Jugador(data[0].trim(), Integer.parseInt(data[1].trim())));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return jugadores;
    }

    public boolean existeCSVFile(){
        File f = new File(getPath());
        return f.exists();
    }

    public void guardarDatosJugadores(List<Jugador> jugadores){
        try {
            FileWriter myWriter = new FileWriter(getPath());
            for (Jugador j: jugadores) {
                myWriter.write(j.toString()+"\n");
            }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
