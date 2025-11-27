package com.bitnic.settings;

/**
 * Result of updating an object field
 */
public class ResultUpdate {

    /**
     * Constructor
     * @param fieldName : Name of the object field
     * @param value : New value
     */
    public ResultUpdate(String fieldName,Object value){

        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Name of the object field
     */
    public final String fieldName;

    /**
     * New value
     */
    public final Object value;
}