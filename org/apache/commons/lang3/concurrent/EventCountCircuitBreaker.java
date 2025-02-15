/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

public class EventCountCircuitBreaker
extends AbstractCircuitBreaker<Integer> {
    private static final Map<AbstractCircuitBreaker.State, StateStrategy> STRATEGY_MAP = EventCountCircuitBreaker.createStrategyMap();
    private final AtomicReference<CheckIntervalData> checkIntervalData = new AtomicReference<CheckIntervalData>(new CheckIntervalData(0, 0L));
    private final int openingThreshold;
    private final long openingInterval;
    private final int closingThreshold;
    private final long closingInterval;

    public EventCountCircuitBreaker(int openingThreshold, long openingInterval, TimeUnit openingUnit, int closingThreshold, long closingInterval, TimeUnit closingUnit) {
        this.openingThreshold = openingThreshold;
        this.openingInterval = openingUnit.toNanos(openingInterval);
        this.closingThreshold = closingThreshold;
        this.closingInterval = closingUnit.toNanos(closingInterval);
    }

    public EventCountCircuitBreaker(int openingThreshold, long checkInterval, TimeUnit checkUnit, int closingThreshold) {
        this(openingThreshold, checkInterval, checkUnit, closingThreshold, checkInterval, checkUnit);
    }

    public EventCountCircuitBreaker(int threshold, long checkInterval, TimeUnit checkUnit) {
        this(threshold, checkInterval, checkUnit, threshold);
    }

    public int getOpeningThreshold() {
        return this.openingThreshold;
    }

    public long getOpeningInterval() {
        return this.openingInterval;
    }

    public int getClosingThreshold() {
        return this.closingThreshold;
    }

    public long getClosingInterval() {
        return this.closingInterval;
    }

    @Override
    public boolean checkState() {
        return this.performStateCheck(0);
    }

    @Override
    public boolean incrementAndCheckState(Integer increment) {
        return this.performStateCheck(increment);
    }

    public boolean incrementAndCheckState() {
        return this.incrementAndCheckState(1);
    }

    @Override
    public void open() {
        super.open();
        this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
    }

    @Override
    public void close() {
        super.close();
        this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
    }

    private boolean performStateCheck(int increment) {
        long time;
        AbstractCircuitBreaker.State currentState;
        CheckIntervalData nextData;
        CheckIntervalData currentData;
        do {
            time = this.nanoTime();
            currentState = (AbstractCircuitBreaker.State)((Object)this.state.get());
        } while (!this.updateCheckIntervalData(currentData = this.checkIntervalData.get(), nextData = this.nextCheckIntervalData(increment, currentData, currentState, time)));
        if (EventCountCircuitBreaker.stateStrategy(currentState).isStateTransition(this, currentData, nextData)) {
            currentState = currentState.oppositeState();
            this.changeStateAndStartNewCheckInterval(currentState);
        }
        return !EventCountCircuitBreaker.isOpen(currentState);
    }

    private boolean updateCheckIntervalData(CheckIntervalData currentData, CheckIntervalData nextData) {
        return currentData == nextData || this.checkIntervalData.compareAndSet(currentData, nextData);
    }

    private void changeStateAndStartNewCheckInterval(AbstractCircuitBreaker.State newState) {
        this.changeState(newState);
        this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
    }

    private CheckIntervalData nextCheckIntervalData(int increment, CheckIntervalData currentData, AbstractCircuitBreaker.State currentState, long time) {
        CheckIntervalData nextData = EventCountCircuitBreaker.stateStrategy(currentState).isCheckIntervalFinished(this, currentData, time) ? new CheckIntervalData(increment, time) : currentData.increment(increment);
        return nextData;
    }

    long nanoTime() {
        return System.nanoTime();
    }

    private static StateStrategy stateStrategy(AbstractCircuitBreaker.State state) {
        return STRATEGY_MAP.get((Object)state);
    }

    private static Map<AbstractCircuitBreaker.State, StateStrategy> createStrategyMap() {
        EnumMap<AbstractCircuitBreaker.State, StateStrategy> map = new EnumMap<AbstractCircuitBreaker.State, StateStrategy>(AbstractCircuitBreaker.State.class);
        map.put(AbstractCircuitBreaker.State.CLOSED, new StateStrategyClosed());
        map.put(AbstractCircuitBreaker.State.OPEN, new StateStrategyOpen());
        return map;
    }

    private static class StateStrategyOpen
    extends StateStrategy {
        private StateStrategyOpen() {
        }

        @Override
        public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
            return nextData.getCheckIntervalStart() != currentData.getCheckIntervalStart() && currentData.getEventCount() < breaker.getClosingThreshold();
        }

        @Override
        protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
            return breaker.getClosingInterval();
        }
    }

    private static class StateStrategyClosed
    extends StateStrategy {
        private StateStrategyClosed() {
        }

        @Override
        public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
            return nextData.getEventCount() > breaker.getOpeningThreshold();
        }

        @Override
        protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
            return breaker.getOpeningInterval();
        }
    }

    private static abstract class StateStrategy {
        private StateStrategy() {
        }

        public boolean isCheckIntervalFinished(EventCountCircuitBreaker breaker, CheckIntervalData currentData, long now) {
            return now - currentData.getCheckIntervalStart() > this.fetchCheckInterval(breaker);
        }

        public abstract boolean isStateTransition(EventCountCircuitBreaker var1, CheckIntervalData var2, CheckIntervalData var3);

        protected abstract long fetchCheckInterval(EventCountCircuitBreaker var1);
    }

    private static class CheckIntervalData {
        private final int eventCount;
        private final long checkIntervalStart;

        CheckIntervalData(int count, long intervalStart) {
            this.eventCount = count;
            this.checkIntervalStart = intervalStart;
        }

        public int getEventCount() {
            return this.eventCount;
        }

        public long getCheckIntervalStart() {
            return this.checkIntervalStart;
        }

        public CheckIntervalData increment(int delta) {
            return delta == 0 ? this : new CheckIntervalData(this.getEventCount() + delta, this.getCheckIntervalStart());
        }
    }
}

