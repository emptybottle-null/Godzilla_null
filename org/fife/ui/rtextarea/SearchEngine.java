/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rtextarea.RDocument;
import org.fife.ui.rtextarea.RDocumentCharSequence;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RegExReplaceInfo;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchResult;

public final class SearchEngine {
    private SearchEngine() {
    }

    public static SearchResult find(JTextArea textArea, SearchContext context) {
        SearchResult result;
        if (textArea instanceof RTextArea || context.getMarkAll()) {
            ((RTextArea)textArea).clearMarkAllHighlights();
        }
        boolean doMarkAll = textArea instanceof RTextArea && context.getMarkAll();
        String text = context.getSearchFor();
        if (text == null || text.length() == 0) {
            if (doMarkAll) {
                List<DocumentRange> emptyRangeList = Collections.emptyList();
                ((RTextArea)textArea).markAll(emptyRangeList);
            }
            return new SearchResult();
        }
        Caret c = textArea.getCaret();
        boolean forward = context.getSearchForward();
        int start = forward ? Math.max(c.getDot(), c.getMark()) : Math.min(c.getDot(), c.getMark());
        String findIn = SearchEngine.getFindInText(textArea, start, forward);
        if (!(context.getSearchWrap() || findIn != null && findIn.length() != 0)) {
            return new SearchResult();
        }
        int markAllCount = 0;
        if (doMarkAll) {
            markAllCount = SearchEngine.markAllImpl((RTextArea)textArea, context).getMarkedCount();
        }
        if ((result = SearchEngine.findImpl(findIn == null ? "" : findIn, context)).wasFound() && !result.getMatchRange().isZeroLength()) {
            textArea.getCaret().setSelectionVisible(true);
            if (forward && start > -1) {
                result.getMatchRange().translate(start);
            }
            RSyntaxUtilities.selectAndPossiblyCenter(textArea, result.getMatchRange(), true);
        } else if (context.getSearchWrap() && !result.wasFound()) {
            start = forward ? 0 : textArea.getDocument().getLength() - 1;
            findIn = SearchEngine.getFindInText(textArea, start, forward);
            if (findIn == null || findIn.length() == 0) {
                SearchResult emptyResult = new SearchResult();
                emptyResult.setWrapped(true);
                return emptyResult;
            }
            if (doMarkAll) {
                markAllCount = SearchEngine.markAllImpl((RTextArea)textArea, context).getMarkedCount();
            }
            result = SearchEngine.findImpl(findIn, context);
            result.setWrapped(true);
            if (result.wasFound() && !result.getMatchRange().isZeroLength()) {
                textArea.getCaret().setSelectionVisible(true);
                if (forward) {
                    result.getMatchRange().translate(start);
                }
                RSyntaxUtilities.selectAndPossiblyCenter(textArea, result.getMatchRange(), true);
            }
        }
        result.setMarkedCount(markAllCount);
        return result;
    }

    private static SearchResult findImpl(String findIn, SearchContext context) {
        String text = context.getSearchFor();
        boolean forward = context.getSearchForward();
        DocumentRange range = null;
        if (!context.isRegularExpression()) {
            int pos = SearchEngine.getNextMatchPos(text, findIn, forward, context.getMatchCase(), context.getWholeWord());
            findIn = null;
            if (pos != -1) {
                range = new DocumentRange(pos, pos + text.length());
            }
        } else {
            Point regExPos = null;
            int start = 0;
            do {
                if ((regExPos = SearchEngine.getNextMatchPosRegEx(text, findIn.substring(start), forward, context.getMatchCase(), context.getWholeWord())) == null) continue;
                if (regExPos.x != regExPos.y) {
                    regExPos.translate(start, start);
                    range = new DocumentRange(regExPos.x, regExPos.y);
                    continue;
                }
                start += regExPos.x + 1;
            } while (start < findIn.length() && regExPos != null && range == null);
        }
        if (range != null) {
            return new SearchResult(range, 1, 0);
        }
        return new SearchResult();
    }

