/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedOrderComparator<T>
implements Comparator<T>,
Serializable {
    private static final long serialVersionUID = 82794675842863201L;
    private final Map<T, Integer> map = new HashMap<T, Integer>();
    private int counter = 0;
    private boolean isLocked = false;
    private UnknownObjectBehavior unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;

    public FixedOrderComparator() {
    }

    public FixedOrderComparator(T ... items) {
        if (items == null) {
            throw new NullPointerException("The list of items must not be null");
        }
        for (T item : items) {
            this.add(item);
        }
    }

    public FixedOrderComparator(List<T> items) {
        if (items == null) {
            throw new NullPointerException("The list of items must not be null");
        }
        for (T t : items) {
            this.add(t);
        }
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    protected void checkLocked() {
        if (this.isLocked()) {
            throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
        }
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        return this.unknownObjectBehavior;
    }

    public void setUnknownObjectBehavior(UnknownObjectBehavior unknownObjectBehavior) {
        this.checkLocked();
        if (unknownObjectBehavior == null) {
            throw new NullPointerException("Unknown object behavior must not be null");
        }
        this.unknownObjectBehavior = unknownObjectBehavior;
    }

    public boolean add(T obj) {
        this.checkLocked();
        Integer position = this.map.put(obj, this.counter++);
        return position == null;
    }

    public boolean addAsEqual(T existingObj, T newObj) {
        this.checkLocked();
        Integer position = this.map.get(existingObj);
        if (position == null) {
            throw new IllegalArgumentException(existingObj + " not known to " + this);
        }
        Integer result = this.map.put(newObj, position);
        return result == null;
    }

    @Override
    public int compare(T obj1, T obj2) {
        this.isLocked = true;
        Integer position1 = this.map.get(obj1);
        Integer position2 = this.map.get(obj2);
        if (position1 == null || position2 == null) {
            switch (this.unknownObjectBehavior) {
                case BEFORE: {
                    return position1 == null ? (position2 == null ? 0 : -1) : 1;
                }
                case AFTER: {
                    return position1 == null ? (position2 == null ? 0 : 1) : -1;
                }
                case EXCEPTION: {
                    T unknownObj = position1 == null ? obj1 : obj2;
                    throw new IllegalArgumentException("Attempting to compare unknown object " + unknownObj);
                }
            }
            throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + (Object)((Object)this.unknownObjectBehavior));
        }
        return position1.compareTo(position2);
    }

    public int hashCode() {
        int total = 17;
        total = total * 37 + this.map.hashCode();
        total = total * 37 + (this.unknownObjectBehavior == null ? 0 : this.unknownObjectBehavior.hashCode());
        total = total * 37 + this.counter;
        total = total * 37 + (this.isLocked ? 0 : 1);
        return total;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (null == object) {
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            FixedOrderComparator comp = (FixedOrderComparator)object;
            return (null == this.map ? null == comp.map : this.map.equals(comp.map)) && (null == this.unknownObjectBehavior ? null == comp.unknownObjectBehavior : this.unknownObjectBehavior == comp.unknownObjectBehavior && this.counter == comp.counter && this.isLocked == comp.isLocked && this.unknownObjectBehavior == comp.unknownObjectBehavior);
        }
        return false;
    }

    public static enum UnknownObjectBehavior {
        BEFORE,
        AFTER,
        EXCEPTION;

    }
}

