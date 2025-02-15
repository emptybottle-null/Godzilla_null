/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.fife.rsta.ac.java.DecoratableIcon;

public class IconFactory {
    public static final String SOURCE_FILE_ICON = "sourceFileIcon";
    public static final String PACKAGE_ICON = "packageIcon";
    public static final String IMPORT_ROOT_ICON = "importRootIcon";
    public static final String IMPORT_ICON = "importIcon";
    public static final String DEFAULT_CLASS_ICON = "defaultClassIcon";
    public static final String DEFAULT_INTERFACE_ICON = "defaultInterfaceIcon";
    public static final String CLASS_ICON = "classIcon";
    public static final String ENUM_ICON = "enumIcon";
    public static final String ENUM_PROTECTED_ICON = "enumProtectedIcon";
    public static final String ENUM_PRIVATE_ICON = "enumPrivateIcon";
    public static final String ENUM_DEFAULT_ICON = "enumDefaultIcon";
    public static final String INNER_CLASS_PUBLIC_ICON = "innerClassPublicIcon";
    public static final String INNER_CLASS_PROTECTED_ICON = "innerClassProtectedIcon";
    public static final String INNER_CLASS_PRIVATE_ICON = "innerClassPrivateIcon";
    public static final String INNER_CLASS_DEFAULT_ICON = "innerClassDefaultIcon";
    public static final String INTERFACE_ICON = "interfaceIcon";
    public static final String JAVADOC_ITEM_ICON = "javadocItemIcon";
    public static final String LOCAL_VARIABLE_ICON = "localVariableIcon";
    public static final String METHOD_PUBLIC_ICON = "methodPublicIcon";
    public static final String METHOD_PROTECTED_ICON = "methodProtectedIcon";
    public static final String METHOD_PRIVATE_ICON = "methodPrivateIcon";
    public static final String METHOD_DEFAULT_ICON = "methodDefaultIcon";
    public static final String TEMPLATE_ICON = "templateIcon";
    public static final String FIELD_PUBLIC_ICON = "fieldPublicIcon";
    public static final String FIELD_PROTECTED_ICON = "fieldProtectedIcon";
    public static final String FIELD_PRIVATE_ICON = "fieldPrivateIcon";
    public static final String FIELD_DEFAULT_ICON = "fieldDefaultIcon";
    public static final String CONSTRUCTOR_ICON = "constructorIcon";
    public static final String DEPRECATED_ICON = "deprecatedIcon";
    public static final String ABSTRACT_ICON = "abstractIcon";
    public static final String FINAL_ICON = "finalIcon";
    public static final String STATIC_ICON = "staticIcon";
    private Map<String, Icon> iconMap = new HashMap<String, Icon>();
    private static final IconFactory INSTANCE = new IconFactory();

