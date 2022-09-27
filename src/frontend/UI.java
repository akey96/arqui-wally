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

        while (true) {
            imprimirEstado();
            int ficha = juego.getSig();
            System.out.println("Ingrese la columna a donde quisiera ingresar la ficha: "+ ficha );
            int columna = teclado.nextInt();
            try {
                EstadoJuego estadoJuego = juego.aniadirColumna(columna);
                System.out.println(estadoJuego);
                if (estadoJuego.equals(EstadoJuego.ganado)){
                    System.out.println("GANO EL JUEGOOOO!!!!!");
                    imprimirEstado();
                    break;
                } else if (estadoJuego.equals(EstadoJuego.perdido)) {
                    System.out.println("Juego terminado, ya no puedes insertar la ficha en ninguna columna!!");
                }
            } catch (ColumnaInvalidaException | SinEspacioColumnaException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void imprimirEstado(){
        System.out.println("############################## JUEGO EXPONENTES #################################\n");
        this.imprimirFichas();
        System.out.println("\n");
        this.imprimirTablero();
    }

    public void imprimirFichas(){
        System.out.print("Fichas: ");
        System.out.print(juego.getSubSig() +", "+ juego.getSig());
    }

    public void imprimirTablero(){
        String[][] tablero = juego.estadoTablero();
        for(int i = 0; i< 6; i++) {
            for(int j = 0; j< 5; j++) {
                try {
//                    String entrada = tablero[j][((tablero[j].length - i - 1) + (juego.MAX_COLUMNAS - tablero[j].length))];
//                    String s = tablero[j][((tablero[j].length - i - 1) + (juego.MAX_COLUMNAS - tablero[j].length))];
                    String s = tablero[i][j];
                    String formato = "   *|";
                    if(!s.equals("")){
                        formato = String.format("%4d|", Integer.parseInt(s));
//                        System.out.println("fila="+j+"  , col="+j );
                    }
                    System.out.print(formato);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.print("    |");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
        String formato = String.format("%4d|%4d|%4d|%4d|%4d|",0,1,2,3,4);
        System.out.println(formato);
    }


}
