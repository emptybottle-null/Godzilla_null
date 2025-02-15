/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.ShorthandCompletionCache;
import org.fife.rsta.ac.java.DocCommentCompletionProvider;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.JavaShorthandCompletionCache;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class JavaCompletionProvider
extends LanguageAwareCompletionProvider {
    private SourceCompletionProvider sourceProvider = (SourceCompletionProvider)this.getDefaultCompletionProvider();
    private CompilationUnit cu;

    public JavaCompletionProvider() {
        this((JarManager)null);
    }

    public JavaCompletionProvider(JarManager jarManager) {
        super(new SourceCompletionProvider(jarManager));
        this.sourceProvider.setJavaProvider(this);
        this.setShorthandCompletionCache(new JavaShorthandCompletionCache(this.sourceProvider, new DefaultCompletionProvider()));
        this.setDocCommentCompletionProvider(new DocCommentCompletionProvider());
    }

    public void addJar(LibraryInfo info) throws IOException {
        this.sourceProvider.addJar(info);
    }

    public void clearJars() {
        this.sourceProvider.clearJars();
    }

    @Override
    public String getAlreadyEnteredText(JTextComponent comp) {
        return this.sourceProvider.getAlreadyEnteredText(comp);
    }

    public synchronized CompilationUnit getCompilationUnit() {
        return this.cu;
    }

    @Override
    public List<Completion> getCompletionsAt(JTextComponent tc, Point p) {
        return this.sourceProvider.getCompletionsAt(tc, p);
    }

    public List<LibraryInfo> getJars() {
        return this.sourceProvider.getJars();
    }

    @Override
    public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent tc) {
        return null;
    }

    public boolean removeJar(File jar) {
        return this.sourceProvider.removeJar(jar);
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

    public synchronized void setCompilationUnit(CompilationUnit cu) {
        this.cu = cu;
    }

    public void setShorthandCompletionCache(ShorthandCompletionCache cache) {
        this.sourceProvider.setShorthandCache(cache);
        this.setCommentCompletions(cache);
    }
}

