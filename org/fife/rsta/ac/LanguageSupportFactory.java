/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class LanguageSupportFactory
implements PropertyChangeListener {
    private static final LanguageSupportFactory INSTANCE = new LanguageSupportFactory();
    private Map<String, String> styleToSupportClass;
    private Map<String, LanguageSupport> styleToSupport;
    private static final String LANGUAGE_SUPPORT_PROPERTY = "org.fife.rsta.ac.LanguageSupport";

    private LanguageSupportFactory() {
        this.createSupportMap();
    }

    public void addLanguageSupport(String style, String lsClassName) {
        this.styleToSupportClass.put(style, lsClassName);
    }

    private void createSupportMap() {
        this.styleToSupport = new HashMap<String, LanguageSupport>();
        this.styleToSupportClass = new HashMap<String, String>();
        String prefix = "org.fife.rsta.ac.";
        this.addLanguageSupport("text/c", prefix + "c.CLanguageSupport");
        this.addLanguageSupport("text/css", prefix + "css.CssLanguageSupport");
        this.addLanguageSupport("text/groovy", prefix + "groovy.GroovyLanguageSupport");
        this.addLanguageSupport("text/html", prefix + "html.HtmlLanguageSupport");
        this.addLanguageSupport("text/java", prefix + "java.JavaLanguageSupport");
        this.addLanguageSupport("text/javascript", prefix + "js.JavaScriptLanguageSupport");
        this.addLanguageSupport("text/jsp", prefix + "jsp.JspLanguageSupport");
        this.addLanguageSupport("text/less", prefix + "less.LessLanguageSupport");
        this.addLanguageSupport("text/perl", prefix + "perl.PerlLanguageSupport");
        this.addLanguageSupport("text/php", prefix + "php.PhpLanguageSupport");
        this.addLanguageSupport("text/typescript", prefix + "ts.TypeScriptLanguageSupport");
        this.addLanguageSupport("text/unix", prefix + "sh.ShellLanguageSupport");
        this.addLanguageSupport("text/xml", prefix + "xml.XmlLanguageSupport");
    }

    public static LanguageSupportFactory get() {
        return INSTANCE;
    }

    public LanguageSupport getSupportFor(String style) {
        String supportClazz;
        LanguageSupport support = this.styleToSupport.get(style);
        if (support == null && (supportClazz = this.styleToSupportClass.get(style)) != null) {
            try {
                Class<?> clazz = Class.forName(supportClazz);
                support = (LanguageSupport)clazz.newInstance();
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.styleToSupport.put(style, support);
            this.styleToSupportClass.remove(style);
        }
        return support;
    }

    private void installSupport(RSyntaxTextArea textArea) {
        String style = textArea.getSyntaxEditingStyle();
        LanguageSupport support = this.getSupportFor(style);
        if (support != null) {
            support.install(textArea);
        }
        textArea.putClientProperty(LANGUAGE_SUPPORT_PROPERTY, support);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        RSyntaxTextArea source = (RSyntaxTextArea)e.getSource();
        String name = e.getPropertyName();
        if ("RSTA.syntaxStyle".equals(name)) {
            this.uninstallSupport(source);
            this.installSupport(source);
        }
    }

    public void register(RSyntaxTextArea textArea) {
        this.installSupport(textArea);
        textArea.addPropertyChangeListener("RSTA.syntaxStyle", this);
    }

    private void uninstallSupport(RSyntaxTextArea textArea) {
        LanguageSupport support = (LanguageSupport)textArea.getClientProperty(LANGUAGE_SUPPORT_PROPERTY);
        if (support != null) {
            support.uninstall(textArea);
        }
    }

    public void unregister(RSyntaxTextArea textArea) {
        this.uninstallSupport(textArea);
        textArea.removePropertyChangeListener("RSTA.syntaxStyle", this);
    }
}

