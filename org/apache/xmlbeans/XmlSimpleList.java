/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.xmlbeans.SimpleValue;

public class XmlSimpleList
implements List,
Serializable {
    private static final long serialVersionUID = 1L;
    private List underlying;

    public XmlSimpleList(List list) {
        this.underlying = list;
    }

    @Override
    public int size() {
        return this.underlying.size();
    }

    @Override
    public boolean isEmpty() {
        return this.underlying.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.underlying.contains(o);
    }

    @Override
    public boolean containsAll(Collection coll) {
        return this.underlying.containsAll(coll);
    }

    @Override
    public Object[] toArray() {
        return this.underlying.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return this.underlying.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Object get(int index) {
        return this.underlying.get(index);
    }

    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return this.underlying.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.underlying.lastIndexOf(o);
    }

    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    public List subList(int from, int to) {
        return new XmlSimpleList(this.underlying.subList(from, to));
    }

    @Override
    public Iterator iterator() {
        return new Iterator(){
            Iterator i;
            {
                this.i = XmlSimpleList.this.underlying.iterator();
            }

            @Override
            public boolean hasNext() {
                return this.i.hasNext();
            }

            public Object next() {
                return this.i.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public ListIterator listIterator() {
        return this.listIterator(0);
    }

    public ListIterator listIterator(final int index) {
        return new ListIterator(){
            ListIterator i;
            {
                this.i = XmlSimpleList.this.underlying.listIterator(index);
            }

            @Override
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override
            public Object next() {
                return this.i.next();
            }

            @Override
            public boolean hasPrevious() {
                return this.i.hasPrevious();
            }

            public Object previous() {
                return this.i.previous();
            }

            @Override
            public int nextIndex() {
                return this.i.nextIndex();
            }

            @Override
            public int previousIndex() {
                return this.i.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(Object o) {
                throw new UnsupportedOperationException();
            }

            public void add(Object o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String stringValue(Object o) {
        if (o instanceof SimpleValue) {
            return ((SimpleValue)o).stringValue();
        }
        return o.toString();
    }

    public String toString() {
        int size = this.underlying.size();
        if (size == 0) {
            return "";
        }
        String first = this.stringValue(this.underlying.get(0));
        if (size == 1) {
            return first;
        }
        StringBuffer result = new StringBuffer(first);
        for (int i = 1; i < size; ++i) {
            result.append(' ');
            result.append(this.stringValue(this.underlying.get(i)));
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XmlSimpleList)) {
            return false;
        }
        XmlSimpleList xmlSimpleList = (XmlSimpleList)o;
        List underlying2 = xmlSimpleList.underlying;
        int size = this.underlying.size();
        if (size != underlying2.size()) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            Object item = this.underlying.get(i);
            Object item2 = underlying2.get(i);
            if (!(item == null ? item2 != null : !item.equals(item2))) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int size = this.underlying.size();
        int hash = 0;
        for (int i = 0; i < size; ++i) {
            Object item = this.underlying.get(i);
            hash *= 19;
            hash += item.hashCode();
        }
        return hash;
    }
}

