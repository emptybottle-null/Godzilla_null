/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

public final class DocumentEndEvent
extends Event {
    private final boolean explicit;

    public DocumentEndEvent(Mark startMark, Mark endMark, boolean explicit) {
        super(startMark, endMark);
        this.explicit = explicit;
    }

    public boolean getExplicit() {
        return this.explicit;
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.DocumentEnd;
    }
}

