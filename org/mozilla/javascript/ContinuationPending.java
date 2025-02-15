/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.NativeContinuation;

public class ContinuationPending
extends RuntimeException {
    private static final long serialVersionUID = 4956008116771118856L;
    private NativeContinuation continuationState;
    private Object applicationState;

    ContinuationPending(NativeContinuation continuationState) {
        this.continuationState = continuationState;
    }

    public Object getContinuation() {
        return this.continuationState;
    }

    NativeContinuation getContinuationState() {
        return this.continuationState;
    }

    public void setApplicationState(Object applicationState) {
        this.applicationState = applicationState;
    }

    public Object getApplicationState() {
        return this.applicationState;
    }
}

