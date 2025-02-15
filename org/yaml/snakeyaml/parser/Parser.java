/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.events.Event;

public interface Parser {
    public boolean checkEvent(Event.ID var1);

    public Event peekEvent();

    public Event getEvent();
}

