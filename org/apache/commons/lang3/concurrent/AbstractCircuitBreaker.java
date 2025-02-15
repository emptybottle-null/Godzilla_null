/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.concurrent.CircuitBreaker;

public abstract class AbstractCircuitBreaker<T>
implements CircuitBreaker<T> {
    public static final String PROPERTY_NAME = "open";
    protected final AtomicReference<State> state = new AtomicReference<State>(State.CLOSED);
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Override
    public boolean isOpen() {
        return AbstractCircuitBreaker.isOpen(this.state.get());
    }

    @Override
    public boolean isClosed() {
        return !this.isOpen();
    }

    @Override
    public abstract boolean checkState();

    @Override
    public abstract boolean incrementAndCheckState(T var1);

    @Override
    public void close() {
        this.changeState(State.CLOSED);
    }

    @Override
    public void open() {
        this.changeState(State.OPEN);
    }

    protected static boolean isOpen(State state) {
        return state == State.OPEN;
    }

    protected void changeState(State newState) {
        if (this.state.compareAndSet(newState.oppositeState(), newState)) {
            this.changeSupport.firePropertyChange(PROPERTY_NAME, !AbstractCircuitBreaker.isOpen(newState), AbstractCircuitBreaker.isOpen(newState));
        }
    }

    public void addChangeListener(PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(listener);
    }

    protected static enum State {
        CLOSED{

            @Override
            public State oppositeState() {
                return OPEN;
            }
        }
        ,
        OPEN{

            @Override
            public State oppositeState() {
                return CLOSED;
            }
        };


        public abstract State oppositeState();
    }
}

