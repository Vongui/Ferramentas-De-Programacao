package br.edu.ifsp.pep.entity;

import java.util.Objects;

/**
 *
 * @author aluno
 */
public class Produto {

    private Integer codigo;

    private String descricao;

    private Double valor;

    private Integer quantidadeEstoque;

    public Produto() {
    }

    public Produto(Integer codigo, String descricao, Double valor, Integer quantidadeEstoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.codigo);
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
        final Produto other = (Produto) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}
