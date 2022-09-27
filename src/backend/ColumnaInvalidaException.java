package backend;

public class ColumnaInvalidaException extends Exception{
    public ColumnaInvalidaException(){
        super("Numero de columna inexistente");
    }
}
