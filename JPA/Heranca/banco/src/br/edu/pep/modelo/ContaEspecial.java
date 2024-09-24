
package br.edu.pep.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;


@Entity
@PrimaryKeyJoinColumns(value = {
    @PrimaryKeyJoinColumn(name = "numero" , referencedColumnName = "numero"),
    @PrimaryKeyJoinColumn(name = "agencia", referencedColumnName = "agencia")
})
@Table(name = "conta_especial")
@DiscriminatorValue(value = "conta_especial")
@NamedQueries(value = {
    @NamedQuery(name = "ContaEspecial.listarEspecialSaldoInferior", query = "select c from ContaEspecial c where c.saldo <= 0.0"),
    @NamedQuery(name = "ContaEspecial.maiorSaldoEspecial", query = "select MAX(c.saldo) from ContaEspecial c")
})
public class ContaEspecial extends Conta{
    
    @Column(name = "limite")
    private double limite;

    public ContaEspecial() {
    }
    
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    
}
