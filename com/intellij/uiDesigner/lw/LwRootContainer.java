/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.compiler.AlienFormFileException;
import com.intellij.uiDesigner.compiler.UnexpectedFormElementException;
import com.intellij.uiDesigner.lw.IButtonGroup;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IRootContainer;
import com.intellij.uiDesigner.lw.LwButtonGroup;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwInspectionSuppression;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import com.intellij.uiDesigner.lw.XYLayoutSerializer;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom.Element;

public final class LwRootContainer
extends LwContainer
implements IRootContainer {
    private String myClassToBind;
    private String myMainComponentBinding;
    private ArrayList myButtonGroups = new ArrayList();
    private ArrayList myInspectionSuppressions = new ArrayList();

    public LwRootContainer() throws Exception {
        super("javax.swing.JPanel");
        this.myLayoutSerializer = XYLayoutSerializer.INSTANCE;
    }

    public String getMainComponentBinding() {
        return this.myMainComponentBinding;
    }

    public String getClassToBind() {
        return this.myClassToBind;
    }

    public void setClassToBind(String classToBind) {
        this.myClassToBind = classToBind;
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        if (element == null) {
            throw new IllegalArgumentException("element cannot be null");
        }
        if (!"form".equals(element.getName())) {
            throw new UnexpectedFormElementException("unexpected element: " + element);
        }
        if (!"http://www.intellij.com/uidesigner/form/".equals(element.getNamespace().getURI())) {
            throw new AlienFormFileException();
        }
        this.setId("root");
        this.myClassToBind = element.getAttributeValue("bind-to-class");
        Iterator i = element.getChildren().iterator();
        while (i.hasNext()) {
            Element child = (Element)i.next();
            if (child.getName().equals("buttonGroups")) {
                this.readButtonGroups(child);
                continue;
            }
            if (child.getName().equals("inspectionSuppressions")) {
                this.readInspectionSuppressions(child);
                continue;
            }
            LwComponent component = LwRootContainer.createComponentFromTag(child);
            this.addComponent(component);
            component.read(child, provider);
        }
        this.myMainComponentBinding = element.getAttributeValue("stored-main-component-binding");
    }

    private void readButtonGroups(Element element) {
        Iterator i = element.getChildren().iterator();
        while (i.hasNext()) {
            Element child = (Element)i.next();
            LwButtonGroup group = new LwButtonGroup();
            group.read(child);
            this.myButtonGroups.add(group);
        }
    }

    private void readInspectionSuppressions(Element element) {
        Iterator i = element.getChildren().iterator();
        while (i.hasNext()) {
            Element child = (Element)i.next();
            String inspectionId = LwXmlReader.getRequiredString(child, "inspection");
            String componentId = LwXmlReader.getString(child, "id");
            this.myInspectionSuppressions.add(new LwInspectionSuppression(inspectionId, componentId));
        }
    }

    public IButtonGroup[] getButtonGroups() {
        return this.myButtonGroups.toArray(new LwButtonGroup[this.myButtonGroups.size()]);
    }

    public String getButtonGroupName(IComponent component) {
        for (int i = 0; i < this.myButtonGroups.size(); ++i) {
            LwButtonGroup group = (LwButtonGroup)this.myButtonGroups.get(i);
            String[] ids = group.getComponentIds();
            for (int j = 0; j < ids.length; ++j) {
                if (!ids[j].equals(component.getId())) continue;
                return group.getName();
            }
        }
        return null;
    }

    public String[] getButtonGroupComponentIds(String groupName) {
        for (int i = 0; i < this.myButtonGroups.size(); ++i) {
            LwButtonGroup group = (LwButtonGroup)this.myButtonGroups.get(i);
            if (!group.getName().equals(groupName)) continue;
            return group.getComponentIds();
        }
        throw new IllegalArgumentException("Cannot find group " + groupName);
    }

    public boolean isInspectionSuppressed(String inspectionId, String componentId) {
        Iterator iterator = this.myInspectionSuppressions.iterator();
        while (iterator.hasNext()) {
            LwInspectionSuppression suppression = (LwInspectionSuppression)iterator.next();
            if (suppression.getComponentId() != null && !suppression.getComponentId().equals(componentId) || !suppression.getInspectionId().equals(inspectionId)) continue;
            return true;
        }
        return false;
    }

    public LwInspectionSuppression[] getInspectionSuppressions() {
        return this.myInspectionSuppressions.toArray(new LwInspectionSuppression[this.myInspectionSuppressions.size()]);
    }
}

