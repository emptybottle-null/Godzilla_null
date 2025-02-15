/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.GraphConnections;
import com.google.common.graph.Graphs;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

final class DirectedGraphConnections<N, V>
implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    private int predecessorCount;
    private int successorCount;

    private DirectedGraphConnections(Map<N, Object> adjacentNodeValues, int predecessorCount, int successorCount) {
        this.adjacentNodeValues = Preconditions.checkNotNull(adjacentNodeValues);
        this.predecessorCount = Graphs.checkNonNegative(predecessorCount);
        this.successorCount = Graphs.checkNonNegative(successorCount);
        Preconditions.checkState(predecessorCount <= adjacentNodeValues.size() && successorCount <= adjacentNodeValues.size());
    }

    static <N, V> DirectedGraphConnections<N, V> of() {
        int initialCapacity = 4;
        return new DirectedGraphConnections(new HashMap(initialCapacity, 1.0f), 0, 0);
    }

    static <N, V> DirectedGraphConnections<N, V> ofImmutable(Set<N> predecessors, Map<N, V> successorValues) {
        HashMap<N, Object> adjacentNodeValues = new HashMap<N, Object>();
        adjacentNodeValues.putAll(successorValues);
        for (N predecessor : predecessors) {
            Object value = adjacentNodeValues.put(predecessor, PRED);
            if (value == null) continue;
            adjacentNodeValues.put(predecessor, new PredAndSucc(value));
        }
        return new DirectedGraphConnections(ImmutableMap.copyOf(adjacentNodeValues), predecessors.size(), successorValues.size());
    }

    @Override
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override
    public Set<N> predecessors() {
        return new AbstractSet<N>(){

            @Override
            public UnmodifiableIterator<N> iterator() {
                final Iterator entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>(){

                    @Override
                    protected N computeNext() {
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry)entries.next();
                            if (!DirectedGraphConnections.isPredecessor(entry.getValue())) continue;
                            return entry.getKey();
                        }
                        return this.endOfData();
                    }
                };
            }

            @Override
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override
    public Set<N> successors() {
        return new AbstractSet<N>(){

            @Override
            public UnmodifiableIterator<N> iterator() {
                final Iterator entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>(){

                    @Override
                    protected N computeNext() {
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry)entries.next();
                            if (!DirectedGraphConnections.isSuccessor(entry.getValue())) continue;
                            return entry.getKey();
                        }
                        return this.endOfData();
                    }
                };
            }

            @Override
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override
    public V value(N node) {
        Object value = this.adjacentNodeValues.get(node);
        if (value == PRED) {
            return null;
        }
        if (value instanceof PredAndSucc) {
            return (V)((PredAndSucc)value).successorValue;
        }
        return (V)value;
    }

    @Override
    public void removePredecessor(N node) {
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == PRED) {
            this.adjacentNodeValues.remove(node);
            Graphs.checkNonNegative(--this.predecessorCount);
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, ((PredAndSucc)previousValue).successorValue);
            Graphs.checkNonNegative(--this.predecessorCount);
        }
    }

    @Override
    public V removeSuccessor(Object node) {
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == null || previousValue == PRED) {
            return null;
        }
        if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, PRED);
            Graphs.checkNonNegative(--this.successorCount);
            return (V)((PredAndSucc)previousValue).successorValue;
        }
        this.adjacentNodeValues.remove(node);
        Graphs.checkNonNegative(--this.successorCount);
        return (V)previousValue;
    }

    @Override
    public void addPredecessor(N node, V unused) {
        Object previousValue = this.adjacentNodeValues.put(node, PRED);
        if (previousValue == null) {
            Graphs.checkPositive(++this.predecessorCount);
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, previousValue);
        } else if (previousValue != PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(previousValue));
            Graphs.checkPositive(++this.predecessorCount);
        }
    }

    @Override
    public V addSuccessor(N node, V value) {
        Object previousValue = this.adjacentNodeValues.put(node, value);
        if (previousValue == null) {
            Graphs.checkPositive(++this.successorCount);
            return null;
        }
        if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            return (V)((PredAndSucc)previousValue).successorValue;
        }
        if (previousValue == PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            Graphs.checkPositive(++this.successorCount);
            return null;
        }
        return (V)previousValue;
    }

    private static boolean isPredecessor(@Nullable Object value) {
        return value == PRED || value instanceof PredAndSucc;
    }

    private static boolean isSuccessor(@Nullable Object value) {
        return value != PRED && value != null;
    }

    private static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object successorValue) {
            this.successorValue = successorValue;
        }
    }
}