    private static CharSequence getFindInCharSequence(RTextArea textArea, int start, boolean forward) {
        RDocument doc = (RDocument)textArea.getDocument();
        int csStart = 0;
        int csEnd = 0;
        if (forward) {
            csStart = start;
            csEnd = doc.getLength();
        } else {
            csStart = 0;
            csEnd = start;
        }
        return new RDocumentCharSequence(doc, csStart, csEnd);
    }

    private static String getFindInText(JTextArea textArea, int start, boolean forward) {
        String findIn = null;
        try {
            findIn = forward ? textArea.getText(start, textArea.getDocument().getLength() - start) : textArea.getText(0, start);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return findIn;
    }

    private static List getMatches(Matcher m, String replaceStr) {
        ArrayList<Object> matches = new ArrayList<Object>();
        while (m.find()) {
            Point loc = new Point(m.start(), m.end());
            if (replaceStr == null) {
                matches.add(loc);
                continue;
            }
            matches.add(new RegExReplaceInfo(m.group(0), loc.x, loc.y, SearchEngine.getReplacementText(m, replaceStr)));
        }
        return matches;
    }

    public static int getNextMatchPos(String searchFor, String searchIn, boolean forward, boolean matchCase, boolean wholeWord) {
        if (!matchCase) {
            return SearchEngine.getNextMatchPosImpl(searchFor.toLowerCase(), searchIn.toLowerCase(), forward, matchCase, wholeWord);
        }
        return SearchEngine.getNextMatchPosImpl(searchFor, searchIn, forward, matchCase, wholeWord);
    }

    private static int getNextMatchPosImpl(String searchFor, String searchIn, boolean goForward, boolean matchCase, boolean wholeWord) {
        if (wholeWord) {
            int len = searchFor.length();
            int temp = goForward ? 0 : searchIn.length();
            int tempChange = goForward ? 1 : -1;
            while ((temp = goForward ? searchIn.indexOf(searchFor, temp) : searchIn.lastIndexOf(searchFor, temp)) != -1) {
                if (SearchEngine.isWholeWord(searchIn, temp, len)) {
                    return temp;
                }
                temp += tempChange;
            }
            return temp;
        }
        return goForward ? searchIn.indexOf(searchFor) : searchIn.lastIndexOf(searchFor);
    }

    private static Point getNextMatchPosRegEx(String regEx, CharSequence searchIn, boolean goForward, boolean matchCase, boolean wholeWord) {
        return (Point)SearchEngine.getNextMatchPosRegExImpl(regEx, searchIn, goForward, matchCase, wholeWord, null);
    }

    private static Object getNextMatchPosRegExImpl(String regEx, CharSequence searchIn, boolean goForward, boolean matchCase, boolean wholeWord, String replaceStr) {
        if (wholeWord) {
            regEx = "\\b" + regEx + "\\b";
        }
        int flags = 8;
        flags = RSyntaxUtilities.getPatternFlags(matchCase, flags);
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(regEx, flags);
        } catch (PatternSyntaxException pse) {
            return null;
        }
        Matcher m = pattern.matcher(searchIn);
        if (goForward) {
            if (m.find()) {
                if (replaceStr == null) {
                    return new Point(m.start(), m.end());
                }
                return new RegExReplaceInfo(m.group(0), m.start(), m.end(), SearchEngine.getReplacementText(m, replaceStr));
            }
        } else {
            List matches = SearchEngine.getMatches(m, replaceStr);
            if (!matches.isEmpty()) {
                return matches.get(matches.size() - 1);
            }
        }
        return null;
    }

    private static RegExReplaceInfo getRegExReplaceInfo(CharSequence searchIn, SearchContext context) {
        String replacement = context.getReplaceWith();
        if (replacement == null) {
            replacement = "";
        }
        String regex = context.getSearchFor();
        boolean goForward = context.getSearchForward();
        boolean matchCase = context.getMatchCase();
        boolean wholeWord = context.getWholeWord();
        return (RegExReplaceInfo)SearchEngine.getNextMatchPosRegExImpl(regex, searchIn, goForward, matchCase, wholeWord, replacement);
    }

