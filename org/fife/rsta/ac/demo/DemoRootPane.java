/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.tree.TreeNode;
import org.fife.rsta.ac.AbstractSourceTree;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.demo.Actions;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.rsta.ac.java.tree.JavaOutlineTree;
import org.fife.rsta.ac.js.tree.JavaScriptOutlineTree;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.rsyntaxtextarea.ErrorStrip;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

class DemoRootPane
extends JRootPane
implements HyperlinkListener,
SyntaxConstants,
Actions {
    private JScrollPane treeSP;
    private AbstractSourceTree tree;
    private RSyntaxTextArea textArea;

    public DemoRootPane() {
        LanguageSupportFactory lsf = LanguageSupportFactory.get();
        LanguageSupport support = lsf.getSupportFor("text/java");
        JavaLanguageSupport jls = (JavaLanguageSupport)support;
        try {
            jls.getJarManager().addCurrentJreClassFileSource();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        JTree dummy = new JTree((TreeNode)null);
        this.treeSP = new JScrollPane(dummy);
        this.textArea = this.createTextArea();
        this.setText("CExample.txt", "text/c");
        RTextScrollPane scrollPane = new RTextScrollPane(this.textArea, true);
        scrollPane.setIconRowHeaderEnabled(true);
        scrollPane.getGutter().setBookmarkingEnabled(true);
        JSplitPane sp = new JSplitPane(1, this.treeSP, scrollPane);
        SwingUtilities.invokeLater(() -> sp.setDividerLocation(0.25));
        sp.setContinuousLayout(true);
        this.setJMenuBar(this.createMenuBar());
        ErrorStrip errorStrip = new ErrorStrip(this.textArea);
        JPanel cp = new JPanel(new BorderLayout());
        cp.add(sp);
        cp.add((Component)errorStrip, "After");
        this.setContentPane(cp);
    }

    private void addItem(Action a, ButtonGroup bg, JMenu menu) {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem(a);
        bg.add(item);
        menu.add(item);
    }

    private JMenuBar createMenuBar() {
        UIManager.LookAndFeelInfo[] infos;
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.add(new JMenuItem(new Actions.OpenAction(this)));
        menu.addSeparator();
        menu.add(new JMenuItem(new Actions.ExitAction()));
        mb.add(menu);
        menu = new JMenu("Language");
        ButtonGroup bg = new ButtonGroup();
        this.addItem(new Actions.StyleAction(this, "C", "CExample.txt", "text/c"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "CSS", "CssExample.txt", "text/css"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "Groovy", "GroovyExample.txt", "text/groovy"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "Java", "JavaExample.txt", "text/java"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "JavaScript", "JSExample.txt", "text/javascript"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "JSP", "JspExample.txt", "text/jsp"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "Less", "LessExample.txt", "text/less"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "Perl", "PerlExample.txt", "text/perl"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "HTML", "HtmlExample.txt", "text/html"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "PHP", "PhpExample.txt", "text/php"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "sh", "ShellExample.txt", "text/unix"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "TypeScript", "TypeScriptExample.txt", "text/typescript"), bg, menu);
        this.addItem(new Actions.StyleAction(this, "XML", "XMLExample.txt", "text/xml"), bg, menu);
        menu.getItem(0).setSelected(true);
        mb.add(menu);
        menu = new JMenu("LookAndFeel");
        bg = new ButtonGroup();
        for (UIManager.LookAndFeelInfo info : infos = UIManager.getInstalledLookAndFeels()) {
            this.addItem(new Actions.LookAndFeelAction(this, info), bg, menu);
        }
        mb.add(menu);
        menu = new JMenu("View");
        menu.add(new JCheckBoxMenuItem(new Actions.ToggleLayeredHighlightsAction(this)));
        mb.add(menu);
        menu = new JMenu("Help");
        menu.add(new JMenuItem(new Actions.AboutAction(this)));
        mb.add(menu);
        return mb;
    }

    private RSyntaxTextArea createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(25, 80);
        LanguageSupportFactory.get().register(textArea);
        textArea.setCaretPosition(0);
        textArea.addHyperlinkListener(this);
        textArea.requestFocusInWindow();
        textArea.setMarkOccurrences(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setTabsEmulated(true);
        textArea.setTabSize(3);
        ToolTipManager.sharedInstance().registerComponent(textArea);
        return textArea;
    }

    void focusTextArea() {
        this.textArea.requestFocusInWindow();
    }

    RSyntaxTextArea getTextArea() {
        return this.textArea;
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            URL url = e.getURL();
            if (url == null) {
                UIManager.getLookAndFeel().provideErrorFeedback(null);
            } else {
                JOptionPane.showMessageDialog(this, "URL clicked:\n" + url.toString());
            }
        }
    }

    public void openFile(File file) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            this.textArea.read(r, null);
            this.textArea.setCaretPosition(0);
            r.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            UIManager.getLookAndFeel().provideErrorFeedback(this);
            return;
        }
    }

    private void refreshSourceTree() {
        String language;
        if (this.tree != null) {
            this.tree.uninstall();
        }
        this.tree = "text/java".equals(language = this.textArea.getSyntaxEditingStyle()) ? new JavaOutlineTree() : ("text/javascript".equals(language) ? new JavaScriptOutlineTree() : ("text/xml".equals(language) ? new XmlOutlineTree() : null));
        if (this.tree != null) {
            this.tree.listenTo(this.textArea);
            this.treeSP.setViewportView(this.tree);
        } else {
            JTree dummy = new JTree((TreeNode)null);
            this.treeSP.setViewportView(dummy);
        }
        this.treeSP.revalidate();
    }

    void setText(String resource, String style) {
        this.textArea.setSyntaxEditingStyle(style);
        ClassLoader cl = this.getClass().getClassLoader();
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("examples/" + resource), StandardCharsets.UTF_8));
            this.textArea.read(r, null);
            r.close();
            this.textArea.setCaretPosition(0);
            this.textArea.discardAllEdits();
            this.refreshSourceTree();
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            this.textArea.setText("Type here to see syntax highlighting");
        }
    }
}

