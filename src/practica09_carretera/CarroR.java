package practica09_carretera;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroR extends Thread {

    private Puente puente;
    private String id;
    private int movimiento;

    public CarroR(Puente puente, String id) {
        this.puente = puente;
        this.id = id;
        this.movimiento = 0;
    }

    public void run() {
        while (true) {
            try {
                puente.carroDerechaPasa();
                synchronized (puente) {
                    System.out.println("Carro de la " + id + " pasando");
                    System.out.println("Carros izquierda: "+puente.getCarroL()+" ----- Carros derecha: "+puente.getCarroR()); 
                }
                sleep(750);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarroR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
