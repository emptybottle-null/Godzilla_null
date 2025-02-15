/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.TypeDeclarationOptions;
import org.fife.ui.autocomplete.Completion;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

public class PreProcessingScripts {
    private SourceCompletionProvider provider;
    private Set<Completion> preProcessingCompletions = new HashSet<Completion>();

    public PreProcessingScripts(SourceCompletionProvider provider) {
        this.provider = provider;
    }

    public void parseScript(String scriptText, TypeDeclarationOptions options) {
        if (scriptText != null && scriptText.length() > 0) {
            CompilerEnvirons env = JavaScriptParser.createCompilerEnvironment(new JavaScriptParser.JSErrorReporter(), this.provider.getLanguageSupport());
            Parser parser = new Parser(env);
            StringReader r = new StringReader(scriptText);
            try {
                AstRoot root = parser.parse(r, null, 0);
                CodeBlock block = this.provider.iterateAstRoot(root, this.preProcessingCompletions, "", Integer.MAX_VALUE, options);
                this.provider.recursivelyAddLocalVars(this.preProcessingCompletions, block, 0, null, false, true);
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    public void reset() {
        this.preProcessingCompletions.clear();
        this.provider.getVariableResolver().resetPreProcessingVariables(true);
    }

    public Set<Completion> getCompletions() {
        return this.preProcessingCompletions;
    }
}

