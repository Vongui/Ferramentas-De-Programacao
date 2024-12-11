package br.edu.ifsp.pep;

import br.edu.ifsp.pep.model.Assalariado;
import br.edu.ifsp.pep.model.Comissionado;
import br.edu.ifsp.pep.model.Funcionario;
import br.edu.ifsp.pep.model.Gerente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author aluno
 */
public class Principal {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("conexaoPU");

        adicionar();
        reajustarSalario(10);
        listarAssalariados();
        
        listarTodosFuncionarios();
    }

    private static boolean existeLogin(String login) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findByLogin", Funcionario.class);
        query.setParameter("login", login);
        Funcionario funcionario = null;
        try {
            funcionario = query.getSingleResult();
            System.out.println("Já existe um funcionário com o login informado.");
            return true;
        } catch (NoResultException e) {
        }

        System.out.println("Não existe.");
        return false;

    }

    private static void gravar(Funcionario funcionario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    /*Inserir 1 instância para cada classe de modelo. 
    Implementar validação, no banco de dados e na lógica 
    de programação, para não permitir inserir 
    instâncias/registros com o mesmo login*/
    private static void adicionar() {

        Gerente gerente = new Gerente(1, "José", "jose", "123", 10.0, 10000.0);
        if (!existeLogin(gerente.getLogin())) {
            gravar(gerente);
        }

        Assalariado assalariado = new Assalariado(2, "Maria", "maria", "123", 5000.0);
        if (!existeLogin(assalariado.getLogin())) {
            gravar(assalariado);
        }

        Comissionado comissionado = new Comissionado(3, "joao", "joao", "123", 3000.0, 500.0);
        if (!existeLogin(comissionado.getLogin())) {
            gravar(comissionado);
        }

    }

    /*
    Reajustar o salário de todos os funcionários assalariados a partir 
    de uma porcentagem fornecida.
     */
    private static void reajustarSalario(double porcentagem) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        javax.persistence.Query query = em.createNamedQuery("Assalariado.reajustarSalario");
        query.setParameter("porcentagem", porcentagem);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    /*Listar somente os funcionários assalariados que possuem salário 
    maior do que R$ 10.000,00. Não deve listar os funcionários com 
    cargo de gerente e nem os funcionários comissionados.
     */
    private static void listarAssalariados() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Assalariado> query = em.createNamedQuery("Assalariado.findAll", Assalariado.class);
        List<Assalariado> assalariados = query.getResultList();
        for (Assalariado assalariado : assalariados) {
            if (assalariado.getClass() == Assalariado.class) {
                System.out.println(assalariado);
            }
        }
    }
    
    /**
     * Realizar uma única consulta no banco de dados que retorne todas 
     * as instâncias das classes de modelo e exibir no console todas 
     * as informações de todas instâncias armazenadas no banco de dados.
     */
    private static void listarTodosFuncionarios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findAll", Funcionario.class);
        List<Funcionario> funcionarios = query.getResultList();
        System.out.println("--- TODOS OS FUNCIONÁRIOS ---");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }
}
