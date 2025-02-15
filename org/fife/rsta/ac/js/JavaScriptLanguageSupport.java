/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.GoToMemberAction;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.buildpath.ClasspathLibraryInfo;
import org.fife.rsta.ac.java.buildpath.ClasspathSourceLocation;
import org.fife.rsta.ac.js.JavaScriptCellRenderer;
import org.fife.rsta.ac.js.JavaScriptCompletionProvider;
import org.fife.rsta.ac.js.JavaScriptDocUrlhandler;
import org.fife.rsta.ac.js.JavaScriptLinkGenerator;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.JsErrorParser;
import org.fife.rsta.ac.js.completion.JavaScriptShorthandCompletion;
import org.fife.rsta.ac.js.tree.JavaScriptOutlineTree;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.modes.JavaScriptTokenMaker;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.NodeVisitor;

public class JavaScriptLanguageSupport
extends AbstractLanguageSupport {
    private Map<JavaScriptParser, Info> parserToInfoMap = new HashMap<JavaScriptParser, Info>();
    private JarManager jarManager = this.createJarManager();
    private boolean xmlAvailable;
    private boolean client;
    private boolean strictMode;
    private int languageVersion;
    private JsErrorParser errorParser;
    private JavaScriptParser parser;
    private JavaScriptCompletionProvider provider = this.createJavaScriptCompletionProvider();
    private File defaultJshintrc;
    private static final String PROPERTY_LISTENER = "org.fife.rsta.ac.js.JavaScriptLanguageSupport.Listener";

    public JavaScriptLanguageSupport() {
        this.setErrorParser(JsErrorParser.RHINO);
        this.setECMAVersion(null, this.jarManager);
        this.setDefaultCompletionCellRenderer(new JavaScriptCellRenderer());
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
        this.setLanguageVersion(Integer.MIN_VALUE);
    }

    protected JarManager createJarManager() {
        JarManager jarManager = new JarManager();
        return jarManager;
    }

    public void setECMAVersion(String version, JarManager jarManager) {
        try {
            List<String> classes = this.provider.getProvider().getTypesFactory().setTypeDeclarationVersion(version, this.isXmlAvailable(), this.isClient());
            this.provider.getProvider().setXMLSupported(this.isXmlAvailable());
            if (classes != null) {
                ClasspathLibraryInfo info = new ClasspathLibraryInfo(classes, new ClasspathSourceLocation());
                jarManager.addClassFileSource(info);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected JavaScriptCompletionProvider createJavaScriptCompletionProvider() {
        return new JavaScriptCompletionProvider(this.jarManager, this);
    }

    public File getDefaultJsHintRCFile() {
        return this.defaultJshintrc;
    }

    public JsErrorParser getErrorParser() {
        return this.errorParser;
    }

    public JarManager getJarManager() {
        return this.jarManager;
    }

    public JavaScriptParser getJavaScriptParser() {
        return this.parser;
    }

    public int getJsHintIndent() {
        int DEFAULT = 4;
        return 4;
    }

    public int getLanguageVersion() {
        return this.languageVersion;
    }

    public JavaScriptParser getParser(RSyntaxTextArea textArea) {
        Object parser = textArea.getClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser");
        if (parser instanceof JavaScriptParser) {
            return (JavaScriptParser)parser;
        }
        return null;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        JavaScriptAutoCompletion ac = new JavaScriptAutoCompletion(this.provider, textArea);
        ac.setListCellRenderer(this.getDefaultCompletionCellRenderer());
        ac.setAutoCompleteEnabled(this.isAutoCompleteEnabled());
        ac.setAutoActivationEnabled(this.isAutoActivationEnabled());
        ac.setAutoActivationDelay(this.getAutoActivationDelay());
        ac.setParameterAssistanceEnabled(this.isParameterAssistanceEnabled());
        ac.setExternalURLHandler(new JavaScriptDocUrlhandler(this));
        ac.setShowDescWindow(this.getShowDescWindow());
        ac.install(textArea);
        this.installImpl(textArea, ac);
        Listener listener = new Listener(textArea);
        textArea.putClientProperty(PROPERTY_LISTENER, listener);
        this.parser = new JavaScriptParser(this, textArea);
        textArea.putClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser", this.parser);
        textArea.addParser(this.parser);
        Info info = new Info(this.provider, this.parser);
        this.parserToInfoMap.put(this.parser, info);
        this.installKeyboardShortcuts(textArea);
        JavaScriptTokenMaker.setE4xSupported(this.isXmlAvailable());
        textArea.setLinkGenerator(new JavaScriptLinkGenerator(this));
    }

    private void installKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.put(KeyStroke.getKeyStroke(79, c | shift), "GoToType");
        am.put("GoToType", new GoToMemberAction(JavaScriptOutlineTree.class));
    }

    public boolean isStrictMode() {
        return this.strictMode;
    }

    public boolean isXmlAvailable() {
        return this.xmlAvailable;
    }

    public boolean isClient() {
        return this.client;
    }

    protected void reparseDocument(int offset) {
        this.provider.reparseDocument(offset);
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean setDefaultJsHintRCFile(File file) {
        if (file == null && this.defaultJshintrc != null || file != null && this.defaultJshintrc == null || file != null && !file.equals(this.defaultJshintrc)) {
            this.defaultJshintrc = file;
            return true;
        }
        return false;
    }

    public boolean setErrorParser(JsErrorParser errorParser) {
        if (errorParser == null) {
            throw new IllegalArgumentException("errorParser cannot be null");
        }
        if (errorParser != this.errorParser) {
            this.errorParser = errorParser;
            return true;
        }
        return false;
    }

    public void setLanguageVersion(int languageVersion) {
        if (languageVersion < 0) {
            languageVersion = -1;
        }
        this.languageVersion = languageVersion;
    }

    public boolean setStrictMode(boolean strict) {
        if (strict != this.strictMode) {
            this.strictMode = strict;
            return true;
        }
        return false;
    }

    public boolean setXmlAvailable(boolean available) {
        if (available != this.xmlAvailable) {
            this.xmlAvailable = available;
            return true;
        }
        return false;
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        JavaScriptParser parser = this.getParser(textArea);
        Info info = this.parserToInfoMap.remove(parser);
        if (info != null) {
            parser.removePropertyChangeListener("AST", info);
        }
        textArea.removeParser(parser);
        textArea.putClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser", null);
        textArea.setToolTipSupplier(null);
        Object listener = textArea.getClientProperty(PROPERTY_LISTENER);
        if (listener instanceof Listener) {
            ((Listener)listener).uninstall();
            textArea.putClientProperty(PROPERTY_LISTENER, null);
        }
        this.uninstallKeyboardShortcuts(textArea);
    }

    private void uninstallKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.remove(KeyStroke.getKeyStroke(79, c | shift));
        am.remove("GoToType");
    }

    private static class DeepestScopeVisitor
    implements NodeVisitor {
        private int offs;
        private AstNode deepestScope;

        private DeepestScopeVisitor() {
        }

        private boolean containsOffs(AstNode node) {
            int start = node.getAbsolutePosition();
            return start <= this.offs && start + node.getLength() > this.offs;
        }

        public AstNode getDeepestScope() {
            return this.deepestScope;
        }

        public void reset(int offs) {
            this.offs = offs;
            this.deepestScope = null;
        }

        @Override
        public boolean visit(AstNode node) {
            switch (node.getType()) {
                case 109: {
                    if (this.containsOffs(node)) {
                        this.deepestScope = node;
                        return true;
                    }
                    return false;
                }
                default: {
                    return true;
                }
                case 129: 
            }
            return true;
        }
    }

    private class Listener
    implements CaretListener,
    ActionListener {
        private RSyntaxTextArea textArea;
        private Timer t;
        private DeepestScopeVisitor visitor;

        public Listener(RSyntaxTextArea textArea) {
            this.textArea = textArea;
            textArea.addCaretListener(this);
            this.t = new Timer(650, this);
            this.t.setRepeats(false);
            this.visitor = new DeepestScopeVisitor();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JavaScriptParser parser = JavaScriptLanguageSupport.this.getParser(this.textArea);
            if (parser == null) {
                return;
            }
            AstRoot astRoot = parser.getAstRoot();
            if (astRoot != null) {
                int dot = this.textArea.getCaretPosition();
                this.visitor.reset(dot);
                astRoot.visit(this.visitor);
                AstNode scope = this.visitor.getDeepestScope();
                if (scope != null && scope != astRoot) {
                    int start = scope.getAbsolutePosition();
                    int end = Math.min(start + scope.getLength() - 1, this.textArea.getDocument().getLength());
                    try {
                        int startLine = this.textArea.getLineOfOffset(start);
                        int endLine = end < 0 ? this.textArea.getLineCount() : this.textArea.getLineOfOffset(end);
                        this.textArea.setActiveLineRange(startLine, endLine);
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                } else {
                    this.textArea.setActiveLineRange(-1, -1);
                }
            }
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            this.t.restart();
        }

        public void uninstall() {
            this.textArea.removeCaretListener(this);
        }
    }

    private class JavaScriptAutoCompletion
    extends AutoCompletion {
        private RSyntaxTextArea textArea;

        public JavaScriptAutoCompletion(JavaScriptCompletionProvider provider, RSyntaxTextArea textArea) {
            super(provider);
            this.textArea = textArea;
        }

        @Override
        protected String getReplacementText(Completion c, Document doc, int start, int len) {
            String replacement = super.getReplacementText(c, doc, start, len);
            if (c instanceof JavaScriptShorthandCompletion) {
                try {
                    int caret = this.textArea.getCaretPosition();
                    String leadingWS = RSyntaxUtilities.getLeadingWhitespace(doc, caret);
                    if (replacement.indexOf(10) > -1) {
                        replacement = replacement.replaceAll("\n", "\n" + leadingWS);
                    }
                } catch (BadLocationException badLocationException) {
                    // empty catch block
                }
            }
            return replacement;
        }

        @Override
        protected int refreshPopupWindow() {
            JavaScriptParser parser = JavaScriptLanguageSupport.this.getParser(this.textArea);
            RSyntaxDocument doc = (RSyntaxDocument)this.textArea.getDocument();
            String style = this.textArea.getSyntaxEditingStyle();
            parser.parse(doc, style);
            return super.refreshPopupWindow();
        }
    }

    private static class Info
    implements PropertyChangeListener {
        public JavaScriptCompletionProvider provider;

        public Info(JavaScriptCompletionProvider provider, JavaScriptParser parser) {
            this.provider = provider;
            parser.addPropertyChangeListener("AST", this);
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("AST".equals(name)) {
                AstRoot root = (AstRoot)e.getNewValue();
                this.provider.setASTRoot(root);
            }
        }
    }
}

