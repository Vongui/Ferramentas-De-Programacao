
package br.edu.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Conta.listarSaldoInferior", query = "select c from Conta c where c.saldo <= 0.0")
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public class Conta implements Serializable{
    
    @EmbeddedId
    @Column(name = "codigo")
    private ContaId codigo;
    
    @Column(name = "saldo")
    private double saldo;
    
    @ManyToOne
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private Cliente cliente;

    public ContaId getCodigo() {
        return codigo;
    }

    public void setCodigo(ContaId codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
