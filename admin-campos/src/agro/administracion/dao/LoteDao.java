/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.dao;

import agro.administracion.Lote;
import java.util.List;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public interface LoteDao {

    public List<Lote> buscarTodos();

    public void guardar(Lote lote);
}
