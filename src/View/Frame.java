package View;

import Controller.ControllerMusica;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 * @author Rafael
 */
public class Frame extends javax.swing.JFrame {

    public ControllerMusica controllerMusica;
    
    public JList<String> getListaM() {
        return ListaM;
    }
    public void setListaM(JList<String> ListaM) {
        this.ListaM = ListaM;
    }

    public Frame() {
        initComponents(); 
        this.controllerMusica = new ControllerMusica();
        this.setarListaM();
    }
    
    DefaultListModel defaultListModel = new DefaultListModel();
    
    private void setarListaM(){
        List<String>a = controllerMusica.MostrarLista();
        for (String i : a){
            defaultListModel.addElement(i);
        }
        ListaM.setModel(defaultListModel);
    }
    
    private void MusicaSelecionada() {
        String selecionado = (String) ListaM.getSelectedValue();
        if (selecionado != null) {
            System.out.println("Item selecionado: " + selecionado);
     }    
    }
    private void TipoDeMusicaSelecionada() {
        String selecionado = (String) TipoPesquisa.getSelectedItem();
        if (selecionado != null) {
            System.out.println("Item selecionado: " + selecionado);
     }    
    }
    private void EstiloSelecionada() {
        String selecionado = (String) Estilo.getText();
        if (selecionado != null) {
            System.out.println("Item selecionado: " + selecionado);
     }    
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        menuBar3 = new java.awt.MenuBar();
        menu5 = new java.awt.Menu();
        menu6 = new java.awt.Menu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaM = new javax.swing.JList<>();
        TipoPesquisa = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        NomeTexto = new javax.swing.JLabel();
        Foto = new javax.swing.JLabel();
        DescricaoTexto = new javax.swing.JLabel();
        Estilo = new javax.swing.JTextField();

        jButton28.setText("jButton3");

        jButton29.setText("jButton3");

        jButton26.setText("jButton3");

        jButton27.setText("jButton3");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        menu5.setLabel("File");
        menuBar3.add(menu5);

        menu6.setLabel("Edit");
        menuBar3.add(menu6);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(757, 495));
        setMinimumSize(new java.awt.Dimension(757, 495));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        ListaM.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaM.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaMValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ListaM);

        TipoPesquisa.setBackground(new java.awt.Color(78, 78, 78));
        TipoPesquisa.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        TipoPesquisa.setForeground(java.awt.Color.white);
        TipoPesquisa.setMaximumRowCount(4);
        TipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome", "Artista", "Gênero" }));
        TipoPesquisa.setToolTipText("");
        TipoPesquisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TipoPesquisa.setMaximumSize(new java.awt.Dimension(81, 23));
        TipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPesquisaActionPerformed(evt);
            }
        });

        NomeTexto.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        NomeTexto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NomeTexto.setText("Hitmitsu da yo");

        Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Sem título.png"))); // NOI18N
        Foto.setMaximumSize(new java.awt.Dimension(100, 100));
        Foto.setMinimumSize(new java.awt.Dimension(100, 100));

        DescricaoTexto.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        DescricaoTexto.setForeground(new java.awt.Color(51, 51, 51));
        DescricaoTexto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DescricaoTexto.setText("Testo aqui");
        DescricaoTexto.setToolTipText("");
        DescricaoTexto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescricaoTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NomeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NomeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Foto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(DescricaoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Estilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstiloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListaMValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaMValueChanged
        MusicaSelecionada();
    }//GEN-LAST:event_ListaMValueChanged

    private void TipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPesquisaActionPerformed
       TipoDeMusicaSelecionada();
    }//GEN-LAST:event_TipoPesquisaActionPerformed

    private void EstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstiloActionPerformed
        EstiloSelecionada();
    }//GEN-LAST:event_EstiloActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Frame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescricaoTexto;
    private javax.swing.JTextField Estilo;
    private javax.swing.JLabel Foto;
    private javax.swing.JList<String> ListaM;
    private javax.swing.JLabel NomeTexto;
    private javax.swing.JComboBox<String> TipoPesquisa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.Menu menu5;
    private java.awt.Menu menu6;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.MenuBar menuBar3;
    // End of variables declaration//GEN-END:variables

}
