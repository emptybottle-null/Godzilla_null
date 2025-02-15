/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.html.AttributeCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;

public class HtmlCompletionProvider
extends DefaultCompletionProvider {
    private Map<String, List<AttributeCompletion>> tagToAttrs;
    private boolean isTagName;
    private String lastTagName;

    public HtmlCompletionProvider() {
        this.initCompletions();
        this.tagToAttrs = new HashMap<String, List<AttributeCompletion>>();
        for (Completion comp : this.completions) {
            MarkupTagCompletion c = (MarkupTagCompletion)comp;
            String tag = c.getName();
            ArrayList<AttributeCompletion> attrs = new ArrayList<AttributeCompletion>();
            this.tagToAttrs.put(tag.toLowerCase(), attrs);
            for (int j = 0; j < c.getAttributeCount(); ++j) {
                ParameterizedCompletion.Parameter param = c.getAttribute(j);
                attrs.add(new AttributeCompletion((CompletionProvider)this, param));
            }
        }
        this.setAutoActivationRules(false, "<");
    }

    protected String defaultGetAlreadyEnteredText(JTextComponent comp) {
        return super.getAlreadyEnteredText(comp);
    }

    private boolean findLastTagNameBefore(RSyntaxDocument doc, Token tokenList, int offs) {
        this.lastTagName = null;
        boolean foundOpenTag = false;
        for (Token t = tokenList; t != null && !t.containsPosition(offs); t = t.getNextToken()) {
            if (t.getType() == 26) {
                this.lastTagName = t.getLexeme();
                continue;
            }
            if (t.getType() != 25) continue;
            this.lastTagName = null;
            foundOpenTag = t.isSingleChar('<');
            if ((t = t.getNextToken()) == null || t.isWhitespace()) continue;
            this.lastTagName = t.getLexeme();
        }
        if (this.lastTagName == null && !foundOpenTag) {
            Element root = doc.getDefaultRootElement();
            for (int prevLine = root.getElementIndex(offs) - 1; prevLine >= 0; --prevLine) {
                for (Token t = tokenList = doc.getTokenListForLine(prevLine); t != null; t = t.getNextToken()) {
                    if (t.getType() == 26) {
                        this.lastTagName = t.getLexeme();
                        continue;
                    }
                    if (t.getType() != 25) continue;
                    this.lastTagName = null;
                    foundOpenTag = t.isSingleChar('<');
                    if ((t = t.getNextToken()) == null || t.isWhitespace()) continue;
                    this.lastTagName = t.getLexeme();
                }
                if (this.lastTagName != null || foundOpenTag) break;
            }
        }
        return this.lastTagName != null;
    }

    @Override
    public String getAlreadyEnteredText(JTextComponent comp) {
        String text;
        block16: {
            this.isTagName = true;
            this.lastTagName = null;
            text = super.getAlreadyEnteredText(comp);
            if (text != null) {
                int dot = comp.getCaretPosition();
                if (dot > 0) {
                    RSyntaxTextArea textArea = (RSyntaxTextArea)comp;
                    try {
                        int line = textArea.getLineOfOffset(dot - 1);
                        Token list = textArea.getTokenListForLine(line);
                        if (list == null) break block16;
                        Token t = RSyntaxUtilities.getTokenAtOffset(list, dot - 1);
                        if (t == null) {
                            text = null;
                        } else if (t.getType() == 25) {
                            if (!HtmlCompletionProvider.isTagOpeningToken(t)) {
                                text = null;
                            }
                        } else if (t.getType() == 21) {
                            if (!HtmlCompletionProvider.insideMarkupTag(textArea, list, line, dot)) {
                                text = null;
                            }
                        } else if (t.getType() != 27 && t.getType() != 26 && (t.getType() > -1 || t.getType() < -9)) {
                            text = null;
                        }
                        if (text != null) {
                            t = HtmlCompletionProvider.getTokenBeforeOffset(list, dot - text.length());
                            boolean bl = this.isTagName = t != null && HtmlCompletionProvider.isTagOpeningToken(t);
                            if (!this.isTagName) {
                                RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
                                this.findLastTagNameBefore(doc, list, dot);
                            }
                        }
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                } else {
                    text = null;
                }
            }
        }
        return text;
    }

    protected List<AttributeCompletion> getAttributeCompletionsForTag(String tagName) {
        return this.tagToAttrs.get(this.lastTagName);
    }

    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        ArrayList<Completion> retVal = new ArrayList<Completion>();
        String text = this.getAlreadyEnteredText(comp);
        List<Completion> completions = this.getTagCompletions();
        if (this.lastTagName != null) {
            this.lastTagName = this.lastTagName.toLowerCase();
            completions = this.getAttributeCompletionsForTag(this.lastTagName);
        }
        if (text != null && completions != null) {
            Completion c;
            int index = Collections.binarySearch(completions, text, this.comparator);
            if (index < 0) {
                index = -index - 1;
            }
            while (index < completions.size() && Util.startsWithIgnoreCase((c = completions.get(index)).getInputText(), text)) {
                retVal.add(c);
                ++index;
            }
        }
        return retVal;
    }

    protected List<Completion> getTagCompletions() {
        return this.completions;
    }

    private static Token getTokenBeforeOffset(Token tokenList, int offs) {
        if (tokenList != null) {
            Token prev = tokenList;
            for (Token t = tokenList.getNextToken(); t != null; t = t.getNextToken()) {
                if (t.containsPosition(offs)) {
                    return prev;
                }
                prev = t;
            }
        }
        return null;
    }

    protected void initCompletions() {
        try {
            this.loadFromXML("data/html.xml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static boolean insideMarkupTag(RSyntaxTextArea textArea, Token list, int line, int offs) {
        int inside = -1;
        block4: for (Token t = list; t != null && !t.containsPosition(offs); t = t.getNextToken()) {
            switch (t.getType()) {
                case 26: 
                case 27: {
                    inside = 1;
                    continue block4;
                }
                case 25: {
                    inside = t.isSingleChar('>') ? 0 : 1;
                }
            }
        }
        if (inside == -1) {
            RSyntaxDocument doc;
            int prevLastToken;
            inside = line == 0 ? 0 : ((prevLastToken = (doc = (RSyntaxDocument)textArea.getDocument()).getLastTokenTypeOnLine(line - 1)) <= -1 && prevLastToken >= -9 ? 1 : 0);
        }
        return inside == 1;
    }

    @Override
    public boolean isAutoActivateOkay(JTextComponent tc) {
        boolean okay = super.isAutoActivateOkay(tc);
        if (okay) {
            RSyntaxTextArea textArea = (RSyntaxTextArea)tc;
            int dot = textArea.getCaretPosition();
            try {
                int line = textArea.getLineOfOffset(dot);
                Token list = textArea.getTokenListForLine(line);
                if (list != null) {
                    return !HtmlCompletionProvider.insideMarkupTag(textArea, list, line, dot);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return okay;
    }

    private static boolean isTagOpeningToken(Token t) {
        return t.isSingleChar('<') || t.length() == 2 && t.charAt(0) == '<' && t.charAt(1) == '/';
    }
}

