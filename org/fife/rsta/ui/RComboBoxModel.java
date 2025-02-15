/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

public class RComboBoxModel<E>
extends DefaultComboBoxModel<E> {
    private static final long serialVersionUID = 1L;
    private int maxNumElements;

    public RComboBoxModel() {
        this.setMaxNumElements(8);
    }

    public RComboBoxModel(E[] items) {
        super(items);
        this.setMaxNumElements(8);
    }

    public RComboBoxModel(Vector<E> v) {
        super(v);
        this.setMaxNumElements(8);
    }

    @Override
    public void addElement(E anObject) {
        this.insertElementAt(anObject, 0);
    }

    private void ensureValidItemCount() {
        while (this.getSize() > this.maxNumElements) {
            this.removeElementAt(this.getSize() - 1);
        }
    }

    public int getMaxNumElements() {
        return this.maxNumElements;
    }

    @Override
    public void insertElementAt(E anObject, int index) {
        int oldPos = this.getIndexOf(anObject);
        if (oldPos == index) {
            return;
        }
        if (oldPos > -1) {
            this.removeElement(anObject);
        }
        super.insertElementAt(anObject, index);
        this.ensureValidItemCount();
    }

    public void setMaxNumElements(int numElements) {
        this.maxNumElements = numElements <= 0 ? 4 : numElements;
        this.ensureValidItemCount();
    }
}

