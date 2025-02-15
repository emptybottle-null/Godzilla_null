/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.AbstractJavaSourceCompletion;
import org.fife.rsta.ac.java.FieldData;
import org.fife.rsta.ac.java.FieldInfoData;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.MethodCompletion;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.ui.autocomplete.CompletionProvider;

class FieldCompletion
extends AbstractJavaSourceCompletion
implements MemberCompletion {
    private MemberCompletion.Data data;
    private static final int RELEVANCE = 3;

    public FieldCompletion(CompletionProvider provider, Field field) {
        super(provider, field.getName());
        this.data = new FieldData(field);
        this.setRelevance(3);
    }

    public FieldCompletion(CompletionProvider provider, FieldInfo info) {
        super(provider, info.getName());
        this.data = new FieldInfoData(info, (SourceCompletionProvider)provider);
        this.setRelevance(3);
    }

    private FieldCompletion(CompletionProvider provider, String text) {
        super(provider, text);
        this.setRelevance(3);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FieldCompletion && ((FieldCompletion)obj).getSignature().equals(this.getSignature());
    }

    public static FieldCompletion createLengthCompletion(CompletionProvider provider, final Type type) {
        FieldCompletion fc = new FieldCompletion(provider, type.toString());
        fc.data = new MemberCompletion.Data(){

            @Override
            public String getEnclosingClassName(boolean fullyQualified) {
                return type.getName(fullyQualified);
            }

            @Override
            public String getIcon() {
                return "fieldPublicIcon";
            }

            @Override
            public String getSignature() {
                return "length";
            }

            @Override
            public String getSummary() {
                return null;
            }

            @Override
            public String getType() {
                return "int";
            }

            @Override
            public boolean isConstructor() {
                return false;
            }

            @Override
            public boolean isDeprecated() {
                return false;
            }

            @Override
            public boolean isAbstract() {
                return false;
            }

            @Override
            public boolean isFinal() {
                return false;
            }

            @Override
            public boolean isStatic() {
                return false;
            }
        };
        return fc;
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
    public String getType() {
        return this.data.getType();
    }

    public int hashCode() {
        return this.getSignature().hashCode();
    }

    @Override
    public boolean isDeprecated() {
        return this.data.isDeprecated();
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        MethodCompletion.rendererText(this, g, x, y, selected);
    }
}

