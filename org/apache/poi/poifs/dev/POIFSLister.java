/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIFSLister {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Must specify at least one file to view");
            System.exit(1);
        }
        boolean withSizes = false;
        boolean newPOIFS = true;
        for (String arg : args) {
            if (arg.equalsIgnoreCase("-size") || arg.equalsIgnoreCase("-sizes")) {
                withSizes = true;
                continue;
            }
            if (arg.equalsIgnoreCase("-old") || arg.equalsIgnoreCase("-old-poifs")) {
                newPOIFS = false;
                continue;
            }
            if (newPOIFS) {
                POIFSLister.viewFile(arg, withSizes);
                continue;
            }
            POIFSLister.viewFileOld(arg, withSizes);
        }
    }

    public static void viewFile(String filename, boolean withSizes) throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem(new File(filename));){
            POIFSLister.displayDirectory(fs.getRoot(), "", withSizes);
        }
    }

    public static void viewFileOld(String filename, boolean withSizes) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename);
             POIFSFileSystem fs = new POIFSFileSystem(fis);){
            POIFSLister.displayDirectory(fs.getRoot(), "", withSizes);
        }
    }

    public static void displayDirectory(DirectoryNode dir, String indent, boolean withSizes) {
        System.out.println(indent + dir.getName() + " -");
        String newIndent = indent + "  ";
        boolean hadChildren = false;
        Iterator<Entry> it = dir.getEntries();
        while (it.hasNext()) {
            hadChildren = true;
            Entry entry = it.next();
            if (entry instanceof DirectoryNode) {
                POIFSLister.displayDirectory((DirectoryNode)entry, newIndent, withSizes);
                continue;
            }
            DocumentNode doc = (DocumentNode)entry;
            String name = doc.getName();
            String size = "";
            if (name.charAt(0) < '\n') {
                String altname = "(0x0" + name.charAt(0) + ")" + name.substring(1);
                name = name.substring(1) + " <" + altname + ">";
            }
            if (withSizes) {
                size = " [" + doc.getSize() + " / 0x" + Integer.toHexString(doc.getSize()) + "]";
            }
            System.out.println(newIndent + name + size);
        }
        if (!hadChildren) {
            System.out.println(newIndent + "(no children)");
        }
    }
}

