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
    DefaultListModel Listaplaylist = new DefaultListModel();
    DefaultListModel ListaMusicas = new DefaultListModel();
    public String estilo;
    String[] vazio = {""};

    public Frame() {
        initComponents();
        this.controllerMusica = new ControllerMusica();
        this.Listaplaylist = new DefaultListModel();
        this.ListaMusicas = new DefaultListModel();
        this.setarListaM("Todos");
        this.setListaPlaylists();
        this.setListaPlaylistsMusicas();
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

    public void listarHistoricoMusicas() {
        defaultListModel.clear();
        List<String> a = controllerMusica.listarHistoricoMusicas();
        for (String i : a) {
            defaultListModel.addElement(i);
        }
        ListaM.setModel(defaultListModel);
    }

    private void setarListaM(String selecionado) {
        defaultListModel.clear();
        String estilo = (String) Estilo.getText();
        List<String> a = controllerMusica.pesquisarMusicas(estilo, selecionado);
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

    private void setListaPlaylists() {
        Listaplaylist.clear();
        List<String> a = controllerMusica.getListaPlaylists();
        for (String i : a) {
            Listaplaylist.addElement(i);
        }
        ListaPlaylist.setModel(Listaplaylist);
    }

    private void setListaPlaylistsMusicas() {
        ListaMusicas.clear();
        String nomePlaylist = (String) ListaPlaylist.getSelectedValue();
        List<String> a = controllerMusica.getListaMusicasPlaylist(nomePlaylist);
        for (String i : a) {
            ListaMusicas.addElement(i);
        }
        MusicasPlaylist.setModel(ListaMusicas);
    }

//================================================================
//              Coloca Nome Imagem e descrição
//================================================================
    private void MusicaSelecionada(JList<String> lista) {
        String selecionado = (String) lista.getSelectedValue();
        controllerMusica.adicionarMusicaAoHistorico(selecionado);
        if (selecionado != null) {

            setNomeTexto(selecionado);

            Foto.setIcon(null);

            String caminhoImagem = "/View/" + selecionado.toLowerCase().replace(" ", "_") + ".png";

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
            setDescricao(controllerMusica.Descricao(lista.getSelectedValue()));
            setTextNomeArtista(controllerMusica.Artista(lista.getSelectedValue()));
            setGenero(controllerMusica.Genero(lista.getSelectedValue()));
            curtir.setSelected(false);
            descurti.setSelected(false);
            int varD = (int) controllerMusica.DeslikeChecar(lista.getSelectedValue());
            if (varD == 1) {
                descurti.setSelected(true);
                curtir.setSelected(false);
            }
            int varC = (int) controllerMusica.likesChecar(lista.getSelectedValue());
            if (varC == 1) {
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
            if ("Todos".equals(tipopesquisa)) {
                setarListaM("Todos");
            }
            if ("Nome".equals(tipopesquisa)) {
                setarListaM("Nome");
            }
            if ("Artista".equals(tipopesquisa)) {
                setarListaM("Artista");
            }
            if ("Gênero".equals(tipopesquisa)) {
                setarListaM("Gênero");
            }
            if ("Curtidas".equals(tipopesquisa)) {
                setarListaMCurtidas();
            }
            if ("Descurtidas".equals(tipopesquisa)) {
                setarListaMDescurtidas();
            }
            if ("Historico".equals(tipopesquisa)) {
                listarHistoricoMusicas();
            }
        }
    }

//================================================================
//                      Like e Deslike
//================================================================
    public void likes() {
        String selected = (String) ListaM.getSelectedValue();
        if (getCurtir() == true) {
            controllerMusica.LikesDeslikes(selected, 1, 0);
            descurti.setSelected(false);
        } else {
            controllerMusica.LikesDeslikes(selected, 0, 0);
        }
    }

    public void Deslike() {
        String selected = (String) ListaM.getSelectedValue();
        if (getDescurti() == true) {
            controllerMusica.LikesDeslikes(selected, 0, 1);
            curtir.setSelected(false);
        } else {
            controllerMusica.LikesDeslikes(selected, 0, 0);
        }
    }
//================================================================
//                         Playlists
//================================================================    

    public void criarPlaylist() {
        String nomePlaylist = (String) playlistnome.getText();
        controllerMusica.criarPlaylist(nomePlaylist);
        playlistnome.setText("");
    }

    public void adicionarMusicaPlaylist() {
        String nomePlaylist = (String) ListaPlaylist.getSelectedValue();
        String nomemusica = (String) ListaM.getSelectedValue();
        controllerMusica.adicionarMusicaPlaylist(nomePlaylist, nomemusica);
    }

    public void removerMusicaPlaylist() {
        String nomePlaylist = (String) ListaPlaylist.getSelectedValue();
        String nomemusica = (String) MusicasPlaylist.getSelectedValue();
        controllerMusica.removerMusicaPlaylist(nomePlaylist, nomemusica);
    }

    public void renomearPlaylist() {
        String novoNome = (String) playlistnome.getText();
        String nomeAtual = (String) ListaPlaylist.getSelectedValue();
        controllerMusica.renomearPlaylist(nomeAtual, novoNome);
    }

    public void deletarPlaylist() {
        String nomePlaylist = (String) ListaPlaylist.getSelectedValue();
        controllerMusica.deletarPlaylist(nomePlaylist);
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
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaM = new javax.swing.JList<>();
        TipoPesquisa = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        Foto = new javax.swing.JLabel();
        NomeTexto = new javax.swing.JTextField();
        curtir = new javax.swing.JCheckBox();
        AdicionarAPlayList = new javax.swing.JButton();
        RemoverDaPlayList = new javax.swing.JButton();
        Descricao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Genero = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextNomeArtista = new javax.swing.JLabel();
        descurti = new javax.swing.JCheckBox();
        Estilo = new javax.swing.JTextField();
        Ok = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaPlaylist = new javax.swing.JList<>();
        playlistnome = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        MusicasPlaylist = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        criar = new javax.swing.JButton();
        deleta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Renomear = new javax.swing.JButton();

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(930, 500));
        setMinimumSize(new java.awt.Dimension(930, 500));
        setPreferredSize(new java.awt.Dimension(930, 500));
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
        TipoPesquisa.setMaximumRowCount(6);
        TipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome", "Artista", "Gênero", "Curtidas", "Descurtidas", "Historico" }));
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

        AdicionarAPlayList.setBackground(new java.awt.Color(255, 255, 255));
        AdicionarAPlayList.setForeground(new java.awt.Color(0, 0, 0));
        AdicionarAPlayList.setText("Adicionar A PlayList");
        AdicionarAPlayList.setBorder(null);
        AdicionarAPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarAPlayListActionPerformed(evt);
            }
        });

        RemoverDaPlayList.setBackground(new java.awt.Color(255, 255, 255));
        RemoverDaPlayList.setForeground(new java.awt.Color(0, 0, 0));
        RemoverDaPlayList.setText("Remover da PlayList");
        RemoverDaPlayList.setBorder(null);
        RemoverDaPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoverDaPlayListActionPerformed(evt);
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
                                        .addComponent(AdicionarAPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(RemoverDaPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(Descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(AdicionarAPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoverDaPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        Ok.setBackground(new java.awt.Color(255, 255, 255));
        Ok.setForeground(new java.awt.Color(0, 0, 0));
        Ok.setText("Ok");
        Ok.setBorder(null);
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });

        ListaPlaylist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaPlaylist.setPreferredSize(new java.awt.Dimension(0, 0));
        ListaPlaylist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaPlaylistValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(ListaPlaylist);

        playlistnome.setToolTipText("");
        playlistnome.setPreferredSize(new java.awt.Dimension(207, 22));
        playlistnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playlistnomeActionPerformed(evt);
            }
        });

        MusicasPlaylist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        MusicasPlaylist.setPreferredSize(new java.awt.Dimension(0, 0));
        MusicasPlaylist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                MusicasPlaylistValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(MusicasPlaylist);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Musicas");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Musicas Playlist");

        criar.setBackground(new java.awt.Color(255, 255, 255));
        criar.setForeground(new java.awt.Color(0, 0, 0));
        criar.setText("Criar");
        criar.setBorder(null);
        criar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarActionPerformed(evt);
            }
        });

        deleta.setBackground(new java.awt.Color(255, 255, 255));
        deleta.setForeground(new java.awt.Color(0, 0, 0));
        deleta.setText("Deletar");
        deleta.setBorder(null);
        deleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PlayListis");

        Renomear.setBackground(new java.awt.Color(255, 255, 255));
        Renomear.setForeground(new java.awt.Color(0, 0, 0));
        Renomear.setText("Renomear");
        Renomear.setBorder(null);
        Renomear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RenomearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Renomear, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playlistnome, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(criar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(criar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playlistnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Renomear, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Estilo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListaMValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaMValueChanged
        if (!evt.getValueIsAdjusting()) {
            MusicaSelecionada(ListaM);
        }
    }//GEN-LAST:event_ListaMValueChanged

    private void TipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPesquisaActionPerformed
        //  Barra leteral (Todos,Nome,artista,Genero,Curtidas,Descurtidas)
    }//GEN-LAST:event_TipoPesquisaActionPerformed

    private void EstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstiloActionPerformed

    }//GEN-LAST:event_EstiloActionPerformed

    private void NomeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeTextoActionPerformed

    }//GEN-LAST:event_NomeTextoActionPerformed

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        TipoDeMusicaSelecionada();
    }//GEN-LAST:event_OkActionPerformed

    private void ListaPlaylistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaPlaylistValueChanged
        if (!evt.getValueIsAdjusting()) {
            setListaPlaylistsMusicas();
        }
    }//GEN-LAST:event_ListaPlaylistValueChanged

    private void playlistnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playlistnomeActionPerformed
        setListaPlaylistsMusicas();
    }//GEN-LAST:event_playlistnomeActionPerformed

    private void MusicasPlaylistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_MusicasPlaylistValueChanged
        if (!evt.getValueIsAdjusting()) {
            MusicaSelecionada(MusicasPlaylist);
        }
    }//GEN-LAST:event_MusicasPlaylistValueChanged

    private void AdicionarAPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarAPlayListActionPerformed
        adicionarMusicaPlaylist();
        setListaPlaylistsMusicas();
    }//GEN-LAST:event_AdicionarAPlayListActionPerformed

    private void criarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarActionPerformed
        criarPlaylist();
        setListaPlaylists();
    }//GEN-LAST:event_criarActionPerformed

    private void curtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curtirActionPerformed
        likes();
    }//GEN-LAST:event_curtirActionPerformed

    private void deletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaActionPerformed
        deletarPlaylist();
        setListaPlaylists();
    }//GEN-LAST:event_deletaActionPerformed

    private void RemoverDaPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoverDaPlayListActionPerformed
        removerMusicaPlaylist();
        setListaPlaylistsMusicas();
    }//GEN-LAST:event_RemoverDaPlayListActionPerformed

    private void descurtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descurtiActionPerformed
        Deslike();
    }//GEN-LAST:event_descurtiActionPerformed

    private void RenomearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenomearActionPerformed
        renomearPlaylist();
        setListaPlaylists();
    }//GEN-LAST:event_RenomearActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdicionarAPlayList;
    private javax.swing.JLabel Descricao;
    private javax.swing.JTextField Estilo;
    private javax.swing.JLabel Foto;
    private javax.swing.JLabel Genero;
    private javax.swing.JList<String> ListaM;
    private javax.swing.JList<String> ListaPlaylist;
    private javax.swing.JList<String> MusicasPlaylist;
    private javax.swing.JTextField NomeTexto;
    private javax.swing.JButton Ok;
    private javax.swing.JButton RemoverDaPlayList;
    private javax.swing.JButton Renomear;
    private javax.swing.JLabel TextNomeArtista;
    private javax.swing.JComboBox<String> TipoPesquisa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton criar;
    private javax.swing.JCheckBox curtir;
    private javax.swing.JButton deleta;
    private javax.swing.JCheckBox descurti;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.Menu menu5;
    private java.awt.Menu menu6;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.MenuBar menuBar3;
    private javax.swing.JTextField playlistnome;
    // End of variables declaration//GEN-END:variables
}
