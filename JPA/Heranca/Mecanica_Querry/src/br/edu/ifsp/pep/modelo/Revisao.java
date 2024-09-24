package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "revisao")
public class Revisao implements Serializable {
    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "data")
    private LocalDate data;
    
    @Column(name = "valor")
    private double valor;
    
    @Lob
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "veiculo_placa", referencedColumnName = "placa", nullable = false),
        @JoinColumn(name = "veiculo_cidade", referencedColumnName = "cidade", nullable = false)
    })
    private Veiculo veiculo;

    public Revisao() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
