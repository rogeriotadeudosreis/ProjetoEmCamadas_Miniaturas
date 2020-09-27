package bll_regrasdenegocios;

import dal_acessosaosdados.FabricanteDal;
import java.util.List;
import model_classededados.Fabricantes;

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
        dal.addFabricante(objeto);
    }

    public void alterar(Fabricantes objeto) throws Exception {
        dal.updateFabricante(objeto);
    }

    public void remover(Fabricantes objeto) throws Exception {
        dal.deleteFabricante(objeto.getId());
    }

    public List<Fabricantes> getConsulta() throws Exception {
        return dal.getAllFabricantes();
    }

    public Fabricantes getConsultaPorId(int id) throws Exception {
        return dal.getFabricanteById(id);
    }
}
