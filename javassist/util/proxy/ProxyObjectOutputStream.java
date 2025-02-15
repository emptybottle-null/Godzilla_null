/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class ProxyObjectOutputStream
extends ObjectOutputStream {
    public ProxyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeClassDescriptor(ObjectStreamClass desc) throws IOException {
        Class<?> cl = desc.forClass();
        if (ProxyFactory.isProxyClass(cl)) {
            this.writeBoolean(true);
            Class<?> superClass = cl.getSuperclass();
            Class<?>[] interfaces = cl.getInterfaces();
            byte[] signature = ProxyFactory.getFilterSignature(cl);
            String name = superClass.getName();
            this.writeObject(name);
            this.writeInt(interfaces.length - 1);
            for (int i = 0; i < interfaces.length; ++i) {
                Class<?> interfaze = interfaces[i];
                if (interfaze == ProxyObject.class || interfaze == Proxy.class) continue;
                name = interfaces[i].getName();
                this.writeObject(name);
            }
            this.writeInt(signature.length);
            this.write(signature);
        } else {
            this.writeBoolean(false);
            super.writeClassDescriptor(desc);
        }
    }
}

