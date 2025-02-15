/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;

public interface DirectoryEntry
extends Entry,
Iterable<Entry> {
    public Iterator<Entry> getEntries();

    public Set<String> getEntryNames();

    public boolean isEmpty();

    public int getEntryCount();

    public boolean hasEntry(String var1);

    public Entry getEntry(String var1) throws FileNotFoundException;

    public DocumentEntry createDocument(String var1, InputStream var2) throws IOException;

    public DocumentEntry createDocument(String var1, int var2, POIFSWriterListener var3) throws IOException;

    public DirectoryEntry createDirectory(String var1) throws IOException;

    public ClassID getStorageClsid();

    public void setStorageClsid(ClassID var1);
}

