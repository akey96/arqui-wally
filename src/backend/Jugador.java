package backend;

public class Jugador implements Comparable<Jugador>{
    private String nick;
    private int puntaje;

    public Jugador(String nick) {
        this.nick = nick;
        puntaje = 0;
    }

    public Jugador(String nick, int puntaje) {
        this.nick = nick;
        this.puntaje = puntaje;
    }


    public String getNick() {
        return nick;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return nick + ", " + puntaje ;
    }

    @Override
    public int compareTo(Jugador o) {
        return Integer.compare(this.puntaje, o.getPuntaje());
    }
}
