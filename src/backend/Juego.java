package backend;

import java.util.Arrays;

public class Juego {

    private String[][] tablero;
    private int sig, subSig;
    public final int MAX_COLUMNAS = 5;
    public final int MAX_FILAS = 6;
    private EstadoJuego estado;

    public Juego(){
        tablero = new String[MAX_FILAS][MAX_COLUMNAS];
        for (String[] strings : tablero) {
            Arrays.fill(strings, "");
        }
        sig = generarNumero();
        subSig = generarNumero();
    }

    public Juego(String juego, int siguiente) {

    }

    public String[][] estadoTablero(){
        return this.tablero;
    }

    private int generarNumero(){
        int [] fichasAleatorias = {2, 4, 8, 16, 32, 64, 128};
        int indice = (int) (Math.random()*7+ 0);
        return fichasAleatorias[indice];
    }

    public EstadoJuego getEstado(){
        return estado;
    }

    private int indicefilaDeUnaColumna(int col){
        int indice = -1;
        for(int i=MAX_FILAS-1; i>=0; i-- ) {

            if(tablero[i][col].equals("")) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public boolean estaDisponibleAlgunaColumna(){
        boolean estaDisponible = false;
        for(int col=0; col<MAX_COLUMNAS; col++) {
            int indiceFila = indicefilaDeUnaColumna(col);
            if(indiceFila>=0){
                estaDisponible = true;
            }
        }
        return estaDisponible;
    }

//    public boolean estaDisponibleUnaColumna(int elemento, int columna){
//        boolean estaDisponible = false;
//
//        if(tablero[columna].peek() == elemento) {
//            estaDisponible = true;
//        }
//
//        return estaDisponible;
//    }

    public EstadoJuego aniadirColumna(int col) throws ColumnaInvalidaException, SinEspacioColumnaException {

        if ((col< 0) || (col >=MAX_COLUMNAS)) {
            throw new ColumnaInvalidaException();
        }

        int indiceFilaColumna = indicefilaDeUnaColumna(col);
        System.out.println("indice inicio ="+indiceFilaColumna);
        if(indiceFilaColumna == -1){
            if(Integer.parseInt(tablero[0][col]) != sig){
                if(!estaDisponibleAlgunaColumna()) {
                    return EstadoJuego.perdido;
                }
                throw new SinEspacioColumnaException();
            }
        }

        // primero simplificar todo en vertical

        if(indiceFilaColumna == MAX_FILAS-1){
            System.out.println("Sig "+sig);
            System.out.println("indicefilaColimna= "+indiceFilaColumna);

            tablero[indiceFilaColumna][col] = sig+"";
            sig = subSig;
            subSig = generarNumero();

        } else {
            // cambiar los elementos eliminados por ""
            if((indiceFilaColumna == -1) && ( Integer.parseInt(tablero[indiceFilaColumna+1][col]) != Integer.parseInt(tablero[indiceFilaColumna+2][col]))) {
                throw new SinEspacioColumnaException();
            }
            System.out.println("aqui = " + indiceFilaColumna);
            tablero[indiceFilaColumna][col] = sig+"";
            indiceFilaColumna = indicefilaDeUnaColumna(col);

            while (indiceFilaColumna != MAX_FILAS-1) {
                System.out.println("dentro while="+indiceFilaColumna);
                int ultimoElementoColumna = Integer.parseInt(tablero[indiceFilaColumna+1][col]);
                int penultimoElementoColumna = Integer.parseInt(tablero[indiceFilaColumna+2][col]);
                if(penultimoElementoColumna == ultimoElementoColumna) {
//                    ([i][j] == [i+1][j])
//                          Eliminar  [i][j]
//                          Sumar todo a [i+1][j]

                    tablero[indiceFilaColumna+1][col] = "";
                    tablero[indiceFilaColumna+2][col] = (penultimoElementoColumna+penultimoElementoColumna)+"";
                    sig = subSig;
                    subSig = generarNumero();
                } else {
                    break;
                }
                indiceFilaColumna = indicefilaDeUnaColumna(col);
            }
        }

        indiceFilaColumna = indicefilaDeUnaColumna(col)+1;

        if((col-1 >= 0) && (tablero[indiceFilaColumna][col].equals(tablero[indiceFilaColumna][col-1]))){ // caso A
            System.out.println("Ingreso caso A, 1");
        } else if((col+1 < MAX_COLUMNAS) && (tablero[indiceFilaColumna][col].equals(tablero[indiceFilaColumna][col+1]))) {
            System.out.println("Ingreso caso A, 2");
            System.out.println(tablero[indiceFilaColumna][col]);
            System.out.println(tablero[indiceFilaColumna][col+1]);
            System.out.println("indicefilaColimna2= "+indiceFilaColumna);
        } else if(((col-1 >= 0) && (indiceFilaColumna+1<MAX_FILAS)) && ((tablero[indiceFilaColumna][col].equals(tablero[indiceFilaColumna][col-1]))
                && (tablero[indiceFilaColumna][col-1].equals(tablero[indiceFilaColumna+1][col])))){  // caso B
            System.out.println("Ingreso caso B, 1");
        } else if(((col+1 < MAX_COLUMNAS) && (indiceFilaColumna+1<MAX_FILAS)) && ((tablero[indiceFilaColumna][col].equals(tablero[indiceFilaColumna][col+1]))
                && (tablero[indiceFilaColumna][col+1].equals(tablero[indiceFilaColumna+1][col])))){
            System.out.println("Ingreso caso B, 2");
        } else if(((col+1 < MAX_COLUMNAS) && ( (col-1 >= 0) && (indiceFilaColumna+1<MAX_FILAS))) && (((tablero[indiceFilaColumna][col].equals(tablero[indiceFilaColumna][col+1]))
                && (tablero[indiceFilaColumna][col+1].equals(tablero[indiceFilaColumna][col-1])))
                && (tablero[indiceFilaColumna][col-1].equals(tablero[indiceFilaColumna+1][col])))){ // caso C
            System.out.println("Ingreso caso C, 1");
        } else {
            System.out.println("Ingreso a la columna");
            estado = EstadoJuego.enJuego;
        }
        return estado;
    }

    @Override
    public String toString() {
        return "";
    }

    public int getSig(){
        return sig;
    }

    public int getSubSig(){
        return subSig;
    }

    public int getColumnas() {
        return 1;
    }

    public int getFilas() {
        return 1;
    }
}
