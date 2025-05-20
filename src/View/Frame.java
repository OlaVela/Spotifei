package View;

import Controller.ControllerMusica;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * @author Rafael
 */
public class Frame extends javax.swing.JFrame {

    public ControllerMusica controllerMusica;
    DefaultListModel defaultListModel = new DefaultListModel();
    public String estilo;
    String[] vazio ={""};
    
    public Frame() {
        initComponents();
        this.controllerMusica = new ControllerMusica();
        this.setarListaM();
    }
//================================================================
//                        Getes e setes
//================================================================    
    public void setNomeTexto(String NomeTexto) {
        this.NomeTexto.setText(NomeTexto);
    }

    public void setDescricao(String selecionado) {
        this.Descricao.setText(selecionado);
    }
    
    public JList<String> getListaM() {
        return ListaM;
    }

    public void setListaM(JList<String> ListaM) {
        this.ListaM = ListaM;
    }
    
    private void setarListaM() {
        defaultListModel.clear();
        String estilo = (String) Estilo.getText();
        List<String> a = controllerMusica.pesquisarMusicas(estilo);
        for (String i : a) {
            defaultListModel.addElement(i);
        }
        ListaM.setModel(defaultListModel);
    }

//================================================================
//              Coloca Nome Imagem e descrição
//================================================================
    private void MusicaSelecionada() {
        String selecionado = (String) ListaM.getSelectedValue();
        if (selecionado != null) {
            //================================================================
            //                  Muda pro nome da musica
            //================================================================
            System.out.println("Música selecionada: " + selecionado);
            setNomeTexto(selecionado);
            //================================================================
            //                         Linpa icone
            //================================================================
            Foto.setIcon(null);
            //================================================================
            //               Define o caminho correto da imagem
            //================================================================
            String caminhoImagem = "/View/" + selecionado.toLowerCase().replace(" ", "_") + ".png";
            //================================================================
            //                     Carrega a imagem
            //================================================================
            try {
                // Usa getResource() para carregar do classpath
                java.net.URL imgURL = getClass().getResource(caminhoImagem);
                if (imgURL != null) {
                    ImageIcon icone = new ImageIcon(imgURL);
                    //Redimensiona 
                    Image img = icone.getImage().getScaledInstance(Foto.getWidth(), Foto.getHeight(), Image.SCALE_SMOOTH);
                    Foto.setIcon(new ImageIcon(img));
                } else {
                    System.err.println("Imagem não encontrada: " + caminhoImagem);
                    // Define um ícone padrão ou placeholder
                    Foto.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar imagem: " + e.getMessage());
                Foto.setIcon(null);
                Foto.setText("Erro ao carregar");
            }
            setDescricao(controllerMusica.Descricao(ListaM.getSelectedValue()));
            
        }
        
    }
    
//================================================================
//              Barra leteral (Todos,Nome,artista,Genero)
//================================================================
    private void TipoDeMusicaSelecionada() {
        String estilo = (String) Estilo.getText();
        String tipopesquisa = (String) TipoPesquisa.getSelectedItem();
        if (tipopesquisa != null) {
            System.out.println("Item selecionado: " + tipopesquisa);
            if ("Todos".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM();
            }
            if ("Nome".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM();
            }
            if ("Artista".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM();
            }
            if ("Gênero".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM();
            }
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
        Foto = new javax.swing.JLabel();
        NomeTexto = new javax.swing.JTextField();
        Descricao = new javax.swing.JTextField();
        Estilo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaM1 = new javax.swing.JList<>();
        Estilo1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListaM2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Estilo2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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
        setMaximumSize(new java.awt.Dimension(854, 456));
        setMinimumSize(new java.awt.Dimension(854, 456));
        setPreferredSize(new java.awt.Dimension(870, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(854, 456));

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
        TipoPesquisa.setBorder(null);
        TipoPesquisa.setMaximumSize(new java.awt.Dimension(81, 23));
        TipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPesquisaActionPerformed(evt);
            }
        });

        jPanel3.setForeground(new java.awt.Color(60, 63, 65));

        Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/bohemian_rhapsody.png"))); // NOI18N
        Foto.setMaximumSize(new java.awt.Dimension(100, 100));
        Foto.setMinimumSize(new java.awt.Dimension(100, 100));

        NomeTexto.setBackground(new java.awt.Color(255, 255, 255));
        NomeTexto.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        NomeTexto.setForeground(new java.awt.Color(0, 0, 0));
        NomeTexto.setText("Bohemian Rhapsody");
        NomeTexto.setBorder(null);
        NomeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeTextoActionPerformed(evt);
            }
        });

        Descricao.setBackground(new java.awt.Color(255, 255, 255));
        Descricao.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        Descricao.setForeground(new java.awt.Color(0, 0, 0));
        Descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Descricao.setText("aiia");
        Descricao.setBorder(null);
        Descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Descricao)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NomeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Foto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(NomeTexto))
                .addGap(18, 18, 18)
                .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        Estilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstiloActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Ok");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ListaM1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaM1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaM1ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(ListaM1);

        Estilo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estilo1ActionPerformed(evt);
            }
        });

        ListaM2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaM2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaM2ValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(ListaM2);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Musicas");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PlayListis");

        Estilo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estilo2ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Add PlayList");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Criar PlayList");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TipoPesquisa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Estilo1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Estilo2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estilo1)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estilo2)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estilo)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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
//  Barra leteral (Todos,Nome,artista,Genero)
    private void TipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPesquisaActionPerformed
        
    }//GEN-LAST:event_TipoPesquisaActionPerformed

    private void EstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstiloActionPerformed
        EstiloSelecionada();
    }//GEN-LAST:event_EstiloActionPerformed

    private void NomeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeTextoActionPerformed

    private void DescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescricaoActionPerformed
//      Botão Pesquisar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TipoDeMusicaSelecionada();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ListaM1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaM1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ListaM1ValueChanged

    private void Estilo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estilo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Estilo1ActionPerformed

    private void ListaM2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaM2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ListaM2ValueChanged

    private void Estilo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estilo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Estilo2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descricao;
    private javax.swing.JTextField Estilo;
    private javax.swing.JTextField Estilo1;
    private javax.swing.JTextField Estilo2;
    private javax.swing.JLabel Foto;
    private javax.swing.JList<String> ListaM;
    private javax.swing.JList<String> ListaM1;
    private javax.swing.JList<String> ListaM2;
    private javax.swing.JTextField NomeTexto;
    private javax.swing.JComboBox<String> TipoPesquisa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
