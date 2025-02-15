/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;

public abstract class Curve {
    protected ControlPath cp;
    protected GroupIterator gi;
    protected boolean connect = false;

    public Curve(ControlPath cp, GroupIterator gi) {
        this.setControlPath(cp);
        this.setGroupIterator(gi);
    }

    public ControlPath getControlPath() {
        return this.cp;
    }

    public void setControlPath(ControlPath cp) {
        if (cp == null) {
            throw new IllegalArgumentException("ControlPath cannot be null.");
        }
        this.cp = cp;
    }

    public GroupIterator getGroupIterator() {
        return this.gi;
    }

    public void setGroupIterator(GroupIterator gi) {
        if (gi == null) {
            throw new IllegalArgumentException("GroupIterator cannot be null.");
        }
        this.gi = gi;
    }

    public boolean getConnect() {
        return this.connect;
    }

    public void setConnect(boolean b) {
        this.connect = b;
    }

    public abstract void appendTo(MultiPath var1);

    public void resetMemory() {
    }
}

