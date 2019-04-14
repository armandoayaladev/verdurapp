/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Armando
 */
public class Configuraciones {

    private String urlConfigEmail;
    private String urlConfigConsecutivo;
    
    public Configuraciones() {
        this.urlConfigEmail = "C:\\Program Files (x86)\\VerdurApp\\config\\config.email.txt";
        this.urlConfigConsecutivo = "C:\\Program Files (x86)\\VerdurApp\\config\\config.sequence.txt";
    }
    
    public Boolean configurarEmail(String email, String pass){
        
        BufferedWriter bw = null;
        try {
            File archivo = new File(this.urlConfigEmail);
            archivo.setWritable(true);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(email+";"+pass);
            bw.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error al configurar el correo");
            return false;
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Boolean configurarConsecutivo(Integer consecutivo){
        
        BufferedWriter bw = null;
        try {
            File archivo = new File(this.urlConfigConsecutivo);
            archivo.setWritable(true);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(new DecimalFormat("00000").format(consecutivo));
            bw.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error al configurar el consecutivo");
            return false;
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String obtenerConsecutivo(){
        try {
            File archivoConfigEmail = new File(urlConfigConsecutivo);
            FileReader fr = new FileReader(archivoConfigEmail);
            BufferedReader br = new BufferedReader(fr);
            String consecutivoString = br.readLine();
            Integer consecutivo = Integer.parseInt(consecutivoString);
            consecutivo++;
            br.close();
            fr.close();
            return new DecimalFormat("00000").format(consecutivo);
        } catch (Exception e) {
            return "";
        }
    }

    public String getUrlConfigEmail() {
        return urlConfigEmail;
    }

    public void setUrlConfigEmail(String urlConfigEmail) {
        this.urlConfigEmail = urlConfigEmail;
    }
    
    
}
