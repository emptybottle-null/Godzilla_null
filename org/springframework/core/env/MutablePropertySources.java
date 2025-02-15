/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.lang.Nullable;

public class MutablePropertySources
implements PropertySources {
    private final List<PropertySource<?>> propertySourceList = new CopyOnWriteArrayList();

    public MutablePropertySources() {
    }

    public MutablePropertySources(PropertySources propertySources) {
        this();
        for (PropertySource propertySource : propertySources) {
            this.addLast(propertySource);
        }
    }

    @Override
    public Iterator<PropertySource<?>> iterator() {
        return this.propertySourceList.iterator();
    }

    @Override
    public Spliterator<PropertySource<?>> spliterator() {
        return Spliterators.spliterator(this.propertySourceList, 0);
    }

    @Override
    public Stream<PropertySource<?>> stream() {
        return this.propertySourceList.stream();
    }

    @Override
    public boolean contains(String name) {
        for (PropertySource<?> propertySource : this.propertySourceList) {
            if (!propertySource.getName().equals(name)) continue;
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public PropertySource<?> get(String name) {
        for (PropertySource<?> propertySource : this.propertySourceList) {
            if (!propertySource.getName().equals(name)) continue;
            return propertySource;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addFirst(PropertySource<?> propertySource) {
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            this.removeIfPresent(propertySource);
            this.propertySourceList.add(0, propertySource);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addLast(PropertySource<?> propertySource) {
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            this.removeIfPresent(propertySource);
            this.propertySourceList.add(propertySource);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addBefore(String relativePropertySourceName, PropertySource<?> propertySource) {
        this.assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            this.removeIfPresent(propertySource);
            int index = this.assertPresentAndGetIndex(relativePropertySourceName);
            this.addAtIndex(index, propertySource);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addAfter(String relativePropertySourceName, PropertySource<?> propertySource) {
        this.assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            this.removeIfPresent(propertySource);
            int index = this.assertPresentAndGetIndex(relativePropertySourceName);
            this.addAtIndex(index + 1, propertySource);
        }
    }

    public int precedenceOf(PropertySource<?> propertySource) {
        return this.propertySourceList.indexOf(propertySource);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public PropertySource<?> remove(String name) {
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            int index = this.propertySourceList.indexOf(PropertySource.named(name));
            return index != -1 ? this.propertySourceList.remove(index) : null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void replace(String name, PropertySource<?> propertySource) {
        List<PropertySource<?>> list = this.propertySourceList;
        synchronized (list) {
            int index = this.assertPresentAndGetIndex(name);
            this.propertySourceList.set(index, propertySource);
        }
    }

    public int size() {
        return this.propertySourceList.size();
    }

    public String toString() {
        return this.propertySourceList.toString();
    }

    protected void assertLegalRelativeAddition(String relativePropertySourceName, PropertySource<?> propertySource) {
        String newPropertySourceName = propertySource.getName();
        if (relativePropertySourceName.equals(newPropertySourceName)) {
            throw new IllegalArgumentException("PropertySource named '" + newPropertySourceName + "' cannot be added relative to itself");
        }
    }

    protected void removeIfPresent(PropertySource<?> propertySource) {
        this.propertySourceList.remove(propertySource);
    }

    private void addAtIndex(int index, PropertySource<?> propertySource) {
        this.removeIfPresent(propertySource);
        this.propertySourceList.add(index, propertySource);
    }

    private int assertPresentAndGetIndex(String name) {
        int index = this.propertySourceList.indexOf(PropertySource.named(name));
        if (index == -1) {
            throw new IllegalArgumentException("PropertySource named '" + name + "' does not exist");
        }
        return index;
    }
}

