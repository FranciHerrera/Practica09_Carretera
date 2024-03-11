package practica09_carretera;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Puente {

    private int carroL;
    private int carroR;

    private boolean derechaPasando;
    private boolean izquierdaPasando;

    public Puente() {
        this.carroL = 1;
        this.carroR = 1;
        this.derechaPasando = false;
        this.izquierdaPasando = false;
    }

    public int getCarroL() {
        return carroL;
    }

    public int getCarroR() {
        return carroR;
    }

    public void setCarroL(int carroL) {
        this.carroL = carroL;
    }

    public void setCarroR(int carroR) {
        this.carroR = carroR;
    }

    public boolean isDerechaPasando() {
        return derechaPasando;
    }

    public boolean isIzquierdaPasando() {
        return izquierdaPasando;
    }

    public synchronized void carroDerechaPasa() {
        while (izquierdaPasando || carroR == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        derechaPasando = true;
        if(carroR > 0){
            carroR--;
        }
        if (carroR == 0) {
            carroL = getCarroL();
            derechaPasando = false;
        }
        notifyAll();
    }

    public synchronized void carroIzquierdaPasa() {
        while (derechaPasando || carroL == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        izquierdaPasando = true;
        if(carroL > 0){
            carroL--;
        }
        if (carroL == 0) {
            carroR = getCarroR();
            izquierdaPasando = false;
        }
        notifyAll();

    }
}
