/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma.e4x;

import org.fife.rsta.ac.js.ast.type.ECMAAdditions;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;

public class ECMAvE4xAdditions
implements ECMAAdditions {
    @Override
    public void addAdditionalTypes(TypeDeclarations typeDecs) {
        typeDecs.addTypeDeclaration("JSGlobal", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x", "E4XGlobal", "Global", false, false));
        typeDecs.addTypeDeclaration("E4XNamespace", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x", "E4XNamespace", "Namespace", false, false));
        typeDecs.addTypeDeclaration("E4XQName", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x", "E4XQName", "QName", false, false));
        typeDecs.addTypeDeclaration("E4XXML", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x", "E4XXML", "XML", false, false));
        typeDecs.addTypeDeclaration("E4XXMLList", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x", "E4XXMLList", "XMLList", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions.E4XGlobalFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions", "E4XGlobalFunctions", "Global", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions.E4XXMLFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions", "E4XXMLFunctions", "XML", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions.E4XXMLListFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.e4x.functions", "E4XXMLListFunctions", "XMList", false, false));
        typeDecs.addECMAObject("E4XNamespace", true);
        typeDecs.addECMAObject("E4XQName", true);
        typeDecs.addECMAObject("E4XXML", true);
        typeDecs.addECMAObject("E4XXMLList", true);
    }
}

