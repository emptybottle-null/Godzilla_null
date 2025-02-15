/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.emitter;

import java.io.IOException;
import org.yaml.snakeyaml.events.Event;

public interface Emitable {
    public void emit(Event var1) throws IOException;
}

