/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.property;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.poifs.property.Child;
import org.apache.poi.poifs.property.Property;

public interface Parent
extends Child,
Iterable<Property> {
    public Iterator<Property> getChildren();

    public void addChild(Property var1) throws IOException;

    @Override
    public void setPreviousChild(Child var1);

    @Override
    public void setNextChild(Child var1);
}

