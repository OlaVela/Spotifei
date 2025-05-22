package View;

import Controller.ControllerMusica;
import java.awt.Image;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
        this.setarListaM("Todos");
    }
//================================================================
//                        Getes e setes
//================================================================    
    public void setNomeTexto(String NomeTexto) {
        this.NomeTexto.setText(NomeTexto);
    }

    public void setGenero(String selecionado) {
        this.Genero.setText(selecionado);
    }

    public void setDescricao(String selecionado) {
        this.Descricao.setText(selecionado);
    }

    public void setTextNomeArtista(String selecionado) {
        this.TextNomeArtista.setText(selecionado);
    }
    public void setListaM(JList<String> ListaM) {
        this.ListaM = ListaM;
    }
    
    public JList<String> getListaM() {
        return ListaM;
    }

    public boolean getDescurti() {
        return descurti.isSelected();
    }

    public boolean getCurtir() {
        return curtir.isSelected();
    }
    
    private void setarListaM(String selecionado) {
        defaultListModel.clear();
        String estilo = (String) Estilo.getText();
        List<String> a = controllerMusica.pesquisarMusicas(estilo,selecionado);
        for (String i : a) {
            defaultListModel.addElement(i);
        }
        ListaM.setModel(defaultListModel);
    }
    
    private void setarListaMDescurtidas() {
        defaultListModel.clear();
        List<String> a = controllerMusica.getMDescurtidas();
        for (String i : a) {
            defaultListModel.addElement(i);
        }
        ListaM.setModel(defaultListModel);
    }
    
    private void setarListaMCurtidas() {
        defaultListModel.clear();
        List<String> a = controllerMusica.getMCurtidas();
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
                    Foto.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar imagem: " + e.getMessage());
                Foto.setIcon(null);
                Foto.setText("Erro ao carregar");
            }
            setDescricao(controllerMusica.Descricao(ListaM.getSelectedValue()));
            setTextNomeArtista(controllerMusica.Artista(ListaM.getSelectedValue()));
            setGenero(controllerMusica.Genero(ListaM.getSelectedValue()));
            curtir.setSelected(false);
            descurti.setSelected(false);
            int varD = (int) controllerMusica.DeslikeChecar(ListaM.getSelectedValue());
                if(varD == 1){
                    descurti.setSelected(true);
                    curtir.setSelected(false);
                }
            int varC = (int) controllerMusica.likesChecar(ListaM.getSelectedValue());    
                if(varC == 1){
                    descurti.setSelected(false);
                    curtir.setSelected(true);                    
                }
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
                setarListaM("Todos");
            }
            if ("Nome".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM("Nome");
            }
            if ("Artista".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM("Artista");
            }
            if ("Gênero".equals(tipopesquisa)) {
                System.out.println(estilo);
                setarListaM("Gênero");
            }
            if ("Curtidas".equals(tipopesquisa)) {
                System.out.println("Curtidas lista");
                setarListaMCurtidas();
            }
            if ("Descurtidas".equals(tipopesquisa)) {
                System.out.println("Descurtidas lista");
                setarListaMDescurtidas();
            }            
        }
    }
//================================================================
//                      Like e Deslike
//================================================================
    public void likes(){
        String selected = (String) ListaM.getSelectedValue();
        if(getCurtir() == true){
            controllerMusica.LikesDeslikes(selected, 1, 0);
            descurti.setSelected(false);
        }
        else{
            controllerMusica.LikesDeslikes(selected, 0, 0);
        }
    }
    public void Deslike(){
        String selected = (String) ListaM.getSelectedValue();
        if(getDescurti() == true){
            controllerMusica.LikesDeslikes(selected, 0, 1);
            curtir.setSelected(false);
        }
        else{
            controllerMusica.LikesDeslikes(selected, 0, 0);
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
        curtir = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Descricao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Genero = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextNomeArtista = new javax.swing.JLabel();
        descurti = new javax.swing.JCheckBox();
        Estilo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaM1 = new javax.swing.JList<>();
        Estilo1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListaM2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

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
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(854, 456));

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
        TipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome", "Artista", "Gênero", "Curtidas", "Descurtidas" }));
        TipoPesquisa.setToolTipText("");
        TipoPesquisa.setBorder(null);
        TipoPesquisa.setMaximumSize(new java.awt.Dimension(81, 23));
        TipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPesquisaActionPerformed(evt);
            }
        });

        jPanel3.setForeground(new java.awt.Color(60, 63, 65));

        Foto.setMaximumSize(new java.awt.Dimension(100, 100));
        Foto.setMinimumSize(new java.awt.Dimension(100, 100));

        NomeTexto.setBackground(new java.awt.Color(255, 255, 255));
        NomeTexto.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        NomeTexto.setForeground(new java.awt.Color(0, 0, 0));
        NomeTexto.setBorder(null);
        NomeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeTextoActionPerformed(evt);
            }
        });

        curtir.setToolTipText("");
        curtir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/like1.png"))); // NOI18N
        curtir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/like2.png"))); // NOI18N
        curtir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curtirActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Adicionar A PlayList");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Remover da PlayList");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        Descricao.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        Descricao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Descricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Por:");

        Genero.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("Descrição");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setText("Gênero:");

        TextNomeArtista.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N

        descurti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/delike1.png"))); // NOI18N
        descurti.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/delike2.png"))); // NOI18N
        descurti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descurtiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Genero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextNomeArtista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(63, 63, 63))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(descurti)
                                    .addGap(5, 5, 5)
                                    .addComponent(curtir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(NomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TextNomeArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(curtir)
                                .addGap(3, 3, 3))
                            .addComponent(descurti, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(22, 22, 22))
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

        Estilo1.setToolTipText("");
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
        jLabel2.setText("Musicas Playlist");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Criar");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Deletar");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PlayListis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Estilo1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Estilo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void TipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPesquisaActionPerformed
    //  Barra leteral (Todos,Nome,artista,Genero,Curtidas,Descurtidas)
    }//GEN-LAST:event_TipoPesquisaActionPerformed

    private void EstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstiloActionPerformed
      
    }//GEN-LAST:event_EstiloActionPerformed

    private void NomeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeTextoActionPerformed

    }//GEN-LAST:event_NomeTextoActionPerformed
        //Botão Pesquisar
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void curtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curtirActionPerformed
              likes();
    }//GEN-LAST:event_curtirActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void descurtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descurtiActionPerformed
            Deslike();
    }//GEN-LAST:event_descurtiActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Descricao;
    private javax.swing.JTextField Estilo;
    private javax.swing.JTextField Estilo1;
    private javax.swing.JLabel Foto;
    private javax.swing.JLabel Genero;
    private javax.swing.JList<String> ListaM;
    private javax.swing.JList<String> ListaM1;
    private javax.swing.JList<String> ListaM2;
    private javax.swing.JTextField NomeTexto;
    private javax.swing.JLabel TextNomeArtista;
    private javax.swing.JComboBox<String> TipoPesquisa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox curtir;
    private javax.swing.JCheckBox descurti;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
