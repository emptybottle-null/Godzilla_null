/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.util.FilerImpl;

public class SchemaCodeGenerator {
    private static Set deleteFileQueue = new HashSet();
    private static int triesRemaining = 0;

    public static void saveTypeSystem(SchemaTypeSystem system, File classesDir, File sourceFile, Repackager repackager, XmlOptions options) throws IOException {
        FilerImpl filer = new FilerImpl(classesDir, null, repackager, false, false);
        system.save(filer);
    }

    static void deleteObsoleteFiles(File rootDir, File srcDir, Set seenFiles) {
        if (!rootDir.isDirectory() || !srcDir.isDirectory()) {
            throw new IllegalArgumentException();
        }
        String absolutePath = srcDir.getAbsolutePath();
        if (absolutePath.length() <= 5) {
            return;
        }
        if (absolutePath.startsWith("/home/") && (absolutePath.indexOf("/", 6) >= absolutePath.length() - 1 || absolutePath.indexOf("/", 6) < 0)) {
            return;
        }
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                SchemaCodeGenerator.deleteObsoleteFiles(rootDir, files[i], seenFiles);
                continue;
            }
            if (seenFiles.contains(files[i])) continue;
            SchemaCodeGenerator.deleteXmlBeansFile(files[i]);
            SchemaCodeGenerator.deleteDirRecursively(rootDir, files[i].getParentFile());
        }
    }

    private static void deleteXmlBeansFile(File file) {
        if (file.getName().endsWith(".java")) {
            file.delete();
        }
    }

    private static void deleteDirRecursively(File root, File dir) {
        String[] list = dir.list();
        while (list != null && list.length == 0 && !dir.equals(root)) {
            dir.delete();
            dir = dir.getParentFile();
            list = dir.list();
        }
    }

    protected static File createTempDir() throws IOException {
        try {
            File tmpDirFile = new File(SystemProperties.getProperty("java.io.tmpdir"));
            tmpDirFile.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File tmpFile = File.createTempFile("xbean", null);
        String path = tmpFile.getAbsolutePath();
        if (!path.endsWith(".tmp")) {
            throw new IOException("Error: createTempFile did not create a file ending with .tmp");
        }
        path = path.substring(0, path.length() - 4);
        File tmpSrcDir = null;
        for (int count = 0; count < 100; ++count) {
            String name = path + ".d" + (count == 0 ? "" : Integer.toString(count++));
            tmpSrcDir = new File(name);
            if (tmpSrcDir.exists()) continue;
            boolean created = tmpSrcDir.mkdirs();
            assert (created) : "Could not create " + tmpSrcDir.getAbsolutePath();
            break;
        }
        tmpFile.deleteOnExit();
        return tmpSrcDir;
    }

    protected static void tryHardToDelete(File dir) {
        SchemaCodeGenerator.tryToDelete(dir);
        if (dir.exists()) {
            SchemaCodeGenerator.tryToDeleteLater(dir);
        }
    }

    private static void tryToDelete(File dir) {
        if (dir.exists()) {
            String[] list;
            if (dir.isDirectory() && (list = dir.list()) != null) {
                for (int i = 0; i < list.length; ++i) {
                    SchemaCodeGenerator.tryToDelete(new File(dir, list[i]));
                }
            }
            if (!dir.delete()) {
                return;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean tryNowThatItsLater() {
        ArrayList files;
        Set set = deleteFileQueue;
        synchronized (set) {
            files = new ArrayList(deleteFileQueue);
            deleteFileQueue.clear();
        }
        ArrayList<File> retry = new ArrayList<File>();
        for (File file : files) {
            SchemaCodeGenerator.tryToDelete(file);
            if (!file.exists()) continue;
            retry.add(file);
        }
        Set set2 = deleteFileQueue;
        synchronized (set2) {
            if (triesRemaining > 0) {
                --triesRemaining;
            }
            if (triesRemaining <= 0 || retry.size() == 0) {
                triesRemaining = 0;
            } else {
                deleteFileQueue.addAll(retry);
            }
            return triesRemaining <= 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void giveUp() {
        Set set = deleteFileQueue;
        synchronized (set) {
            deleteFileQueue.clear();
            triesRemaining = 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void tryToDeleteLater(File dir) {
        Set set = deleteFileQueue;
        synchronized (set) {
            deleteFileQueue.add(dir);
            if (triesRemaining == 0) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            while (true) {
                                if (SchemaCodeGenerator.tryNowThatItsLater()) {
                                    return;
                                }
                                Thread.sleep(3000L);
                            }
                        } catch (InterruptedException e) {
                            SchemaCodeGenerator.giveUp();
                            return;
                        }
                    }
                };
            }
            if (triesRemaining < 10) {
                triesRemaining = 10;
            }
        }
    }
}

