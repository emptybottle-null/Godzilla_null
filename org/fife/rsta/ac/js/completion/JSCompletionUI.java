/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import org.fife.ui.autocomplete.Completion;

public interface JSCompletionUI
extends Completion {
    public static final int LOCAL_VARIABLE_RELEVANCE = 9;
    public static final int GLOBAL_VARIABLE_RELEVANCE = 8;
    public static final int DEFAULT_VARIABLE_RELEVANCE = 7;
    public static final int STATIC_FIELD_RELEVANCE = 6;
    public static final int BEAN_METHOD_RELEVANCE = 5;
    public static final int DEFAULT_FUNCTION_RELEVANCE = 4;
    public static final int GLOBAL_FUNCTION_RELEVANCE = 3;
    public static final int DEFAULT_CLASS_RELEVANCE = 2;
    public static final int BASIC_COMPLETION_RELEVANCE = 1;
    public static final int TEMPLATE_RELEVANCE = 0;
}

