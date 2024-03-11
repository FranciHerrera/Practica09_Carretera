package practica09_carretera;

public class CarroReponedor extends Thread {

    private Puente puente;
    private int numCarrosR;
    private int numCarrosL;
    private int intervalo;

    public CarroReponedor(Puente puente, int numCarrosR, int numCarrosL, int intervalo) {
        this.puente = puente;
        this.numCarrosL = numCarrosL;
        this.numCarrosR = numCarrosR;
        this.intervalo = intervalo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(intervalo * 1000);
                puente.setCarroL(puente.getCarroL() + numCarrosL);
                puente.setCarroR(puente.getCarroR() + numCarrosR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Llegaron mas carros");
        }
    }
}
