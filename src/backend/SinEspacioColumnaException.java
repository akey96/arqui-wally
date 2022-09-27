package backend;

public class SinEspacioColumnaException extends Exception{
    public SinEspacioColumnaException(){
        super("Sin Espacio en la Columna, intente con otra columna");
    }
}