/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager;
import org.apache.xmlbeans.impl.tool.CommandLine;

public class SchemaResourceManager
extends BaseSchemaResourceManager {
    private File _directory;

    public static void printUsage() {
        System.out.println("Maintains \"xsdownload.xml\", an index of locally downloaded .xsd files");
        System.out.println("usage: sdownload [-dir directory] [-refresh] [-recurse] [-sync] [url/file...]");
        System.out.println("");
        System.out.println("URLs that are specified are downloaded if they aren't already cached.");
        System.out.println("In addition:");
        System.out.println("  -dir specifies the directory for the xsdownload.xml file (default .).");
        System.out.println("  -sync synchronizes the index to any local .xsd files in the tree.");
        System.out.println("  -recurse recursively downloads imported and included .xsd files.");
        System.out.println("  -refresh redownloads all indexed .xsd files.");
        System.out.println("If no files or URLs are specified, all indexed files are relevant.");
    }

    public static void main(String[] args) throws IOException {
        File[] files;
        String[] filenames;
        SchemaResourceManager mgr;
        if (args.length == 0) {
            SchemaResourceManager.printUsage();
            System.exit(0);
            return;
        }
        HashSet<String> flags = new HashSet<String>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("sync");
        flags.add("refresh");
        flags.add("recurse");
        HashSet<String> opts = new HashSet<String>();
        opts.add("dir");
        CommandLine cl = new CommandLine(args, flags, opts);
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null) {
            SchemaResourceManager.printUsage();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (int i = 0; i < badopts.length; ++i) {
                System.out.println("Unrecognized option: " + badopts[i]);
            }
            SchemaResourceManager.printUsage();
            System.exit(0);
            return;
        }
        if (cl.getOpt("license") != null) {
            CommandLine.printLicense();
            System.exit(0);
            return;
        }
        if (cl.getOpt("version") != null) {
            CommandLine.printVersion();
            System.exit(0);
            return;
        }
        args = cl.args();
        boolean sync = cl.getOpt("sync") != null;
        boolean refresh = cl.getOpt("refresh") != null;
        boolean imports = cl.getOpt("recurse") != null;
        String dir = cl.getOpt("dir");
        if (dir == null) {
            dir = ".";
        }
        File directory = new File(dir);
        try {
            mgr = new SchemaResourceManager(directory);
        } catch (IllegalStateException e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
            }
            System.exit(1);
            return;
        }
        ArrayList<String> uriList = new ArrayList<String>();
        List fileList = new ArrayList<File>();
        for (int i = 0; i < args.length; ++i) {
            if (SchemaResourceManager.looksLikeURL(args[i])) {
                uriList.add(args[i]);
                continue;
            }
            fileList.add(new File(directory, args[i]));
        }
        Iterator i = fileList.iterator();
        while (i.hasNext()) {
            File file = (File)i.next();
            if (SchemaResourceManager.isInDirectory(file, directory)) continue;
            System.err.println("File not within directory: " + file);
            i.remove();
        }
        fileList = SchemaResourceManager.collectXSDFiles(fileList.toArray(new File[0]));
        String[] uris = uriList.toArray(new String[0]);
        if (uris.length + (filenames = SchemaResourceManager.relativeFilenames(files = fileList.toArray(new File[0]), directory)).length > 0) {
            mgr.process(uris, filenames, sync, refresh, imports);
        } else {
            mgr.processAll(sync, refresh, imports);
        }
        mgr.writeCache();
        System.exit(0);
    }

    private static boolean looksLikeURL(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("ftp:") || str.startsWith("file:");
    }

    private static String relativeFilename(File file, File directory) {
        if (file == null || file.equals(directory)) {
            return ".";
        }
        return SchemaResourceManager.relativeFilename(file.getParentFile(), directory) + "/" + file.getName();
    }

    private static String[] relativeFilenames(File[] files, File directory) {
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            result[i] = SchemaResourceManager.relativeFilename(files[i], directory);
        }
        return result;
    }

    private static boolean isInDirectory(File file, File dir) {
        if (file == null) {
            return false;
        }
        if (file.equals(dir)) {
            return true;
        }
        return SchemaResourceManager.isInDirectory(file.getParentFile(), dir);
    }

    public SchemaResourceManager(File directory) {
        this._directory = directory;
        this.init();
    }

    @Override
    protected void warning(String msg) {
        System.out.println(msg);
    }

    @Override
    protected boolean fileExists(String filename) {
        return new File(this._directory, filename).exists();
    }

    @Override
    protected InputStream inputStreamForFile(String filename) throws IOException {
        return new FileInputStream(new File(this._directory, filename));
    }

    @Override
    protected void writeInputStreamToFile(InputStream input, String filename) throws IOException {
        File targetFile = new File(this._directory, filename);
        File parent = targetFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        FileOutputStream output = new FileOutputStream(targetFile);
        IOUtil.copyCompletely(input, output);
    }

    @Override
    protected void deleteFile(String filename) {
        new File(this._directory, filename).delete();
    }

    @Override
    protected String[] getAllXSDFilenames() {
        File[] allFiles = SchemaResourceManager.collectXSDFiles(new File[]{this._directory}).toArray(new File[0]);
        return SchemaResourceManager.relativeFilenames(allFiles, this._directory);
    }

    private static List collectXSDFiles(File[] dirs) {
        ArrayList<File> files = new ArrayList<File>();
        for (int i = 0; i < dirs.length; ++i) {
            File f = dirs[i];
            if (!f.isDirectory()) {
                files.add(f);
                continue;
            }
            files.addAll(SchemaResourceManager.collectXSDFiles(f.listFiles(new FileFilter(){

                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.isFile() && file.getName().endsWith(".xsd");
                }
            })));
        }
        return files;
    }
}

