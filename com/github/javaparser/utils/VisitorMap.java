/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VisitorMap<N extends Node, V>
implements Map<N, V> {
    private final Map<EqualsHashcodeOverridingFacade, V> innerMap = new HashMap<EqualsHashcodeOverridingFacade, V>();
    private final GenericVisitor<Integer, Void> hashcodeVisitor;
    private final GenericVisitor<Boolean, Visitable> equalsVisitor;

    public VisitorMap(GenericVisitor<Integer, Void> hashcodeVisitor, GenericVisitor<Boolean, Visitable> equalsVisitor) {
        this.hashcodeVisitor = hashcodeVisitor;
        this.equalsVisitor = equalsVisitor;
    }

    @Override
    public int size() {
        return this.innerMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.innerMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.innerMap.containsKey(new EqualsHashcodeOverridingFacade(this, (Node)key));
    }

    @Override
    public boolean containsValue(Object value) {
        return this.innerMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.innerMap.get(new EqualsHashcodeOverridingFacade(this, (Node)key));
    }

    @Override
    public V put(N key, V value) {
        return this.innerMap.put(new EqualsHashcodeOverridingFacade(this, key), value);
    }

    @Override
    public V remove(Object key) {
        return this.innerMap.remove(new EqualsHashcodeOverridingFacade(this, (Node)key));
    }

    @Override
    public void putAll(Map<? extends N, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        this.innerMap.clear();
    }

    @Override
    public Set<N> keySet() {
        return this.innerMap.keySet().stream().map(k -> ((EqualsHashcodeOverridingFacade)k).overridden).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return this.innerMap.values();
    }

    @Override
    public Set<Map.Entry<N, V>> entrySet() {
        return this.innerMap.entrySet().stream().map(e -> new AbstractMap.SimpleEntry(((EqualsHashcodeOverridingFacade)e.getKey()).overridden, e.getValue())).collect(Collectors.toSet());
    }

    private static class EqualsHashcodeOverridingFacade
    implements Visitable {
        private final N overridden;
        final /* synthetic */ VisitorMap this$0;

        EqualsHashcodeOverridingFacade(N overridden) {
            this.this$0 = var1_1;
            this.overridden = overridden;
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
            throw new AssertionError();
        }

        @Override
        public <A> void accept(VoidVisitor<A> v, A arg) {
            throw new AssertionError();
        }

        public final int hashCode() {
            return (Integer)this.overridden.accept(this.this$0.hashcodeVisitor, null);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof EqualsHashcodeOverridingFacade)) {
                return false;
            }
            return (Boolean)this.overridden.accept(this.this$0.equalsVisitor, ((EqualsHashcodeOverridingFacade)obj).overridden);
        }
    }
}

