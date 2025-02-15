/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.lang.reflect.Method;

public interface MethodFilter {
    public boolean isHandled(Method var1);
}

