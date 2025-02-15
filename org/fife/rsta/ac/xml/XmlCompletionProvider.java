/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.html.AttributeCompletion;
import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

class XmlCompletionProvider
extends DefaultCompletionProvider {
    private static final char[] TAG_SELF_CLOSE = new char[]{'/', '>'};

    public XmlCompletionProvider() {
        this.setAutoActivationRules(false, "<");
    }

    private void addCompletionImpl(String word, int desiredType) {
        AbstractCompletion c;
        if (desiredType == 26) {
            c = new MarkupTagCompletion((CompletionProvider)this, word);
        } else {
            ParameterizedCompletion.Parameter param = new ParameterizedCompletion.Parameter(null, word);
            c = new AttributeCompletion((CompletionProvider)this, param);
        }
        this.completions.add(c);
    }

    private Set<String> collectCompletionWordsAttribute(RSyntaxDocument doc, Token inTag, int currentWordStart) {
        HashSet<String> possibleAttrs = new HashSet<String>();
        HashSet<String> attrs = new HashSet<String>();
        HashSet<String> attrsAlreadySpecified = new HashSet<String>();
        String desiredTagName = inTag.getLexeme();
        boolean collectAttrs = false;
        boolean inCurTag = false;
        for (Token t2 : doc) {
            int type = t2.getType();
            if (type == 26) {
                collectAttrs = desiredTagName.equals(t2.getLexeme());
                boolean bl = inCurTag = t2.getOffset() == inTag.getOffset();
                if (attrs.isEmpty()) continue;
                possibleAttrs.addAll(attrs);
                attrs.clear();
                continue;
            }
            if (type != 27 || !collectAttrs || t2.getOffset() == currentWordStart) continue;
            String word = t2.getLexeme();
            if (inCurTag) {
                if (word.indexOf(60) > -1) {
                    collectAttrs = false;
                    attrs.clear();
                    continue;
                }
                attrsAlreadySpecified.add(word);
                continue;
            }
            if (word.indexOf(60) > -1) {
                collectAttrs = false;
                attrs.clear();
                attrsAlreadySpecified.clear();
                continue;
            }
            attrs.add(word);
        }
        if (!attrs.isEmpty()) {
            possibleAttrs.addAll(attrs);
        }
        possibleAttrs.removeAll(attrsAlreadySpecified);
        return possibleAttrs;
    }

    private Set<String> collectCompletionWordsTag(RSyntaxDocument doc, int currentWordStart) {
        HashSet<String> words = new HashSet<String>();
        for (Token t2 : doc) {
            if (t2.getType() != 26 || t2.getOffset() == currentWordStart) continue;
            words.add(t2.getLexeme());
        }
        return words;
    }

    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        Set<String> words;
        this.completions.clear();
        String text = this.getAlreadyEnteredText(comp);
        if (text == null) {
            return this.completions;
        }
        RSyntaxTextArea textArea = (RSyntaxTextArea)comp;
        int dot = textArea.getCaretPosition();
        RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
        Token t = RSyntaxUtilities.getPreviousImportantTokenFromOffs(doc, dot);
        if (t == null) {
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            return this.completions;
        }
        int desiredType = XmlCompletionProvider.getDesiredTokenType(t, dot);
        if (desiredType == 0) {
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            return this.completions;
        }
        int currentWordStart = dot - text.length();
        if (desiredType == 26) {
            words = this.collectCompletionWordsTag(doc, currentWordStart);
        } else {
            Token tagNameToken = XmlCompletionProvider.getTagNameTokenForCaretOffset(textArea);
            if (tagNameToken != null) {
                tagNameToken = new TokenImpl(tagNameToken);
                words = this.collectCompletionWordsAttribute(doc, tagNameToken, currentWordStart);
            } else {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return this.completions;
            }
        }
        for (String word : words) {
            this.addCompletionImpl(word, desiredType);
        }
        Collections.sort(this.completions);
        return super.getCompletionsImpl(comp);
    }

    private static final int getDesiredTokenType(Token t, int dot) {
        switch (t.getType()) {
            case 26: {
                if (t.containsPosition(dot - 1)) {
                    return t.getType();
                }
                return 27;
            }
            case 27: {
                return t.getType();
            }
            case 28: {
                if (t.containsPosition(dot)) {
                    return 0;
                }
                return 27;
            }
            case 25: {
                if (t.isSingleChar('<')) {
                    return 26;
                }
                return 0;
            }
        }
        return 0;
    }

    public static final Token getTagNameTokenForCaretOffset(RSyntaxTextArea textArea) {
        int dot = textArea.getCaretPosition();
        int line = textArea.getCaretLineNumber();
        Token toMark = null;
        block0: do {
            for (Token t = textArea.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                if (t.getType() == 26) {
                    toMark = t;
                }
                if (t.getEndOffset() == dot || t.containsPosition(dot)) continue block0;
                if (t.getType() != 25 || !t.isSingleChar('>') && !t.is(TAG_SELF_CLOSE)) continue;
                toMark = null;
            }
        } while (toMark == null && --line >= 0);
        return toMark;
    }
}