    public static String getReplacementText(Matcher m, CharSequence template) {
        int cursor = 0;
        StringBuilder result = new StringBuilder();
        while (cursor < template.length()) {
            char nextChar = template.charAt(cursor);
            if (nextChar == '\\') {
                nextChar = template.charAt(++cursor);
                switch (nextChar) {
                    case 'n': {
                        nextChar = '\n';
                        break;
                    }
                    case 't': {
                        nextChar = '\t';
                    }
                }
                result.append(nextChar);
                ++cursor;
                continue;
            }
            if (nextChar == '$') {
                int nextDigit;
                int refNum;
                if ((refNum = template.charAt(++cursor) - 48) < 0 || refNum > 9) {
                    throw new IndexOutOfBoundsException("No group " + template.charAt(cursor));
                }
                ++cursor;
                boolean done = false;
                while (!done && cursor < template.length() && (nextDigit = template.charAt(cursor) - 48) >= 0 && nextDigit <= 9) {
                    int newRefNum = refNum * 10 + nextDigit;
                    if (m.groupCount() < newRefNum) {
                        done = true;
                        continue;
                    }
                    refNum = newRefNum;
                    ++cursor;
                }
                if (m.group(refNum) == null) continue;
                result.append(m.group(refNum));
                continue;
            }
            result.append(nextChar);
            ++cursor;
        }
        return result.toString();
    }

    private static boolean isWholeWord(CharSequence searchIn, int offset, int len) {
        boolean wsAfter;
        boolean wsBefore;
        try {
            wsBefore = !Character.isLetterOrDigit(searchIn.charAt(offset - 1));
        } catch (IndexOutOfBoundsException e) {
            wsBefore = true;
        }
        try {
            wsAfter = !Character.isLetterOrDigit(searchIn.charAt(offset + len));
        } catch (IndexOutOfBoundsException e) {
            wsAfter = true;
        }
        return wsBefore && wsAfter;
    }

    private static int makeMarkAndDotEqual(JTextArea textArea, boolean forward) {
        Caret c = textArea.getCaret();
        int val = forward ? Math.min(c.getDot(), c.getMark()) : Math.max(c.getDot(), c.getMark());
        c.setDot(val);
        return val;
    }

    public static SearchResult markAll(RTextArea textArea, SearchContext context) {
        textArea.clearMarkAllHighlights();
        return SearchEngine.markAllImpl(textArea, context);
    }

    private static SearchResult markAllImpl(RTextArea textArea, SearchContext context) {
        String toMark = context.getSearchFor();
        int markAllCount = 0;
        if (context.getMarkAll() && toMark != null && toMark.length() > 0) {
            ArrayList<DocumentRange> highlights = new ArrayList<DocumentRange>();
            context = context.clone();
            context.setSearchForward(true);
            context.setMarkAll(false);
            String findIn = textArea.getText();
            int start = 0;
            if (!context.getMatchCase()) {
                context.setMatchCase(true);
                context.setSearchFor(toMark.toLowerCase());
                findIn = findIn.toLowerCase();
            }
            SearchResult res = SearchEngine.findImpl(findIn, context);
            while (res.wasFound()) {
                DocumentRange match = res.getMatchRange().translate(start);
                if (match.isZeroLength()) {
                    start = match.getEndOffset() + 1;
                    if (start > findIn.length()) {
                        break;
                    }
                } else {
                    highlights.add(match);
                    start = match.getEndOffset();
                }
                res = SearchEngine.findImpl(findIn.substring(start), context);
            }
            textArea.markAll(highlights);
            markAllCount = highlights.size();
        } else {
            List<DocumentRange> empty = Collections.emptyList();
            textArea.markAll(empty);
        }
        return new SearchResult(null, 0, markAllCount);
    }

