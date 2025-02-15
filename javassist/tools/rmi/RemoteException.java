/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.rmi;

public class RemoteException
extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RemoteException(String msg) {
        super(msg);
    }

    public RemoteException(Exception e) {
        super("by " + e.toString());
    }
}

