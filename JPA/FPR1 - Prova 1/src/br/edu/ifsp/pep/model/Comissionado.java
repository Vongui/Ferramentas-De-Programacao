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
@Table(name = "comissionado")
@DiscriminatorValue("Comissionado")
public class Comissionado extends Assalariado {

    @Column(name = "comissao", nullable = false)
    private double comissao;

    public Comissionado() {
    }

    public Comissionado(long codigo, String nome, String login, String senha, double salarioFixo, double comissao) {
        super(codigo, nome, login, senha, salarioFixo);
        this.comissao = comissao;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    @Override
    public String toString() {
        return super.toString() + "comissao=" + comissao;
    }

    
}
