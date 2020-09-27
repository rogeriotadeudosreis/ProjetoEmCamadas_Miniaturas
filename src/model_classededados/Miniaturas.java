/*
CREATE TABLE miniaturas (
   id_min SERIAL PRIMARY KEY,
   modelo_min VARCHAR(80) NOT NULL,
   ano_min TIMESTAMP,
   observacoes_min VARCHAR(100),
   edicao_min VARCHAR(80) NOT NULL,
   escala_min VARCHAR(80) NOT NULL,
   valor_min NUMERIC(7,2) NOT NULL,
   id_fab_min INTEGER,
   id_tipMin_min INTEGER,
   id_tem_min INTEGER,
   FOREIGN KEY (id_fab_min) REFERENCES fabricantes (id_fab),
   FOREIGN KEY (id_tipMin_min) REFERENCES tipo_miniaturas (id_tipMin),
   FOREIGN KEY (id_tem_min) REFERENCES temas (id_tem)   
);

*/

package model_classededados;

import java.util.Date;

/**
 *
 * @author roger
 */
public class Miniaturas {

    private int id;
    private String modelo_min;
    private Date ano_min;
    private String observacoes_min;
    private String edicao_min;
    private String escala_min;
    private double valor_min;
    private int id_fab_min;
    private int id_tipMin_min;
    private int id_tem_min;

    public Miniaturas() {
    }

    public Miniaturas(int id, String modelo_min, Date ano_min, String observacoes_min, String edicao_min, String escala_min, double valor_min, int id_fab_min, int id_tipMin_min, int id_tem_min) {
        this.id = id;
        this.modelo_min = modelo_min;
        this.ano_min = ano_min;
        this.observacoes_min = observacoes_min;
        this.edicao_min = edicao_min;
        this.escala_min = escala_min;
        this.valor_min = valor_min;
        this.id_fab_min = id_fab_min;
        this.id_tipMin_min = id_tipMin_min;
        this.id_tem_min = id_tem_min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo_min() {
        return modelo_min;
    }

    public void setModelo_min(String modelo_min) {
        this.modelo_min = modelo_min;
    }

    public Date getAno_min() {
        return ano_min;
    }

    public void setAno_min(Date ano_min) {
        this.ano_min = ano_min;
    }

    public String getObservacoes_min() {
        return observacoes_min;
    }

    public void setObservacoes_min(String observacoes_min) {
        this.observacoes_min = observacoes_min;
    }

    public String getEdicao_min() {
        return edicao_min;
    }

    public void setEdicao_min(String edicao_min) {
        this.edicao_min = edicao_min;
    }

    public String getEscala_min() {
        return escala_min;
    }

    public void setEscala_min(String escala_min) {
        this.escala_min = escala_min;
    }

    public double getValor_min() {
        return valor_min;
    }

    public void setValor_min(double valor_min) {
        this.valor_min = valor_min;
    }

    public int getId_fab_min() {
        return id_fab_min;
    }

    public void setId_fab_min(int id_fab_min) {
        this.id_fab_min = id_fab_min;
    }

    public int getId_tipMin_min() {
        return id_tipMin_min;
    }

    public void setId_tipMin_min(int id_tipMin_min) {
        this.id_tipMin_min = id_tipMin_min;
    }

    public int getId_tem_min() {
        return id_tem_min;
    }

    public void setId_tem_min(int id_tem_min) {
        this.id_tem_min = id_tem_min;
    }
    
    

}
