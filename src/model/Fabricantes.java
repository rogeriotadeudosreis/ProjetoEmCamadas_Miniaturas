/*
CREATE TABLE fabricantes (
   id_fab SERIAL PRIMARY KEY,
   nome_fab VARCHAR (80) NOT NULL
);
*/

package model;

/**
 *
 * @author roger
 */
public class Fabricantes {
    
    private int id;
    private String nome;

    public Fabricantes() {
    }

    public Fabricantes(int id, String nome) {
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

    @Override
    public String toString() {
        
        String saida = "";
        saida += this.id;
        saida += this.nome;
        return saida; 
    }
    
    public void setSplitFabricante (String combBox)throws Exception{
        String[] vetor = combBox.split(" - ");
        this.id = Integer.parseInt(vetor[0]);
        this.nome = vetor[1];
    }

       
    
}
