/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.perl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import org.fife.rsta.ac.IOUtil;
import org.fife.rsta.ac.OutputCollector;
import org.fife.rsta.ac.perl.PerlLanguageSupport;
import org.fife.rsta.ac.perl.PerlOutputCollector;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.AbstractParser;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;

public class PerlParser
extends AbstractParser {
    private DefaultParseResult result = new DefaultParseResult(this);
    private boolean taintModeEnabled;
    private boolean warningsEnabled;
    private String perl5LibOverride;
    private String[] perlEnvironment;
    private static final int MAX_COMPILE_MILLIS = 10000;

    private void createPerlEnvironment() {
        this.perlEnvironment = null;
        String perl5Lib = this.getPerl5LibOverride();
        if (perl5Lib != null) {
            String[] toAdd = new String[]{"PERL5LIB", perl5Lib};
            this.perlEnvironment = IOUtil.getEnvironmentSafely(toAdd);
        }
    }

    public String getPerl5LibOverride() {
        return this.perl5LibOverride;
    }

    public boolean getWarningsEnabled() {
        return this.warningsEnabled;
    }

    public boolean isTaintModeEnabled() {
        return this.taintModeEnabled;
    }

    @Override
    public ParseResult parse(RSyntaxDocument doc, String style) {
        this.result.clearNotices();
        int lineCount = doc.getDefaultRootElement().getElementCount();
        this.result.setParsedLines(0, lineCount - 1);
        long start = System.currentTimeMillis();
        try {
            File dir = PerlLanguageSupport.getPerlInstallLocation();
            if (dir == null) {
                return this.result;
            }
            String exe = File.separatorChar == '\\' ? "bin/perl.exe" : "bin/perl";
            File perl = new File(dir, exe);
            if (!perl.isFile()) {
                return this.result;
            }
            File tempFile = File.createTempFile("perlParser", ".tmp");
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
            try {
                new DefaultEditorKit().write(out, (Document)doc, 0, doc.getLength());
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                throw new IOException(ble.getMessage());
            }
            out.close();
            String opts = "-c";
            if (this.getWarningsEnabled()) {
                opts = opts + "w";
            }
            if (this.isTaintModeEnabled()) {
                opts = opts + "t";
            }
            String[] envp = this.perlEnvironment;
            String[] cmd = new String[]{perl.getAbsolutePath(), opts, tempFile.getAbsolutePath()};
            Process p = Runtime.getRuntime().exec(cmd, envp);
            Element root = doc.getDefaultRootElement();
            OutputCollector stdout = new OutputCollector(p.getInputStream(), false);
            Thread t = new Thread(stdout);
            t.start();
            PerlOutputCollector stderr = new PerlOutputCollector(p.getErrorStream(), this, this.result, root);
            Thread t2 = new Thread(stderr);
            t2.start();
            try {
                t2.join(10000L);
                t.join(10000L);
                if (t.isAlive()) {
                    t.interrupt();
                } else {
                    p.waitFor();
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            long time = System.currentTimeMillis() - start;
            this.result.setParseTime(time);
        } catch (IOException ioe) {
            this.result.setError(ioe);
            ioe.printStackTrace();
        }
        return this.result;
    }

    public void setPerl5LibOverride(String override) {
        this.perl5LibOverride = override;
        this.createPerlEnvironment();
    }

    public void setTaintModeEnabled(boolean enabled) {
        this.taintModeEnabled = enabled;
    }

    public void setWarningsEnabled(boolean enabled) {
        this.warningsEnabled = enabled;
    }
}

