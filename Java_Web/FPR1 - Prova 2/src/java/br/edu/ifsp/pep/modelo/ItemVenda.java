package br.edu.ifsp.pep.modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author cesar
 */
@Entity
@Table(name = "item_venda")
@IdClass(ItemVendaPK.class)
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i"),
    @NamedQuery(name = "ItemVenda.findByQuantidade", query = "SELECT i FROM ItemVenda i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemVenda.findByPreco", query = "SELECT i FROM ItemVenda i WHERE i.preco = :preco")})
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
//    @EmbeddedId
//    protected ItemVendaPK itemVendaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco", nullable = false)
    private double preco;
    @Id
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @Id
    @JoinColumn(name = "venda_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(Produto produto, Venda venda) {
        this.produto = produto;
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.produto);
        hash = 97 * hash + Objects.hashCode(this.venda);
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
        final ItemVenda other = (ItemVenda) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return Objects.equals(this.venda, other.venda);
    }

}
