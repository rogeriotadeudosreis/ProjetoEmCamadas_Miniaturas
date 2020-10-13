package app;

import bll.FabricanteBll;
import bll.MiniaturaBll;
import bll.TemaBll;
import bll.TipoMiniaturaBll;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fabricantes;
import model.Miniaturas;
import model.Temas;
import model.TipoMiniaturas;

/**
 *
 * @author roger
 */
public class CadMiniaturas extends javax.swing.JDialog {

    MiniaturaBll miniBll = new MiniaturaBll();
    Miniaturas mini = new Miniaturas();
    FabricanteBll fabBll = new FabricanteBll();
    TipoMiniaturaBll tipoBll = new TipoMiniaturaBll();
    TemaBll temaBll = new TemaBll();

    /**
     * Creates new form CadMiniaturas
     */
    public CadMiniaturas(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        //consultarMiniaturas(miniBll.getConsultar());

        List<Fabricantes> listaFabricantes = new ArrayList<Fabricantes>();
        listaFabricantes = fabBll.getConsulta();
        fabBll.ordenaListaFabricantes(listaFabricantes);
        jComboBoxFabricantes.removeAllItems();
        jComboBoxFabricantes.addItem("< Selecione aqui o fabricante >");
        for (int i = 0; i < listaFabricantes.size(); i++) {
            Fabricantes aux = listaFabricantes.get(i);
            jComboBoxFabricantes.addItem(aux.getId() + " - " + aux.getNome().toUpperCase());
        }

        List<TipoMiniaturas> listaTipoDeMiniaturas = new ArrayList<>();
        listaTipoDeMiniaturas = tipoBll.getConsulta();
        tipoBll.ordenaListaDeTipoDeMiniaturas(listaTipoDeMiniaturas);
        jComboBoxTiposDeMiniatura.removeAllItems();
        jComboBoxTiposDeMiniatura.addItem("< Selecione aqui o tipo de miniatura >");
        for (int pos = 0; pos < listaTipoDeMiniaturas.size(); pos++) {
            TipoMiniaturas aux = listaTipoDeMiniaturas.get(pos);
            jComboBoxTiposDeMiniatura.addItem(aux.getId() + " - " + aux.getTipo().toUpperCase());
        }

        List<Temas> listaTemas = new ArrayList<>();
        listaTemas = temaBll.getConsulta();
        temaBll.ordenaListaDeTemas(listaTemas);
        jComboBoxTemas.removeAllItems();
        jComboBoxTemas.addItem("< Selecione aqui o tema da miniatura >");
        for (int pos = 0; pos < listaTemas.size(); pos++) {
            Temas aux = listaTemas.get(pos);
            jComboBoxTemas.addItem(aux.getId() + " - " + aux.getNome().toUpperCase());
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

    private void consultarMiniaturas(List<Miniaturas> lista) throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTableMiniaturas.getModel();
        model.setNumRows(0);

        miniBll.ordenaListaMiniaturas(lista);

        for (int pos = 0; pos < lista.size(); pos++) {
            String[] linha = new String[10];
            Miniaturas aux = lista.get(pos);
            linha[0] = String.format("%02d", aux.getId());
            linha[1] = aux.getModelo_min().toUpperCase();
            linha[2] = aux.getAno_min() + "";
            linha[3] = aux.getObservacoes_min().toUpperCase();
            linha[4] = aux.getEdicao_min().toUpperCase();
            linha[5] = aux.getEscala_min().toUpperCase();
            linha[6] = String.format("%.2f", aux.getValor_min());
            linha[7] = String.format("%02d", aux.getFabricante().getId()) + " - " + aux.getFabricante().getNome().toUpperCase();
            linha[8] = String.format("%02d", aux.getTipoMin().getId()) + " - " + aux.getTipoMin().getTipo().toUpperCase();
            linha[9] = String.format("%02d", aux.getTema().getId()) + " - " + aux.getTema().getNome().toUpperCase();
            model.addRow(linha);
        }
        int registros = lista.size();
        jTextFieldQuantRegistros.setText(String.format("%02d", registros));
    }

    private void limpaCampos() {
        jTextFieldAno.setText("");
        jTextFieldCódigo.setText("");
        jTextFieldEdicao.setText("");
        jTextFieldEscala.setText("");
        jTextFieldModelo.setText("");
        jTextFieldObservacoes.setText("");
        jTextFieldValor.setText("");
        jComboBoxFabricantes.setSelectedIndex(0);
        jComboBoxTiposDeMiniatura.setSelectedIndex(0);
        jComboBoxTemas.setSelectedIndex(0);
    }

    private void preencheCampos(int id) {
        try {
            if (id > 0) {
                mini = miniBll.getConsultarPorId(id);
                jTextFieldCódigo.setText(id + "");
                jTextFieldModelo.setText(mini.getModelo_min());
                jTextFieldAno.setText(mini.getAno_min() + "");
                jTextFieldObservacoes.setText(mini.getObservacoes_min());
                jTextFieldEdicao.setText(mini.getEdicao_min());
                jTextFieldEscala.setText(mini.getEscala_min());
                jTextFieldValor.setText(String.format("%.2f", mini.getValor_min()));

                String fabricante = mini.getFabricante().getId() + " - " + mini.getFabricante().getNome().toUpperCase();
                jComboBoxFabricantes.setSelectedItem(fabricante);
                String tipoMin = mini.getTipoMin().getId() + " - " + mini.getTipoMin().getTipo().toUpperCase();
                jComboBoxTiposDeMiniatura.setSelectedItem(tipoMin);
                String tema = mini.getTema().getId() + " - " + mini.getTema().getNome().toUpperCase();
                jComboBoxTemas.setSelectedItem(tema);

                jButtonSalvar.setLabel("EDITAR");
                jTextFieldModelo.requestFocus();

            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!! " + erro.getMessage());
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

        jPanelMiniaturas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCódigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldObservacoes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEdicao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldEscala = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxFabricantes = new javax.swing.JComboBox<>();
        jComboBoxTiposDeMiniatura = new javax.swing.JComboBox<>();
        jComboBoxTemas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMiniaturas = new javax.swing.JTable();
        jButtonSalvar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();
        jButtonManutencaoFabricante = new javax.swing.JButton();
        jButtonManutencaoTipoMiniatura = new javax.swing.JButton();
        jButtonManutencaoTemas = new javax.swing.JButton();
        jTextFieldPesquisarMiniatura = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Miniaturas");
        setResizable(false);

        jPanelMiniaturas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel1.setText("Id:");

        jTextFieldCódigo.setEditable(false);
        jTextFieldCódigo.setEnabled(false);

        jLabel2.setText("Modelo:");

        jTextFieldModelo.setText("Modelo 01");

        jLabel3.setText("Ano:");

        jTextFieldAno.setText("2020");

        jLabel4.setText("Observações:");

        jTextFieldObservacoes.setText("Miniatura padrão");

        jLabel5.setText("Edição:");

        jTextFieldEdicao.setText("Especial");

        jLabel6.setText("Escala:");

        jTextFieldEscala.setText("Simples");

        jLabel7.setText("Valor R$: ");

        jLabel8.setText("Fabricante:");

        jLabel9.setText("Tipo de Miniatura:");

        jLabel10.setText("Tema:");

        jComboBoxFabricantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione o fabricante >" }));

        jComboBoxTiposDeMiniatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione o tipo de miniatura >" }));

        jComboBoxTemas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione o tema da miniatura >" }));

        jTableMiniaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Modelo", "Ano", "Observações", "Edição", "Escala", "Valor", "Fabricante", "Tipo de Min.", "Tema da Min."
            }
        ));
        jTableMiniaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMiniaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMiniaturas);
        if (jTableMiniaturas.getColumnModel().getColumnCount() > 0) {
            jTableMiniaturas.getColumnModel().getColumn(0).setMinWidth(60);
            jTableMiniaturas.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTableMiniaturas.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        Excluir.setText("EXCLUIR");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("CONSULTAR");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jButtonNovo.setText("NOVO");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabel11.setText("Quant. Registros:");

        jTextFieldQuantRegistros.setEditable(false);

        jButtonManutencaoFabricante.setText("Manutenção de Fabricante");
        jButtonManutencaoFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManutencaoFabricanteActionPerformed(evt);
            }
        });

        jButtonManutencaoTipoMiniatura.setText("Manutenção de Tipo de Miniatura");
        jButtonManutencaoTipoMiniatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManutencaoTipoMiniaturaActionPerformed(evt);
            }
        });

        jButtonManutencaoTemas.setText("Manutenção de Temas");
        jButtonManutencaoTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManutencaoTemasActionPerformed(evt);
            }
        });

        jTextFieldPesquisarMiniatura.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldPesquisarMiniatura.setText("< Digite aqui um dado da miniatura >");

        javax.swing.GroupLayout jPanelMiniaturasLayout = new javax.swing.GroupLayout(jPanelMiniaturas);
        jPanelMiniaturas.setLayout(jPanelMiniaturasLayout);
        jPanelMiniaturasLayout.setHorizontalGroup(
            jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                        .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldPesquisarMiniatura)
                            .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxFabricantes, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMiniaturasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCódigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMiniaturasLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEdicao))
                            .addComponent(jButtonManutencaoFabricante))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObservacoes))
                            .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonManutencaoTipoMiniatura)
                                    .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(9, 9, 9)
                                            .addComponent(jComboBoxTiposDeMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMiniaturasLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(60, 60, 60))
                                        .addComponent(jButtonConsultar)))
                                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldValor))
                                    .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                                        .addGap(0, 46, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMiniaturasLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonManutencaoTemas))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMiniaturasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Excluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvar)))))
                .addContainerGap())
        );
        jPanelMiniaturasLayout.setVerticalGroup(
            jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMiniaturasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCódigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxTemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFabricantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxTiposDeMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonManutencaoFabricante)
                    .addComponent(jButtonManutencaoTipoMiniatura)
                    .addComponent(jButtonManutencaoTemas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonConsultar)
                    .addComponent(Excluir)
                    .addComponent(jButtonSalvar)
                    .addComponent(jTextFieldPesquisarMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanelMiniaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMiniaturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMiniaturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (CamposIsEmpety()) {
            JOptionPane.showMessageDialog(null, "Existem campos não preenchidos, verifique !");

        } else {

            try {

                mini.setModelo_min(jTextFieldModelo.getText());
                mini.setAno_min(Integer.parseInt(jTextFieldAno.getText()));
                mini.setObservacoes_min(jTextFieldObservacoes.getText());
                mini.setEdicao_min(jTextFieldEdicao.getText());
                mini.setEscala_min(jTextFieldEscala.getText());
                mini.setValor_min(Double.parseDouble(jTextFieldValor.getText()));

                Fabricantes auxFab = new Fabricantes();
                auxFab.setSplitFabricante(jComboBoxFabricantes.getSelectedItem().toString());
                mini.setFabricante(auxFab);

                TipoMiniaturas auxTipo = new TipoMiniaturas();
                auxTipo.setSplitTipoMiniatura(jComboBoxTiposDeMiniatura.getSelectedItem().toString());
                mini.setTipoMin(auxTipo);

                Temas auxTema = new Temas();
                auxTema.setSplitTema(jComboBoxTemas.getSelectedItem().toString());
                mini.setTema(auxTema);

                if (jButtonSalvar.getLabel().equals("SALVAR")) {
                    miniBll.adicionar(mini);
                } else {
                    miniBll.alterar(mini);
                }
                consultarMiniaturas(miniBll.getConsultar());
                limpaCampos();
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Atenção !!!\n" + erro.getMessage());
            }
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // método para verificar campos vazios na inserção de miniaturas
    private boolean CamposIsEmpety() {
        String[] campos = new String[6];
        campos[0] = jTextFieldModelo.getText();
        campos[1] = jTextFieldAno.getText();
        campos[2] = jTextFieldObservacoes.getText();
        campos[3] = jTextFieldEdicao.getText();
        campos[4] = jTextFieldEscala.getText();
        campos[5] = jTextFieldValor.getText();
        if (jComboBoxFabricantes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o fabricante da miniatura\n");
        }
        if (jComboBoxTemas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o tema da miniatura\n");
        }
        if (jComboBoxTiposDeMiniatura.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo da miniatura\n");
        }

        for (int i = 0; i < campos.length; i++) {
            if (campos[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        try {

            miniBll.deletar(mini.getId());
            consultarMiniaturas(miniBll.getConsultar());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!\n" + erro.getMessage());
        }
    }//GEN-LAST:event_ExcluirActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        try {

            consultarMiniaturas(miniBll.pesquisarMiniatura(jTextFieldPesquisarMiniatura.getText()));

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção !!!\n " + erro.getMessage());
        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        try {
            limpaCampos();
            jTextFieldModelo.requestFocus();
            jButtonSalvar.setLabel("SALVAR");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jTableMiniaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMiniaturasMouseClicked
        try {
            int linha = jTableMiniaturas.getSelectedRow();
            Integer codigo = Integer.parseInt(jTableMiniaturas.getValueAt(linha, 0).toString());
            preencheCampos((int) codigo);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção\n " + erro.getMessage());
        }
    }//GEN-LAST:event_jTableMiniaturasMouseClicked

    private void jButtonManutencaoFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManutencaoFabricanteActionPerformed
        // TODO add your handling code here:
        try {
            new CadFabricantes(null, true).setVisible(true);

            List<Fabricantes> lista = fabBll.getConsulta();
            fabBll.ordenaListaFabricantes(lista);
            jComboBoxFabricantes.removeAllItems();
            jComboBoxFabricantes.addItem("< Selecione o fabricante >");
            for (int pos = 0; pos < lista.size(); pos++) {
                Fabricantes aux = lista.get(pos);
                jComboBoxFabricantes.addItem(String.format("%02d", aux.getId()) + " - " + aux.getNome().toUpperCase());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonManutencaoFabricanteActionPerformed

    private void jButtonManutencaoTipoMiniaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManutencaoTipoMiniaturaActionPerformed
        // TODO add your handling code here:
        try {
            new CadTipoMiniaturas(null, true).setVisible(true);

            List<TipoMiniaturas> listaTipoDeMiniaturas = new ArrayList<>();
            listaTipoDeMiniaturas = tipoBll.getConsulta();
            tipoBll.ordenaListaDeTipoDeMiniaturas(listaTipoDeMiniaturas);
            jComboBoxTiposDeMiniatura.removeAllItems();
            jComboBoxTiposDeMiniatura.addItem("< Selecione aqui o tipo de miniatura >");
            for (int pos = 0; pos < listaTipoDeMiniaturas.size(); pos++) {
                TipoMiniaturas aux = listaTipoDeMiniaturas.get(pos);
                jComboBoxTiposDeMiniatura.addItem(String.format("%02d", aux.getId()) + " - " + aux.getTipo().toUpperCase());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonManutencaoTipoMiniaturaActionPerformed

    private void jButtonManutencaoTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManutencaoTemasActionPerformed
        // TODO add your handling code here:
        try {
            new CadTemas(null, true).setVisible(true);

            List<Temas> listaTemas = new ArrayList<>();
            listaTemas = temaBll.getConsulta();
            temaBll.ordenaListaDeTemas(listaTemas);
            jComboBoxTemas.removeAllItems();
            jComboBoxTemas.addItem("< Selecione aqui o tema da miniatura >");
            for (int pos = 0; pos < listaTemas.size(); pos++) {
                Temas aux = listaTemas.get(pos);
                jComboBoxTemas.addItem(String.format("%02d", aux.getId()) + " - " + aux.getNome().toUpperCase());
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonManutencaoTemasActionPerformed

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
            java.util.logging.Logger.getLogger(CadMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadMiniaturas dialog = null;
                try {
                    dialog = new CadMiniaturas(new javax.swing.JFrame(), true);
                } catch (Exception ex) {
                    Logger.getLogger(CadMiniaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton Excluir;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonManutencaoFabricante;
    private javax.swing.JButton jButtonManutencaoTemas;
    private javax.swing.JButton jButtonManutencaoTipoMiniatura;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxFabricantes;
    private javax.swing.JComboBox<String> jComboBoxTemas;
    private javax.swing.JComboBox<String> jComboBoxTiposDeMiniatura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelMiniaturas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMiniaturas;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldCódigo;
    private javax.swing.JTextField jTextFieldEdicao;
    private javax.swing.JTextField jTextFieldEscala;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldObservacoes;
    private javax.swing.JTextField jTextFieldPesquisarMiniatura;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
