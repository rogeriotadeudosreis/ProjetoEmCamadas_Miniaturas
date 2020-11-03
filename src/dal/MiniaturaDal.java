package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Miniaturas;
import util.Conexao;

/**
 *
 * @author roger
 */
public class MiniaturaDal {

    private static MiniaturaDal instance = null;
    private Connection conexao;

    private MiniaturaDal() {
        conexao = Conexao.getConexao();
    }

    public static MiniaturaDal getInstance() {
        if (instance == null) {
            instance = new MiniaturaDal();
        }
        return instance;
    }

    public void addMiniatura(Miniaturas miniatura) throws Exception {

        String sql = "INSERT INTO miniaturas(modelo_min, ano_min, observacoes_min,"
                + "edicao_min, escala_min, valor_min, id_fab_min, id_tipMin_min,"
                + "id_tem_min) VALUES (?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, miniatura.getModelo_min());
            preparedStatement.setInt(2, miniatura.getAno_min());
            preparedStatement.setString(3, miniatura.getObservacoes_min());
            preparedStatement.setString(4, miniatura.getEdicao_min());
            preparedStatement.setString(5, miniatura.getEscala_min());
            preparedStatement.setDouble(6, miniatura.getValor_min());
            preparedStatement.setInt(7, miniatura.getFabricante().getId());
            preparedStatement.setInt(8, miniatura.getTipoMin().getId());
            preparedStatement.setInt(9, miniatura.getTema().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao adicionar este registro\n"
                    + erro.getMessage());
        }
    }

    public void deleteMiniatura(int id) throws Exception {
        String sql = "DELETE FROM miniaturas WHERE id_min=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw new Exception("Ocorreu um erro ao deletar este registro!\n"
                    + erro.getMessage());
        }
    }

    public void updateMiniatura(Miniaturas miniatura) throws Exception {
        String sql = "UPDATE miniaturas SET modelo_min=?, ano_min=?,"
                + " observacoes_min=?, edicao_min=?, escala_min=?,"
                + " valor_min=?, id_fab_min=?, id_tipMin_min=?,"
                + "id_tem_min=? WHERE id_min=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, miniatura.getModelo_min());
            preparedStatement.setInt(2, miniatura.getAno_min());
            preparedStatement.setString(3, miniatura.getObservacoes_min());
            preparedStatement.setString(4, miniatura.getEdicao_min());
            preparedStatement.setString(5, miniatura.getEscala_min());
            preparedStatement.setDouble(6, miniatura.getValor_min());
            preparedStatement.setInt(7, miniatura.getFabricante().getId());
            preparedStatement.setInt(8, miniatura.getTipoMin().getId());
            preparedStatement.setInt(9, miniatura.getTema().getId());
            preparedStatement.setInt(10, miniatura.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao alterar este registro\n"
                    + erro.getMessage());
        }
    }

    public List<Miniaturas> getAllMiniaturas() throws Exception {
        List<Miniaturas> listaMiniaturas = new ArrayList<Miniaturas>();
        String sql = "SELECT * FROM miniaturas";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Miniaturas mini = new Miniaturas();
                mini.setId(rs.getInt("id_min"));
                mini.setModelo_min(rs.getString("modelo_min"));
                mini.setAno_min(rs.getInt("ano_min"));
                mini.setObservacoes_min(rs.getString("observacoes_min"));
                mini.setEdicao_min(rs.getString("edicao_min"));
                mini.setEscala_min(rs.getString("escala_min"));
                mini.setValor_min(rs.getDouble("valor_min"));
                FabricanteDal fab = FabricanteDal.getInstance();
                mini.setFabricante(fab.getFabricanteById(rs.getInt("id_fab_min")));
                Tipo_MiniaturaDal tipo = Tipo_MiniaturaDal.getInstance();
                mini.setTipoMin(tipo.getTipoMinById(rs.getInt("id_tipMin_min")));
                TemaDal tema = TemaDal.getInstance();
                mini.setTema(tema.getTemasById(rs.getInt("id_tem_min")));

                listaMiniaturas.add(mini);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de miniaturas\n"
                    + erro.getMessage());
        }
        return listaMiniaturas;
    }

    public Miniaturas getMiniaturaById(int id) throws Exception {
        Miniaturas mini = new Miniaturas();
        String sql = "SELECT * FROM miniaturas WHERE id_min=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                mini.setId(rs.getInt("id_min"));
                mini.setModelo_min(rs.getString("modelo_min"));
                mini.setAno_min(rs.getInt("ano_min"));
                mini.setObservacoes_min(rs.getString("observacoes_min"));
                mini.setEdicao_min(rs.getString("edicao_min"));
                mini.setEscala_min(rs.getString("escala_min"));
                mini.setValor_min(rs.getDouble("valor_min"));
                FabricanteDal fab = FabricanteDal.getInstance();
                mini.setFabricante(fab.getFabricanteById(rs.getInt("id_fab_min")));
                Tipo_MiniaturaDal tipo = Tipo_MiniaturaDal.getInstance();
                mini.setTipoMin(tipo.getTipoMinById(rs.getInt("id_tipMin_min")));
                TemaDal tema = TemaDal.getInstance();
                mini.setTema(tema.getTemasById(rs.getInt("id_tem_min")));

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de miniaturas\n"
                    + erro.getMessage());
        }
        return mini;
    }

    public ArrayList pesquisarMiniatura(String dados) throws Exception {
        String textoDigitado = dados;
        ArrayList<Miniaturas> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = false;
        for (Miniaturas mini : getAllMiniaturas()) {
            if (mini.getModelo_min().toLowerCase().trim().contains(textoDigitado)
                    || mini.getObservacoes_min().toLowerCase().trim().contains(textoDigitado)
                    || (mini.getEdicao_min().toLowerCase().trim().contains(textoDigitado)
                    || (mini.getEscala_min().toLowerCase().trim().contains(textoDigitado)))) {
                resultadoDaPesquisa.add(mini);
                vdd = true;
            }
        }
        if (!vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }

}
