package frontend;

import backend.ColumnaInvalidaException;
import backend.EstadoJuego;
import backend.Juego;
import backend.SinEspacioColumnaException;

import java.util.Scanner;

public class UI {
    private Juego juego;

    public UI(Juego juego){
        this.juego = juego;
    }

    public void jugar(){
        Scanner teclado = new Scanner(System.in);

        do {
            imprimirEstado();
            int ficha = juego.getSig();
            System.out.println("Ingrese la columna a donde quisiera ingresar la ficha: "+ ficha );
            int columna = teclado.nextInt();
            try {
                EstadoJuego estadoJuego = juego.aniadirColumna(columna);
//                imprimirEstado();
//                System.out.println(estadoJuego);
                int[] indices = juego.indiceSimplificar();
                while (indices[0] != -1 && indices[1] != -1) {
                    imprimirEstado();
                    System.out.println(juego.simplificar(indices[0], indices[1]));
//                    imprimirEstado();
                    indices = juego.indiceSimplificar();
                }

            } catch (ColumnaInvalidaException | SinEspacioColumnaException e) {
                System.out.println(e.getMessage());
            }

        } while(juego.getEstado().equals(EstadoJuego.enJuego) || juego.getEstado().equals(EstadoJuego.perdido));

        if (juego.getEstado().equals(EstadoJuego.ganado)){
            imprimirEstado();
            System.out.println("GANO EL JUEGOOOO!!!!!");
//
        } else if (juego.getEstado().equals(EstadoJuego.perdido)) {
            System.out.println("Juego terminado, ya no puedes insertar la ficha en ninguna columna!!");
        }

    }

    public void imprimirEstado(){

        this.imprimirFichas();
        System.out.println("\n");
        this.imprimirTablero();
        System.out.println("############################## JUEGO EXPONENTES #################################\n");
    }

    public void imprimirFichas(){
        System.out.print("Fichas: ");
        System.out.print("SubSig = " + juego.getSubSig() +", Sig = "+ juego.getSig());
    }

    public void imprimirTablero(){
        String[][] tablero = juego.estadoTablero();
        for(int i = 0; i< 6; i++) {
            for(int j = 0; j< 5; j++) {
                try {
                    String s = tablero[i][j];
                    String formato = "    |";
                    if(!s.equals(" ")){
                        formato = String.format("%4d|", Integer.parseInt(s));
                    }
                    System.out.print(formato);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.print("    |");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
        String formato = String.format("%4d|%4d|%4d|%4d|%4d|",0,1,2,3,4);
        System.out.println(formato);
    }
}
