/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author umash
 */
@Stateless
public class StuFacade extends AbstractFacade<Stu> implements StuFacadeLocal {

    @PersistenceContext(unitName = "zlab13-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StuFacade() {
        super(Stu.class);
    }

    @Override
    public void addStu(String usn, String name, String branch) {
        Stu ob=new Stu();
        ob.setUsn(usn);
        ob.setName(name);
        ob.setBranch(branch);
        persist(ob);
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
