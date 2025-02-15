/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.macros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import org.apache.poi.poifs.macros.VBAMacroReader;
import org.apache.poi.util.StringUtil;

public class VBAMacroExtractor {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Use:");
            System.err.println("   VBAMacroExtractor <office.doc> [output]");
            System.err.println();
            System.err.println("If an output directory is given, macros are written there");
            System.err.println("Otherwise they are output to the screen");
            System.exit(1);
        }
        File input = new File(args[0]);
        File output = null;
        if (args.length > 1) {
            output = new File(args[1]);
        }
        VBAMacroExtractor extractor = new VBAMacroExtractor();
        extractor.extract(input, output);
    }

    public void extract(File input, File outputDir, String extension) throws IOException {
        Map<String, String> macros;
        if (!input.exists()) {
            throw new FileNotFoundException(input.toString());
        }
        System.err.print("Extracting VBA Macros from " + input + " to ");
        if (outputDir != null) {
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                throw new IOException("Output directory " + outputDir + " could not be created");
            }
            System.err.println(outputDir);
        } else {
            System.err.println("STDOUT");
        }
        VBAMacroReader reader = new VBAMacroReader(input);
        Object object = null;
        try {
            macros = reader.readMacros();
        } catch (Throwable throwable) {
            object = throwable;
            throw throwable;
        } finally {
            if (reader != null) {
                if (object != null) {
                    try {
                        reader.close();
                    } catch (Throwable throwable) {
                        ((Throwable)object).addSuppressed(throwable);
                    }
                } else {
                    reader.close();
                }
            }
        }
        String divider = "---------------------------------------";
        for (Map.Entry entry : macros.entrySet()) {
            String moduleName = (String)entry.getKey();
            String moduleCode = (String)entry.getValue();
            if (outputDir == null) {
                System.out.println("---------------------------------------");
                System.out.println(moduleName);
                System.out.println();
                System.out.println(moduleCode);
                continue;
            }
            File out = new File(outputDir, moduleName + extension);
            try (FileOutputStream fout = new FileOutputStream(out);
                 OutputStreamWriter fwriter = new OutputStreamWriter((OutputStream)fout, StringUtil.UTF8);){
                fwriter.write(moduleCode);
            }
            System.out.println("Extracted " + out);
        }
        if (outputDir == null) {
            System.out.println("---------------------------------------");
        }
    }

    public void extract(File input, File outputDir) throws IOException {
        this.extract(input, outputDir, ".vba");
    }
}

