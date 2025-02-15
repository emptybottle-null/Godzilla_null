/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class IOExceptionList
extends IOException {
    private static final long serialVersionUID = 1L;
    private final List<? extends Throwable> causeList;

    public IOExceptionList(List<? extends Throwable> causeList) {
        this(String.format("%,d exceptions: %s", causeList == null ? 0 : causeList.size(), causeList), causeList);
    }

    public IOExceptionList(String message, List<? extends Throwable> causeList) {
        super(message, causeList == null || causeList.isEmpty() ? null : causeList.get(0));
        this.causeList = causeList == null ? Collections.emptyList() : causeList;
    }

    public <T extends Throwable> T getCause(int index) {
        return (T)this.causeList.get(index);
    }

    public <T extends Throwable> T getCause(int index, Class<T> clazz) {
        return (T)((Throwable)clazz.cast(this.causeList.get(index)));
    }

    public <T extends Throwable> List<T> getCauseList() {
        return this.causeList;
    }

    public <T extends Throwable> List<T> getCauseList(Class<T> clazz) {
        return this.causeList;
    }
}

