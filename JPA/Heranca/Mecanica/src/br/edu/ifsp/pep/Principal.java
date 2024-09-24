
package br.edu.ifsp.pep;

import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.modelo.Revisao;
import br.edu.ifsp.pep.modelo.Veiculo;
import br.edu.ifsp.pep.modelo.VeiculoId;
import java.util.Date;
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
        //adicionar();
        adicionarVeiculo(adicionarPessoa("Guilherme"), "KLJ09K", "SP", 2023, "Camaro");
        adicionarRevisao("Troca de óleo", "KLJ09K", "SP", 250.00);
        
        adicionarVeiculo(adicionarPessoa("Zezé"), "LKO5P0", "PE", 2010, "Porsche");
        adicionarRevisao("Alinhamento e balanceamento", "LKO5P0", "PE", 300.0);
        
        adicionarVeiculo(adicionarPessoa("Maria"),"OIJ8P0" , "RJ", 2005, "Civic");
        
    }
    
    private static Pessoa adicionarPessoa(String nome){
        Pessoa p = new Pessoa();
        p.setNome(nome);
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        
        return p;
    }
    
    private static void adicionarVeiculo(Pessoa pessoa, String placa, String cidade, int anoFabricacao, String modelo){
        VeiculoId vd = new VeiculoId();
        Veiculo v = new Veiculo();
        
        vd.setPlaca(placa);
        vd.setCidade(cidade);
        v.setCodigo(vd);
        v.setAnoFabricacao(anoFabricacao);
        v.setModelo(modelo);
        v.setPessoa(pessoa);
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
        em.close();
        
    }
    
    private static void adicionarRevisao(String descricao, String placa, String cidade, Double valor) {
        VeiculoId vd = new VeiculoId();
        vd.setCidade(cidade);
        vd.setPlaca(placa);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Veiculo v = em.find(Veiculo.class, vd);
            if (v != null) {

                Revisao r = new Revisao();
                r.setData(new Date());
                r.setDescricao(descricao);
                r.setValor(valor);
                r.setVeiculo(v);
                em.persist(r);
            }
            else{
                System.out.println("Veiculo com a placa: "+placa+" e cidade: " +cidade+" Não encontrado!!!");
            }
            
            em.getTransaction().commit();
      
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
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
    
    private static void aggregationFunctionPorCidade(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Object[]> count = em.createQuery("select v.codigo.cidade, count(v), "
                + "Max(v.anoFabricacao), Min(v.anoFabricacao), Avg(v.anoFabricacao), SUM(v.anoFabricacao) "
                + "from Veiculo v "
                + "group by v.codigo.cidade order by v.codigo.cidade", Object[].class);
        
        List<Object[]> result = count.getResultList();
        
        for (Object[] objects : result) {
            System.out.println(objects[0] + " Count: "+ objects[1] +" Max: " +objects[2] +" Min: "+objects[3]
            + " Avg: " + objects[4] + " Sum: " + objects[5]);
        }
       em.close();
    }
    
    private static void join(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo v JOIN v.pessoa p WHERE p.codigo = :codigo", Veiculo.class);
        query.setParameter("codigo", 1);
        
        List<Veiculo> veiculos = query.getResultList();
        for (Veiculo veiculo : veiculos) {
            System.out.println("Pessoa: "+veiculo.getPessoa().getNome()+"\nPlaca: "+veiculo.getCodigo().getPlaca()+"\n");
        }
        
        em.close();
    }
    
    //JOIN FETCH ->  é mais eficiente que o join normal
    private static void joinFetch(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo v JOIN FETCH v.pessoa p WHERE p.codigo = :codigo", Veiculo.class);
        query.setParameter("codigo", 1);
        
        List<Veiculo> veiculos = query.getResultList();
        for (Veiculo veiculo : veiculos) {
            System.out.println("Pessoa: "+veiculo.getPessoa().getNome()+"\nPlaca: "+veiculo.getCodigo().getPlaca()+"\n");
        }
        
        em.close();
    }
    
}


