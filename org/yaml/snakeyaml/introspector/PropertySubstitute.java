/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.introspector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.introspector.Property;

public class PropertySubstitute
extends Property {
    private static final Logger log = Logger.getLogger(PropertySubstitute.class.getPackage().getName());
    protected Class<?> targetType;
    private final String readMethod;
    private final String writeMethod;
    private transient Method read;
    private transient Method write;
    private Field field;
    protected Class<?>[] parameters;
    private Property delegate;
    private boolean filler;

    public PropertySubstitute(String name, Class<?> type, String readMethod, String writeMethod, Class<?> ... params) {
        super(name, type);
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
        this.setActualTypeArguments(params);
        this.filler = false;
    }

    public PropertySubstitute(String name, Class<?> type, Class<?> ... params) {
        this(name, type, (String)null, (String)null, params);
    }

    @Override
    public Class<?>[] getActualTypeArguments() {
        if (this.parameters == null && this.delegate != null) {
            return this.delegate.getActualTypeArguments();
        }
        return this.parameters;
    }

    public void setActualTypeArguments(Class<?> ... args) {
        this.parameters = args != null && args.length > 0 ? args : null;
    }

    @Override
    public void set(Object object, Object value) throws Exception {
        if (this.write != null) {
            if (!this.filler) {
                this.write.invoke(object, value);
            } else if (value != null) {
                if (value instanceof Collection) {
                    Collection collection = (Collection)value;
                    for (Object val : collection) {
                        this.write.invoke(object, val);
                    }
                } else if (value instanceof Map) {
                    Map map = (Map)value;
                    for (Map.Entry entry : map.entrySet()) {
                        this.write.invoke(object, entry.getKey(), entry.getValue());
                    }
                } else if (value.getClass().isArray()) {
                    int len = Array.getLength(value);
                    for (int i = 0; i < len; ++i) {
                        this.write.invoke(object, Array.get(value, i));
                    }
                }
            }
        } else if (this.field != null) {
            this.field.set(object, value);
        } else if (this.delegate != null) {
            this.delegate.set(object, value);
        } else {
            log.warning("No setter/delegate for '" + this.getName() + "' on object " + object);
        }
    }

    @Override
    public Object get(Object object) {
        try {
            if (this.read != null) {
                return this.read.invoke(object, new Object[0]);
            }
            if (this.field != null) {
                return this.field.get(object);
            }
        } catch (Exception e) {
            throw new YAMLException("Unable to find getter for property '" + this.getName() + "' on object " + object + ":" + e);
        }
        if (this.delegate != null) {
            return this.delegate.get(object);
        }
        throw new YAMLException("No getter or delegate for property '" + this.getName() + "' on object " + object);
    }

    @Override
    public List<Annotation> getAnnotations() {
        Annotation[] annotations = null;
        if (this.read != null) {
            annotations = this.read.getAnnotations();
        } else if (this.field != null) {
            annotations = this.field.getAnnotations();
        }
        return annotations != null ? Arrays.asList(annotations) : this.delegate.getAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        A annotation = this.read != null ? this.read.getAnnotation(annotationType) : (this.field != null ? this.field.getAnnotation(annotationType) : this.delegate.getAnnotation(annotationType));
        return annotation;
    }

    public void setTargetType(Class<?> targetType) {
        if (this.targetType != targetType) {
            this.targetType = targetType;
            String name = this.getName();
            block0: for (Class<?> c = targetType; c != null; c = c.getSuperclass()) {
                for (Field f : c.getDeclaredFields()) {
                    if (!f.getName().equals(name)) continue;
                    int modifiers = f.getModifiers();
                    if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) continue block0;
                    f.setAccessible(true);
                    this.field = f;
                    continue block0;
                }
            }
            if (this.field == null && log.isLoggable(Level.FINE)) {
                log.fine(String.format("Failed to find field for %s.%s", targetType.getName(), this.getName()));
            }
            if (this.readMethod != null) {
                this.read = this.discoverMethod(targetType, this.readMethod, new Class[0]);
            }
            if (this.writeMethod != null) {
                this.filler = false;
                this.write = this.discoverMethod(targetType, this.writeMethod, this.getType());
                if (this.write == null && this.parameters != null) {
                    this.filler = true;
                    this.write = this.discoverMethod(targetType, this.writeMethod, this.parameters);
                }
            }
        }
    }

    private Method discoverMethod(Class<?> type, String name, Class<?> ... params) {
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            for (Method method : c.getDeclaredMethods()) {
                Class<?>[] parameterTypes;
                if (!name.equals(method.getName()) || (parameterTypes = method.getParameterTypes()).length != params.length) continue;
                boolean found = true;
                for (int i = 0; i < parameterTypes.length; ++i) {
                    if (parameterTypes[i].isAssignableFrom(params[i])) continue;
                    found = false;
                }
                if (!found) continue;
                method.setAccessible(true);
                return method;
            }
        }
        if (log.isLoggable(Level.FINE)) {
            log.fine(String.format("Failed to find [%s(%d args)] for %s.%s", name, params.length, this.targetType.getName(), this.getName()));
        }
        return null;
    }

    @Override
    public String getName() {
        String n = super.getName();
        if (n != null) {
            return n;
        }
        return this.delegate != null ? this.delegate.getName() : null;
    }

    @Override
    public Class<?> getType() {
        Class<?> t = super.getType();
        if (t != null) {
            return t;
        }
        return this.delegate != null ? this.delegate.getType() : null;
    }

    @Override
    public boolean isReadable() {
        return this.read != null || this.field != null || this.delegate != null && this.delegate.isReadable();
    }

    @Override
    public boolean isWritable() {
        return this.write != null || this.field != null || this.delegate != null && this.delegate.isWritable();
    }

    public void setDelegate(Property delegate) {
        this.delegate = delegate;
        if (this.writeMethod != null && this.write == null && !this.filler) {
            this.filler = true;
            this.write = this.discoverMethod(this.targetType, this.writeMethod, this.getActualTypeArguments());
        }
    }
}

