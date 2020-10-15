/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dal.FotoDal;
import java.util.List;
import model.Fotos;

/**
 *
 * @author roger
 */
public class FotoBll {

    private static final long serialVersionUID = 1L;
    private FotoDal dal;

    public FotoBll() {
        super();
        dal = FotoDal.getInstance();
    }

    public void adicionar(Fotos objeto) throws Exception {
        dal.addFoto(objeto);
    }

    public void remover(int id) throws Exception {
        try {
            dal.deleteFoto(id);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("A foto que deseja deletar está relacionada "
                        + "a um registro de miniatura!\nVerifique!\n");
            }
        }
    }

    public void alterar(Fotos foto) throws Exception {
        dal.updateFoto(foto);
    }

    public List<Fotos> getConsultar() throws Exception {
        return dal.getAllFotos();
    }

    public Fotos consultarPorId(int id) throws Exception {
        return dal.getFotoById(id);
    }

    public void ordenaListaFotos(List<Fotos> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getMiniatura().getModelo_min().compareToIgnoreCase(lista.get(j).getMiniatura().getModelo_min()) >= 0) {
                    Fotos temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }

}
