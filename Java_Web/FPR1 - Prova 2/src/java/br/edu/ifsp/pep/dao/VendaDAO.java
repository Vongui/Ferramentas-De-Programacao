package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.modelo.Venda;
import jakarta.ejb.Stateless;

/**
 *
 * @author cesar
 */
@Stateless
public class VendaDAO extends AbstractDAO<Venda> {

    public VendaDAO() {
        super(Venda.class);
    }

}
