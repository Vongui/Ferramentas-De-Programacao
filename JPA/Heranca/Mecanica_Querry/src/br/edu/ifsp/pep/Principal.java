package br.edu.ifsp.pep;

//import br.edu.ifsp.pep.modelo.Proprietario;
import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.modelo.Revisao;
import br.edu.ifsp.pep.modelo.Veiculo;
import br.edu.ifsp.pep.modelo.VeiculoId;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Principal {

    // O cara que permite fazer as coisas
    private static EntityManagerFactory enf = Persistence.createEntityManagerFactory("conexaoPU");

    public static void main(String[] args) {
        Pessoa p = adicionarPessoa("Biel");
        
        Veiculo v1 = adicionarVeiculo("KIA", 2000, "GEI-8542", "Santos", p);
        Veiculo v2 = adicionarVeiculo("FIAT", 2010, "ABC-1234", "Guaruja", p);
        Veiculo v3 = adicionarVeiculo("Nissan", 2015, "CDE-4315", "Presidente Epitacio", p);
        Veiculo v4 = adicionarVeiculo("Ford", 2000, "FDW-9457", "Guarujá", p);
        Veiculo v5 = adicionarVeiculo("Chevrolet", 2012, "GEN-8922", "Santos", p);
        
        adicionarRevisao(LocalDate.of(2022, 1, 1), 100.0, "Revisão de rotina", v1);
        adicionarRevisao(LocalDate.of(2022, 3, 15), 200.0, "Troca de óleo", v3);
        adicionarRevisao(LocalDate.of(2022, 6, 20), 150.0, "Revisão de freios", v4);
        adicionarRevisao(LocalDate.of(2022, 9, 1), 120.0, "Revisão de suspensão", v5);

//        QueryNormal();
//        QueryComNome();
//        QueryComParametro("GEI-8542");
//        QueryCom2Parametros("ABC-1234", "Guaruja");
//        createNativeQuery();
//        aggregationFunctions();
//        aggregationFunctionsPorCidade();
        joinVeiculoPessoa();
    }

    private static void adicionarRevisao(LocalDate data, double valor, String descricao, Veiculo v){
        Revisao r = new Revisao();
        r.setData(data);
        r.setValor(valor);
        r.setDescricao(descricao);
        r.setVeiculo(v);

        EntityManager em = enf.createEntityManager();

        em.getTransaction().begin();
        em.persist(r);

        em.getTransaction().commit();
        em.close();
    }
    
    private static Pessoa adicionarPessoa(String nome){
        EntityManager em = enf.createEntityManager();
        Pessoa p = new Pessoa(nome);
        
        em.getTransaction().begin();
        em.persist(p);

        em.getTransaction().commit();
        em.close();
        
        return p;
    }

    private static Veiculo adicionarVeiculo(String fabricante, int anoFabricacao, String placa, String cidade, Pessoa p) {
        Veiculo veiculo = new Veiculo();
        veiculo.setAnoFabricacao(anoFabricacao);
        veiculo.setFabricante(fabricante);
        VeiculoId vId = new VeiculoId(placa, cidade);
        veiculo.setCodigo(vId);
        veiculo.setPessoa(p);

        EntityManager em = enf.createEntityManager();

        em.getTransaction().begin();
        em.persist(veiculo);

        em.getTransaction().commit();
        em.close();
        
        return veiculo;
    }

    private static void QueryNormal() {
        EntityManager em = enf.createEntityManager();

        // A consulta é semelhante ao SQL (tabelas)
        // JPQL - baseado na classe (entidade)
        // Ex1: Select v from Veiculo v where v.anoFabricacao = 2000;
        // Ex2: from Veiculo v where v.anoFabricacao = 2000; -> é igual ao de cima
        
        //Querry com tipo
        TypedQuery<Veiculo> query = em.createQuery("Select v from Veiculo v where v.anoFabricacao = 2000", Veiculo.class);
        List<Veiculo> resultList = query.getResultList();

        System.out.println("\nQuery Normal: ");
        if (resultList != null) {
            for (Veiculo v : resultList) {
                System.out.println("Placa: " + v.getCodigo().getPlaca());
            }
        }
        em.close();
    }

    private static void QueryComNome() {
        EntityManager em = enf.createEntityManager();

        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPeloAno", Veiculo.class);
        List<Veiculo> resultList = query.getResultList();
        
        System.out.println("\nNamed Query: ");
        if (resultList != null) {
            for (Veiculo v : resultList) {
                System.out.println("Placa: " + v.getCodigo().getPlaca());
            }
        }
        em.close();
    }

    private static void QueryComParametro(String placa) {
        EntityManager em = enf.createEntityManager();

        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPelaPlaca", Veiculo.class);
        query.setParameter("placa", placa);
        List<Veiculo> resultList = query.getResultList();
        
        System.out.println("\nQuery com Parametro: ");
        if (resultList != null) {
            for (Veiculo v : resultList) {
                System.out.println("Ano: " + v.getFabricante());
            }
        }
        em.close();
    }

    private static void QueryCom2Parametros(String placa, String cidade) {
        EntityManager em = enf.createEntityManager();

        TypedQuery<Veiculo> query = em.createNamedQuery("Veiculo.buscarPelaPlacaECidade", Veiculo.class);
        query.setParameter("placa", placa);
        query.setParameter("cidade", cidade);

        try {
            Veiculo v = query.getSingleResult();
            System.out.println("\nQuery com 2 parametros: \nFabricante:" + v.getFabricante());
        } catch (NoResultException e) {
            System.out.println("\nQuery com 2 parametros: \nNão encontrou o veiculo. ");
        }
        em.close();
    }
    
    //Querry com a linguagem do SQL mesmo, com o nome das tabelas e tudo
    private static void createNativeQuery() {
        EntityManager em = enf.createEntityManager();
        Query nativeQuery = em.createNativeQuery("Select * from veiculo", Veiculo.class);
        
        System.out.println("\nNative Query: ");
        List veiculos = nativeQuery.getResultList();
        for (Object obj : veiculos) {
            if (obj instanceof Veiculo) {
                Veiculo v = (Veiculo) obj;
                System.out.println("Placa: " + v.getCodigo().getPlaca());
            }
        }
        
        em.close();
    }
    
    private static void aggregationFunctions(){
        EntityManager em = enf.createEntityManager();
        
        System.out.println("\nAggregation Functions: ");
        
        TypedQuery<Long> queryCount = em.createQuery("SELECT COUNT(v) FROM Veiculo v", Long.class);
        Long result = queryCount.getSingleResult();
        System.out.println("Count: " + result);
        
        //max
        TypedQuery<Integer> maxAno = em.createQuery("SELECT MAX(v.anoFabricacao) FROM Veiculo v", Integer.class);
        Integer resultMaxAno = maxAno.getSingleResult();
        System.out.println("Max: " + resultMaxAno);
        
        //max
        TypedQuery<Integer> minAno = em.createQuery("SELECT MIN(v.anoFabricacao) FROM Veiculo v", Integer.class);
        Integer resultMinAno = minAno.getSingleResult();
        System.out.println("Min: " + resultMinAno);
        
        //avg
        TypedQuery<Double> avgAno = em.createQuery("SELECT AVG(v.anoFabricacao) FROM Veiculo v", Double.class);
        Double resultAvgAno = avgAno.getSingleResult();
        System.out.println("AVG: " + resultAvgAno);
        
        //sum
        TypedQuery<Long> sumAno = em.createQuery("SELECT SUM(v.anoFabricacao) FROM Veiculo v", Long.class);
        Long resultSumAno = sumAno.getSingleResult();
        System.out.println("Sum: " + resultSumAno);
    }
    
    private static void aggregationFunctionsPorCidade(){
        EntityManager em = enf.createEntityManager();
        
        System.out.println("\nAggregation Functions por Cidade: ");
        TypedQuery<Object[]> carros = em.createQuery("SELECT v.codigo.cidade, COUNT(v), MAX(v.anoFabricacao), "
                + "MIN(v.anoFabricacao), SUM(v.anoFabricacao), AVG(v.anoFabricacao) "
                + "FROM Veiculo v "
                + "GROUP BY v.codigo.cidade "
                + "ORDER BY v.codigo.cidade", Object[].class);
        List<Object[]> result = carros.getResultList();
        
//        Gambiarra        
//        TypedQuery<String> cidade = em.createQuery("SELECT DISTINCT v.codigo.cidade FROM Veiculo v ORDER BY v.codigo.cidade", String.class);
//        List<String> resultCidade = cidade.getResultList();
        
        for (Object[] obj : result) {
            System.out.println("\nCidade: " + obj[0]);
            System.out.println("Quantidade de carros: " + obj[1]);
            System.out.println("Carro mais novo: " + obj[2]);
            System.out.println("Carro mais velho: " + obj[3]);
            System.out.println("Soma dos anoFabricacaos: " + obj[4]);
            System.out.println("Media de anoFabricacaos: " + obj[5]);
        }
    }
    
    // JOIN FETCH = é melhor do que o JOIN normal, só adicionar o FETCH do lado
    private static void joinVeiculoPessoa(){
        EntityManager em = enf.createEntityManager();
        
        // "FROM Veiculo v JOIN v.pessoa p WHERE p.codigo = :codigo"
        
        TypedQuery<Veiculo> query = em.createQuery(
                "FROM Veiculo v JOIN FETCH v.pessoa p WHERE p.codigo = :codigo",
                Veiculo.class
        );
        query.setParameter("codigo", 1);
        
        System.out.println("JoinVeiculoPessoa: ");
        
        List<Veiculo> veiculos = query.getResultList();
        for (Veiculo veiculo : veiculos) {
            System.out.println("\nPlaca: " + veiculo.getCodigo().getPlaca());
            System.out.println("Proprietario: " + veiculo.getPessoa().getNome());
        }
        
        em.close();
    }
}
