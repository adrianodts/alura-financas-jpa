package br.com.alura.financas.jpa;

import javax.persistence.EntityManager;

import br.com.alura.financas.jpa.model.Conta;
import br.com.alura.financas.jpa.util.JPAUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	//TRANSIENT
    	Conta conta = new Conta();
    	conta.setTitular("Teste Update");
    	conta.setBanco("Bradesco");
    	conta.setNumero("67123-8");
    	conta.setAgencia("2000-Paulista");
    	
    	
    	EntityManager em = new JPAUtil().getEntityManager();
    	
    	em.getTransaction().begin();
    	//INSERT //MANAGED
		em.persist(conta); 
		
		//UPDATE
		conta.setAgencia("341-Itau");
		
		em.getTransaction().commit();
		
		//Neste ponto a conta se torna unmanaged
    	em.close();
    	
    	
    	EntityManager em1 = new JPAUtil().getEntityManager();
    	
    	//Neste ponto a conta se torna managed
    	conta = em1.find(Conta.class, 1);
    	
    	em1.getTransaction().begin();
    	conta.setTitular("Teste Managed");
    	em1.getTransaction().commit();
    	
    	em1.close();
    	
    	//Detached Test
    	EntityManager em2 = new JPAUtil().getEntityManager();
    	    	
    	em2.getTransaction().begin();
    	conta.setTitular("Teste Detached");
    	//Neste ponto a conta se torna managed por conta do metodo merge()
    	em2.merge(conta);
    	em2.getTransaction().commit();
    	
    	em2.close();
    	
    	//Teste exclusão
    	EntityManager em3 = new JPAUtil().getEntityManager();
    	conta = em3.find(Conta.class, 1);
    	
    	em3.getTransaction().begin();
    	em3.remove(conta);
    	em3.getTransaction().commit();
    	
    	em3.close();
    }
}
