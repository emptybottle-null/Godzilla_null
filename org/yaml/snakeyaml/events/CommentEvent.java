/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

public final class CommentEvent
extends Event {
    private final CommentType type;
    private final String value;

    public CommentEvent(CommentType type, String value, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        if (type == null) {
            throw new NullPointerException("Event Type must be provided.");
        }
        this.type = type;
        if (value == null) {
            throw new NullPointerException("Value must be provided.");
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public CommentType getCommentType() {
        return this.type;
    }

    @Override
    protected String getArguments() {
        return super.getArguments() + "type=" + (Object)((Object)this.type) + ", value=" + this.value;
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.Comment;
    }
}

