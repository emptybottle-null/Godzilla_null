/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public interface ObjectData {
    public InputStream getInputStream() throws IOException;

    public OutputStream getOutputStream() throws IOException;

    default public byte[] getBytes() throws IOException {
        try (InputStream is = this.getInputStream();){
            byte[] byArray = IOUtils.toByteArray(is);
            return byArray;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    default public boolean hasDirectoryEntry() {
        try (InputStream is = FileMagic.prepareToCheckMagic(this.getInputStream());){
            FileMagic fm = FileMagic.valueOf(is);
            boolean bl = fm == FileMagic.OLE2;
            return bl;
        } catch (IOException e) {
            POILogger LOG = POILogFactory.getLogger(ObjectData.class);
            LOG.log(5, "Can't determine filemagic of ole stream", e);
            return false;
        }
    }

    default public DirectoryEntry getDirectory() throws IOException {
        try (InputStream is = this.getInputStream();){
            DirectoryNode directoryNode = new POIFSFileSystem(is).getRoot();
            return directoryNode;
        }
    }

    public String getOLE2ClassName();

    public String getFileName();
}

