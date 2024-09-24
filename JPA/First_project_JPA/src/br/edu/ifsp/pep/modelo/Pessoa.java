package br.edu.ifsp.pep.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity // se a classe é entidade, é uma classe que pode ser persistida, ou seja, pode ser manuseada no banco de dados
 //anotação dizendo que tem uma tabela, senão colocar nome, é dado um nome aleatório
@Table(name = "pessoa", uniqueConstraints = {//UniqueConstraints um conjunto de regras em que os atributos listados não podem se repetir
    @UniqueConstraint(name = "unique_cpf_nome", columnNames = {"cpf", "nome"}),
    @UniqueConstraint(name = "unique_cpf_status", columnNames = {"cpf", "status"})
})//UniqueConstraint -> declara o nome do "grupo", e quais são são atributos
public class Pessoa implements Serializable {
    
    @Id //Diz que o atributo logo abaixo é a chave primaria
    @Column(name = "codigo") //Nomeia a coluna
    //Não declarar a anotação abaixo se usar sem auto increment
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private int codigo;
    // length -> quantidade de caracteres
    // nullable -> se o atributo pode ser null na tabela
    
    @Column(name = "cpf", length = 14, nullable = true)
    private String cpf;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    //enum
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 7, nullable = true)
    private PessoaStatus status;
    
    //numeros decimais
    @Column(name = "salario")
    private double salario;
    
    //precision -> quantas casas vai ter no total
    //scale -> quantas casas vão ser decimais
    @Column(name = "vale_transporte", nullable = true, scale = 2, precision = 8)
    private BigDecimal valeTransporte;
    
    //Date
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    
    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;
    
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    //Boolean
    @Column(name = "administrador")
    private boolean administrador;
    
    @Column(name = "financeiro")
    private boolean financeiro;
    
    //Char
    @Column(name = "tipo1", length = 5)
    private char tipo1;
    
    @Column(name ="tipo2", length = 3)
    private Character tipo2;
    
    @Lob //Texto longo
    @Column(name = "bibliografia")
    private String bibliografia;
    
    //fotos -> byte
    //columnDefinition -> defini qual tipo de dado vai ser utilizado no no banco de dados
    @Lob
    @Column(name = "foto", columnDefinition = "MEDIUMBLOB") 
    private byte[] foto;
    
    // Para a classe ser considerada uma entidade é necessário
    // um construtor sem parametro
    public Pessoa() {
        
    }

    public Pessoa(int codigo, String nome, PessoaStatus status) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
    }
    
    public Pessoa(int codigo, String cpf, String nome, PessoaStatus status, double salario, BigDecimal valeTransporte, Date dataNascimento, LocalDate dataAlteracao, LocalDateTime dataCadastro) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.nome = nome;
        this.status = status;
        this.salario = salario;
        this.valeTransporte = valeTransporte;
        this.dataNascimento = dataNascimento;
        this.dataAlteracao = dataAlteracao;
        this.dataCadastro = dataCadastro;
    }    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public PessoaStatus getStatus() {
        return status;
    }

    public void setStatus(PessoaStatus status) {
        this.status = status;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public BigDecimal getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(BigDecimal valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(boolean financeiro) {
        this.financeiro = financeiro;
    }

    public char getTipo1() {
        return tipo1;
    }

    public void setTipo1(char tipo1) {
        this.tipo1 = tipo1;
    }

    public Character getTipo2() {
        return tipo2;
    }

    public void setTipo2(Character tipo2) {
        this.tipo2 = tipo2;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
 
    
}
