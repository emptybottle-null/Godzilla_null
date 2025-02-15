/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public interface ExceptionContext {
    public ExceptionContext addContextValue(String var1, Object var2);

    public ExceptionContext setContextValue(String var1, Object var2);

    public List<Object> getContextValues(String var1);

    public Object getFirstContextValue(String var1);

    public Set<String> getContextLabels();

    public List<Pair<String, Object>> getContextEntries();

    public String getFormattedExceptionMessage(String var1);
}

