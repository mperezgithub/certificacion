/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion;

/**
 *
 */
public class Lote {

    private Integer numeroLote;
    private Double superficie;
    private TipoSuelo tipoSuelo;

    /**
     * Default constructor
     */
    public Lote() {
    }

    /**
     * Constructor con parametros
     * @param numeroLote
     * @param superficie
     * @param tipoSuelo 
     */
    public Lote(Integer numeroLote, Double superficie, TipoSuelo tipoSuelo) {
        this.numeroLote = numeroLote;
        this.superficie = superficie;
        this.tipoSuelo = tipoSuelo;
    }
    
    /**
     * Devuelve el Tipo de Suelo del lote
     *
     * @return TipoSuelo tipo de suelo del lote
     */
    public TipoSuelo getTipoSuelo() {
        return tipoSuelo;
    }

    /**
     * Setea el Tipo de Suelo del lote
     *
     * @param tipoSuelo valor del tipo de suelo
     */
    public void setTipoSuelo(TipoSuelo tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    /**
     * Devuelve el numero de lote
     *
     * @return Integer con el numero de lote
     */
    public Integer getNumeroLote() {
        return this.numeroLote;
    }

    /**
     * Setea el numero de lote
     *
     * @param value con el numero del lote
     */
    public void setNumeroLote(Integer value) {
        this.numeroLote = value;
    }

    /**
     * Devuelve la superficie del lote
     *
     * @return Double valor de la superficie en ha (hectareas)
     */
    public Double getSuperficie() {
        // TODO implement here
        return this.superficie;
    }

    /**
     * Setea el valor de la superficie
     *
     * @param value superficie en lote
     */
    public void setSuperficie(Double value) {
        this.superficie = value;
    }
}
