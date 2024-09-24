
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Acessorio;
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
        Acessorio roda = new Acessorio();
        roda.setDescricao("Rodas de liga leve");
        Acessorio sensor = new Acessorio();
        sensor.setDescricao("Sensores de estacionamento");
        Acessorio mp3 = new Acessorio();
        mp3.setDescricao("MP3 Player");
        Acessorio pintura = new Acessorio();
        pintura.setDescricao("Pintura metalizada");
        
        Veiculo veiculo1 = new Veiculo();
        veiculo1.setAno(2024);
        veiculo1.setFabricante("Toyota");
        veiculo1.getAcessorios().add(mp3);
        veiculo1.getAcessorios().add(roda);
        veiculo1.getAcessorios().add(pintura);
        
        Veiculo veiculo2 = new Veiculo();
        veiculo2.setAno(1990);
        veiculo2.setFabricante("Fiat");
        veiculo2.getAcessorios().add(sensor);
        veiculo2.getAcessorios().add(pintura);
        
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(veiculo1);
        em.persist(veiculo2);
        em.persist(mp3);
        em.persist(roda);
        em.persist(pintura);
        em.persist(sensor);
        
        em.getTransaction().commit();
        em.close();
        
    }
}