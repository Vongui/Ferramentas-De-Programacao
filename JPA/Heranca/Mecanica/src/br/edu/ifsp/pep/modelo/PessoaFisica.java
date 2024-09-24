package br.edu.ifsp.pep.modelo;

import javax.persistence.Column;


public class PessoaFisica extends Pessoa{

    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
