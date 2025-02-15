/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type;

import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;
import org.fife.rsta.ac.js.ast.type.ecma.client.ClientBrowserAdditions;
import org.fife.rsta.ac.js.ast.type.ecma.client.DOMAddtions;
import org.fife.rsta.ac.js.ast.type.ecma.client.HTMLDOMAdditions;
import org.fife.rsta.ac.js.ast.type.ecma.e4x.ECMAvE4xAdditions;
import org.fife.rsta.ac.js.ast.type.ecma.v5.TypeDeclarationsECMAv5;

public class TypeDeclarationFactory {
    private TypeDeclarations ecma;

    public TypeDeclarationFactory() {
        this.setTypeDeclarationVersion(null, false, false);
    }

    public List<String> setTypeDeclarationVersion(String ecmaVersion, boolean xmlSupported, boolean client) {
        try {
            ecmaVersion = ecmaVersion == null ? this.getDefaultECMAVersion() : ecmaVersion;
            Class<?> ecmaClass = TypeDeclarationFactory.class.getClassLoader().loadClass(ecmaVersion);
            this.ecma = (TypeDeclarations)ecmaClass.newInstance();
        } catch (Exception e) {
            this.ecma = new TypeDeclarationsECMAv5();
        }
        if (xmlSupported) {
            new ECMAvE4xAdditions().addAdditionalTypes(this.ecma);
        }
        if (client) {
            new ClientBrowserAdditions().addAdditionalTypes(this.ecma);
            new DOMAddtions().addAdditionalTypes(this.ecma);
            new HTMLDOMAdditions().addAdditionalTypes(this.ecma);
        }
        return this.ecma.getAllClasses();
    }

    protected String getDefaultECMAVersion() {
        return TypeDeclarationsECMAv5.class.getName();
    }

    public List<TypeDeclaration> getAllJavaScriptTypes() {
        return this.ecma.getAllJavaScriptTypeDeclarations();
    }

    public void removeType(String name) {
        this.ecma.removeType(name);
    }

    public boolean isJavaScriptType(TypeDeclaration td) {
        return this.ecma.isJavaScriptType(td);
    }

    public TypeDeclaration getTypeDeclaration(String name) {
        return this.ecma.getTypeDeclaration(name);
    }

    private String getJSTypeDeclarationAsString(String name) {
        TypeDeclaration dec = this.getTypeDeclaration(name);
        return dec != null ? dec.getJSName() : null;
    }

    public String convertJavaScriptType(String lookupName, boolean qualified) {
        if (lookupName != null) {
            if (TypeDeclarations.NULL_TYPE.equals(lookupName)) {
                return null;
            }
            if (lookupName.indexOf(60) > -1) {
                lookupName = lookupName.substring(0, lookupName.indexOf(60));
            }
            String lookup = !qualified ? this.getJSTypeDeclarationAsString(lookupName) : lookupName;
            String string = lookupName = lookup != null ? lookup : lookupName;
            if (!qualified && lookupName != null && lookupName.contains(".")) {
                return lookupName.substring(lookupName.lastIndexOf(".") + 1, lookupName.length());
            }
        }
        return lookupName;
    }

    public TypeDeclaration getDefaultTypeDeclaration() {
        return this.getTypeDeclaration("any");
    }

    public void addType(String name, TypeDeclaration dec) {
        this.ecma.addTypeDeclaration(name, dec);
    }

    public String getClassName(String lookup) throws RuntimeException {
        TypeDeclaration td = this.getTypeDeclaration(lookup);
        if (td != null) {
            return td.getQualifiedName();
        }
        throw new RuntimeException("Error finding TypeDeclaration for: " + lookup);
    }

    public Set<TypeDeclarations.JavaScriptObject> getECMAScriptObjects() {
        return this.ecma.getJavaScriptObjects();
    }

    public boolean canJavaScriptBeInstantiated(String name) {
        return this.ecma.canECMAObjectBeInstantiated(name);
    }
}

