/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress;

import java.io.IOException;

public class PasswordRequiredException
extends IOException {
    private static final long serialVersionUID = 1391070005491684483L;

    public PasswordRequiredException(String name) {
        super("Cannot read encrypted content from " + name + " without a password.");
    }
}

