/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;


import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import reporteria.GeneradorReportes;
import src.Configuraciones;
import src.ListadoProductos;
import src.NotificacionesEmail;

/**
 *
 * @author Armando
 */
public class ventanaInfoFactura extends javax.swing.JInternalFrame {

    private ListadoProductos listadoProductos;
    private GeneradorReportes gr;

    /**
     * Creates new form ventanaInfoFactura
     */
    public ventanaInfoFactura() {
        initComponents();
        cargarConsecutivoFactura();
        this.txt_nombreCliente.setText("");
        this.txt_facturas.setText("");
        gr = new GeneradorReportes();
        this.listadoProductos = new ListadoProductos();
    }

    public void cargarConsecutivoFactura() {
        Configuraciones conf = new Configuraciones();
        this.lblFactura.setText(conf.obtenerConsecutivo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nombreCliente = new javax.swing.JTextField();
        txt_facturas = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblFactura = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        radiobtn_contado = new javax.swing.JRadioButton();
        radiobtn_credito = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Información de la factura");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Factura #:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Factura(s) involucrada(s):");

        jButton1.setText("Generar Factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblFactura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFactura.setForeground(new java.awt.Color(255, 0, 0));
        lblFactura.setText("lblFactura");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Tipo de factura:");

        radiobtn_contado.setText("Contado");
        radiobtn_contado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtn_contadoActionPerformed(evt);
            }
        });

        radiobtn_credito.setText("Crédito");
        radiobtn_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtn_creditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblFactura)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_fecha))))
                            .addComponent(txt_nombreCliente)
                            .addComponent(txt_facturas)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton1)
                        .addGap(53, 53, 53)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addComponent(radiobtn_contado)
                .addGap(31, 31, 31)
                .addComponent(radiobtn_credito)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFactura)
                    .addComponent(jLabel5)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_facturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(radiobtn_contado)
                    .addComponent(radiobtn_credito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (!this.txt_facturas.getText().isEmpty() && !this.txt_nombreCliente.getText().isEmpty() && !this.txt_fecha.getText().isEmpty() && (this.radiobtn_contado.isSelected() || this.radiobtn_credito.isSelected())) {                
                Map parametros = new HashMap<String, Object>();
                parametros.put("pFacturasInvolucradas", this.txt_facturas.getText());
                parametros.put("pNombreCliente", this.txt_nombreCliente.getText());
                parametros.put("pMontoTotal", this.listadoProductos.obtenerPrecioFormatoMoneda(this.listadoProductos.resultadoRedondeadoDosDecimales(this.listadoProductos.getCantidadTotalListado(), new Double("1"))));                
                parametros.put("pNumFactura", this.lblFactura.getText());
                parametros.put("pFecha", this.txt_fecha.getText());
                if(this.radiobtn_contado.isSelected()){
                    parametros.put("pTipoFactura", "CONTADO");
                }else{
                    parametros.put("pTipoFactura", "CRÉDITO");
                }

                NotificacionesEmail notify = new NotificacionesEmail();
                try {
                    if (notify.verificarConexioInternet()) {
                        notify.SendMail(this.lblFactura.getText());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se enviara la notificacion al correo porque no hay conexion a Internet");
                } finally {
                    JRCsvDataSource ds;   
                    ds = new JRCsvDataSource(JRLoader.getLocationInputStream(System.getProperty("user.home") + "/Documents/config.listado.csv"),"UTF-8");
                    String[] columnas  = {"codigo","detalle","sol","rec","unidad","precio","sub"};
                    ds.setColumnNames(columnas);
                    ds.setUseFirstRowAsHeader(true);
                    ds.setRecordDelimiter("%");
                    ds.setFieldDelimiter(';');
                    gr.generarReporte("rpt_facturacionGeneral", parametros, ds);
                    Configuraciones conf = new Configuraciones();
                    conf.configurarConsecutivo(Integer.parseInt(this.lblFactura.getText()));
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para generar el reporte se necesita ingresar las órdenes involucradas\n el número de factura, fecha y tipo de factura", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void radiobtn_contadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtn_contadoActionPerformed
        if(this.radiobtn_credito.isSelected()){
            this.radiobtn_credito.setSelected(false);
            this.radiobtn_contado.setSelected(true);
        }
    }//GEN-LAST:event_radiobtn_contadoActionPerformed

    private void radiobtn_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtn_creditoActionPerformed
        if(this.radiobtn_contado.isSelected()){
            this.radiobtn_contado.setSelected(false);
            this.radiobtn_credito.setSelected(true);
        }
    }//GEN-LAST:event_radiobtn_creditoActionPerformed

    public ListadoProductos getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(ListadoProductos listadoProductos) {
        this.listadoProductos = listadoProductos;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JRadioButton radiobtn_contado;
    private javax.swing.JRadioButton radiobtn_credito;
    private javax.swing.JTextField txt_facturas;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_nombreCliente;
    // End of variables declaration//GEN-END:variables
}
