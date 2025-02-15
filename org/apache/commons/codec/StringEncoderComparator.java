/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec;

import java.util.Comparator;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class StringEncoderComparator
implements Comparator {
    private final StringEncoder stringEncoder;

    @Deprecated
    public StringEncoderComparator() {
        this.stringEncoder = null;
    }

    public StringEncoderComparator(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }

    public int compare(Object o1, Object o2) {
        int compareCode = 0;
        try {
            Comparable s1 = (Comparable)this.stringEncoder.encode(o1);
            Comparable s2 = (Comparable)this.stringEncoder.encode(o2);
            compareCode = s1.compareTo(s2);
        } catch (EncoderException ee) {
            compareCode = 0;
        }
        return compareCode;
    }
}

