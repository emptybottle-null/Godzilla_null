/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DefaultExceptionContext
implements ExceptionContext,
Serializable {
    private static final long serialVersionUID = 20110706L;
    private final List<Pair<String, Object>> contextValues = new ArrayList<Pair<String, Object>>();

    @Override
    public DefaultExceptionContext addContextValue(String label, Object value) {
        this.contextValues.add(new ImmutablePair<String, Object>(label, value));
        return this;
    }

    @Override
    public DefaultExceptionContext setContextValue(String label, Object value) {
        this.contextValues.removeIf(p -> StringUtils.equals(label, (CharSequence)p.getKey()));
        this.addContextValue(label, value);
        return this;
    }

    @Override
    public List<Object> getContextValues(String label) {
        ArrayList<Object> values = new ArrayList<Object>();
        for (Pair<String, Object> pair : this.contextValues) {
            if (!StringUtils.equals(label, pair.getKey())) continue;
            values.add(pair.getValue());
        }
        return values;
    }

    @Override
    public Object getFirstContextValue(String label) {
        for (Pair<String, Object> pair : this.contextValues) {
            if (!StringUtils.equals(label, pair.getKey())) continue;
            return pair.getValue();
        }
        return null;
    }

    @Override
    public Set<String> getContextLabels() {
        HashSet<String> labels = new HashSet<String>();
        for (Pair<String, Object> pair : this.contextValues) {
            labels.add(pair.getKey());
        }
        return labels;
    }

    @Override
    public List<Pair<String, Object>> getContextEntries() {
        return this.contextValues;
    }

    @Override
    public String getFormattedExceptionMessage(String baseMessage) {
        StringBuilder buffer = new StringBuilder(256);
        if (baseMessage != null) {
            buffer.append(baseMessage);
        }
        if (!this.contextValues.isEmpty()) {
            if (buffer.length() > 0) {
                buffer.append('\n');
            }
            buffer.append("Exception Context:\n");
            int i = 0;
            for (Pair<String, Object> pair : this.contextValues) {
                buffer.append("\t[");
                buffer.append(++i);
                buffer.append(':');
                buffer.append(pair.getKey());
                buffer.append("=");
                Object value = pair.getValue();
                if (value == null) {
                    buffer.append("null");
                } else {
                    String valueStr;
                    try {
                        valueStr = value.toString();
                    } catch (Exception e) {
                        valueStr = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(e);
                    }
                    buffer.append(valueStr);
                }
                buffer.append("]\n");
            }
            buffer.append("---------------------------------");
        }
        return buffer.toString();
    }
}

