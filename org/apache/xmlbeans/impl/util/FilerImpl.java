/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.util.Diff;

public class FilerImpl
implements Filer {
    private File classdir;
    private File srcdir;
    private Repackager repackager;
    private boolean verbose;
    private List sourceFiles;
    private boolean incrSrcGen;
    private Set seenTypes;
    private static final Charset CHARSET;

    public FilerImpl(File classdir, File srcdir, Repackager repackager, boolean verbose, boolean incrSrcGen) {
        this.classdir = classdir;
        this.srcdir = srcdir;
        this.repackager = repackager;
        this.verbose = verbose;
        this.sourceFiles = this.sourceFiles != null ? this.sourceFiles : new ArrayList();
        this.incrSrcGen = incrSrcGen;
        if (this.incrSrcGen) {
            this.seenTypes = new HashSet();
        }
    }

    @Override
    public OutputStream createBinaryFile(String typename) throws IOException {
        if (this.verbose) {
            System.err.println("created binary: " + typename);
        }
        File source = new File(this.classdir, typename);
        source.getParentFile().mkdirs();
        return new FileOutputStream(source);
    }

    @Override
    public Writer createSourceFile(String typename) throws IOException {
        if (this.incrSrcGen) {
            this.seenTypes.add(typename);
        }
        if (typename.indexOf(36) > 0) {
            typename = typename.substring(0, typename.lastIndexOf(46)) + "." + typename.substring(typename.indexOf(36) + 1);
        }
        String filename = typename.replace('.', File.separatorChar) + ".java";
        File sourcefile = new File(this.srcdir, filename);
        sourcefile.getParentFile().mkdirs();
        if (this.verbose) {
            System.err.println("created source: " + sourcefile.getAbsolutePath());
        }
        this.sourceFiles.add(sourcefile);
        if (this.incrSrcGen && sourcefile.exists()) {
            return new IncrFileWriter(sourcefile, this.repackager);
        }
        return this.repackager == null ? FilerImpl.writerForFile(sourcefile) : new RepackagingWriter(sourcefile, this.repackager);
    }

    public List getSourceFiles() {
        return new ArrayList(this.sourceFiles);
    }

    public Repackager getRepackager() {
        return this.repackager;
    }

    private static final Writer writerForFile(File f) throws IOException {
        if (CHARSET == null) {
            return new FileWriter(f);
        }
        FileOutputStream fileStream = new FileOutputStream(f);
        CharsetEncoder ce = CHARSET.newEncoder();
        ce.onUnmappableCharacter(CodingErrorAction.REPORT);
        return new OutputStreamWriter((OutputStream)fileStream, ce);
    }

    static {
        Charset temp = null;
        try {
            temp = Charset.forName(System.getProperty("file.encoding"));
        } catch (Exception exception) {
            // empty catch block
        }
        CHARSET = temp;
    }

    static class RepackagingWriter
    extends StringWriter {
        private File _file;
        private Repackager _repackager;

        public RepackagingWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            super.close();
            Writer fw = FilerImpl.writerForFile(this._file);
            try {
                fw.write(this._repackager.repackage(this.getBuffer()).toString());
            } finally {
                fw.close();
            }
        }
    }

    static class IncrFileWriter
    extends StringWriter {
        private File _file;
        private Repackager _repackager;

        public IncrFileWriter(File file, Repackager repackager) {
            this._file = file;
            this._repackager = repackager;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            super.close();
            StringBuffer sb = this._repackager != null ? this._repackager.repackage(this.getBuffer()) : this.getBuffer();
            String str = sb.toString();
            ArrayList diffs = new ArrayList();
            StringReader sReader = new StringReader(str);
            FileReader fReader = new FileReader(this._file);
            try {
                Diff.readersAsText(sReader, "<generated>", fReader, this._file.getName(), diffs);
            } finally {
                sReader.close();
                fReader.close();
            }
            if (diffs.size() > 0) {
                Writer fw = FilerImpl.writerForFile(this._file);
                try {
                    fw.write(str);
                } finally {
                    fw.close();
                }
            }
        }
    }
}

