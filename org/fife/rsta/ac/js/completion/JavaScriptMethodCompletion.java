/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import java.util.ArrayList;
import javax.swing.Icon;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class JavaScriptMethodCompletion
extends FunctionCompletion
implements JSCompletion {
    private Method method;
    private String compareString;
    private boolean systemFunction;
    private String nameAndParameters;

    public JavaScriptMethodCompletion(CompletionProvider provider, Method method) {
        super(provider, method.getName(), null);
        this.method = method;
        int count = method.getParameterCount();
        ArrayList<ParameterizedCompletion.Parameter> params = new ArrayList<ParameterizedCompletion.Parameter>(count);
        for (int i = 0; i < count; ++i) {
            FormalParameter param = method.getParameter(i);
            String name = param.getName();
            params.add(new ParameterizedCompletion.Parameter(null, name));
        }
        this.setParams(params);
    }

    private String createNameAndParameters() {
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append('(');
        int count = this.method.getParameterCount();
        for (int i = 0; i < count; ++i) {
            FormalParameter fp = this.method.getParameter(i);
            sb.append(fp.getName());
            if (i >= count - 1) continue;
            sb.append(", ");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon(this.systemFunction ? "function" : "default_function");
    }

    @Override
    public int getRelevance() {
        return this.systemFunction ? 3 : 4;
    }

    public void setSystemFunction(boolean systemFunction) {
        this.systemFunction = systemFunction;
    }

    public boolean isSystemFunction() {
        return this.systemFunction;
    }

    @Override
    public String getSummary() {
        String summary = this.getMethodSummary();
        if (summary != null && summary.startsWith("/**")) {
            summary = Util.docCommentToHtml(summary);
        }
        return summary;
    }

    public String getSignature() {
        if (this.nameAndParameters == null) {
            this.nameAndParameters = this.createNameAndParameters();
        }
        return this.nameAndParameters;
    }

    @Override
    public int hashCode() {
        return this.getCompareString().hashCode();
    }

    @Override
    public String toString() {
        return this.getSignature();
    }

    private String getMethodSummary() {
        String docComment = this.method.getDocComment();
        return docComment != null ? docComment : this.method.toString();
    }

    @Override
    public int compareTo(Completion other) {
        int rc = -1;
        if (other == this) {
            rc = 0;
        } else if (other instanceof JSCompletion) {
            JSCompletion c2 = (JSCompletion)other;
            rc = this.getLookupName().compareTo(c2.getLookupName());
        } else if (other != null) {
            Completion c2 = other;
            rc = this.toString().compareTo(c2.toString());
            if (rc == 0) {
                String clazz1 = this.getClass().getName();
                clazz1 = clazz1.substring(clazz1.lastIndexOf(46));
                String clazz2 = c2.getClass().getName();
                clazz2 = clazz2.substring(clazz2.lastIndexOf(46));
                rc = clazz1.compareTo(clazz2);
            }
        }
        return rc;
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
    public String getDefinitionString() {
        return this.getSignature();
    }

    @Override
    public String getType(boolean qualified) {
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType("void", qualified);
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        return null;
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
}

