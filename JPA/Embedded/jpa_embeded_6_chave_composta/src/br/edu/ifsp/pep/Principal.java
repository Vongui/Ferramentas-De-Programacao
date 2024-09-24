
package br.edu.ifsp.pep;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    
    public static void main(String[] args) {
        //adicionar();
        
    }
    
    private static void adicionar(){
        
        
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
       
        
        em.getTransaction().commit();
        em.close();
        
    }
}