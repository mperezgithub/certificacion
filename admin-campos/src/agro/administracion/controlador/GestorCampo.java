/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.controlador;

import agro.administracion.Campo;
import agro.administracion.Lote;
import agro.administracion.TipoSuelo;
import agro.administracion.dao.TipoSueloDao;
import agro.administracion.dao.impl.TipoSueloDaoImpl;
import agro.administracion.ui.campo.registracion.Registra_Campo_Form;
import agro.administracion.dao.CampoDao;
import agro.administracion.dao.LoteDao;
import agro.administracion.dao.impl.CampoDaoImpl;
import agro.administracion.dao.impl.LoteDaoImpl;
import org.hibernate.SessionFactory;

/**
 * Clase gestora de Campos
 */
public class GestorCampo {

    private final SessionFactory sessionFactory;
    TipoSueloDao tipoSueloDao;
    CampoDao campoDao;
    LoteDao loteDao;

    /**
     * Constructor
     *
     * @param sessionFactory
     */
    public GestorCampo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.tipoSueloDao = new TipoSueloDaoImpl(sessionFactory);
        this.campoDao = new CampoDaoImpl(sessionFactory);
        this.loteDao = new LoteDaoImpl(sessionFactory);

        inicializarDatosTipoSuelo();
    }

    /**
     * Ejecuta formulario principal
     */
    public void run() {
        new Registra_Campo_Form(this).setVisible(true);
    }

    /**
     * Check campos existence
     *
     * @param nombreCampo
     * @return boolean true si el campo existe
     */
    public boolean checkearCampoExistente(String nombreCampo) {
        return (this.campoDao.buscarPorNombre(nombreCampo) != null);
    }

    /**
     * Persistir datos del campo
     *
     * @param campo
     */
    public void guardarCampo(Campo campo) {
        campoDao.guardar(campo);
    }

    /**
     * Persistir datos del lote
     *
     * @param lote
     */
    public void guardarLote(Lote lote) {
        loteDao.guardar(lote);
    }

    public void guardarTipoSuelo(TipoSuelo tipoSuelo) {
        tipoSueloDao.guardar(tipoSuelo);
    }

    /**
     * Inicializar datos de tipo de suelo
     */
    private void inicializarDatosTipoSuelo() {
        //Guardar tipo suelo 1,2,3,4,5
        TipoSuelo tipoSuelo = new TipoSuelo();
        tipoSuelo.setId(1);
        tipoSuelo.setDescripcion("TIPO I");
        this.guardarTipoSuelo(tipoSuelo);

        tipoSuelo = new TipoSuelo();
        tipoSuelo.setId(2);
        tipoSuelo.setDescripcion("TIPO II");
        this.guardarTipoSuelo(tipoSuelo);

        tipoSuelo = new TipoSuelo();
        tipoSuelo.setId(3);
        tipoSuelo.setDescripcion("TIPO III");
        this.guardarTipoSuelo(tipoSuelo);

        tipoSuelo = new TipoSuelo();
        tipoSuelo.setId(4);
        tipoSuelo.setDescripcion("TIPO IV");
        this.guardarTipoSuelo(tipoSuelo);

        tipoSuelo = new TipoSuelo();
        tipoSuelo.setId(5);
        tipoSuelo.setDescripcion("TIPO V");
        this.guardarTipoSuelo(tipoSuelo);
    }

    /**
     * Obtener todos los tipos de suelo
     *
     * @return
     */
    public String[] getTiposSuelo() {
        String[] tipoSuelo = this.tipoSueloDao.buscarDescripcionesTipoSuelo();
        return tipoSuelo;
    }

    /**
     * Obtener tipo de suelo usando descripcion
     *
     * @param descripcion
     * @return
     */
    public TipoSuelo getTipoSueloPorDescripcion(String descripcion) {
        TipoSuelo tipoSuelo = this.tipoSueloDao.buscarPorDescripcion(descripcion);
        return tipoSuelo;
    }

}
