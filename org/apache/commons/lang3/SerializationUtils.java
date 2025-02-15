/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.Validate;

public class SerializationUtils {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static <T extends Serializable> T clone(T object) {
        if (object == null) {
            return null;
        }
        byte[] objectData = SerializationUtils.serialize(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        try (ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());){
            Serializable readObject;
            Serializable serializable = readObject = (Serializable)in.readObject();
            return (T)serializable;
        } catch (ClassNotFoundException ex) {
            throw new SerializationException("ClassNotFoundException while reading cloned object data", ex);
        } catch (IOException ex) {
            throw new SerializationException("IOException while reading or closing cloned object data", ex);
        }
    }

    public static <T> T deserialize(byte[] objectData) {
        Validate.notNull(objectData, "objectData", new Object[0]);
        return SerializationUtils.deserialize(new ByteArrayInputStream(objectData));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static <T> T deserialize(InputStream inputStream) {
        Validate.notNull(inputStream, "inputStream", new Object[0]);
        try (ObjectInputStream in = new ObjectInputStream(inputStream);){
            Object obj;
            Object object = obj = in.readObject();
            return (T)object;
        } catch (IOException | ClassNotFoundException ex) {
            throw new SerializationException(ex);
        }
    }

    public static <T extends Serializable> T roundtrip(T obj) {
        return (T)((Serializable)SerializationUtils.deserialize(SerializationUtils.serialize(obj)));
    }

    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        SerializationUtils.serialize(obj, baos);
        return baos.toByteArray();
    }

    public static void serialize(Serializable obj, OutputStream outputStream) {
        Validate.notNull(outputStream, "outputStream", new Object[0]);
        try (ObjectOutputStream out = new ObjectOutputStream(outputStream);){
            out.writeObject(obj);
        } catch (IOException ex) {
            throw new SerializationException(ex);
        }
    }

    static class ClassLoaderAwareObjectInputStream
    extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String name = desc.getName();
            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException ex) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException cnfe) {
                    Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw cnfe;
                }
            }
        }

        static {
            primitiveTypes.put("byte", Byte.TYPE);
            primitiveTypes.put("short", Short.TYPE);
            primitiveTypes.put("int", Integer.TYPE);
            primitiveTypes.put("long", Long.TYPE);
            primitiveTypes.put("float", Float.TYPE);
            primitiveTypes.put("double", Double.TYPE);
            primitiveTypes.put("boolean", Boolean.TYPE);
            primitiveTypes.put("char", Character.TYPE);
            primitiveTypes.put("void", Void.TYPE);
        }
    }
}

