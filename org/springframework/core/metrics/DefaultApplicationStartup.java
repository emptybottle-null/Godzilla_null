/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;

class DefaultApplicationStartup
implements ApplicationStartup {
    private static final DefaultStartupStep DEFAULT_STARTUP_STEP = new DefaultStartupStep();

    DefaultApplicationStartup() {
    }

    @Override
    public DefaultStartupStep start(String name) {
        return DEFAULT_STARTUP_STEP;
    }

    static class DefaultStartupStep
    implements StartupStep {
        private final DefaultTags TAGS = new DefaultTags();

        DefaultStartupStep() {
        }

        @Override
        public String getName() {
            return "default";
        }

        @Override
        public long getId() {
            return 0L;
        }

        @Override
        public Long getParentId() {
            return null;
        }

        @Override
        public StartupStep.Tags getTags() {
            return this.TAGS;
        }

        @Override
        public StartupStep tag(String key, String value) {
            return this;
        }

        @Override
        public StartupStep tag(String key, Supplier<String> value) {
            return this;
        }

        @Override
        public void end() {
        }

        static class DefaultTags
        implements StartupStep.Tags {
            DefaultTags() {
            }

            @Override
            public Iterator<StartupStep.Tag> iterator() {
                return Collections.emptyIterator();
            }
        }
    }
}

