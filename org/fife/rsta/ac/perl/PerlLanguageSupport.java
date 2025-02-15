/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.perl;

import java.io.File;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.IOUtil;
import org.fife.rsta.ac.perl.PerlCompletionProvider;
import org.fife.rsta.ac.perl.PerlParser;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class PerlLanguageSupport
extends AbstractLanguageSupport {
    private PerlCompletionProvider provider;
    private PerlParser parser;
    private static File perlInstallLoc;
    private static File DEFAULT_PERL_INSTALL_LOC;
    private static boolean useParensWithFunctions;
    private static boolean useSystemPerldoc;

    public PerlLanguageSupport() {
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
        this.setAutoCompleteEnabled(true);
        this.setAutoActivationEnabled(true);
        this.setAutoActivationDelay(800);
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        CompletionCellRenderer ccr = new CompletionCellRenderer();
        ccr.setShowTypes(false);
        return ccr;
    }

    public static File getDefaultPerlInstallLocation() {
        return DEFAULT_PERL_INSTALL_LOC;
    }

    public static File getPerlInstallLocation() {
        return perlInstallLoc;
    }

    private PerlParser getParser() {
        if (this.parser == null) {
            this.parser = new PerlParser();
        }
        return this.parser;
    }

    public PerlParser getParser(RSyntaxTextArea textArea) {
        Object parser = textArea.getClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser");
        if (parser instanceof PerlParser) {
            return (PerlParser)parser;
        }
        return null;
    }

    private PerlCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new PerlCompletionProvider();
        }
        return this.provider;
    }

    public String getPerl5LibOverride() {
        return this.getParser().getPerl5LibOverride();
    }

    public boolean getUseParensWithFunctions() {
        return useParensWithFunctions;
    }

    public static boolean getUseSystemPerldoc() {
        return useSystemPerldoc;
    }

    public boolean getWarningsEnabled() {
        return this.getParser().getWarningsEnabled();
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        PerlCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        textArea.setToolTipSupplier(provider);
        PerlParser parser = this.getParser();
        textArea.addParser(parser);
        textArea.putClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser", parser);
    }

    public boolean isParsingEnabled() {
        return this.getParser().isEnabled();
    }

    public boolean isTaintModeEnabled() {
        return this.getParser().isTaintModeEnabled();
    }

    public void setParsingEnabled(boolean enabled) {
        this.getParser().setEnabled(enabled);
    }

    public void setPerl5LibOverride(String override) {
        this.getParser().setPerl5LibOverride(override);
    }

    public static void setPerlInstallLocation(File loc) {
        perlInstallLoc = loc;
    }

    public void setTaintModeEnabled(boolean enabled) {
        this.getParser().setTaintModeEnabled(enabled);
    }

    public void setWarningsEnabled(boolean enabled) {
        this.getParser().setWarningsEnabled(enabled);
    }

    public void setUseParensWithFunctions(boolean use) {
        if (use != useParensWithFunctions) {
            useParensWithFunctions = use;
            if (this.provider != null) {
                this.provider.setUseParensWithFunctions(use);
            }
        }
    }

    public static void setUseSystemPerldoc(boolean use) {
        useSystemPerldoc = use;
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        PerlParser parser = this.getParser(textArea);
        if (parser != null) {
            textArea.removeParser(parser);
        }
    }

    static {
        String path = IOUtil.getEnvSafely("PATH");
        if (path != null) {
            String perlLoc = "perl";
            if (File.separatorChar == '\\') {
                perlLoc = perlLoc + ".exe";
            }
            String[] dirs = path.split(File.pathSeparator);
            for (int i = 0; i < dirs.length; ++i) {
                File temp = new File(dirs[i], perlLoc);
                if (!temp.isFile()) continue;
                DEFAULT_PERL_INSTALL_LOC = new File(dirs[i]).getParentFile();
                break;
            }
            perlInstallLoc = DEFAULT_PERL_INSTALL_LOC;
        }
    }
}

