/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.tree;

import javax.swing.Icon;
import org.fife.rsta.ac.java.DecoratableIcon;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.rjc.ast.EnumDeclaration;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.NormalInterfaceDeclaration;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.tree.JavaTreeNode;

class TypeDeclarationTreeNode
extends JavaTreeNode {
    public TypeDeclarationTreeNode(TypeDeclaration typeDec) {
        super(typeDec);
        String iconName = null;
        int priority = 0;
        if (typeDec instanceof NormalClassDeclaration) {
            NormalClassDeclaration ncd = (NormalClassDeclaration)typeDec;
            iconName = ncd.getModifiers() != null ? (ncd.getModifiers().isPublic() ? "classIcon" : (ncd.getModifiers().isProtected() ? "innerClassProtectedIcon" : (ncd.getModifiers().isPrivate() ? "innerClassPrivateIcon" : "innerClassDefaultIcon"))) : "defaultClassIcon";
        } else if (typeDec instanceof NormalInterfaceDeclaration) {
            NormalInterfaceDeclaration nid = (NormalInterfaceDeclaration)typeDec;
            iconName = nid.getModifiers() != null && nid.getModifiers().isPublic() ? "interfaceIcon" : "defaultInterfaceIcon";
        } else if (typeDec instanceof EnumDeclaration) {
            EnumDeclaration ed = (EnumDeclaration)typeDec;
            iconName = ed.getModifiers() != null ? (ed.getModifiers().isPublic() ? "enumIcon" : (ed.getModifiers().isProtected() ? "enumProtectedIcon" : (ed.getModifiers().isPrivate() ? "enumPrivateIcon" : "enumDefaultIcon"))) : "enumDefaultIcon";
        }
        IconFactory fact = IconFactory.get();
        Icon mainIcon = fact.getIcon(iconName);
        if (mainIcon == null) {
            System.out.println("*** " + typeDec);
        } else {
            DecoratableIcon di = new DecoratableIcon(mainIcon);
            di.setDeprecated(typeDec.isDeprecated());
            Modifiers mods = typeDec.getModifiers();
            if (mods != null) {
                if (mods.isAbstract()) {
                    di.addDecorationIcon(fact.getIcon("abstractIcon"));
                } else if (mods.isFinal()) {
                    di.addDecorationIcon(fact.getIcon("finalIcon"));
                }
                if (mods.isStatic()) {
                    di.addDecorationIcon(fact.getIcon("staticIcon"));
                    priority = -16;
                }
            }
            this.setIcon(di);
        }
        this.setSortPriority(priority);
    }

    @Override
    public String getText(boolean selected) {
        TypeDeclaration typeDec = (TypeDeclaration)this.getUserObject();
        return typeDec != null ? typeDec.getName() : null;
    }
}

