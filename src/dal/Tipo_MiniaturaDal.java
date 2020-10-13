/*
 CREATE TABLE tipo_miniaturas (
   id_tipMin SERIAL PRIMARY KEY,
   tipo_tipMin VARCHAR (80) NOT NULL
);
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Fabricantes;
import model.TipoMiniaturas;
import util.Conexao;

/**
 *
 * @author roger
 */
public class Tipo_MiniaturaDal {

    private static Tipo_MiniaturaDal instance = null;
    private Connection conexao;

    private Tipo_MiniaturaDal() {
        conexao = Conexao.getConexao();
    }

    public static Tipo_MiniaturaDal getInstance() {
        if (instance == null) {
            instance = new Tipo_MiniaturaDal();
        }
        return instance;
    }

    public void addTipoMiniaturas(TipoMiniaturas tipo) throws Exception {
        String sql = "INSERT INTO tipo_miniaturas (tipo_tipmin) VALUES (?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, tipo.getTipo());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao adicionar este registro\n"
                    + erro.getMessage());
        }
    }

    public void deleteTipoMiniaturas(int id) throws Exception {
        String sql = "DELETE FROM tipo_miniaturas WHERE id_tipmin=?";
        try {

            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao deletar este registro!\n"
                    + erro.getMessage());
        }
    }

    public void updateTipoMiniaturas(TipoMiniaturas tipo) throws Exception {
        String sql = "UPDATE tipo_miniaturas SET tipo_tipMin=? WHERE id_tipmin=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, tipo.getTipo());
            preparedStatement.setInt(2, tipo.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao alterar este registro\n"
                    + erro.getMessage());
        }
    }

    public List<TipoMiniaturas> getAllTipoMiniaturas() throws Exception {
        List<TipoMiniaturas> list = new ArrayList<TipoMiniaturas>();
        String sql = "SELECT * FROM tipo_miniaturas";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TipoMiniaturas tipo = new TipoMiniaturas();
                tipo.setId(rs.getInt("id_tipmin"));
                tipo.setTipo(rs.getString("tipo_tipMin"));
                list.add(tipo);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar os registros de tipo de miniaturas\n"
                    + erro.getMessage());
        }
        return list;
    }

    public TipoMiniaturas getTipoMinById(int id) throws Exception {
        TipoMiniaturas tipo = new TipoMiniaturas();
        String sql = "SELECT * FROM tipo_miniaturas WHERE id_tipMin=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tipo.setId(rs.getInt("id_tipMin"));
                tipo.setTipo(rs.getString("tipo_tipMin"));
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de tipo de miniaturas\n"
                    + erro.getMessage());
        }
        return tipo;
    }

    public ArrayList pesquisarTipoDeMiniatura(String dados) throws Exception {
        String textoDigitado = dados;
        ArrayList<TipoMiniaturas> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (TipoMiniaturas tipo : getAllTipoMiniaturas()) {
            if (tipo.getTipo().toLowerCase().trim().contains(textoDigitado)) {
                resultadoDaPesquisa.add(tipo);
                vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }
}
