
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.modelo.Revisao;
import br.edu.ifsp.pep.modelo.Veiculo;
import br.edu.ifsp.pep.modelo.VeiculoId;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    
    public static void main(String[] args) {
        adicionar();
        
        
    }
    
    
    private static void adicionar(){
        
        Pessoa p1 = new Pessoa();
        p1.setNome("Zezé");
        
        VeiculoId vd1 = new VeiculoId();
        vd1.setCidade("SP");
        vd1.setPlaca("KPP4KO4");
        Veiculo v1 = new Veiculo();
        v1.setModelo("UNO");
        v1.setAnoFabricacao(2000);
        v1.setCodigo(vd1);
        v1.setPessoa(p1);
        
        VeiculoId vd2 = new VeiculoId();
        vd2.setCidade("PE");
        vd2.setPlaca("KJ9IO");
        Veiculo v2 = new Veiculo();
        v2.setModelo("CIVIC");
        v2.setAnoFabricacao(2016);
        v2.setCodigo(vd2);
        v2.setPessoa(p1);
        
        VeiculoId vd3 = new VeiculoId();
        vd3.setCidade("RJ");
        vd3.setPlaca("JG6JFS");
        Veiculo v3 = new Veiculo();
        v3.setModelo("CHEVET");
        v3.setAnoFabricacao(1990);
        v3.setCodigo(vd3);
        v3.setPessoa(p1);
        
        VeiculoId vd4 = new VeiculoId();
        vd4.setCidade("RJ");
        vd4.setPlaca("JG9JKS");
        Veiculo v4 = new Veiculo();
        v4.setModelo("MOBI");
        v4.setAnoFabricacao(1989);
        v4.setCodigo(vd4);
        v4.setPessoa(p1);
        
        VeiculoId vd5 = new VeiculoId();
        vd5.setCidade("RJ");
        vd5.setPlaca("DS43D");
        Veiculo v5 = new Veiculo();
        v5.setModelo("PALIO");
        v5.setAnoFabricacao(2005);
        v5.setCodigo(vd5);
        v5.setPessoa(p1);
        
        
        Revisao r1 = new Revisao();
        r1.setData(new Date());
        r1.setDescricao("Retificar Motor");
        r1.setValor(12000.00);
        r1.setVeiculo(v1);
        
        Revisao r2 = new Revisao();
        r2.setData(new Date());
        r2.setDescricao("Troca de óleo");
        r2.setValor(150.00);
        r2.setVeiculo(v2);

        Revisao r3 = new Revisao();
        r3.setData(new Date());
        r3.setDescricao("Alinhamento e balanceamento");
        r3.setValor(300.00);
        r3.setVeiculo(v3);

        Revisao r4 = new Revisao();
        r4.setData(new Date());
        r4.setDescricao("Substituição de pastilhas de freio");
        r4.setValor(400.00);
        r4.setVeiculo(v4);

        Revisao r5 = new Revisao();
        r5.setData(new Date());
        r5.setDescricao("Revisão geral");
        r5.setValor(800.00);
        r5.setVeiculo(v5);
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(v1);
        em.persist(v2);
        em.persist(v3);
        em.persist(v4);
        em.persist(v5);
        em.persist(p1);
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.persist(r5);
        
        
        em.getTransaction().commit();
        em.close();
        
    }
    
}


