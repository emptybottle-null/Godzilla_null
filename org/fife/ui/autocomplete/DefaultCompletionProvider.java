/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.Segment;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionXMLParser;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultCompletionProvider
extends AbstractCompletionProvider {
    protected Segment seg;
    private String lastCompletionsAtText;
    private List<Completion> lastParameterizedCompletionsAt;

    public DefaultCompletionProvider() {
        this.init();
    }

    public DefaultCompletionProvider(String[] words) {
        this.init();
        this.addWordCompletions(words);
    }

    @Override
    public String getAlreadyEnteredText(JTextComponent comp) {
        Document doc = comp.getDocument();
        int dot = comp.getCaretPosition();
        Element root = doc.getDefaultRootElement();
        int index = root.getElementIndex(dot);
        Element elem = root.getElement(index);
        int start = elem.getStartOffset();
        int len = dot - start;
        try {
            doc.getText(start, len, this.seg);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            return "";
        }
        int segEnd = this.seg.offset + len;
        for (start = segEnd - 1; start >= this.seg.offset && this.isValidChar(this.seg.array[start]); --start) {
        }
        return (len = segEnd - ++start) == 0 ? "" : new String(this.seg.array, start, len);
    }

    @Override
    public List<Completion> getCompletionsAt(JTextComponent tc, Point p) {
        int offset = tc.viewToModel(p);
        if (offset < 0 || offset >= tc.getDocument().getLength()) {
            this.lastCompletionsAtText = null;
            this.lastParameterizedCompletionsAt = null;
            return null;
        }
        Segment s = new Segment();
        Document doc = tc.getDocument();
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(offset);
        Element elem = root.getElement(line);
        int start = elem.getStartOffset();
        int end = elem.getEndOffset() - 1;
        try {
            int endOffs;
            int startOffs;
            doc.getText(start, end - start, s);
            for (startOffs = s.offset + (offset - start) - 1; startOffs >= s.offset && this.isValidChar(s.array[startOffs]); --startOffs) {
            }
            for (endOffs = s.offset + (offset - start); endOffs < s.offset + s.count && this.isValidChar(s.array[endOffs]); ++endOffs) {
            }
            int len = endOffs - startOffs - 1;
            if (len <= 0) {
                this.lastParameterizedCompletionsAt = null;
                return null;
            }
            String text = new String(s.array, startOffs + 1, len);
            if (text.equals(this.lastCompletionsAtText)) {
                return this.lastParameterizedCompletionsAt;
            }
            List<Completion> list = this.getCompletionByInputText(text);
            this.lastCompletionsAtText = text;
            this.lastParameterizedCompletionsAt = list;
            return this.lastParameterizedCompletionsAt;
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            this.lastCompletionsAtText = null;
            this.lastParameterizedCompletionsAt = null;
            return null;
        }
    }

    @Override
    public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent tc) {
        int line;
        ArrayList<ParameterizedCompletion> list = null;
        char paramListStart = this.getParameterListStart();
        if (paramListStart == '\u0000') {
            return list;
        }
        int dot = tc.getCaretPosition();
        Segment s = new Segment();
        Document doc = tc.getDocument();
        Element root = doc.getDefaultRootElement();
        Element elem = root.getElement(line = root.getElementIndex(dot));
        int offs = elem.getStartOffset();
        int len = dot - offs - 1;
        if (len <= 0) {
            return list;
        }
        try {
            doc.getText(offs, len, s);
            for (offs = s.offset + len - 1; offs >= s.offset && Character.isWhitespace(s.array[offs]); --offs) {
            }
            int end = offs;
            while (offs >= s.offset && this.isValidChar(s.array[offs])) {
                --offs;
            }
            String text = new String(s.array, offs + 1, end - offs);
            List<Completion> l = this.getCompletionByInputText(text);
            if (l != null && !l.isEmpty()) {
                for (Completion o : l) {
                    if (!(o instanceof ParameterizedCompletion)) continue;
                    if (list == null) {
                        list = new ArrayList<ParameterizedCompletion>(1);
                    }
                    list.add((ParameterizedCompletion)o);
                }
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return list;
    }

    protected void init() {
        this.seg = new Segment();
    }

    protected boolean isValidChar(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_';
    }

    public void loadFromXML(File file) throws IOException {
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));){
            this.loadFromXML(bin);
        }
    }

    public void loadFromXML(InputStream in) throws IOException {
        this.loadFromXML(in, null);
    }

    public void loadFromXML(InputStream in, ClassLoader cl) throws IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        CompletionXMLParser handler = new CompletionXMLParser(this, cl);
        try (BufferedInputStream bin = new BufferedInputStream(in);){
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse((InputStream)bin, (DefaultHandler)handler);
            List<Completion> completions = handler.getCompletions();
            this.addCompletions(completions);
            char startChar = handler.getParamStartChar();
            if (startChar != '\u0000') {
                char endChar = handler.getParamEndChar();
                String sep = handler.getParamSeparator();
                if (endChar != '\u0000' && sep != null && sep.length() > 0) {
                    this.setParameterizedCompletionParams(startChar, sep, endChar);
                }
            }
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException(e.toString());
        }
    }

    public void loadFromXML(String resource) throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream(resource);
        if (in == null) {
            File file = new File(resource);
            if (file.isFile()) {
                in = new FileInputStream(file);
            } else {
                throw new IOException("No such resource: " + resource);
            }
        }
        try (BufferedInputStream bin = new BufferedInputStream(in);){
            this.loadFromXML(bin);
        }
    }
}

