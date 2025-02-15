/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import javax.swing.ListCellRenderer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public interface LanguageSupport {
    public static final String PROPERTY_LANGUAGE_PARSER = "org.fife.rsta.ac.LanguageSupport.LanguageParser";

    public int getAutoActivationDelay();

    public ListCellRenderer<Object> getDefaultCompletionCellRenderer();

    public boolean getShowDescWindow();

    public boolean isAutoActivationEnabled();

    public boolean isAutoCompleteEnabled();

    public void install(RSyntaxTextArea var1);

    public boolean isParameterAssistanceEnabled();

    public void setAutoActivationDelay(int var1);

    public void setAutoActivationEnabled(boolean var1);

    public void setAutoCompleteEnabled(boolean var1);

    public void setDefaultCompletionCellRenderer(ListCellRenderer<Object> var1);

    public void setParameterAssistanceEnabled(boolean var1);

    public void setShowDescWindow(boolean var1);

    public void uninstall(RSyntaxTextArea var1);
}

