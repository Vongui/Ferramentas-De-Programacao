package br.edu.ifsp.pep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.buscarTodas", query = "FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.autenticar", query = "SELECT p FROM Pessoa p where p.login = :login AND p.senha = :senha")
})
public class Pessoa implements Serializable {
    
    @Id
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "login", length = 20, nullable = false)
    private String login;
    
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
