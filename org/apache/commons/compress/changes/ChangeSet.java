/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.changes.Change;

public final class ChangeSet {
    private final Set<Change> changes = new LinkedHashSet<Change>();

    public void delete(String fileName) {
        this.addDeletion(new Change(fileName, 1));
    }

    public void deleteDir(String dirName) {
        this.addDeletion(new Change(dirName, 4));
    }

    public void add(ArchiveEntry pEntry, InputStream pInput) {
        this.add(pEntry, pInput, true);
    }

    public void add(ArchiveEntry pEntry, InputStream pInput, boolean replace) {
        this.addAddition(new Change(pEntry, pInput, replace));
    }

    private void addAddition(Change pChange) {
        if (2 != pChange.type() || pChange.getInput() == null) {
            return;
        }
        if (!this.changes.isEmpty()) {
            Iterator<Change> it = this.changes.iterator();
            while (it.hasNext()) {
                ArchiveEntry entry;
                Change change = it.next();
                if (change.type() != 2 || change.getEntry() == null || !(entry = change.getEntry()).equals(pChange.getEntry())) continue;
                if (pChange.isReplaceMode()) {
                    it.remove();
                    this.changes.add(pChange);
                    return;
                }
                return;
            }
        }
        this.changes.add(pChange);
    }

    private void addDeletion(Change pChange) {
        if (1 != pChange.type() && 4 != pChange.type() || pChange.targetFile() == null) {
            return;
        }
        String source = pChange.targetFile();
        if (source != null && !this.changes.isEmpty()) {
            Iterator<Change> it = this.changes.iterator();
            while (it.hasNext()) {
                String target;
                Change change = it.next();
                if (change.type() != 2 || change.getEntry() == null || (target = change.getEntry().getName()) == null || (1 != pChange.type() || !source.equals(target)) && (4 != pChange.type() || !target.matches(source + "/.*"))) continue;
                it.remove();
            }
        }
        this.changes.add(pChange);
    }

    Set<Change> getChanges() {
        return new LinkedHashSet<Change>(this.changes);
    }
}

