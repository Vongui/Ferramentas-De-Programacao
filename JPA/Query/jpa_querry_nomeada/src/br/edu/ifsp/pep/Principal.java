
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Veiculo;
import br.edu.ifsp.pep.modelo.VeiculoId;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.persistence.TypedQuery;


public class Principal {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexaoPU");
    private static Scanner leia = new Scanner(System.in);
    
    public static void main(String[] args) {
        adicionar();
        //createQuery();
        //buscarAnoFabricacao();
        //String placa = leia.nextLine();
        //buscarPlaca();
        //buscarPlacaCidade();
        //nativeQuery();
        aggregationFunctions();
        contarVeiculoPorCidade();
    }
    
    private static void adicionar(){
        
        VeiculoId vd1 = new VeiculoId();
        vd1.setCidade("SP");
        vd1.setPlaca("KPP4KO4");
        Veiculo v1 = new Veiculo();
        v1.setModelo("UNO");
        v1.setAnoFabricacao(2000);
        v1.setCodigo(vd1);
        
        VeiculoId vd2 = new VeiculoId();
        vd2.setCidade("PE");
        vd2.setPlaca("KJ9IO");
        Veiculo v2 = new Veiculo();
        v2.setModelo("CIVIC");
        v2.setAnoFabricacao(2016);
        v2.setCodigo(vd2);
        
        VeiculoId vd3 = new VeiculoId();
        vd3.setCidade("RJ");
        vd3.setPlaca("JG6JFS");
        Veiculo v3 = new Veiculo();
        v3.setModelo("CHEVET");
        v3.setAnoFabricacao(1990);
        v3.setCodigo(vd3);
        
        VeiculoId vd4 = new VeiculoId();
        vd4.setCidade("RJ");
        vd4.setPlaca("JG9JKS");
        Veiculo v4 = new Veiculo();
        v4.setModelo("MOBI");
        v4.setAnoFabricacao(1989);
        v4.setCodigo(vd4);
        
        VeiculoId vd5 = new VeiculoId();
        vd5.setCidade("RJ");
        vd5.setPlaca("DS43D");
        Veiculo v5 = new Veiculo();
        v5.setModelo("PALIO");
        v5.setAnoFabricacao(2005);
        v5.setCodigo(vd5);
        
        VeiculoId vd6 = new VeiculoId();
        vd6.setCidade("SP");
        vd6.setPlaca("LK89L0");
        Veiculo v6 = new Veiculo();
        v6.setModelo("PORSCHE");
        v6.setAnoFabricacao(2024);
        v6.setCodigo(vd6);
        
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(v1);
        em.persist(v2);
        em.persist(v3);
        em.persist(v4);
        em.persist(v5);
        em.persist(v6);
        
        
        em.getTransaction().commit();
        em.close();
        
    }
    
    private static void createQuery(){
        EntityManager em = emf.createEntityManager();
        
        /*
        A consulta semelhante ao SQL
        
        JPOL -> Baseada na classe(entidade)
        Ex1: select * from veiculo v where = v.anoFabricacao = 2000
        Ex2: from Veiculo v where v.anoFabricacao = 2000
        */
        
        // query tipada
        TypedQuery<Veiculo> query = em.createQuery("select v from Veiculo v where v.anoFabricacao = 2000", Veiculo.class);
        List<Veiculo> carros = query.getResultList();
        
        if (carros != null) {
            for (Veiculo v : carros) {
                System.out.println("query-> Placa: "+v.getCodigo().getPlaca());
            }
        }
        em.close();
    }
    
    private static void buscarAnoFabricacao(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPeloAnoFabricacao", Veiculo.class);
        List<Veiculo> carros = query.getResultList();
        
        if (carros != null) {
            for (Veiculo v : carros) {
                System.out.println("namedQuery-> Placa: "+v.getCodigo().getPlaca());
            }
        }
        em.close();
    }
    
    //Com parametro
    private static void buscarPlaca(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPelaPlaca", Veiculo.class);
        query.setParameter("placa", "KPP4KO4");
        List<Veiculo> carros = query.getResultList();
        
        if (carros != null) {
            for (Veiculo v : carros) {
                System.out.println("namedQueryWithParameter-> Placa: "+v.getCodigo().getPlaca());
            }
        }
        em.close();
    }
    //Dois parametros
    private static void buscarPlacaCidade(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPelaPlacaCidade", Veiculo.class);
        query.setParameter("placa", "KPP4KO4");
        query.setParameter("cidade", "SP");
       
        try{
            Veiculo v = query.getSingleResult();
            System.out.println("namedQueryWithTwoParameter-> Placa: "+v.getCodigo().getPlaca()+" Cidade: "+v.getCodigo().getCidade());
        } catch(NoResultException e){
            System.out.println("Não encontrou o veiculo!!!");
        }
       
        em.close();
    }
    
    private static void nativeQuery(){
        EntityManager em = emf.createEntityManager();
        Query nativeQuery = em.createNativeQuery("select * from veiculo", Veiculo.class);
        
        List<Veiculo> carros = nativeQuery.getResultList();
        for (Object obj : carros) {
            if (obj instanceof Veiculo) {
                Veiculo v = (Veiculo) obj;
                System.out.println("NativeQuery-> Placa: "+v.getCodigo().getPlaca() +" Cidade: "+v.getCodigo().getCidade());
            }
        }
        em.close();
    }
 
    private static void aggregationFunctions(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Long> queryCount = em.createQuery("select count(v) from Veiculo v", Long.class);
        
        Long resultCount = queryCount.getSingleResult();
        System.out.println("Count: " + resultCount);
        
        TypedQuery<Integer> queryMax = em.createQuery("select max(v.anoFabricacao) from Veiculo v", Integer.class);
        Integer resultMax = queryMax.getSingleResult();
        System.out.println("Max: "+resultMax);
        
        TypedQuery<Integer> queryMin = em.createQuery("select MIN(v.anoFabricacao) from Veiculo v", Integer.class);
        Integer resultMin = queryMin.getSingleResult();
        System.out.println("Min: "+resultMin);
        
        TypedQuery<Double> queryAvg = em.createQuery("select AVG(v.anoFabricacao) from Veiculo v", Double.class);
        Double resultAvg = queryAvg.getSingleResult();
        System.out.println("Avg: "+resultAvg);
        
        TypedQuery<Long> querySum = em.createQuery("select SUM(v.anoFabricacao) from Veiculo v", Long.class);
        Long resultSum = querySum.getSingleResult();
        System.out.println("Sum: "+resultSum);
        
        em.close();
    }
    
    private static void contarVeiculoPorCidade(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Object[]> count = em.createQuery("select v.codigo.cidade, count(v), "
                + "Max(v.anoFabricacao), Min(v.anoFabricacao), Avg(v.anoFabricacao), SUM(v.anoFabricacao) "
                + "from Veiculo v "
                + "group by v.codigo.cidade order by v.codigo.cidade", Object[].class);
        
        List<Object[]> result = count.getResultList();
        
        for (Object[] objects : result) {
            System.out.println(objects[0] + ": "+ objects[1] +" " +objects[2] +" "+objects[3]);
        }
        
    }
}