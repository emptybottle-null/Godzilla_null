/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.EntryNode;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;

public class DirectoryNode
extends EntryNode
implements DirectoryEntry,
POIFSViewable,
Iterable<Entry> {
    private final Map<String, Entry> _byname = new HashMap<String, Entry>();
    private final ArrayList<Entry> _entries = new ArrayList();
    private final POIFSFileSystem _filesystem;
    private final POIFSDocumentPath _path;

    DirectoryNode(DirectoryProperty property, POIFSFileSystem filesystem, DirectoryNode parent) {
        super(property, parent);
        this._filesystem = filesystem;
        this._path = parent == null ? new POIFSDocumentPath() : new POIFSDocumentPath(parent._path, new String[]{property.getName()});
        Iterator<Property> iter = property.getChildren();
        while (iter.hasNext()) {
            EntryNode childNode;
            Property child = iter.next();
            if (child.isDirectory()) {
                DirectoryProperty childDir = (DirectoryProperty)child;
                childNode = new DirectoryNode(childDir, this._filesystem, this);
            } else {
                childNode = new DocumentNode((DocumentProperty)child, this);
            }
            this._entries.add(childNode);
            this._byname.put(childNode.getName(), childNode);
        }
    }

    public POIFSDocumentPath getPath() {
        return this._path;
    }

    public POIFSFileSystem getFileSystem() {
        return this._filesystem;
    }

    public DocumentInputStream createDocumentInputStream(String documentName) throws IOException {
        return this.createDocumentInputStream(this.getEntry(documentName));
    }

    public DocumentInputStream createDocumentInputStream(Entry document) throws IOException {
        if (!document.isDocumentEntry()) {
            throw new IOException("Entry '" + document.getName() + "' is not a DocumentEntry");
        }
        DocumentEntry entry = (DocumentEntry)document;
        return new DocumentInputStream(entry);
    }

    DocumentEntry createDocument(POIFSDocument document) throws IOException {
        DocumentProperty property = document.getDocumentProperty();
        DocumentNode rval = new DocumentNode(property, this);
        ((DirectoryProperty)this.getProperty()).addChild(property);
        this._filesystem.addDocument(document);
        this._entries.add(rval);
        this._byname.put(property.getName(), rval);
        return rval;
    }

    boolean changeName(String oldName, String newName) {
        boolean rval = false;
        EntryNode child = (EntryNode)this._byname.get(oldName);
        if (child != null && (rval = ((DirectoryProperty)this.getProperty()).changeName(child.getProperty(), newName))) {
            this._byname.remove(oldName);
            this._byname.put(child.getProperty().getName(), child);
        }
        return rval;
    }

    boolean deleteEntry(EntryNode entry) {
        boolean rval = ((DirectoryProperty)this.getProperty()).deleteChild(entry.getProperty());
        if (rval) {
            this._entries.remove(entry);
            this._byname.remove(entry.getName());
            try {
                this._filesystem.remove(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return rval;
    }

    @Override
    public Iterator<Entry> getEntries() {
        return this._entries.iterator();
    }

    @Override
    public Set<String> getEntryNames() {
        return this._byname.keySet();
    }

    @Override
    public boolean isEmpty() {
        return this._entries.isEmpty();
    }

    @Override
    public int getEntryCount() {
        return this._entries.size();
    }

    @Override
    public boolean hasEntry(String name) {
        return name != null && this._byname.containsKey(name);
    }

    @Override
    public Entry getEntry(String name) throws FileNotFoundException {
        Entry rval = null;
        if (name != null) {
            rval = this._byname.get(name);
        }
        if (rval == null) {
            if (this._byname.containsKey("Workbook")) {
                throw new IllegalArgumentException("The document is really a XLS file");
            }
            if (this._byname.containsKey("PowerPoint Document")) {
                throw new IllegalArgumentException("The document is really a PPT file");
            }
            if (this._byname.containsKey("VisioDocument")) {
                throw new IllegalArgumentException("The document is really a VSD file");
            }
            throw new FileNotFoundException("no such entry: \"" + name + "\", had: " + this._byname.keySet());
        }
        return rval;
    }

    @Override
    public DocumentEntry createDocument(String name, InputStream stream) throws IOException {
        return this.createDocument(new POIFSDocument(name, this._filesystem, stream));
    }

    @Override
    public DocumentEntry createDocument(String name, int size, POIFSWriterListener writer) throws IOException {
        return this.createDocument(new POIFSDocument(name, size, this._filesystem, writer));
    }

    @Override
    public DirectoryEntry createDirectory(String name) throws IOException {
        DirectoryProperty property = new DirectoryProperty(name);
        DirectoryNode rval = new DirectoryNode(property, this._filesystem, this);
        this._filesystem.addDirectory(property);
        ((DirectoryProperty)this.getProperty()).addChild(property);
        this._entries.add(rval);
        this._byname.put(name, rval);
        return rval;
    }

    public DocumentEntry createOrUpdateDocument(String name, InputStream stream) throws IOException {
        if (!this.hasEntry(name)) {
            return this.createDocument(name, stream);
        }
        DocumentNode existing = (DocumentNode)this.getEntry(name);
        POIFSDocument nDoc = new POIFSDocument(existing);
        nDoc.replaceContents(stream);
        return existing;
    }

    @Override
    public ClassID getStorageClsid() {
        return this.getProperty().getStorageClsid();
    }

    @Override
    public void setStorageClsid(ClassID clsidStorage) {
        this.getProperty().setStorageClsid(clsidStorage);
    }

    @Override
    public boolean isDirectoryEntry() {
        return true;
    }

    @Override
    protected boolean isDeleteOK() {
        return this.isEmpty();
    }

    @Override
    public Object[] getViewableArray() {
        return new Object[0];
    }

    @Override
    public Iterator<Object> getViewableIterator() {
        ArrayList<Object> components = new ArrayList<Object>();
        components.add(this.getProperty());
        components.addAll(this._entries);
        return components.iterator();
    }

    @Override
    public boolean preferArray() {
        return false;
    }

    @Override
    public String getShortDescription() {
        return this.getName();
    }

    @Override
    public Iterator<Entry> iterator() {
        return this.getEntries();
    }
}

