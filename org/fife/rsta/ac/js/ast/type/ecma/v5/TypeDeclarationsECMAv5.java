/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma.v5;

import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.v3.TypeDeclarationsECMAv3;

public class TypeDeclarationsECMAv5
extends TypeDeclarationsECMAv3 {
    @Override
    protected void loadTypes() {
        super.loadTypes();
        this.addTypeDeclaration("JSArray", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5Array", "Array", false, false));
        this.addTypeDeclaration("JSDate", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5Date", "Date", false, false));
        this.addTypeDeclaration("JSFunction", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5Function", "Function", false, false));
        this.addTypeDeclaration("JSObject", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5Object", "Object", false, false));
        this.addTypeDeclaration("JSString", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5String", "String", false, false));
        this.addTypeDeclaration("JSJSON", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5", "JS5JSON", "JSON", false, false));
        this.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ArrayFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions", "JS5ArrayFunctions", "Array", false, false));
        this.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5DateFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions", "JS5DateFunctions", "Date", false, false));
        this.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5FunctionFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions", "JS5FunctionFunctions", "Function", false, false));
        this.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions", "JS5ObjectFunctions", "Object", false, false));
        this.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5StringFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.ecma5.functions", "JS5StringFunctions", "String", false, false));
    }
}

