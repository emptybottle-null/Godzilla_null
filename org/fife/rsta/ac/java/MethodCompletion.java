/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.MethodData;
import org.fife.rsta.ac.java.MethodInfoData;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;

class MethodCompletion
extends FunctionCompletion
implements MemberCompletion {
    private MemberCompletion.Data data;
    private String compareString;
    private static final int NON_CONSTRUCTOR_RELEVANCE = 2;

    public MethodCompletion(CompletionProvider provider, Method m) {
        super(provider, m.getName(), m.getType() == null ? "void" : m.getType().toString());
        this.setDefinedIn(m.getParentTypeDeclaration().getName());
        this.data = new MethodData(m);
        this.setRelevanceAppropriately();
        int count = m.getParameterCount();
        ArrayList<ParameterizedCompletion.Parameter> params = new ArrayList<ParameterizedCompletion.Parameter>(count);
        for (int i = 0; i < count; ++i) {
            FormalParameter param = m.getParameter(i);
            Type type = param.getType();
            String name = param.getName();
            params.add(new ParameterizedCompletion.Parameter(type, name));
        }
        this.setParams(params);
    }

    public MethodCompletion(CompletionProvider provider, MethodInfo info) {
        super(provider, info.getName(), info.getReturnTypeString(false));
        this.setDefinedIn(info.getClassFile().getClassName(false));
        this.data = new MethodInfoData(info, (SourceCompletionProvider)provider);
        this.setRelevanceAppropriately();
        String[] paramTypes = info.getParameterTypes();
        ArrayList<ParameterizedCompletion.Parameter> params = new ArrayList<ParameterizedCompletion.Parameter>(paramTypes.length);
        for (int i = 0; i < paramTypes.length; ++i) {
            String name = ((MethodInfoData)this.data).getParameterName(i);
            String type = paramTypes[i].substring(paramTypes[i].lastIndexOf(46) + 1);
            params.add(new ParameterizedCompletion.Parameter(type, name));
        }
        this.setParams(params);
    }

    @Override
    public int compareTo(Completion c2) {
        int rc = -1;
        if (c2 == this) {
            rc = 0;
        } else if (c2 instanceof MethodCompletion) {
            rc = this.getCompareString().compareTo(((MethodCompletion)c2).getCompareString());
        } else if (c2 != null && (rc = this.toString().compareToIgnoreCase(c2.toString())) == 0) {
            String clazz1 = this.getClass().getName();
            clazz1 = clazz1.substring(clazz1.lastIndexOf(46));
            String clazz2 = c2.getClass().getName();
            clazz2 = clazz2.substring(clazz2.lastIndexOf(46));
            rc = clazz1.compareTo(clazz2);
        }
        return rc;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MethodCompletion && ((MethodCompletion)obj).getCompareString().equals(this.getCompareString());
    }

    @Override
    public String getAlreadyEntered(JTextComponent comp) {
        String temp = this.getProvider().getAlreadyEnteredText(comp);
        int lastDot = temp.lastIndexOf(46);
        if (lastDot > -1) {
            temp = temp.substring(lastDot + 1);
        }
        return temp;
    }

    private String getCompareString() {
        if (this.compareString == null) {
            StringBuilder sb = new StringBuilder(this.getName());
            int paramCount = this.getParamCount();
            if (paramCount < 10) {
                sb.append('0');
            }
            sb.append(paramCount);
            for (int i = 0; i < paramCount; ++i) {
                String type = this.getParam(i).getType();
                sb.append(type);
                if (i >= paramCount - 1) continue;
                sb.append(',');
            }
            this.compareString = sb.toString();
        }
        return this.compareString;
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        return this.data.getEnclosingClassName(fullyQualified);
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon(this.data);
    }

    @Override
    public String getSignature() {
        return this.data.getSignature();
    }

    @Override
    public String getSummary() {
        String summary = this.data.getSummary();
        if (summary != null && summary.startsWith("/**")) {
            summary = Util.docCommentToHtml(summary);
        }
        return summary;
    }

    @Override
    public int hashCode() {
        return this.getCompareString().hashCode();
    }

    @Override
    public boolean isDeprecated() {
        return this.data.isDeprecated();
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        MethodCompletion.rendererText(this, g, x, y, selected);
    }

    private void setRelevanceAppropriately() {
        if (!this.data.isConstructor()) {
            this.setRelevance(2);
        }
    }

    public static void rendererText(MemberCompletion mc, Graphics g, int x, int y, boolean selected) {
        String shortType = mc.getType();
        int dot = shortType.lastIndexOf(46);
        if (dot > -1) {
            shortType = shortType.substring(dot + 1);
        }
        String sig = mc.getSignature();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(sig, x, y);
        int newX = x + fm.stringWidth(sig);
        if (mc.isDeprecated()) {
            int midY = y + fm.getDescent() - fm.getHeight() / 2;
            g.drawLine(x, midY, newX, midY);
        }
        x = newX;
        StringBuilder sb = new StringBuilder(" : ").append(shortType);
        sb.append(" - ");
        String s = sb.toString();
        g.drawString(s, x, y);
        x += fm.stringWidth(s);
        Color origColor = g.getColor();
        if (!selected) {
            g.setColor(Color.GRAY);
        }
        g.drawString(mc.getEnclosingClassName(false), x, y);
        if (!selected) {
            g.setColor(origColor);
        }
    }

    @Override
    public String toString() {
        return this.getSignature();
    }
}

