package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Fotos;
import util.Conexao;

/**
 *
 * @author roger
 */
public class FotoDal {

    private static FotoDal instance = null;
    private Connection conexao;

    private FotoDal() {
        conexao = Conexao.getConexao();
    }

    public static FotoDal getInstance() {
        if (instance == null) {
            instance = new FotoDal();
        }
        return instance;
    }

    public void addFoto(Fotos foto) throws Exception {

        String sql = "INSERT INTO foto(caminho_fot, descricao_fot, "
                + "id_min_fot) VALUES (?1,?2,?3)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, foto.getPath());
            preparedStatement.setString(2, foto.getDescricao());
            preparedStatement.setInt(3, foto.getMiniatura().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao adicionar este registro\n"
                    + erro.getMessage());
        }
    }

    public void deleteFoto(int id) throws Exception {
        String sql = "DELETE FROM foto WHERE id_fot=?";
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

    public void updateFoto(Fotos foto) throws Exception {
        String sql = "UPDATE foto SET caminho_fot=?,"
                + "descricao_fot=?, id_min_fot=? WHERE id_fot=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, foto.getPath());
            preparedStatement.setString(2, foto.getDescricao());
            preparedStatement.setInt(3, foto.getMiniatura().getId());
            preparedStatement.setInt(4, foto.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao alterar este registro\n"
                    + erro.getMessage());
        }

    }

    public List<Fotos> getAllFotos() throws Exception {
        List<Fotos> listaFotos = new ArrayList<Fotos>();
        String sql = "SELECT * FROM fotos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Fotos foto = new Fotos();
                foto.setId(rs.getInt("id_fot"));
                foto.setPath(rs.getString("caminho_fot"));
                foto.setDescricao(rs.getString("descricao_fot"));
                MiniaturaDal mini = MiniaturaDal.getInstance();
                foto.setMiniatura(mini.getMiniaturaById(rs.getInt("id_min_fot")));
                listaFotos.add(foto);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar os registros de fotos\n"
                    + erro.getMessage());
        }
        return listaFotos;
    }

    public Fotos getFotoById(int id) throws Exception {
        Fotos foto = new Fotos();
        String sql = "SELECT * FROM fotos WHERE id_fot=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                foto.setId(rs.getInt("id_fot"));
                foto.setPath(rs.getString("caminho_fot"));
                foto.setDescricao(rs.getString("descricao_fot"));
                MiniaturaDal mini = MiniaturaDal.getInstance();
                foto.setMiniatura(mini.getMiniaturaById(rs.getInt("id_min_fot")));

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de fotos\n"
                    + erro.getMessage());
        }
        return foto;
    }

}
