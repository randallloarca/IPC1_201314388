
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ComponentesDeJuego extends JFrame {

    private JLabel nombre;
    private JButton IniciarJuego, CargarArchivo, Salir;

    public ComponentesDeJuego() {
        InicializarFrame();
        getLabel();
        getButton();
    }

    public void InicializarFrame() {
        setLayout(null);
        setSize(400, 300);
        setTitle("Medievil");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JLabel getLabel() {
        nombre = new JLabel("Iniciar Partida");
        nombre.setBounds(75, 10, 250, 50);
        nombre.setVisible(true);
        Font auxFont = nombre.getFont();
        nombre.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
        nombre.setHorizontalAlignment(JLabel.CENTER);
        this.add(nombre);
        return nombre;
    }
    Font auxFont;

    public JButton getButton() {
        IniciarJuego = new JButton("Jugar");
        IniciarJuego.setBounds(75, 80, 250, 30);
        IniciarJuego.setVisible(true);
        auxFont = IniciarJuego.getFont();
        IniciarJuego.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
        IniciarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Ventana_Juego tablero = new Ventana_Juego();
                tablero.show();

            }
        });
        this.add(IniciarJuego);

        CargarArchivo = new JButton("Cargar Archivo");
        CargarArchivo.setBounds(75, 130, 250, 30);
        CargarArchivo.setVisible(true);
        auxFont = CargarArchivo.getFont();
        CargarArchivo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
        CargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(CargarArchivo);

        Salir = new JButton("Salir");
        Salir.setBounds(75, 180, 250, 30);
        Salir.setVisible(true);
        auxFont = Salir.getFont();
        Salir.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dato;
                dato = JOptionPane.showConfirmDialog(null, "Desea Salir del Juego.", "Seleccione Una Opción:", JOptionPane.YES_NO_CANCEL_OPTION);
                if (dato == 0) {
                    dispose();
                }
            }
        });
        this.add(Salir);

        return IniciarJuego;
    }
}
