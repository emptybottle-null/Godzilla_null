/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics.jfr;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;

@Category(value={"Spring Application"})
@Label(value="Startup Step")
@Description(value="Spring Application Startup")
class FlightRecorderStartupEvent
extends Event {
    public final long eventId;
    public final long parentId;
    @Label(value="Name")
    public final String name;
    @Label(value="Tags")
    String tags = "";

    public FlightRecorderStartupEvent(long eventId, String name, long parentId) {
        this.name = name;
        this.eventId = eventId;
        this.parentId = parentId;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

