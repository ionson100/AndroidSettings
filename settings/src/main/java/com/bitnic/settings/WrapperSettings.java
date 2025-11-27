package com.bitnic.settings;

import java.lang.reflect.Field;
 class WrapperSettings{
    public final float leftWeight, rightWeight;
    public WrapperSettings(Field field, SettingItem item, float leftWeight, float rightWeight){

        this.field = field;
        this.item = item;
        this.rightWeight = rightWeight;
        this.leftWeight = leftWeight;
    }
    public final Field field;
    public final SettingItem item;
}
