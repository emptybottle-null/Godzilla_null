/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class ServiceLoaderIterator<E>
implements Iterator<E> {
    private E nextServiceLoader;
    private final Class<E> service;
    private final Iterator<E> serviceLoaderIterator;

    public ServiceLoaderIterator(Class<E> service) {
        this(service, ClassLoader.getSystemClassLoader());
    }

    public ServiceLoaderIterator(Class<E> service, ClassLoader classLoader) {
        this.service = service;
        ServiceLoader<E> serviceLoader = ServiceLoader.load(service, classLoader);
        this.serviceLoaderIterator = serviceLoader.iterator();
        this.nextServiceLoader = null;
    }

    private boolean getNextServiceLoader() {
        while (this.nextServiceLoader == null) {
            try {
                if (!this.serviceLoaderIterator.hasNext()) {
                    return false;
                }
                this.nextServiceLoader = this.serviceLoaderIterator.next();
            } catch (ServiceConfigurationError e) {
                if (e.getCause() instanceof SecurityException) continue;
                throw e;
            }
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        return this.getNextServiceLoader();
    }

    @Override
    public E next() {
        if (!this.getNextServiceLoader()) {
            throw new NoSuchElementException("No more elements for service " + this.service.getName());
        }
        E tempNext = this.nextServiceLoader;
        this.nextServiceLoader = null;
        return tempNext;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("service=" + this.service.getName());
    }
}

