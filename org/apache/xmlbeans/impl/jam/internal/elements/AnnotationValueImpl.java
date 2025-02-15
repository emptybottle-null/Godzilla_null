/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.QualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public class AnnotationValueImpl
implements JAnnotationValue {
    private Object mValue = null;
    private JClassRef mType = null;
    private String mName;
    private ElementContext mContext;

    public AnnotationValueImpl(ElementContext ctx, String name, Object value, JClass type) {
        if (ctx == null) {
            throw new IllegalArgumentException("null ctx");
        }
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        this.mValue = value.getClass().isArray() ? AnnotationValueImpl.ensureArrayWrapped(value) : value;
        this.mContext = ctx;
        this.mName = name;
        this.mType = QualifiedJClassRef.create(type);
    }

    @Override
    public boolean isDefaultValueUsed() {
        throw new IllegalStateException("NYI");
    }

    @Override
    public String getName() {
        return this.mName;
    }

    @Override
    public JClass getType() {
        return this.mType.getRefClass();
    }

    @Override
    public JAnnotation asAnnotation() {
        if (this.mValue instanceof JAnnotation) {
            return (JAnnotation)this.mValue;
        }
        return null;
    }

    @Override
    public JClass asClass() {
        if (this.mValue instanceof JClass) {
            return (JClass)this.mValue;
        }
        return null;
    }

    @Override
    public String asString() {
        if (this.mValue == null) {
            return null;
        }
        return this.mValue.toString();
    }

    @Override
    public int asInt() throws NumberFormatException {
        if (this.mValue == null) {
            return 0;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).intValue();
        }
        try {
            return Integer.parseInt(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    @Override
    public boolean asBoolean() throws IllegalArgumentException {
        if (this.mValue == null) {
            return false;
        }
        return Boolean.valueOf(this.mValue.toString().trim());
    }

    @Override
    public long asLong() throws NumberFormatException {
        if (this.mValue == null) {
            return 0L;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).longValue();
        }
        try {
            return Long.parseLong(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0L;
        }
    }

    @Override
    public short asShort() throws NumberFormatException {
        if (this.mValue == null) {
            return 0;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).shortValue();
        }
        try {
            return Short.parseShort(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    @Override
    public double asDouble() throws NumberFormatException {
        if (this.mValue == null) {
            return 0.0;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).doubleValue();
        }
        try {
            return Double.parseDouble(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0.0;
        }
    }

    @Override
    public float asFloat() throws NumberFormatException {
        if (this.mValue == null) {
            return 0.0f;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).floatValue();
        }
        try {
            return Float.parseFloat(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0.0f;
        }
    }

    @Override
    public byte asByte() throws NumberFormatException {
        if (this.mValue == null) {
            return 0;
        }
        if (this.mValue instanceof Number) {
            return ((Number)this.mValue).byteValue();
        }
        try {
            return Byte.parseByte(this.mValue.toString().trim());
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    @Override
    public char asChar() throws IllegalArgumentException {
        if (this.mValue == null) {
            return '\u0000';
        }
        if (this.mValue instanceof Character) {
            return ((Character)this.mValue).charValue();
        }
        this.mValue = this.mValue.toString();
        return ((String)this.mValue).length() == 0 ? (char)'\u0000' : ((String)this.mValue).charAt(0);
    }

    @Override
    public JClass[] asClassArray() {
        if (this.mValue instanceof JClass[]) {
            return (JClass[])this.mValue;
        }
        return null;
    }

    @Override
    public JAnnotation[] asAnnotationArray() {
        if (this.mValue instanceof JAnnotation[]) {
            return (JAnnotation[])this.mValue;
        }
        return null;
    }

    @Override
    public String[] asStringArray() {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        String[] out = new String[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element on " + this.getName());
                out[i] = "";
                continue;
            }
            out[i] = ((Object[])this.mValue)[i].toString();
        }
        return out;
    }

    @Override
    public int[] asIntArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        int[] out = new int[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0;
                continue;
            }
            out[i] = Integer.parseInt(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public boolean[] asBooleanArray() throws IllegalArgumentException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        boolean[] out = new boolean[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = false;
                continue;
            }
            out[i] = Boolean.valueOf(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public short[] asShortArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        short[] out = new short[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0;
                continue;
            }
            out[i] = Short.parseShort(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public long[] asLongArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        long[] out = new long[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0L;
                continue;
            }
            out[i] = Long.parseLong(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public double[] asDoubleArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        double[] out = new double[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0.0;
                continue;
            }
            out[i] = Double.parseDouble(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public float[] asFloatArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        float[] out = new float[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0.0f;
                continue;
            }
            out[i] = Float.parseFloat(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public byte[] asByteArray() throws NumberFormatException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        byte[] out = new byte[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = 0;
                continue;
            }
            out[i] = Byte.parseByte(((Object[])this.mValue)[i].toString());
        }
        return out;
    }

    @Override
    public char[] asCharArray() throws IllegalArgumentException {
        if (!this.mValue.getClass().isArray()) {
            return null;
        }
        char[] out = new char[((Object[])this.mValue).length];
        for (int i = 0; i < out.length; ++i) {
            if (((Object[])this.mValue)[i] == null) {
                this.mContext.getLogger().error("Null annotation value array element " + i + " on " + this.getName());
                out[i] = '\u0000';
                continue;
            }
            out[i] = ((Object[])this.mValue)[i].toString().charAt(0);
        }
        return out;
    }

    private static final Object[] ensureArrayWrapped(Object o) {
        if (o instanceof Object[]) {
            return (Object[])o;
        }
        if (o instanceof int[]) {
            int dims = ((int[])o).length;
            Object[] out = new Integer[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Integer(((int[])o)[i]);
            }
            return out;
        }
        if (o instanceof boolean[]) {
            int dims = ((boolean[])o).length;
            Object[] out = new Boolean[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = ((boolean[])o)[i];
            }
            return out;
        }
        if (o instanceof byte[]) {
            int dims = ((byte[])o).length;
            Object[] out = new Byte[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Byte(((byte[])o)[i]);
            }
            return out;
        }
        if (o instanceof char[]) {
            int dims = ((char[])o).length;
            Object[] out = new Character[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Character(((char[])o)[i]);
            }
            return out;
        }
        if (o instanceof float[]) {
            int dims = ((float[])o).length;
            Object[] out = new Float[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Float(((float[])o)[i]);
            }
            return out;
        }
        if (o instanceof double[]) {
            int dims = ((double[])o).length;
            Object[] out = new Double[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Double(((double[])o)[i]);
            }
            return out;
        }
        if (o instanceof long[]) {
            int dims = ((long[])o).length;
            Object[] out = new Long[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Long(((long[])o)[i]);
            }
            return out;
        }
        if (o instanceof short[]) {
            int dims = ((short[])o).length;
            Object[] out = new Short[dims];
            for (int i = 0; i < dims; ++i) {
                out[i] = new Short(((short[])o)[i]);
            }
            return out;
        }
        throw new IllegalStateException("Unknown array type " + o.getClass());
    }

    @Override
    public Object getValue() {
        return this.mValue;
    }
}

