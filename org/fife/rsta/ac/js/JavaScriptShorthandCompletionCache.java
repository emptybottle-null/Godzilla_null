/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.util.ResourceBundle;
import org.fife.rsta.ac.ShorthandCompletionCache;
import org.fife.rsta.ac.js.completion.JavaScriptTemplateCompletion;
import org.fife.rsta.ac.js.completion.JavascriptBasicCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class JavaScriptShorthandCompletionCache
extends ShorthandCompletionCache {
    private static final String MSG = "org.fife.rsta.ac.js.resources";
    private static final ResourceBundle msg = ResourceBundle.getBundle("org.fife.rsta.ac.js.resources");

    public JavaScriptShorthandCompletionCache(DefaultCompletionProvider templateProvider, DefaultCompletionProvider commentsProvider, boolean e4xSuppport) {
        super(templateProvider, commentsProvider);
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "do"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "if"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "while"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "for"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "switch"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "try"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "catch"));
        this.addShorthandCompletion(new JavascriptBasicCompletion((CompletionProvider)templateProvider, "case"));
        String template = "for (var ${i} = 0; ${i} < ${array}.length; ${i}++) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "for", "for-loop-array", template, msg.getString("for.array.shortDesc"), msg.getString("for.array.summary")));
        template = "for (var ${i} = 0; ${i} < ${10}; ${i}++) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "for", "for-loop", template, msg.getString("for.loop.shortDesc"), msg.getString("for.loop.summary")));
        template = "for (var ${iterable_element} in ${iterable})\n{\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "for", "for-loop-in", template, msg.getString("for.in.shortDesc"), msg.getString("for.in.summary")));
        if (e4xSuppport) {
            template = "for each (var ${iterable_element} in ${iterable})\n{\n\t${cursor}\n}";
            this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "for", "for-loop-in-each", template, msg.getString("for.in.each.shortDesc"), msg.getString("for.in.each.summary")));
        }
        template = "do {\n\t${cursor}\n} while (${condition});";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "do-while", "do-loop", template, msg.getString("do.shortDesc"), msg.getString("do.summary")));
        template = "if (${condition}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "if", "if-cond", template, msg.getString("if.cond.shortDesc"), msg.getString("if.cond.summary")));
        template = "if (${condition}) {\n\t${cursor}\n} else {\n\t\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "if", "if-else", template, msg.getString("if.else.shortDesc"), msg.getString("if.else.summary")));
        template = "while (${condition}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "while", "while-cond", template, msg.getString("while.shortDesc"), msg.getString("while.summary")));
        template = "switch (${key}) {\n\tcase ${value}:\n\t\t${cursor}\n\t\tbreak;\n\tdefault:\n\t\tbreak;\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "switch", "switch-statement", template, msg.getString("switch.case.shortDesc"), msg.getString("switch.case.summary")));
        template = "try {\n\t ${cursor} \n} catch (${err}) {\n\t\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "try", "try-catch", template, msg.getString("try.catch.shortDesc"), msg.getString("try.catch.summary")));
        template = "catch (${err}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaScriptTemplateCompletion(templateProvider, "catch", "catch-block", template, msg.getString("catch.block.shortDesc"), msg.getString("catch.block.summary")));
        this.addCommentCompletion(new BasicCompletion(commentsProvider, "TODO:", null, msg.getString("todo")));
        this.addCommentCompletion(new BasicCompletion(commentsProvider, "FIXME:", null, msg.getString("fixme")));
    }
}

