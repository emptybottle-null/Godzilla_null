/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.rsta.ac.js.completion.JSMethodData;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class JSFunctionCompletion
extends FunctionCompletion
implements JSCompletion {
    private JSMethodData methodData;
    private String compareString;
    private String nameAndParameters;

    public JSFunctionCompletion(CompletionProvider provider, MethodInfo method) {
        this(provider, method, false);
    }

    public JSFunctionCompletion(CompletionProvider provider, MethodInfo methodInfo, boolean showParameterType) {
        super(provider, JSFunctionCompletion.getMethodName(methodInfo, provider), null);
        this.methodData = new JSMethodData(methodInfo, ((SourceCompletionProvider)provider).getJarManager());
        List<ParameterizedCompletion.Parameter> params = this.populateParams(this.methodData, showParameterType);
        this.setParams(params);
    }

    private static String getMethodName(MethodInfo info, CompletionProvider provider) {
        if (info.isConstructor()) {
            return ((SourceCompletionProvider)provider).getTypesFactory().convertJavaScriptType(info.getClassFile().getClassName(true), false);
        }
        return info.getName();
    }

    private List<ParameterizedCompletion.Parameter> populateParams(JSMethodData methodData, boolean showParameterType) {
        MethodInfo methodInfo = methodData.getMethodInfo();
        int count = methodInfo.getParameterCount();
        String[] paramTypes = methodInfo.getParameterTypes();
        ArrayList<ParameterizedCompletion.Parameter> params = new ArrayList<ParameterizedCompletion.Parameter>(count);
        for (int i = 0; i < count; ++i) {
            String name = methodData.getParameterName(i);
            String type = methodData.getParameterType(paramTypes, i, this.getProvider());
            params.add(new JSFunctionParam(type, name, showParameterType, this.getProvider()));
        }
        return params;
    }

    @Override
    public int compareTo(Completion other) {
        int rc = -1;
        if (other == this) {
            rc = 0;
        } else if (other instanceof JSCompletion) {
            JSCompletion c2 = (JSCompletion)other;
            rc = this.getLookupName().compareTo(c2.getLookupName());
        } else if (other != null && (rc = this.toString().compareTo(other.toString())) == 0) {
            String clazz1 = this.getClass().getName();
            clazz1 = clazz1.substring(clazz1.lastIndexOf(46));
            String clazz2 = other.getClass().getName();
            clazz2 = clazz2.substring(clazz2.lastIndexOf(46));
            rc = clazz1.compareTo(clazz2);
        }
        return rc;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JSCompletion && ((JSCompletion)obj).getLookupName().equals(this.getLookupName());
    }

    @Override
    public String getAlreadyEntered(JTextComponent comp) {
        String temp = this.getProvider().getAlreadyEnteredText(comp);
        int lastDot = JavaScriptHelper.findLastIndexOfJavaScriptIdentifier(temp);
        if (lastDot > -1) {
            temp = temp.substring(lastDot + 1);
        }
        return temp;
    }

    private String getCompareString() {
        if (this.compareString == null) {
            this.compareString = this.getLookupName();
        }
        return this.compareString;
    }

    @Override
    public String getLookupName() {
        SourceCompletionProvider provider = (SourceCompletionProvider)this.getProvider();
        return provider.getJavaScriptEngine().getJavaScriptResolver(provider).getLookupText(this.methodData, this.getName());
    }

    @Override
    public String getDefinitionString() {
        return this.getSignature();
    }

    private String getMethodSummary() {
        String summary;
        Method method = this.methodData.getMethod();
        String string = summary = method != null ? method.getDocComment() : null;
        if (summary != null && summary.startsWith("/**")) {
            summary = Util.docCommentToHtml(summary);
        }
        return summary != null ? summary : this.getNameAndParameters();
    }

    private String getNameAndParameters() {
        if (this.nameAndParameters == null) {
            this.nameAndParameters = JSFunctionCompletion.formatMethodAtString(this.getName(), this.methodData);
        }
        return this.nameAndParameters;
    }

    private static String formatMethodAtString(String name, JSMethodData method) {
        StringBuilder sb = new StringBuilder(name);
        sb.append('(');
        int count = method.getParameterCount();
        for (int i = 0; i < count; ++i) {
            sb.append(method.getParameterName(i));
            if (i >= count - 1) continue;
            sb.append(", ");
        }
        sb.append(')');
        return sb.toString();
    }

    public String getSignature() {
        return this.getNameAndParameters();
    }

    @Override
    public String getSummary() {
        String summary = this.getMethodSummary();
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
    public String toString() {
        return this.getSignature();
    }

    @Override
    public String getType() {
        String value = this.getType(true);
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType(value, false);
    }

    @Override
    public String getType(boolean qualified) {
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType(this.methodData.getType(qualified), qualified);
    }

    @Override
    public Icon getIcon() {
        return this.methodData.isStatic() ? IconFactory.getIcon("public_static_function") : IconFactory.getIcon("default_function");
    }

    @Override
    public int getRelevance() {
        return 4;
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        return this.methodData.getEnclosingClassName(fullyQualified);
    }

    public JSMethodData getMethodData() {
        return this.methodData;
    }

    public static class JSFunctionParam
    extends ParameterizedCompletion.Parameter {
        private boolean showParameterType;
        private CompletionProvider provider;

        public JSFunctionParam(Object type, String name, boolean showParameterType, CompletionProvider provider) {
            super(type, name);
            this.showParameterType = showParameterType;
            this.provider = provider;
        }

        @Override
        public String getType() {
            return this.showParameterType ? ((SourceCompletionProvider)this.provider).getTypesFactory().convertJavaScriptType(super.getType(), false) : null;
        }
    }
}

