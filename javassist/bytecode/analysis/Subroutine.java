/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subroutine {
    private List<Integer> callers = new ArrayList<Integer>();
    private Set<Integer> access = new HashSet<Integer>();
    private int start;

    public Subroutine(int start, int caller) {
        this.start = start;
        this.callers.add(caller);
    }

    public void addCaller(int caller) {
        this.callers.add(caller);
    }

    public int start() {
        return this.start;
    }

    public void access(int index) {
        this.access.add(index);
    }

    public boolean isAccessed(int index) {
        return this.access.contains(index);
    }

    public Collection<Integer> accessed() {
        return this.access;
    }

    public Collection<Integer> callers() {
        return this.callers;
    }

    public String toString() {
        return "start = " + this.start + " callers = " + this.callers.toString();
    }
}

