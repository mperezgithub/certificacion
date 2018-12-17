/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao.impl;

import agro.administracion.TipoSuelo;
import agro.administracion.dao.TipoSueloDao;
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
public class TipoSueloDaoHibernateImpl implements TipoSueloDao {

    
    private SessionFactory sessionFactory;

    public TipoSueloDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TipoSuelo buscarPorId(Integer id) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TipoSuelo> query = builder.createQuery(TipoSuelo.class);
        Root<TipoSuelo> root = query.from(TipoSuelo.class);
        query.select(root);
        query.where(builder.equal(root.get("id"), id));

        TipoSuelo tipoSuelo = session.createQuery(query).uniqueResult();

        session.close();
        return tipoSuelo;
    }

    @Override
    public List<TipoSuelo> buscarTodos() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TipoSuelo> query = builder.createQuery(TipoSuelo.class);
        Root<TipoSuelo> root = query.from(TipoSuelo.class);
        query.select(root);
        List<TipoSuelo> tipoSuelo = session.createQuery(query).list();
        session.close();
        return tipoSuelo;
    }

    @Override
    public String[] buscarDescripcionesTipoSuelo() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TipoSuelo> query = builder.createQuery(TipoSuelo.class);
        Root<TipoSuelo> root = query.from(TipoSuelo.class);
        query.select(root);
        List <TipoSuelo> tipoSueloLista = session.createQuery("select descripcion from TipoSuelo").list();
        session.close();
        return tipoSueloLista.toArray(new String[tipoSueloLista.size()]);
    }

    @Override
    public void guardar(TipoSuelo tipoSuelo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(tipoSuelo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TipoSuelo buscarPorDescripcion(String descripcion) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TipoSuelo> query = builder.createQuery(TipoSuelo.class);
        Root<TipoSuelo> root = query.from(TipoSuelo.class);
        query.select(root);
        query.where(builder.equal(root.get("descripcion"), descripcion));
        TipoSuelo tipoSuelo = session.createQuery(query).uniqueResult();
        session.close();
        return tipoSuelo;
    }
}
