package br.com.alura.financas.jpa.test;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.alura.financas.jpa.model.Conta;
import br.com.alura.financas.jpa.model.Movimentacao;
import br.com.alura.financas.jpa.model.TipoMovimentacao;
import br.com.alura.financas.jpa.util.JPAUtil;

public class TesteJPARelacionamento {

	public static void main(String[] args) {		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		//Conta conta = em.find(Conta.class, 1);
		
		Conta conta = new Conta();
    	conta.setTitular("Adriano");
    	conta.setBanco("Itau");
    	conta.setNumero("67123-8");
    	conta.setAgencia("2000-Paulista");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDescricao("Saque");
		movimentacao.setValor(new BigDecimal(500.52));
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setData(Calendar.getInstance());
		
		em.getTransaction().begin();
		em.persist(conta);
		em.persist(movimentacao);
		em.getTransaction().commit();
		
		em.close();
	}
}
