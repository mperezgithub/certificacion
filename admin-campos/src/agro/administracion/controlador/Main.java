/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.controlador;

import org.hibernate.SessionFactory;

/**
 * Main class
 *
 * @author AMP <Angel Mario Perez>
 */
public class Main {

    /**
     * Default constructor
     */
    public Main() {
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        new GestorCampo(sessionFactory).run();
    }
}
