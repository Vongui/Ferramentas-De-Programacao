package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entity.TipoVeiculo;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class TipoVeiculoDAO extends AbstractDAO<TipoVeiculo>{

    public List<TipoVeiculo> buscarTiposVeiculos(){
        return em.createNamedQuery("TipoVeiculo.buscarTipoVeiculo", TipoVeiculo.class)
                .getResultList();
    }
    
    public TipoVeiculo buscarPeloCodigo(long codigo){
        return em.find(TipoVeiculo.class, codigo);
    }
}
