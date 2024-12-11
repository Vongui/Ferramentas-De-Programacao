package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.VeiculoDAO;
import br.edu.ifsp.pep.entity.StatusVeiculo;
import br.edu.ifsp.pep.entity.Veiculo;
import br.edu.ifsp.pep.util.Mensagem;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named //ManageBean
@SessionScoped //Escopo do tipo session
public class VeiculoController implements Serializable {

    @Inject
    private VeiculoDAO veiculoDAO;

    private Veiculo veiculo = new Veiculo();
    private Veiculo veiculoSelecionado;
    private List<Veiculo> veiculos;

    public VeiculoController() {        
    }
    
    public String prepararCadastro() {
        this.veiculo = new Veiculo();
        
        return "/veiculo/CreateVeiculo";
    }

    public void remover() {
        if (veiculoSelecionado != null) {
            veiculoDAO.excluir(veiculoSelecionado);
            veiculos = null;
            
            Mensagem.sucesso("Veículo excluído.");
        } else {
            Mensagem.atencao("Selecione um Veículo.");
        }
    }

    public void adicionar() {
        
        //Insere no BD
        veiculoDAO.inserir(veiculo);
        
        //Para permitir atualizar os dados no BD
        veiculos = null;

        //Criar veículo
        this.veiculo = new Veiculo();
        
        Mensagem.sucesso("Veículo cadastrado.");
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionada) {
        this.veiculoSelecionado = veiculoSelecionada;
    }

    public List<Veiculo> getVeiculos() {
        if (veiculos == null) {
            veiculos = veiculoDAO.buscarTodas();
        }
        return veiculos;
    }

    

}
