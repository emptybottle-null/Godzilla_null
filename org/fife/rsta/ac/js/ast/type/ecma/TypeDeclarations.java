/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.ast.type.ArrayTypeDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;

public abstract class TypeDeclarations {
    private static final String ECMA_DEFAULT_PACKAGE = "org.fife.rsta.ac.js.ecma.api";
    public static final String ECMA_ARRAY = "JSArray";
    public static final String ECMA_BOOLEAN = "JSBoolean";
    public static final String ECMA_DATE = "JSDate";
    public static final String ECMA_ERROR = "JSError";
    public static final String ECMA_FUNCTION = "JSFunction";
    public static final String ECMA_MATH = "JSMath";
    public static final String ECMA_NUMBER = "JSNumber";
    public static final String ECMA_OBJECT = "JSObject";
    public static final String ECMA_REGEXP = "JSRegExp";
    public static final String ECMA_STRING = "JSString";
    public static final String ECMA_GLOBAL = "JSGlobal";
    public static final String ECMA_JSON = "JSJSON";
    public static final String ECMA_NAMESPACE = "E4XNamespace";
    public static final String ECMA_QNAME = "E4XQName";
    public static final String ECMA_XML = "E4XXML";
    public static final String ECMA_XMLLIST = "E4XXMLList";
    public static final String FUNCTION_CALL = "FC";
    public static final String ANY = "any";
    public static String NULL_TYPE = "void";
    private final HashMap<String, TypeDeclaration> types = new HashMap();
    private final HashMap<String, String> javascriptReverseLookup = new HashMap();
    private final HashSet<JavaScriptObject> ecmaObjects = new HashSet();

    public TypeDeclarations() {
        this.loadTypes();
        this.loadExtensions();
        this.loadReverseLookup();
        this.loadJavaScriptConstructors();
    }

    private void loadExtensions() {
        this.addTypeDeclaration(FUNCTION_CALL, new TypeDeclaration(null, FUNCTION_CALL, FUNCTION_CALL, false, false));
        this.addTypeDeclaration(ANY, new TypeDeclaration(null, ANY, ANY));
    }

    protected void loadJavaScriptConstructors() {
        this.addECMAObject(ECMA_STRING, true);
        this.addECMAObject(ECMA_DATE, true);
        this.addECMAObject(ECMA_NUMBER, true);
        this.addECMAObject(ECMA_MATH, false);
        this.addECMAObject(ECMA_OBJECT, true);
        this.addECMAObject(ECMA_FUNCTION, true);
        this.addECMAObject(ECMA_BOOLEAN, true);
        this.addECMAObject(ECMA_REGEXP, true);
        this.addECMAObject(ECMA_ARRAY, true);
        this.addECMAObject(ECMA_ERROR, true);
        this.addECMAObject(ECMA_JSON, false);
    }

    public void addECMAObject(String type, boolean canBeInstantiated) {
        this.ecmaObjects.add(new JavaScriptObject(type, canBeInstantiated));
    }

