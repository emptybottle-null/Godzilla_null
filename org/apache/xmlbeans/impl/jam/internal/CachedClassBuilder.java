/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;

public class CachedClassBuilder
extends JamClassBuilder {
    private Map mQcname2jclass = null;
    private List mClassNames = new ArrayList();

    @Override
    public MClass build(String packageName, String className) {
        if (this.mQcname2jclass == null) {
            return null;
        }
        if (packageName.trim().length() > 0) {
            className = packageName + '.' + className;
        }
        return (MClass)this.mQcname2jclass.get(className);
    }

    @Override
    public MClass createClassToBuild(String packageName, String className, String[] importSpecs) {
        MClass out;
        String qualifiedName = packageName.trim().length() > 0 ? packageName + '.' + className : className;
        if (this.mQcname2jclass != null) {
            out = (MClass)this.mQcname2jclass.get(qualifiedName);
            if (out != null) {
                return out;
            }
        } else {
            this.mQcname2jclass = new HashMap();
        }
        out = super.createClassToBuild(packageName, className, importSpecs);
        this.mQcname2jclass.put(qualifiedName, out);
        this.mClassNames.add(qualifiedName);
        return out;
    }

    public String[] getClassNames() {
        String[] out = new String[this.mClassNames.size()];
        this.mClassNames.toArray(out);
        return out;
    }
}

