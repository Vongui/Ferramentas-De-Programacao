package br.edu.ifsp.pep.modelo;

import javax.persistence.Column;


public class PessoaJuridica extends Pessoa{

    @Column(name = "cnpj", length = 18, nullable = false)
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
