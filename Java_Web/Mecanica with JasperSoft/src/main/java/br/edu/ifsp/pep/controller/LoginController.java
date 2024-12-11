package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.entity.Pessoa;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private PessoaDAO pessoaDAO;

    //Dados do login (formulário)
    private Pessoa pessoa = new Pessoa();
    
    private Pessoa pessoaAutenticada;

    public String prepareLogin() {
        this.pessoaAutenticada = null;        
        this.pessoa = new Pessoa();
        return "/pessoa/Login";
    }
    
    public String autenticar() {        
        this.pessoaAutenticada = pessoaDAO
                .autenticar(pessoa.getLogin(), pessoa.getSenha());
        return "/index";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaAutenticada() {
        return pessoaAutenticada;
    }
    
    

}
