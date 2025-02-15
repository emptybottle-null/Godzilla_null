/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.provider;

import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;

public class CompositeJamClassBuilder
extends JamClassBuilder {
    private JamClassBuilder[] mBuilders;

    public CompositeJamClassBuilder(JamClassBuilder[] builders) {
        if (builders == null) {
            throw new IllegalArgumentException("null builders");
        }
        this.mBuilders = builders;
    }

    @Override
    public void init(ElementContext ctx) {
        for (int i = 0; i < this.mBuilders.length; ++i) {
            this.mBuilders[i].init(ctx);
        }
    }

    @Override
    public MClass build(String pkg, String cname) {
        MClass out = null;
        for (int i = 0; i < this.mBuilders.length; ++i) {
            out = this.mBuilders[i].build(pkg, cname);
            if (out == null) continue;
            return out;
        }
        return null;
    }
}

