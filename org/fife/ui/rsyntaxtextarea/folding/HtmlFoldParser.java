/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;

public class HtmlFoldParser
implements FoldParser {
    public static final int LANGUAGE_HTML = -1;
    public static final int LANGUAGE_PHP = 0;
    public static final int LANGUAGE_JSP = 1;
    private final int language;
    private static final Set<String> FOLDABLE_TAGS;
    private static final char[] MARKUP_CLOSING_TAG_START;
    private static final char[] MLC_START;
    private static final char[] MLC_END;
    private static final char[] PHP_START;
    private static final char[] PHP_END;
    private static final char[] JSP_START;
    private static final char[] JSP_END;
    private static final char[][] LANG_START;
    private static final char[][] LANG_END;
    private static final char[] JSP_COMMENT_START;
    private static final char[] JSP_COMMENT_END;

    public HtmlFoldParser(int language) {
        if (language < -1 && language > 1) {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language = language;
    }

    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Stack<String> tagNameStack = new Stack<String>();
        boolean inSublanguage = false;
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        boolean inMLC = false;
        boolean inJSMLC = false;
        TagCloseInfo tci = new TagCloseInfo();
        try {
            for (int line = 0; line < lineCount; ++line) {
                Token t = textArea.getTokenListForLine(line);
                while (t != null && t.isPaintable()) {
                    Fold parentFold;
                    if (this.language >= 0 && t.getType() == 22) {
                        if (t.startsWith(LANG_START[this.language])) {
                            if (currentFold == null) {
                                currentFold = new Fold(0, textArea, t.getOffset());
                                folds.add(currentFold);
                            } else {
                                currentFold = currentFold.createChild(0, t.getOffset());
                            }
                            inSublanguage = true;
                        } else if (t.startsWith(LANG_END[this.language]) && currentFold != null) {
                            int phpEnd = t.getEndOffset() - 1;
                            currentFold.setEndOffset(phpEnd);
                            parentFold = currentFold.getParent();
                            if (currentFold.isOnSingleLine()) {
                                HtmlFoldParser.removeFold(currentFold, folds);
                            }
                            currentFold = parentFold;
                            inSublanguage = false;
                            t = t.getNextToken();
                            continue;
                        }
                    }
                    if (!inSublanguage) {
                        Token tagNameToken;
                        if (t.getType() == 2) {
                            int mlcEnd;
                            if (inMLC) {
                                if (t.endsWith(MLC_END)) {
                                    mlcEnd = t.getEndOffset() - 1;
                                    currentFold.setEndOffset(mlcEnd);
                                    parentFold = currentFold.getParent();
                                    if (currentFold.isOnSingleLine()) {
                                        HtmlFoldParser.removeFold(currentFold, folds);
                                    }
                                    currentFold = parentFold;
                                    inMLC = false;
                                }
                            } else if (inJSMLC) {
                                if (t.endsWith(JSP_COMMENT_END)) {
                                    mlcEnd = t.getEndOffset() - 1;
                                    currentFold.setEndOffset(mlcEnd);
                                    parentFold = currentFold.getParent();
                                    if (currentFold.isOnSingleLine()) {
                                        HtmlFoldParser.removeFold(currentFold, folds);
                                    }
                                    currentFold = parentFold;
                                    inJSMLC = false;
                                }
                            } else if (t.startsWith(MLC_START) && !t.endsWith(MLC_END)) {
                                if (currentFold == null) {
                                    currentFold = new Fold(1, textArea, t.getOffset());
                                    folds.add(currentFold);
                                } else {
                                    currentFold = currentFold.createChild(1, t.getOffset());
                                }
                                inMLC = true;
                            } else if (this.language == 1 && t.startsWith(JSP_COMMENT_START) && !t.endsWith(JSP_COMMENT_END)) {
                                if (currentFold == null) {
                                    currentFold = new Fold(1, textArea, t.getOffset());
                                    folds.add(currentFold);
                                } else {
                                    currentFold = currentFold.createChild(1, t.getOffset());
                                }
                                inJSMLC = true;
                            }
                        } else if (t.isSingleChar(25, '<')) {
                            Token tagStartToken = t;
                            Token tagNameToken2 = t.getNextToken();
                            if (HtmlFoldParser.isFoldableTag(tagNameToken2)) {
                                int newLine = this.getTagCloseInfo(tagNameToken2, textArea, line, tci);
                                if (tci.line == -1) {
                                    return folds;
                                }
                                Token tagCloseToken = tci.closeToken;
                                if (tagCloseToken.isSingleChar(25, '>')) {
                                    if (currentFold == null) {
                                        currentFold = new Fold(0, textArea, tagStartToken.getOffset());
                                        folds.add(currentFold);
                                    } else {
                                        currentFold = currentFold.createChild(0, tagStartToken.getOffset());
                                    }
                                    tagNameStack.push(tagNameToken2.getLexeme());
                                }
                                t = tagCloseToken;
                                line = newLine;
                            }
                        } else if (t.is(25, MARKUP_CLOSING_TAG_START) && currentFold != null && HtmlFoldParser.isFoldableTag(tagNameToken = t.getNextToken()) && HtmlFoldParser.isEndOfLastFold(tagNameStack, tagNameToken)) {
                            tagNameStack.pop();
                            currentFold.setEndOffset(t.getOffset());
                            parentFold = currentFold.getParent();
                            if (currentFold.isOnSingleLine()) {
                                HtmlFoldParser.removeFold(currentFold, folds);
                            }
                            currentFold = parentFold;
                            t = tagNameToken;
                        }
                    }
                    t = t.getNextToken();
                }
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }

    private int getTagCloseInfo(Token tagNameToken, RSyntaxTextArea textArea, int line, TagCloseInfo info) {
        info.reset();
        Token t = tagNameToken.getNextToken();
        while (true) {
            if (t != null && t.getType() != 25) {
                t = t.getNextToken();
                continue;
            }
            if (t != null) {
                info.closeToken = t;
                info.line = line;
                break;
            }
            if (++line >= textArea.getLineCount() || (t = textArea.getTokenListForLine(line)) == null) break;
        }
        return line;
    }

    private static boolean isEndOfLastFold(Stack<String> tagNameStack, Token tagNameToken) {
        if (tagNameToken != null && tagNameToken.getLexeme() != null && !tagNameStack.isEmpty()) {
            return tagNameToken.getLexeme().equalsIgnoreCase(tagNameStack.peek());
        }
        return false;
    }

    private static boolean isFoldableTag(Token tagNameToken) {
        return tagNameToken != null && tagNameToken.getLexeme() != null && FOLDABLE_TAGS.contains(tagNameToken.getLexeme().toLowerCase());
    }

    private static void removeFold(Fold fold, List<Fold> folds) {
        if (!fold.removeFromParent()) {
            folds.remove(folds.size() - 1);
        }
    }

    static {
        MARKUP_CLOSING_TAG_START = "</".toCharArray();
        MLC_START = "<!--".toCharArray();
        MLC_END = "-->".toCharArray();
        PHP_START = "<?".toCharArray();
        PHP_END = "?>".toCharArray();
        JSP_START = "<%".toCharArray();
        JSP_END = "%>".toCharArray();
        LANG_START = new char[][]{PHP_START, JSP_START};
        LANG_END = new char[][]{PHP_END, JSP_END};
        JSP_COMMENT_START = "<%--".toCharArray();
        JSP_COMMENT_END = "--%>".toCharArray();
        FOLDABLE_TAGS = new HashSet<String>();
        FOLDABLE_TAGS.add("body");
        FOLDABLE_TAGS.add("canvas");
        FOLDABLE_TAGS.add("div");
        FOLDABLE_TAGS.add("form");
        FOLDABLE_TAGS.add("head");
        FOLDABLE_TAGS.add("html");
        FOLDABLE_TAGS.add("ol");
        FOLDABLE_TAGS.add("pre");
        FOLDABLE_TAGS.add("script");
        FOLDABLE_TAGS.add("span");
        FOLDABLE_TAGS.add("style");
        FOLDABLE_TAGS.add("table");
        FOLDABLE_TAGS.add("tfoot");
        FOLDABLE_TAGS.add("thead");
        FOLDABLE_TAGS.add("tr");
        FOLDABLE_TAGS.add("td");
        FOLDABLE_TAGS.add("ul");
    }

    private static class TagCloseInfo {
        private Token closeToken;
        private int line;

        private TagCloseInfo() {
        }

        public void reset() {
            this.closeToken = null;
            this.line = -1;
        }

        public String toString() {
            return "[TagCloseInfo: closeToken=" + this.closeToken + ", line=" + this.line + "]";
        }
    }
}

