package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.TipoVeiculoDAO;
import br.edu.ifsp.pep.entity.TipoVeiculo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TipoVeiculoController implements Serializable {

    @Inject
    private TipoVeiculoDAO tipoVeiculoDAO;

    public TipoVeiculoController() {        
    }
    
    public List<TipoVeiculo> buscarTiposVeiculos() {
        return tipoVeiculoDAO.buscarTodos();
    }    

}
