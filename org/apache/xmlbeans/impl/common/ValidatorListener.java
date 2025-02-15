/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.common.PrefixResolver;

public interface ValidatorListener {
    public static final int BEGIN = 1;
    public static final int END = 2;
    public static final int TEXT = 3;
    public static final int ATTR = 4;
    public static final int ENDATTRS = 5;

    public void nextEvent(int var1, Event var2);

    public static interface Event
    extends PrefixResolver {
        public static final int PRESERVE = 1;
        public static final int REPLACE = 2;
        public static final int COLLAPSE = 3;

        public XmlCursor getLocationAsCursor();

        public Location getLocation();

        public String getXsiType();

        public String getXsiNil();

        public String getXsiLoc();

        public String getXsiNoLoc();

        public QName getName();

        public String getText();

        public String getText(int var1);

        public boolean textIsWhitespace();
    }
}

