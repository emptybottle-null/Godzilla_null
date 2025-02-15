/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.changes;

import java.util.ArrayList;
import java.util.List;

public class ChangeSetResults {
    private final List<String> addedFromChangeSet = new ArrayList<String>();
    private final List<String> addedFromStream = new ArrayList<String>();
    private final List<String> deleted = new ArrayList<String>();

    void deleted(String fileName) {
        this.deleted.add(fileName);
    }

    void addedFromStream(String fileName) {
        this.addedFromStream.add(fileName);
    }

    void addedFromChangeSet(String fileName) {
        this.addedFromChangeSet.add(fileName);
    }

    public List<String> getAddedFromChangeSet() {
        return this.addedFromChangeSet;
    }

    public List<String> getAddedFromStream() {
        return this.addedFromStream;
    }

    public List<String> getDeleted() {
        return this.deleted;
    }

    boolean hasBeenAdded(String fileName) {
        return this.addedFromChangeSet.contains(fileName) || this.addedFromStream.contains(fileName);
    }
}

