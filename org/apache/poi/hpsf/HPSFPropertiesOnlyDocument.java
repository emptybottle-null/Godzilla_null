/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.poi.POIDocument;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HPSFPropertiesOnlyDocument
extends POIDocument {
    public HPSFPropertiesOnlyDocument(POIFSFileSystem fs) {
        super(fs);
    }

    @Override
    public void write() throws IOException {
        POIFSFileSystem fs = this.getDirectory().getFileSystem();
        this.validateInPlaceWritePossible();
        this.writeProperties(fs, null);
        fs.writeFilesystem();
    }

    @Override
    public void write(File newFile) throws IOException {
        try (POIFSFileSystem fs = POIFSFileSystem.create(newFile);){
            this.write(fs);
            fs.writeFilesystem();
        }
    }

    @Override
    public void write(OutputStream out) throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem();){
            this.write(fs);
            fs.writeFilesystem(out);
        }
    }

    private void write(POIFSFileSystem fs) throws IOException {
        ArrayList<String> excepts = new ArrayList<String>(2);
        this.writeProperties(fs, excepts);
        FilteringDirectoryNode src = new FilteringDirectoryNode(this.getDirectory(), excepts);
        FilteringDirectoryNode dest = new FilteringDirectoryNode(fs.getRoot(), excepts);
        EntryUtils.copyNodes(src, dest);
    }
}

