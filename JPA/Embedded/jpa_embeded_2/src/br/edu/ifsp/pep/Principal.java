
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Proprietario;
import br.edu.ifsp.pep.modelo.Veiculo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    
    public static void main(String[] args) {
        adicionar();
        
    }
    
    private static void adicionar(){
        Proprietario proprietario1 = new Proprietario();
        proprietario1.setEmail("email@.com");
        proprietario1.setNome("Claudio");
        proprietario1.setTelefone("(11) 982345769");
        
        Veiculo veiculo1 = new Veiculo();
        veiculo1.setAno(2024);
        veiculo1.setFabricante("Toyota");
        veiculo1.setProprietario(proprietario1);
        
        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAno(1990);
        veiculo2.setFabricante("Fiat");
        veiculo2.setProprietario(proprietario1);
        
        
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(veiculo1);
        em.persist(proprietario1);
        em.persist(proprietario1);
        
        em.getTransaction().commit();
        em.close();
        
    }
}