package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.modelo.PessoaStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {

    //Cria a fabrica de entidade
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    
    public static void main(String[] args) {
        
        adicionar();
        alterar();
        
        //testePersist();
    }

    private static void adicionar() {
        Pessoa p1 = new Pessoa(1, "122.236.569-4", "Guilherme", PessoaStatus.Ativa, 15000.59, new BigDecimal (200.89), new Date(), LocalDate.now(), LocalDateTime.now());
        Pessoa p2 = new Pessoa(2, "136.548.985-8", "Augusto", PessoaStatus.Inativa, 16000.59, new BigDecimal (200.89), new Date(), LocalDate.now(), LocalDateTime.now());
        
        //Gerencia a entidade(transação, CRUD)
        EntityManager em = emf.createEntityManager();
        
        //Inicia uma transação
        em.getTransaction().begin();
        
        //Gravar o objeto (p1) no banco de dados (INSERT)
        em.persist(p1);
        em.persist(p2);
        
        //Finaliza uma transação
        em.getTransaction().commit();
        em.close();
    }

    private static void alterar() {
        EntityManager em = emf.createEntityManager();
        
        //Alteração
        Pessoa p1 = em.find(Pessoa.class, 1); //Parecido com o select
        p1.setNome("João");
        
        em.getTransaction().begin();
        em.merge(p1); //Altera ou cria no banco de dados
        em.getTransaction().commit();
        
        //Fecha a conexao
        em.close();
    }
    
    /*
    private static void testePersist(){
        Pessoa p = new Pessoa("xxxxx", PessoaStatus.Ativa);
        
        //Gerencia a entidade(transação, CRUD)
        EntityManager em = emf.createEntityManager();
        
        //Inicia uma transação
        em.getTransaction().begin();
        
        //Gravar o objeto (p1) no banco de dados (INSERT)
        //em.persist(p); Erro de chave duplicada
        em.merge(p); //Deve alterar o nome da pessoa com o código 1
        
        //Finaliza uma transação
        em.getTransaction().commit();
        em.close();
    }
    */
}
