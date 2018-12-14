/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao.impl;

import agro.administracion.Lote;
import agro.administracion.dao.LoteDao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public class LoteDaoImpl implements LoteDao {

    private final SessionFactory sessionFactory;

    public LoteDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Lote lote) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lote);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Lote> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
