/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.serialize.ScriptableOutputStream;

public class ScriptableInputStream
extends ObjectInputStream {
    private Scriptable scope;
    private ClassLoader classLoader;

    public ScriptableInputStream(InputStream in, Scriptable scope) throws IOException {
        super(in);
        this.scope = scope;
        this.enableResolveObject(true);
        Context cx = Context.getCurrentContext();
        if (cx != null) {
            this.classLoader = cx.getApplicationClassLoader();
        }
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String name = desc.getName();
        if (this.classLoader != null) {
            try {
                return this.classLoader.loadClass(name);
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return super.resolveClass(desc);
    }

    @Override
    protected Object resolveObject(Object obj) throws IOException {
        if (obj instanceof ScriptableOutputStream.PendingLookup) {
            String name = ((ScriptableOutputStream.PendingLookup)obj).getName();
            if ((obj = ScriptableOutputStream.lookupQualifiedName(this.scope, name)) == Scriptable.NOT_FOUND) {
                throw new IOException("Object " + name + " not found upon " + "deserialization.");
            }
        } else if (obj instanceof UniqueTag) {
            obj = ((UniqueTag)obj).readResolve();
        } else if (obj instanceof Undefined) {
            obj = ((Undefined)obj).readResolve();
        }
        return obj;
    }
}

