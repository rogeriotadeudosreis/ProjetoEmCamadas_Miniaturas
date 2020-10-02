/*
CREATE TABLE tipo_miniaturas (
   id_tipMin SERIAL PRIMARY KEY,
   tipo_tipMin VARCHAR (80) NOT NULL
);
*/

package model;

/**
 *
 * @author roger
 */
public class TipoMiniaturas {
    
    private int id;
    private String tipo;

    public TipoMiniaturas() {
    }

    public TipoMiniaturas(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
