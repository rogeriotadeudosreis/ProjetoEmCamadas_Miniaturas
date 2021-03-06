package bll;

import dal.Tipo_MiniaturaDal;
import java.util.ArrayList;
import java.util.List;
import model.Temas;
import model.TipoMiniaturas;

/**
 *
 * @author roger
 */
public class TipoMiniaturaBll {

    //
    private static final long serialVersionUID = 1L;
    private Tipo_MiniaturaDal dal;

    public TipoMiniaturaBll() {
        super();
        dal = Tipo_MiniaturaDal.getInstance();
    }

    public void adicionarTipoDeMiniatura(TipoMiniaturas tipo) throws Exception {
        validaTipo(tipo);
        dal.addTipoMiniaturas(tipo);
    }

    public void alterarTipoDeMiniatura(TipoMiniaturas tipo) throws Exception {
        validaTipo(tipo);
        dal.updateTipoMiniaturas(tipo);
    }

    public void removerTipoDeMiniaturas(TipoMiniaturas tipo) throws Exception {
        try {
            dal.deleteTipoMiniaturas(tipo.getId());
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("O tipo de miniatura que deseja deletar "
                        + "está relacionado com um registro de miniatura!\n"
                        + "Verifique\n");
            }
        }
    }

    public List<TipoMiniaturas> getConsulta() throws Exception {
        return dal.getAllTipoMiniaturas();
    }

    public TipoMiniaturas getConsultaPorId(int id) throws Exception {
        return dal.getTipoMinById(id);
    }

    public void validaTipo(TipoMiniaturas objeto) throws Exception {
        String tipo = objeto.getTipo().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (tipo.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição do tipo inválido!");
            }
        }
        if (tipo.equals("")) {
            throw new Exception("Informe a descrição do tipo");
        }

        if (tipo.length() < 3) {
            throw new Exception("A descrição do tipo deve ter no mínimo 3 letras!\n");
        }

        List<TipoMiniaturas> lista = dal.getAllTipoMiniaturas();
        for (int pos = 0; pos < lista.size(); pos++) {
            TipoMiniaturas aux = lista.get(pos);
            if (tipo.equalsIgnoreCase(aux.getTipo())) {
                throw new Exception("A descrição do tipo informado já existe!\n");
            }
        }
    }

    public void ordenaListaDeTipoDeMiniaturas(List<TipoMiniaturas> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getTipo().compareToIgnoreCase(lista.get(j).getTipo()) >= 0) {
                    TipoMiniaturas temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }

    public ArrayList pesquisarTipoDeMiniatura(String dados) throws Exception {
        return this.dal.pesquisarTipoDeMiniatura(dados);
    }

}
