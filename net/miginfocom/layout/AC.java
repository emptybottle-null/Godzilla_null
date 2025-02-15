/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.DimConstraint;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.UnitValue;

public final class AC
implements Externalizable {
    private final ArrayList<DimConstraint> cList = new ArrayList(1);
    private transient int curIx = 0;

    public AC() {
        this.cList.add(new DimConstraint());
    }

    public final DimConstraint[] getConstaints() {
        return this.cList.toArray(new DimConstraint[this.cList.size()]);
    }

    public final void setConstaints(DimConstraint[] constr) {
        if (constr == null || constr.length < 1) {
            constr = new DimConstraint[]{new DimConstraint()};
        }
        this.cList.clear();
        this.cList.ensureCapacity(constr.length);
        for (DimConstraint c : constr) {
            this.cList.add(c);
        }
    }

    public int getCount() {
        return this.cList.size();
    }

    public final AC count(int size) {
        this.makeSize(size);
        return this;
    }

    public final AC noGrid() {
        return this.noGrid(this.curIx);
    }

    public final AC noGrid(int ... indexes) {
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setNoGrid(true);
        }
        return this;
    }

    public final AC index(int i) {
        this.makeSize(i);
        this.curIx = i;
        return this;
    }

    public final AC fill() {
        return this.fill(this.curIx);
    }

    public final AC fill(int ... indexes) {
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setFill(true);
        }
        return this;
    }

    public final AC sizeGroup() {
        return this.sizeGroup("", this.curIx);
    }

    public final AC sizeGroup(String s) {
        return this.sizeGroup(s, this.curIx);
    }

    public final AC sizeGroup(String s, int ... indexes) {
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setSizeGroup(s);
        }
        return this;
    }

    public final AC size(String s) {
        return this.size(s, this.curIx);
    }

    public final AC size(String size, int ... indexes) {
        BoundSize bs = ConstraintParser.parseBoundSize(size, false, true);
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setSize(bs);
        }
        return this;
    }

    public final AC gap() {
        ++this.curIx;
        this.makeSize(this.curIx);
        return this;
    }

    public final AC gap(String size) {
        return this.gap(size, this.curIx++);
    }

    public final AC gap(String size, int ... indexes) {
        BoundSize bsa = size != null ? ConstraintParser.parseBoundSize(size, true, true) : null;
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix + 1);
            if (bsa == null) continue;
            this.cList.get(ix).setGapAfter(bsa);
        }
        return this;
    }

    public final AC align(String side) {
        return this.align(side, this.curIx);
    }

    public final AC align(String side, int ... indexes) {
        UnitValue al = ConstraintParser.parseAlignKeywords(side, true);
        if (al == null) {
            al = ConstraintParser.parseAlignKeywords(side, false);
        }
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setAlign(al);
        }
        return this;
    }

    public final AC growPrio(int p) {
        return this.growPrio(p, this.curIx);
    }

    public final AC growPrio(int p, int ... indexes) {
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setGrowPriority(p);
        }
        return this;
    }

    public final AC grow() {
        return this.grow(100.0f, this.curIx);
    }

    public final AC grow(float w) {
        return this.grow(w, this.curIx);
    }

    public final AC grow(float w, int ... indexes) {
        Float gw = new Float(w);
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setGrow(gw);
        }
        return this;
    }

    public final AC shrinkPrio(int p) {
        return this.shrinkPrio(p, this.curIx);
    }

    public final AC shrinkPrio(int p, int ... indexes) {
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setShrinkPriority(p);
        }
        return this;
    }

    public final AC shrink() {
        return this.shrink(100.0f, this.curIx);
    }

    public final AC shrink(float w) {
        return this.shrink(w, this.curIx);
    }

    public final AC shrink(float w, int ... indexes) {
        Float sw = new Float(w);
        for (int i = indexes.length - 1; i >= 0; --i) {
            int ix = indexes[i];
            this.makeSize(ix);
            this.cList.get(ix).setShrink(sw);
        }
        return this;
    }

    public final AC shrinkWeight(float w) {
        return this.shrink(w);
    }

    public final AC shrinkWeight(float w, int ... indexes) {
        return this.shrink(w, indexes);
    }

    private void makeSize(int sz) {
        if (this.cList.size() <= sz) {
            this.cList.ensureCapacity(sz);
            for (int i = this.cList.size(); i <= sz; ++i) {
                this.cList.add(new DimConstraint());
            }
        }
    }

    private Object readResolve() throws ObjectStreamException {
        return LayoutUtil.getSerializedObject(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        LayoutUtil.setSerializedObject(this, LayoutUtil.readAsXML(in));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this.getClass() == AC.class) {
            LayoutUtil.writeAsXML(out, this);
        }
    }
}

