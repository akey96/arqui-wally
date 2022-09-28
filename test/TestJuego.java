import backend.Juego;

import backend.TipoDeSimplificacion;
import frontend.UI;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestJuego {
    static Juego juego;

    @BeforeEach
    public void setup(){

        String escenario1 = "2;4;4;16;16.2;4;2;2;64.64;8;4;2;32.8;16;2;2;126.16;16;16;32;64.8;8;8;32;32.";
        int siguiente = 4;
        juego = new Juego(escenario1, siguiente);

    }

    @Test
    void testSePuedeSimplificarUnaColumna(){
        assertEquals(juego.tipoSimplificacion(1,1), TipoDeSimplificacion.C_1);
        assertEquals(juego.tipoSimplificacion(0,3), TipoDeSimplificacion.B_1);
        assertEquals(juego.tipoSimplificacion(0,1), TipoDeSimplificacion.B_2);
        assertEquals(juego.tipoSimplificacion(2,3), TipoDeSimplificacion.B_3);
        assertEquals(juego.tipoSimplificacion(4,3), TipoDeSimplificacion.B_4);
        assertEquals(juego.tipoSimplificacion(5,1), TipoDeSimplificacion.B_5);
        assertEquals(juego.tipoSimplificacion(5,3), TipoDeSimplificacion.A_1);
        assertEquals(juego.tipoSimplificacion(4,0), TipoDeSimplificacion.A_2);
        assertEquals(juego.tipoSimplificacion(1,4), TipoDeSimplificacion.NONE);
    }

    @Test
    void testSimplificarCaso_C1(){
        assertEquals(juego.simplificar(1, 1), TipoDeSimplificacion.C_1);
        assertEquals(juego.estadoTablero()[0][0], " ");
        assertEquals(juego.estadoTablero()[0][1], " ");
        assertEquals(juego.estadoTablero()[0][2], " ");
        assertEquals(juego.estadoTablero()[2][1], "128");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_B1(){
        assertEquals(juego.simplificar(0, 3), TipoDeSimplificacion.B_1);
        assertEquals(juego.estadoTablero()[0][3], " ");
        assertEquals(juego.estadoTablero()[0][4], " ");
        assertEquals(juego.estadoTablero()[1][3], "128");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_B2(){
        assertEquals(juego.simplificar(0, 1), TipoDeSimplificacion.B_2);
        assertEquals(juego.estadoTablero()[0][0], " ");
        assertEquals(juego.estadoTablero()[0][2], " ");
        assertEquals(juego.estadoTablero()[0][1], "32");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_B3(){
        assertEquals(juego.simplificar(2, 3), TipoDeSimplificacion.B_3);
        assertEquals(juego.estadoTablero()[0][2], " ");
        assertEquals(juego.estadoTablero()[0][3], " ");
        assertEquals(juego.estadoTablero()[3][3], "8");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_B4(){
        assertEquals(juego.simplificar(4, 3), TipoDeSimplificacion.B_4);
        assertEquals(juego.estadoTablero()[0][2], " ");
        assertEquals(juego.estadoTablero()[0][3], " ");
        assertEquals(juego.estadoTablero()[4][3], "8");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_B5(){
        assertEquals(juego.simplificar(5, 1), TipoDeSimplificacion.B_5);
        assertEquals(juego.estadoTablero()[0][1], " ");
        assertEquals(juego.estadoTablero()[0][2], " ");
        assertEquals(juego.estadoTablero()[5][1], "16");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_A1(){
        assertEquals(juego.simplificar(5, 3), TipoDeSimplificacion.A_1);
        assertEquals(juego.estadoTablero()[0][3], " ");
        assertEquals(juego.estadoTablero()[5][4], "32");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }

    @Test
    void testSimplificarCaso_A2(){
        assertEquals(juego.simplificar(4, 0), TipoDeSimplificacion.A_2);
        assertEquals(juego.estadoTablero()[0][0], " ");
        assertEquals(juego.estadoTablero()[5][0], "4");
//        UI ui = new UI(juego);
//        ui.imprimirTablero();
    }
}
