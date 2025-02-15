/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.tool.CodeGenUtil;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

public class FactorImports {
    public static void printUsage() {
        System.out.println("Refactors a directory of XSD files to remove name conflicts.");
        System.out.println("Usage: sfactor [-import common.xsd] [-out outputdir] inputdir");
        System.out.println("    -import common.xsd - The XSD file to contain redundant ");
        System.out.println("                         definitions for importing.");
        System.out.println("    -out outputdir - The directory into which to place XSD ");
        System.out.println("                     files resulting from refactoring, ");
        System.out.println("                     plus a commonly imported common.xsd.");
        System.out.println("    inputdir - The directory containing the XSD files with");
        System.out.println("               redundant definitions.");
        System.out.println("    -license - Print license information.");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        String out;
        HashSet<String> flags = new HashSet<String>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        CommandLine cl = new CommandLine(args, flags, Arrays.asList("import", "out"));
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null || args.length < 1) {
            FactorImports.printUsage();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (int i = 0; i < badopts.length; ++i) {
                System.out.println("Unrecognized option: " + badopts[i]);
            }
            FactorImports.printUsage();
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
        if (args.length != 1) {
            System.exit(0);
            return;
        }
        String commonName = cl.getOpt("import");
        if (commonName == null) {
            commonName = "common.xsd";
        }
        if ((out = cl.getOpt("out")) == null) {
            System.out.println("Using output directory 'out'");
            out = "out";
        }
        File outdir = new File(out);
        File basedir = new File(args[0]);
        File[] files = cl.getFiles();
        HashMap<SchemaDocument, File> schemaDocs = new HashMap<SchemaDocument, File>();
        HashSet elementNames = new HashSet();
        HashSet attributeNames = new HashSet();
        HashSet typeNames = new HashSet();
        HashSet modelGroupNames = new HashSet();
        HashSet attrGroupNames = new HashSet();
        HashSet dupeElementNames = new HashSet();
        HashSet dupeAttributeNames = new HashSet();
        HashSet dupeTypeNames = new HashSet();
        HashSet dupeModelGroupNames = new HashSet();
        HashSet dupeAttrGroupNames = new HashSet();
        HashSet dupeNamespaces = new HashSet();
        for (int i = 0; i < files.length; ++i) {
            try {
                String targetNamespace;
                SchemaDocument doc = SchemaDocument.Factory.parse(files[i]);
                schemaDocs.put(doc, files[i]);
                if (doc.getSchema().sizeOfImportArray() > 0 || doc.getSchema().sizeOfIncludeArray() > 0) {
                    System.out.println("warning: " + files[i] + " contains imports or includes that are being ignored.");
                }
                if ((targetNamespace = doc.getSchema().getTargetNamespace()) == null) {
                    targetNamespace = "";
                }
                TopLevelComplexType[] ct = doc.getSchema().getComplexTypeArray();
                for (int j = 0; j < ct.length; ++j) {
                    FactorImports.noteName(ct[j].getName(), targetNamespace, typeNames, dupeTypeNames, dupeNamespaces);
                }
                TopLevelSimpleType[] st = doc.getSchema().getSimpleTypeArray();
                for (int j = 0; j < st.length; ++j) {
                    FactorImports.noteName(st[j].getName(), targetNamespace, typeNames, dupeTypeNames, dupeNamespaces);
                }
                TopLevelElement[] el = doc.getSchema().getElementArray();
                for (int j = 0; j < el.length; ++j) {
                    FactorImports.noteName(el[j].getName(), targetNamespace, elementNames, dupeElementNames, dupeNamespaces);
                }
                TopLevelAttribute[] at = doc.getSchema().getAttributeArray();
                for (int j = 0; j < at.length; ++j) {
                    FactorImports.noteName(at[j].getName(), targetNamespace, attributeNames, dupeAttributeNames, dupeNamespaces);
                }
                NamedGroup[] gr = doc.getSchema().getGroupArray();
                for (int j = 0; j < gr.length; ++j) {
                    FactorImports.noteName(gr[j].getName(), targetNamespace, modelGroupNames, dupeModelGroupNames, dupeNamespaces);
                }
                NamedAttributeGroup[] ag = doc.getSchema().getAttributeGroupArray();
                for (int j = 0; j < ag.length; ++j) {
                    FactorImports.noteName(ag[j].getName(), targetNamespace, attrGroupNames, dupeAttrGroupNames, dupeNamespaces);
                }
                continue;
            } catch (XmlException e) {
                System.out.println("warning: " + files[i] + " is not a schema file - " + e.getError().toString());
                continue;
            } catch (IOException e) {
                System.err.println("Unable to load " + files[i] + " - " + e.getMessage());
                System.exit(1);
                return;
            }
        }
        if (schemaDocs.size() == 0) {
            System.out.println("No schema files found.");
            System.exit(0);
            return;
        }
        if (dupeTypeNames.size() + dupeElementNames.size() + dupeAttributeNames.size() + dupeModelGroupNames.size() + dupeAttrGroupNames.size() == 0) {
            System.out.println("No duplicate names found.");
            System.exit(0);
            return;
        }
        HashMap<String, SchemaDocument> commonDocs = new HashMap<String, SchemaDocument>();
        HashMap<SchemaDocument, File> commonFiles = new HashMap<SchemaDocument, File>();
        int count = dupeNamespaces.size() == 1 ? 0 : 1;
        for (String namespace : dupeNamespaces) {
            SchemaDocument commonDoc = SchemaDocument.Factory.parse("<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'/>");
            if (namespace.length() > 0) {
                commonDoc.getSchema().setTargetNamespace(namespace);
            }
            commonDoc.getSchema().setElementFormDefault(FormChoice.QUALIFIED);
            commonDocs.put(namespace, commonDoc);
            commonFiles.put(commonDoc, FactorImports.commonFileFor(commonName, namespace, count++, outdir));
        }
        for (SchemaDocument doc : schemaDocs.keySet()) {
            String targetNamespace = doc.getSchema().getTargetNamespace();
            if (targetNamespace == null) {
                targetNamespace = "";
            }
            SchemaDocument commonDoc = (SchemaDocument)commonDocs.get(targetNamespace);
            boolean needImport = false;
            TopLevelComplexType[] ct = doc.getSchema().getComplexTypeArray();
            for (int j = ct.length - 1; j >= 0; --j) {
                if (!FactorImports.isDuplicate(ct[j].getName(), targetNamespace, dupeTypeNames)) continue;
                if (FactorImports.isFirstDuplicate(ct[j].getName(), targetNamespace, typeNames, dupeTypeNames)) {
                    commonDoc.getSchema().addNewComplexType().set(ct[j]);
                }
                needImport = true;
                doc.getSchema().removeComplexType(j);
            }
            TopLevelSimpleType[] st = doc.getSchema().getSimpleTypeArray();
            for (int j = 0; j < st.length; ++j) {
                if (!FactorImports.isDuplicate(st[j].getName(), targetNamespace, dupeTypeNames)) continue;
                if (FactorImports.isFirstDuplicate(st[j].getName(), targetNamespace, typeNames, dupeTypeNames)) {
                    commonDoc.getSchema().addNewSimpleType().set(st[j]);
                }
                needImport = true;
                doc.getSchema().removeSimpleType(j);
            }
            TopLevelElement[] el = doc.getSchema().getElementArray();
            for (int j = 0; j < el.length; ++j) {
                if (!FactorImports.isDuplicate(el[j].getName(), targetNamespace, dupeElementNames)) continue;
                if (FactorImports.isFirstDuplicate(el[j].getName(), targetNamespace, elementNames, dupeElementNames)) {
                    commonDoc.getSchema().addNewElement().set(el[j]);
                }
                needImport = true;
                doc.getSchema().removeElement(j);
            }
            TopLevelAttribute[] at = doc.getSchema().getAttributeArray();
            for (int j = 0; j < at.length; ++j) {
                if (!FactorImports.isDuplicate(at[j].getName(), targetNamespace, dupeAttributeNames)) continue;
                if (FactorImports.isFirstDuplicate(at[j].getName(), targetNamespace, attributeNames, dupeAttributeNames)) {
                    commonDoc.getSchema().addNewElement().set(at[j]);
                }
                needImport = true;
                doc.getSchema().removeElement(j);
            }
            NamedGroup[] gr = doc.getSchema().getGroupArray();
            for (int j = 0; j < gr.length; ++j) {
                if (!FactorImports.isDuplicate(gr[j].getName(), targetNamespace, dupeModelGroupNames)) continue;
                if (FactorImports.isFirstDuplicate(gr[j].getName(), targetNamespace, modelGroupNames, dupeModelGroupNames)) {
                    commonDoc.getSchema().addNewElement().set(gr[j]);
                }
                needImport = true;
                doc.getSchema().removeElement(j);
            }
            NamedAttributeGroup[] ag = doc.getSchema().getAttributeGroupArray();
            for (int j = 0; j < ag.length; ++j) {
                if (!FactorImports.isDuplicate(ag[j].getName(), targetNamespace, dupeAttrGroupNames)) continue;
                if (FactorImports.isFirstDuplicate(ag[j].getName(), targetNamespace, attrGroupNames, dupeAttrGroupNames)) {
                    commonDoc.getSchema().addNewElement().set(ag[j]);
                }
                needImport = true;
                doc.getSchema().removeElement(j);
            }
            if (!needImport) continue;
            IncludeDocument.Include newInclude = doc.getSchema().addNewInclude();
            File inputFile = (File)schemaDocs.get(doc);
            File outputFile = FactorImports.outputFileFor(inputFile, basedir, outdir);
            File commonFile = (File)commonFiles.get(commonDoc);
            if (targetNamespace == null) continue;
            newInclude.setSchemaLocation(FactorImports.relativeURIFor(outputFile, commonFile));
        }
        if (!outdir.isDirectory() && !outdir.mkdirs()) {
            System.err.println("Unable to makedir " + outdir);
            System.exit(1);
            return;
        }
        for (SchemaDocument doc : schemaDocs.keySet()) {
            File inputFile = (File)schemaDocs.get(doc);
            File outputFile = FactorImports.outputFileFor(inputFile, basedir, outdir);
            if (outputFile == null) {
                System.out.println("Cannot copy " + inputFile);
                continue;
            }
            doc.save(outputFile, new XmlOptions().setSavePrettyPrint().setSaveAggresiveNamespaces());
        }
        for (SchemaDocument doc : commonFiles.keySet()) {
            File outputFile = (File)commonFiles.get(doc);
            doc.save(outputFile, new XmlOptions().setSavePrettyPrint().setSaveAggresiveNamespaces());
        }
    }

