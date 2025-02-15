/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;

public abstract class Event {
    private final Mark startMark;
    private final Mark endMark;

    public Event(Mark startMark, Mark endMark) {
        this.startMark = startMark;
        this.endMark = endMark;
    }

    public String toString() {
        return "<" + this.getClass().getName() + "(" + this.getArguments() + ")>";
    }

    public Mark getStartMark() {
        return this.startMark;
    }

    public Mark getEndMark() {
        return this.endMark;
    }

    protected String getArguments() {
        return "";
    }

    public boolean is(ID id) {
        return this.getEventId() == id;
    }

    public abstract ID getEventId();

    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return this.toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public static enum ID {
        Alias,
        Comment,
        DocumentEnd,
        DocumentStart,
        MappingEnd,
        MappingStart,
        Scalar,
        SequenceEnd,
        SequenceStart,
        StreamEnd,
        StreamStart;

    }
}

