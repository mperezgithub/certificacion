/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao.impl;

import agro.administracion.TipoSuelo;
import agro.administracion.dao.TipoSueloDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public class TipoSueloDaoImpl implements TipoSueloDao {

    private final List<TipoSuelo> tipo_suelo_lista;
    private final SessionFactory sessionFactory;

    public TipoSueloDaoImpl(SessionFactory sessionFactory) {
        this.tipo_suelo_lista = new ArrayList<>();
        this.sessionFactory = sessionFactory;
        // Creamos los distintos tipos de suelos posibles.
        this.tipo_suelo_lista.add(new TipoSuelo(1, "TIPO I"));
        this.tipo_suelo_lista.add(new TipoSuelo(2, "TIPO II"));
        this.tipo_suelo_lista.add(new TipoSuelo(3, "TIPO III"));
        this.tipo_suelo_lista.add(new TipoSuelo(4, "TIPO IV"));
        this.tipo_suelo_lista.add(new TipoSuelo(5, "TIPO V"));
    }

    @Override
    public TipoSuelo buscarPorId(Integer id) {
        return this.tipo_suelo_lista.stream().filter(tipoSuelo -> id.equals(tipoSuelo.getId())).findAny().orElse(null);
    }

    @Override
    public List<TipoSuelo> buscarTodos() {
        return this.tipo_suelo_lista;
    }

    @Override
    public String[] buscarDescripcionesTipoSuelo() {
        List<String> tipoSueloLista = buscarTodos().stream().map(tiposuelo -> tiposuelo.getDescripcion()).collect(Collectors.toList());
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
