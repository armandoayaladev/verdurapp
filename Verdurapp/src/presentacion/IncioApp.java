
package presentacion;

/**
 * Clase que permite iniciar el programa VerdurApp con una presentación y luego 
 * la ejecución del programa
 * 
 * @author: Armando Ayala C
 * @fecha: 18/05/2018
 */
public class IncioApp {
    
    public static void main(String args[]){
        try {
            presentación p = new presentación();
            Thread t = new Thread(p);
            t.start();
            t.sleep(3000);
            p.dispose();
            t.stop();
            new VentanaPrincipal().setVisible(true);
        } catch (InterruptedException ex) {
            System.err.println("Error al iniiar el programa");
        }
    }
}
