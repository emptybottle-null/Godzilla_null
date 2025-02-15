/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.jmx;

import java.lang.reflect.Method;

class MethodUnion {
    Method readMethod;
    Method writeMethod;

    MethodUnion(Method readMethod, Method writeMethod) {
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
    }
}

