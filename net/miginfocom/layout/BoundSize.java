/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.UnitValue;

public class BoundSize
implements Serializable {
    public static final BoundSize NULL_SIZE = new BoundSize(null, null);
    public static final BoundSize ZERO_PIXEL = new BoundSize(UnitValue.ZERO, "0px");
    private final transient UnitValue min;
    private final transient UnitValue pref;
    private final transient UnitValue max;
    private final transient boolean gapPush;
    private static final long serialVersionUID = 1L;

    public BoundSize(UnitValue minMaxPref, String createString) {
        this(minMaxPref, minMaxPref, minMaxPref, createString);
    }

    public BoundSize(UnitValue min, UnitValue preferred, UnitValue max, String createString) {
        this(min, preferred, max, false, createString);
    }

    public BoundSize(UnitValue min, UnitValue preferred, UnitValue max, boolean gapPush, String createString) {
        this.min = min;
        this.pref = preferred;
        this.max = max;
        this.gapPush = gapPush;
        LayoutUtil.putCCString(this, createString);
    }

    public final UnitValue getMin() {
        return this.min;
    }

    public final UnitValue getPreferred() {
        return this.pref;
    }

    public final UnitValue getMax() {
        return this.max;
    }

    public boolean getGapPush() {
        return this.gapPush;
    }

    public boolean isUnset() {
        return this == ZERO_PIXEL || this.pref == null && this.min == null && this.max == null && !this.gapPush;
    }

    public int constrain(int size, float refValue, ContainerWrapper parent) {
        if (this.max != null) {
            size = Math.min(size, this.max.getPixels(refValue, parent, parent));
        }
        if (this.min != null) {
            size = Math.max(size, this.min.getPixels(refValue, parent, parent));
        }
        return size;
    }

    final UnitValue getSize(int sizeType) {
        switch (sizeType) {
            case 0: {
                return this.min;
            }
            case 1: {
                return this.pref;
            }
            case 2: {
                return this.max;
            }
        }
        throw new IllegalArgumentException("Unknown size: " + sizeType);
    }

    final int[] getPixelSizes(float refSize, ContainerWrapper parent, ComponentWrapper comp) {
        return new int[]{this.min != null ? this.min.getPixels(refSize, parent, comp) : 0, this.pref != null ? this.pref.getPixels(refSize, parent, comp) : 0, this.max != null ? this.max.getPixels(refSize, parent, comp) : 2097051};
    }

    String getConstraintString() {
        String cs = LayoutUtil.getCCString(this);
        if (cs != null) {
            return cs;
        }
        if (this.min == this.pref && this.pref == this.max) {
            return this.min != null ? this.min.getConstraintString() + "!" : "null";
        }
        StringBuilder sb = new StringBuilder(16);
        if (this.min != null) {
            sb.append(this.min.getConstraintString()).append(':');
        }
        if (this.pref != null) {
            if (this.min == null && this.max != null) {
                sb.append(":");
            }
            sb.append(this.pref.getConstraintString());
        } else if (this.min != null) {
            sb.append('n');
        }
        if (this.max != null) {
            sb.append(sb.length() == 0 ? "::" : ":").append(this.max.getConstraintString());
        }
        if (this.gapPush) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append("push");
        }
        return sb.toString();
    }

    void checkNotLinked() {
        if (this.isLinked()) {
            throw new IllegalArgumentException("Size may not contain links");
        }
    }

    boolean isLinked() {
        return this.min != null && this.min.isLinkedDeep() || this.pref != null && this.pref.isLinkedDeep() || this.max != null && this.max.isLinkedDeep();
    }

    boolean isAbsolute() {
        return !(this.min != null && !this.min.isAbsoluteDeep() || this.pref != null && !this.pref.isAbsoluteDeep() || this.max != null && !this.max.isAbsoluteDeep());
    }

    public String toString() {
        return "BoundSize{min=" + this.min + ", pref=" + this.pref + ", max=" + this.max + ", gapPush=" + this.gapPush + '}';
    }

    protected Object readResolve() throws ObjectStreamException {
        return LayoutUtil.getSerializedObject(this);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        if (this.getClass() == BoundSize.class) {
            LayoutUtil.writeAsXML(out, this);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        LayoutUtil.setSerializedObject(this, LayoutUtil.readAsXML(in));
    }

    static {
        if (LayoutUtil.HAS_BEANS) {
            LayoutUtil.setDelegate(BoundSize.class, new PersistenceDelegate(){

                @Override
                protected Expression instantiate(Object oldInstance, Encoder out) {
                    BoundSize bs = (BoundSize)oldInstance;
                    return new Expression(oldInstance, BoundSize.class, "new", new Object[]{bs.getMin(), bs.getPreferred(), bs.getMax(), bs.getGapPush(), bs.getConstraintString()});
                }
            });
        }
    }
}

