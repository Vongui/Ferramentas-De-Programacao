package br.edu.ifsp.pep.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "gerente")
@DiscriminatorValue("Gerente")
public class Gerente extends Funcionario {

    @Column(name = "percentual", nullable = false)
    private double percentual;

    @Column(name = "valor_vendas", nullable = false)
    private double valorVendas;

    public Gerente() {
    }

    public Gerente(long codigo, String nome, String login, String senha, double percentual, double valorVendas) {
        super(codigo, nome, login, senha);
        this.percentual = percentual;
        this.valorVendas = valorVendas;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(double valorVendas) {
        this.valorVendas = valorVendas;
    }

    @Override
    public String toString() {
        return super.toString() + " percentual=" + percentual + ", valorVendas=" + valorVendas;
    }
    
    

}
