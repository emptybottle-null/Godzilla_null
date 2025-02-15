/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma.client;

import org.fife.rsta.ac.js.ast.type.ECMAAdditions;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;

public class ClientBrowserAdditions
implements ECMAAdditions {
    @Override
    public void addAdditionalTypes(TypeDeclarations typeDecs) {
        typeDecs.addTypeDeclaration("Window", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "Window", "Window", false, false));
        typeDecs.addTypeDeclaration("History", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "History", "History", false, false));
        typeDecs.addTypeDeclaration("Location", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "Location", "Location", false, false));
        typeDecs.addTypeDeclaration("Navigator", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "Navigator", "Navigator", false, false));
        typeDecs.addTypeDeclaration("Screen", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "Screen", "Screen", false, false));
        typeDecs.addTypeDeclaration("BarProp", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client", "BarProp", "BarProp", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions.HistoryFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions", "HistoryFunctions", "History", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions.LocationFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions", "LocationFunctions", "Location", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions.NavigatorFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions", "NavigatorFunctions", "Navigator", false, false));
        typeDecs.addTypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions.WindowFunctions", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.client.funtions", "WindowFunctions", "Window", false, false));
        typeDecs.addECMAObject("Window", true);
        typeDecs.addECMAObject("History", true);
        typeDecs.addECMAObject("Location", true);
        typeDecs.addECMAObject("Navigator", true);
        typeDecs.addECMAObject("Screen", true);
        typeDecs.addECMAObject("BarProp", true);
    }
}

