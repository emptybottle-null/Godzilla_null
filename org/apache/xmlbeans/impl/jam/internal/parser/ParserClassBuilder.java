/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamClassPopulator;
import org.apache.xmlbeans.impl.jam.provider.JamServiceContext;
import org.apache.xmlbeans.impl.jam.provider.ResourcePath;

public class ParserClassBuilder
extends JamClassBuilder
implements JamClassPopulator {
    private static final boolean VERBOSE = false;
    private ResourcePath mSourcePath;
    private boolean mVerbose = false;
    private PrintWriter mOut = new PrintWriter(System.out);

    private ParserClassBuilder() {
    }

    public ParserClassBuilder(JamServiceContext jsp) {
        this.mSourcePath = jsp.getInputSourcepath();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MClass build(String pkg, String name) {
        if (pkg == null) {
            throw new IllegalArgumentException("null pkg");
        }
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        String filespec = pkg.replace('.', File.separatorChar) + File.separatorChar + name + ".java";
        if (name.indexOf(".") != -1) {
            throw new IllegalArgumentException("inner classes are NYI at the moment");
        }
        InputStream in = this.mSourcePath.findInPath(filespec);
        if (in == null) {
            if (this.mVerbose) {
                this.mOut.println("[ParserClassBuilder] could not find " + filespec);
            }
            return null;
        }
        if (this.mVerbose) {
            this.mOut.println("[ParserClassBuilder] loading class " + pkg + "  " + name);
            this.mOut.println("[ParserClassBuilder] from file " + filespec);
        }
        InputStreamReader rin = new InputStreamReader(in);
        try {
            ((Reader)rin).close();
        } catch (IOException ohwell) {
            ohwell.printStackTrace();
        }
        return null;
    }

    @Override
    public void populate(MClass m) {
        throw new IllegalStateException("NYI");
    }

    private static MClass[] parse(Reader in, JamClassLoader loader) throws Exception {
        if (in == null) {
            throw new IllegalArgumentException("null in");
        }
        if (loader == null) {
            throw new IllegalArgumentException("null loader");
        }
        throw new IllegalStateException("temporarily NI");
    }

    public static void main(String[] files) {
        new MainTool().process(files);
    }

    static class MainTool {
        private List mFailures = new ArrayList();
        private int mCount = 0;
        private PrintWriter mOut = new PrintWriter(System.out);
        private long mStartTime = System.currentTimeMillis();

        MainTool() {
        }

        public void process(String[] files) {
            try {
                for (int i = 0; i < files.length; ++i) {
                    File input = new File(files[i]);
                    this.parse(new ParserClassBuilder(), input);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mOut.println("\n\n\n");
            int fails = this.mFailures.size();
            if (fails != 0) {
                this.mOut.println("The following files failed to parse:");
                for (int i = 0; i < fails; ++i) {
                    this.mOut.println(((File)this.mFailures.get(i)).getAbsolutePath());
                }
            }
            this.mOut.println((this.mCount - fails) * 100 / this.mCount + "% (" + (this.mCount - fails) + "/" + this.mCount + ") " + "of input java files successfully parsed.");
            this.mOut.println("Total time: " + (System.currentTimeMillis() - this.mStartTime) / 1000L + " seconds.");
            this.mOut.flush();
            System.out.flush();
            System.err.flush();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private void parse(ParserClassBuilder parser, File input) throws Exception {
            System.gc();
            if (input.isDirectory()) {
                File[] files = input.listFiles();
                for (int i = 0; i < files.length; ++i) {
                    this.parse(parser, files[i]);
                }
                return;
            } else {
                if (!input.getName().endsWith(".java")) {
                    return;
                }
                ++this.mCount;
                MClass[] results = null;
                try {
                    results = ParserClassBuilder.parse(new FileReader(input), null);
                    if (results != null) return;
                    this.mOut.println("[error, parser result is null]");
                    this.addFailure(input);
                    return;
                } catch (Throwable e) {
                    e.printStackTrace(this.mOut);
                    this.addFailure(input);
                }
            }
        }

        private void addFailure(File file) {
            this.mFailures.add(file);
        }
    }
}

