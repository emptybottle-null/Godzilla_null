/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.perl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.c.CCompletionProvider;
import org.fife.rsta.ac.common.CodeBlock;
import org.fife.rsta.ac.common.TokenScanner;
import org.fife.rsta.ac.common.VariableDeclaration;
import org.fife.rsta.ac.perl.PerlCodeCompletionProvider;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;

public class PerlCompletionProvider
extends CCompletionProvider {
    private boolean useParensWithFunctions;
    private CaseInsensitiveComparator comparator = new CaseInsensitiveComparator();

    @Override
    protected void addShorthandCompletions(DefaultCompletionProvider codeCP) {
    }

    private CodeBlock createAst(RSyntaxTextArea textArea) {
        CodeBlock ast = new CodeBlock(0);
        TokenScanner scanner = new TokenScanner(textArea);
        this.parseCodeBlock(scanner, ast);
        return ast;
    }

    @Override
    protected CompletionProvider createCodeCompletionProvider() {
        PerlCodeCompletionProvider cp = new PerlCodeCompletionProvider(this);
        this.loadCodeCompletionsFromXml(cp);
        this.addShorthandCompletions(cp);
        cp.setAutoActivationRules(true, null);
        return cp;
    }

    @Override
    protected CompletionProvider createStringCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.setAutoActivationRules(true, null);
        return cp;
    }

    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        List<Completion> completions = super.getCompletionsImpl(comp);
        SortedSet<Completion> varCompletions = this.getVariableCompletions(comp);
        if (varCompletions != null) {
            completions.addAll(varCompletions);
            Collections.sort(completions);
        }
        return completions;
    }

    @Override
    public char getParameterListEnd() {
        return this.getUseParensWithFunctions() ? (char)')' : '\u0000';
    }

    @Override
    public char getParameterListStart() {
        return this.getUseParensWithFunctions() ? (char)'(' : ' ';
    }

    public boolean getUseParensWithFunctions() {
        return this.useParensWithFunctions;
    }

    private SortedSet<Completion> getVariableCompletions(JTextComponent comp) {
        char firstChar;
        RSyntaxTextArea textArea = (RSyntaxTextArea)comp;
        int dot = textArea.getCaretPosition();
        SortedSet<Completion> varCompletions = new TreeSet<Completion>(this.comparator);
        CompletionProvider p = this.getDefaultCompletionProvider();
        String text = p.getAlreadyEnteredText(comp);
        char c = firstChar = text.length() == 0 ? (char)'\u0000' : text.charAt(0);
        if (firstChar != '$' && firstChar != '@' && firstChar != '%') {
            System.out.println("DEBUG: No use matching variables, exiting");
            return null;
        }
        CodeBlock block = this.createAst(textArea);
        this.recursivelyAddLocalVars(varCompletions, block, dot, firstChar);
        if (varCompletions.size() > 0) {
            BasicCompletion from = new BasicCompletion(p, text);
            BasicCompletion to = new BasicCompletion(p, text + '{');
            varCompletions = varCompletions.subSet(from, to);
        }
        return varCompletions;
    }

    @Override
    protected String getXmlResource() {
        return "data/perl5.xml";
    }

    private void parseCodeBlock(TokenScanner scanner, CodeBlock block) {
        Token t = scanner.next();
        while (t != null) {
            if (t.isRightCurly()) {
                block.setEndOffset(t.getOffset());
                return;
            }
            if (t.isLeftCurly()) {
                CodeBlock child = block.addChildCodeBlock(t.getOffset());
                this.parseCodeBlock(scanner, child);
            } else if (t.getType() == 17) {
                VariableDeclaration varDec = new VariableDeclaration(t.getLexeme(), t.getOffset());
                block.addVariable(varDec);
            }
            t = scanner.next();
        }
    }

    private void recursivelyAddLocalVars(SortedSet<Completion> completions, CodeBlock block, int dot, int firstChar) {
        VariableDeclaration dec;
        int decOffs;
        int i;
        if (!block.contains(dot)) {
            return;
        }
        for (i = 0; i < block.getVariableDeclarationCount() && (decOffs = (dec = block.getVariableDeclaration(i)).getOffset()) < dot; ++i) {
            String name = dec.getName();
            char ch = name.charAt(0);
            if (firstChar > ch) continue;
            if (firstChar < ch) {
                name = firstChar + name.substring(1);
            }
            BasicCompletion c = new BasicCompletion((CompletionProvider)this, name);
            completions.add(c);
        }
        for (i = 0; i < block.getChildCodeBlockCount(); ++i) {
            CodeBlock child = block.getChildCodeBlock(i);
            if (!child.contains(dot)) continue;
            this.recursivelyAddLocalVars(completions, child, dot, firstChar);
            return;
        }
    }

    public void setUseParensWithFunctions(boolean use) {
        this.useParensWithFunctions = use;
    }

    private static class CaseInsensitiveComparator
    implements Comparator<Completion>,
    Serializable {
        private CaseInsensitiveComparator() {
        }

        @Override
        public int compare(Completion c1, Completion c2) {
            String s1 = c1.getInputText();
            String s2 = c2.getInputText();
            return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        }
    }
}

