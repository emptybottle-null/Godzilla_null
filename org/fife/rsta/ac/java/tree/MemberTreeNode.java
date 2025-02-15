/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.tree;

import javax.swing.Icon;
import org.fife.rsta.ac.java.DecoratableIcon;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.tree.JavaTreeNode;
import org.fife.ui.autocomplete.Util;

class MemberTreeNode
extends JavaTreeNode {
    private String text;

    public MemberTreeNode(CodeBlock cb) {
        super(cb);
        this.text = "<html>" + cb.getName();
        IconFactory fact = IconFactory.get();
        Icon base = fact.getIcon("methodPrivateIcon");
        DecoratableIcon di = new DecoratableIcon(base);
        int priority = 3;
        if (cb.isStatic()) {
            di.addDecorationIcon(fact.getIcon("staticIcon"));
            priority -= 16;
        }
        this.setIcon(di);
        this.setSortPriority(priority);
    }

    public MemberTreeNode(Field field) {
        super(field);
        Modifiers mods = field.getModifiers();
        String icon = mods == null ? "fieldDefaultIcon" : (mods.isPrivate() ? "fieldPrivateIcon" : (mods.isProtected() ? "fieldProtectedIcon" : (mods.isPublic() ? "fieldPublicIcon" : "fieldDefaultIcon")));
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(field.getName());
        sb.append(" : ");
        sb.append("<font color='#888888'>");
        MemberTreeNode.appendType(field.getType(), sb);
        this.text = sb.toString();
        int priority = 1;
        IconFactory fact = IconFactory.get();
        Icon base = fact.getIcon(icon);
        DecoratableIcon di = new DecoratableIcon(base);
        di.setDeprecated(field.isDeprecated());
        if (mods != null) {
            if (mods.isStatic()) {
                di.addDecorationIcon(fact.getIcon("staticIcon"));
                priority -= 16;
            }
            if (mods.isFinal()) {
                di.addDecorationIcon(fact.getIcon("finalIcon"));
            }
        }
        this.setIcon(di);
        this.setSortPriority(priority);
    }

    public MemberTreeNode(Method method) {
        super(method);
        int priority = 3;
        Modifiers mods = method.getModifiers();
        String icon = mods == null ? "methodDefaultIcon" : (mods.isPrivate() ? "methodPrivateIcon" : (mods.isProtected() ? "methodProtectedIcon" : (mods.isPublic() ? "methodPublicIcon" : "methodDefaultIcon")));
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(method.getName());
        sb.append('(');
        int paramCount = method.getParameterCount();
        for (int i = 0; i < paramCount; ++i) {
            FormalParameter param = method.getParameter(i);
            MemberTreeNode.appendType(param.getType(), sb);
            if (i >= paramCount - 1) continue;
            sb.append(", ");
        }
        sb.append(')');
        if (method.getType() != null) {
            sb.append(" : ");
            sb.append("<font color='#888888'>");
            MemberTreeNode.appendType(method.getType(), sb);
        }
        this.text = sb.toString();
        IconFactory fact = IconFactory.get();
        Icon base = fact.getIcon(icon);
        DecoratableIcon di = new DecoratableIcon(base);
        di.setDeprecated(method.isDeprecated());
        if (mods != null) {
            if (mods.isAbstract()) {
                di.addDecorationIcon(fact.getIcon("abstractIcon"));
            }
            if (method.isConstructor()) {
                di.addDecorationIcon(fact.getIcon("constructorIcon"));
                priority = 2;
            }
            if (mods.isStatic()) {
                di.addDecorationIcon(fact.getIcon("staticIcon"));
                priority -= 16;
            }
            if (mods.isFinal()) {
                di.addDecorationIcon(fact.getIcon("finalIcon"));
            }
        }
        this.setIcon(di);
        this.setSortPriority(priority);
    }

    static void appendType(Type type, StringBuilder sb) {
        if (type != null) {
            String t = type.toString();
            t = t.replaceAll("<", "&lt;");
            t = t.replaceAll(">", "&gt;");
            sb.append(t);
        }
    }

    @Override
    public String getText(boolean selected) {
        return selected ? Util.stripHtml(this.text).replaceAll("&lt;", "<").replaceAll("&gt;", ">") : this.text;
    }
}

