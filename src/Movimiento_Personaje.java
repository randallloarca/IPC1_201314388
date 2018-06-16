
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Movimiento_Personaje extends Thread {

    public Ventana_Juego tab;
    private int[][] matrizRec;
    public int cantidad = 0, direccion = 0;
    public boolean turno = false;

    public Movimiento_Personaje(int[][] matriz, int cantidad, int direccion, boolean turno) {
        this.matrizRec = matriz;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.turno = turno;
    }

    public void Select_movimiento(Boolean turno_j, int direccion, int cantidad) {
        switch (direccion) {
            case 6:
                Mover_Derecha(cantidad);
                break;
            case 4:
                Mover_Izquierda(cantidad);
                break;
            case 8:
                Mover_Arriba(cantidad);
                break;
            case 2:
                Mover_Abajo(cantidad);
                break;
        }
    }

    public void Mover_Derecha(int cantidad) {
        try {
            if (cantidad <= 0) {
                //tjugador=true;
                return;
            }
            if (comprobarDerecha()) {
                //regresar el jugador a la posicion 0
                cantidad = 0;
                tab.mtablero[tab.per.Posicion_Personaje_Y][tab.tam - 1] = 0;
                tab.tablero[tab.per.Posicion_Personaje_Y][tab.tam - 1].setIcon(null);
                tab.per.Posicion_Personaje_X = 0;
                tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }
            // Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();
            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_X = tab.per.Posicion_Personaje_X + 1;
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
            tab.repintar();
            cantidad--;
            Thread.sleep(500);
            Select_movimiento(turno, direccion, cantidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Movimiento_Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Mover_Izquierda(int cantidad) {
        try {
            if (cantidad <= 0) {
                return;
            }
            if (comprobarIzquierda()) {
                //regresar el jugador a la posicion 0
                cantidad = 0;
                tab.mtablero[tab.per.Posicion_Personaje_Y][tab.tam - 1] = 0;
                tab.tablero[tab.per.Posicion_Personaje_Y][tab.tam - 1].setIcon(null);
                tab.per.Posicion_Personaje_X = 0;
                tab.per.Posicion_Personaje_Y = 0;
                tab.mtablero[0][0] = 1;
                tab.repintar();
                tab.mtablero[tab.per.Posicion_Personaje_Y][0] = 0;
                tab.tablero[tab.per.Posicion_Personaje_Y][0].setIcon(null);
                tab.per.Posicion_Personaje_X = tab.tam - 1;
                tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }

//             Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();

            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_X = tab.per.Posicion_Personaje_X - 1;
//        if(tab.posjugx<0){
//            tab.posjugx=tab.tam-1;
//            cantidad=0;
//        }
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
            tab.repintar();
            cantidad--;

            Thread.sleep(500);

            Select_movimiento(turno, direccion, cantidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Movimiento_Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Mover_Arriba(int cantidad) {
        try {
            if (cantidad <= 0) {
                return;
            }
//
            if (comprobarArriba()) {
                //regresar el jugador a la posicion 0
                cantidad = 0;
                tab.mtablero[0][tab.per.Posicion_Personaje_X] = 0;
                tab.tablero[0][tab.per.Posicion_Personaje_X].setIcon(null);
                //tab.posjugx = 0;
                tab.per.Posicion_Personaje_Y = tab.tam - 1;
                tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }

            // Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();
            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_Y = tab.per.Posicion_Personaje_Y - 1;
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
            tab.repintar();
            cantidad--;
            Thread.sleep(500);
            Select_movimiento(turno, direccion, cantidad);
        } catch (InterruptedException ex) {

            Logger.getLogger(Movimiento_Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Mover_Abajo(int cantidad) {
        try {
            if (cantidad <= 0) {
                return;
            }

            if (comprobarAbajo()) {
                //regresar el jugador a la posicion 0
                cantidad = 0;
                tab.mtablero[tab.tam - 1][tab.per.Posicion_Personaje_X] = 0;
                tab.tablero[tab.tam - 1][tab.per.Posicion_Personaje_X].setIcon(null);
                //tab.posjugx = 0;
                tab.per.Posicion_Personaje_Y = 0;
                tab.mtablero[0][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }

            // Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();
            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_Y = tab.per.Posicion_Personaje_Y + 1;
            tab.mtablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
            tab.repintar();
            cantidad--;
            Thread.sleep(500);
            Select_movimiento(turno, direccion, cantidad);
        } catch (InterruptedException ex) {

            Logger.getLogger(Movimiento_Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean comprobarIzquierda() {
        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
            if (tab.per.Posicion_Personaje_X <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarDerecha() {
        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
            if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarArriba() {
        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {

            if (tab.per.Posicion_Personaje_Y <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarAbajo() {
        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
            if (tab.per.Posicion_Personaje_Y >= tab.tam - 1) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        Select_movimiento(turno, direccion, cantidad);
    }
}
