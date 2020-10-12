/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bll.TipoMiniaturaBll;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoMiniaturas;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roger
 */
public class CadTipoMiniaturas extends javax.swing.JDialog {

    private DefaultTableModel modelo = new DefaultTableModel();
    private TipoMiniaturaBll tipoMinBll = new TipoMiniaturaBll();
    private TipoMiniaturas tipoMin = new TipoMiniaturas();

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

    /**
     * Creates new form CadTipoDeTemas
     */
    public CadTipoMiniaturas(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        criarTblTipoMiniatura();
        consultaTipoMiniatura();

    }

    private void criarTblTipoMiniatura() {
        modelo.addColumn("Código");
        modelo.addColumn("Tipo");

        jTableTipoDeMiniatura.setModel(modelo);
        
        jTableTipoDeMiniatura.getColumnModel().getColumn(0).setMinWidth(60);
        jTableTipoDeMiniatura.getColumnModel().getColumn(0).setMaxWidth(60);
        jTableTipoDeMiniatura.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    private void consultaTipoMiniatura() throws Exception {
        modelo.setRowCount(0);

        List<TipoMiniaturas> listaTipoMiniaturas = new ArrayList<TipoMiniaturas>();
        listaTipoMiniaturas = tipoMinBll.getConsulta();

        // Chamada do método para ordenar a lista de tipo de miniaturas
        tipoMinBll.ordenaListaDeTipoDeMiniaturas(listaTipoMiniaturas);

        for (int i = 0; i < listaTipoMiniaturas.size(); i++) {
            modelo.addRow(new Object[]{listaTipoMiniaturas.get(i).getId(),
                listaTipoMiniaturas.get(i).getTipo().toUpperCase()});
        }
        int registros = listaTipoMiniaturas.size();
        jTextFieldQuantRegistros.setText(String.format("%02d", registros));
    }

    private void limpaCampos() {
        jTextFieldCodTipoMiniatura.setText("");
        jTextFieldNomeTipoMiniatura.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadFabricantes = new javax.swing.JPanel();
        jTextFieldCodTipoMiniatura = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeTipoMiniatura = new javax.swing.JTextField();
        jButtonExcluir = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTipoDeMiniatura = new javax.swing.JTable(modelo);
        jButtonConsultar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Tipo de Miniaturas");

        jTextFieldCodTipoMiniatura.setEditable(false);

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cod.");

        jLabel2.setText("Tipo");

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonNovo.setText("NOVO");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jTableTipoDeMiniatura.setModel(modelo);
        jTableTipoDeMiniatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTipoDeMiniaturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTipoDeMiniatura);

        jButtonConsultar.setText("CONSULTAR");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jLabel3.setText("Quant.Registros:");

        jTextFieldQuantRegistros.setEditable(false);

        javax.swing.GroupLayout jPanelCadFabricantesLayout = new javax.swing.GroupLayout(jPanelCadFabricantes);
        jPanelCadFabricantes.setLayout(jPanelCadFabricantesLayout);
        jPanelCadFabricantesLayout.setHorizontalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCodTipoMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeTipoMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadFabricantesLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanelCadFabricantesLayout.setVerticalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodTipoMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomeTipoMiniatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonConsultar)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCadFabricantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadFabricantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (jTextFieldNomeTipoMiniatura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o nome do tipo de miniatura");
        } else {

            try {

                tipoMin.setTipo(jTextFieldNomeTipoMiniatura.getText());

                if (jButtonSalvar.getLabel().equals("SALVAR")) {
                    tipoMinBll.adicionarTipoDeMiniatura(tipoMin);
                } else {
                    tipoMinBll.alterarTipoDeMiniatura(tipoMin);
                }
                consultaTipoMiniatura();

                limpaCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        jTextFieldNomeTipoMiniatura.requestFocus();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            tipoMinBll.removerTipoDeMiniaturas(tipoMinBll.getConsultaPorId(tipoMin.getId()));
            consultaTipoMiniatura();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        limpaCampos();
        jTextFieldNomeTipoMiniatura.requestFocus();
        jButtonSalvar.setLabel("SALVAR");
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jTableTipoDeMiniaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTipoDeMiniaturaMouseClicked
        int linha = jTableTipoDeMiniatura.getSelectedRow();
        Integer codigo = (Integer) jTableTipoDeMiniatura.getValueAt(linha, 0);
        preencherCampos((int) codigo);
    }//GEN-LAST:event_jTableTipoDeMiniaturaMouseClicked

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        try {
            consultaTipoMiniatura();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void preencherCampos(int id) {

        try {
            if (id > 0) {
                tipoMin = tipoMinBll.getConsultaPorId(id);
                jTextFieldNomeTipoMiniatura.setText(tipoMin.getTipo());
                jTextFieldCodTipoMiniatura.setText(id + "");
                jButtonSalvar.setLabel("EDITAR");
                jTextFieldNomeTipoMiniatura.requestFocus();

            } else {
                jButtonSalvar.setLabel("SALVAR");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção mouse click!!!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

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
            java.util.logging.Logger.getLogger(CadTipoMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadTipoMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadTipoMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadTipoMiniaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadTipoMiniaturas dialog = null;
                try {
                    dialog = new CadTipoMiniaturas(new javax.swing.JFrame(), true);
                } catch (Exception ex) {
                    Logger.getLogger(CadTipoMiniaturas.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelCadFabricantes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTipoDeMiniatura;
    private javax.swing.JTextField jTextFieldCodTipoMiniatura;
    private javax.swing.JTextField jTextFieldNomeTipoMiniatura;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    // End of variables declaration//GEN-END:variables
}
