
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Movimiento_Personaje extends Thread {

    public Ventana_Juego tab;
    public int cantidad = 0, direccion = 0;
    public boolean turno = false;

    public Movimiento_Personaje(Ventana_Juego tab2, int cantidad, int direccion, boolean turno) {
        tab = tab2;

        this.cantidad = cantidad;
        this.direccion = direccion;
        this.turno = turno;
    }

    public Movimiento_Personaje(int cantidad, int direccion, boolean turno) {

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

    public void llenar_Matriz(int fila, int columna, int elemento, String nombre) {
        if (tab.mtablero[fila][columna] == 0) {
            if (elemento == 7) {
                tab.mtablero[fila][columna] = elemento;
                tab.tablero[fila][columna].setIcon(tab.Vida_Bomba.obtener_Vida(tab.tambloquex, tab.tambloquex));
            }
            if (elemento == 8) {
                tab.mtablero[fila][columna] = elemento;
                tab.tablero[fila][columna].setIcon(tab.Vida_Bomba.obtener_Bomba(tab.tambloquex, tab.tambloquex));
            }
            if (elemento == 1 || elemento == 2 || elemento == 3) {
                if (nombre.equals("Princesa")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenPrincesa(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                if (nombre.equals("Guerrero")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenGuerrero(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                if (nombre.equals("Mago")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenMago(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            if (elemento == 4 || elemento == 5 || elemento == 6) {
                if (nombre.equals("Princesa")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenPrincesa(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }
                if (nombre.equals("Guerrero")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenGuerrero(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }
                if (nombre.equals("Mago")) {
                    tab.mtablero[fila][columna] = elemento;
                    tab.tablero[fila][columna].setIcon(tab.per.Obtener_ImagenMago(tab.tambloquex, tab.tambloquex));
                    tab.tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }

            }
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
                tab.limpiar_casilla(tab.avatar_f, tab.avatar_c, tab.turno_jugador);
                tab.avatar_c++;
                tab.llenar_Matriz(tab.avatar_f, tab.avatar_c, tab.turno_jugador, tab.turno_avatar);
                cantidad--;
                Thread.sleep(500);
                Mover_Derecha(cantidad);
                return;
            }
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
                tab.limpiar_casilla(tab.avatar_f, tab.avatar_c, tab.turno_jugador);
                tab.avatar_c--;
                tab.llenar_Matriz(tab.avatar_f, tab.avatar_c, tab.turno_jugador, tab.turno_avatar);
                cantidad--;
                Thread.sleep(500);
                Mover_Izquierda(cantidad);
                return;
            }
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
                tab.vecL[0][tab.per.Posicion_Personaje_X] = 0;
                tab.tablero[0][tab.per.Posicion_Personaje_X].setIcon(null);
                //tab.posjugx = 0;
                tab.per.Posicion_Personaje_Y = tab.tam - 1;
                tab.vecL[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }

            // Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.vecL[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();
            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_Y = tab.per.Posicion_Personaje_Y - 1;
            tab.vecL[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
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
                tab.vecL[tab.tam - 1][tab.per.Posicion_Personaje_X] = 0;
                tab.tablero[tab.tam - 1][tab.per.Posicion_Personaje_X].setIcon(null);
                //tab.posjugx = 0;
                tab.per.Posicion_Personaje_Y = 0;
                tab.vecL[0][tab.per.Posicion_Personaje_X] = 1;
                tab.repintar();
                return;
            }

            // Select_movimiento una posicion
            tab.tablero[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X].setIcon(null);
            tab.vecL[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 0;
            tab.repintar();
            //simula el movimiento hacia adelante
            tab.per.Posicion_Personaje_Y = tab.per.Posicion_Personaje_Y + 1;
            tab.vecL[tab.per.Posicion_Personaje_Y][tab.per.Posicion_Personaje_X] = 1;
            tab.repintar();
            cantidad--;
            Thread.sleep(500);
            Select_movimiento(turno, direccion, cantidad);
        } catch (InterruptedException ex) {

            Logger.getLogger(Movimiento_Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean comprobarIzquierda() {
        tab.buscar_avatar(tab.turno_jugador);
        if (tab.avatar_c >= tab.tam - 1) {
            if (tab.avatar_c <= 0) {
                return true;
            }
        }

//        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
//            if (tab.per.Posicion_Personaje_X <= 0) {
//                return true;
//            }
//        }
        return false;
    }

    public boolean comprobarDerecha() {
        //solo para el 1
        tab.buscar_avatar(tab.turno_jugador);
        if (tab.avatar_c >= tab.tam - 1) {
            return true;
        }

//        if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
//            if (tab.per.Posicion_Personaje_X >= tab.tam - 1) {
//                return true;
//            }
//        }
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
