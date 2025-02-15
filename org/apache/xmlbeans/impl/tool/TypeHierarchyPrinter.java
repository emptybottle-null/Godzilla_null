/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class TypeHierarchyPrinter {
    public static void printUsage() {
        System.out.println("Prints the inheritance hierarchy of types defined in a schema.\n");
        System.out.println("Usage: xsdtree [-noanon] [-nopvr] [-noupa] [-partial] [-license] schemafile.xsd*");
        System.out.println("    -noanon - Don't include anonymous types in the tree.");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -partial - Print only part of the hierarchy.");
        System.out.println("    -license - prints license information");
        System.out.println("    schemafile.xsd - File containing the schema for which to print a tree.");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        SchemaTypeSystem typeSystem;
        HashSet<String> flags = new HashSet<String>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("noanon");
        flags.add("noupr");
        flags.add("noupa");
        flags.add("partial");
        CommandLine cl = new CommandLine(args, flags, Collections.EMPTY_SET);
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null) {
            TypeHierarchyPrinter.printUsage();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (int i = 0; i < badopts.length; ++i) {
                System.out.println("Unrecognized option: " + badopts[i]);
            }
            TypeHierarchyPrinter.printUsage();
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
        if (cl.args().length == 0) {
            TypeHierarchyPrinter.printUsage();
            return;
        }
        boolean noanon = cl.getOpt("noanon") != null;
        boolean nopvr = cl.getOpt("nopvr") != null;
        boolean noupa = cl.getOpt("noupa") != null;
        boolean partial = cl.getOpt("partial") != null;
        File[] schemaFiles = cl.filesEndingWith(".xsd");
        File[] jarFiles = cl.filesEndingWith(".jar");
        ArrayList<SchemaDocument> sdocs = new ArrayList<SchemaDocument>();
        for (int i = 0; i < schemaFiles.length; ++i) {
            try {
                sdocs.add(SchemaDocument.Factory.parse(schemaFiles[i], new XmlOptions().setLoadLineNumbers()));
                continue;
            } catch (Exception e) {
                System.err.println(schemaFiles[i] + " not loadable: " + e);
            }
        }
        XmlObject[] schemas = sdocs.toArray(new XmlObject[0]);
        SchemaTypeLoader linkTo = null;
        ArrayList compErrors = new ArrayList();
        XmlOptions schemaOptions = new XmlOptions();
        schemaOptions.setErrorListener(compErrors);
        schemaOptions.setCompileDownloadUrls();
        if (nopvr) {
            schemaOptions.setCompileNoPvrRule();
        }
        if (noupa) {
            schemaOptions.setCompileNoUpaRule();
        }
        if (partial) {
            schemaOptions.put("COMPILE_PARTIAL_TYPESYSTEM");
        }
        if (jarFiles != null && jarFiles.length > 0) {
            linkTo = XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(jarFiles));
        }
        try {
            typeSystem = XmlBeans.compileXsd(schemas, linkTo, schemaOptions);
        } catch (XmlException e) {
            System.out.println("Schema invalid:" + (partial ? " couldn't recover from errors" : ""));
            if (compErrors.isEmpty()) {
                System.out.println(e.getMessage());
            } else {
                Iterator i = compErrors.iterator();
                while (i.hasNext()) {
                    System.out.println(i.next());
                }
            }
            return;
        }
        if (partial && !compErrors.isEmpty()) {
            System.out.println("Schema invalid: partial schema type system recovered");
            Iterator i = compErrors.iterator();
            while (i.hasNext()) {
                System.out.println(i.next());
            }
        }
        HashMap<String, String> prefixes = new HashMap<String, String>();
        prefixes.put("http://www.w3.org/XML/1998/namespace", "xml");
        prefixes.put("http://www.w3.org/2001/XMLSchema", "xs");
        System.out.println("xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"");
        HashMap<SchemaType, ArrayList<SchemaType>> childTypes = new HashMap<SchemaType, ArrayList<SchemaType>>();
        ArrayList<SchemaType> allSeenTypes = new ArrayList<SchemaType>();
        allSeenTypes.addAll(Arrays.asList(typeSystem.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(typeSystem.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(typeSystem.globalTypes()));
        for (int i = 0; i < allSeenTypes.size(); ++i) {
            SchemaType sType = (SchemaType)allSeenTypes.get(i);
            if (!noanon) {
                allSeenTypes.addAll(Arrays.asList(sType.getAnonymousTypes()));
            }
            if (sType.isDocumentType() || sType.isAttributeType() || sType == XmlObject.type) continue;
            TypeHierarchyPrinter.noteNamespace(prefixes, sType);
            ArrayList<SchemaType> children = (ArrayList<SchemaType>)childTypes.get(sType.getBaseType());
            if (children == null) {
                children = new ArrayList<SchemaType>();
                childTypes.put(sType.getBaseType(), children);
                if (sType.getBaseType().isBuiltinType()) {
                    allSeenTypes.add(sType.getBaseType());
                }
            }
            children.add(sType);
        }
        ArrayList<SchemaType> typesToPrint = new ArrayList<SchemaType>();
        typesToPrint.add(XmlObject.type);
        StringBuffer spaces = new StringBuffer();
        while (!typesToPrint.isEmpty()) {
            SchemaType sType = (SchemaType)typesToPrint.remove(typesToPrint.size() - 1);
            if (sType == null) {
                spaces.setLength(Math.max(0, spaces.length() - 2));
                continue;
            }
            System.out.println(spaces + "+-" + QNameHelper.readable(sType, prefixes) + TypeHierarchyPrinter.notes(sType));
            Collection children = (Collection)childTypes.get(sType);
            if (children == null || children.size() <= 0) continue;
            spaces.append(typesToPrint.size() == 0 || typesToPrint.get(typesToPrint.size() - 1) == null ? "  " : "| ");
            typesToPrint.add(null);
            typesToPrint.addAll(children);
        }
    }

    private static String notes(SchemaType sType) {
        if (sType.isBuiltinType()) {
            return " (builtin)";
        }
        if (sType.isSimpleType()) {
            switch (sType.getSimpleVariety()) {
                case 3: {
                    return " (list)";
                }
                case 2: {
                    return " (union)";
                }
            }
            if (sType.getEnumerationValues() != null) {
                return " (enumeration)";
            }
            return "";
        }
        switch (sType.getContentType()) {
            case 4: {
                return " (mixed)";
            }
            case 2: {
                return " (complex)";
            }
        }
        return "";
    }

    private static void noteNamespace(Map prefixes, SchemaType sType) {
        String base;
        String namespace = QNameHelper.namespace(sType);
        if (namespace.equals("") || prefixes.containsKey(namespace)) {
            return;
        }
        String result = base = QNameHelper.suggestPrefix(namespace);
        int n = 0;
        while (prefixes.containsValue(result)) {
            result = base + n;
            ++n;
        }
        prefixes.put(namespace, result);
        System.out.println("xmlns:" + result + "=\"" + namespace + "\"");
    }
}

