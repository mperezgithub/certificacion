/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao.impl;

import agro.administracion.Campo;
import agro.administracion.dao.CampoDao;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public class CampoDaoImpl implements CampoDao {

    private final SessionFactory sessionFactory;

    public CampoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @param nombre
     * @return
     */
    @Override
    public Campo buscarPorNombre(String nombre) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Campo> query = builder.createQuery(Campo.class);
        Root<Campo> root = query.from(Campo.class);
        query.select(root);
        query.where(builder.equal(root.get("nombre"), nombre));

        Campo campo = session.createQuery(query).uniqueResult();

        session.close();
        return campo;
    }

    @Override
    public List<Campo> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar(Campo campo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(campo);
        session.getTransaction().commit();
        session.close();
    }
}
