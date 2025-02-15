/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.util.ResourceBundle;
import org.fife.rsta.ac.ShorthandCompletionCache;
import org.fife.rsta.ac.java.JavaTemplateCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class JavaShorthandCompletionCache
extends ShorthandCompletionCache {
    private static final String MSG = "org.fife.rsta.ac.java.resources";
    private static final ResourceBundle msg = ResourceBundle.getBundle("org.fife.rsta.ac.java.resources");

    public JavaShorthandCompletionCache(DefaultCompletionProvider templateProvider, DefaultCompletionProvider commentsProvider) {
        super(templateProvider, commentsProvider);
        String template = "System.out.println(${});${cursor}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "sysout", "sysout", template, msg.getString("sysout.shortDesc"), msg.getString("sysout.summary")));
        template = "System.err.println(${});${cursor}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "syserr", "syserr", template, msg.getString("syserr.shortDesc"), msg.getString("syserr.summary")));
        template = "for (int ${i} = 0; ${i} < ${array}.length; ${i}++) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "for", "for-loop-array", template, msg.getString("for.array.shortDesc"), msg.getString("for.array.summary")));
        template = "for (int ${i} = 0; ${i} < ${10}; ${i}++) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "for", "for-loop", template, msg.getString("for.loop.shortDesc"), msg.getString("for.loop.summary")));
        template = "if (${condition}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "if", "if-cond", template, msg.getString("if.cond.shortDesc"), msg.getString("if.cond.summary")));
        template = "if (${condition}) {\n\t${cursor}\n}\nelse {\n\t\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "if", "if-else", template, msg.getString("if.else.shortDesc"), msg.getString("if.else.summary")));
        template = "do {\n\t${cursor}\n} while (${condition});";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "do", "do-loop", template, msg.getString("do.shortDesc"), msg.getString("do.summary")));
        template = "while (${condition}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "while", "while-cond", template, msg.getString("while.shortDesc"), msg.getString("while.summary")));
        template = "new Runnable() {\n\tpublic void run() {\n\t\t${cursor}\n\t}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "runnable", "runnable", template, msg.getString("runnable.shortDesc")));
        template = "switch (${key}) {\n\tcase ${value}:\n\t\t${cursor}\n\t\tbreak;\n\tdefault:\n\t\tbreak;\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "switch", "switch-statement", template, msg.getString("switch.case.shortDesc"), msg.getString("switch.case.summary")));
        template = "try {\n\t ${cursor} \n} catch (${err}) {\n\t\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "try", "try-catch", template, msg.getString("try.catch.shortDesc"), msg.getString("try.catch.summary")));
        template = "catch (${err}) {\n\t${cursor}\n}";
        this.addShorthandCompletion(new JavaTemplateCompletion(templateProvider, "catch", "catch-block", template, msg.getString("catch.block.shortDesc"), msg.getString("catch.block.summary")));
        this.addCommentCompletion(new BasicCompletion(commentsProvider, "TODO:", null, msg.getString("todo")));
        this.addCommentCompletion(new BasicCompletion(commentsProvider, "FIXME:", null, msg.getString("fixme")));
    }
}

