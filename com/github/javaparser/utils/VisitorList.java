/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class VisitorList<N extends Node>
implements List<N> {
    protected List<EqualsHashcodeOverridingFacade> innerList;
    protected final GenericVisitor<Integer, Void> hashcodeVisitor;
    protected final GenericVisitor<Boolean, Visitable> equalsVisitor;

    public VisitorList(GenericVisitor<Integer, Void> hashcodeVisitor, GenericVisitor<Boolean, Visitable> equalsVisitor) {
        this.hashcodeVisitor = hashcodeVisitor;
        this.equalsVisitor = equalsVisitor;
        this.innerList = new ArrayList<EqualsHashcodeOverridingFacade>();
    }

    @Override
    public boolean add(N elem) {
        return this.innerList.add(new EqualsHashcodeOverridingFacade(this, elem));
    }

    @Override
    public void add(int index, N elem) {
        this.innerList.add(index, new EqualsHashcodeOverridingFacade(this, elem));
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
    public boolean addAll(int index, Collection<? extends N> col) {
        if (col.isEmpty()) {
            return false;
        }
        for (Node elem : col) {
            if (index == this.size()) {
                this.add((N)elem);
            } else {
                this.add(index, (N)elem);
            }
            ++index;
        }
        return true;
    }

    @Override
    public void clear() {
        this.innerList.clear();
    }

    @Override
    public boolean contains(Object elem) {
        return this.innerList.contains(new EqualsHashcodeOverridingFacade(this, (Node)elem));
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
    public N get(int index) {
        return (N)this.innerList.get(index).overridden;
    }

    @Override
    public int indexOf(Object elem) {
        return this.innerList.indexOf(new EqualsHashcodeOverridingFacade(this, (Node)elem));
    }

    @Override
    public boolean isEmpty() {
        return this.innerList.isEmpty();
    }

    @Override
    public Iterator<N> iterator() {
        return new Iterator<N>(){
            final Iterator<EqualsHashcodeOverridingFacade> itr;
            {
                this.itr = VisitorList.this.innerList.iterator();
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
    public int lastIndexOf(Object elem) {
        return this.innerList.lastIndexOf(new EqualsHashcodeOverridingFacade(this, (Node)elem));
    }

    @Override
    public ListIterator<N> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<N> listIterator(final int index) {
        return new ListIterator<N>(){
            final ListIterator<EqualsHashcodeOverridingFacade> itr;
            {
                this.itr = VisitorList.this.innerList.listIterator(index);
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

            @Override
            public void add(N elem) {
                this.itr.add(new EqualsHashcodeOverridingFacade(VisitorList.this, elem));
            }

            @Override
            public boolean hasPrevious() {
                return this.itr.hasPrevious();
            }

            @Override
            public int nextIndex() {
                return this.itr.nextIndex();
            }

            @Override
            public N previous() {
                return this.itr.previous().overridden;
            }

            @Override
            public int previousIndex() {
                return this.itr.previousIndex();
            }

            @Override
            public void set(N elem) {
                this.itr.set(new EqualsHashcodeOverridingFacade(VisitorList.this, elem));
            }
        };
    }

    @Override
    public boolean remove(Object elem) {
        return this.innerList.remove(new EqualsHashcodeOverridingFacade(this, (Node)elem));
    }

    @Override
    public N remove(int index) {
        return (N)this.innerList.remove(index).overridden;
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
    public N set(int index, N elem) {
        return (N)this.innerList.set(index, new EqualsHashcodeOverridingFacade(this, elem)).overridden;
    }

    @Override
    public int size() {
        return this.innerList.size();
    }

    @Override
    public List<N> subList(final int fromIndex, final int toIndex) {
        return new VisitorList<N>(this.hashcodeVisitor, this.equalsVisitor){
            {
                super(hashcodeVisitor, equalsVisitor);
                this.innerList = VisitorList.this.innerList.subList(fromIndex, toIndex);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return this.innerList.stream().map(facade -> ((EqualsHashcodeOverridingFacade)facade).overridden).collect(Collectors.toList()).toArray();
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        return this.innerList.stream().map(facade -> ((EqualsHashcodeOverridingFacade)facade).overridden).collect(Collectors.toList()).toArray(arr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (this.size() == 0) {
            return sb.append("]").toString();
        }
        for (EqualsHashcodeOverridingFacade facade : this.innerList) {
            sb.append(facade.overridden.toString() + ", ");
        }
        return sb.replace(sb.length() - 2, sb.length(), "]").toString();
    }

    private static class EqualsHashcodeOverridingFacade
    implements Visitable {
        private final N overridden;
        final /* synthetic */ VisitorList this$0;

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
            return this.overridden.accept(this.this$0.hashcodeVisitor, null);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof EqualsHashcodeOverridingFacade)) {
                return false;
            }
            return this.overridden.accept(this.this$0.equalsVisitor, ((EqualsHashcodeOverridingFacade)obj).overridden);
        }
    }
}

