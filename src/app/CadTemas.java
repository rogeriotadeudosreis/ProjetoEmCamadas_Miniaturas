/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bll.TemaBll;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Temas;

/**
 *
 * @author roger
 */
public class CadTemas extends javax.swing.JDialog {

    private DefaultTableModel modelo = new DefaultTableModel();
    private TemaBll temaBll = new TemaBll();
    private Temas tema = new Temas();

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
     * Creates new form CadTemas
     */
    public CadTemas(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        criarTblTemas();
        consultaTemas();
        initComponents();
    }

    private void criarTblTemas() {
        jTableTemas = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");

        jTableTemas.getColumnModel().getColumn(0).setMinWidth(10);
        jTableTemas.getColumnModel().getColumn(0).setMaxWidth(10);
        jTableTemas.getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    private void consultaTemas() throws Exception {
        modelo.setRowCount(0);

        List<Temas> listaTemas = new ArrayList<Temas>();
        listaTemas = temaBll.getConsulta();

        // Chamda do método para ordenar a lista de temas 
        temaBll.ordenaListaDeTemas(listaTemas);

        for (int i = 0; i < listaTemas.size(); i++) {
            modelo.addRow(new Object[]{listaTemas.get(i).getId(),
                listaTemas.get(i).getNome().toUpperCase()});
        };
    }

    private void limpaCampos() {
        jTextFieldCodTema.setText("");
        jTextFieldNomeTema.setText("");

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
        jTextFieldCodTema = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeTema = new javax.swing.JTextField();
        jButtonExcluir = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTemas = new javax.swing.JTable(modelo);
        jButtonConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Temas de Miniaturas");

        jTextFieldCodTema.setEditable(false);

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

        jTableTemas.setModel(modelo);
        jTableTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTemasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTemas);

        jButtonConsultar.setText("CONSULTAR");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadFabricantesLayout = new javax.swing.GroupLayout(jPanelCadFabricantes);
        jPanelCadFabricantes.setLayout(jPanelCadFabricantesLayout);
        jPanelCadFabricantesLayout.setHorizontalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jTextFieldCodTema, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeTema, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanelCadFabricantesLayout.setVerticalGroup(
            jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadFabricantesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomeTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCadFabricantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonConsultar))
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

        if (jTextFieldNomeTema.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o nome do tema da miniatura");
        } else {

            try {

                tema.setNome(jTextFieldNomeTema.getText());

                if (jButtonSalvar.getLabel().equals("SALVAR")) {
                    temaBll.adicionar(tema);
                } else {
                    temaBll.alterar(tema);
                }
                consultaTemas();

                limpaCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        jTextFieldNomeTema.requestFocus();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            temaBll.remover(temaBll.getConsultaPorId(tema.getId()));
            consultaTemas();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "\nAtenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        limpaCampos();
        jTextFieldNomeTema.requestFocus();
        jButtonSalvar.setLabel("SALVAR");
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jTableTemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTemasMouseClicked
        int linha = jTableTemas.getSelectedRow();
        Integer codigo = (Integer) jTableTemas.getValueAt(linha, 0);
        preencherCampos((int) codigo);
    }//GEN-LAST:event_jTableTemasMouseClicked

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        try {
            consultaTemas();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void preencherCampos(int id) {
        try {
            if (id > 0) {
                tema = temaBll.getConsultaPorId(id);
                jTextFieldNomeTema.setText(tema.getNome());
                jTextFieldCodTema.setText(tema.getId() + "");
                jButtonSalvar.setLabel("EDITAR");
                jTextFieldNomeTema.requestFocus();
            } else {
                jButtonSalvar.setLabel("SALVAR");
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atenção!!!" + erro.getMessage());
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
            java.util.logging.Logger.getLogger(CadTemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadTemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadTemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadTemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadTemas dialog = new CadTemas(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(CadTemas.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JPanel jPanelCadFabricantes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTemas;
    private javax.swing.JTextField jTextFieldCodTema;
    private javax.swing.JTextField jTextFieldNomeTema;
    // End of variables declaration//GEN-END:variables
}
