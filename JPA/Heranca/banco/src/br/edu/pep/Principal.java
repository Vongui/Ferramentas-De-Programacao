
package br.edu.pep;

import br.edu.pep.modelo.Cliente;
import br.edu.pep.modelo.Conta;
import br.edu.pep.modelo.ContaCorrente;
import br.edu.pep.modelo.ContaEspecial;
import br.edu.pep.modelo.ContaId;
import br.edu.pep.modelo.ContaPoupanca;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Principal {
 
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
    
    public static void main(String[] args) {
        
        // Adicionando clientes
        Cliente cliente1 = adicionarCliente("Guilherme", "guilherme@gmail.com");
        Cliente cliente2 = adicionarCliente("Ana", "ana@gmail.com");
        Cliente cliente3 = adicionarCliente("Carlos", "carlos@gmail.com");
        
        // Adicionando contas para o cliente 1
        adicionarContaPoupanca(cliente1, 101, 1, 0.0);
        adicionarContaPoupanca(cliente1, 101, 2, 1500.0);
        adicionarContaPoupanca(cliente1, 101, 3, 2000.0);
        
        adicionarContaCorrente(cliente1, 101, 4, 500.0, 10.0);
        adicionarContaCorrente(cliente1, 101, 5, 600.0, 15.0);
        adicionarContaCorrente(cliente1, 101, 6, 700.0, 20.0);
        
        adicionarContaEspecial(cliente1, 101, 7, 3000.0);
        adicionarContaEspecial(cliente1, 101, 8, 3500.0);
        adicionarContaEspecial(cliente1, 101, 9, 4000.0);
        
        // Adicionando contas para o cliente 2
        adicionarContaPoupanca(cliente2, 102, 10, 1200.0);
        adicionarContaPoupanca(cliente2, 102, 11, 1800.0);
        adicionarContaPoupanca(cliente2, 102, 12, 2500.0);
        
        adicionarContaCorrente(cliente2, 102, 13, 800.0, 12.0);
        adicionarContaCorrente(cliente2, 102, 14, 900.0, 18.0);
        adicionarContaCorrente(cliente2, 102, 15, 1000.0, 22.0);
        
        adicionarContaEspecial(cliente2, 102, 16, 3500.0);
        adicionarContaEspecial(cliente2, 102, 17, 4000.0);
        adicionarContaEspecial(cliente2, 102, 18, 4500.0);
        
        // Adicionando contas para o cliente 3
        adicionarContaPoupanca(cliente3, 103, 19, 1100.0);
        adicionarContaPoupanca(cliente3, 103, 20, 0.0);
        adicionarContaPoupanca(cliente3, 103, 21, 2100.0);
        
        adicionarContaCorrente(cliente3, 103, 22, 550.0, 11.0);
        adicionarContaCorrente(cliente3, 103, 23, 650.0, 16.0);
        adicionarContaCorrente(cliente3, 103, 24, 750.0, 21.0);
        
        adicionarContaEspecial(cliente3, 103, 25, 3200.0);
        adicionarContaEspecial(cliente3, 103, 26, 3700.0);
        adicionarContaEspecial(cliente3, 103, 27, 0.0);
        
        listarContaSaldoInferior();
        listarContaEspecialSaldoInferior();
        listarMaiorSaldoContaEspecial();
        listarContaPoupanca(procuraData(20, 103));
        listarSomaSaldoContaCorrente();
    }
    
    private static Date procuraData(int numero, int agencia){
        ContaId ci = new ContaId();
        ci.setAgencia(agencia);
        ci.setNumero(numero);
        EntityManager em = emf.createEntityManager();
        
        ContaPoupanca cp = em.find(ContaPoupanca.class, ci);
        if (cp != null) {
            em.close();
            System.out.println(cp.getDiaAniversario());
            return cp.getDiaAniversario();
        }
        System.out.println("Conta não existente!!!");
        em.close();
        return null;
    }
    
    private static void adicionarContaPoupanca(Cliente c, int agencia, int numero, double saldo){
        EntityManager em = emf.createEntityManager();
        
        ContaId ci = new ContaId();
        ci.setAgencia(agencia);
        ci.setNumero(numero);
        
        ContaPoupanca cp = new ContaPoupanca();
        cp.setCodigo(ci);
        cp.setSaldo(saldo);
        cp.setDiaAniversario(new Date());
        cp.setCliente(c);
        
        em.getTransaction().begin();
        em.persist(cp);
        em.getTransaction().commit();
        em.close();
        
    }
    
    private static void adicionarContaEspecial(Cliente c, int agencia, int numero, double saldo){
        EntityManager em = emf.createEntityManager();
        
        ContaId ci = new ContaId();
        ci.setAgencia(agencia);
        ci.setNumero(numero);
        
        ContaEspecial ce = new ContaEspecial();
        ce.setCodigo(ci);
        ce.setSaldo(saldo);
        ce.setLimite(500);
        ce.setCliente(c);
        
        em.getTransaction().begin();
        em.persist(ce);
        em.getTransaction().commit();
        em.close();
    }
    
    private static void adicionarContaCorrente(Cliente c, int agencia, int numero, double saldo ,double tarifa){
        EntityManager em = emf.createEntityManager();
        
        ContaId ci = new ContaId();
        ci.setAgencia(agencia);
        ci.setNumero(numero);
        
        ContaCorrente cc = new ContaCorrente();
        cc.setCodigo(ci);
        cc.setSaldo(saldo);
        cc.setTarifa(tarifa);
        cc.setCliente(c);
        
        em.getTransaction().begin();
        em.persist(cc);
        em.getTransaction().commit();
        em.close();
        
    }
    
    private static Cliente adicionarCliente(String nome, String email){
        EntityManager em = emf.createEntityManager();
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        
        return c;
    }
    
    
    private static void listarContaSaldoInferior(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Conta> query = em.createNamedQuery("Conta.listarSaldoInferior", Conta.class);
        List<Conta> contas = query.getResultList();
        
        System.out.println("Contas com Saldo inferior ou igual a 0.0");
        System.out.println("========================================");
        if (contas != null) {
            for (Conta conta : contas) {
                System.out.println("Cliente:"+conta.getCliente().getNome()+" Tipo:" +conta.getClass().getSimpleName()
                        +" agencia:"+conta.getCodigo().getAgencia()
                        +" numero:"+conta.getCodigo().getNumero());
            }
        }
        System.out.println("========================================");
        em.close();
    }
    
    private static void listarContaEspecialSaldoInferior(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<ContaEspecial> query = em.createNamedQuery("ContaEspecial.listarEspecialSaldoInferior", ContaEspecial.class);
        List<ContaEspecial> contas = query.getResultList();
        
        System.out.println("Contas Especiais com Saldo inferior ou igual a 0.0");
        System.out.println("========================================");
        if (contas != null) {
            for (Conta conta : contas) {
                System.out.println("Cliente:"+conta.getCliente().getNome()+" Tipo:" +conta.getClass().getSimpleName()
                        +" agencia:"+conta.getCodigo().getAgencia()+
                        " numero:"+conta.getCodigo().getNumero());
            }
        }
        System.out.println("========================================");
        em.close();
    }
    
    private static void listarMaiorSaldoContaEspecial(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Double> query = em.createNamedQuery("ContaEspecial.maiorSaldoEspecial", Double.class);
        Double ce = query.getSingleResult();
        
        System.out.println("Maior Saldo Conta Especial");
        System.out.println("========================================");
        if (ce != null) {
            System.out.println("Saldo: "+ ce);
        }
        System.out.println("========================================");
        em.close();
    }
    
    private static void listarContaPoupanca(Date dia){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<ContaPoupanca> query = em.createNamedQuery("ContaPoupanca.listaPoupancaSaldoInferior", ContaPoupanca.class);
        query.setParameter("diaAniversario", dia);
        List<ContaPoupanca> contas = query.getResultList();
        
        System.out.println("Contas Poupanca pela data");
        System.out.println("========================================");
        if (contas != null) {
            for (Conta conta : contas) {
                System.out.println("Cliente:"+conta.getCliente().getNome()+" Tipo:" +conta.getClass().getSimpleName()
                        +" agencia:"+conta.getCodigo().getAgencia()+
                        " numero:"+conta.getCodigo().getNumero());
            }
        }
        System.out.println("========================================");
        em.close();
    }
    
    private static void listarSomaSaldoContaCorrente(){
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Double> query = em.createNamedQuery("ContaCorrente.somarSaldo", Double.class);
        Double sum = query.getSingleResult();
        
        System.out.println("Soma Saldo Conta Corrente");
        System.out.println("========================================");
        if (sum != null) {
                System.out.println("Soma dos Saldos: "+sum);
        }
        System.out.println("========================================");
        em.close();
    }
    
    
}
