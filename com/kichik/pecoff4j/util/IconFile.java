/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.io.DataReader;
import com.kichik.pecoff4j.io.IDataReader;
import com.kichik.pecoff4j.io.IDataWriter;
import com.kichik.pecoff4j.io.ResourceAssembler;
import com.kichik.pecoff4j.io.ResourceParser;
import com.kichik.pecoff4j.resources.IconDirectory;
import com.kichik.pecoff4j.resources.IconImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IconFile {
    private IconDirectory directory;
    private IconImage[] images;

    public static IconFile parse(String filename) throws IOException {
        return IconFile.read(new DataReader(new FileInputStream(filename)));
    }

    public static IconFile parse(File file) throws IOException {
        return IconFile.read(new DataReader(new FileInputStream(file)));
    }

    public static IconFile read(IDataReader dr) throws IOException {
        IconFile ic = new IconFile();
        ic.directory = ResourceParser.readIconDirectory(dr);
        ic.images = new IconImage[ic.directory.getCount()];
        for (int i = 0; i < ic.directory.getCount(); ++i) {
            dr.jumpTo(ic.directory.getEntry(i).getOffset());
            ic.images[i] = ResourceParser.readIconImage(dr, ic.directory.getEntry(i).getBytesInRes());
        }
        return ic;
    }

    public void write(IDataWriter dw) throws IOException {
        int i;
        int offset = this.directory.sizeOf();
        for (i = 0; i < this.images.length; ++i) {
            this.directory.getEntry(i).setOffset(offset);
            offset += this.images[i].sizeOf();
        }
        ResourceAssembler.write(this.directory, dw);
        for (i = 0; i < this.images.length; ++i) {
            ResourceAssembler.write(this.images[i], dw);
        }
    }

    public IconDirectory getDirectory() {
        return this.directory;
    }

    public void setDirectory(IconDirectory directory) {
        this.directory = directory;
    }

    public IconImage[] getImages() {
        return this.images;
    }

    public void setImages(IconImage[] images) {
        this.images = images;
    }
}

