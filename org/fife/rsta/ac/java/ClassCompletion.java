/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.Icon;
import org.fife.rsta.ac.java.AbstractJavaSourceCompletion;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

class ClassCompletion
extends AbstractJavaSourceCompletion {
    private ClassFile cf;

    public ClassCompletion(CompletionProvider provider, ClassFile cf) {
        super(provider, cf.getClassName(false));
        this.cf = cf;
    }

    @Override
    public int compareTo(Completion c2) {
        if (c2 == this) {
            return 0;
        }
        if (c2.toString().equalsIgnoreCase(this.toString()) && c2 instanceof ClassCompletion) {
            ClassCompletion cc2 = (ClassCompletion)c2;
            return this.getClassName(true).compareTo(cc2.getClassName(true));
        }
        return super.compareTo(c2);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassCompletion && ((ClassCompletion)obj).getReplacementText().equals(this.getReplacementText());
    }

    public String getClassName(boolean fullyQualified) {
        return this.cf.getClassName(fullyQualified);
    }

    @Override
    public Icon getIcon() {
        boolean isInterface = false;
        boolean isPublic = false;
        boolean isDefault = false;
        int access = this.cf.getAccessFlags();
        if ((access & 0x200) > 0) {
            isInterface = true;
        } else if (org.fife.rsta.ac.java.classreader.Util.isPublic(access)) {
            isPublic = true;
        } else {
            isDefault = true;
        }
        IconFactory fact = IconFactory.get();
        String key = null;
        if (isInterface) {
            key = isDefault ? "defaultInterfaceIcon" : "interfaceIcon";
        } else if (isDefault) {
            key = "defaultClassIcon";
        } else if (isPublic) {
            key = "classIcon";
        }
        return fact.getIcon(key, this.cf.isDeprecated());
    }

    public String getPackageName() {
        return this.cf.getPackageName();
    }

    @Override
    public String getSummary() {
        CompilationUnit cu;
        SourceCompletionProvider scp = (SourceCompletionProvider)this.getProvider();
        SourceLocation loc = scp.getSourceLocForClass(this.cf.getClassName(true));
        if (loc != null && (cu = Util.getCompilationUnitFromDisk(loc, this.cf)) != null) {
            Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
            while (i.hasNext()) {
                String summary;
                TypeDeclaration td = i.next();
                String typeName = td.getName();
                if (!typeName.equals(this.cf.getClassName(false)) || (summary = td.getDocComment()) == null || !summary.startsWith("/**")) continue;
                return Util.docCommentToHtml(summary);
            }
        }
        return this.cf.getClassName(true);
    }

    @Override
    public String getToolTipText() {
        return "class " + this.getReplacementText();
    }

    public int hashCode() {
        return this.getReplacementText().hashCode();
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        String s = this.cf.getClassName(false);
        g.drawString(s, x, y);
        FontMetrics fm = g.getFontMetrics();
        int newX = x + fm.stringWidth(s);
        if (this.cf.isDeprecated()) {
            int midY = y + fm.getDescent() - fm.getHeight() / 2;
            g.drawLine(x, midY, newX, midY);
        }
        x = newX;
        s = " - ";
        g.drawString(s, x, y);
        x += fm.stringWidth(s);
        String pkgName = this.cf.getClassName(true);
        int lastIndexOf = pkgName.lastIndexOf(46);
        if (lastIndexOf != -1) {
            pkgName = pkgName.substring(0, lastIndexOf);
            Color origColor = g.getColor();
            if (!selected) {
                g.setColor(Color.GRAY);
            }
            g.drawString(pkgName, x, y);
            if (!selected) {
                g.setColor(origColor);
            }
        }
    }
}

