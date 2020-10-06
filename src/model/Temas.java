/*
CREATE TABLE temas (
   id_tem SERIAL PRIMARY KEY,
   nome_tem VARCHAR (80) NOT NULL
);
*/

package model;

/**
 *
 * @author roger
 */
public class Temas {
    
    private int id;
    private String nome;

    public Temas() {
    }

    public Temas(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setSplitTema(String combBox)throws Exception{
        String []vetor = combBox.split("-");
        this.id = Integer.parseInt(vetor[0]);
        this.nome = vetor[1];
    }
    
}
