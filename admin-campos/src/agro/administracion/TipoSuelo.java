/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion;

/**
 *
 */
public class TipoSuelo {

    /**
     * Constructor
     */
    public TipoSuelo() {
    }

    /**
     * Constructor con parametros
     */
    public TipoSuelo(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    private Integer id;
    private String descripcion;

    /**
     * Constructor con parametros
     *
     * @param id
     * @param descripcion
     */
    public TipoSuelo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el identificador del tipo de suelo
     *
     * @return Integer con el valor identificador del tipo de suelo
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setea el identificador del tipo de suelo
     *
     * @param tipoSueloID
     */
    public void setId(Integer tipoSueloID) {
        this.id = tipoSueloID;
    }

    /**
     * Devuelve la descripcion del tipo de lote
     *
     * @return
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setea la descripcion del tipo de lote
     *
     * @param value String con descripci�n
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    @Override
    public String toString() {
        return "Tipo Suelo con id:" + this.id + ","
                + "y descripcion:" + this.descripcion ;
    }
}
