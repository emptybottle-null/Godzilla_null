/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.emitter;

import java.io.IOException;

interface EmitterState {
    public void expect() throws IOException;
}

