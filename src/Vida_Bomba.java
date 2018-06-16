
import java.awt.Image;
import javax.swing.ImageIcon;

public class Vida_Bomba {

    public int Posicion_VidaYBomba = 0;
    public ImageIcon imgVida_Bomba;

    public ImageIcon obtener_Vida(int tamx, int tamy) {
        ImageIcon vida = new ImageIcon(getClass().getResource("/Imagenes/vida.png"));
        Image vida1 = vida.getImage();
        Image imgtam = vida1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
        vida = new ImageIcon(imgtam);
        return vida;
    }

    public ImageIcon obtener_Bomba(int tamx, int tamy) {
        ImageIcon bomba = new ImageIcon(getClass().getResource("/Imagenes/bomba.png"));
        Image bomba1 = bomba.getImage();
        Image imgtam = bomba1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
        bomba = new ImageIcon(imgtam);
        return bomba;
    }
    
//    public ImageIcon obtener_Fondo(int tamx, int tamy) {
//        ImageIcon bomba = new ImageIcon(getClass().getResource("/Imagenes/fondo.png"));
//        Image bomba1 = bomba.getImage();
//        Image imgtam = bomba1.getScaledInstance(tamx, tamy, Image.SCALE_DEFAULT);
//        bomba = new ImageIcon(imgtam);
//        return bomba;
//    }
}
