/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public abstract class AbstractLanguageSupport
implements LanguageSupport {
    private Map<RSyntaxTextArea, AutoCompletion> textAreaToAutoCompletion;
    private boolean autoCompleteEnabled;
    private boolean autoActivationEnabled;
    private int autoActivationDelay;
    private boolean parameterAssistanceEnabled;
    private boolean showDescWindow;
    private ListCellRenderer<Object> renderer;

    protected AbstractLanguageSupport() {
        this.setDefaultCompletionCellRenderer(null);
        this.textAreaToAutoCompletion = new HashMap<RSyntaxTextArea, AutoCompletion>();
        this.autoCompleteEnabled = true;
        this.autoActivationEnabled = false;
        this.autoActivationDelay = 300;
    }

    protected AutoCompletion createAutoCompletion(CompletionProvider p) {
        AutoCompletion ac = new AutoCompletion(p);
        ac.setListCellRenderer(this.getDefaultCompletionCellRenderer());
        ac.setAutoCompleteEnabled(this.isAutoCompleteEnabled());
        ac.setAutoActivationEnabled(this.isAutoActivationEnabled());
        ac.setAutoActivationDelay(this.getAutoActivationDelay());
        ac.setParameterAssistanceEnabled(this.isParameterAssistanceEnabled());
        ac.setShowDescWindow(this.getShowDescWindow());
        return ac;
    }

    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new DefaultListCellRenderer();
    }

    private void delegateToSubstanceRenderer(CompletionCellRenderer ccr) {
        try {
            ccr.delegateToSubstanceRenderer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAutoActivationDelay() {
        return this.autoActivationDelay;
    }

    protected AutoCompletion getAutoCompletionFor(RSyntaxTextArea textArea) {
        return this.textAreaToAutoCompletion.get(textArea);
    }

    @Override
    public ListCellRenderer<Object> getDefaultCompletionCellRenderer() {
        return this.renderer;
    }

    @Override
    public boolean getShowDescWindow() {
        return this.showDescWindow;
    }

    protected Set<RSyntaxTextArea> getTextAreas() {
        return this.textAreaToAutoCompletion.keySet();
    }

    protected void installImpl(RSyntaxTextArea textArea, AutoCompletion ac) {
        this.textAreaToAutoCompletion.put(textArea, ac);
    }

    @Override
    public boolean isAutoActivationEnabled() {
        return this.autoActivationEnabled;
    }

    @Override
    public boolean isAutoCompleteEnabled() {
        return this.autoCompleteEnabled;
    }

    @Override
    public boolean isParameterAssistanceEnabled() {
        return this.parameterAssistanceEnabled;
    }

    @Override
    public void setAutoActivationDelay(int ms) {
        if ((ms = Math.max(0, ms)) != this.autoActivationDelay) {
            this.autoActivationDelay = ms;
            for (AutoCompletion ac : this.textAreaToAutoCompletion.values()) {
                ac.setAutoActivationDelay(this.autoActivationDelay);
            }
        }
    }

    @Override
    public void setAutoActivationEnabled(boolean enabled) {
        if (enabled != this.autoActivationEnabled) {
            this.autoActivationEnabled = enabled;
            for (AutoCompletion ac : this.textAreaToAutoCompletion.values()) {
                ac.setAutoActivationEnabled(enabled);
            }
        }
    }

    @Override
    public void setAutoCompleteEnabled(boolean enabled) {
        if (enabled != this.autoCompleteEnabled) {
            this.autoCompleteEnabled = enabled;
            for (AutoCompletion ac : this.textAreaToAutoCompletion.values()) {
                ac.setAutoCompleteEnabled(enabled);
            }
        }
    }

    @Override
    public void setDefaultCompletionCellRenderer(ListCellRenderer<Object> r) {
        if (r == null) {
            r = this.createDefaultCompletionCellRenderer();
        }
        if (r instanceof CompletionCellRenderer && Util.getUseSubstanceRenderers() && UIManager.getLookAndFeel().getClass().getName().contains(".Substance")) {
            CompletionCellRenderer ccr = (CompletionCellRenderer)r;
            this.delegateToSubstanceRenderer(ccr);
        }
        this.renderer = r;
    }

    @Override
    public void setParameterAssistanceEnabled(boolean enabled) {
        if (enabled != this.parameterAssistanceEnabled) {
            this.parameterAssistanceEnabled = enabled;
            for (AutoCompletion ac : this.textAreaToAutoCompletion.values()) {
                ac.setParameterAssistanceEnabled(enabled);
            }
        }
    }

    @Override
    public void setShowDescWindow(boolean show) {
        if (show != this.showDescWindow) {
            this.showDescWindow = show;
            for (AutoCompletion ac : this.textAreaToAutoCompletion.values()) {
                ac.setShowDescWindow(show);
            }
        }
    }

    protected void uninstallImpl(RSyntaxTextArea textArea) {
        AutoCompletion ac = this.getAutoCompletionFor(textArea);
        if (ac != null) {
            ac.uninstall();
        }
        this.textAreaToAutoCompletion.remove(textArea);
    }
}

