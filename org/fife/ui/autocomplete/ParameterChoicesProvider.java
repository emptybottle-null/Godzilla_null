/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.List;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public interface ParameterChoicesProvider {
    public List<Completion> getParameterChoices(JTextComponent var1, ParameterizedCompletion.Parameter var2);
}

