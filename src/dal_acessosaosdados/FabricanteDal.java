/*
CREATE TABLE fabricantes (
   id_fab SERIAL PRIMARY KEY,
   nome_fab VARCHAR (80) NOT NULL
);
 */
package dal_acessosaosdados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model_classededados.Fabricantes;
import util_utilidades.Conexao;

/**
 *
 * @author roger
 */
public class FabricanteDal {

    private static FabricanteDal instance = null;
    private Connection conexao;

    private FabricanteDal() {
        conexao = Conexao.getConexao();
    }

    public static FabricanteDal getInstance() {
        if (instance == null) {
            instance = new FabricanteDal();
        }
        return instance;

    }

    public void addFabricante(Fabricantes fabricante) throws Exception {
        String sql = "INSERT INTO fabricante(nome) VALUES (?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, fabricante.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new Exception("Ocorreu um erro ao adicionar este registro\n");
        }
    }

    public void deleteFabricante(int id) throws Exception {
        String sql = "DELETE FROM fabricantes WHERE id=?";
        try {

            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new Exception("Ocorreu um erro ao deletar este registro!\n");
        }
    }

    public void updateFabricante(Fabricantes fabricante) throws Exception {
        String sql = "UPDATE fabricantes SET nome=? WHERE id=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, fabricante.getNome());
            preparedStatement.setInt(2, fabricante.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            erro.printStackTrace();
            throw new Exception("Ocorreu um erro ao alterar este registro\n");
        }

    }

    public List<Fabricantes> getAllFabricantes() throws Exception {
        List<Fabricantes> listFabricantes = new ArrayList<Fabricantes>();
        String sql = "SELECT * FROM fabricantes";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Fabricantes fabricante = new Fabricantes();
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));
                listFabricantes.add(fabricante);
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            throw new Exception("Ocorreu um erro ao consultar os registros de fabricantes\n");
        }
        return listFabricantes;
    }

    public Fabricantes getFabricanteById(int id) throws Exception {
        Fabricantes fabricante = new Fabricantes();
        String sql = "SELECT * FROM fabricantes WHERE id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            throw new Exception("Ocorreu um erro ao buscar este registro de fabricante\n");
        }
        return null;
    }
}
