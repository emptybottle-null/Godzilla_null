/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.Segment;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.fife.rsta.ac.css.BasicCssCompletion;
import org.fife.rsta.ac.css.BorderStyleCompletionGenerator;
import org.fife.rsta.ac.css.ColorCompletionGenerator;
import org.fife.rsta.ac.css.CommonFontCompletionGenerator;
import org.fife.rsta.ac.css.CompletionGenerator;
import org.fife.rsta.ac.css.PercentageOrLengthCompletionGenerator;
import org.fife.rsta.ac.css.PropertyCompletion;
import org.fife.rsta.ac.css.TimeCompletionGenerator;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProviderBase;
import org.fife.ui.autocomplete.CompletionXMLParser;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.xml.sax.helpers.DefaultHandler;

public class PropertyValueCompletionProvider
extends CompletionProviderBase {
    private List<Completion> htmlTagCompletions;
    private List<Completion> propertyCompletions;
    private Map<String, List<Completion>> valueCompletions;
    private Map<String, List<CompletionGenerator>> valueCompletionGenerators;
    private Segment seg = new Segment();
    private AbstractCompletionProvider.CaseInsensitiveComparator comparator;
    private String currentProperty;
    private boolean isLess;
    private static final Pattern VENDOR_PREFIXES = Pattern.compile("^\\-(?:ms|moz|o|xv|webkit|khtml|apple)\\-");
    private final Completion INHERIT_COMPLETION = new BasicCssCompletion(this, "inherit", "css_propertyvalue_identifier");

    public PropertyValueCompletionProvider(boolean isLess) {
        this.setAutoActivationRules(true, "@: ");
        this.setParameterizedCompletionParams('(', ", ", ')');
        this.isLess = isLess;
        try {
            this.valueCompletions = new HashMap<String, List<Completion>>();
            this.valueCompletionGenerators = new HashMap<String, List<CompletionGenerator>>();
            this.loadPropertyCompletions();
            this.htmlTagCompletions = this.loadHtmlTagCompletions();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        this.comparator = new AbstractCompletionProvider.CaseInsensitiveComparator();
    }

    private void addAtRuleCompletions(List<Completion> completions) {
        completions.add(new BasicCssCompletion(this, "@charset", "charset_rule"));
        completions.add(new BasicCssCompletion(this, "@import", "link_rule"));
        completions.add(new BasicCssCompletion(this, "@namespace", "charset_rule"));
        completions.add(new BasicCssCompletion(this, "@media", "media_rule"));
        completions.add(new BasicCssCompletion(this, "@page", "page_rule"));
        completions.add(new BasicCssCompletion(this, "@font-face", "fontface_rule"));
        completions.add(new BasicCssCompletion(this, "@keyframes", "charset_rule"));
        completions.add(new BasicCssCompletion(this, "@supports", "charset_rule"));
        completions.add(new BasicCssCompletion(this, "@document", "charset_rule"));
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
        if ((len = segEnd - ++start) == 0) {
            return "";
        }
        String text = new String(this.seg.array, start, len);
        return PropertyValueCompletionProvider.removeVendorPrefix(text);
    }

    private static final String removeVendorPrefix(String text) {
        Matcher m;
        if (text.length() > 0 && text.charAt(0) == '-' && (m = VENDOR_PREFIXES.matcher(text)).find()) {
            text = text.substring(m.group().length());
        }
        return text;
    }

    @Override
    public List<Completion> getCompletionsAt(JTextComponent comp, Point p) {
        return null;
    }

    @Override
    public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent tc) {
        return null;
    }

    private LexerState getLexerState(RSyntaxTextArea textArea, int line) {
        int dot = textArea.getCaretPosition();
        LexerState state = LexerState.SELECTOR;
        boolean somethingFound = false;
        this.currentProperty = null;
        while (line >= 0 && !somethingFound) {
            for (Token t = textArea.getTokenListForLine(line--); t != null && t.isPaintable() && !t.containsPosition(dot); t = t.getNextToken()) {
                if (t.getType() == 6) {
                    state = LexerState.PROPERTY;
                    this.currentProperty = PropertyValueCompletionProvider.removeVendorPrefix(t.getLexeme());
                    somethingFound = true;
                    continue;
                }
                if (!this.isLess && t.getType() == 17) {
                    state = LexerState.SELECTOR;
                    this.currentProperty = null;
                    somethingFound = true;
                    continue;
                }
                if (t.getType() == 24 || t.getType() == 8 || t.getType() == 10) {
                    state = LexerState.VALUE;
                    somethingFound = true;
                    continue;
                }
                if (t.isLeftCurly()) {
                    state = LexerState.PROPERTY;
                    somethingFound = true;
                    continue;
                }
                if (t.isRightCurly()) {
                    state = LexerState.SELECTOR;
                    this.currentProperty = null;
                    somethingFound = true;
                    continue;
                }
                if (t.isSingleChar(23, ':')) {
                    state = LexerState.VALUE;
                    somethingFound = true;
                    continue;
                }
                if (!t.isSingleChar(23, ';')) continue;
                state = LexerState.PROPERTY;
                this.currentProperty = null;
                somethingFound = true;
            }
        }
        return state;
    }

    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        ArrayList<Completion> retVal = new ArrayList<Completion>();
        String text = this.getAlreadyEnteredText(comp);
        if (text != null) {
            Completion c;
            int index;
            RSyntaxTextArea textArea = (RSyntaxTextArea)comp;
            LexerState lexerState = this.getLexerState(textArea, textArea.getCaretLineNumber());
            List<Object> choices = new ArrayList();
            switch (lexerState) {
                case SELECTOR: {
                    choices = this.htmlTagCompletions;
                    break;
                }
                case PROPERTY: {
                    choices = this.propertyCompletions;
                    break;
                }
                case VALUE: {
                    choices = this.valueCompletions.get(this.currentProperty);
                    List<CompletionGenerator> generators = this.valueCompletionGenerators.get(this.currentProperty);
                    if (generators != null) {
                        for (CompletionGenerator generator : generators) {
                            List<Completion> toMerge = generator.generate(this, text);
                            if (toMerge == null) continue;
                            if (choices == null) {
                                choices = toMerge;
                                continue;
                            }
                            choices = new ArrayList<Object>(choices);
                            choices.addAll(toMerge);
                        }
                    }
                    if (choices == null) {
                        choices = new ArrayList();
                    }
                    Collections.sort(choices);
                }
            }
            if (this.isLess && this.addLessCompletions(choices, lexerState, comp, text)) {
                Collections.sort(choices);
            }
            if ((index = Collections.binarySearch(choices, text, this.comparator)) < 0) {
                index = -index - 1;
            } else {
                for (int pos = index - 1; pos > 0 && this.comparator.compare(choices.get(pos), text) == 0; --pos) {
                    retVal.add((Completion)choices.get(pos));
                }
            }
            while (index < choices.size() && Util.startsWithIgnoreCase((c = (Completion)choices.get(index)).getInputText(), text)) {
                retVal.add(c);
                ++index;
            }
        }
        return retVal;
    }

    protected boolean addLessCompletions(List<Completion> completions, LexerState state, JTextComponent comp, String alreadyEntered) {
        return false;
    }

    @Override
    public boolean isAutoActivateOkay(JTextComponent tc) {
        boolean ok = super.isAutoActivateOkay(tc);
        if (ok) {
            RSyntaxDocument doc = (RSyntaxDocument)tc.getDocument();
            int dot = tc.getCaretPosition();
            try {
                if (dot > 1 && doc.charAt(dot) == ' ') {
                    ok = doc.charAt(dot - 1) == ':';
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return ok;
    }

    public boolean isValidChar(char ch) {
        switch (ch) {
            case '#': 
            case '-': 
            case '.': 
            case '@': 
            case '_': {
                return true;
            }
        }
        return Character.isLetterOrDigit(ch);
    }

    private List<Completion> loadHtmlTagCompletions() throws IOException {
        List<Completion> completions = this.loadFromXML("data/html.xml");
        this.addAtRuleCompletions(completions);
        Collections.sort(completions);
        return completions;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void loadPropertyCompletions() throws IOException {
        this.propertyCompletions = new ArrayList<Completion>();
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream("data/css_properties.txt");
        try (BufferedReader r = in != null ? new BufferedReader(new InputStreamReader(in)) : new BufferedReader(new FileReader("data/css_properties.txt"));){
            String line;
            while ((line = r.readLine()) != null) {
                if (line.length() <= 0 || line.charAt(0) == '#') continue;
                this.parsePropertyValueCompletionLine(line);
            }
        }
        Collections.sort(this.propertyCompletions);
    }

    private List<Completion> loadFromXML(InputStream in, ClassLoader cl) throws IOException {
        List<Completion> completions;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        CompletionXMLParser handler = new CompletionXMLParser(this, cl);
        try (BufferedInputStream bin = new BufferedInputStream(in);){
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse((InputStream)bin, (DefaultHandler)handler);
            completions = handler.getCompletions();
        }
        return completions;
    }

    protected List<Completion> loadFromXML(String resource) throws IOException {
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
            List<Completion> list = this.loadFromXML(bin, null);
            return list;
        }
    }

    private static final void add(Map<String, List<CompletionGenerator>> generatorMap, String prop, CompletionGenerator generator) {
        List<CompletionGenerator> generators = generatorMap.get(prop);
        if (generators == null) {
            generators = new ArrayList<CompletionGenerator>();
            generatorMap.put(prop, generators);
        }
        generators.add(generator);
    }

    private void parsePropertyValueCompletionLine(String line) {
        String[] tokens = line.split("\\s+");
        String prop = tokens[0];
        String icon = tokens.length > 1 ? tokens[1] : null;
        this.propertyCompletions.add(new PropertyCompletion(this, prop, icon));
        if (tokens.length > 2) {
            ArrayList<Completion> completions = new ArrayList<Completion>();
            completions.add(this.INHERIT_COMPLETION);
            if (tokens[2].equals("[") && tokens[tokens.length - 1].equals("]")) {
                for (int i = 3; i < tokens.length - 1; ++i) {
                    String token = tokens[i];
                    BasicCssCompletion completion = null;
                    if ("*length*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new PercentageOrLengthCompletionGenerator(false));
                    } else if ("*percentage-or-length*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new PercentageOrLengthCompletionGenerator(true));
                    } else if ("*color*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new ColorCompletionGenerator(this));
                    } else if ("*border-style*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new BorderStyleCompletionGenerator());
                    } else if ("*time*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new TimeCompletionGenerator());
                    } else if ("*common-fonts*".equals(token)) {
                        PropertyValueCompletionProvider.add(this.valueCompletionGenerators, prop, new CommonFontCompletionGenerator());
                    } else {
                        completion = new BasicCssCompletion(this, tokens[i], "css_propertyvalue_identifier");
                    }
                    if (completion == null) continue;
                    completions.add(completion);
                }
            }
            this.valueCompletions.put(prop, completions);
        }
    }

    protected static enum LexerState {
        SELECTOR,
        PROPERTY,
        VALUE;

    }
}

