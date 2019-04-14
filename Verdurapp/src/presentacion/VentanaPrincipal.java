package presentacion;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reporteria.GeneradorReportes;
import src.Configuraciones;
import src.Constantes;
import src.ListadoProductos;

/**
 * Clase principal del programa
 *
 * @autor: Armando Ayala C
 * @fecha: 07/05/2018
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //<editor-fold desc="atributos" defaultstate="collapsed">
    private ListadoProductos listadoProductos;
    private File archivoCSV_OC;
    private File archivoCSV_OEM;
    private DefaultTableModel dtm;
    private GeneradorReportes gr;

    private String rutaConfiguracionGeneral;
    public Boolean mostrarVentanaConfiguracionCorreo;

    //</editor-fold>
    //<editor-fold desc="metodos" defaultstate="collapsed">
    /**
     * Constructor de la clase
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public VentanaPrincipal() {
        initComponents();
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/multimedia/verdurapp-icon.png")));
        dtm = (DefaultTableModel) this.jtbl_listadoProductos.getModel();
        gr = new GeneradorReportes();
        this.listadoProductos = new ListadoProductos();
        this.btn_generarReporte.setVisible(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir de la aplicación?", "¡Atención!", JOptionPane.WARNING_MESSAGE);
                if (x == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        crearArchivoCSV();
        eliminarRowsIniciales();
        formatoCeldas();
        

        this.mostrarVentanaConfiguracionCorreo = false;
    }
    
    public void crearArchivoCSV(){
        try {
            File csv = new File(System.getProperty("user.home") + "/Documents/config.listado.csv");
            if(!csv.exists()){
                csv.createNewFile();
            }else{
                try (FileWriter fw = new FileWriter(csv)) {
                    fw.write("");
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Metodo que permite dar formato a las celdas de la tabla
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public void formatoCeldas() {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        this.jtbl_listadoProductos.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        this.jtbl_listadoProductos.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        this.jtbl_listadoProductos.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        this.jtbl_listadoProductos.getColumnModel().getColumn(4).setCellRenderer(dtcr);
        this.jtbl_listadoProductos.getColumnModel().getColumn(5).setCellRenderer(dtcr);
        this.jtbl_listadoProductos.getColumnModel().getColumn(6).setCellRenderer(dtcr);
    }

    /**
     * Metodo que elimina las filas iniciales al crear la tabla
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public void eliminarRowsIniciales() {
        int cant = this.dtm.getRowCount();
        for (int x = cant - 1; x >= 0; x--) {
            dtm.removeRow(x);
        }
    }

    /**
     * Metodo que permite cargar los datos en la tabla para mostrar
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public void cargarDatosEnTabla() throws IOException {
        
        Object[][] resultados = this.listadoProductos.obtieneListadoProductosParaJTable();
        //PrintWriter bw = null;
        //PrintWriter bw2 = null;
        OutputStreamWriter osw = null;
        try {
            File f = new File(System.getProperty("user.home") + "/Documents/config.listado.csv");
            f.setWritable(true);
            //FileWriter fw = new FileWriter(f);
            //bw2  = new PrintWriter(new FileWriter(f,true));
            //bw2  = new PrintWriter(new FileWriter(f,));
            FileOutputStream fos = new FileOutputStream(f);
            osw = new OutputStreamWriter(fos, "UTF-8");
            //bw2.write("");
            //bw2.close();
            String linea="codigo;detalle;sol;rec;unidad;precio;sub%\n";
            osw.write(linea);
            for (Object[] registroAux : resultados) {
            this.dtm.addRow(registroAux);
            linea = "\n";
            for(int x=0; x < registroAux.length; x++){
                if(x < registroAux.length-1){
                    if(registroAux[x].toString().contains("₡ ")){
                        linea += registroAux[x].toString().split("₡ ")[1]+";";
                    }else{
                        linea += registroAux[x].toString()+";";
                    }
                    
                    //bw.write(registroAux[x].toString());
                }else{
                    if(registroAux[x].toString().contains("₡ ")){
                        linea += registroAux[x].toString().split("₡ ")[1];
                    }else{
                        linea += registroAux[x].toString();
                    }
                    
                }                                
            }
            osw.write(linea+"%");
        }
            osw.close();
        } catch (Exception e) {
            osw.close();
        }
        

    }

    //</editor-fold>
    //<editor-fold desc="codigo generado" defaultstate="collapsed">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jl_pathArchivoSeleccionado_OC = new javax.swing.JLabel();
        jbtn_examinarOC = new javax.swing.JButton();
        jbtn_ejecutarCalculos = new javax.swing.JButton();
        jl_pathArchivoSeleccionado_OEM = new javax.swing.JLabel();
        jbtn_examinarOEM = new javax.swing.JButton();
        js_separador = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_listadoProductos = new javax.swing.JTable();
        btn_generarReporte = new javax.swing.JButton();
        jl_totalFactura = new javax.swing.JLabel();
        jtf_totalFactura = new javax.swing.JTextField();
        jmb_barraMenu = new javax.swing.JMenuBar();
        jm_archivo = new javax.swing.JMenu();
        jmi_infoApp = new javax.swing.JMenuItem();
        jmi_cerrarApp = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmi_configEmail = new javax.swing.JMenuItem();
        jmi_configuracionConsecutivo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("VerdurApp");
        setPreferredSize(new java.awt.Dimension(1000, 470));

        jl_pathArchivoSeleccionado_OC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_pathArchivoSeleccionado_OC.setText("Seleccione el archivo de Órdenes de Compra");

        jbtn_examinarOC.setText("Examinar");
        jbtn_examinarOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_examinarOCActionPerformed(evt);
            }
        });

        jbtn_ejecutarCalculos.setText("Calcular");
        jbtn_ejecutarCalculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ejecutarCalculosActionPerformed(evt);
            }
        });

        jl_pathArchivoSeleccionado_OEM.setText("Seleccione el archivo de Órdenes de Entrega");

        jbtn_examinarOEM.setText("Examinar");
        jbtn_examinarOEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_examinarOEMActionPerformed(evt);
            }
        });

        jtbl_listadoProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Detalle", "Solicitado", "Recibido", "Unidad", "Precio Unitario", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbl_listadoProductos);
        if (jtbl_listadoProductos.getColumnModel().getColumnCount() > 0) {
            jtbl_listadoProductos.getColumnModel().getColumn(0).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtbl_listadoProductos.getColumnModel().getColumn(1).setPreferredWidth(300);
            jtbl_listadoProductos.getColumnModel().getColumn(2).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(2).setPreferredWidth(5);
            jtbl_listadoProductos.getColumnModel().getColumn(3).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(3).setPreferredWidth(5);
            jtbl_listadoProductos.getColumnModel().getColumn(4).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(4).setPreferredWidth(5);
            jtbl_listadoProductos.getColumnModel().getColumn(5).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(5).setPreferredWidth(20);
            jtbl_listadoProductos.getColumnModel().getColumn(6).setResizable(false);
            jtbl_listadoProductos.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        btn_generarReporte.setText("Generar Reporte");
        btn_generarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarReporteActionPerformed(evt);
            }
        });

        jl_totalFactura.setText("Total");

        jtf_totalFactura.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jl_pathArchivoSeleccionado_OC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtn_examinarOC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtn_ejecutarCalculos))
                    .addComponent(js_separador)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jl_pathArchivoSeleccionado_OEM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtn_examinarOEM)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_generarReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_totalFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtf_totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_pathArchivoSeleccionado_OC)
                    .addComponent(jbtn_examinarOC)
                    .addComponent(jbtn_ejecutarCalculos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_pathArchivoSeleccionado_OEM)
                    .addComponent(jbtn_examinarOEM))
                .addGap(18, 18, 18)
                .addComponent(js_separador, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf_totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_generarReporte)
                        .addComponent(jl_totalFactura)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jm_archivo.setText("Archivo");

        jmi_infoApp.setText("Info");
        jmi_infoApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_infoAppActionPerformed(evt);
            }
        });
        jm_archivo.add(jmi_infoApp);

        jmi_cerrarApp.setText("Cerrar");
        jmi_cerrarApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_cerrarAppActionPerformed(evt);
            }
        });
        jm_archivo.add(jmi_cerrarApp);

        jmb_barraMenu.add(jm_archivo);

        jMenu1.setText("Configuración");

        jmi_configEmail.setText("Correo");
        jmi_configEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_configEmailActionPerformed(evt);
            }
        });
        jMenu1.add(jmi_configEmail);

        jmi_configuracionConsecutivo.setText("Consecutivo");
        jmi_configuracionConsecutivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_configuracionConsecutivoActionPerformed(evt);
            }
        });
        jMenu1.add(jmi_configuracionConsecutivo);

        jmb_barraMenu.add(jMenu1);

        setJMenuBar(jmb_barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>

    //<editor-fold desc="metodos generados" defaultstate="collapsed">
    /**
     * Metodo que permite cargar los datos de las OC y almacenarlos en un
     * listado para obtener los precios
     *
     * @autor: Armando Ayala C
     * @fecha: 15/05/2018
     * @param evt
     */
    private void jbtn_examinarOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_examinarOCActionPerformed
        try {
            JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + "/Desktop");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("*.CSV", "csv");
            jfc.setFileFilter(fnef);
            int seleccion = jfc.showOpenDialog(this.getContentPane());
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                this.archivoCSV_OC = jfc.getSelectedFile();
                if (listadoProductos.verificadorArchivoTab(archivoCSV_OC, "OC")) {
                    this.jl_pathArchivoSeleccionado_OC.setText(archivoCSV_OC.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado para las OC no se ha tabulado correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    this.archivoCSV_OC = null;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar el archivo CSV", "Error", ERROR);
        }
    }//GEN-LAST:event_jbtn_examinarOCActionPerformed

    private void jmi_cerrarAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_cerrarAppActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir de la aplicación?", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jmi_cerrarAppActionPerformed

    private void jbtn_ejecutarCalculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ejecutarCalculosActionPerformed
        try {
            if (this.archivoCSV_OC != null && this.archivoCSV_OEM != null) {
                eliminarRowsIniciales();
                this.listadoProductos.agregarProductos_OC(archivoCSV_OC);
                this.listadoProductos.agregarProductos_OEM(archivoCSV_OEM);
                cargarDatosEnTabla();
                this.btn_generarReporte.setVisible(true);
                this.jtf_totalFactura.setText(this.listadoProductos.obtenerPrecioFormatoMoneda(this.listadoProductos.getCantidadTotalListado()));
            } else {
                JOptionPane.showMessageDialog(null, "Verifique que el archivo se haya seleccionado");
            }
        } catch (Exception e) {
            System.err.println("Error al leer archivo");
        } finally {
            this.archivoCSV_OC = null;
            this.jl_pathArchivoSeleccionado_OC.setText("Seleccione el archivo de Órdenes de Compra");
            this.jl_pathArchivoSeleccionado_OEM.setText("Seleccione el archivo de Órdenes de Entrega");
        }
    }//GEN-LAST:event_jbtn_ejecutarCalculosActionPerformed

    private void jmi_infoAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_infoAppActionPerformed
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/multimedia/Armando Ayala Colores.png"));
        Image image = icono.getImage();
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(newimg);
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_INFO, "Info VerdurApp", JOptionPane.DEFAULT_OPTION, icono);
    }//GEN-LAST:event_jmi_infoAppActionPerformed

    private void btn_generarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarReporteActionPerformed
        try {
            ventanaInfoFactura v = new ventanaInfoFactura();
            v.setListadoProductos(this.listadoProductos);
            Dimension d = this.jDesktopPane1.getSize();
            Dimension d2 = v.getSize();
            v.setLocation((d.width - d2.width) / 2, (d.height - d2.height) / 2);
            this.jDesktopPane1.add(v);
            v.show();
        } catch (Exception e) {
            System.out.println("presentacion.VentanaPrincipal.btn_generarReporteActionPerformed()");
        }
    }//GEN-LAST:event_btn_generarReporteActionPerformed

    private void jbtn_examinarOEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_examinarOEMActionPerformed
        try {
            JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + "/Desktop");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("*.CSV", "csv");
            jfc.setFileFilter(fnef);
            int seleccion = jfc.showOpenDialog(this.getContentPane());
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                this.archivoCSV_OEM = jfc.getSelectedFile();
                if (listadoProductos.verificadorArchivoTab(archivoCSV_OEM, "OEM")) {
                    this.jl_pathArchivoSeleccionado_OEM.setText(archivoCSV_OEM.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado para las OEM no se ha tabulado correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    this.archivoCSV_OEM = null;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar el archivo CSV", "Error", ERROR);
        }
    }//GEN-LAST:event_jbtn_examinarOEMActionPerformed

    private void jmi_configEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_configEmailActionPerformed
        ventanaConfigCorreo jit = new ventanaConfigCorreo();
        Dimension d = this.jDesktopPane1.getSize();
        Dimension d2 = jit.getSize();
        jit.setLocation((d.width - d2.width) / 2, (d.height - d2.height) / 2);
        this.jDesktopPane1.add(jit);
        jit.show();
    }//GEN-LAST:event_jmi_configEmailActionPerformed

    private void jmi_configuracionConsecutivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_configuracionConsecutivoActionPerformed
        ventanaConfigConsecutivo jit = new ventanaConfigConsecutivo();
        Dimension d = this.jDesktopPane1.getSize();
        Dimension d2 = jit.getSize();
        jit.setLocation((d.width - d2.width) / 2, (d.height - d2.height) / 2);
        this.jDesktopPane1.add(jit);
        jit.show();
    }//GEN-LAST:event_jmi_configuracionConsecutivoActionPerformed

    //</editor-fold>
    //<editor-fold desc="atributos generados" defaultstate="collapsed">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generarReporte;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtn_ejecutarCalculos;
    private javax.swing.JButton jbtn_examinarOC;
    private javax.swing.JButton jbtn_examinarOEM;
    private javax.swing.JLabel jl_pathArchivoSeleccionado_OC;
    private javax.swing.JLabel jl_pathArchivoSeleccionado_OEM;
    private javax.swing.JLabel jl_totalFactura;
    private javax.swing.JMenu jm_archivo;
    private javax.swing.JMenuBar jmb_barraMenu;
    private javax.swing.JMenuItem jmi_cerrarApp;
    private javax.swing.JMenuItem jmi_configEmail;
    private javax.swing.JMenuItem jmi_configuracionConsecutivo;
    private javax.swing.JMenuItem jmi_infoApp;
    private javax.swing.JSeparator js_separador;
    private javax.swing.JTable jtbl_listadoProductos;
    private javax.swing.JTextField jtf_totalFactura;
    // End of variables declaration//GEN-END:variables

    public JButton getBtn_generarReporte() {
        return btn_generarReporte;
    }

    public void setBtn_generarReporte(JButton btn_generarReporte) {
        this.btn_generarReporte = btn_generarReporte;
    }
    //</editor-fold>

}
