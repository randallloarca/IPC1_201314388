
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Ventana_Juego extends javax.swing.JFrame {

    //turnos
    public int turno_jugador = 1;
    public String turno_avatar;

    //Variables usadas para generar la matriz del juego, y el tablero 
    public int tam = 0;
    public Personaje per;
    public Vida_Bomba Vida_Bomba;
    public int[][] vecL;
    public int tambloquex = 0;
    public JLabel[][] tablero;
    private JPanel panel;
    public int TamañoMatriz;

    //variables usadas para ingresar los nombres de los jugadores y seleccionar sus personajes en el orden que ellos deseen
    private String nombreJ1, nombreJ2, Per1Jug1, Per2Jug1, Per3Jug1, Per1Jug2, Per2Jug2, Per3Jug2;
    private javax.swing.border.Border border;

    //set y get del JLabel del tiempo
    public JLabel getTiempo() {
        return Tiempo;
    }

    public void setTiempo(JLabel Tiempo) {
        this.Tiempo = Tiempo;
    }

    //constructor de la clase
    public Ventana_Juego() {
        initComponents();
        ConfiguracionDelJuego();
        add(getPanel(tamMatriz()));
        inicializarMatriz();
        Crear_Personajes(TamañoMatriz);
        crear_vidas(TamañoMatriz);
        crear_bombas(TamañoMatriz);
        crearDado();
        add(GenerarVidasJug1());
        add(GenerarVidasJug2());
    }

    //variables usadas para menear el avatar
    int avatar_f = 0;
    int avatar_c = 0;

    public void buscar_avatar(int avatar) {
        for (int i = 0; i < TamañoMatriz; i++) {
            for (int j = 0; j < TamañoMatriz; j++) {
                if (mtablero[i][j] == avatar) {
                    avatar_f = i;
                    avatar_c = j;
                }
            }
        }
    }

    //metodo para crear las vidas aleatorias del juego
    private void crear_vidas(int tamaño) {
        double real = (0.05) * tamaño * tamaño;
        int c_vidas = (int) real;
        llenar_vidas(c_vidas, 7);
    }

    //metodo para crear las bomabas aleatorias del juego
    private void crear_bombas(int tamaño) {
        double real = (0.1) * tamaño * tamaño;
        int c_vidas = (int) real;
        llenar_vidas(c_vidas, 8);
    }

    //metodo usado para crear personajes aleatorios en la matriz del juego
    private void Crear_Personajes(int tamaño) {
        Random rnd = new Random();
        for (int i = 1; i <= 6; i++) {
            int f = rnd.nextInt(tamaño);
            int c = rnd.nextInt(tamaño);
            switch (i) {
                case 1:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per1Jug1);
                    } else {
                        i--;
                    }
                    break;
                case 2:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per2Jug1);
                    } else {
                        i--;
                    }
                    break;
                case 3:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per3Jug1);
                    } else {
                        i--;
                    }
                    break;
                case 4:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per1Jug2);
                    } else {
                        i--;
                    }
                    break;
                case 5:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per2Jug2);
                    } else {
                        i--;
                    }
                    break;
                case 6:
                    if (mtablero[fila][columna] == 0) {
                        llenar_Matriz(f, c, i, Per3Jug2);
                    } else {
                        i--;
                    }
                    break;
            }
        }
    }

    //definir
    //vacia = 0;
    //per1=1;
    //per12=2
    //per12=3
    //per22=4
    //per22=5
    //per22=6
    //vidas =7;
    //bombas = 8;
    public int[][] mtablero;
    public int fila;
    public int columna;

    public void llenar_Matriz(int fila, int columna, int elemento, String nombre) {
        Boolean item = false;
        int temp = 0;
        if (mtablero[fila][columna] == 0) {
            if (elemento == 7) {
                mtablero[fila][columna] = elemento;
                tablero[fila][columna].setIcon(this.Vida_Bomba.obtener_Vida(tambloquex, tambloquex));
            }
            if (elemento == 8) {
                mtablero[fila][columna] = elemento;
                tablero[fila][columna].setIcon(this.Vida_Bomba.obtener_Bomba(tambloquex, tambloquex));
            }
            if (elemento == 1 || elemento == 2 || elemento == 3) {
                if (mtablero[fila][columna] == 7 || mtablero[fila][columna] == 8) {
                    temp = mtablero[fila][columna];
                    item = true;

                }
                if (nombre.equals("Princesa")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenPrincesa(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                if (nombre.equals("Guerrero")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenGuerrero(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                if (nombre.equals("Mago")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenMago(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                if (item) {

                    llenar_Matriz(fila, columna, temp, "");
                }
            }
            if (elemento == 4 || elemento == 5 || elemento == 6) {
                if (nombre.equals("Princesa")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenPrincesa(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }
                if (nombre.equals("Guerrero")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenGuerrero(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }
                if (nombre.equals("Mago")) {
                    mtablero[fila][columna] = elemento;
                    tablero[fila][columna].setIcon(this.per.Obtener_ImagenMago(tambloquex, tambloquex));
                    tablero[fila][columna].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                }

            }
        }
    }

    public void inicializarMatriz() {
        mtablero = new int[TamañoMatriz][TamañoMatriz];
        for (int i = 0; i < TamañoMatriz; i++) {
            for (int j = 0; j < TamañoMatriz; j++) {
                mtablero[i][j] = 0;
            }
        }
    }

    public void llenar_vidas(int c_vidas, int elemento) {
        Random rposx = new Random();
        Random rposy = new Random();
        for (int c = 0; c < c_vidas; c++) {
            fila = rposx.nextInt(TamañoMatriz);
            columna = rposy.nextInt(TamañoMatriz);
            if (mtablero[fila][columna] == 0) {
                llenar_Matriz(fila, columna, elemento, "");
            } else {
                c--;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombreJug1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombreJug2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Tirar = new javax.swing.JButton();
        Mov_Izquierda = new javax.swing.JButton();
        Mov_Arriba = new javax.swing.JButton();
        Mov_Derecha = new javax.swing.JButton();
        Mov_Abajo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        personaje1 = new javax.swing.JLabel();
        personaje2 = new javax.swing.JLabel();
        personaje3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        personaje4 = new javax.swing.JLabel();
        personaje5 = new javax.swing.JLabel();
        personaje6 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        lblDado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Medievil");
        setName("ventana"); // NOI18N

        jLabel1.setText("Cronometro");

        jLabel3.setText("Nombre Jugador1");

        jLabel5.setText("Vidas");

        jLabel7.setText("Nombre Jugador 2");

        jLabel9.setText("Vidas");

        Tirar.setText("Tirar");
        Tirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TirarActionPerformed(evt);
            }
        });

        Mov_Izquierda.setText("Izquierda");
        Mov_Izquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mov_IzquierdaActionPerformed(evt);
            }
        });

        Mov_Arriba.setText("Arriba");

        Mov_Derecha.setText("Derecha");
        Mov_Derecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mov_DerechaActionPerformed(evt);
            }
        });

        Mov_Abajo.setText("Abajo");

        jLabel11.setText("Personajes");

        jLabel15.setText("Personajes");

        Regresar.setText("Regresar Menu");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Guardar.setText("Guardar Juego");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombreJug2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(personaje5, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(personaje4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(personaje1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(personaje2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(personaje3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(53, 53, 53))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(personaje6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Tirar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(502, 502, 502)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombreJug1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(591, 591, 591)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Mov_Arriba, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Mov_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Regresar)
                                .addGap(61, 61, 61)
                                .addComponent(Guardar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(Mov_Izquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Mov_Derecha, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nombreJug1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(nombreJug2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(personaje4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personaje5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(personaje6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Tirar))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mov_Arriba)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mov_Derecha)
                    .addComponent(Mov_Izquierda))
                .addGap(4, 4, 4)
                .addComponent(Mov_Abajo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Regresar)
                    .addComponent(Guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        try {
            int dato;
            dato = JOptionPane.showConfirmDialog(null, "¡Desea Volver a la Página Principal?", "Seleccione Una Opción", JOptionPane.YES_NO_CANCEL_OPTION);
            if (dato == 0) {
                this.dispose();
                ComponentesDeJuego vj = new ComponentesDeJuego();
                vj.show();
                if (evt.getSource() == Regresar) {
                    corriendo = false;
                    iniciaHilo = false;
                    Cronometro c = new Cronometro(Tiempo,cantidadCrono);
                    c.stop();
                }
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_RegresarActionPerformed

    //variable usada para capturar el valor del dado obtenido por el random
    int valor_dado = 0;
    private void TirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TirarActionPerformed
        Random rnd = new Random();
        this.repaint();
        valor_dado = rnd.nextInt(6) + 1;
        lblDado.setIcon(ImagenDado(valor_dado));

        switch (turno_jugador) {
            case 1:
                turno_avatar = this.Per1Jug1;
                break;
            case 2:
                turno_avatar = this.Per2Jug1;
                break;
            case 3:
                turno_avatar = this.Per3Jug1;
                break;
            case 4:
                turno_avatar = this.Per1Jug2;
                break;
            case 5:
                turno_avatar = this.Per2Jug2;
                break;
            case 6:
                turno_avatar = this.Per3Jug2;
                break;
        }
        JOptionPane.showMessageDialog(null, "Turno: " + turno_jugador + " " + "Jugador: " + turno_avatar);

    }//GEN-LAST:event_TirarActionPerformed

    public void limpiar_casilla(int fila, int columna, int elemento) {
        if (mtablero[fila][columna] == elemento) {
            tablero[fila][columna].setIcon(null);
            tablero[fila][columna].setBorder(border);
            mtablero[fila][columna] = 0;
        }
    }
    Thread hilo;

    private void Mov_IzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mov_IzquierdaActionPerformed
        buscar_avatar(turno_jugador);
        select_Turno = true;
        if (select_Turno == true) {
            Movimiento_Personaje mov = new Movimiento_Personaje(this, valor_dado, 4, select_Turno);
            mov.start();
            cambia_turnos(turno_jugador);
        }

    }//GEN-LAST:event_Mov_IzquierdaActionPerformed

    public boolean select_Turno = false;
    private void Mov_DerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mov_DerechaActionPerformed
        //solo prueba
        buscar_avatar(turno_jugador);
        //
        select_Turno = true;
        if (select_Turno == true) {
            Movimiento_Personaje mov = new Movimiento_Personaje(this, valor_dado, 6, select_Turno);
            mov.start();

            cambia_turnos(turno_jugador);
        }

    }//GEN-LAST:event_Mov_DerechaActionPerformed

    public void cambia_turnos(int turno) {

        switch (turno_jugador) {
            case 1:
                turno_jugador = 4;
                break;
            case 4:
                turno_jugador = 2;
                break;
            case 2:
                turno_jugador = 5;
                break;
            case 5:
                turno_jugador = 3;
                break;
            case 3:
                turno_jugador = 6;
                break;
            case 6:
                turno_jugador = 1;
                break;
        }

    }
    //Funcion en la cual genera el tamaño de la matriz
    static int cantidadCrono;

    public int tamMatriz() {
        TamañoMatriz = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Tamaño de La Matriz (Inicia de 8x8 a 18x18)", JOptionPane.INFORMATION_MESSAGE));
        cantidadCrono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese El Tiempo del Juego", JOptionPane.INFORMATION_MESSAGE));
        int comprobar = TamañoMatriz;
        if ((comprobar < 8 || comprobar > 18)) {
            JOptionPane.showMessageDialog(null, "Fuera del Rango establecido al inicio, Ingrese de Nuevo los Valores");
            comprobar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese Tamaño de La Matriz (Inicia de 8x8 a 18x18)", JOptionPane.INFORMATION_MESSAGE));
        } else {
            if (corriendo == false) {
                iniciaHilo = true;
                corriendo = true;
                iniciarHiloCronometro();
            }
        }

        return TamañoMatriz;
    }

    // esta funcion nos sirve para crear todas las configuraciones previas antes de comenzar el juego
    public String ConfiguracionDelJuego() {
        Jugador1();
        Jugador2();
        cronometro();
        return nombreJ1;
    }

    // Metodo en el cual se genera las retricciones de jugador 1, nombre, personaje
    public void Jugador1() {
        nombreJ1 = JOptionPane.showInputDialog(null, "Ingrese Nombre Jugador 1:", JOptionPane.INFORMATION_MESSAGE);
        nombreJug1.setText(nombreJ1);
        border = LineBorder.createGrayLineBorder();
        nombreJug1.setBorder(BorderFactory.createLineBorder(Color.RED));
        nombreJug1.setHorizontalAlignment(JLabel.CENTER);
        String[] personajes = {
            "Princesa",
            "Mago",
            "Guerrero"
        };
        ImageIcon icon = new ImageIcon();
        Per1Jug1 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[0]);
        Per2Jug1 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[1]);
        Per3Jug1 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[2]);
        personaje1.setText(Per1Jug1);
        personaje1.setBorder(BorderFactory.createLineBorder(Color.RED));
        personaje1.setHorizontalAlignment(JLabel.CENTER);
        personaje2.setText(Per2Jug1);
        personaje2.setBorder(BorderFactory.createLineBorder(Color.RED));
        personaje2.setHorizontalAlignment(JLabel.CENTER);
        personaje3.setText(Per3Jug1);
        personaje3.setBorder(BorderFactory.createLineBorder(Color.RED));
        personaje3.setHorizontalAlignment(JLabel.CENTER);
    }

    //Metodo en el cual se genera las retricciones de jugador 2, nombre, personaje
    public void Jugador2() {
        ImageIcon icon = new ImageIcon();
        nombreJ2 = JOptionPane.showInputDialog(null, "Ingrese Nombre Jugador 2:", JOptionPane.INFORMATION_MESSAGE);
        nombreJug2.setText(nombreJ2);
        nombreJug2.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        nombreJug2.setHorizontalAlignment(JLabel.CENTER);
        String[] personajes = {
            "Princesa",
            "Mago",
            "Guerrero"
        };
        Per1Jug2 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[0]);
        Per2Jug2 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[1]);
        Per3Jug2 = (String) JOptionPane.showInputDialog(null, "Seleccione El Orden de Sus Personajes", "Personajes", JOptionPane.INFORMATION_MESSAGE, icon, personajes, personajes[2]);
        personaje4.setText(Per1Jug2);
        personaje4.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        personaje4.setHorizontalAlignment(JLabel.CENTER);
        personaje5.setText(Per2Jug2);
        personaje5.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        personaje5.setHorizontalAlignment(JLabel.CENTER);
        personaje6.setText(Per3Jug2);
        personaje6.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        personaje6.setHorizontalAlignment(JLabel.CENTER);
    }

    //variables usadas para generar el conometro del juego
    public static int hora = 0, minutos = 0, segundos = 0;
    public JLabel Tiempo;

    //Crea el Conometro en le cual iniciara el juego
    public void cronometro() {
        Tiempo = new JLabel();
        Tiempo.setBounds(610, 25, 70, 20);
        Tiempo.setBorder(border);
        Tiempo.setVisible(true);
        Tiempo.setHorizontalAlignment(JLabel.CENTER);
        add(Tiempo);
    }

    //Variales utilizadas para el funcionamiento del cronometro
    public static boolean iniciaHilo = true, corriendo = false;

    //Metodo en el cual se le da el inicio al crometro
    private void iniciarHiloCronometro() {
        if (iniciaHilo == true) {
            minutos = cantidadCrono;
            Cronometro miCronometro = new Cronometro(Tiempo,cantidadCrono);
            miCronometro.start();
        }
    }

//    Crea el panel donde se encuentra la matriz que se visualizara como el tablero del juego
    public JPanel getPanel(int TamañoMatriz) {
        tambloquex = 400 / TamañoMatriz;// se utliza para redimensionar el tamaño de la imagen
        panel = new JPanel();//se crea el panel donde estara contenido la matriz
        border = LineBorder.createBlackLineBorder();//se le crea un borde al panel
        panel.setBorder(border);//se le setea el borde al panel
        tablero = new JLabel[TamañoMatriz][TamañoMatriz];
        panel.setLayout(new GridLayout(TamañoMatriz, TamañoMatriz));
        panel.setBounds(50, 20, 400, 400);
        vecL = new int[TamañoMatriz][TamañoMatriz];//se inicializa la matriz de enteros con el tamaño correspondiente de la matriz
        vecL[0][0] = 1;//el vector inicia 0 
        this.Vida_Bomba = new Vida_Bomba();// inicializamos la vida o bomba 
        this.per = new Personaje();
        this.Vida_Bomba.Posicion_VidaYBomba = 0;// colocamos la posicion donde se encuentra la vida o bomba
        for (int i = 1; i < TamañoMatriz; i++) {//recorremos la matriz
            for (int j = 1; j < TamañoMatriz; j++) {
                vecL[i][j] = 0;
            }
        }
//        repintar();
        for (int i = 0; i < TamañoMatriz; i++) {
            for (int j = 0; j < TamañoMatriz; j++) {
                tablero[i][j] = new JLabel();
                tablero[i][j].setBorder(border);
                panel.add(tablero[i][j]);
            }
        }
        return panel;
    }

    public void repintar() {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                JLabel tab;
                //vacio
                if (vecL[i][j] == 0) {
                    tab = new JLabel();
                    tab.setOpaque(false);
                    tab.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 1)));
                    tab.setBounds(i * tambloquex, 0, tambloquex, 150);
                    tablero[i][j] = tab;
                    panel.add(tablero[i][j], BorderLayout.CENTER);
                    panel.repaint();

                }
                if (vecL[i][j] == 1) {
                    tab = new JLabel(this.Vida_Bomba.obtener_Vida(tambloquex, tambloquex));
                    tab.setOpaque(false);
                    tab.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 1)));
                    tab.setBounds(i * tambloquex, 0, tambloquex, 150);
                    tablero[i][j] = tab;
                    panel.add(tablero[i][j], BorderLayout.CENTER);
                    panel.repaint();
                }
            }
        }
    }

    //Variable usadas para generar el dado del juego
    Icon ricono;
    ImageIcon imagen;

    // metodo en el cual creamos la imagen del dado que se visualizara en el juego
    public void crearDado() {
        ImageIcon imagen = new ImageIcon(getClass().getResource("Imagenes/1.jpg"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblDado.getWidth(), lblDado.getHeight(), Image.SCALE_DEFAULT));
        lblDado.setIcon(icono);
        this.repaint();
    }

    //creamos una funcion de tipo icon la cual nos devolvera las imagenes del dado de 1 a 6
    public Icon ImagenDado(int o) {
        switch (o) {
            case 1:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/1.jpg"));
                break;
            case 2:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/2.jpg"));
                break;
            case 3:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/3.jpg"));
                break;
            case 4:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/4.jpg"));
                break;
            case 5:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/5.jpg"));
                break;
            case 6:
                imagen = new ImageIcon(getClass().getResource("/Imagenes/6.jpg"));
                break;
        }
        ricono = new ImageIcon(imagen.getImage().getScaledInstance(lblDado.getWidth(), lblDado.getHeight(), Image.SCALE_DEFAULT));
        return ricono;
    }

    private JLabel[] vidasJugs;
    private JPanel contenedorJug1, contenedorJug2;

    public JPanel GenerarVidasJug1() {
        contenedorJug1 = new JPanel();
        contenedorJug1.setBounds(550, 95, 120, 80);

        border = LineBorder.createBlackLineBorder();
        contenedorJug1.setBorder(border);
        int cantidad = 5;
        vidasJugs = new JLabel[cantidad];
// inicializamos la vida o bomba 
        for (int i = 0; i < cantidad; i++) {
            this.Vida_Bomba = new Vida_Bomba();
            vidasJugs[i] = new JLabel(this.Vida_Bomba.obtener_Vida(20, 20));
            contenedorJug1.add(vidasJugs[i]);
            contenedorJug1.repaint();
        }
        return contenedorJug1;
    }

    public JPanel GenerarVidasJug2() {
        contenedorJug2 = new JPanel();
        contenedorJug2.setBounds(550, 205, 120, 80);
        border = LineBorder.createBlackLineBorder();
        contenedorJug2.setBorder(border);
        int cantidad = 5;
        vidasJugs = new JLabel[cantidad];
// inicializamos la vida o bomba 
        for (int i = 0; i < cantidad; i++) {
            this.Vida_Bomba = new Vida_Bomba();
            vidasJugs[i] = new JLabel(this.Vida_Bomba.obtener_Vida(20, 20));
            contenedorJug2.add(vidasJugs[i]);
            contenedorJug2.repaint();
        }
        return contenedorJug2;
    }

    //Metodo Main de la clase
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new AluminiumLookAndFeel());
            ComponentesDeJuego vj;
            vj = new ComponentesDeJuego();
            vj.show();
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Mov_Abajo;
    private javax.swing.JButton Mov_Arriba;
    private javax.swing.JButton Mov_Derecha;
    private javax.swing.JButton Mov_Izquierda;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Tirar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDado;
    private javax.swing.JLabel nombreJug1;
    private javax.swing.JLabel nombreJug2;
    private javax.swing.JLabel personaje1;
    private javax.swing.JLabel personaje2;
    private javax.swing.JLabel personaje3;
    private javax.swing.JLabel personaje4;
    private javax.swing.JLabel personaje5;
    private javax.swing.JLabel personaje6;
    // End of variables declaration//GEN-END:variables

}
