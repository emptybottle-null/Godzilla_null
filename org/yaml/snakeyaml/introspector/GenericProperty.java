/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.introspector;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.yaml.snakeyaml.introspector.Property;

public abstract class GenericProperty
extends Property {
    private Type genType;
    private boolean actualClassesChecked;
    private Class<?>[] actualClasses;

    public GenericProperty(String name, Class<?> aClass, Type aType) {
        super(name, aClass);
        this.genType = aType;
        this.actualClassesChecked = aType == null;
    }

    @Override
    public Class<?>[] getActualTypeArguments() {
        if (!this.actualClassesChecked) {
            Class classType;
            if (this.genType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType)this.genType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    this.actualClasses = new Class[actualTypeArguments.length];
                    for (int i = 0; i < actualTypeArguments.length; ++i) {
                        if (actualTypeArguments[i] instanceof Class) {
                            this.actualClasses[i] = (Class)actualTypeArguments[i];
                            continue;
                        }
                        if (actualTypeArguments[i] instanceof ParameterizedType) {
                            this.actualClasses[i] = (Class)((ParameterizedType)actualTypeArguments[i]).getRawType();
                            continue;
                        }
                        if (actualTypeArguments[i] instanceof GenericArrayType) {
                            Type componentType = ((GenericArrayType)actualTypeArguments[i]).getGenericComponentType();
                            if (componentType instanceof Class) {
                                this.actualClasses[i] = Array.newInstance((Class)componentType, 0).getClass();
                                continue;
                            }
                            this.actualClasses = null;
                        } else {
                            this.actualClasses = null;
                        }
                        break;
                    }
                }
            } else if (this.genType instanceof GenericArrayType) {
                Type componentType = ((GenericArrayType)this.genType).getGenericComponentType();
                if (componentType instanceof Class) {
                    this.actualClasses = new Class[]{(Class)componentType};
                }
            } else if (this.genType instanceof Class && (classType = (Class)this.genType).isArray()) {
                this.actualClasses = new Class[1];
                this.actualClasses[0] = this.getType().getComponentType();
            }
            this.actualClassesChecked = true;
        }
        return this.actualClasses;
    }
}

