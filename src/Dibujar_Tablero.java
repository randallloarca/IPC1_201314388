
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Dibujar_Tablero extends JFrame {

    private JLabel[][] tablero;
    private JPanel panel;

    private JLabel cronometro, verCronometro, nombreJudador1, verNombreJugador1, nombreJudador2, verNombreJugador2;
    private JLabel personajesJugador1, personaje1_Jug1, personaje2_Jug1, personaje3_Jug1;
    private JLabel personajesJugador2, personaje1_Jug2, personaje2_Jug2, personaje3_Jug2;

    public Dibujar_Tablero() {

        add(getPanel());
        InicializarVentana();
    }

    public int tamMatriz() {
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Tamaño de La Matriz (Inicia de 8x8 a 18x18)", JOptionPane.QUESTION_MESSAGE));
        return tamaño;
    }

    public JPanel getPanel() {

        panel = new JPanel();

        int tam = tamMatriz();
        tablero = new JLabel[tam][tam];
        panel.setLayout(new GridLayout(tam, tam));
        int num = (int) Math.random() * 10;
        panel.setBounds(50, 50, 400, 400);
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                tablero[i][j] = new JLabel(String.valueOf(num));
                panel.add(tablero[i][j]);
            }
        }
        return panel;
    }

    public void InicializarVentana() {
        setLayout(null);
        setSize(800, 600);
        setTitle("Medievil");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}
