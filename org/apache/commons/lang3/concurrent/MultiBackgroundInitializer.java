/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;

public class MultiBackgroundInitializer
extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    private final Map<String, BackgroundInitializer<?>> childInitializers = new HashMap();

    public MultiBackgroundInitializer() {
    }

    public MultiBackgroundInitializer(ExecutorService exec) {
        super(exec);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addInitializer(String name, BackgroundInitializer<?> backgroundInitializer) {
        Validate.notNull(name, "name", new Object[0]);
        Validate.notNull(backgroundInitializer, "backgroundInitializer", new Object[0]);
        MultiBackgroundInitializer multiBackgroundInitializer = this;
        synchronized (multiBackgroundInitializer) {
            if (this.isStarted()) {
                throw new IllegalStateException("addInitializer() must not be called after start()!");
            }
            this.childInitializers.put(name, backgroundInitializer);
        }
    }

    @Override
    protected int getTaskCount() {
        int result = 1;
        for (BackgroundInitializer<?> bi : this.childInitializers.values()) {
            result += bi.getTaskCount();
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected MultiBackgroundInitializerResults initialize() throws Exception {
        HashMap inits;
        MultiBackgroundInitializer multiBackgroundInitializer = this;
        synchronized (multiBackgroundInitializer) {
            inits = new HashMap(this.childInitializers);
        }
        ExecutorService exec = this.getActiveExecutor();
        for (BackgroundInitializer bi : inits.values()) {
            if (bi.getExternalExecutor() == null) {
                bi.setExternalExecutor(exec);
            }
            bi.start();
        }
        HashMap results = new HashMap();
        HashMap excepts = new HashMap();
        for (Map.Entry e : inits.entrySet()) {
            try {
                results.put(e.getKey(), ((BackgroundInitializer)e.getValue()).get());
            } catch (ConcurrentException cex) {
                excepts.put(e.getKey(), cex);
            }
        }
        return new MultiBackgroundInitializerResults(inits, results, excepts);
    }

    public static class MultiBackgroundInitializerResults {
        private final Map<String, BackgroundInitializer<?>> initializers;
        private final Map<String, Object> resultObjects;
        private final Map<String, ConcurrentException> exceptions;

        private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> inits, Map<String, Object> results, Map<String, ConcurrentException> excepts) {
            this.initializers = inits;
            this.resultObjects = results;
            this.exceptions = excepts;
        }

        public BackgroundInitializer<?> getInitializer(String name) {
            return this.checkName(name);
        }

        public Object getResultObject(String name) {
            this.checkName(name);
            return this.resultObjects.get(name);
        }

        public boolean isException(String name) {
            this.checkName(name);
            return this.exceptions.containsKey(name);
        }

        public ConcurrentException getException(String name) {
            this.checkName(name);
            return this.exceptions.get(name);
        }

        public Set<String> initializerNames() {
            return Collections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }

        private BackgroundInitializer<?> checkName(String name) {
            BackgroundInitializer<?> init = this.initializers.get(name);
            if (init == null) {
                throw new NoSuchElementException("No child initializer with name " + name);
            }
            return init;
        }
    }
}