    private static File outputFileFor(File file, File baseDir, File outdir) {
        URI abs;
        URI base = baseDir.getAbsoluteFile().toURI();
        URI rel = base.relativize(abs = file.getAbsoluteFile().toURI());
        if (rel.isAbsolute()) {
            System.out.println("Cannot relativize " + file);
            return null;
        }
        URI outbase = outdir.toURI();
        URI out = CodeGenUtil.resolve(outbase, rel);
        return new File(out);
    }

    private static URI commonAncestor(URI first, URI second) {
        int i;
        String firstStr = first.toString();
        String secondStr = second.toString();
        int len = firstStr.length();
        if (secondStr.length() < len) {
            len = secondStr.length();
        }
        for (i = 0; i < len && firstStr.charAt(i) == secondStr.charAt(i); ++i) {
        }
        if (--i >= 0) {
            i = firstStr.lastIndexOf(47, i);
        }
        if (i < 0) {
            return null;
        }
        try {
            return new URI(firstStr.substring(0, i));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static String relativeURIFor(File source, File target) {
        URI abs;
        URI base = source.getAbsoluteFile().toURI();
        URI commonBase = FactorImports.commonAncestor(base, abs = target.getAbsoluteFile().toURI());
        if (commonBase == null) {
            return abs.toString();
        }
        URI baserel = commonBase.relativize(base);
        URI targetrel = commonBase.relativize(abs);
        if (baserel.isAbsolute() || targetrel.isAbsolute()) {
            return abs.toString();
        }
        String prefix = "";
        String sourceRel = baserel.toString();
        for (int i = 0; i < sourceRel.length() && (i = sourceRel.indexOf(47, i)) >= 0; ++i) {
            prefix = prefix + "../";
        }
        return prefix + targetrel.toString();
    }

    private static File commonFileFor(String commonName, String namespace, int i, File outdir) {
        String name = commonName;
        if (i > 0) {
            int index = commonName.lastIndexOf(46);
            if (index < 0) {
                index = commonName.length();
            }
            name = commonName.substring(0, index) + i + commonName.substring(index);
        }
        return new File(outdir, name);
    }

    private static void noteName(String name, String targetNamespace, Set seen, Set dupes, Set dupeNamespaces) {
        if (name == null) {
            return;
        }
        QName qName = new QName(targetNamespace, name);
        if (seen.contains(qName)) {
            dupes.add(qName);
            dupeNamespaces.add(targetNamespace);
        } else {
            seen.add(qName);
        }
    }

    private static boolean isFirstDuplicate(String name, String targetNamespace, Set notseen, Set dupes) {
        if (name == null) {
            return false;
        }
        QName qName = new QName(targetNamespace, name);
        if (dupes.contains(qName) && notseen.contains(qName)) {
            notseen.remove(qName);
            return true;
        }
        return false;
    }

    private static boolean isDuplicate(String name, String targetNamespace, Set dupes) {
        if (name == null) {
            return false;
        }
        QName qName = new QName(targetNamespace, name);
        return dupes.contains(qName);
    }
}

