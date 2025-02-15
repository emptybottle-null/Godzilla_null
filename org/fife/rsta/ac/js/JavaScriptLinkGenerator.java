/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import javax.swing.text.BadLocationException;
import org.fife.rsta.ac.js.JavaScriptLanguageSupport;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.ast.JavaScriptDeclaration;
import org.fife.rsta.ac.js.ast.VariableResolver;
import org.fife.ui.rsyntaxtextarea.LinkGenerator;
import org.fife.ui.rsyntaxtextarea.LinkGeneratorResult;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.SelectRegionLinkGeneratorResult;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

public class JavaScriptLinkGenerator
implements LinkGenerator {
    private JavaScriptLanguageSupport language;
    private boolean findLocal;
    private boolean findPreprocessed;
    private boolean findSystem;

    public JavaScriptLinkGenerator(JavaScriptLanguageSupport language) {
        this.language = language;
        this.findLocal = true;
    }

    @Override
    public LinkGeneratorResult isLinkAtOffset(RSyntaxTextArea textArea, int offs) {
        JavaScriptDeclaration dec = null;
        IsLinkableCheckResult result = this.checkForLinkableToken(textArea, offs);
        if (result != null) {
            JavaScriptParser parser;
            VariableResolver variableResolver;
            Token t = result.token;
            boolean function = result.function;
            String name = t.getLexeme();
            if (name != null && name.length() > 0 && (name.length() > 1 || name.length() == 1 && Character.isJavaIdentifierPart(name.charAt(0)))) {
                this.language.reparseDocument(offs);
            }
            if ((variableResolver = (parser = this.language.getJavaScriptParser()).getVariablesAndFunctions()) != null) {
                if (!function) {
                    dec = variableResolver.findDeclaration(name, offs, this.findLocal, this.findPreprocessed, this.findSystem);
                } else {
                    String lookup = this.getLookupNameForFunction(textArea, offs, name);
                    dec = variableResolver.findFunctionDeclaration(lookup, this.findLocal, this.findPreprocessed);
                    if (dec == null) {
                        dec = variableResolver.findFunctionDeclarationByFunctionName(name, this.findLocal, this.findPreprocessed);
                    }
                }
            }
            if (dec != null) {
                return this.createSelectedRegionResult(textArea, t, dec);
            }
        }
        return null;
    }

    protected LinkGeneratorResult createSelectedRegionResult(RSyntaxTextArea textArea, Token t, JavaScriptDeclaration dec) {
        if (dec.getTypeDeclarationOptions() != null && !dec.getTypeDeclarationOptions().isSupportsLinks()) {
            return null;
        }
        return new SelectRegionLinkGeneratorResult(textArea, t.getOffset(), dec.getStartOffSet(), dec.getEndOffset());
    }

    public void setFindLocal(boolean find) {
        this.findLocal = find;
    }

    public void setFindPreprocessed(boolean find) {
        this.findPreprocessed = find;
    }

    public void setFindSystem(boolean find) {
        this.findSystem = find;
    }

    private String getLookupNameForFunction(RSyntaxTextArea textArea, int offs, String name) {
        StringBuilder temp = new StringBuilder();
        if (offs >= 0) {
            try {
                Token first;
                int line = textArea.getLineOfOffset(offs);
                Token t = first = this.wrapToken(textArea.getTokenListForLine(line));
                while (t != null && t.isPaintable()) {
                    if (t.containsPosition(offs)) {
                        Token tt = t;
                        while (tt != null && tt.isPaintable()) {
                            temp.append(tt.getLexeme());
                            if (tt.isSingleChar(22, ')')) break;
                            tt = this.wrapToken(tt.getNextToken());
                        }
                    }
                    t = this.wrapToken(t.getNextToken());
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        String function = temp.toString().replaceAll("\\s", "");
        boolean params = false;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < function.length(); ++i) {
            char ch = function.charAt(i);
            if (ch == '(') {
                params = true;
                count = 0;
                sb.append(ch);
                continue;
            }
            if (ch == ')') {
                sb.append(ch);
                break;
            }
            if (ch == ',') {
                count = 0;
                sb.append(ch);
                continue;
            }
            if (params && count == 0) {
                sb.append('p');
                ++count;
                continue;
            }
            if (params) continue;
            sb.append(ch);
        }
        return sb.toString();
    }

    private IsLinkableCheckResult checkForLinkableToken(RSyntaxTextArea textArea, int offs) {
        IsLinkableCheckResult result = null;
        if (offs >= 0) {
            try {
                int line = textArea.getLineOfOffset(offs);
                Token first = this.wrapToken(textArea.getTokenListForLine(line));
                Token prev = null;
                Token t = first;
                while (t != null && t.isPaintable()) {
                    if (t.containsPosition(offs)) {
                        Token token = this.wrapToken(t);
                        boolean isFunction = false;
                        if (prev == null || !prev.isSingleChar('.')) {
                            Token next = this.wrapToken(RSyntaxUtilities.getNextImportantToken(t.getNextToken(), textArea, line));
                            if (next != null && next.isSingleChar(22, '(')) {
                                isFunction = true;
                            }
                            result = new IsLinkableCheckResult(token, isFunction);
                        }
                        break;
                    }
                    if (!t.isCommentOrWhitespace()) {
                        prev = t;
                    }
                    t = this.wrapToken(t.getNextToken());
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return result;
    }

    private Token wrapToken(Token token) {
        if (token != null) {
            return new TokenImpl(token);
        }
        return token;
    }

    public JavaScriptLanguageSupport getLanguage() {
        return this.language;
    }

    private static class IsLinkableCheckResult {
        private Token token;
        private boolean function;

        private IsLinkableCheckResult(Token token, boolean function) {
            this.token = token;
            this.function = function;
        }
    }
}

