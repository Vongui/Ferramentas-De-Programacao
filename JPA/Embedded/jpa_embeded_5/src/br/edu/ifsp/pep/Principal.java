
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Proprietario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    
    public static void main(String[] args) {
        adicionar();
        
    }
    
    private static void adicionar(){
        Proprietario prop = new Proprietario();
        prop.setNome("Carlos");
        prop.setEmail("carlos@gmail.com");
        prop.getTelefones().add("(34) 1234-5678");
        prop.getTelefones().add("(11) 9876-5432");
        
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(prop);
        
        em.getTransaction().commit();
        em.close();
        
    }
}