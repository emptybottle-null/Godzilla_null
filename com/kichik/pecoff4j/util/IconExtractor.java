/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.ResourceDirectory;
import com.kichik.pecoff4j.ResourceEntry;
import com.kichik.pecoff4j.io.DataReader;
import com.kichik.pecoff4j.io.DataWriter;
import com.kichik.pecoff4j.io.PEParser;
import com.kichik.pecoff4j.io.ResourceParser;
import com.kichik.pecoff4j.resources.GroupIconDirectory;
import com.kichik.pecoff4j.resources.GroupIconDirectoryEntry;
import com.kichik.pecoff4j.resources.IconDirectory;
import com.kichik.pecoff4j.resources.IconDirectoryEntry;
import com.kichik.pecoff4j.resources.IconImage;
import com.kichik.pecoff4j.util.IconFile;
import com.kichik.pecoff4j.util.ResourceHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IconExtractor {
    public static void extract(File pecoff, File outputDir) throws IOException {
        PE pe = PEParser.parse(pecoff);
        ResourceDirectory rd = pe.getImageData().getResourceTable();
        if (rd == null) {
            return;
        }
        ResourceEntry[] entries = ResourceHelper.findResources(rd, 14);
        for (int i = 0; i < entries.length; ++i) {
            GroupIconDirectory gid = GroupIconDirectory.read(entries[i].getData());
            IconFile icf = new IconFile();
            IconDirectory icd = new IconDirectory();
            icd.setType(1);
            icd.setReserved(0);
            icf.setDirectory(icd);
            IconImage[] images = new IconImage[gid.getCount()];
            icf.setImages(images);
            for (int j = 0; j < gid.getCount(); ++j) {
                IconImage ii;
                GroupIconDirectoryEntry gide = gid.getEntry(j);
                IconDirectoryEntry ide = new IconDirectoryEntry();
                ide.copyFrom(gide);
                icd.add(ide);
                ResourceEntry[] icos = ResourceHelper.findResources(rd, 3, gide.getId());
                if (icos == null || icos.length != 1) {
                    throw new IOException("Unexpected icons in resource file");
                }
                byte[] d = icos[0].getData();
                ide.setBytesInRes(d.length);
                images[j] = gide.getWidth() == 0 && gide.getHeight() == 0 ? (ii = ResourceParser.readPNG(d)) : (ii = ResourceParser.readIconImage(new DataReader(d), gide.getBytesInRes()));
            }
            File outFile = new File(outputDir, pecoff.getName() + "-icon" + i + ".ico");
            DataWriter dw = new DataWriter(new FileOutputStream(outFile));
            icf.write(dw);
            dw.close();
        }
    }
}

