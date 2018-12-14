/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao;

import agro.administracion.Campo;
import java.util.List;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public interface CampoDao {

    public Campo buscarPorNombre(String nombre);

    public List<Campo> buscarTodos();

    public void guardar(Campo campo);
}
