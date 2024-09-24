package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 15, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "pessoa")
public class Pessoa implements Serializable{
    
    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "nome")
    private String nome;

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
