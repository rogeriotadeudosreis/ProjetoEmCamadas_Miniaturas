package app;

import bll.FotoBll;
import bll.MiniaturaBll;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import model.Fotos;
import model.Miniaturas;

/**
 *
 * @author roger
 */
public class CadFotos extends javax.swing.JDialog {

    JFileChooser chooser = new JFileChooser();
    File f;

    Fotos foto = new Fotos();
    FotoBll fotoBll = new FotoBll();
    Miniaturas mini = new Miniaturas();
    MiniaturaBll miniBll = new MiniaturaBll();

    /**
     * Creates new form CadFotos
     */
    public CadFotos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButtonExcluir.setEnabled(false);

        try {
            // consultarFotos(fotoBll.getConsultar());

            jTextAreaDescricao.setLineWrap(true);
            miniBll = new MiniaturaBll();
            List<Miniaturas> lista = miniBll.getConsultar();
            miniBll.ordenaListaMiniaturas(lista);
            jComboBoxMiniaturas.removeAllItems();
            jComboBoxMiniaturas.addItem("< Selecione a Miniatura >");
            for (int pos = 0; pos < lista.size(); pos++) {
                Miniaturas aux = lista.get(pos);
                jComboBoxMiniaturas.addItem(String.format("%02d", aux.getId()) + " - " + aux.getModelo_min().toUpperCase());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!" + erro.getMessage());

        }
    }

