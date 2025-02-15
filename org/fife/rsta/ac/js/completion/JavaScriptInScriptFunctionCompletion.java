/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import javax.swing.Icon;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;

public class JavaScriptInScriptFunctionCompletion
extends FunctionCompletion
implements JSCompletion {
    private TypeDeclaration returnType;

    public JavaScriptInScriptFunctionCompletion(CompletionProvider provider, String name, TypeDeclaration returnType) {
        super(provider, name, null);
        this.setRelevance(4);
        this.returnType = returnType;
    }

    @Override
    public String getSummary() {
        String summary = super.getShortDescription();
        if (summary != null && summary.startsWith("/**")) {
            summary = Util.docCommentToHtml(summary);
        }
        return summary;
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon("default_function");
    }

    @Override
    public String getLookupName() {
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append('(');
        int count = this.getParamCount();
        for (int i = 0; i < count; ++i) {
            sb.append("p");
            if (i >= count - 1) continue;
            sb.append(",");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String getType() {
        String value = this.getType(true);
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType(value, false);
    }

    @Override
    public String getType(boolean qualified) {
        String type = this.returnType != null ? this.returnType.getQualifiedName() : null;
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType(type, qualified);
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JSCompletion) {
            JSCompletion jsComp = (JSCompletion)obj;
            return this.getLookupName().equals(jsComp.getLookupName());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.getLookupName().hashCode();
    }

    @Override
    public String toString() {
        return this.getLookupName();
    }

    @Override
    public int compareTo(Completion other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof JSCompletion) {
            JSCompletion c2 = (JSCompletion)other;
            return this.getLookupName().compareTo(c2.getLookupName());
        }
        if (other != null) {
            return this.toString().compareTo(other.toString());
        }
        return -1;
    }
}