    private static SearchResult regexReplace(RTextArea textArea, SearchContext context) {
        Caret c = textArea.getCaret();
        boolean forward = context.getSearchForward();
        int start = SearchEngine.makeMarkAndDotEqual(textArea, forward);
        CharSequence findIn = SearchEngine.getFindInCharSequence(textArea, start, forward);
        if (findIn == null) {
            return new SearchResult();
        }
        int markAllCount = 0;
        if (context.getMarkAll()) {
            markAllCount = SearchEngine.markAllImpl(textArea, context).getMarkedCount();
        }
        RegExReplaceInfo info = SearchEngine.getRegExReplaceInfo(findIn, context);
        DocumentRange range = null;
        if (info != null) {
            c.setSelectionVisible(true);
            int matchStart = info.getStartIndex();
            int matchEnd = info.getEndIndex();
            if (forward) {
                matchStart += start;
                matchEnd += start;
            }
            textArea.setSelectionStart(matchStart);
            textArea.setSelectionEnd(matchEnd);
            String replacement = info.getReplacement();
            textArea.replaceSelection(replacement);
            int dot = matchStart + replacement.length();
            findIn = SearchEngine.getFindInCharSequence(textArea, dot, forward);
            info = SearchEngine.getRegExReplaceInfo(findIn, context);
            if (info != null) {
                matchStart = info.getStartIndex();
                matchEnd = info.getEndIndex();
                if (forward) {
                    matchStart += dot;
                    matchEnd += dot;
                }
                range = new DocumentRange(matchStart, matchEnd);
            } else {
                range = new DocumentRange(dot, dot);
            }
            RSyntaxUtilities.selectAndPossiblyCenter(textArea, range, true);
        }
        int count = range != null ? 1 : 0;
        return new SearchResult(range, count, markAllCount);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SearchResult replace(RTextArea textArea, SearchContext context) {
        String toFind;
        if (context.getMarkAll()) {
            textArea.clearMarkAllHighlights();
        }
        if ((toFind = context.getSearchFor()) == null || toFind.length() == 0) {
            return new SearchResult();
        }
        textArea.beginAtomicEdit();
        try {
            if (context.isRegularExpression()) {
                SearchResult searchResult = SearchEngine.regexReplace(textArea, context);
                return searchResult;
            }
            SearchEngine.makeMarkAndDotEqual(textArea, context.getSearchForward());
            SearchResult res = SearchEngine.find(textArea, context);
            if (res.wasFound() && !res.getMatchRange().isZeroLength()) {
                String replacement = context.getReplaceWith();
                textArea.replaceSelection(replacement);
                int dot = res.getMatchRange().getStartOffset();
                if (context.getSearchForward()) {
                    int length = replacement == null ? 0 : replacement.length();
                    dot += length;
                }
                textArea.setCaretPosition(dot);
                SearchResult next = SearchEngine.find(textArea, context);
                DocumentRange range = next.wasFound() ? next.getMatchRange() : new DocumentRange(dot, dot);
                res.setMatchRange(range);
                RSyntaxUtilities.selectAndPossiblyCenter(textArea, range, true);
            }
            SearchResult searchResult = res;
            return searchResult;
        } finally {
            textArea.endAtomicEdit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SearchResult replaceAll(RTextArea textArea, SearchContext context) {
        if (context.getMarkAll()) {
            textArea.clearMarkAllHighlights();
        }
        context.setSearchForward(true);
        String toFind = context.getSearchFor();
        if (toFind == null || toFind.length() == 0) {
            return new SearchResult();
        }
        if (context.getMarkAll()) {
            context = context.clone();
            context.setMarkAll(false);
        }
        SearchResult lastFound = null;
        int count = 0;
        textArea.beginAtomicEdit();
        try {
            int oldOffs = textArea.getCaretPosition();
            textArea.setCaretPosition(0);
            SearchResult res = SearchEngine.replace(textArea, context);
            while (res.wasFound()) {
                lastFound = res;
                ++count;
                if (res.getMatchRange().isZeroLength()) {
                    if (res.getMatchRange().getStartOffset() == textArea.getDocument().getLength()) break;
                    textArea.setCaretPosition(textArea.getCaretPosition() + 1);
                }
                res = SearchEngine.replace(textArea, context);
            }
            if (lastFound == null) {
                textArea.setCaretPosition(oldOffs);
                lastFound = new SearchResult();
            }
        } finally {
            textArea.endAtomicEdit();
        }
        lastFound.setCount(count);
        return lastFound;
    }
}

