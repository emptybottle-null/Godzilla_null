/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IButtonGroup;
import com.intellij.uiDesigner.lw.LwXmlReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom.Element;

public class LwButtonGroup
implements IButtonGroup {
    private String myName;
    private ArrayList myComponentIds = new ArrayList();
    private boolean myBound;

    public void read(Element element) {
        this.myName = element.getAttributeValue("name");
        this.myBound = LwXmlReader.getOptionalBoolean(element, "bound", false);
        Iterator i = element.getChildren().iterator();
        while (i.hasNext()) {
            Element child = (Element)i.next();
            this.myComponentIds.add(child.getAttributeValue("id"));
        }
    }

    public String getName() {
        return this.myName;
    }

    public String[] getComponentIds() {
        return this.myComponentIds.toArray(new String[this.myComponentIds.size()]);
    }

    public boolean isBound() {
        return this.myBound;
    }
}

