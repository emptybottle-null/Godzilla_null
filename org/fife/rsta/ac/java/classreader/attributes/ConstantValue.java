/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.attributes;

import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantDoubleInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantFloatInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantIntegerInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantLongInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantStringInfo;

public class ConstantValue
extends AttributeInfo {
    private int constantValueIndex;

    public ConstantValue(ClassFile cf, int constantValueIndex) {
        super(cf);
        this.constantValueIndex = constantValueIndex;
    }

    public int getConstantValueIndex() {
        return this.constantValueIndex;
    }

    public String getConstantValueAsString() {
        ClassFile cf = this.getClassFile();
        ConstantPoolInfo cpi = cf.getConstantPoolInfo(this.getConstantValueIndex());
        if (cpi instanceof ConstantDoubleInfo) {
            ConstantDoubleInfo cdi = (ConstantDoubleInfo)cpi;
            double value = cdi.getDoubleValue();
            return Double.toString(value);
        }
        if (cpi instanceof ConstantFloatInfo) {
            ConstantFloatInfo cfi = (ConstantFloatInfo)cpi;
            float value = cfi.getFloatValue();
            return Float.toString(value);
        }
        if (cpi instanceof ConstantIntegerInfo) {
            ConstantIntegerInfo cii = (ConstantIntegerInfo)cpi;
            int value = cii.getIntValue();
            return Integer.toString(value);
        }
        if (cpi instanceof ConstantLongInfo) {
            ConstantLongInfo cli = (ConstantLongInfo)cpi;
            long value = cli.getLongValue();
            return Long.toString(value);
        }
        if (cpi instanceof ConstantStringInfo) {
            ConstantStringInfo csi = (ConstantStringInfo)cpi;
            return csi.getStringValue();
        }
        return "INVALID_CONSTANT_TYPE_" + cpi.toString();
    }
}

