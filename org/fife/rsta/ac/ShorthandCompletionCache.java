/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.Completion;

public class ShorthandCompletionCache {
    private List<Completion> shorthandCompletion = new ArrayList<Completion>();
    private List<Completion> commentCompletion = new ArrayList<Completion>();
    private AbstractCompletionProvider templateProvider;
    private AbstractCompletionProvider commentProvider;

    public ShorthandCompletionCache(AbstractCompletionProvider templateProvider, AbstractCompletionProvider commentProvider) {
        this.templateProvider = templateProvider;
        this.commentProvider = commentProvider;
    }

    public void addShorthandCompletion(Completion completion) {
        ShorthandCompletionCache.addSorted(this.shorthandCompletion, completion);
    }

    private static void addSorted(List<Completion> list, Completion completion) {
        int index = Collections.binarySearch(list, completion);
        if (index < 0) {
            index = -(index + 1);
        }
        list.add(index, completion);
    }

    public List<Completion> getShorthandCompletions() {
        return this.shorthandCompletion;
    }

    public void removeShorthandCompletion(Completion completion) {
        this.shorthandCompletion.remove(completion);
    }

    public void clearCache() {
        this.shorthandCompletion.clear();
    }

    public void addCommentCompletion(Completion completion) {
        ShorthandCompletionCache.addSorted(this.commentCompletion, completion);
    }

    public List<Completion> getCommentCompletions() {
        return this.commentCompletion;
    }

    public void removeCommentCompletion(Completion completion) {
        this.commentCompletion.remove(completion);
    }

    public AbstractCompletionProvider getTemplateProvider() {
        return this.templateProvider;
    }

    public AbstractCompletionProvider getCommentProvider() {
        return this.commentProvider;
    }
}

