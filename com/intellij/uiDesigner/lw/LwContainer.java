/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.compiler.UnexpectedFormElementException;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.lw.BorderLayoutSerializer;
import com.intellij.uiDesigner.lw.CardLayoutSerializer;
import com.intellij.uiDesigner.lw.ColorDescriptor;
import com.intellij.uiDesigner.lw.ComponentVisitor;
import com.intellij.uiDesigner.lw.FlowLayoutSerializer;
import com.intellij.uiDesigner.lw.FontDescriptor;
import com.intellij.uiDesigner.lw.FormLayoutSerializer;
import com.intellij.uiDesigner.lw.GridBagLayoutSerializer;
import com.intellij.uiDesigner.lw.GridLayoutSerializer;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IContainer;
import com.intellij.uiDesigner.lw.LayoutSerializer;
import com.intellij.uiDesigner.lw.LwAtomicComponent;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwHSpacer;
import com.intellij.uiDesigner.lw.LwNestedForm;
import com.intellij.uiDesigner.lw.LwScrollPane;
import com.intellij.uiDesigner.lw.LwSplitPane;
import com.intellij.uiDesigner.lw.LwTabbedPane;
import com.intellij.uiDesigner.lw.LwToolBar;
import com.intellij.uiDesigner.lw.LwVSpacer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import com.intellij.uiDesigner.lw.StringDescriptor;
import com.intellij.uiDesigner.lw.XYLayoutSerializer;
import com.intellij.uiDesigner.shared.BorderType;
import com.intellij.uiDesigner.shared.XYLayoutManager;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom.Element;