    private IconFactory() {
        this.iconMap.put(SOURCE_FILE_ICON, this.loadIcon("jcu_obj.gif"));
        this.iconMap.put(PACKAGE_ICON, this.loadIcon("package_obj.gif"));
        this.iconMap.put(IMPORT_ROOT_ICON, this.loadIcon("impc_obj.gif"));
        this.iconMap.put(IMPORT_ICON, this.loadIcon("imp_obj.gif"));
        this.iconMap.put(DEFAULT_CLASS_ICON, this.loadIcon("class_default_obj.gif"));
        this.iconMap.put(DEFAULT_INTERFACE_ICON, this.loadIcon("int_default_obj.gif"));
        this.iconMap.put(CLASS_ICON, this.loadIcon("class_obj.gif"));
        this.iconMap.put(ENUM_ICON, this.loadIcon("enum_obj.gif"));
        this.iconMap.put(ENUM_PROTECTED_ICON, this.loadIcon("enum_protected_obj.gif"));
        this.iconMap.put(ENUM_PRIVATE_ICON, this.loadIcon("enum_private_obj.gif"));
        this.iconMap.put(ENUM_DEFAULT_ICON, this.loadIcon("enum_default_obj.gif"));
        this.iconMap.put(INNER_CLASS_PUBLIC_ICON, this.loadIcon("innerclass_public_obj.gif"));
        this.iconMap.put(INNER_CLASS_PROTECTED_ICON, this.loadIcon("innerclass_protected_obj.gif"));
        this.iconMap.put(INNER_CLASS_PRIVATE_ICON, this.loadIcon("innerclass_private_obj.gif"));
        this.iconMap.put(INNER_CLASS_DEFAULT_ICON, this.loadIcon("innerclass_default_obj.gif"));
        this.iconMap.put(INTERFACE_ICON, this.loadIcon("int_obj.gif"));
        this.iconMap.put(JAVADOC_ITEM_ICON, this.loadIcon("jdoc_tag_obj.gif"));
        this.iconMap.put(LOCAL_VARIABLE_ICON, this.loadIcon("localvariable_obj.gif"));
        this.iconMap.put(METHOD_PUBLIC_ICON, this.loadIcon("methpub_obj.gif"));
        this.iconMap.put(METHOD_PROTECTED_ICON, this.loadIcon("methpro_obj.gif"));
        this.iconMap.put(METHOD_PRIVATE_ICON, this.loadIcon("methpri_obj.gif"));
        this.iconMap.put(METHOD_DEFAULT_ICON, this.loadIcon("methdef_obj.gif"));
        this.iconMap.put(TEMPLATE_ICON, this.loadIcon("template_obj.gif"));
        this.iconMap.put(FIELD_PUBLIC_ICON, this.loadIcon("field_public_obj.gif"));
        this.iconMap.put(FIELD_PROTECTED_ICON, this.loadIcon("field_protected_obj.gif"));
        this.iconMap.put(FIELD_PRIVATE_ICON, this.loadIcon("field_private_obj.gif"));
        this.iconMap.put(FIELD_DEFAULT_ICON, this.loadIcon("field_default_obj.gif"));
        this.iconMap.put(CONSTRUCTOR_ICON, this.loadIcon("constr_ovr.gif"));
        this.iconMap.put(DEPRECATED_ICON, this.loadIcon("deprecated.gif"));
        this.iconMap.put(ABSTRACT_ICON, this.loadIcon("abstract_co.gif"));
        this.iconMap.put(FINAL_ICON, this.loadIcon("final_co.gif"));
        this.iconMap.put(STATIC_ICON, this.loadIcon("static_co.gif"));
    }

    public static IconFactory get() {
        return INSTANCE;
    }

    public Icon getIcon(String key) {
        return this.getIcon(key, false);
    }

    public Icon getIcon(String key, boolean deprecated) {
        Icon icon = this.iconMap.get(key);
        if (deprecated) {
            DecoratableIcon di = new DecoratableIcon(16, icon);
            di.setDeprecated(deprecated);
            icon = di;
        }
        return icon;
    }

    public Icon getIcon(IconData data) {
        DecoratableIcon icon = new DecoratableIcon(16, this.getIcon(data.getIcon()));
        icon.setDeprecated(data.isDeprecated());
        if (data.isAbstract()) {
            icon.addDecorationIcon(this.getIcon(ABSTRACT_ICON));
        }
        if (data.isStatic()) {
            icon.addDecorationIcon(this.getIcon(STATIC_ICON));
        }
        if (data.isFinal()) {
            icon.addDecorationIcon(this.getIcon(FINAL_ICON));
        }
        return icon;
    }

    private Icon loadIcon(String name) {
        URL res = this.getClass().getResource("img/" + name);
        if (res == null) {
            throw new IllegalArgumentException("icon not found: img/" + name);
        }
        return new ImageIcon(res);
    }

    public static interface IconData {
        public String getIcon();

        public boolean isAbstract();

        public boolean isDeprecated();

        public boolean isFinal();

        public boolean isStatic();
    }
}

