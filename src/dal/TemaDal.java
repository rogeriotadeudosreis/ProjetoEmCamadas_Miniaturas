package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Temas;
import util.Conexao;

/**
 *
 * @author roger
 */
public class TemaDal {

    private static TemaDal instance = null;
    private Connection conexao;

    private TemaDal() {
        conexao = Conexao.getConexao();
    }

    public static TemaDal getInstance() {
        if (instance == null) {
            instance = new TemaDal();
        }
        return instance;
    }

    public void addTemasDal(Temas tema) throws Exception {
        String sql = "INSERT INTO temas(nome_tem) VALUES (?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, tema.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao adicionar este registro\n"
                    + erro.getMessage());
        }
    }

    public void deleteTemas(int id) throws Exception {
        String sql = "DELETE FROM temas WHERE id_tem=?";
        try {

            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void updateTemas(Temas tema) throws Exception {
        String sql = "UPDATE temas SET nome_tem=? WHERE id_tem=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, tema.getNome());
            preparedStatement.setInt(2, tema.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao alterar este registro\n"
                    + erro.getMessage());
        }

    }

    public List<Temas> getAllTemas() throws Exception {
        List<Temas> listTemas = new ArrayList<>();
        String sql = "SELECT * FROM temas";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Temas tema = new Temas();
                tema.setId(rs.getInt("id_tem"));
                tema.setNome(rs.getString("nome_tem"));
                listTemas.add(tema);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar os registros de temas\n"
                    + erro.getMessage());
        }
        return listTemas;
    }

    public Temas getTemasById(int id) throws Exception {
        Temas tema = new Temas();
        String sql = "SELECT * FROM temas WHERE id_tem=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tema.setId(rs.getInt("id_tem"));
                tema.setNome(rs.getString("nome_tem"));
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de fabricantes\n"
                    + erro.getMessage());
        }
        return tema;
    }
    
     public ArrayList pesquisarTemas(String dados) throws Exception {
        String textoDigitado = dados;
        ArrayList<Temas> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Temas tema : getAllTemas()) {
            if (tema.getNome().toLowerCase().trim().contains(textoDigitado)) {
                resultadoDaPesquisa.add(tema);
                vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }
}
