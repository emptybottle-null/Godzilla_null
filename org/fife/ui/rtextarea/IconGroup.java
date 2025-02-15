/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.AccessControlException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconGroup {
    private String path;
    private boolean separateLargeIcons;
    private String largeIconSubDir;
    private String extension;
    private String name;
    private String jarFile;
    private static final String DEFAULT_EXTENSION = "gif";

    public IconGroup(String name, String path) {
        this(name, path, null);
    }

    public IconGroup(String name, String path, String largeIconSubDir) {
        this(name, path, largeIconSubDir, DEFAULT_EXTENSION);
    }

    public IconGroup(String name, String path, String largeIconSubDir, String extension) {
        this(name, path, largeIconSubDir, extension, null);
    }

    public IconGroup(String name, String path, String largeIconSubDir, String extension, String jar) {
        this.name = name;
        this.path = path;
        if (path != null && path.length() > 0 && !path.endsWith("/")) {
            this.path = this.path + "/";
        }
        this.separateLargeIcons = largeIconSubDir != null;
        this.largeIconSubDir = largeIconSubDir;
        this.extension = extension != null ? extension : DEFAULT_EXTENSION;
        this.jarFile = jar;
    }

    public boolean equals(Object o2) {
        IconGroup ig2;
        if (o2 instanceof IconGroup && (ig2 = (IconGroup)o2).getName().equals(this.getName()) && this.separateLargeIcons == ig2.hasSeparateLargeIcons()) {
            if (this.separateLargeIcons && !this.largeIconSubDir.equals(ig2.largeIconSubDir)) {
                return false;
            }
            return this.path.equals(ig2.path);
        }
        return false;
    }

    public Icon getFileTypeIcon(String rstaSyntax) {
        int slash = rstaSyntax.indexOf(47);
        if (slash > -1) {
            String fileType = rstaSyntax.substring(slash + 1).toLowerCase();
            String path = "fileTypes/" + fileType + '.' + this.extension;
            Icon icon = this.getIconImpl(path);
            if (icon == null) {
                icon = this.getIconImpl("fileTypes/default." + this.extension);
            }
            return icon;
        }
        return null;
    }

    public Icon getIcon(String name) {
        Icon icon = this.getIconImpl(this.path + name + "." + this.extension);
        if (icon != null && (icon.getIconWidth() < 1 || icon.getIconHeight() < 1)) {
            icon = null;
        }
        return icon;
    }

    protected Icon getIconImpl(String iconFullPath) {
        try {
            if (this.jarFile == null) {
                URL url = this.getClass().getClassLoader().getResource(iconFullPath);
                if (url != null) {
                    return new ImageIcon(url);
                }
                BufferedImage image = ImageIO.read(new File(iconFullPath));
                return image != null ? new ImageIcon(image) : null;
            }
            URL url = new URL("jar:file:///" + this.jarFile + "!/" + iconFullPath);
            ImageIcon icon = new ImageIcon(url);
            return icon.getIconWidth() == -1 ? null : icon;
        } catch (IOException | AccessControlException ace) {
            return null;
        }
    }

    public Icon getLargeIcon(String name) {
        return this.getIconImpl(this.path + this.largeIconSubDir + "/" + name + "." + this.extension);
    }

    public String getName() {
        return this.name;
    }

    public boolean hasSeparateLargeIcons() {
        return this.separateLargeIcons;
    }

    public int hashCode() {
        return this.getName().hashCode();
    }
}

