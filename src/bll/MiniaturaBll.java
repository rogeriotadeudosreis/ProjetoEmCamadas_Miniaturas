package bll;

import dal.MiniaturaDal;
import java.util.List;
import model.Miniaturas;

/**
 *
 * @author roger
 */
public class MiniaturaBll {
    
    private static final long serialVersionUID = 1L;
    private MiniaturaDal dal;

    public MiniaturaBll() {
        super();
        dal = MiniaturaDal.getInstance();
    }
    
    public void adicionar (Miniaturas objeto) throws Exception{
        validaMiniatura(objeto);
        dal.addMiniatura(objeto);
    }
    
    public void deletar(int id) throws Exception{
        dal.deleteMiniatura(id);
    }
    
    public void alterar(Miniaturas objeto) throws Exception{
        validaMiniatura(objeto);
        dal.updateMiniatura(objeto);
    }
    
    public List<Miniaturas> getConsultar ()throws Exception{
        return dal.getAllMiniaturas();
    }
    
    public Miniaturas getConsultarPorId (int id) throws Exception{
        return dal.getMiniaturaById(id);
    }
    
     public void ordenaListaMiniaturas(List<Miniaturas> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getModelo_min().compareToIgnoreCase(lista.get(j).getModelo_min()) >= 0) {
                    Miniaturas temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
       
    }
     
     public void validaMiniatura (Miniaturas objeto)throws Exception{
         List<Miniaturas> lista = dal.getAllMiniaturas();
         for (int pos = 0; pos < lista.size(); pos++) {
             Miniaturas aux = lista.get(pos);
             if (objeto.getModelo_min().equalsIgnoreCase(aux.getModelo_min().trim())) {
                 throw new Exception("O modelo informado já existe nos registros de miniaturas");
             }
         }
         if (objeto.getValor_min() < 0) {
             throw new Exception("O valor da miniatura não pode ser menor que zero!");
         }
     }
     
     
    
    
    
}
