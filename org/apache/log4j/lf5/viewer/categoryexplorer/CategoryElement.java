/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

public class CategoryElement {
    protected String _categoryTitle;

    public CategoryElement() {
    }

    public CategoryElement(String title) {
        this._categoryTitle = title;
    }

    public String getTitle() {
        return this._categoryTitle;
    }

    public void setTitle(String title) {
        this._categoryTitle = title;
    }
}

