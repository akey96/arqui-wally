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
            Arrays.fill(strings, " ");
        }
        sig = generarNumero();
        subSig = generarNumero();
    }

    public Juego(String juego, int siguiente) {
        tablero = new String[MAX_FILAS][MAX_COLUMNAS];
        String[] filas = juego.split("\\.");
        for (int i = filas.length-1; i >=0 ; i--) {
            String[] columnas = filas[filas.length-i-1].split(";");
            for (int j = 0; j < columnas.length; j++) {
                tablero[i][j] = columnas[j];
            }
        }
        sig = siguiente;
        subSig = generarNumero();
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

    public boolean gano(){
        boolean bandera = false;
        for (int i = 0; i < MAX_FILAS; i++) {
            for (int j = 0; j < MAX_COLUMNAS; j++) {
                if(tablero[i][j].equals("256")){
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    private int indicefilaDeUnaColumna(int col){
        int indice = -1;
        for(int i=MAX_FILAS-1; i>=0; i-- ) {
            if(tablero[i][col].equals(" ")) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public int[] indiceSimplificar(){
        int[] indices = new int[] {-1, -1};

        for (int i = 0; i < MAX_FILAS; i++) {
            for (int j = 0; j < MAX_COLUMNAS; j++) {
                TipoDeSimplificacion tp = tipoSimplificacion(i, j);
                if(!tp.equals(TipoDeSimplificacion.NONE)){
                      indices[0] = i;
                      indices[1] = j;
                }
            }
        }
        return indices;
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

    public TipoDeSimplificacion tipoSimplificacion(int fila, int col){

        TipoDeSimplificacion tipoDeSimplificacion = TipoDeSimplificacion.NONE;

        if (tablero[fila][col].equals(" ")) {
            return tipoDeSimplificacion;
        }

        if(((col+1<MAX_COLUMNAS) && ((col-1>=0) && (fila+1<MAX_FILAS)) ) && (((tablero[fila][col].equals(tablero[fila][col+1])) &&
                (tablero[fila][col+1].equals(tablero[fila][col-1])))) && (tablero[fila][col-1].equals(tablero[fila+1][col]))) {
            tipoDeSimplificacion = TipoDeSimplificacion.C_1;
        } else if(((col+1<MAX_COLUMNAS) && (fila+1<MAX_FILAS)) && ((tablero[fila][col].equals(tablero[fila][col+1])) &&
                (tablero[fila][col+1].equals(tablero[fila+1][col])))) {
            tipoDeSimplificacion = TipoDeSimplificacion.B_1;
        } else if(((col+1<MAX_COLUMNAS) && (col-1>=0)) && ((tablero[fila][col].equals(tablero[fila][col+1])) &&
                (tablero[fila][col+1].equals(tablero[fila][col-1])))) {
            tipoDeSimplificacion = TipoDeSimplificacion.B_2;
        } else if(((col-1>=0) && (fila+1<MAX_FILAS)) && ((tablero[fila][col].equals(tablero[fila][col-1])) &&
                (tablero[fila][col-1].equals(tablero[fila+1][col])))) {
            tipoDeSimplificacion = TipoDeSimplificacion.B_3;
        } else if(((col-1>=0) && (fila-1>=0)) && ((tablero[fila][col].equals(tablero[fila][col-1])) &&
                (tablero[fila][col-1].equals(tablero[fila-1][col])))) {
            tipoDeSimplificacion = TipoDeSimplificacion.B_4;
        } else if(((fila-1>=0) && (col+1<MAX_COLUMNAS)) && ((tablero[fila][col].equals(tablero[fila-1][col])) &&
                (tablero[fila-1][col].equals(tablero[fila][col+1])))) {
            tipoDeSimplificacion = TipoDeSimplificacion.B_5;
        } else if((col+1<MAX_COLUMNAS) && (tablero[fila][col].equals(tablero[fila][col+1]))) {
            tipoDeSimplificacion = TipoDeSimplificacion.A_1;
        } else if((fila+1<MAX_FILAS) && (tablero[fila][col].equals(tablero[fila+1][col]))){
            tipoDeSimplificacion = TipoDeSimplificacion.A_2;
        }
        return tipoDeSimplificacion;
    }

    public void bajarElementos(int col){
        for(int i=MAX_FILAS-1; i>0; i--){
            if(tablero[i][col].equals(" ") && !tablero[i-1][col].equals(" ")  ){
                for (int j = i; j >=0 ; j--) {
                    if(j==0){
                        tablero[j][col] = " ";
                    }else {
                        tablero[j][col] = tablero[j-1][col];
                    }

                }
            }
        }
    }

    private int formulaExponente(String valor, int exponente){
        int n = Integer.parseInt(valor);
        for(int i=1; i<=exponente;i++){
            n +=n;
        }
        return n;
    }

    public TipoDeSimplificacion simplificar(int fila, int col){

        if(tablero[fila][col].equals(" ")){
            return TipoDeSimplificacion.NONE;
        }

        TipoDeSimplificacion sePuede = tipoSimplificacion(fila, col);

//        System.out.println(sePuede);
        System.out.println("Fila = " + fila + " col = " + col);
        if(sePuede.equals(TipoDeSimplificacion.C_1)){
            int n = formulaExponente(tablero[fila][col], 3);
            tablero[fila+1][col] = n+"";
            tablero[fila][col] = " ";
            tablero[fila][col-1] = " ";
            tablero[fila][col+1] = " ";
            bajarElementos(col);
            bajarElementos(col-1);
            bajarElementos(col+1);
        } else if (sePuede.equals(TipoDeSimplificacion.B_1)){
            int n = formulaExponente(tablero[fila][col], 2);
            tablero[fila+1][col] = n+"";
            tablero[fila][col] = " ";
            tablero[fila][col+1] = " ";

            bajarElementos(col);
            bajarElementos(col+1);
        } else if (sePuede.equals(TipoDeSimplificacion.B_2)){
            int n = formulaExponente(tablero[fila][col], 2);
            tablero[fila][col] = n+"";
            tablero[fila][col-1] = " ";
            tablero[fila][col+1] = " ";

            bajarElementos(col-1);
            bajarElementos(col+1);
        }else if (sePuede.equals(TipoDeSimplificacion.B_3)){
            int n = formulaExponente(tablero[fila][col], 2);
            tablero[fila+1][col] = n+"";
            tablero[fila][col] = " ";
            tablero[fila][col-1] = " ";
            bajarElementos(col);
            bajarElementos(col-1);

        } else if (sePuede.equals(TipoDeSimplificacion.B_4)){
            int n = formulaExponente(tablero[fila][col], 2);
            tablero[fila][col] = n+"";
            tablero[fila][col-1] = " ";
            tablero[fila-1][col] = " ";
            bajarElementos(col);
            bajarElementos(col-1);

        } else if (sePuede.equals(TipoDeSimplificacion.B_5)){
            int n = formulaExponente(tablero[fila][col], 2);
            tablero[fila][col] = n+"";
            tablero[fila][col+1] = " ";
            tablero[fila-1][col] = " ";
            bajarElementos(col);
            bajarElementos(col+1);

        } else if (sePuede.equals(TipoDeSimplificacion.A_1)){
            int n = formulaExponente(tablero[fila][col], 1);
            tablero[fila][col+1] = n+"";
            tablero[fila][col] = " ";
            bajarElementos(col);

        } else if (sePuede.equals(TipoDeSimplificacion.A_2)){
            int n = formulaExponente(tablero[fila][col], 1);
            tablero[fila+1][col] = n+"";
            tablero[fila][col] = " ";
            bajarElementos(col);
        }
        // sino ver lo que se pueda
        if(gano()) {
            estado = EstadoJuego.ganado;
        }
        return sePuede;
    }


    public EstadoJuego aniadirColumna(int col) throws ColumnaInvalidaException, SinEspacioColumnaException {
        estado = EstadoJuego.enJuego;
        if ((col< 0) || (col >=MAX_COLUMNAS)) {
            throw new ColumnaInvalidaException();
        }

        int indiceFilaColumna = indicefilaDeUnaColumna(col);
        if(indiceFilaColumna == -1){
            if(Integer.parseInt(tablero[0][col]) != sig){
                if(!estaDisponibleAlgunaColumna()) {
                    estado = EstadoJuego.perdido;
                    return EstadoJuego.perdido;
                }
                throw new SinEspacioColumnaException();
            }
        }

        // verificar si podemos ingresar en la cabeza cuando este lleno

//        if(indiceFilaColumna == MAX_FILAS-1){
//            System.out.println(indiceFilaColumna);
//            tablero[indiceFilaColumna][col] = sig+"";
//            sig = subSig;
//            subSig = generarNumero();
//        } else {
//            tablero[indiceFilaColumna][col] = sig+"";
//            sig = subSig;
//            subSig = generarNumero();
//        }
        tablero[indiceFilaColumna][col] = sig+"";
        sig = subSig;
        subSig = generarNumero();

        indiceFilaColumna = indicefilaDeUnaColumna(col);
        if(!tipoSimplificacion(indiceFilaColumna+1, col).equals(TipoDeSimplificacion.NONE)) {
            System.out.println("Movimiento = " + simplificar(indiceFilaColumna+1, col));

            sig = subSig;
            subSig = generarNumero();
        }

        return estado;
    }

    @Override
    public String toString() {
        StringBuilder cad  = new StringBuilder();
        for (int i = tablero.length; i >=0 ; i--) {
            for (int j = 0; j < tablero[i].length; j++) {
                cad.append( tablero[i][j]);
                if(j==tablero[i].length-1) {
                    cad.append(".");
                } else {
                    cad.append(";");
                }
            }
        }
        return cad.toString();
    }

    public int getSig(){
        return sig;
    }

    public int getSubSig(){
        return subSig;
    }

    public int getColumnas() {
        return MAX_COLUMNAS;
    }

    public int getFilas() {
        return MAX_FILAS;
    }
}
