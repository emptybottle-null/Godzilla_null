/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.extensions.compactnotation;

import org.yaml.snakeyaml.SecClass;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;

public class PackageCompactConstructor
extends CompactConstructor {
    private String packageName;

    public PackageCompactConstructor(String packageName) {
        this.packageName = packageName;
    }

    @Override
    protected Class<?> getClassForName(String name) throws ClassNotFoundException {
        if (name.indexOf(46) < 0) {
            try {
                Class clazz = SecClass.forName(this.packageName + "." + name);
                return clazz;
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return super.getClassForName(name);
    }
}

