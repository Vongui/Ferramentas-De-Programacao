package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
@NamedQueries(value = {
    @NamedQuery(name = "Veiculo.buscarPeloAno", query = "Select v from Veiculo v where v.anoFabricacao = 2015"),
    @NamedQuery(name = "Veiculo.buscarPelaPlaca", query = "FROM Veiculo v WHERE v.codigo.placa = :placa"),
    @NamedQuery(name = "Veiculo.buscarPelaPlacaECidade", query = "FROM Veiculo v WHERE v.codigo.placa = :placa AND v.codigo.cidade = :cidade")
})
public class Veiculo implements Serializable {
    
    @EmbeddedId
    private VeiculoId codigo;
    
    @Column(name = "fabricante")
    private String fabricante;
    
    @Column(name = "anoFabricacao")
    private int anoFabricacao;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_codigo", nullable = false)
    private Pessoa pessoa;
    
    public Veiculo() {
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public VeiculoId getCodigo() {
        return codigo;
    }

    public void setCodigo(VeiculoId codigo) {
        this.codigo = codigo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
