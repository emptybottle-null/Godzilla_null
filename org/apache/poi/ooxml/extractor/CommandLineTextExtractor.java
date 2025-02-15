/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.extractor;

import java.io.File;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.extractor.ExtractorFactory;

public class CommandLineTextExtractor {
    public static final String DIVIDER = "=======================";

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("   CommandLineTextExtractor <filename> [filename] [filename]");
            System.exit(1);
        }
        for (String arg : args) {
            System.out.println(DIVIDER);
            File f = new File(arg);
            System.out.println(f);
            try (Object extractor = ExtractorFactory.createExtractor(f);){
                POITextExtractor metadataExtractor = ((POITextExtractor)extractor).getMetadataTextExtractor();
                System.out.println("   =======================");
                String metaData = metadataExtractor.getText();
                System.out.println(metaData);
                System.out.println("   =======================");
                String text = ((POITextExtractor)extractor).getText();
                System.out.println(text);
                System.out.println(DIVIDER);
                System.out.println("Had " + metaData.length() + " characters of metadata and " + text.length() + " characters of text");
            }
        }
    }
}

