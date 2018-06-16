
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
