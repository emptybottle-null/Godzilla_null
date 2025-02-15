/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.lw.CompiledClassPropertiesProvider;
import com.intellij.uiDesigner.lw.ComponentVisitor;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IContainer;
import com.intellij.uiDesigner.lw.IProperty;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwIntroBooleanProperty;
import com.intellij.uiDesigner.lw.LwIntroIntProperty;
import com.intellij.uiDesigner.lw.LwIntroPrimitiveTypeProperty;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public abstract class LwComponent
implements IComponent {
    private String myId;
    private String myBinding;
    private final String myClassName;
    private LwContainer myParent;
    private final GridConstraints myConstraints;
    private Object myCustomLayoutConstraints;
    private final Rectangle myBounds;
    private final HashMap myIntrospectedProperty2Value;
    private Element myErrorComponentProperties;
    protected final HashMap myClientProperties;
    protected final HashMap myDelegeeClientProperties;
    private boolean myCustomCreate = false;
    private boolean myDefaultBinding = false;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Double;

    public LwComponent(String className) {
        if (className == null) {
            throw new IllegalArgumentException("className cannot be null");
        }
        this.myBounds = new Rectangle();
        this.myConstraints = new GridConstraints();
        this.myIntrospectedProperty2Value = new HashMap();
        this.myClassName = className;
        this.myClientProperties = new HashMap();
        this.myDelegeeClientProperties = new HashMap();
    }

    public final String getId() {
        return this.myId;
    }

    public final void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.myId = id;
    }

    public final String getBinding() {
        return this.myBinding;
    }

    public final void setBinding(String binding) {
        this.myBinding = binding;
    }

    public final Object getCustomLayoutConstraints() {
        return this.myCustomLayoutConstraints;
    }

    public final void setCustomLayoutConstraints(Object customLayoutConstraints) {
        this.myCustomLayoutConstraints = customLayoutConstraints;
    }

    public final String getComponentClassName() {
        return this.myClassName;
    }

    public IProperty[] getModifiedProperties() {
        return this.getAssignedIntrospectedProperties();
    }

    public final Rectangle getBounds() {
        return (Rectangle)this.myBounds.clone();
    }

    public final GridConstraints getConstraints() {
        return this.myConstraints;
    }

    public boolean isCustomCreate() {
        return this.myCustomCreate;
    }

    public boolean isDefaultBinding() {
        return this.myDefaultBinding;
    }

    public boolean accept(ComponentVisitor visitor) {
        return visitor.visit(this);
    }

    public boolean areChildrenExclusive() {
        return false;
    }

    public final LwContainer getParent() {
        return this.myParent;
    }

    public IContainer getParentContainer() {
        return this.myParent;
    }

    protected final void setParent(LwContainer parent) {
        this.myParent = parent;
    }

    public final void setBounds(Rectangle bounds) {
        this.myBounds.setBounds(bounds);
    }

    public final Object getPropertyValue(LwIntrospectedProperty property) {
        return this.myIntrospectedProperty2Value.get(property);
    }

    public final void setPropertyValue(LwIntrospectedProperty property, Object value) {
        this.myIntrospectedProperty2Value.put(property, value);
    }

    public final Element getErrorComponentProperties() {
        return this.myErrorComponentProperties;
    }

    public final LwIntrospectedProperty[] getAssignedIntrospectedProperties() {
        LwIntrospectedProperty[] properties = new LwIntrospectedProperty[this.myIntrospectedProperty2Value.size()];
        Iterator iterator = this.myIntrospectedProperty2Value.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            properties[i] = (LwIntrospectedProperty)iterator.next();
            ++i;
        }
        return properties;
    }

    protected final void readBase(Element element) {
        this.setId(LwXmlReader.getRequiredString(element, "id"));
        this.setBinding(element.getAttributeValue("binding"));
        this.myCustomCreate = LwXmlReader.getOptionalBoolean(element, "custom-create", false);
        this.myDefaultBinding = LwXmlReader.getOptionalBoolean(element, "default-binding", false);
    }

    protected final void readProperties(Element element, PropertiesProvider provider) {
        HashMap name2property;
        if (provider == null) {
            return;
        }
        Element propertiesElement = LwXmlReader.getChild(element, "properties");
        if (propertiesElement == null) {
            propertiesElement = new Element("properties", element.getNamespace());
        }
        if ((name2property = provider.getLwProperties(this.getComponentClassName())) == null) {
            this.myErrorComponentProperties = (Element)propertiesElement.clone();
            return;
        }
        List propertyElements = propertiesElement.getChildren();
        for (int i = 0; i < propertyElements.size(); ++i) {
            Element t = (Element)propertyElements.get(i);
            String name = t.getName();
            LwIntrospectedProperty property = (LwIntrospectedProperty)name2property.get(name);
            if (property == null) continue;
            try {
                Object value = property.read(t);
                this.setPropertyValue(property, value);
                continue;
            } catch (Exception exc) {
                // empty catch block
            }
        }
        this.readClientProperties(element);
    }

    private void readClientProperties(Element element) {
        Element propertiesElement = LwXmlReader.getChild(element, "clientProperties");
        if (propertiesElement == null) {
            return;
        }
        List clientPropertyList = propertiesElement.getChildren();
        for (int i = 0; i < clientPropertyList.size(); ++i) {
            Object value;
            LwIntrospectedProperty lwProp;
            Element prop = (Element)clientPropertyList.get(i);
            String propName = prop.getName();
            String className = LwXmlReader.getRequiredString(prop, "class");
            if (className.equals((class$java$lang$Integer == null ? LwComponent.class$("java.lang.Integer") : class$java$lang$Integer).getName())) {
                lwProp = new LwIntroIntProperty(propName);
            } else if (className.equals((class$java$lang$Boolean == null ? LwComponent.class$("java.lang.Boolean") : class$java$lang$Boolean).getName())) {
                lwProp = new LwIntroBooleanProperty(propName);
            } else if (className.equals((class$java$lang$Double == null ? LwComponent.class$("java.lang.Double") : class$java$lang$Double).getName())) {
                lwProp = new LwIntroPrimitiveTypeProperty(propName, class$java$lang$Double == null ? LwComponent.class$("java.lang.Double") : class$java$lang$Double);
            } else {
                Class<?> propClass;
                try {
                    propClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    continue;
                }
                lwProp = CompiledClassPropertiesProvider.propertyFromClass(propClass, propName);
            }
            if (lwProp == null) continue;
            try {
                value = ((LwIntrospectedProperty)lwProp).read(prop);
            } catch (Exception e) {
                continue;
            }
            this.myDelegeeClientProperties.put(propName, value);
        }
    }

    protected final void readConstraints(Element element) {
        LwContainer parent = this.getParent();
        if (parent == null) {
            throw new IllegalStateException("component must be in LW tree: " + this);
        }
        parent.readConstraintsForChild(element, this);
    }

    public abstract void read(Element var1, PropertiesProvider var2) throws Exception;

    public final Object getClientProperty(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return this.myClientProperties.get(key);
    }

    public final void putClientProperty(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        this.myClientProperties.put(key, value);
    }

    public HashMap getDelegeeClientProperties() {
        return this.myDelegeeClientProperties;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

