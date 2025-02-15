/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.GoToMemberAction;
import org.fife.rsta.ac.java.ClassCompletion;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.JavaCellRenderer;
import org.fife.rsta.ac.java.JavaCompletionProvider;
import org.fife.rsta.ac.java.JavaLinkGenerator;
import org.fife.rsta.ac.java.JavaParamListCellRenderer;
import org.fife.rsta.ac.java.JavaParser;
import org.fife.rsta.ac.java.JavadocUrlHandler;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.tree.JavaOutlineTree;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class JavaLanguageSupport
extends AbstractLanguageSupport {
    private Map<JavaParser, Info> parserToInfoMap = new HashMap<JavaParser, Info>();
    private JarManager jarManager = new JarManager();
    private static final String PROPERTY_LISTENER = "org.fife.rsta.ac.java.JavaLanguageSupport.Listener";

    public JavaLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
    }

    public JavaCompletionProvider getCompletionProvider(RSyntaxTextArea textArea) {
        AutoCompletion ac = this.getAutoCompletionFor(textArea);
        return (JavaCompletionProvider)ac.getCompletionProvider();
    }

    public JarManager getJarManager() {
        return this.jarManager;
    }

    public JavaParser getParser(RSyntaxTextArea textArea) {
        Object parser = textArea.getClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser");
        if (parser instanceof JavaParser) {
            return (JavaParser)parser;
        }
        return null;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        JavaCompletionProvider p = new JavaCompletionProvider(this.jarManager);
        JavaAutoCompletion ac = new JavaAutoCompletion(p, textArea);
        ac.setListCellRenderer(new JavaCellRenderer());
        ac.setAutoCompleteEnabled(this.isAutoCompleteEnabled());
        ac.setAutoActivationEnabled(this.isAutoActivationEnabled());
        ac.setAutoActivationDelay(this.getAutoActivationDelay());
        ac.setExternalURLHandler(new JavadocUrlHandler());
        ac.setParameterAssistanceEnabled(this.isParameterAssistanceEnabled());
        ac.setParamChoicesRenderer(new JavaParamListCellRenderer());
        ac.setShowDescWindow(this.getShowDescWindow());
        ac.install(textArea);
        this.installImpl(textArea, ac);
        textArea.setToolTipSupplier(p);
        Listener listener = new Listener(textArea);
        textArea.putClientProperty(PROPERTY_LISTENER, listener);
        JavaParser parser = new JavaParser(textArea);
        textArea.putClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser", parser);
        textArea.addParser(parser);
        textArea.setToolTipSupplier(p);
        Info info = new Info(textArea, p, parser);
        this.parserToInfoMap.put(parser, info);
        this.installKeyboardShortcuts(textArea);
        textArea.setLinkGenerator(new JavaLinkGenerator(this));
    }

    private void installKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.put(KeyStroke.getKeyStroke(79, c | shift), "GoToType");
        am.put("GoToType", new GoToMemberAction(JavaOutlineTree.class));
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        JavaParser parser = this.getParser(textArea);
        Info info = this.parserToInfoMap.remove(parser);
        if (info != null) {
            parser.removePropertyChangeListener("CompilationUnit", info);
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
        textArea.setLinkGenerator(null);
    }

    private void uninstallKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.remove(KeyStroke.getKeyStroke(79, c | shift));
        am.remove("GoToType");
    }

    private class Listener
    implements CaretListener,
    ActionListener {
        private RSyntaxTextArea textArea;
        private Timer t;

        public Listener(RSyntaxTextArea textArea) {
            this.textArea = textArea;
            textArea.addCaretListener(this);
            this.t = new Timer(650, this);
            this.t.setRepeats(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JavaParser parser = JavaLanguageSupport.this.getParser(this.textArea);
            if (parser == null) {
                return;
            }
            CompilationUnit cu = parser.getCompilationUnit();
            if (cu != null) {
                int dot = this.textArea.getCaretPosition();
                Point p = cu.getEnclosingMethodRange(dot);
                if (p != null) {
                    try {
                        int startLine = this.textArea.getLineOfOffset(p.x);
                        int endOffs = Math.min(p.y, this.textArea.getDocument().getLength());
                        int endLine = this.textArea.getLineOfOffset(endOffs);
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

    private class JavaAutoCompletion
    extends AutoCompletion {
        private RSyntaxTextArea textArea;
        private String replacementTextPrefix;

        public JavaAutoCompletion(JavaCompletionProvider provider, RSyntaxTextArea textArea) {
            super(provider);
            this.textArea = textArea;
        }

        private String getCurrentLineText() {
            int caretPosition = this.textArea.getCaretPosition();
            Element root = this.textArea.getDocument().getDefaultRootElement();
            int line = root.getElementIndex(caretPosition);
            Element elem = root.getElement(line);
            int endOffset = elem.getEndOffset();
            int lineStart = elem.getStartOffset();
            String text = "";
            try {
                text = this.textArea.getText(lineStart, endOffset - lineStart).trim();
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected String getReplacementText(Completion c, Document doc, int start, int len) {
            String text = super.getReplacementText(c, doc, start, len);
            if (this.replacementTextPrefix != null) {
                text = this.replacementTextPrefix + text;
                this.replacementTextPrefix = null;
            }
            return text;
        }

        private ImportToAddInfo getShouldAddImport(ClassCompletion cc) {
            String text = this.getCurrentLineText();
            if (!text.startsWith("import ")) {
                String pkgName;
                String ccPkg;
                JavaCompletionProvider provider = (JavaCompletionProvider)this.getCompletionProvider();
                CompilationUnit cu = provider.getCompilationUnit();
                int offset = 0;
                boolean alreadyImported = false;
                if (cu == null) {
                    return null;
                }
                if ("java.lang".equals(cc.getPackageName())) {
                    return null;
                }
                String className = cc.getClassName(false);
                String fqClassName = cc.getClassName(true);
                int lastClassNameDot = fqClassName.lastIndexOf(46);
                boolean ccInPackage = lastClassNameDot > -1;
                Package pkg = cu.getPackage();
                if (ccInPackage && pkg != null ? (ccPkg = fqClassName.substring(0, lastClassNameDot)).equals(pkgName = pkg.getName()) : !ccInPackage && pkg == null) {
                    return null;
                }
                Iterator<ImportDeclaration> i = cu.getImportIterator();
                while (i.hasNext()) {
                    int dot;
                    ImportDeclaration id = i.next();
                    offset = id.getNameEndOffset() + 1;
                    if (id.isStatic()) continue;
                    if (id.isWildcard()) {
                        String classPkg;
                        String imported;
                        String importedPkg;
                        if (lastClassNameDot <= -1 || !(importedPkg = (imported = id.getName()).substring(0, dot = imported.lastIndexOf(46))).equals(classPkg = fqClassName.substring(0, lastClassNameDot))) continue;
                        alreadyImported = true;
                        break;
                    }
                    String fullyImportedClassName = id.getName();
                    String importedClassName = fullyImportedClassName.substring((dot = fullyImportedClassName.lastIndexOf(46)) + 1);
                    if (!className.equals(importedClassName)) continue;
                    offset = -1;
                    if (!fqClassName.equals(fullyImportedClassName)) break;
                    alreadyImported = true;
                    break;
                }
                if (!alreadyImported) {
                    StringBuilder importToAdd = new StringBuilder();
                    if (offset == 0 && pkg != null) {
                        offset = pkg.getNameEndOffset() + 1;
                        importToAdd.append('\n');
                    }
                    if (offset > -1) {
                        if (offset > 0) {
                            importToAdd.append("\nimport ").append(fqClassName).append(';');
                        } else {
                            importToAdd.append("import ").append(fqClassName).append(";\n");
                        }
                        return new ImportToAddInfo(offset, importToAdd.toString());
                    }
                    int dot = fqClassName.lastIndexOf(46);
                    if (dot > -1) {
                        String pkgName2;
                        this.replacementTextPrefix = pkgName2 = fqClassName.substring(0, dot + 1);
                    }
                }
            }
            return null;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        protected void insertCompletion(Completion c, boolean typedParamListStartChar) {
            ImportToAddInfo importInfo = null;
            if (c instanceof ClassCompletion && (importInfo = this.getShouldAddImport((ClassCompletion)c)) != null) {
                this.textArea.beginAtomicEdit();
            }
            try {
                super.insertCompletion(c, typedParamListStartChar);
                if (importInfo != null) {
                    this.textArea.insert(importInfo.text, importInfo.offs);
                }
            } finally {
                this.textArea.endAtomicEdit();
            }
        }

        @Override
        protected int refreshPopupWindow() {
            JavaParser parser = JavaLanguageSupport.this.getParser(this.textArea);
            RSyntaxDocument doc = (RSyntaxDocument)this.textArea.getDocument();
            String style = this.textArea.getSyntaxEditingStyle();
            parser.parse(doc, style);
            return super.refreshPopupWindow();
        }
    }

    private static class Info
    implements PropertyChangeListener {
        public JavaCompletionProvider provider;

        public Info(RSyntaxTextArea textArea, JavaCompletionProvider provider, JavaParser parser) {
            this.provider = provider;
            parser.addPropertyChangeListener("CompilationUnit", this);
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("CompilationUnit".equals(name)) {
                CompilationUnit cu = (CompilationUnit)e.getNewValue();
                this.provider.setCompilationUnit(cu);
            }
        }
    }

    private static class ImportToAddInfo {
        public int offs;
        public String text;

        public ImportToAddInfo(int offset, String text) {
            this.offs = offset;
            this.text = text;
        }
    }
}

