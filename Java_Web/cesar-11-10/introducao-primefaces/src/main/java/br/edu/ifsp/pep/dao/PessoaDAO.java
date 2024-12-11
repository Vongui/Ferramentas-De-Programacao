package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entity.Pessoa;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class PessoaDAO extends AbstractDAO<Pessoa> {

    public List<Pessoa> buscarTodas() {
        return em.createNamedQuery("Pessoa.buscarTodas", Pessoa.class)
                .getResultList();
    }

}
