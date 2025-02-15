/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;

class IconFactory {
    private static IconFactory INSTANCE;
    private Map<String, Icon> iconMap = new HashMap<String, Icon>();

    private IconFactory() {
    }

    public static IconFactory get() {
        if (INSTANCE == null) {
            INSTANCE = new IconFactory();
        }
        return INSTANCE;
    }

    public Icon getIcon(String key) {
        Icon icon = this.iconMap.get(key);
        if (icon == null) {
            icon = this.loadIcon(key + ".gif");
            this.iconMap.put(key, icon);
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
}

