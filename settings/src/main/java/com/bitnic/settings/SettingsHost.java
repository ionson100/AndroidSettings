package com.bitnic.settings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ratio of the setting title width to its value.
 * Applies to all settings in the settings panel.
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SettingsHost {
    /**
     * Setting title panel width
     * @return float
     */
    float leftWeight();

    /**
     * Setting value panel width
     * @return float
     */
    float rightWeight();
}
