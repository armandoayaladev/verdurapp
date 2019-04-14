package presentacion;

import java.awt.Toolkit;

/**
 * Clase que sirve de presentacion al programa
 *
 * @autor: Armando Ayala C
 * @fecha: 07/05/2018
 */
public class presentación extends javax.swing.JFrame implements Runnable {

    //<editor-fold desc="atributos" defaultstate="collapsed">
    //Thread t;
    //</editor-fold>

    //<editor-fold desc="metodos" defaultstate="collapsed">
    /**
     * Constructor por defecto de la clase
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    public presentación() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/multimedia/Armando Ayala Colores.png")));
    }

    /**
     * Metodo sobreescrito de la clase Thread para ejecutarse por un periodo de
     * tiempo
     *
     * @autor: Armando Ayala C
     * @fecha: 07/05/2018
     */
    @Override
    public void run() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //</editor-fold>
    
    //<editor-fold desc="metodos generados" defaultstate="collapsed">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_contenedorGeneral = new javax.swing.JPanel();
        jbl_nombreApp = new javax.swing.JLabel();
        jlb_versionApp = new javax.swing.JLabel();
        jbl_imagenApp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(600, 200));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(600, 200));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jp_contenedorGeneral.setBackground(new java.awt.Color(0, 204, 51));
        jp_contenedorGeneral.setForeground(new java.awt.Color(102, 255, 153));
        jp_contenedorGeneral.setPreferredSize(new java.awt.Dimension(300, 100));

        jbl_nombreApp.setFont(new java.awt.Font("Impact", 2, 48)); // NOI18N
        jbl_nombreApp.setForeground(new java.awt.Color(255, 255, 102));
        jbl_nombreApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbl_nombreApp.setText("VerdurApp");

        jlb_versionApp.setForeground(new java.awt.Color(255, 255, 255));
        jlb_versionApp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlb_versionApp.setText("Ver. 1.0.201805");

        jbl_imagenApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/verdurapp-icon.png"))); // NOI18N

        javax.swing.GroupLayout jp_contenedorGeneralLayout = new javax.swing.GroupLayout(jp_contenedorGeneral);
        jp_contenedorGeneral.setLayout(jp_contenedorGeneralLayout);
        jp_contenedorGeneralLayout.setHorizontalGroup(
            jp_contenedorGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_contenedorGeneralLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jbl_imagenApp, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jp_contenedorGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_contenedorGeneralLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jlb_versionApp, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_contenedorGeneralLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbl_nombreApp, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jp_contenedorGeneralLayout.setVerticalGroup(
            jp_contenedorGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_contenedorGeneralLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(jp_contenedorGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_contenedorGeneralLayout.createSequentialGroup()
                        .addComponent(jbl_nombreApp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlb_versionApp))
                    .addComponent(jbl_imagenApp, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_contenedorGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_contenedorGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>

    //<editor-fold desc="atributos generados" defaultstate="collapsed">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jbl_imagenApp;
    private javax.swing.JLabel jbl_nombreApp;
    private javax.swing.JLabel jlb_versionApp;
    private javax.swing.JPanel jp_contenedorGeneral;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

}
