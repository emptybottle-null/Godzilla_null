/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.templates;

import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;

public abstract class AbstractCodeTemplate
implements CodeTemplate {
    private String id;

    public AbstractCodeTemplate() {
    }

    public AbstractCodeTemplate(String id) {
        this.setID(id);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError("CodeTemplate implementation not Cloneable: " + this.getClass().getName());
        }
    }

    @Override
    public int compareTo(CodeTemplate o) {
        if (o == null) {
            return -1;
        }
        return this.getID().compareTo(o.getID());
    }

    public boolean equals(Object obj) {
        if (obj instanceof CodeTemplate) {
            return this.compareTo((CodeTemplate)obj) == 0;
        }
        return false;
    }

    @Override
    public String getID() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public void setID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }
}

