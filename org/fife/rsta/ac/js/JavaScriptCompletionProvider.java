/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import org.fife.rsta.ac.ShorthandCompletionCache;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.js.JavaScriptLanguageSupport;
import org.fife.rsta.ac.js.JavaScriptShorthandCompletionCache;
import org.fife.rsta.ac.js.JsDocCompletionProvider;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;
import org.mozilla.javascript.ast.AstRoot;

public class JavaScriptCompletionProvider
extends LanguageAwareCompletionProvider {
    private AstRoot astRoot;
    private SourceCompletionProvider sourceProvider = (SourceCompletionProvider)this.getDefaultCompletionProvider();
    private JavaScriptLanguageSupport languageSupport;

    public JavaScriptCompletionProvider(JarManager jarManager, JavaScriptLanguageSupport languageSupport) {
        this(new SourceCompletionProvider(languageSupport.isXmlAvailable()), jarManager, languageSupport);
    }

    public JavaScriptCompletionProvider(SourceCompletionProvider provider, JarManager jarManager, JavaScriptLanguageSupport ls) {
        super(provider);
        this.sourceProvider.setJarManager(jarManager);
        this.languageSupport = ls;
        this.setShorthandCompletionCache(new JavaScriptShorthandCompletionCache(this.sourceProvider, new DefaultCompletionProvider(), ls.isXmlAvailable()));
        this.sourceProvider.setParent(this);
        this.setDocCommentCompletionProvider(new JsDocCompletionProvider());
    }

    public synchronized AstRoot getASTRoot() {
        return this.astRoot;
    }

    public JarManager getJarManager() {
        return ((SourceCompletionProvider)this.getDefaultCompletionProvider()).getJarManager();
    }

    public JavaScriptLanguageSupport getLanguageSupport() {
        return this.languageSupport;
    }

    public SourceCompletionProvider getProvider() {
        return this.sourceProvider;
    }

    public void setShorthandCompletionCache(ShorthandCompletionCache shorthandCache) {
        this.sourceProvider.setShorthandCache(shorthandCache);
        this.setCommentCompletions(shorthandCache);
    }

    private void setCommentCompletions(ShorthandCompletionCache shorthandCache) {
        AbstractCompletionProvider provider = shorthandCache.getCommentProvider();
        if (provider != null) {
            for (Completion c : shorthandCache.getCommentCompletions()) {
                provider.addCompletion(c);
            }
            this.setCommentCompletionProvider(provider);
        }
    }

    public synchronized void setASTRoot(AstRoot root) {
        this.astRoot = root;
    }

    protected synchronized void reparseDocument(int offset) {
        this.sourceProvider.parseDocument(offset);
    }
}

