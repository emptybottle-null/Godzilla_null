/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.analysis;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.analysis.MultiType;
import javassist.bytecode.analysis.Type;

public class MultiArrayType
extends Type {
    private MultiType component;
    private int dims;

    public MultiArrayType(MultiType component, int dims) {
        super(null);
        this.component = component;
        this.dims = dims;
    }

    @Override
    public CtClass getCtClass() {
        CtClass clazz = this.component.getCtClass();
        if (clazz == null) {
            return null;
        }
        ClassPool pool = clazz.getClassPool();
        if (pool == null) {
            pool = ClassPool.getDefault();
        }
        String name = this.arrayName(clazz.getName(), this.dims);
        try {
            return pool.get(name);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    boolean popChanged() {
        return this.component.popChanged();
    }

    @Override
    public int getDimensions() {
        return this.dims;
    }

    @Override
    public Type getComponent() {
        return this.dims == 1 ? this.component : new MultiArrayType(this.component, this.dims - 1);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isAssignableFrom(Type type) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean isReference() {
        return true;
    }

    public boolean isAssignableTo(Type type) {
        if (MultiArrayType.eq(type.getCtClass(), Type.OBJECT.getCtClass())) {
            return true;
        }
        if (MultiArrayType.eq(type.getCtClass(), Type.CLONEABLE.getCtClass())) {
            return true;
        }
        if (MultiArrayType.eq(type.getCtClass(), Type.SERIALIZABLE.getCtClass())) {
            return true;
        }
        if (!type.isArray()) {
            return false;
        }
        Type typeRoot = this.getRootComponent(type);
        int typeDims = type.getDimensions();
        if (typeDims > this.dims) {
            return false;
        }
        if (typeDims < this.dims) {
            if (MultiArrayType.eq(typeRoot.getCtClass(), Type.OBJECT.getCtClass())) {
                return true;
            }
            if (MultiArrayType.eq(typeRoot.getCtClass(), Type.CLONEABLE.getCtClass())) {
                return true;
            }
            return MultiArrayType.eq(typeRoot.getCtClass(), Type.SERIALIZABLE.getCtClass());
        }
        return this.component.isAssignableTo(typeRoot);
    }

    @Override
    public int hashCode() {
        return this.component.hashCode() + this.dims;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MultiArrayType)) {
            return false;
        }
        MultiArrayType multi = (MultiArrayType)o;
        return this.component.equals(multi.component) && this.dims == multi.dims;
    }

    @Override
    public String toString() {
        return this.arrayName(this.component.toString(), this.dims);
    }
}

