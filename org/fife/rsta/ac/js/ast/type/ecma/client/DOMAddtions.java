/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma.client;

import org.fife.rsta.ac.js.ast.type.ECMAAdditions;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;

public class DOMAddtions
implements ECMAAdditions {
    @Override
    public void addAdditionalTypes(TypeDeclarations typeDecs) {
        typeDecs.addTypeDeclaration("Attr", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSAttr", "Attr", false, false));
        typeDecs.addTypeDeclaration("CDATASection", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSCDATASection", "CDATASection", false, false));
        typeDecs.addTypeDeclaration("CharacterData", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSCharacterData", "CharacterData", false, false));
        typeDecs.addTypeDeclaration("Comment", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSComment", "Comment", false, false));
        typeDecs.addTypeDeclaration("Document", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDocument", "Document", false, false));
        typeDecs.addTypeDeclaration("DocumentFragment", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDocumentFragment", "DocumentFragment", false, false));
        typeDecs.addTypeDeclaration("DocumentType", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDocumentType", "DocumentType", false, false));
        typeDecs.addTypeDeclaration("DOMConfiguration", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDOMConfiguration", "DOMConfiguration", false, false));
        typeDecs.addTypeDeclaration("DOMImplementation", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDOMImplementation", "DOMImplementation", false, false));
        typeDecs.addTypeDeclaration("DOMImplementationList", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDOMImplementationList", "DOMImplementationList", false, false));
        typeDecs.addTypeDeclaration("DOMImplementationSource", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "DOMImplementationSource", "DOMImplementationSource", false, false));
        typeDecs.addTypeDeclaration("DOMLocator", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDOMLocator", "DOMLocator", false, false));
        typeDecs.addTypeDeclaration("DOMStringList", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSDOMStringList", "DOMStringList", false, false));
        typeDecs.addTypeDeclaration("Element", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSElement", "Element", false, false));
        typeDecs.addTypeDeclaration("Entity", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSEntity", "Entity", false, false));
        typeDecs.addTypeDeclaration("EntityReference", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSEntityReference", "EntityReference", false, false));
        typeDecs.addTypeDeclaration("NamedNodeMap", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSNamedNodeMap", "NamedNodeMap", false, false));
        typeDecs.addTypeDeclaration("NameList", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSNameList", "NameList", false, false));
        typeDecs.addTypeDeclaration("Node", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSNode", "Node", false, false));
        typeDecs.addTypeDeclaration("NodeList", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSNodeList", "NodeList", false, false));
        typeDecs.addTypeDeclaration("Notation", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSNotation", "Notation", false, false));
        typeDecs.addTypeDeclaration("ProcessingInstruction", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSProcessingInstruction", "ProcessingInstruction", false, false));
        typeDecs.addTypeDeclaration("Text", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSText", "Text", false, false));
        typeDecs.addTypeDeclaration("TypeInfo", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSTypeInfo", "TypeInfo", false, false));
        typeDecs.addTypeDeclaration("UserDataHandler", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom", "JSUserDataHandler", "UserDataHandler", false, false));
        typeDecs.addECMAObject("Attr", true);
        typeDecs.addECMAObject("CDATASection", true);
        typeDecs.addECMAObject("CharacterData", true);
        typeDecs.addECMAObject("Comment", true);
        typeDecs.addECMAObject("Document", true);
        typeDecs.addECMAObject("DocumentFragment", true);
        typeDecs.addECMAObject("DOMConfiguration", true);
        typeDecs.addECMAObject("DOMImplementation", true);
        typeDecs.addECMAObject("DOMImplementationList", true);
        typeDecs.addECMAObject("DOMLocator", true);
        typeDecs.addECMAObject("DOMStringList", true);
        typeDecs.addECMAObject("Element", true);
        typeDecs.addECMAObject("Entity", true);
        typeDecs.addECMAObject("EntityReference", true);
        typeDecs.addECMAObject("NamedNodeMap", true);
        typeDecs.addECMAObject("NameList", true);
        typeDecs.addECMAObject("Node", true);
        typeDecs.addECMAObject("NodeList", true);
        typeDecs.addECMAObject("Notation", true);
        typeDecs.addECMAObject("ProcessingInstruction", true);
        typeDecs.addECMAObject("Text", true);
        typeDecs.addECMAObject("TypeInfo", true);
        typeDecs.addECMAObject("UserDataHandler", true);
    }
}

