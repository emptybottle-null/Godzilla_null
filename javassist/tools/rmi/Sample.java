/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.rmi;

import javassist.tools.rmi.ObjectImporter;
import javassist.tools.rmi.RemoteException;

public class Sample {
    private ObjectImporter importer;
    private int objectId;

    public Object forward(Object[] args, int identifier) {
        return this.importer.call(this.objectId, identifier, args);
    }

    public static Object forwardStatic(Object[] args, int identifier) throws RemoteException {
        throw new RemoteException("cannot call a static method.");
    }
}

