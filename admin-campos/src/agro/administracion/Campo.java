/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion;

import java.util.*;

/**
 * Clase Campo
 */
public class Campo {

    private Set<Lote> lotes;
    private Integer numeroCampo;
    private String nombre;
    private EstadoCampo estado;

    /**
     * Default constructor
     */
    public Campo() {
    }

    /**
     * Devuelve estado del campo
     *
     * @return EstadoCampo indica el estado del campo
     */
    public EstadoCampo getEstado() {
        return estado;
    }

    /**
     * Devuelve conjunto de lotes
     *
     * @return Set con el conjunto de lotes
     */
    public Set<Lote> getLotes() {
        return lotes;
    }

    /**
     * Setea el estado del campo
     *
     * @param estado EstadoCampo que necesita setearse
     */
    public void setEstado(EstadoCampo estado) {
        this.estado = estado;
    }

    /**
     * Setea lotes
     *
     * @param lotes Set conjunto de lotes
     */
    public void setLotes(Set<Lote> lotes) {
        this.lotes = lotes;
    }

    /**
     * Devuelve el numero del campo
     *
     * @return
     */
    public Integer getNumeroCampo() {
        return this.numeroCampo;
    }

    /**
     * Setea el numero del campo
     *
     * @param value Integer con el valor del numero de campo
     */
    public void setNumeroCampo(Integer value) {
        this.numeroCampo = value;
    }

    /**
     * Devuelve el nombre del campo
     *
     * @return String con el nombre del campo
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre del campo
     *
     * @param value String nombre del campo
     */
    public void setNombre(String value) {
        this.nombre = value;
    }
}
