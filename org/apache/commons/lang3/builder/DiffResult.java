/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiffResult<T>
implements Iterable<Diff<?>> {
    public static final String OBJECTS_SAME_STRING = "";
    private static final String DIFFERS_STRING = "differs from";
    private final List<Diff<?>> diffList;
    private final T lhs;
    private final T rhs;
    private final ToStringStyle style;

    DiffResult(T lhs, T rhs, List<Diff<?>> diffList, ToStringStyle style) {
        Validate.notNull(lhs, "lhs", new Object[0]);
        Validate.notNull(rhs, "rhs", new Object[0]);
        Validate.notNull(diffList, "diffList", new Object[0]);
        this.diffList = diffList;
        this.lhs = lhs;
        this.rhs = rhs;
        this.style = style == null ? ToStringStyle.DEFAULT_STYLE : style;
    }

    public T getLeft() {
        return this.lhs;
    }

    public T getRight() {
        return this.rhs;
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(this.diffList);
    }

    public int getNumberOfDiffs() {
        return this.diffList.size();
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    public String toString() {
        return this.toString(this.style);
    }

    public String toString(ToStringStyle style) {
        if (this.diffList.isEmpty()) {
            return OBJECTS_SAME_STRING;
        }
        ToStringBuilder lhsBuilder = new ToStringBuilder(this.lhs, style);
        ToStringBuilder rhsBuilder = new ToStringBuilder(this.rhs, style);
        for (Diff<?> diff : this.diffList) {
            lhsBuilder.append(diff.getFieldName(), diff.getLeft());
            rhsBuilder.append(diff.getFieldName(), diff.getRight());
        }
        return String.format("%s %s %s", lhsBuilder.build(), DIFFERS_STRING, rhsBuilder.build());
    }

    @Override
    public Iterator<Diff<?>> iterator() {
        return this.diffList.iterator();
    }
}

