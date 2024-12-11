package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.entity.Pessoa;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named //ManageBean
@SessionScoped //Escopo do tipo Session
public class LoginController implements Serializable{

    @Inject
    private PessoaDAO pessoaDAO;

    private Pessoa pessoa = new Pessoa();

    private Pessoa pessoaAutenticada;
    
    public void autenticar(){
        //Se o login e senha forem válidos
        this.pessoaAutenticada = pessoaDAO.autenticar(pessoa.getLogin(), pessoa.getSenha());
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public Pessoa getPessoaAutenticada() {
        return pessoaAutenticada;
    }

    public void setPessoaAutenticada(Pessoa pessoaAutenticada) {
        this.pessoaAutenticada = pessoaAutenticada;
    }

}
