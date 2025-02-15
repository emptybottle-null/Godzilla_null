/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.ObjectData;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.util.IOUtils;

public interface ObjectShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Shape<S, P>,
PlaceableShape<S, P> {
    public PictureData getPictureData();

    public String getProgId();

    public String getFullName();

    public OutputStream updateObjectData(ObjectMetaData.Application var1, ObjectMetaData var2) throws IOException;

    default public InputStream readObjectData() throws IOException {
        ByteArrayOutputStream bos;
        block40: {
            String progId = this.getProgId();
            if (progId == null) {
                throw new IllegalStateException("Ole object hasn't been initialized or provided in the source xml. use updateObjectData() first or check the corresponding slideXXX.xml");
            }
            ObjectMetaData.Application app = ObjectMetaData.Application.lookup(progId);
            bos = new ByteArrayOutputStream(50000);
            try (InputStream is = FileMagic.prepareToCheckMagic(this.readObjectDataRaw());){
                FileMagic fm = FileMagic.valueOf(is);
                if (fm == FileMagic.OLE2) {
                    try (POIFSFileSystem poifs = new POIFSFileSystem(is);){
                        String[] names = new String[]{app == null ? null : app.getMetaData().getOleEntry(), "Package", "Contents", "CONTENTS", "CONTENTSV30"};
                        DirectoryNode root = poifs.getRoot();
                        String entryName = null;
                        for (String n : names) {
                            if (!root.hasEntry(n)) continue;
                            entryName = n;
                            break;
                        }
                        if (entryName == null) {
                            poifs.writeFilesystem(bos);
                            break block40;
                        }
                        try (DocumentInputStream is2 = poifs.createDocumentInputStream(entryName);){
                            IOUtils.copy((InputStream)is2, bos);
                            break block40;
                        }
                    }
                }
                IOUtils.copy(is, bos);
            }
        }
        return new ByteArrayInputStream(bos.toByteArray());
    }

    default public InputStream readObjectDataRaw() throws IOException {
        return this.getObjectData().getInputStream();
    }

    public ObjectData getObjectData();
}

