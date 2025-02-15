/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletionInsertionInfo;
import org.fife.ui.autocomplete.TemplatePiece;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;

public class TemplateCompletion
extends AbstractCompletion
implements ParameterizedCompletion {
    private List<TemplatePiece> pieces;
    private String inputText;
    private String definitionString;
    private String shortDescription;
    private String summary;
    private List<ParameterizedCompletion.Parameter> params;

    public TemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template) {
        this(provider, inputText, definitionString, template, null, null);
    }

    public TemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template, String shortDescription, String summary) {
        super(provider);
        this.inputText = inputText;
        this.definitionString = definitionString;
        this.shortDescription = shortDescription;
        this.summary = summary;
        this.pieces = new ArrayList<TemplatePiece>(3);
        this.params = new ArrayList<ParameterizedCompletion.Parameter>(3);
        this.parse(template);
    }

    private void addTemplatePiece(TemplatePiece piece) {
        this.pieces.add(piece);
        if (piece instanceof TemplatePiece.Param && !"cursor".equals(piece.getText())) {
            Object type = null;
            ParameterizedCompletion.Parameter param = new ParameterizedCompletion.Parameter(type, piece.getText());
            this.params.add(param);
        }
    }

    @Override
    public String getInputText() {
        return this.inputText;
    }

    private String getPieceText(int index, String leadingWS) {
        TemplatePiece piece = this.pieces.get(index);
        String text = piece.getText();
        if (text.indexOf(10) > -1) {
            text = text.replaceAll("\n", "\n" + leadingWS);
        }
        return text;
    }

    @Override
    public String getReplacementText() {
        return null;
    }

    @Override
    public String getSummary() {
        return this.summary;
    }

    @Override
    public String getDefinitionString() {
        return this.definitionString;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    @Override
    public boolean getShowParameterToolTip() {
        return false;
    }

    @Override
    public ParameterizedCompletionInsertionInfo getInsertionInfo(JTextComponent tc, boolean replaceTabsWithSpaces) {
        String leadingWS;
        int selStart;
        int dot;
        ParameterizedCompletionInsertionInfo info = new ParameterizedCompletionInsertionInfo();
        StringBuilder sb = new StringBuilder();
        int minPos = dot = tc.getCaretPosition();
        Position maxPos = null;
        int defaultEndOffs = -1;
        try {
            maxPos = tc.getDocument().createPosition(dot);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        info.setCaretRange(minPos, maxPos);
        int selEnd = selStart = dot;
        Document doc = tc.getDocument();
        try {
            leadingWS = RSyntaxUtilities.getLeadingWhitespace(doc, dot);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            leadingWS = "";
        }
        int start = dot;
        for (int i = 0; i < this.pieces.size(); ++i) {
            TemplatePiece piece = this.pieces.get(i);
            String text = this.getPieceText(i, leadingWS);
            if (piece instanceof TemplatePiece.Text) {
                if (replaceTabsWithSpaces) {
                    start = this.possiblyReplaceTabsWithSpaces(sb, text, tc, start);
                    continue;
                }
                sb.append(text);
                start += text.length();
                continue;
            }
            if (piece instanceof TemplatePiece.Param && "cursor".equals(text)) {
                defaultEndOffs = start;
                continue;
            }
            int end = start + text.length();
            sb.append(text);
            if (piece instanceof TemplatePiece.Param) {
                info.addReplacementLocation(start, end);
                if (selStart == dot) {
                    selStart = start;
                    selEnd = selStart + text.length();
                }
            } else if (piece instanceof TemplatePiece.ParamCopy) {
                info.addReplacementCopy(piece.getText(), start, end);
            }
            start = end;
        }
        if (selStart == minPos && selStart == selEnd && this.getParamCount() == 0 && defaultEndOffs > -1) {
            selStart = selEnd = defaultEndOffs;
        }
        info.setInitialSelection(selStart, selEnd);
        if (defaultEndOffs > -1) {
            info.addReplacementLocation(defaultEndOffs, defaultEndOffs);
        }
        info.setDefaultEndOffs(defaultEndOffs);
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

    private boolean isParamDefined(String name) {
        for (int i = 0; i < this.getParamCount(); ++i) {
            ParameterizedCompletion.Parameter param = this.getParam(i);
            if (!name.equals(param.getName())) continue;
            return true;
        }
        return false;
    }

    private void parse(String template) {
        int offs;
        int lastOffs = 0;
        while ((offs = template.indexOf(36, lastOffs)) > -1 && offs < template.length() - 1) {
            char next = template.charAt(offs + 1);
            switch (next) {
                case '$': {
                    this.addTemplatePiece(new TemplatePiece.Text(template.substring(lastOffs, offs + 1)));
                    lastOffs = offs + 2;
                    break;
                }
                case '{': {
                    int closingCurly = template.indexOf(125, offs + 2);
                    if (closingCurly <= -1) break;
                    this.addTemplatePiece(new TemplatePiece.Text(template.substring(lastOffs, offs)));
                    String varName = template.substring(offs + 2, closingCurly);
                    if (!"cursor".equals(varName) && this.isParamDefined(varName)) {
                        this.addTemplatePiece(new TemplatePiece.ParamCopy(varName));
                    } else {
                        this.addTemplatePiece(new TemplatePiece.Param(varName));
                    }
                    lastOffs = closingCurly + 1;
                    break;
                }
            }
        }
        if (lastOffs < template.length()) {
            String text = template.substring(lastOffs);
            this.addTemplatePiece(new TemplatePiece.Text(text));
        }
    }

    private int possiblyReplaceTabsWithSpaces(StringBuilder sb, String text, JTextComponent tc, int start) {
        int tab = text.indexOf(9);
        if (tab > -1) {
            Integer i;
            int startLen = sb.length();
            int size = 4;
            Document doc = tc.getDocument();
            if (doc != null && (i = (Integer)doc.getProperty("tabSize")) != null) {
                size = i;
            }
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < size; ++i2) {
                sb2.append(' ');
            }
            String tabStr = sb2.toString();
            int lastOffs = 0;
            do {
                sb.append(text, lastOffs, tab);
                sb.append(tabStr);
            } while ((tab = text.indexOf(9, lastOffs = tab + 1)) > -1);
            sb.append(text.substring(lastOffs));
            start += sb.length() - startLen;
        } else {
            sb.append(text);
            start += text.length();
        }
        return start;
    }

    public void setShortDescription(String shortDesc) {
        this.shortDescription = shortDesc;
    }

    @Override
    public String toString() {
        return this.getDefinitionString();
    }
}

