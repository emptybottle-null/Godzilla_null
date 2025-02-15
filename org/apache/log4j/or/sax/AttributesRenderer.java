/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.or.sax;

import org.apache.log4j.or.ObjectRenderer;
import org.xml.sax.Attributes;

public class AttributesRenderer
implements ObjectRenderer {
    public String doRender(Object o) {
        if (o instanceof Attributes) {
            StringBuffer sbuf = new StringBuffer();
            Attributes a = (Attributes)o;
            int len = a.getLength();
            boolean first = true;
            for (int i = 0; i < len; ++i) {
                if (first) {
                    first = false;
                } else {
                    sbuf.append(", ");
                }
                sbuf.append(a.getQName(i));
                sbuf.append('=');
                sbuf.append(a.getValue(i));
            }
            return sbuf.toString();
        }
        try {
            return o.toString();
        } catch (Exception ex) {
            return ex.toString();
        }
    }
}

