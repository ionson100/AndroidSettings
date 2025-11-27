package com.bitnic.settings;


/**
 * Action check item settings
 * @param <T> type params
 */
public interface IActionSettings<T> {

    /**
     * Call method
     *
     * @param t Call method argument
     * @return String
     */
    String action(T t);
}
