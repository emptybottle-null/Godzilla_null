/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.eventfilesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderRegistry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.property.RootProperty;
import org.apache.poi.util.IOUtils;

public class POIFSReader {
    private final POIFSReaderRegistry registry = new POIFSReaderRegistry();
    private boolean registryClosed = false;
    private boolean notifyEmptyDirectories;

    public void read(InputStream stream) throws IOException {
        try (POIFSFileSystem poifs = new POIFSFileSystem(stream);){
            this.read(poifs);
        }
    }

    public void read(File poifsFile) throws IOException {
        try (POIFSFileSystem poifs = new POIFSFileSystem(poifsFile, true);){
            this.read(poifs);
        }
    }

    public void read(POIFSFileSystem poifs) throws IOException {
        this.registryClosed = true;
        PropertyTable properties = poifs.getPropertyTable();
        RootProperty root = properties.getRoot();
        this.processProperties(poifs, root, new POIFSDocumentPath());
    }

    public void registerListener(POIFSReaderListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        if (this.registryClosed) {
            throw new IllegalStateException();
        }
        this.registry.registerListener(listener);
    }

    public void registerListener(POIFSReaderListener listener, String name) {
        this.registerListener(listener, null, name);
    }

    public void registerListener(POIFSReaderListener listener, POIFSDocumentPath path, String name) {
        if (listener == null || name == null || name.length() == 0) {
            throw new NullPointerException();
        }
        if (this.registryClosed) {
            throw new IllegalStateException();
        }
        this.registry.registerListener(listener, path == null ? new POIFSDocumentPath() : path, name);
    }

    public void setNotifyEmptyDirectories(boolean notifyEmptyDirectories) {
        this.notifyEmptyDirectories = notifyEmptyDirectories;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("at least one argument required: input filename(s)");
            System.exit(1);
        }
        for (String arg : args) {
            POIFSReader reader = new POIFSReader();
            reader.registerListener(POIFSReader::readEntry);
            System.out.println("reading " + arg);
            reader.read(new File(arg));
        }
    }

    private static void readEntry(POIFSReaderEvent event) {
        POIFSDocumentPath path = event.getPath();
        StringBuilder sb = new StringBuilder();
        try (DocumentInputStream istream = event.getStream();){
            sb.setLength(0);
            int pathLength = path.length();
            for (int k = 0; k < pathLength; ++k) {
                sb.append("/").append(path.getComponent(k));
            }
            byte[] data = IOUtils.toByteArray(istream);
            sb.append("/").append(event.getName()).append(": ").append(data.length).append(" bytes read");
            System.out.println(sb);
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    private void processProperties(POIFSFileSystem poifs, DirectoryProperty dir, POIFSDocumentPath path) {
        boolean hasChildren = false;
        for (Property property : dir) {
            hasChildren = true;
            String name = property.getName();
            if (property.isDirectory()) {
                POIFSDocumentPath new_path = new POIFSDocumentPath(path, new String[]{name});
                this.processProperties(poifs, (DirectoryProperty)property, new_path);
                continue;
            }
            POIFSDocument document = null;
            for (POIFSReaderListener rl : this.registry.getListeners(path, name)) {
                if (document == null) {
                    document = new POIFSDocument((DocumentProperty)property, poifs);
                }
                DocumentInputStream dis = new DocumentInputStream(document);
                Throwable throwable = null;
                try {
                    POIFSReaderEvent pe = new POIFSReaderEvent(dis, path, name);
                    rl.processPOIFSReaderEvent(pe);
                } catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                } finally {
                    if (dis == null) continue;
                    if (throwable != null) {
                        try {
                            dis.close();
                        } catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        continue;
                    }
                    dis.close();
                }
            }
        }
        if (hasChildren || !this.notifyEmptyDirectories) {
            return;
        }
        for (POIFSReaderListener rl : this.registry.getListeners(path, ".")) {
            POIFSReaderEvent pe = new POIFSReaderEvent(null, path, null);
            rl.processPOIFSReaderEvent(pe);
        }
    }
}

