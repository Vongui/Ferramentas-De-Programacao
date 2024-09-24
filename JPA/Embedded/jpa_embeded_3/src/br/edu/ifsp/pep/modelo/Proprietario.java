package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.JoinFetch;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "proprietario")
public class Proprietario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "proprietario")
    private List<Veiculo> veiculos;
    
    public Proprietario() {
        
    }
    
    public Proprietario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

//    public Veiculo getVeiculo() {
//        return veiculo;
//    }
//
//    public void setVeiculo(Veiculo veiculo) {
//        this.veiculo = veiculo;
//    }
    
}
