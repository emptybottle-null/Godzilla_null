/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics.jfr;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.metrics.StartupStep;
import org.springframework.core.metrics.jfr.FlightRecorderStartupEvent;

class FlightRecorderStartupStep
implements StartupStep {
    private final FlightRecorderStartupEvent event;
    private final FlightRecorderTags tags = new FlightRecorderTags();
    private final Consumer<FlightRecorderStartupStep> recordingCallback;

    public FlightRecorderStartupStep(long id, String name, long parentId, Consumer<FlightRecorderStartupStep> recordingCallback) {
        this.event = new FlightRecorderStartupEvent(id, name, parentId);
        this.event.begin();
        this.recordingCallback = recordingCallback;
    }

    @Override
    public String getName() {
        return this.event.name;
    }

    @Override
    public long getId() {
        return this.event.eventId;
    }

    @Override
    public Long getParentId() {
        return this.event.parentId;
    }

    @Override
    public StartupStep tag(String key, String value) {
        this.tags.add(key, value);
        return this;
    }

    @Override
    public StartupStep tag(String key, Supplier<String> value) {
        this.tags.add(key, value.get());
        return this;
    }

    @Override
    public StartupStep.Tags getTags() {
        return this.tags;
    }

    @Override
    public void end() {
        this.event.end();
        if (this.event.shouldCommit()) {
            StringBuilder builder = new StringBuilder();
            this.tags.forEach(tag -> builder.append(tag.getKey()).append('=').append(tag.getValue()).append(','));
            this.event.setTags(builder.toString());
        }
        this.event.commit();
        this.recordingCallback.accept(this);
    }

    protected FlightRecorderStartupEvent getEvent() {
        return this.event;
    }

    static class FlightRecorderTag
    implements StartupStep.Tag {
        private final String key;
        private final String value;

        public FlightRecorderTag(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public String getValue() {
            return this.value;
        }
    }

    static class FlightRecorderTags
    implements StartupStep.Tags {
        private StartupStep.Tag[] tags = new StartupStep.Tag[0];

        FlightRecorderTags() {
        }

        public void add(String key, String value) {
            StartupStep.Tag[] newTags = new StartupStep.Tag[this.tags.length + 1];
            System.arraycopy(this.tags, 0, newTags, 0, this.tags.length);
            newTags[newTags.length - 1] = new FlightRecorderTag(key, value);
            this.tags = newTags;
        }

        public void add(String key, Supplier<String> value) {
            this.add(key, value.get());
        }

        @Override
        @NotNull
        public Iterator<StartupStep.Tag> iterator() {
            return new TagsIterator();
        }

        private class TagsIterator
        implements Iterator<StartupStep.Tag> {
            private int idx = 0;

            private TagsIterator() {
            }

            @Override
            public boolean hasNext() {
                return this.idx < FlightRecorderTags.this.tags.length;
            }

            @Override
            public StartupStep.Tag next() {
                return FlightRecorderTags.this.tags[this.idx++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("tags are append only");
            }
        }
    }
}

