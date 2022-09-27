import backend.*;
import frontend.*;

public class Main {
    public static void main(String[] args) {
        Juego juego;
        UI ui;

        String escenario1 = "";
        int siguiente1 = 1;

        juego = new Juego();
        ui = new UI(juego);
        ui.jugar();


        // preparar string escenario 1 y siguiente
//        juego = new Juego(escenario1,siguiente1);
//        ui = new UI(juego);
//        ui.jugar();

        // preparar string escenario 2 y siguiente
//        juego = new Juego(escenario2,siguiente1);
//        ui = new UI(juego);
//        ui.jugar();

        // preparar string escenario 3 y siguiente
//        juego = new Juego(escenario3,siguiente1);
//        ui = new UI(juego);
//        ui.jugar();
    }
}
