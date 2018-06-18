
import javax.swing.JLabel;

public class Cronometro extends Thread {

    public JLabel etiqueta;
    int tiempomin = 0;

    public Cronometro(JLabel cronometro, int tmpMin) {
        this.etiqueta = cronometro;
        this.tiempomin = tiempomin;

    }

    public void run() {
        try {
            int x = 0;
            while (Ventana_Juego.iniciaHilo) {
                Thread.sleep(999);
                ejecutarHiloCronometro(x);
                x++;
            }
        } catch (Exception e) {
            System.out.println("Error en el Hilo");
        }
    }

    private void ejecutarHiloCronometro(int x) {
        System.out.println(x + "-" + Thread.currentThread().getName());
        if (Ventana_Juego.segundos != 0) {
            Ventana_Juego.segundos--;
        } else {
            if (Ventana_Juego.minutos != 0) {
                Ventana_Juego.segundos = 59;
                Ventana_Juego.minutos--;
            }
        }
        String textSeg = "", textMin = "", textHora = "";
        if (Ventana_Juego.segundos < 10) {
            textSeg = "0" + Ventana_Juego.segundos;
        } else {
            textSeg = "" + Ventana_Juego.segundos;
        }
        if (Ventana_Juego.minutos < 10) {
            textMin = "0" + Ventana_Juego.minutos;
        } else {
            textMin = "" + Ventana_Juego.minutos;
        }
        if (Ventana_Juego.hora < 10) {
            textHora = "0" + Ventana_Juego.hora;
        } else {
            textHora = "" + Ventana_Juego.hora;
        }

        String reloj = textHora + ":" + textMin + ":" + textSeg;
        etiqueta.setText(reloj);
    }
}
