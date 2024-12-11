package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entity.Veiculo;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author aluno
 */
@Stateless
public class VeiculoDAO extends AbstractDAO<Veiculo> {
    
     public List<Veiculo> buscarTodas() {
        return em.createNamedQuery("Veiculo.buscarTodos", Veiculo.class)
                .getResultList();
    }

    
}
