/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.InputStream;
import java.net.URL;
import javassist.NotFoundException;

public interface ClassPath {
    public InputStream openClassfile(String var1) throws NotFoundException;

    public URL find(String var1);
}

