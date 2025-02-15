/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task;

import org.springframework.core.task.TaskRejectedException;

public class TaskTimeoutException
extends TaskRejectedException {
    public TaskTimeoutException(String msg) {
        super(msg);
    }

    public TaskTimeoutException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

