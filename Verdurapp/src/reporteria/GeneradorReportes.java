package reporteria;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import src.Constantes;

/**
 * Clase que controla los diferentes reportes que se producen
 *
 * @autor: Armando Ayala C
 * @fecha: 07/05/2018
 */
public class GeneradorReportes {

    //<editor-fold desc="atributos" defaultstate="collapsed">
    private Map listadoReportes;
    private String pathRutaAlmacenamientoReporte;
    //</editor-fold>

    //<editor-fold desc="metodos" defaultstate="collapsed">
    /**
     * Constructor por defecto de la clase
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public GeneradorReportes() {
        this.listadoReportes = new HashMap<String, String>();
        this.listadoReportes.put("rpt_facturacionGeneral", "/presentacion/reportes/rptAuxiilo.jasper");
        this.pathRutaAlmacenamientoReporte = System.getProperty("user.home") + "\\Desktop\\Reportes Generados - VERDURAPP";
    }

    /**
     * Metodo que permite verificar si una ruta es valida o no
     *
     * @autor: Armando Ayala C
     * @fecha: 18/05/2018
     * @param pRuta
     */
    public void verificarRutaPorCrear(String pRuta) {
        File folder = new File(pRuta);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Metodo para iniciar la ruta por año
     *
     * @autor: Armando Ayala C
     * @fecha: 18/05/2018
     * @return
     */
    public String generarRutaPorAnno() {
        Calendar calendario = Calendar.getInstance();
        return "\\" + calendario.get(Calendar.YEAR);
    }

    /**
     * Metodo que permite generar la ruta por mes
     *
     * @autor: Armando Ayala C
     * @fecha: 18/05/2018
     * @return
     */
    public String generarRutaPorMes() {
        Calendar calendario = Calendar.getInstance();
        String ruta = "";

        switch (calendario.get(Calendar.MONTH)) {
            case Calendar.JANUARY:
                ruta += "\\ENERO";
                break;
            case Calendar.FEBRUARY:
                ruta += "\\FEBRERO";
                break;
            case Calendar.MARCH:
                ruta += "\\MARZO";
                break;
            case Calendar.APRIL:
                ruta += "\\ABRIL";
                break;
            case Calendar.MAY:
                ruta += "\\MAYO";
                break;
            case Calendar.JUNE:
                ruta += "\\JUNIO";
                break;
            case Calendar.JULY:
                ruta += "\\JULIO";
                break;
            case Calendar.AUGUST:
                ruta += "\\AGOSTO";
                break;
            case Calendar.SEPTEMBER:
                ruta += "\\SETIEMBRE";
                break;
            case Calendar.OCTOBER:
                ruta += "\\OCTUBRE";
                break;
            case Calendar.NOVEMBER:
                ruta += "\\NOVIEMBRE";
                break;
            case Calendar.DECEMBER:
                ruta += "\\DICIEMBRE";
                break;
        }
        return ruta;
    }

    /**
     * Metodo que devuelve la ruta del reporte por id
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param pIdReporte
     * @return
     */
    public String obtenerReportePorId(String pIdReporte) {
        return this.listadoReportes.get(pIdReporte).toString();
    }

    /**
     * Metodo que permite obtener el nombre del reporte al momento en que se
     * genera el reporte
     *
     * @autor: Armando Ayala C
     * @fecha: 18/05/2018
     * @return
     */
    public String obtenerNombreReporte() {
        Calendar calendario = Calendar.getInstance();
        String nombre = " ";

        switch (calendario.get(Calendar.MONTH)) {
            case Calendar.JANUARY:
                nombre += "01-";
                break;
            case Calendar.FEBRUARY:
                nombre += "02-";
                break;
            case Calendar.MARCH:
                nombre += "03-";
                break;
            case Calendar.APRIL:
                nombre += "04-";
                break;
            case Calendar.MAY:
                nombre += "05-";
                break;
            case Calendar.JUNE:
                nombre += "06-";
                break;
            case Calendar.JULY:
                nombre += "07-";
                break;
            case Calendar.AUGUST:
                nombre += "08-";
                break;
            case Calendar.SEPTEMBER:
                nombre += "09-";
                break;
            case Calendar.OCTOBER:
                nombre += "10-";
                break;
            case Calendar.NOVEMBER:
                nombre += "11-";
                break;
            case Calendar.DECEMBER:
                nombre += "12-";
                break;
        }

        nombre += calendario.get(Calendar.DAY_OF_MONTH) + " ";
        nombre += calendario.get(Calendar.HOUR) + "-" + calendario.get(Calendar.MINUTE) + "-" + calendario.get(Calendar.SECOND);
        return nombre;
    }

    /**
     * Metodo que permite generar el reporte
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     * @param pNombreReporte
     * @param pParametros
     * @param pDataSource
     */
    public void generarReporte(String pNombreReporte, Map pParametros, JRCsvDataSource pDataSource) {
        try {            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(GeneradorReportes.class.getResource(obtenerReportePorId(pNombreReporte)));
            JasperPrint jp = JasperFillManager.fillReport(reporte, pParametros, pDataSource);
            verificarRutaPorCrear(this.pathRutaAlmacenamientoReporte);            
            this.pathRutaAlmacenamientoReporte += generarRutaPorAnno();
            verificarRutaPorCrear(this.pathRutaAlmacenamientoReporte);
            this.pathRutaAlmacenamientoReporte += generarRutaPorMes();
            verificarRutaPorCrear(this.pathRutaAlmacenamientoReporte);

            String nombreReporte = this.pathRutaAlmacenamientoReporte + "\\" + "Facturación General (" + obtenerNombreReporte() + ").pdf";
            JasperExportManager.exportReportToPdfFile(jp, nombreReporte);
            File f = new File(nombreReporte);
            Desktop.getDesktop().open(f);
            JOptionPane.showMessageDialog(null, "Reporte exitosamente generado.\nEncuentre el reporte en la carpeta: Reportes Generados - VERDURAPP", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getStackTrace());
            JOptionPane.showMessageDialog(null, "Se produjo un problema al generar el reporte", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Se produjo un problema al intentar abrir el reporte", "ERROR", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, ex.getStackTrace());
        }
    }

    //</editor-fold>
}
