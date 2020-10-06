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
        dal.addMiniatura(objeto);
    }
    
    public void deletar(int id) throws Exception{
        dal.deleteMiniatura(id);
    }
    
    public void alterar(Miniaturas objeto) throws Exception{
        dal.updateMiniatura(objeto);
    }
    
    public List<Miniaturas> getConsultar ()throws Exception{
        return dal.getAllMiniaturas();
    }
    
    public Miniaturas getConsultarPorId (int id) throws Exception{
        return dal.getMiniaturaById(id);
    }
    
    
    
}
