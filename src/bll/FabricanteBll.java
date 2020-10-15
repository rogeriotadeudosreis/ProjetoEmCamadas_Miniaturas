package bll;

import dal.FabricanteDal;
import java.util.ArrayList;
import java.util.List;
import model.Fabricantes;

/**
 *
 * @author roger
 */
public class FabricanteBll {

    private static final long serialVersionUID = 1L;
    private FabricanteDal dal;

    public FabricanteBll() {
        super();
        dal = FabricanteDal.getInstance();
    }

    public void adicionar(Fabricantes objeto) throws Exception {
        validaFabricante(objeto);
        dal.addFabricante(objeto);
    }

    public void alterar(Fabricantes objeto) throws Exception {
        validaFabricante(objeto);
        dal.updateFabricante(objeto);

    }

    public void remover(Fabricantes objeto) throws Exception {
        try {
            dal.deleteFabricante(objeto.getId());
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("O fabricante informado não pode ser deletado "
                        + "porque existem registros relacionados com ele!\nVerifique\n");
            }
        }
    }

    public List<Fabricantes> getConsulta() throws Exception {
        return dal.getAllFabricantes();
    }

    public Fabricantes getConsultaPorId(int id) throws Exception {
        return dal.getFabricanteById(id);
    }

    public void validaFabricante(Fabricantes objeto) throws Exception {
        String nome = objeto.getNome().trim().toLowerCase();
        String invalidos = "'\"!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome do fabricante inválido!");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe o nome do fabricante");
        }
        if (nome.length() < 3) {
            throw new Exception("A descrição do fabricante deve ter no mínimo 3 letras!\n");
        }

        List<Fabricantes> lista = dal.getAllFabricantes();
        for (int pos = 0; pos < lista.size(); pos++) {
            Fabricantes aux = lista.get(pos);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                throw new Exception("O nome informado já existe "
                        + " nos registros de fabricantes!\n");
            }
        }
    }

    public void ordenaListaFabricantes(List<Fabricantes> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) >= 0) {
                    Fabricantes temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }

    public ArrayList pesquisarFbricante(String dados) throws Exception {
        return dal.pesquisarFbricante(dados);
    }

}
