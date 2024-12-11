/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author aluno
 */
//Generics
public abstract class AbstractDAO<T> {

    @PersistenceContext(unitName = "conexaoPU")
    protected EntityManager em;

    public void inserir(T entity) {
        em.persist(entity);
    }

    public void excluir(T entity) {
        em.remove(em.merge(entity));
    }

    public void alterar(T entity) {
        em.merge(entity);
    }

}
