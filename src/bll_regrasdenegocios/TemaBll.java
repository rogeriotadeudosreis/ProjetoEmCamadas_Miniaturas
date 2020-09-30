package bll_regrasdenegocios;

import dal_acessosaosdados.TemaDal;
import java.util.List;
import model_classededados.Temas;

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
        dal.addTemasDal(tema);
    }

    public void alterar(Temas tema) throws Exception {
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

}
