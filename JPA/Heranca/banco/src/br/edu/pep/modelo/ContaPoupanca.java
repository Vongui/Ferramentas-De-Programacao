
package br.edu.pep.modelo;

import java.util.Date;
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
@Table(name = "conta_poupanca")
@DiscriminatorValue(value = "conta_poupanca")
@NamedQuery(name = "ContaPoupanca.listaPoupancaSaldoInferior", query = "select c from ContaPoupanca c where c.diaAniversario = :diaAniversario")
public class ContaPoupanca extends Conta{
    
    @Column(name = "dia_aniversario")
    private Date diaAniversario;

    public ContaPoupanca() {
    }

    public Date getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(Date diaAniversario) {
        this.diaAniversario = diaAniversario;
    }
    
    
}
