package br.edu.ifsp.pep.modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import java.io.Serializable;

/**
 *
 * @author cesar
 */
public class ItemVendaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto_id", nullable = false)
    private int produto;
    @Basic(optional = false)
    @Column(name = "venda_id", nullable = false)
    private int venda;

    public ItemVendaPK() {
    }

    public ItemVendaPK(int produto, int venda) {
        this.produto = produto;
        this.venda = venda;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.produto;
        hash = 53 * hash + this.venda;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemVendaPK other = (ItemVendaPK) obj;
        if (this.produto != other.produto) {
            return false;
        }
        return this.venda == other.venda;
    }

}
