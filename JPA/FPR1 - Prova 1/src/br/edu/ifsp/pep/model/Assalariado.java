package br.edu.ifsp.pep.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "assalariado")
@DiscriminatorValue("Assalariado")
@NamedQueries(value = {
    @NamedQuery(name = "Assalariado.reajustarSalario",
            query = "UPDATE Assalariado a SET a.salarioFixo = a.salarioFixo + a.salarioFixo * :porcentagem / 100"),
    @NamedQuery(name = "Assalariado.findAll",
            query = "FROM Assalariado a")
})

public class Assalariado extends Funcionario {

    @Column(name = "salario_fixo", length = 50, nullable = false)
    private double salarioFixo;

    public Assalariado() {
    }

    public Assalariado(long codigo, String nome, String login, String senha, double salarioFixo) {
        super(codigo, nome, login, senha);
        this.salarioFixo = salarioFixo;
    }

    public double getSalarioFixo() {
        return salarioFixo;
    }

    public void setSalarioFixo(double salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    @Override
    public String toString() {
        return super.toString() + " salarioFixo=" + salarioFixo;
    }

}
