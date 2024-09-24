package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VeiculoId implements Serializable{
    @Column(name = "placa")
    private String placa;
    
    @Column(name = "cidade")
    private String cidade;

    public VeiculoId() {
    }

    public VeiculoId(String placa, String cidade) {
        this.placa = placa;
        this.cidade = cidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