    protected void loadReverseLookup() {
        this.addJavaScriptLookup("String", ECMA_STRING);
        this.addJavaScriptLookup("Date", ECMA_DATE);
        this.addJavaScriptLookup("RegExp", ECMA_REGEXP);
        this.addJavaScriptLookup("Number", ECMA_NUMBER);
        this.addJavaScriptLookup("Math", ECMA_MATH);
        this.addJavaScriptLookup("Function", ECMA_FUNCTION);
        this.addJavaScriptLookup("Object", ECMA_OBJECT);
        this.addJavaScriptLookup("Array", ECMA_ARRAY);
        this.addJavaScriptLookup("Boolean", ECMA_BOOLEAN);
        this.addJavaScriptLookup("Error", ECMA_ERROR);
        this.addJavaScriptLookup("java.lang.String", ECMA_STRING);
        this.addJavaScriptLookup("java.lang.Number", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Short", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Long", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Float", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Byte", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Double", ECMA_NUMBER);
        this.addJavaScriptLookup("java.lang.Boolean", ECMA_BOOLEAN);
        this.addJavaScriptLookup("short", ECMA_NUMBER);
        this.addJavaScriptLookup("long", ECMA_NUMBER);
        this.addJavaScriptLookup("float", ECMA_NUMBER);
        this.addJavaScriptLookup("byte", ECMA_NUMBER);
        this.addJavaScriptLookup("double", ECMA_NUMBER);
        this.addJavaScriptLookup("int", ECMA_NUMBER);
        this.addJavaScriptLookup("boolean", ECMA_BOOLEAN);
        this.addJavaScriptLookup("JSON", ECMA_JSON);
        this.addJavaScriptLookup("Namespace", ECMA_NAMESPACE);
        this.addJavaScriptLookup("QName", ECMA_QNAME);
        this.addJavaScriptLookup("XML", ECMA_XML);
        this.addJavaScriptLookup("XMLList", ECMA_XMLLIST);
    }

    protected abstract void loadTypes();

    public void addTypeDeclaration(String name, TypeDeclaration dec) {
        this.types.put(name, dec);
        this.addJavaScriptLookup(dec.getQualifiedName(), name);
    }

    public String getClassName(String lookupType) {
        TypeDeclaration dec = this.types.get(lookupType);
        return dec != null ? dec.getQualifiedName() : null;
    }

    public List<String> getAllClasses() {
        ArrayList<String> classes = new ArrayList<String>();
        for (String name : this.types.keySet()) {
            TypeDeclaration dec = this.types.get(name);
            if (dec == null) continue;
            classes.add(dec.getQualifiedName());
        }
        return classes;
    }

    public List<TypeDeclaration> getAllJavaScriptTypeDeclarations() {
        ArrayList<TypeDeclaration> jsTypes = new ArrayList<TypeDeclaration>();
        for (String name : this.types.keySet()) {
            TypeDeclaration dec = this.types.get(name);
            if (!this.isJavaScriptType(dec)) continue;
            jsTypes.add(dec);
        }
        return jsTypes;
    }

    public void addJavaScriptLookup(String apiName, String jsName) {
        this.javascriptReverseLookup.put(apiName, jsName);
    }

    public void removeType(String name) {
        this.types.remove(name);
    }

    public boolean isJavaScriptType(TypeDeclaration td) {
        return td != null && td.getPackageName() != null && td.getPackageName().startsWith(ECMA_DEFAULT_PACKAGE);
    }

    public TypeDeclaration getTypeDeclaration(String name) {
        if (name == null) {
            return null;
        }
        TypeDeclaration typeDeclation = this.types.get(name);
        if (typeDeclation == null) {
            typeDeclation = this.getJSType(name);
        }
        return typeDeclation;
    }

    private TypeDeclaration getJSType(String lookupName) {
        if (lookupName.indexOf(91) > -1 && lookupName.indexOf(93) > -1) {
            TypeDeclaration arrayType = this.getTypeDeclaration(ECMA_ARRAY);
            ArrayTypeDeclaration arrayDec = new ArrayTypeDeclaration(arrayType.getPackageName(), arrayType.getAPITypeName(), arrayType.getJSName());
            String arrayTypeName = lookupName.substring(0, lookupName.indexOf(91));
            TypeDeclaration containerType = JavaScriptHelper.createNewTypeDeclaration(arrayTypeName);
            arrayDec.setArrayType(containerType);
            return arrayDec;
        }
        String name = this.javascriptReverseLookup.get(lookupName);
        if (name != null) {
            return this.types.get(name);
        }
        return null;
    }

    public Set<JavaScriptObject> getJavaScriptObjects() {
        return this.ecmaObjects;
    }

    public boolean canECMAObjectBeInstantiated(String name) {
        String tempName = this.javascriptReverseLookup.get(name);
        if (tempName != null) {
            name = tempName;
        }
        for (JavaScriptObject jo : this.ecmaObjects) {
            if (!jo.getName().equals(name)) continue;
            return jo.canBeInstantiated();
        }
        return false;
    }

    public static class JavaScriptObject {
        private String name;
        private boolean canBeInstantiated;

        public JavaScriptObject(String name, boolean canBeInstantiated) {
            this.name = name;
            this.canBeInstantiated = canBeInstantiated;
        }

        public String getName() {
            return this.name;
        }

        public boolean canBeInstantiated() {
            return this.canBeInstantiated;
        }

        public boolean equals(Object jsObj) {
            if (jsObj == this) {
                return true;
            }
            if (jsObj instanceof JavaScriptObject) {
                return ((JavaScriptObject)jsObj).getName().equals(this.getName());
            }
            return false;
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }
}

