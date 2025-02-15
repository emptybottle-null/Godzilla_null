/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.fife.ui.autocomplete.EmptyIcon;

public class IconFactory {
    public static final String FUNCTION_ICON = "function";
    public static final String LOCAL_VARIABLE_ICON = "local_variable";
    public static final String TEMPLATE_ICON = "template";
    public static final String EMPTY_ICON = "empty";
    public static final String GLOBAL_VARIABLE_ICON = "global_variable";
    public static final String DEFAULT_FUNCTION_ICON = "default_function";
    public static final String PUBLIC_STATIC_FUNCTION_ICON = "public_static_function";
    public static final String STATIC_VAR_ICON = "static_var";
    public static final String DEFAULT_VARIABLE_ICON = "default_variable";
    public static final String DEFAULT_CLASS_ICON = "default_class";
    public static final String PUBLIC_METHOD_ICON = "methpub_obj";
    public static final String PUBLIC_FIELD_ICON = "field_public_obj";
    public static final String JSDOC_ITEM_ICON = "jsdoc_item";
    private Map<String, Icon> iconMap = new HashMap<String, Icon>();
    private static final IconFactory INSTANCE = new IconFactory();

    private IconFactory() {
        this.iconMap.put(FUNCTION_ICON, this.loadIcon("methpub_obj.gif"));
        this.iconMap.put(PUBLIC_STATIC_FUNCTION_ICON, this.loadIcon("methpub_static.gif"));
        this.iconMap.put(LOCAL_VARIABLE_ICON, this.loadIcon("localvariable_obj.gif"));
        this.iconMap.put(GLOBAL_VARIABLE_ICON, this.loadIcon("field_public_obj.gif"));
        this.iconMap.put(TEMPLATE_ICON, this.loadIcon("template_obj.gif"));
        this.iconMap.put(DEFAULT_FUNCTION_ICON, this.loadIcon("methdef_obj.gif"));
        this.iconMap.put(STATIC_VAR_ICON, this.loadIcon("static_co.gif"));
        this.iconMap.put(DEFAULT_VARIABLE_ICON, this.loadIcon("field_default_obj.gif"));
        this.iconMap.put(DEFAULT_CLASS_ICON, this.loadIcon("class_obj.gif"));
        this.iconMap.put(PUBLIC_METHOD_ICON, this.loadIcon("methpub_obj.gif"));
        this.iconMap.put(PUBLIC_FIELD_ICON, this.loadIcon("field_public_obj.gif"));
        this.iconMap.put(JSDOC_ITEM_ICON, this.loadIcon("jdoc_tag_obj.gif"));
        this.iconMap.put(EMPTY_ICON, new EmptyIcon(16));
    }

    private Icon getIconImage(String name) {
        return this.iconMap.get(name);
    }

    public static Icon getIcon(String name) {
        return INSTANCE.getIconImage(name);
    }

    public static String getEmptyIcon() {
        return EMPTY_ICON;
    }

    private Icon loadIcon(String name) {
        name = "org/fife/rsta/ac/js/img/" + name;
        URL res = this.getClass().getClassLoader().getResource(name);
        if (res == null) {
            throw new IllegalArgumentException("icon not found: " + name);
        }
        return new ImageIcon(res);
    }
}

