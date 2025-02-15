/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.Internal;

@Internal
public final class EntryUtils {
    private EntryUtils() {
    }

    @Internal
    public static void copyNodeRecursively(Entry entry, DirectoryEntry target) throws IOException {
        if (entry.isDirectoryEntry()) {
            DirectoryEntry dirEntry = (DirectoryEntry)entry;
            DirectoryEntry newTarget = target.createDirectory(entry.getName());
            newTarget.setStorageClsid(dirEntry.getStorageClsid());
            Iterator<Entry> entries = dirEntry.getEntries();
            while (entries.hasNext()) {
                EntryUtils.copyNodeRecursively(entries.next(), newTarget);
            }
        } else {
            DocumentEntry dentry = (DocumentEntry)entry;
            DocumentInputStream dstream = new DocumentInputStream(dentry);
            target.createDocument(dentry.getName(), dstream);
            dstream.close();
        }
    }

    public static void copyNodes(DirectoryEntry sourceRoot, DirectoryEntry targetRoot) throws IOException {
        for (Entry entry : sourceRoot) {
            EntryUtils.copyNodeRecursively(entry, targetRoot);
        }
    }

    public static void copyNodes(POIFSFileSystem source, POIFSFileSystem target) throws IOException {
        EntryUtils.copyNodes(source.getRoot(), target.getRoot());
    }

    public static void copyNodes(POIFSFileSystem source, POIFSFileSystem target, List<String> excepts) throws IOException {
        EntryUtils.copyNodes(new FilteringDirectoryNode(source.getRoot(), excepts), new FilteringDirectoryNode(target.getRoot(), excepts));
    }

    public static boolean areDirectoriesIdentical(DirectoryEntry dirA, DirectoryEntry dirB) {
        return new DirectoryDelegate(dirA).equals(new DirectoryDelegate(dirB));
    }

    public static boolean areDocumentsIdentical(DocumentEntry docA, DocumentEntry docB) throws IOException {
        try {
            return new DocumentDelegate(docA).equals(new DocumentDelegate(docB));
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw (IOException)e.getCause();
            }
            throw e;
        }
    }

    private static class DocumentDelegate
    implements POIDelegate {
        final DocumentEntry doc;

        DocumentDelegate(DocumentEntry doc) {
            this.doc = doc;
        }

        public int hashCode() {
            return this.doc.getName().hashCode();
        }

        /*
         * Exception decompiling
         */
        public boolean equals(Object other) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
             *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
             *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
             *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
             *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
             *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
             *     at java.lang.Thread.run(Thread.java:750)
             */
            throw new IllegalStateException("Decompilation failed");
        }

        private static boolean isEqual(DocumentInputStream i1, DocumentInputStream i2) throws IOException {
            byte[] buf1 = new byte[4096];
            byte[] buf2 = new byte[4096];
            try {
                int len;
                while ((len = i1.read(buf1)) > 0) {
                    i2.readFully(buf2, 0, len);
                    for (int i = 0; i < len; ++i) {
                        if (buf1[i] == buf2[i]) continue;
                        return false;
                    }
                }
                return i2.read() < 0;
            } catch (EOFException | RuntimeException ioe) {
                return false;
            }
        }
    }

    private static class DirectoryDelegate
    implements POIDelegate {
        final DirectoryEntry dir;

        DirectoryDelegate(DirectoryEntry dir) {
            this.dir = dir;
        }

        private Map<String, POIDelegate> entries() {
            return StreamSupport.stream(this.dir.spliterator(), false).collect(Collectors.toMap(Entry::getName, DirectoryDelegate::toDelegate));
        }

        private static POIDelegate toDelegate(Entry entry) {
            return entry.isDirectoryEntry() ? new DirectoryDelegate((DirectoryEntry)entry) : new DocumentDelegate((DocumentEntry)entry);
        }

        public int hashCode() {
            return this.dir.getName().hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof DirectoryDelegate)) {
                return false;
            }
            DirectoryDelegate dd = (DirectoryDelegate)other;
            if (this == dd) {
                return true;
            }
            if (!Objects.equals(this.dir.getName(), dd.dir.getName())) {
                return false;
            }
            if (this.dir.getEntryCount() != dd.dir.getEntryCount()) {
                return false;
            }
            return this.entries().equals(dd.entries());
        }
    }

    private static interface POIDelegate {
    }
}

