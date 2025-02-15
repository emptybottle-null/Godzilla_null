/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JClass;

public interface JAnnotationValue {
    public String getName();

    public JClass getType();

    public JAnnotation asAnnotation();

    public JClass asClass();

    public String asString();

    public int asInt() throws NumberFormatException;

    public boolean asBoolean() throws IllegalArgumentException;

    public long asLong() throws NumberFormatException;

    public short asShort() throws NumberFormatException;

    public double asDouble() throws NumberFormatException;

    public float asFloat() throws NumberFormatException;

    public byte asByte() throws NumberFormatException;

    public char asChar() throws IllegalArgumentException;

    public JClass[] asClassArray();

    public JAnnotation[] asAnnotationArray();

    public String[] asStringArray();

    public int[] asIntArray() throws NumberFormatException;

    public boolean[] asBooleanArray() throws IllegalArgumentException;

    public short[] asShortArray() throws NumberFormatException;

    public long[] asLongArray() throws NumberFormatException;

    public double[] asDoubleArray() throws NumberFormatException;

    public float[] asFloatArray() throws NumberFormatException;

    public byte[] asByteArray() throws NumberFormatException;

    public char[] asCharArray() throws IllegalArgumentException;

    public boolean isDefaultValueUsed();

    public Object getValue();
}

