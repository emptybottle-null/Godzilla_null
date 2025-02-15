/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import org.mozilla.javascript.RhinoSecurityManager;

public class SecurityUtilities {
    public static String getSystemProperty(final String name) {
        return AccessController.doPrivileged(new PrivilegedAction<String>(){

            @Override
            public String run() {
                return System.getProperty(name);
            }
        });
    }

    public static ProtectionDomain getProtectionDomain(final Class<?> clazz) {
        return AccessController.doPrivileged(new PrivilegedAction<ProtectionDomain>(){

            @Override
            public ProtectionDomain run() {
                return clazz.getProtectionDomain();
            }
        });
    }

    public static ProtectionDomain getScriptProtectionDomain() {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager instanceof RhinoSecurityManager) {
            return AccessController.doPrivileged(new PrivilegedAction<ProtectionDomain>(){

                @Override
                public ProtectionDomain run() {
                    Class<?> c = ((RhinoSecurityManager)securityManager).getCurrentScriptClass();
                    return c == null ? null : c.getProtectionDomain();
                }
            });
        }
        return null;
    }
}

