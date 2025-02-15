/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSStream;
import org.apache.poi.poifs.property.PropertyTable;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;

public final class POIFSDump {
    private static final int MAX_RECORD_LENGTH = 100000;

    private POIFSDump() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Must specify at least one file to dump");
            System.exit(1);
        }
        boolean dumpProps = false;
        boolean dumpMini = false;
        for (String filename : args) {
            if (filename.equalsIgnoreCase("-dumprops") || filename.equalsIgnoreCase("-dump-props") || filename.equalsIgnoreCase("-dump-properties")) {
                dumpProps = true;
                continue;
            }
            if (filename.equalsIgnoreCase("-dumpmini") || filename.equalsIgnoreCase("-dump-mini") || filename.equalsIgnoreCase("-dump-ministream") || filename.equalsIgnoreCase("-dump-mini-stream")) {
                dumpMini = true;
                continue;
            }
            System.out.println("Dumping " + filename);
            try (FileInputStream is = new FileInputStream(filename);
                 POIFSFileSystem fs = new POIFSFileSystem(is);){
                DirectoryNode root = fs.getRoot();
                String filenameWithoutPath = new File(filename).getName();
                File dumpDir = new File(filenameWithoutPath + "_dump");
                File file = new File(dumpDir, root.getName());
                if (!file.exists() && !file.mkdirs()) {
                    throw new IOException("Could not create directory " + file);
                }
                POIFSDump.dump(root, file);
                if (dumpProps) {
                    HeaderBlock header = fs.getHeaderBlock();
                    POIFSDump.dump(fs, header.getPropertyStart(), "properties", file);
                }
                if (!dumpMini) continue;
                PropertyTable props = fs.getPropertyTable();
                int startBlock = props.getRoot().getStartBlock();
                if (startBlock == -2) {
                    System.err.println("No Mini Stream in file");
                    continue;
                }
                POIFSDump.dump(fs, startBlock, "mini-stream", file);
            }
        }
    }

    public static void dump(DirectoryEntry root, File parent) throws IOException {
        Iterator<Entry> it = root.getEntries();
        while (it.hasNext()) {
            Entry entry = it.next();
            if (entry instanceof DocumentNode) {
                DocumentNode node = (DocumentNode)entry;
                DocumentInputStream is = new DocumentInputStream(node);
                byte[] bytes = IOUtils.toByteArray(is);
                is.close();
                FileOutputStream out = new FileOutputStream(new File(parent, node.getName().trim()));
                Throwable throwable = null;
                try {
                    ((OutputStream)out).write(bytes);
                    continue;
                } catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                } finally {
                    if (out == null) continue;
                    if (throwable != null) {
                        try {
                            ((OutputStream)out).close();
                        } catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        continue;
                    }
                    ((OutputStream)out).close();
                    continue;
                }
            }
            if (entry instanceof DirectoryEntry) {
                DirectoryEntry dir = (DirectoryEntry)entry;
                File file = new File(parent, entry.getName());
                if (!file.exists() && !file.mkdirs()) {
                    throw new IOException("Could not create directory " + file);
                }
                POIFSDump.dump(dir, file);
                continue;
            }
            System.err.println("Skipping unsupported POIFS entry: " + entry);
        }
    }

    public static void dump(POIFSFileSystem fs, int startBlock, String name, File parent) throws IOException {
        File file = new File(parent, name);
        try (FileOutputStream out = new FileOutputStream(file);){
            POIFSStream stream = new POIFSStream(fs, startBlock);
            byte[] b = IOUtils.safelyAllocate(fs.getBigBlockSize(), 100000);
            for (ByteBuffer bb : stream) {
                int len = bb.remaining();
                bb.get(b);
                out.write(b, 0, len);
            }
        }
    }
}