public class LwContainer
extends LwComponent
implements IContainer {
    private final ArrayList myComponents = new ArrayList();
    private BorderType myBorderType;
    private StringDescriptor myBorderTitle;
    private int myBorderTitleJustification;
    private int myBorderTitlePosition;
    private FontDescriptor myBorderTitleFont;
    private ColorDescriptor myBorderTitleColor;
    private Insets myBorderSize;
    private ColorDescriptor myBorderColor;
    private LayoutManager myLayout;
    private String myLayoutManager;
    protected LayoutSerializer myLayoutSerializer;

    public LwContainer(String className) {
        super(className);
        this.setBorderType(BorderType.NONE);
        this.myLayout = this.createInitialLayout();
    }

    protected LayoutManager createInitialLayout() {
        return new XYLayoutManager();
    }

    public final LayoutManager getLayout() {
        return this.myLayout;
    }

    public final void setLayout(LayoutManager layout) {
        this.myLayout = layout;
    }

    public String getLayoutManager() {
        return this.myLayoutManager;
    }

    public final boolean isGrid() {
        return this.getLayout() instanceof GridLayoutManager;
    }

    public final boolean isXY() {
        return this.getLayout() instanceof XYLayoutManager;
    }

    public final void addComponent(LwComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("component cannot be null");
        }
        if (this.myComponents.contains(component)) {
            throw new IllegalArgumentException("component is already added: " + component);
        }
        if (component.getParent() != null) {
            throw new IllegalArgumentException("component already added to another container");
        }
        this.myComponents.add(component);
        component.setParent(this);
    }

    public final IComponent getComponent(int index) {
        return (IComponent)this.myComponents.get(index);
    }

    public final int getComponentCount() {
        return this.myComponents.size();
    }

    public int indexOfComponent(IComponent lwComponent) {
        return this.myComponents.indexOf(lwComponent);
    }

    public final BorderType getBorderType() {
        return this.myBorderType;
    }

    public boolean accept(ComponentVisitor visitor) {
        if (!super.accept(visitor)) {
            return false;
        }
        for (int i = 0; i < this.getComponentCount(); ++i) {
            IComponent c = this.getComponent(i);
            if (c.accept(visitor)) continue;
            return false;
        }
        return true;
    }

    public final void setBorderType(BorderType type) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.myBorderType = type;
    }

    public final StringDescriptor getBorderTitle() {
        return this.myBorderTitle;
    }

    public final void setBorderTitle(StringDescriptor title) {
        this.myBorderTitle = title;
    }

    public int getBorderTitleJustification() {
        return this.myBorderTitleJustification;
    }

    public int getBorderTitlePosition() {
        return this.myBorderTitlePosition;
    }

    public FontDescriptor getBorderTitleFont() {
        return this.myBorderTitleFont;
    }

    public ColorDescriptor getBorderTitleColor() {
        return this.myBorderTitleColor;
    }

    public Insets getBorderSize() {
        return this.myBorderSize;
    }

    public ColorDescriptor getBorderColor() {
        return this.myBorderColor;
    }

    protected void readConstraintsForChild(Element element, LwComponent component) {
        if (this.myLayoutSerializer != null) {
            Element constraintsElement = LwXmlReader.getRequiredChild(element, "constraints");
            this.myLayoutSerializer.readChildConstraints(constraintsElement, component);
        }
    }

    protected final void readBorder(Element element) {
        Element borderElement = LwXmlReader.getRequiredChild(element, "border");
        this.setBorderType(BorderType.valueOf(LwXmlReader.getRequiredString(borderElement, "type")));
        StringDescriptor descriptor = LwXmlReader.getStringDescriptor(borderElement, "title", "title-resource-bundle", "title-key");
        if (descriptor != null) {
            this.setBorderTitle(descriptor);
        }
        this.myBorderTitleJustification = LwXmlReader.getOptionalInt(borderElement, "title-justification", 0);
        this.myBorderTitlePosition = LwXmlReader.getOptionalInt(borderElement, "title-position", 0);
        Element fontElement = LwXmlReader.getChild(borderElement, "font");
        if (fontElement != null) {
            this.myBorderTitleFont = LwXmlReader.getFontDescriptor(fontElement);
        }
        this.myBorderTitleColor = LwXmlReader.getOptionalColorDescriptor(LwXmlReader.getChild(borderElement, "title-color"));
        this.myBorderColor = LwXmlReader.getOptionalColorDescriptor(LwXmlReader.getChild(borderElement, "color"));
        Element sizeElement = LwXmlReader.getChild(borderElement, "size");
        if (sizeElement != null) {
            try {
                this.myBorderSize = LwXmlReader.readInsets(sizeElement);
            } catch (Exception e) {
                this.myBorderSize = null;
            }
        }
    }

    protected final void readChildren(Element element, PropertiesProvider provider) throws Exception {
        Element childrenElement = LwXmlReader.getRequiredChild(element, "children");
        Iterator i = childrenElement.getChildren().iterator();
        while (i.hasNext()) {
            Element child = (Element)i.next();
            LwComponent component = LwContainer.createComponentFromTag(child);
            this.addComponent(component);
            component.read(child, provider);
        }
    }

    public static LwComponent createComponentFromTag(Element child) throws Exception {
        LwComponent component;
        String name = child.getName();
        if ("component".equals(name)) {
            String className = LwXmlReader.getRequiredString(child, "class");
            component = new LwAtomicComponent(className);
        } else if ("nested-form".equals(name)) {
            component = new LwNestedForm();
        } else if ("vspacer".equals(name)) {
            component = new LwVSpacer();
        } else if ("hspacer".equals(name)) {
            component = new LwHSpacer();
        } else if ("xy".equals(name) || "grid".equals(name)) {
            String className = LwXmlReader.getOptionalString(child, "class", "javax.swing.JPanel");
            component = new LwContainer(className);
        } else if ("scrollpane".equals(name)) {
            String className = LwXmlReader.getOptionalString(child, "class", "javax.swing.JScrollPane");
            component = new LwScrollPane(className);
        } else if ("tabbedpane".equals(name)) {
            String className = LwXmlReader.getOptionalString(child, "class", "javax.swing.JTabbedPane");
            component = new LwTabbedPane(className);
        } else if ("splitpane".equals(name)) {
            String className = LwXmlReader.getOptionalString(child, "class", "javax.swing.JSplitPane");
            component = new LwSplitPane(className);
        } else if ("toolbar".equals(name)) {
            String className = LwXmlReader.getOptionalString(child, "class", "javax.swing.JToolBar");
            component = new LwToolBar(className);
        } else {
            throw new UnexpectedFormElementException("unexpected element: " + child);
        }
        return component;
    }

    protected final void readLayout(Element element) {
        this.myLayoutManager = element.getAttributeValue("layout-manager");
        if ("xy".equals(element.getName())) {
            this.myLayoutSerializer = XYLayoutSerializer.INSTANCE;
        } else if ("grid".equals(element.getName())) {
            this.createLayoutSerializer();
        } else {
            throw new UnexpectedFormElementException("unexpected element: " + element);
        }
        this.myLayoutSerializer.readLayout(element, this);
    }

    public void setLayoutManager(String layoutManager) {
        this.myLayoutManager = layoutManager;
        this.createLayoutSerializer();
    }

    private void createLayoutSerializer() {
        this.myLayoutSerializer = "BorderLayout".equals(this.myLayoutManager) ? BorderLayoutSerializer.INSTANCE : ("FlowLayout".equals(this.myLayoutManager) ? FlowLayoutSerializer.INSTANCE : ("CardLayout".equals(this.myLayoutManager) ? CardLayoutSerializer.INSTANCE : ("XYLayout".equals(this.myLayoutManager) ? XYLayoutSerializer.INSTANCE : ("FormLayout".equals(this.myLayoutManager) ? FormLayoutSerializer.INSTANCE : ("GridBagLayout".equals(this.myLayoutManager) ? GridBagLayoutSerializer.INSTANCE : GridLayoutSerializer.INSTANCE)))));
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readBase(element);
        this.readLayout(element);
        this.readConstraints(element);
        this.readProperties(element, provider);
        this.readBorder(element);
        this.readChildren(element, provider);
    }

    protected void readNoLayout(Element element, PropertiesProvider provider) throws Exception {
        this.readBase(element);
        this.readConstraints(element);
        this.readProperties(element, provider);
        this.readBorder(element);
        this.readChildren(element, provider);
    }

    public boolean areChildrenExclusive() {
        return "CardLayout".equals(this.myLayoutManager);
    }
}

