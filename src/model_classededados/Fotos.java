/*
CREATE TABLE fotos (
   id_fot SERIAL PRIMARY KEY,
   caminho_fot VARCHAR (150) NOT NULL,
   descricao_fot VARCHAR (80) NOT NULL,
   id_min_fot INTEGER,
   FOREIGN KEY (id_min_fot) REFERENCES miniaturas (id_min)
);
*/

package model_classededados;

/**
 *
 * @author roger
 */
public class Fotos {
    
    private int id;
    private String path;
    private String descricao;
    private int id_min;

    public Fotos() {
    }

    public Fotos(int id, String path, String descricao, int id_min) {
        this.id = id;
        this.path = path;
        this.descricao = descricao;
        this.id_min = id_min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_min() {
        return id_min;
    }

    public void setId_min(int id_min) {
        this.id_min = id_min;
    }
    
    
    
}
