/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;

public class FilteringDirectoryNode
implements DirectoryEntry {
    private Set<String> excludes;
    private Map<String, List<String>> childExcludes;
    private DirectoryEntry directory;

    public FilteringDirectoryNode(DirectoryEntry directory, Collection<String> excludes) {
        if (directory == null) {
            throw new IllegalArgumentException("directory cannot be null");
        }
        this.directory = directory;
        this.excludes = new HashSet<String>();
        this.childExcludes = new HashMap<String, List<String>>();
        for (String excl : excludes) {
            int splitAt = excl.indexOf(47);
            if (splitAt == -1) {
                this.excludes.add(excl);
                continue;
            }
            String child = excl.substring(0, splitAt);
            String childExcl = excl.substring(splitAt + 1);
            if (!this.childExcludes.containsKey(child)) {
                this.childExcludes.put(child, new ArrayList());
            }
            this.childExcludes.get(child).add(childExcl);
        }
    }

    @Override
    public DirectoryEntry createDirectory(String name) throws IOException {
        return this.directory.createDirectory(name);
    }

    @Override
    public DocumentEntry createDocument(String name, InputStream stream) throws IOException {
        return this.directory.createDocument(name, stream);
    }

    @Override
    public DocumentEntry createDocument(String name, int size, POIFSWriterListener writer) throws IOException {
        return this.directory.createDocument(name, size, writer);
    }

    @Override
    public Iterator<Entry> getEntries() {
        return new FilteringIterator();
    }

    @Override
    public Iterator<Entry> iterator() {
        return this.getEntries();
    }

    @Override
    public int getEntryCount() {
        int size = this.directory.getEntryCount();
        for (String excl : this.excludes) {
            if (!this.directory.hasEntry(excl)) continue;
            --size;
        }
        return size;
    }

    @Override
    public Set<String> getEntryNames() {
        HashSet<String> names = new HashSet<String>();
        for (String name : this.directory.getEntryNames()) {
            if (this.excludes.contains(name)) continue;
            names.add(name);
        }
        return names;
    }

    @Override
    public boolean isEmpty() {
        return this.getEntryCount() == 0;
    }

    @Override
    public boolean hasEntry(String name) {
        if (this.excludes.contains(name)) {
            return false;
        }
        return this.directory.hasEntry(name);
    }

    @Override
    public Entry getEntry(String name) throws FileNotFoundException {
        if (this.excludes.contains(name)) {
            throw new FileNotFoundException(name);
        }
        Entry entry = this.directory.getEntry(name);
        return this.wrapEntry(entry);
    }

    private Entry wrapEntry(Entry entry) {
        String name = entry.getName();
        if (this.childExcludes.containsKey(name) && entry instanceof DirectoryEntry) {
            return new FilteringDirectoryNode((DirectoryEntry)entry, (Collection<String>)this.childExcludes.get(name));
        }
        return entry;
    }

    @Override
    public ClassID getStorageClsid() {
        return this.directory.getStorageClsid();
    }

    @Override
    public void setStorageClsid(ClassID clsidStorage) {
        this.directory.setStorageClsid(clsidStorage);
    }

    @Override
    public boolean delete() {
        return this.directory.delete();
    }

    @Override
    public boolean renameTo(String newName) {
        return this.directory.renameTo(newName);
    }

    @Override
    public String getName() {
        return this.directory.getName();
    }

    @Override
    public DirectoryEntry getParent() {
        return this.directory.getParent();
    }

    @Override
    public boolean isDirectoryEntry() {
        return true;
    }

    @Override
    public boolean isDocumentEntry() {
        return false;
    }

    private class FilteringIterator
    implements Iterator<Entry> {
        private Iterator<Entry> parent;
        private Entry next;

        private FilteringIterator() {
            this.parent = FilteringDirectoryNode.this.directory.getEntries();
            this.locateNext();
        }

        private void locateNext() {
            this.next = null;
            while (this.parent.hasNext() && this.next == null) {
                Entry e = this.parent.next();
                if (FilteringDirectoryNode.this.excludes.contains(e.getName())) continue;
                this.next = FilteringDirectoryNode.this.wrapEntry(e);
            }
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public Entry next() {
            Entry e = this.next;
            this.locateNext();
            return e;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}

