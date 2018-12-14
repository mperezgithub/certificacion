/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao;

import agro.administracion.TipoSuelo;
import java.util.List;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public interface TipoSueloDao {

    public TipoSuelo buscarPorId(Integer id);

    public List<TipoSuelo> buscarTodos();

    public String[] buscarDescripcionesTipoSuelo();

    public TipoSuelo buscarPorDescripcion(String descripcion);

    public void guardar(TipoSuelo tipoSuelo);
}
