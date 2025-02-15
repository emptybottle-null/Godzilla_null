/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.util.Objects;
import org.apache.poi.hpsf.Property;

public class CustomProperty
extends Property {
    private String name;

    public CustomProperty() {
        this.name = null;
    }

    public CustomProperty(Property property) {
        this(property, null);
    }

    public CustomProperty(Property property, String name) {
        super(property);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equalsContents(Object o) {
        CustomProperty c = (CustomProperty)o;
        String name1 = c.getName();
        String name2 = this.getName();
        boolean equalNames = true;
        equalNames = name1 == null ? name2 == null : name1.equals(name2);
        return equalNames && c.getID() == this.getID() && c.getType() == this.getType() && c.getValue().equals(this.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.getID());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CustomProperty && this.equalsContents(o);
    }
}

