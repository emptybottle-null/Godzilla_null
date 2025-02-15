/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.XmlErrorWatcher;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.SchemaDependencies;
import org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;
import org.apache.xmlbeans.impl.schema.StscChecker;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.schema.StscJavaizer;
import org.apache.xmlbeans.impl.schema.StscResolver;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.schema.StscTranslator;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class SchemaTypeSystemCompiler {
    public static SchemaTypeSystem compile(Parameters params) {
        return SchemaTypeSystemCompiler.compileImpl(params.getExistingTypeSystem(), params.getName(), params.getSchemas(), params.getConfig(), params.getLinkTo(), params.getOptions(), params.getErrorListener(), params.isJavaize(), params.getBaseURI(), params.getSourcesToCopyMap(), params.getSchemasDir());
    }

    public static SchemaTypeSystemImpl compile(String name, SchemaTypeSystem existingSTS, XmlObject[] input, BindingConfig config, SchemaTypeLoader linkTo, Filer filer, XmlOptions options) throws XmlException {
        options = XmlOptions.maskNull(options);
        ArrayList<XmlObject> schemas = new ArrayList<XmlObject>();
        if (input != null) {
            for (int i = 0; i < input.length; ++i) {
                if (input[i] instanceof SchemaDocument.Schema) {
                    schemas.add(input[i]);
                    continue;
                }
                if (input[i] instanceof SchemaDocument && ((SchemaDocument)input[i]).getSchema() != null) {
                    schemas.add(((SchemaDocument)input[i]).getSchema());
                    continue;
                }
                throw new XmlException("Thread " + Thread.currentThread().getName() + ": The " + i + "th supplied input is not a schema document: its type is " + input[i].schemaType());
            }
        }
        Collection userErrors = (Collection)options.get("ERROR_LISTENER");
        XmlErrorWatcher errorWatcher = new XmlErrorWatcher(userErrors);
        SchemaTypeSystemImpl stsi = SchemaTypeSystemCompiler.compileImpl(existingSTS, name, schemas.toArray(new SchemaDocument.Schema[schemas.size()]), config, linkTo, options, errorWatcher, filer != null, (URI)options.get("BASE_URI"), null, null);
        if (errorWatcher.hasError() && stsi == null) {
            throw new XmlException(errorWatcher.firstError());
        }
        if (stsi != null && !stsi.isIncomplete() && filer != null) {
            stsi.save(filer);
            SchemaTypeSystemCompiler.generateTypes(stsi, filer, options);
        }
        return stsi;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static SchemaTypeSystemImpl compileImpl(SchemaTypeSystem system, String name, SchemaDocument.Schema[] schemas, BindingConfig config, SchemaTypeLoader linkTo, XmlOptions options, Collection outsideErrors, boolean javaize, URI baseURI, Map sourcesToCopyMap, File schemasDir) {
        if (linkTo == null) {
            throw new IllegalArgumentException("Must supply linkTo");
        }
        XmlErrorWatcher errorWatcher = new XmlErrorWatcher(outsideErrors);
        boolean incremental = system != null;
        StscState state = StscState.start();
        boolean validate = options == null || !options.hasOption("COMPILE_NO_VALIDATION");
        try {
            state.setErrorListener(errorWatcher);
            state.setBindingConfig(config);
            state.setOptions(options);
            state.setGivenTypeSystemName(name);
            state.setSchemasDir(schemasDir);
            if (baseURI != null) {
                state.setBaseUri(baseURI);
            }
            linkTo = SchemaTypeLoaderImpl.build(new SchemaTypeLoader[]{BuiltinSchemaTypeSystem.get(), linkTo}, null, null);
            state.setImportingTypeLoader(linkTo);
            ArrayList<SchemaDocument.Schema> validSchemas = new ArrayList<SchemaDocument.Schema>(schemas.length);
            if (validate) {
                XmlOptions validateOptions = new XmlOptions().setErrorListener(errorWatcher);
                if (options.hasOption("VALIDATE_TREAT_LAX_AS_SKIP")) {
                    validateOptions.setValidateTreatLaxAsSkip();
                }
                for (int i = 0; i < schemas.length; ++i) {
                    if (!schemas[i].validate(validateOptions)) continue;
                    validSchemas.add(schemas[i]);
                }
            } else {
                validSchemas.addAll(Arrays.asList(schemas));
            }
            SchemaDocument.Schema[] startWith = validSchemas.toArray(new SchemaDocument.Schema[validSchemas.size()]);
            if (incremental) {
                HashSet namespaces = new HashSet();
                startWith = SchemaTypeSystemCompiler.getSchemasToRecompile((SchemaTypeSystemImpl)system, startWith, namespaces);
                state.initFromTypeSystem((SchemaTypeSystemImpl)system, namespaces);
            } else {
                state.setDependencies(new SchemaDependencies());
            }
            StscImporter.SchemaToProcess[] schemasAndChameleons = StscImporter.resolveImportsAndIncludes(startWith, incremental);
            StscTranslator.addAllDefinitions(schemasAndChameleons);
            StscResolver.resolveAll();
            StscChecker.checkAll();
            StscJavaizer.javaizeAllTypes(javaize);
            state.get().sts().loadFromStscState(state);
            if (sourcesToCopyMap != null) {
                sourcesToCopyMap.putAll(state.sourceCopyMap());
            }
            if (errorWatcher.hasError()) {
                if (state.allowPartial() && state.getRecovered() == errorWatcher.size()) {
                    state.get().sts().setIncomplete(true);
                } else {
                    SchemaTypeSystemImpl schemaTypeSystemImpl = null;
                    return schemaTypeSystemImpl;
                }
            }
            if (system != null) {
                ((SchemaTypeSystemImpl)system).setIncomplete(true);
            }
            SchemaTypeSystemImpl schemaTypeSystemImpl = state.get().sts();
            return schemaTypeSystemImpl;
        } finally {
            StscState.end();
        }
    }

    private static SchemaDocument.Schema[] getSchemasToRecompile(SchemaTypeSystemImpl system, SchemaDocument.Schema[] modified, Set namespaces) {
        HashSet<String> modifiedFiles = new HashSet<String>();
        HashMap<String, SchemaDocument.Schema> haveFile = new HashMap<String, SchemaDocument.Schema>();
        ArrayList<SchemaDocument.Schema> result = new ArrayList<SchemaDocument.Schema>();
        for (int i = 0; i < modified.length; ++i) {
            String fileURL = modified[i].documentProperties().getSourceName();
            if (fileURL == null) {
                throw new IllegalArgumentException("One of the Schema files passed in doesn't have the source set, which prevents it to be incrementally compiled");
            }
            modifiedFiles.add(fileURL);
            haveFile.put(fileURL, modified[i]);
            result.add(modified[i]);
        }
        SchemaDependencies dep = system.getDependencies();
        List nss = dep.getNamespacesTouched(modifiedFiles);
        namespaces.addAll(dep.computeTransitiveClosure(nss));
        List needRecompilation = dep.getFilesTouched(namespaces);
        StscState.get().setDependencies(new SchemaDependencies(dep, namespaces));
        for (int i = 0; i < needRecompilation.size(); ++i) {
            String url = (String)needRecompilation.get(i);
            SchemaDocument.Schema have = (SchemaDocument.Schema)haveFile.get(url);
            if (have != null) continue;
            try {
                XmlObject xdoc = StscImporter.DownloadTable.downloadDocument(StscState.get().getS4SLoader(), null, url);
                XmlOptions voptions = new XmlOptions();
                voptions.setErrorListener(StscState.get().getErrorListener());
                if (!(xdoc instanceof SchemaDocument) || !xdoc.validate(voptions)) {
                    StscState.get().error("Referenced document is not a valid schema, URL = " + url, 56, null);
                    continue;
                }
                SchemaDocument sDoc = (SchemaDocument)xdoc;
                result.add(sDoc.getSchema());
                continue;
            } catch (MalformedURLException mfe) {
                StscState.get().error("exception.loading.url", new Object[]{"MalformedURLException", url, mfe.getMessage()}, null);
                continue;
            } catch (IOException ioe) {
                StscState.get().error("exception.loading.url", new Object[]{"IOException", url, ioe.getMessage()}, null);
                continue;
            } catch (XmlException xmle) {
                StscState.get().error("exception.loading.url", new Object[]{"XmlException", url, xmle.getMessage()}, null);
            }
        }
        return result.toArray(new SchemaDocument.Schema[result.size()]);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean generateTypes(SchemaTypeSystem system, Filer filer, XmlOptions options) {
        if (system instanceof SchemaTypeSystemImpl && ((SchemaTypeSystemImpl)system).isIncomplete()) {
            return false;
        }
        boolean success = true;
        ArrayList<SchemaType> types = new ArrayList<SchemaType>();
        types.addAll(Arrays.asList(system.globalTypes()));
        types.addAll(Arrays.asList(system.documentTypes()));
        types.addAll(Arrays.asList(system.attributeTypes()));
        for (SchemaType type : types) {
            if (type.isBuiltinType() || type.getFullJavaName() == null) continue;
            String fjn = type.getFullJavaName();
            Writer writer = null;
            try {
                writer = filer.createSourceFile(fjn);
                SchemaTypeCodePrinter.printType(writer, type, options);
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                success = false;
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {}
            }
            try {
                fjn = type.getFullJavaImplName();
                writer = filer.createSourceFile(fjn);
                SchemaTypeCodePrinter.printTypeImpl(writer, type, options);
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                success = false;
            } finally {
                try {
                    if (writer == null) continue;
                    writer.close();
                } catch (IOException e) {}
            }
        }
        return success;
    }

    public static class Parameters {
        private SchemaTypeSystem existingSystem;
        private String name;
        private SchemaDocument.Schema[] schemas;
        private BindingConfig config;
        private SchemaTypeLoader linkTo;
        private XmlOptions options;
        private Collection errorListener;
        private boolean javaize;
        private URI baseURI;
        private Map sourcesToCopyMap;
        private File schemasDir;

        public SchemaTypeSystem getExistingTypeSystem() {
            return this.existingSystem;
        }

        public void setExistingTypeSystem(SchemaTypeSystem system) {
            this.existingSystem = system;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SchemaDocument.Schema[] getSchemas() {
            return this.schemas;
        }

        public void setSchemas(SchemaDocument.Schema[] schemas) {
            this.schemas = schemas;
        }

        public BindingConfig getConfig() {
            return this.config;
        }

        public void setConfig(BindingConfig config) {
            this.config = config;
        }

        public SchemaTypeLoader getLinkTo() {
            return this.linkTo;
        }

        public void setLinkTo(SchemaTypeLoader linkTo) {
            this.linkTo = linkTo;
        }

        public XmlOptions getOptions() {
            return this.options;
        }

        public void setOptions(XmlOptions options) {
            this.options = options;
        }

        public Collection getErrorListener() {
            return this.errorListener;
        }

        public void setErrorListener(Collection errorListener) {
            this.errorListener = errorListener;
        }

        public boolean isJavaize() {
            return this.javaize;
        }

        public void setJavaize(boolean javaize) {
            this.javaize = javaize;
        }

        public URI getBaseURI() {
            return this.baseURI;
        }

        public void setBaseURI(URI baseURI) {
            this.baseURI = baseURI;
        }

        public Map getSourcesToCopyMap() {
            return this.sourcesToCopyMap;
        }

        public void setSourcesToCopyMap(Map sourcesToCopyMap) {
            this.sourcesToCopyMap = sourcesToCopyMap;
        }

        public File getSchemasDir() {
            return this.schemasDir;
        }

        public void setSchemasDir(File schemasDir) {
            this.schemasDir = schemasDir;
        }
    }
}

