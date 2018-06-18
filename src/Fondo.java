
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author MariaJo
 */
public class Fondo extends JPanel {

    public Fondo() {
        setSize(400, 400);
    }

    public void paint(Graphics grafico) {
        Dimension height = getSize();
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/fondo.png"));
        grafico.drawImage(img.getImage(), 50,20,height.width, height.height, null);
        setOpaque(false);
        super.paintComponents(grafico);

    }
}
