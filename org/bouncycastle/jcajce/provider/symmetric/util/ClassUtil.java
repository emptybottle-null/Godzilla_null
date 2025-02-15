/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class ClassUtil {
    public static Class loadClass(Class clazz, final String string) {
        try {
            ClassLoader classLoader = clazz.getClassLoader();
            if (classLoader != null) {
                return classLoader.loadClass(string);
            }
            return (Class)AccessController.doPrivileged(new PrivilegedAction(){

                public Object run() {
                    try {
                        return Class.forName(string);
                    } catch (Exception exception) {
                        return null;
                    }
                }
            });
        } catch (ClassNotFoundException classNotFoundException) {
            return null;
        }
    }
}

