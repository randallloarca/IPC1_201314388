
import java.awt.Image;
import javax.swing.ImageIcon;

public class Personaje {

    public int Posicion_Personaje_X = 0,Posicion_Personaje_Y = 0;
    public ImageIcon imgper;

    public ImageIcon Obtener_ImagenPrincesa(int tamx, int tamy) {
        ImageIcon per = new ImageIcon(getClass().getResource("/Imagenes/princesa.png"));
        Image per1 = per.getImage();
        Image imgtam = per1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
        per = new ImageIcon(imgtam);
        return per;
    }

    public ImageIcon Obtener_ImagenGuerrero(int tamx, int tamy) {
        ImageIcon per = new ImageIcon(getClass().getResource("/Imagenes/guerrero.png"));
        Image per1 = per.getImage();
        Image imgtam = per1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
        per = new ImageIcon(imgtam);
        return per;
    }

    public ImageIcon Obtener_ImagenMago(int tamx, int tamy) {
        ImageIcon per = new ImageIcon(getClass().getResource("/Imagenes/mago.png"));
        Image per1 = per.getImage();
        Image imgtam = per1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
        per = new ImageIcon(imgtam);
        return per;
    }
}
