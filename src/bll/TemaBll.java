package bll;

import dal.TemaDal;
import java.util.ArrayList;
import java.util.List;
import model.Fabricantes;
import model.Temas;

/**
 *
 * @author roger
 */
public class TemaBll {

    private static final long serialVersionUID = 1L;

    private TemaDal dal;

    public TemaBll() {
        super();
        dal = TemaDal.getInstance();
    }

    public void adicionar(Temas tema) throws Exception {
        validaTema(tema);
        dal.addTemasDal(tema);
    }

    public void alterar(Temas tema) throws Exception {
        validaTema(tema);
        dal.updateTemas(tema);
    }

    public void remover(Temas tema) throws Exception {
        dal.deleteTemas(tema.getId());
    }

    public List<Temas> getConsulta() throws Exception {
        return dal.getAllTemas();
    }
    
    public Temas getConsultaPorId (int id)throws Exception{
        return dal.getTemasById(id);
    }
    
    public void validaTema(Temas objeto)throws Exception{
        String nome = objeto.getNome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição do tema inválido!");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe a descrição do tema");
        }
        
        if (nome.length() < 3) {
            throw new Exception("A descrição do tema deve ter no mínimo 3 letras!\n");
        }
        
        List<Temas> lista = dal.getAllTemas();
        for (int pos = 0; pos < lista.size(); pos++) {
            Temas aux = lista.get(pos);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                throw new Exception("A descrição informada já existe!\n");
            }
        }
   }
    
    public void ordenaListaDeTemas(List<Temas> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) >= 0) {
                    Temas temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }
    
     public ArrayList pesquisarTemas(String dados) throws Exception{
         return this.dal.pesquisarTemas(dados);
     }

}
