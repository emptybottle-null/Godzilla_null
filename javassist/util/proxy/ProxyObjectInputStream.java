/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import javassist.util.proxy.ProxyFactory;

public class ProxyObjectInputStream
extends ObjectInputStream {
    private ClassLoader loader = Thread.currentThread().getContextClassLoader();

    public ProxyObjectInputStream(InputStream in) throws IOException {
        super(in);
        if (this.loader == null) {
            this.loader = ClassLoader.getSystemClassLoader();
        }
    }

    public void setClassLoader(ClassLoader loader) {
        if (loader != null) {
            this.loader = loader;
        } else {
            loader = ClassLoader.getSystemClassLoader();
        }
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        boolean isProxy = this.readBoolean();
        if (isProxy) {
            String name = (String)this.readObject();
            Class<?> superClass = this.loader.loadClass(name);
            int length = this.readInt();
            Class[] interfaces = new Class[length];
            for (int i = 0; i < length; ++i) {
                name = (String)this.readObject();
                interfaces[i] = this.loader.loadClass(name);
            }
            length = this.readInt();
            byte[] signature = new byte[length];
            this.read(signature);
            ProxyFactory factory = new ProxyFactory();
            factory.setUseCache(true);
            factory.setUseWriteReplace(false);
            factory.setSuperclass(superClass);
            factory.setInterfaces(interfaces);
            Class<?> proxyClass = factory.createClass(signature);
            return ObjectStreamClass.lookup(proxyClass);
        }
        return super.readClassDescriptor();
    }
}

