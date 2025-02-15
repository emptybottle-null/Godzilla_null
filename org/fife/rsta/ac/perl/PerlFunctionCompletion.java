/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.perl;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;
import org.fife.rsta.ac.OutputCollector;
import org.fife.rsta.ac.perl.PerlLanguageSupport;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;

public class PerlFunctionCompletion
extends FunctionCompletion {
    public PerlFunctionCompletion(CompletionProvider provider, String name, String returnType) {
        super(provider, name, returnType);
    }

    @Override
    public String getSummary() {
        String summary = null;
        File installLoc = PerlLanguageSupport.getPerlInstallLocation();
        if (installLoc != null && PerlLanguageSupport.getUseSystemPerldoc()) {
            summary = this.getSummaryFromPerldoc(installLoc);
        }
        if (summary == null) {
            summary = super.getSummary();
        }
        return summary;
    }

    private String getSummaryFromPerldoc(File installLoc) {
        Process p;
        File perldoc;
        String fileName = "bin/perldoc";
        if (File.separatorChar == '\\') {
            fileName = fileName + ".bat";
        }
        if (!(perldoc = new File(installLoc, fileName)).isFile()) {
            return null;
        }
        String[] cmd = new String[]{perldoc.getAbsolutePath(), "-f", this.getName()};
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        OutputCollector oc = new OutputCollector(p.getInputStream());
        Thread t = new Thread(oc);
        t.start();
        int rc = 0;
        try {
            rc = p.waitFor();
            t.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        StringBuilder output = null;
        if (rc == 0 && (output = oc.getOutput()) != null && output.length() > 0) {
            output = PerlFunctionCompletion.perldocToHtml(output);
        }
        return output == null ? null : output.toString();
    }

    private static StringBuilder perldocToHtml(CharSequence text) {
        Font font = UIManager.getFont("Label.font");
        StringBuilder sb = font != null ? new StringBuilder("<html><style>pre { font-family: ").append(font.getFamily()).append("; }</style><pre>") : new StringBuilder("<html><pre>");
        sb.append(text);
        return sb;
    }
}

