
package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aluno
 */
@Embeddable
public class VeiculoId implements Serializable{
    
    @Column(name = "placa", length = 20)
    private String placa;
    @Column(name = "cidade", length = 30)
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
