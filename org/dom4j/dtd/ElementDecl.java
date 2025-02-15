/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dtd;

import org.dom4j.dtd.Decl;

public class ElementDecl
implements Decl {
    private String name;
    private String model;

    public ElementDecl() {
    }

    public ElementDecl(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String toString() {
        return "<!ELEMENT " + this.name + " " + this.model + ">";
    }
}

