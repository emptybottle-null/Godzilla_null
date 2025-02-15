/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.ITabbedPane;
import com.intellij.uiDesigner.lw.IconDescriptor;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import com.intellij.uiDesigner.lw.StringDescriptor;
import java.awt.LayoutManager;
import org.jdom.Element;

public final class LwTabbedPane
extends LwContainer
implements ITabbedPane {
    public LwTabbedPane(String className) {
        super(className);
    }

    protected LayoutManager createInitialLayout() {
        return null;
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readNoLayout(element, provider);
    }

    protected void readConstraintsForChild(Element element, LwComponent component) {
        String icon;
        Element constraintsElement = LwXmlReader.getRequiredChild(element, "constraints");
        Element tabbedPaneChild = LwXmlReader.getRequiredChild(constraintsElement, "tabbedpane");
        StringDescriptor descriptor = LwXmlReader.getStringDescriptor(tabbedPaneChild, "title", "title-resource-bundle", "title-key");
        if (descriptor == null) {
            throw new IllegalArgumentException("String descriptor value required");
        }
        Constraints constraints = new Constraints(descriptor);
        Element tooltipElement = LwXmlReader.getChild(tabbedPaneChild, "tooltip");
        if (tooltipElement != null) {
            constraints.myToolTip = LwXmlReader.getStringDescriptor(tooltipElement, "value", "resource-bundle", "key");
        }
        if ((icon = tabbedPaneChild.getAttributeValue("icon")) != null) {
            constraints.myIcon = new IconDescriptor(icon);
        }
        if ((icon = tabbedPaneChild.getAttributeValue("disabled-icon")) != null) {
            constraints.myDisabledIcon = new IconDescriptor(icon);
        }
        constraints.myEnabled = LwXmlReader.getOptionalBoolean(tabbedPaneChild, "enabled", true);
        component.setCustomLayoutConstraints(constraints);
    }

    public StringDescriptor getTabProperty(IComponent component, String propName) {
        LwComponent lwComponent = (LwComponent)component;
        Constraints constraints = (Constraints)lwComponent.getCustomLayoutConstraints();
        if (constraints == null) {
            return null;
        }
        return constraints.getProperty(propName);
    }

    public boolean areChildrenExclusive() {
        return true;
    }

    public static final class Constraints {
        public StringDescriptor myTitle;
        public StringDescriptor myToolTip;
        public IconDescriptor myIcon;
        public IconDescriptor myDisabledIcon;
        public boolean myEnabled = true;

        public Constraints(StringDescriptor title) {
            if (title == null) {
                throw new IllegalArgumentException("title cannot be null");
            }
            this.myTitle = title;
        }

        public StringDescriptor getProperty(String propName) {
            if (propName.equals("Tab Title")) {
                return this.myTitle;
            }
            if (propName.equals("Tab Tooltip")) {
                return this.myToolTip;
            }
            throw new IllegalArgumentException("Unknown property name " + propName);
        }
    }
}

