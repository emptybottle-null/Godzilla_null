/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletionInsertionInfo;
import org.fife.ui.autocomplete.SuppressFBWarnings;
import org.fife.ui.autocomplete.VariableCompletion;

public class FunctionCompletion
extends VariableCompletion
implements ParameterizedCompletion {
    private List<ParameterizedCompletion.Parameter> params;
    private String returnValDesc;
    private String compareString;

    public FunctionCompletion(CompletionProvider provider, String name, String returnType) {
        super(provider, name, returnType);
    }

    @Override
    protected void addDefinitionString(StringBuilder sb) {
        sb.append("<html><b>");
        sb.append(this.getDefinitionString());
        sb.append("</b>");
    }

    protected void addParameters(StringBuilder sb) {
        int paramCount = this.getParamCount();
        if (paramCount > 0) {
            sb.append("<b>Parameters:</b><br>");
            sb.append("<center><table width='90%'><tr><td>");
            for (int i = 0; i < paramCount; ++i) {
                ParameterizedCompletion.Parameter param = this.getParam(i);
                sb.append("<b>");
                sb.append(param.getName() != null ? param.getName() : param.getType());
                sb.append("</b>&nbsp;");
                String desc = param.getDescription();
                if (desc != null) {
                    sb.append(desc);
                }
                sb.append("<br>");
            }
            sb.append("</td></tr></table></center><br><br>");
        }
        if (this.returnValDesc != null) {
            sb.append("<b>Returns:</b><br><center><table width='90%'><tr><td>");
            sb.append(this.returnValDesc);
            sb.append("</td></tr></table></center><br><br>");
        }
    }

    @Override
    public int compareTo(Completion c2) {
        int rc = c2 == this ? 0 : (c2 instanceof FunctionCompletion ? this.getCompareString().compareTo(((FunctionCompletion)c2).getCompareString()) : super.compareTo(c2));
        return rc;
    }

    public boolean equals(Object other) {
        return other instanceof Completion && this.compareTo((Completion)other) == 0;
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
        StringBuilder sb = new StringBuilder();
        String type = this.getType();
        if (type != null) {
            sb.append(type).append(' ');
        }
        sb.append(this.getName());
        CompletionProvider provider = this.getProvider();
        char start = provider.getParameterListStart();
        if (start != '\u0000') {
            sb.append(start);
        }
        for (int i = 0; i < this.getParamCount(); ++i) {
            ParameterizedCompletion.Parameter param = this.getParam(i);
            type = param.getType();
            String name = param.getName();
            if (type != null) {
                sb.append(type);
                if (name != null) {
                    sb.append(' ');
                }
            }
            if (name != null) {
                sb.append(name);
            }
            if (i >= this.params.size() - 1) continue;
            sb.append(provider.getParameterListSeparator());
        }
        char end = provider.getParameterListEnd();
        if (end != '\u0000') {
            sb.append(end);
        }
        return sb.toString();
    }

    @Override
    public ParameterizedCompletionInsertionInfo getInsertionInfo(JTextComponent tc, boolean replaceTabsWithSpaces) {
        ParameterizedCompletionInsertionInfo info = new ParameterizedCompletionInsertionInfo();
        StringBuilder sb = new StringBuilder();
        char paramListStart = this.getProvider().getParameterListStart();
        if (paramListStart != '\u0000') {
            sb.append(paramListStart);
        }
        int dot = tc.getCaretPosition() + sb.length();
        int paramCount = this.getParamCount();
        int minPos = dot;
        Position maxPos = null;
        try {
            maxPos = tc.getDocument().createPosition(dot - sb.length() + 1);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        info.setCaretRange(minPos, maxPos);
        int firstParamLen = 0;
        int start = dot;
        for (int i = 0; i < paramCount; ++i) {
            ParameterizedCompletion.Parameter param = this.getParam(i);
            String paramText = this.getParamText(param);
            if (i == 0) {
                firstParamLen = paramText.length();
            }
            sb.append(paramText);
            int end = start + paramText.length();
            info.addReplacementLocation(start, end);
            String sep = this.getProvider().getParameterListSeparator();
            if (i >= paramCount - 1 || sep == null) continue;
            sb.append(sep);
            start = end + sep.length();
        }
        sb.append(this.getProvider().getParameterListEnd());
        int endOffs = dot + sb.length();
        info.addReplacementLocation(--endOffs, endOffs);
        info.setDefaultEndOffs(endOffs);
        int selectionEnd = paramCount > 0 ? dot + firstParamLen : dot;
        info.setInitialSelection(dot, selectionEnd);
        info.setTextToInsert(sb.toString());
        return info;
    }

    @Override
    public ParameterizedCompletion.Parameter getParam(int index) {
        return this.params.get(index);
    }

    @Override
    public int getParamCount() {
        return this.params == null ? 0 : this.params.size();
    }

    @Override
    public boolean getShowParameterToolTip() {
        return true;
    }

    private String getParamText(ParameterizedCompletion.Parameter param) {
        String text = param.getName();
        if (text == null && (text = param.getType()) == null) {
            text = "arg";
        }
        return text;
    }

    public String getReturnValueDescription() {
        return this.returnValDesc;
    }

    @Override
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        this.addDefinitionString(sb);
        if (!this.possiblyAddDescription(sb)) {
            sb.append("<br><br><br>");
        }
        this.addParameters(sb);
        this.possiblyAddDefinedIn(sb);
        return sb.toString();
    }

    @Override
    @SuppressFBWarnings(value={"RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE"}, justification="Subclasses could return null")
    public String getToolTipText() {
        String text = this.getSummary();
        if (text == null) {
            text = this.getDefinitionString();
        }
        return text;
    }

    public int hashCode() {
        int hashCode = super.hashCode();
        for (int i = 0; i < this.getParamCount(); ++i) {
            hashCode ^= this.getParam(i).hashCode();
        }
        hashCode ^= this.returnValDesc != null ? this.returnValDesc.hashCode() : 0;
        return hashCode ^= this.compareString != null ? this.compareString.hashCode() : 0;
    }

    public void setParams(List<ParameterizedCompletion.Parameter> params) {
        if (params != null) {
            this.params = new ArrayList<ParameterizedCompletion.Parameter>(params);
        }
    }

    public void setReturnValueDescription(String desc) {
        this.returnValDesc = desc;
    }
}

