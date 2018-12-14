/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.controlador;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utilidad de gestión con el mecanismo de persistencia Hibernate
 *
 * @author AMP <Angel Mario Perez>
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // load from different directory
            return new Configuration().configure(
                    "resources/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("SessionFactory falló en la creación." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Obtiene la session de hibernate
     *
     * @return sessionFactory con la session creada en el mecanismo de
     * persistencia
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Libera la session creada en el mecanismo de persistencia
     */
    public static void shutdown() {
        // Cierra factory session
        getSessionFactory().close();
    }
}
