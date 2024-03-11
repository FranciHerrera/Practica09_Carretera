package practica09_carretera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameCarretera extends JFrame implements ActionListener {

    JButton btnIniciar;

    Puente p = new Puente();
    CarroR hcr = new CarroR(p, "derecha");
    CarroL hcl = new CarroL(p, "izquierda");
    CarroReponedor reponer = new CarroReponedor(p, 1, 1, 3);

    Graphics cL, cR, w, fondo;

    ImageIcon cafe, rojo, way;

    private int carrosIzq, carrosDer;

    public FrameCarretera() {
        setTitle("Carretera");
        setSize(1000, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(10, 400, 80, 40);
        btnIniciar.addActionListener(this);

        cafe = new ImageIcon("src/practica09_carretera/CarroCafe.png");
        rojo = new ImageIcon("src/practica09_carretera/CarroRojo.png");
        way = new ImageIcon("src/practica09_carretera/Carretera.png");

        add(btnIniciar);
        setVisible(true);
    }

    public void Draw() {
        cL = this.getGraphics();
        cR = this.getGraphics();
        w = this.getGraphics();
        fondo = this.getGraphics();

        if (p.isIzquierdaPasando()) {
            for (int i = -50; i <= 1100; i += 50) {
                //w.clearRect(0, 0, 1000, 500);
                fondo.drawImage(way.getImage(), 0, 0, 1000, 500, null);
                cL.drawImage(cafe.getImage(), i, 180, 100, 80, null);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrameCarretera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (p.isDerechaPasando()) {
            for (int i = 1100; i >= -100; i -= 50) {
                //w.clearRect(0, 0, 1000, 500);
                fondo.drawImage(way.getImage(), 0, 0, 1000, 500, null);
                cR.drawImage(rojo.getImage(), i, 180, 111, 80, null);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrameCarretera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void Loop() {
        while (true) {
            Draw();
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            p.setCarroL(2);
            p.setCarroR(2);
            System.out.println("EMPIEZA");
            hcl.start();
            hcr.start();
            reponer.start();
            btnIniciar.setVisible(false);
            Loop();
        }
    }
}
