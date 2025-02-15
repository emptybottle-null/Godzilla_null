/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.socksServer;

public interface SocketStatus {
    public String getErrorMessage();

    public boolean isActive();

    public boolean start();

    public boolean stop();
}

