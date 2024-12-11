/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "veiculo")
@NamedQueries({
    @NamedQuery(name = "Veiculo.buscarTodos", query = "FROM Veiculo v")
})
public class Veiculo {

    @Id
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome", length = 25, nullable = false)
    private String nome;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "placa", length = 9, nullable = false)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private StatusVeiculo status;

    @Column(name = "valor_aluguel", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorAluguel;

    public Veiculo() {
        this.status = StatusVeiculo.Disponivel;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

}