    private static Date createNewDate(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(data);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formato.format(dtConsulta);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

    private void consultarFotos(List<Fotos> lista) throws Exception {
        jTextFieldQuantRegistros.setText(lista.size() + "");
        DefaultTableModel model = (DefaultTableModel) jTableFotosMiniatura.getModel();
        model.setNumRows(0);

        fotoBll.ordenaListaFotos(lista);

        for (int pos = 0; pos < lista.size(); pos++) {
            String[] linha = new String[3];
            Fotos aux = lista.get(pos);
            linha[0] = String.format("%02d", aux.getId());
            linha[1] = aux.getMiniatura().getModelo_min().toUpperCase();
            linha[2] = aux.getDescricao().toUpperCase();
            model.addRow(linha);
        }
    }

    public void limpaCampos() {
//        DefaultTableModel model = (DefaultTableModel) jTableFotosMiniatura.getModel();
//        model.setRowCount(0);
        
        jTextFieldIdFoto.setText("");
        jComboBoxMiniaturas.setSelectedIndex(0);
        jTextAreaDescricao.setText("");
        jTextFieldQuantRegistros.setText("");
        jTextFieldCaminhoDaFoto.setText("");
        jLabelExibirFoto.setIcon(null);
        
        jButtonExcluir.setEnabled(false);
        
        
    }

    public void preencherCampos(int id) throws Exception {
        try {

            if (id > 0) {
                foto = fotoBll.consultarPorId(id);
                jTextFieldIdFoto.setText(id + "");
                String mini = String.format("%02d", foto.getMiniatura().getId()) + " - "
                        + foto.getMiniatura().getModelo_min().toUpperCase();
                jComboBoxMiniaturas.setSelectedItem(mini);
                jTextAreaDescricao.setText(foto.getDescricao());
                jTextFieldCaminhoDaFoto.setText(foto.getPath());

                ImageIcon imageIcon = new ImageIcon(foto.getPath());
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(380, 290, java.awt.Image.SCALE_SMOOTH);
                // Fim do código para redimensionar a foto
                ImageIcon icon = new ImageIcon(newimg);
                jLabelExibirFoto.setText("");
                jLabelExibirFoto.setIcon(icon);

                jButtonSalvar.setLabel("EDITAR");
            } else {
                jButtonSalvar.setLabel("SALVAR");
            }
            jComboBoxMiniaturas.requestFocus();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, " Atenção !!!\n" + erro.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadFotos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIdFoto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFotosMiniatura = new javax.swing.JTable();
        jComboBoxMiniaturas = new javax.swing.JComboBox<>();
        jButtonSelecioneAfoto = new javax.swing.JButton();
        jTextFieldCaminhoDaFoto = new javax.swing.JTextField();
        jPanelMostrarFoto = new javax.swing.JPanel();
        jLabelExibirFoto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonConsulta = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonManutencaoDeMiniatura = new javax.swing.JButton();
        jButtonListaMiniaturaDetalhada = new javax.swing.JButton();
        jButtonFECHAR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fotos de Miniaturas");
        setResizable(false);

        jLabel1.setText("Id:");

        jTextFieldIdFoto.setEditable(false);

        jLabel2.setText("Caminho:");

        jLabel3.setText("Descrição:");

        jLabel4.setText("Miniatura:");

        jTableFotosMiniatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Miniatura / Modelo", "Descrição da Miniatura"
            }
        ));
        jTableFotosMiniatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableFotosMiniaturaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFotosMiniatura);
        if (jTableFotosMiniatura.getColumnModel().getColumnCount() > 0) {
            jTableFotosMiniatura.getColumnModel().getColumn(0).setMinWidth(50);
            jTableFotosMiniatura.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableFotosMiniatura.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jComboBoxMiniaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione a miniatura >" }));
        jComboBoxMiniaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxMiniaturasMouseClicked(evt);
            }
        });

        jButtonSelecioneAfoto.setText("Selecione uma imagem");
        jButtonSelecioneAfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecioneAfotoActionPerformed(evt);
            }
        });

        jPanelMostrarFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelExibirFoto.setText("                                   Foto selecionada exibida aqui");

        javax.swing.GroupLayout jPanelMostrarFotoLayout = new javax.swing.GroupLayout(jPanelMostrarFoto);
        jPanelMostrarFoto.setLayout(jPanelMostrarFotoLayout);
        jPanelMostrarFotoLayout.setHorizontalGroup(
            jPanelMostrarFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMostrarFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelExibirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMostrarFotoLayout.setVerticalGroup(
            jPanelMostrarFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostrarFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelExibirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescricao);

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonConsulta.setText("CONSULTA");
        jButtonConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaActionPerformed(evt);
            }
        });

        jButtonNovo.setText("NOVO");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabel5.setText("Quant. Registros:");

        jTextFieldQuantRegistros.setEditable(false);

        jButtonCancelar.setText("CANCELAR");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonManutencaoDeMiniatura.setText("Manutenção de Miniatura");
        jButtonManutencaoDeMiniatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManutencaoDeMiniaturaActionPerformed(evt);
            }
        });

        jButtonListaMiniaturaDetalhada.setText("LISTAR");
        jButtonListaMiniaturaDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListaMiniaturaDetalhadaActionPerformed(evt);
            }
        });

        jButtonFECHAR.setText("FECHAR");
        jButtonFECHAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFECHARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadFotosLayout = new javax.swing.GroupLayout(jPanelCadFotos);
        jPanelCadFotos.setLayout(jPanelCadFotosLayout);
        jPanelCadFotosLayout.setHorizontalGroup(
            jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadFotosLayout.createSequentialGroup()
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCaminhoDaFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonNovo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonListaMiniaturaDetalhada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonCancelar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonConsulta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonExcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSalvar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonFECHAR))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxMiniaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldIdFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadFotosLayout.createSequentialGroup()
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(171, 171, 171))
                            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                .addComponent(jButtonManutencaoDeMiniatura)
                                .addGap(108, 108, 108)))
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadFotosLayout.createSequentialGroup()
                                .addComponent(jButtonSelecioneAfoto)
                                .addGap(141, 141, 141))
                            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                                .addComponent(jPanelMostrarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))))))
        );
        jPanelCadFotosLayout.setVerticalGroup(
            jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldIdFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadFotosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonSelecioneAfoto)))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxMiniaturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jButtonManutencaoDeMiniatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelMostrarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadFotosLayout.createSequentialGroup()
                        .addComponent(jTextFieldCaminhoDaFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNovo)
                            .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonSalvar)
                                .addComponent(jButtonExcluir)
                                .addComponent(jButtonConsulta)
                                .addComponent(jButtonCancelar)
                                .addComponent(jButtonListaMiniaturaDetalhada)
                                .addComponent(jButtonFECHAR))
                            .addGroup(jPanelCadFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addComponent(jLabel2))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelCadFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelecioneAfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecioneAfotoActionPerformed
        // TODO add your handling code here:
        try {
            // Criar estrutura para buscar a foto em meu computador 
            chooser.showOpenDialog(null);
            f = chooser.getSelectedFile();
            String caminho = f.getAbsolutePath();
            jTextFieldCaminhoDaFoto.setText(caminho);

            // Código para definir o tamanho da imagem
            ImageIcon imageIcon = new ImageIcon(f.getPath());
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(380, 290, java.awt.Image.SCALE_SMOOTH);
            // Fim do código para redimensionar a foto

            ImageIcon icon = new ImageIcon(newimg);
            jLabelExibirFoto.setText("");
            jLabelExibirFoto.setIcon(icon);

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(null, "Atenção ao botão selecionar fotos\n" + erro.getMessage());
        }

        JFileChooser jfc
                = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(new File("./")));
        jfc.setFileFilter(new FileNameExtensionFilter("Image files", "bmp", "png", "jpg"));
        jfc.setAcceptAllFileFilterUsed(false);
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //("Image files","bmp","png","jpg");
        }


    }//GEN-LAST:event_jButtonSelecioneAfotoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:

        if (jComboBoxMiniaturas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione a miniatura\n");
            jComboBoxMiniaturas.requestFocus();
        } else if (jTextAreaDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Insira uma descrição da miniatura\n");
            jTextAreaDescricao.requestFocus();
        } else if (jTextFieldCaminhoDaFoto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma foto para a miniatura\n");
            jButtonSelecioneAfoto.requestFocus();
        } else {

            try {

                Miniaturas auxMini = new Miniaturas();
                auxMini.setSplitMiniaturas(jComboBoxMiniaturas.getSelectedItem().toString());
                foto.setMiniatura(auxMini);

                foto.setDescricao(jTextAreaDescricao.getText());
                foto.setPath(jTextFieldCaminhoDaFoto.getText());

                if (jButtonSalvar.getLabel().equalsIgnoreCase("SALVAR")) {
                    fotoBll.adicionar(foto);
                } else {
                    fotoBll.alterar(foto);
                }
                consultarFotos(fotoBll.getConsultar());
                limpaCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
        try {

            fotoBll.remover(foto.getId());
            consultarFotos(fotoBll.getConsultar());
            limpaCampos();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!\n" + erro.getMessage());
        }

    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaActionPerformed
        // TODO add your handling code here:
        try {
            consultarFotos(fotoBll.getConsultar());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonConsultaActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        // TODO add your handling code here:
        jButtonSalvar.setLabel("SALVAR");
        limpaCampos();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonManutencaoDeMiniaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManutencaoDeMiniaturaActionPerformed
        // TODO add your handling code here:
        try {
            new CadMiniaturas(null, true).setVisible(true);

            List<Miniaturas> lista = miniBll.getConsultar();
            miniBll.ordenaListaMiniaturas(lista);
            jComboBoxMiniaturas.removeAllItems();
            jComboBoxMiniaturas.addItem("< Selecione a Miniatura >");
            for (int pos = 0; pos < lista.size(); pos++) {
                Miniaturas aux = lista.get(pos);
                jComboBoxMiniaturas.addItem(String.format("%02d", aux.getId()) + " - " + aux.getModelo_min().toUpperCase());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!!! erro ao abrir o"
                    + "cadastro de miniaturas " + erro.getMessage());
        }

    }//GEN-LAST:event_jButtonManutencaoDeMiniaturaActionPerformed

    private void jComboBoxMiniaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxMiniaturasMouseClicked
        // TODO add your handling code here:      
    }//GEN-LAST:event_jComboBoxMiniaturasMouseClicked

    private void jTableFotosMiniaturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFotosMiniaturaMouseReleased
        // TODO add your handling code here:
        try {
            jButtonExcluir.setEnabled(true);
            int linha = jTableFotosMiniatura.getSelectedRow();
            Integer codigo = Integer.parseInt(jTableFotosMiniatura.getValueAt(linha, 0).toString());
            preencherCampos((int) codigo);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_jTableFotosMiniaturaMouseReleased

    private void jButtonListaMiniaturaDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListaMiniaturaDetalhadaActionPerformed
        // TODO add your handling code here:
        try {
            new ListaDeMiniaturasView(null, true).setVisible(true);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!!!" + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListaMiniaturaDetalhadaActionPerformed

    private void jButtonFECHARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFECHARActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFECHARActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadFotos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadFotos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadFotos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadFotos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadFotos dialog = new CadFotos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonFECHAR;
    private javax.swing.JButton jButtonListaMiniaturaDetalhada;
    private javax.swing.JButton jButtonManutencaoDeMiniatura;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelecioneAfoto;
    private javax.swing.JComboBox<String> jComboBoxMiniaturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelExibirFoto;
    private javax.swing.JPanel jPanelCadFotos;
    private javax.swing.JPanel jPanelMostrarFoto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableFotosMiniatura;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldCaminhoDaFoto;
    private javax.swing.JTextField jTextFieldIdFoto;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    // End of variables declaration//GEN-END:variables
}
