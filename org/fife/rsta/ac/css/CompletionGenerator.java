/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import java.util.List;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public interface CompletionGenerator {
    public List<Completion> generate(CompletionProvider var1, String var2);
}

