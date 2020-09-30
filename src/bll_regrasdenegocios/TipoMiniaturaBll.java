package bll_regrasdenegocios;

import dal_acessosaosdados.Tipo_MiniaturaDal;
import java.util.List;
import model_classededados.TipoMiniaturas;

/**
 *
 * @author roger
 */
public class TipoMiniaturaBll {
    
    //
    private static final long serialVersionUID = 1L;
    private Tipo_MiniaturaDal dal;
    
    public TipoMiniaturaBll(){
        super();
        dal = Tipo_MiniaturaDal.getInstance();
    }
    
    public void adicionarTipoDeMiniatura (TipoMiniaturas tipo) throws Exception{
        dal.addTipoMiniaturas(tipo);
    }
    
    public void alterarTipoDeMiniatura (TipoMiniaturas tipo)throws Exception{
        dal.updateTipoMiniaturas(tipo);
    }
    
    public void removerTipoDeMiniaturas(TipoMiniaturas tipo)throws Exception{
        dal.deleteTipoMiniaturas(tipo.getId());
    }
    
    public List<TipoMiniaturas> getConsultaPorId (int id)throws Exception{
        return dal.getAllTipoMiniaturas();
    }
}
