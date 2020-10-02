/*
CREATE TABLE fabricantes (
   id_fab SERIAL PRIMARY KEY,
   nome_fab VARCHAR (80) NOT NULL
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
import util.Conexao;

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
        String sql = "INSERT INTO fabricantes(nome_fab) VALUES (?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, fabricante.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao adicionar este registro\n"
            + erro.getMessage());
        }
    }
    
    public void deleteFabricante(int id) throws Exception {
        String sql = "DELETE FROM fabricantes WHERE id_fab=?";
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

    public void updateFabricante(Fabricantes fabricante) throws Exception {
        String sql = "UPDATE fabricantes SET nome_fab=? WHERE id_fab=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, fabricante.getNome());
            preparedStatement.setInt(2, fabricante.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao alterar este registro\n"
            + erro.getMessage());
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
                fabricante.setId(rs.getInt("id_fab"));
                fabricante.setNome(rs.getString("nome_fab"));
                listFabricantes.add(fabricante);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar os registros de fabricantes\n"
            + erro.getMessage());
        }
        return listFabricantes;
    }
    
    public Fabricantes getFabricanteById(int id) throws Exception {
        Fabricantes fabricante = new Fabricantes();
        String sql = "SELECT * FROM fabricantes WHERE id_fab=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                fabricante.setId(rs.getInt("id_fab"));
                fabricante.setNome(rs.getString("nome_fab"));
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de fabricantes\n"
             + erro.getMessage());
        }
        return fabricante;
    }
}











