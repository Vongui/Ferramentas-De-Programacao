
package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "veiculo")
@NamedQueries(value = {
    @NamedQuery(name = "Veiculo.buscarPeloAnoFabricacao", query = "select v from Veiculo v where v.anoFabricacao = 2000"),
    @NamedQuery(name = "Veiculo.buscarPelaPlaca", query = "FROM Veiculo v Where v.codigo.placa = :placa"),// : -> parametro
    @NamedQuery(name = "Veiculo.buscarPelaPlacaCidade", query = "FROM Veiculo v Where v.codigo.placa = :placa AND v.codigo.cidade = :cidade")
})
public class Veiculo implements Serializable{
    
    @EmbeddedId
    private VeiculoId codigo;
    
    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano_fabricacao")
    private int anoFabricacao;
    
    public VeiculoId getCodigo() {
        return codigo;
    }

    public void setCodigo(VeiculoId codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    
    
    
}
