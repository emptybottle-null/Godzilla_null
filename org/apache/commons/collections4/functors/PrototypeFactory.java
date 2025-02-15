/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.apache.commons.collections4.functors.InstantiateFactory;

public class PrototypeFactory {
    public static <T> Factory<T> prototypeFactory(T prototype) {
        if (prototype == null) {
            return ConstantFactory.constantFactory(null);
        }
        try {
            Method method = prototype.getClass().getMethod("clone", null);
            return new PrototypeCloneFactory(prototype, method);
        } catch (NoSuchMethodException ex) {
            try {
                prototype.getClass().getConstructor(prototype.getClass());
                return new InstantiateFactory(prototype.getClass(), new Class[]{prototype.getClass()}, new Object[]{prototype});
            } catch (NoSuchMethodException ex2) {
                if (prototype instanceof Serializable) {
                    return new PrototypeSerializationFactory((Serializable)prototype, null);
                }
                throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
            }
        }
    }

    private PrototypeFactory() {
    }

    static class PrototypeSerializationFactory<T extends Serializable>
    implements Factory<T> {
        private final T iPrototype;

        private PrototypeSerializationFactory(T prototype) {
            this.iPrototype = prototype;
        }

        @Override
        public T create() {
            Serializable serializable;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            ByteArrayInputStream bais = null;
            try {
                ObjectOutputStream out = new ObjectOutputStream(baos);
                out.writeObject(this.iPrototype);
                bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream in = new ObjectInputStream(bais);
                serializable = (Serializable)in.readObject();
            } catch (ClassNotFoundException ex) {
                throw new FunctorException(ex);
            } catch (IOException ex) {
                throw new FunctorException(ex);
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                } catch (IOException iOException) {}
                try {
                    baos.close();
                } catch (IOException iOException) {}
            }
            return (T)serializable;
        }

        /* synthetic */ PrototypeSerializationFactory(Serializable x0, 1 x1) {
            this(x0);
        }
    }

    static class PrototypeCloneFactory<T>
    implements Factory<T> {
        private final T iPrototype;
        private transient Method iCloneMethod;

        private PrototypeCloneFactory(T prototype, Method method) {
            this.iPrototype = prototype;
            this.iCloneMethod = method;
        }

        private void findCloneMethod() {
            try {
                this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", null);
            } catch (NoSuchMethodException ex) {
                throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
            }
        }

        @Override
        public T create() {
            if (this.iCloneMethod == null) {
                this.findCloneMethod();
            }
            try {
                return (T)this.iCloneMethod.invoke(this.iPrototype, null);
            } catch (IllegalAccessException ex) {
                throw new FunctorException("PrototypeCloneFactory: Clone method must be public", ex);
            } catch (InvocationTargetException ex) {
                throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", ex);
            }
        }
    }
}

