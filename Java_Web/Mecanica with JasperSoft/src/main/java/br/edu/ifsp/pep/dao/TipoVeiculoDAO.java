package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entity.TipoVeiculo;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author aluno
 */
@Stateless
public class TipoVeiculoDAO extends AbstractDAO<TipoVeiculo> {

    public List<TipoVeiculo> buscarTodos() {
        return em.createNamedQuery("TipoVeiculo.buscarTodos", TipoVeiculo.class)
                .getResultList();
    }

    public TipoVeiculo buscarPeloCodigo(Long codigo) {
        System.out.println("Código: " + codigo);
        return em.find(TipoVeiculo.class, codigo);
    }

}
