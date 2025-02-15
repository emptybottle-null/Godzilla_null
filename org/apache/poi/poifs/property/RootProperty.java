/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.property;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.poi.poifs.property.DirectoryProperty;

public final class RootProperty
extends DirectoryProperty {
    private static final String NAME = "Root Entry";

    RootProperty() {
        super(NAME);
        this.setNodeColor((byte)1);
        this.setPropertyType((byte)5);
        this.setStartBlock(-2);
    }

    RootProperty(int index, byte[] array, int offset) {
        super(index, array, offset);
    }

    @Override
    public void setSize(int size) {
        int BLOCK_SHIFT = 6;
        int _block_size = 64;
        super.setSize(ArithmeticUtils.mulAndCheck(size, 64));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

