/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy;
import org.apache.xmlbeans.impl.inst2xsd.SalamiSliceStrategy;
import org.apache.xmlbeans.impl.inst2xsd.VenetianBlindStrategy;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class Inst2Xsd {
    public static void main(String[] args) {
        String enumerations;
        String simpleContent;
        if (args == null || args.length == 0) {
            Inst2Xsd.printHelp();
            System.exit(0);
            return;
        }
        HashSet<String> flags = new HashSet<String>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("verbose");
        flags.add("validate");
        HashSet<String> opts = new HashSet<String>();
        opts.add("design");
        opts.add("simple-content-types");
        opts.add("enumerations");
        opts.add("outDir");
        opts.add("outPrefix");
        CommandLine cl = new CommandLine(args, flags, opts);
        Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
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
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null) {
            Inst2Xsd.printHelp();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (int i = 0; i < badopts.length; ++i) {
                System.out.println("Unrecognized option: " + badopts[i]);
            }
            Inst2Xsd.printHelp();
            System.exit(0);
            return;
        }
        String design = cl.getOpt("design");
        if (design != null) {
            if (design.equals("vb")) {
                inst2XsdOptions.setDesign(3);
            } else if (design.equals("rd")) {
                inst2XsdOptions.setDesign(1);
            } else if (design.equals("ss")) {
                inst2XsdOptions.setDesign(2);
            } else {
                Inst2Xsd.printHelp();
                System.exit(0);
                return;
            }
        }
        if ((simpleContent = cl.getOpt("simple-content-types")) != null) {
            if (simpleContent.equals("smart")) {
                inst2XsdOptions.setSimpleContentTypes(1);
            } else if (simpleContent.equals("string")) {
                inst2XsdOptions.setSimpleContentTypes(2);
            } else {
                Inst2Xsd.printHelp();
                System.exit(0);
                return;
            }
        }
        if ((enumerations = cl.getOpt("enumerations")) != null) {
            if (enumerations.equals("never")) {
                inst2XsdOptions.setUseEnumerations(1);
            } else {
                try {
                    int intVal = Integer.parseInt(enumerations);
                    inst2XsdOptions.setUseEnumerations(intVal);
                } catch (NumberFormatException e) {
                    Inst2Xsd.printHelp();
                    System.exit(0);
                    return;
                }
            }
        }
        File outDir = new File(cl.getOpt("outDir") == null ? "." : cl.getOpt("outDir"));
        String outPrefix = cl.getOpt("outPrefix");
        if (outPrefix == null) {
            outPrefix = "schema";
        }
        inst2XsdOptions.setVerbose(cl.getOpt("verbose") != null);
        boolean validate = cl.getOpt("validate") != null;
        File[] xmlFiles = cl.filesEndingWith(".xml");
        XmlObject[] xmlInstances = new XmlObject[xmlFiles.length];
        if (xmlInstances.length == 0) {
            Inst2Xsd.printHelp();
            System.exit(0);
            return;
        }
        int i = 0;
        try {
            for (i = 0; i < xmlFiles.length; ++i) {
                xmlInstances[i] = XmlObject.Factory.parse(xmlFiles[i]);
            }
        } catch (XmlException e) {
            System.err.println("Invalid xml file: '" + xmlFiles[i].getName() + "'. " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Could not read file: '" + xmlFiles[i].getName() + "'. " + e.getMessage());
            return;
        }
        SchemaDocument[] schemaDocs = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);
        try {
            for (i = 0; i < schemaDocs.length; ++i) {
                SchemaDocument schema = schemaDocs[i];
                if (inst2XsdOptions.isVerbose()) {
                    System.out.println("----------------------\n\n" + schema);
                }
                schema.save(new File(outDir, outPrefix + i + ".xsd"), new XmlOptions().setSavePrettyPrint());
            }
        } catch (IOException e) {
            System.err.println("Could not write file: '" + outDir + File.pathSeparator + outPrefix + i + ".xsd" + "'. " + e.getMessage());
            return;
        }
        if (validate) {
            Inst2Xsd.validateInstances(schemaDocs, xmlInstances);
        }
    }

    private static void printHelp() {
        System.out.println("Generates XMLSchema from instance xml documents.");
        System.out.println("Usage: inst2xsd [opts] [instance.xml]*");
        System.out.println("Options include:");
        System.out.println("    -design [rd|ss|vb] - XMLSchema design type");
        System.out.println("             rd  - Russian Doll Design - local elements and local types");
        System.out.println("             ss  - Salami Slice Design - global elements and local types");
        System.out.println("             vb  - Venetian Blind Design (default) - local elements and global complex types");
        System.out.println("    -simple-content-types [smart|string] - Simple content types detection (leaf text). Smart is the default");
        System.out.println("    -enumerations [never|NUMBER] - Use enumerations. Default value is 10.");
        System.out.println("    -outDir [dir] - Directory for output files. Default is '.'");
        System.out.println("    -outPrefix [file_name_prefix] - Prefix for output file names. Default is 'schema'");
        System.out.println("    -validate - Validates input instances agaist generated schemas.");
        System.out.println("    -verbose - print more informational messages");
        System.out.println("    -license - print license information");
        System.out.println("    -help - help imformation");
    }

    private Inst2Xsd() {
    }

    public static SchemaDocument[] inst2xsd(Reader[] instReaders, Inst2XsdOptions options) throws IOException, XmlException {
        XmlObject[] instances = new XmlObject[instReaders.length];
        for (int i = 0; i < instReaders.length; ++i) {
            instances[i] = XmlObject.Factory.parse(instReaders[i]);
        }
        return Inst2Xsd.inst2xsd(instances, options);
    }

    public static SchemaDocument[] inst2xsd(XmlObject[] instances, Inst2XsdOptions options) {
        RussianDollStrategy strategy;
        if (options == null) {
            options = new Inst2XsdOptions();
        }
        TypeSystemHolder typeSystemHolder = new TypeSystemHolder();
        switch (options.getDesign()) {
            case 1: {
                strategy = new RussianDollStrategy();
                break;
            }
            case 2: {
                strategy = new SalamiSliceStrategy();
                break;
            }
            case 3: {
                strategy = new VenetianBlindStrategy();
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown design.");
            }
        }
        strategy.processDoc(instances, options, typeSystemHolder);
        if (options.isVerbose()) {
            System.out.println("typeSystemHolder.toString(): " + typeSystemHolder);
        }
        SchemaDocument[] sDocs = typeSystemHolder.getSchemaDocuments();
        return sDocs;
    }

    private static boolean validateInstances(SchemaDocument[] sDocs, XmlObject[] instances) {
        SchemaTypeLoader sLoader;
        ArrayList compErrors = new ArrayList();
        XmlOptions schemaOptions = new XmlOptions();
        schemaOptions.setErrorListener(compErrors);
        try {
            sLoader = XmlBeans.loadXsd(sDocs, schemaOptions);
        } catch (Exception e) {
            if (compErrors.isEmpty() || !(e instanceof XmlException)) {
                e.printStackTrace(System.out);
            }
            System.out.println("\n-------------------\n\nInvalid schemas.");
            for (XmlError xe : compErrors) {
                System.out.println(xe.getLine() + ":" + xe.getColumn() + " " + xe.getMessage());
            }
            return false;
        }
        System.out.println("\n-------------------");
        boolean result = true;
        for (int i = 0; i < instances.length; ++i) {
            XmlObject xobj;
            try {
                xobj = sLoader.parse(instances[i].newXMLStreamReader(), null, new XmlOptions().setLoadLineNumbers());
            } catch (XmlException e) {
                System.out.println("Error:\n" + instances[i].documentProperties().getSourceName() + " not loadable: " + e);
                e.printStackTrace(System.out);
                result = false;
                continue;
            }
            ArrayList errors = new ArrayList();
            if (xobj.schemaType() == XmlObject.type) {
                System.out.println(instances[i].documentProperties().getSourceName() + " NOT valid.  ");
                System.out.println("  Document type not found.");
                result = false;
                continue;
            }
            if (xobj.validate(new XmlOptions().setErrorListener(errors))) {
                System.out.println("Instance[" + i + "] valid - " + instances[i].documentProperties().getSourceName());
                continue;
            }
            System.out.println("Instance[" + i + "] NOT valid - " + instances[i].documentProperties().getSourceName());
            for (XmlError xe : errors) {
                System.out.println(xe.getLine() + ":" + xe.getColumn() + " " + xe.getMessage());
            }
            result = false;
        }
        return result;
    }
}

