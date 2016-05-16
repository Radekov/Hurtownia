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
public class ShopsFacade extends AbstractFacade<Shops> {

    @PersistenceContext(unitName = "pl.pawww_Hurt_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShopsFacade() {
        super(Shops.class);
    }
        public List<Shops> findAllBySklep(String sklep) {
        TypedQuery<Shops> query = em.createNamedQuery("Shops.findBySklep", Shops.class);
        query.setParameter("sklep", sklep);
        List<Shops> result = query.getResultList();
        return result;
    }
}
