/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class VisitorSet<N extends Node>
implements Set<N> {
    private final Set<EqualsHashcodeOverridingFacade> innerSet = new HashSet<EqualsHashcodeOverridingFacade>();
    private final GenericVisitor<Integer, Void> hashcodeVisitor;
    private final GenericVisitor<Boolean, Visitable> equalsVisitor;

    public VisitorSet(GenericVisitor<Integer, Void> hashcodeVisitor, GenericVisitor<Boolean, Visitable> equalsVisitor) {
        this.hashcodeVisitor = hashcodeVisitor;
        this.equalsVisitor = equalsVisitor;
    }

    @Override
    public boolean add(N elem) {
        return this.innerSet.add(new EqualsHashcodeOverridingFacade(this, elem));
    }

    @Override
    public boolean addAll(Collection<? extends N> col) {
        boolean modified = false;
        for (Node elem : col) {
            if (!this.add((N)elem)) continue;
            modified = true;
        }
        return modified;
    }

    @Override
    public void clear() {
        this.innerSet.clear();
    }

    @Override
    public boolean contains(Object elem) {
        return this.innerSet.contains(new EqualsHashcodeOverridingFacade(this, (Node)elem));
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        for (Object elem : col) {
            if (this.contains(elem)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.innerSet.isEmpty();
    }

    @Override
    public Iterator<N> iterator() {
        return new Iterator<N>(){
            final Iterator<EqualsHashcodeOverridingFacade> itr;
            {
                this.itr = VisitorSet.this.innerSet.iterator();
            }

            @Override
            public boolean hasNext() {
                return this.itr.hasNext();
            }

            @Override
            public N next() {
                return this.itr.next().overridden;
            }

            @Override
            public void remove() {
                this.itr.remove();
            }
        };
    }

    @Override
    public boolean remove(Object elem) {
        return this.innerSet.remove(new EqualsHashcodeOverridingFacade(this, (Node)elem));
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        boolean modified = false;
        for (Object elem : col) {
            if (!this.remove(elem)) continue;
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        int oldSize = this.size();
        this.clear();
        this.addAll((Collection<? extends N>)col);
        return this.size() != oldSize;
    }

    @Override
    public int size() {
        return this.innerSet.size();
    }

    @Override
    public Object[] toArray() {
        return this.innerSet.stream().map(facade -> ((EqualsHashcodeOverridingFacade)facade).overridden).collect(Collectors.toList()).toArray();
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        return this.innerSet.stream().map(facade -> ((EqualsHashcodeOverridingFacade)facade).overridden).collect(Collectors.toList()).toArray(arr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (this.size() == 0) {
            return sb.append("]").toString();
        }
        for (EqualsHashcodeOverridingFacade facade : this.innerSet) {
            sb.append(facade.overridden.toString() + ",");
        }
        return sb.replace(sb.length() - 2, sb.length(), "]").toString();
    }

    private static class EqualsHashcodeOverridingFacade
    implements Visitable {
        private final N overridden;
        final /* synthetic */ VisitorSet this$0;

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

