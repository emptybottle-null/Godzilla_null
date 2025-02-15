/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;
import org.springframework.core.io.VfsUtils;
import org.springframework.lang.Nullable;

abstract class VfsPatternUtils
extends VfsUtils {
    VfsPatternUtils() {
    }

    @Nullable
    static Object getVisitorAttributes() {
        return VfsPatternUtils.doGetVisitorAttributes();
    }

    static String getPath(Object resource) {
        String path = VfsPatternUtils.doGetPath(resource);
        return path != null ? path : "";
    }

    static Object findRoot(URL url) throws IOException {
        return VfsPatternUtils.getRoot(url);
    }

    static void visit(Object resource, InvocationHandler visitor) throws IOException {
        Object visitorProxy = Proxy.newProxyInstance(VIRTUAL_FILE_VISITOR_INTERFACE.getClassLoader(), new Class[]{VIRTUAL_FILE_VISITOR_INTERFACE}, visitor);
        VfsPatternUtils.invokeVfsMethod(VIRTUAL_FILE_METHOD_VISIT, resource, visitorProxy);
    }
}

