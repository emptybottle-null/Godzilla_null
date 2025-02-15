/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import org.jdom.Element;

public class LwNestedForm
extends LwComponent {
    private String myFormFileName;

    public LwNestedForm() {
        super("");
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.myFormFileName = LwXmlReader.getRequiredString(element, "form-file");
        this.readBase(element);
        this.readConstraints(element);
    }

    public String getFormFileName() {
        return this.myFormFileName;
    }
}

