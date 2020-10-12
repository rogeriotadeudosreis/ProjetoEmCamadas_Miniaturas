/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bll.FabricanteBll;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Fabricantes;

/**
 *
 * @author roger
 */
public class CadFabricantes extends javax.swing.JDialog {

    private DefaultTableModel modelo = new DefaultTableModel();
    private FabricanteBll fabricanteBll = new FabricanteBll();
    private Fabricantes fabricante = new Fabricantes();

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
     * Creates new form CadFabricantes
     */
    public CadFabricantes(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        criarTblFabricantes();
        consultaFabricantes();

    }

    private void criarTblFabricantes() {
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        jTableFabricantes.setModel(modelo);

        jTableFabricantes.getColumnModel().getColumn(0).setMinWidth(60);
        jTableFabricantes.getColumnModel().getColumn(0).setMaxWidth(60);
        jTableFabricantes.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    private void consultaFabricantes() throws Exception {
        modelo.setRowCount(0);

        List<Fabricantes> listaFabricantes = new ArrayList<>();
        listaFabricantes = fabricanteBll.getConsulta();
        
        //Chamado do método para ordenar a lista de fabricantes
        fabricanteBll.ordenaListaFabricantes(listaFabricantes);
        
        for (int i = 0; i < listaFabricantes.size(); i++) {
            modelo.addRow(new Object[]{listaFabricantes.get(i).getId(),
                listaFabricantes.get(i).getNome().toUpperCase()});
        }
        int registro = listaFabricantes.size();
        jTextFieldQuantRegistros.setText(String.format("%02d", registro));
    }
    
    private void limpaCampos() {
        jTextFieldCodFabricante.setText("");
        jTextFieldNomeFabricante.setText("");
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
        jTextFieldCodFabricante = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeFabricante = new javax.swing.JTextField();
        jButtonExcluir = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFabricantes = new javax.swing.JTable(modelo);
        jButtonConsultar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldQuantRegistros = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fabricantes");

        jTextFieldCodFabricante.setEditable(false);

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cod.");

        jLabel2.setText("Nome:");

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

        jTableFabricantes.setModel(modelo);
        jTableFabricantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFabricantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFabricantes);

        jButtonConsultar.setText("CONSULTAR");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jLabel3.setText("Quant. de Registros:");

        jTextFieldQuantRegistros.setEditable(false);

        javax.swing.GroupLayout jPanelCadFabricantesLayout = new javax.swing.GroupLayout(jPanelCadFabricantes);
        jPanelCadFabricantes.setLayout(jPanelCadFabricantesLayout);
        jPanelCadFabricantesLayout.setHorizontalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvar))
                            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNomeFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCadFabricantesLayout.setVerticalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomeFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonConsultar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldQuantRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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

        if (jTextFieldNomeFabricante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o nome do fabricante ");
        } else {

            try {

                fabricante.setNome(jTextFieldNomeFabricante.getText());

                if (jButtonSalvar.getLabel().equals("SALVAR")) {
                    fabricanteBll.adicionar(fabricante);
                } else {
                    fabricanteBll.alterar(fabricante);
                }
                consultaFabricantes();
                limpaCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        jTextFieldNomeFabricante.requestFocus();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            fabricanteBll.remover(fabricanteBll.getConsultaPorId(fabricante.getId()));
            consultaFabricantes();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableFabricantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFabricantesMouseClicked
        int linha = jTableFabricantes.getSelectedRow();
        Integer codigo = (Integer) jTableFabricantes.getValueAt(linha, 0);
        preencherCampos((int) codigo);
    }//GEN-LAST:event_jTableFabricantesMouseClicked

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        limpaCampos();
        jTextFieldNomeFabricante.requestFocus();
        jButtonSalvar.setLabel("SALVAR");
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        try {
            consultaFabricantes();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void preencherCampos(int id) {

        try {
            if (id > 0) {
                fabricante = fabricanteBll.getConsultaPorId(id);
                jTextFieldNomeFabricante.setText(fabricante.getNome());
                jTextFieldCodFabricante.setText(id + "");
                jButtonSalvar.setLabel("EDITAR");
                jTextFieldNomeFabricante.requestFocus();

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
            java.util.logging.Logger.getLogger(CadFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadFabricantes dialog = null;
                try {
                    dialog = new CadFabricantes(new javax.swing.JFrame(), true);
                } catch (Exception ex) {
                    Logger.getLogger(CadFabricantes.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JTable jTableFabricantes;
    private javax.swing.JTextField jTextFieldCodFabricante;
    private javax.swing.JTextField jTextFieldNomeFabricante;
    private javax.swing.JTextField jTextFieldQuantRegistros;
    // End of variables declaration//GEN-END:variables
}
