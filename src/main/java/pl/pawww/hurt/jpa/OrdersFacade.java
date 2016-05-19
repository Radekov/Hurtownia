/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author r
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "pl.pawww_Hurt_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public List<Orders> findByRealized() {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findByRealized", Orders.class);
        List<Orders> result = query.getResultList();
        return result;
    }

    public List<Orders> findByUnrealized() {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findByUnrealized", Orders.class);
        List<Orders> result = query.getResultList();
        return result;
    }
}
