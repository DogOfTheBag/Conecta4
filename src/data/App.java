package data;

import GUI.VPal;

/**
 *
 * @author alber
 */
public class App extends Thread{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new App().start();
    }

    @Override
    public void run() {
        //le paso a la ventana al juego para que pueda acceder a él el panel de juego posteriormente
        Juego juego = new Juego();
        new VPal("Conecta 4", juego).setVisible(true);
      
    }

}
