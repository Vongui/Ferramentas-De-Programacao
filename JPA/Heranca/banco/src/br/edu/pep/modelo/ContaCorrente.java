
package br.edu.pep.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumns(value = {
    @PrimaryKeyJoinColumn(name = "numero" , referencedColumnName = "numero"),
    @PrimaryKeyJoinColumn(name = "agencia", referencedColumnName = "agencia")
})
@Table(name = "conta_corrente")
@DiscriminatorValue(value = "conta_corrente")
@NamedQuery(name = "ContaCorrente.somarSaldo", query = "select SUM(c.saldo) from ContaCorrente c")
public class ContaCorrente extends Conta{
    
    @Column(name = "tarifa")
    private double tarifa;

    public ContaCorrente() {
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
    
}
