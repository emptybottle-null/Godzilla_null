/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.metrics.jfr;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;
import org.springframework.core.metrics.jfr.FlightRecorderStartupStep;

public class FlightRecorderApplicationStartup
implements ApplicationStartup {
    private final AtomicLong currentSequenceId = new AtomicLong(0L);
    private final Deque<Long> currentSteps = new ConcurrentLinkedDeque<Long>();

    public FlightRecorderApplicationStartup() {
        this.currentSteps.offerFirst(this.currentSequenceId.get());
    }

    @Override
    public StartupStep start(String name) {
        long sequenceId = this.currentSequenceId.incrementAndGet();
        this.currentSteps.offerFirst(sequenceId);
        return new FlightRecorderStartupStep(sequenceId, name, this.currentSteps.getFirst(), committedStep -> this.currentSteps.removeFirstOccurrence(sequenceId));
    }
}

