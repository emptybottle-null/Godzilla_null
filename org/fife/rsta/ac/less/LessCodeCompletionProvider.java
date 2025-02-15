/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.less;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.css.PropertyValueCompletionProvider;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.FunctionCompletion;

class LessCodeCompletionProvider
extends PropertyValueCompletionProvider {
    private List<Completion> functionCompletions;

    LessCodeCompletionProvider() {
        super(true);
        try {
            this.functionCompletions = this.createFunctionCompletions();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    protected boolean addLessCompletions(List<Completion> completions, PropertyValueCompletionProvider.LexerState state, JTextComponent comp, String alreadyEntered) {
        boolean modified = false;
        if (alreadyEntered != null && alreadyEntered.length() > 0 && alreadyEntered.charAt(0) == '@') {
            this.addLessVariableCompletions(completions, comp, alreadyEntered);
            modified = true;
        }
        if (state == PropertyValueCompletionProvider.LexerState.VALUE) {
            this.addLessBuiltinFunctionCompletions(completions, alreadyEntered);
            modified = true;
        }
        return modified;
    }

    private void addLessBuiltinFunctionCompletions(List<Completion> completions, String alreadyEntered) {
        completions.addAll(this.functionCompletions);
    }

    private void addLessVariableCompletions(List<Completion> completions, JTextComponent comp, String alreadyEntered) {
    }

    private List<Completion> createFunctionCompletions() throws IOException {
        Icon functionIcon = this.loadIcon("methpub_obj");
        List<Completion> completions = this.loadFromXML("data/less_functions.xml");
        for (Completion fc : completions) {
            ((FunctionCompletion)fc).setIcon(functionIcon);
        }
        Collections.sort(completions);
        return completions;
    }

    private Icon loadIcon(String name) {
        String imageFile = "img/" + name + ".gif";
        URL res = this.getClass().getResource(imageFile);
        if (res == null) {
            throw new IllegalArgumentException("icon not found: " + imageFile);
        }
        return new ImageIcon(res);
    }
}

