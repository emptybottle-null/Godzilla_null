/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.or;

import org.apache.log4j.or.ObjectRenderer;

class DefaultRenderer
implements ObjectRenderer {
    DefaultRenderer() {
    }

    public String doRender(Object o) {
        try {
            return o.toString();
        } catch (Exception ex) {
            return ex.toString();
        }
    }
}

