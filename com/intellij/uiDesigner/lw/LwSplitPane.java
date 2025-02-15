/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.compiler.UnexpectedFormElementException;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import java.awt.LayoutManager;
import org.jdom.Element;

public final class LwSplitPane
extends LwContainer {
    public static final String POSITION_LEFT = "left";
    public static final String POSITION_RIGHT = "right";

    public LwSplitPane(String className) {
        super(className);
    }

    protected LayoutManager createInitialLayout() {
        return null;
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readNoLayout(element, provider);
    }

    protected void readConstraintsForChild(Element element, LwComponent component) {
        Element constraintsElement = LwXmlReader.getRequiredChild(element, "constraints");
        Element splitterChild = LwXmlReader.getRequiredChild(constraintsElement, "splitpane");
        String position = LwXmlReader.getRequiredString(splitterChild, "position");
        if (POSITION_LEFT.equals(position)) {
            component.setCustomLayoutConstraints(POSITION_LEFT);
        } else if (POSITION_RIGHT.equals(position)) {
            component.setCustomLayoutConstraints(POSITION_RIGHT);
        } else {
            throw new UnexpectedFormElementException("unexpected position: " + position);
        }
    }
}

