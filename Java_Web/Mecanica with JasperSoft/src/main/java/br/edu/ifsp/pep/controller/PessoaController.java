package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.entity.Pessoa;
import br.edu.ifsp.pep.util.Mensagem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named //ManageBean
//@RequestScoped //Escopo do tipo Request
@ViewScoped //Escopo do tipo View
//@SessionScoped //Escopo do tipo session (para cada cliente)
//@ApplicationScoped //Escopo único para a aplicação
public class PessoaController implements Serializable {

    @Inject
    private PessoaDAO pessoaDAO;

    private Pessoa pessoa = new Pessoa();
    private Pessoa pessoaSelecionada;
    private List<Pessoa> pessoas;

    public PessoaController() {
        System.out.println("Construtor Pessoa Controller.");
    }

    public void exibir() {
        System.out.println("Método exibir");

        if (pessoaSelecionada != null) {
            System.out.println(pessoaSelecionada.getNome());
        }

//        for (Pessoa p : pessoas) {
//            System.out.println("Nome: " + p.getNome());
//        }
    }

    public void remover() {
        if (pessoaSelecionada != null) {
            System.out.println("Removendo pessoa selecionada.");
            pessoaDAO.excluir(pessoaSelecionada);
            pessoas = null;
            
            Mensagem.sucesso("Pessoa excluída.");
        } else {
            Mensagem.atencao("Selecione uma Pessoa.");
        }
    }

    public void adicionar() {
        System.out.println("Método adicionar");

        //Insere no BD
        pessoaDAO.inserir(pessoa);
        
        //Para permitir atualizar os dados no BD
        pessoas = null;

        //Criar pessoa
        this.pessoa = new Pessoa();
        
        Mensagem.sucesso("Pessoa cadastrada.");
        
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public List<Pessoa> getPessoas() {
        if (pessoas == null) {
            System.out.println("Buscando as pessoas no DB");
            pessoas = pessoaDAO.buscarTodas();
        }
        return pessoas;
    }
    
    public boolean exibirItemMenu() {
        return true;
    }

}
