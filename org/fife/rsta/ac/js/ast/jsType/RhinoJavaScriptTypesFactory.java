/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.jsType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.ast.jsType.JSR223JavaScriptTypesFactory;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptType;
import org.fife.rsta.ac.js.ast.parser.RhinoJavaScriptAstParser;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclarationFactory;

public class RhinoJavaScriptTypesFactory
extends JSR223JavaScriptTypesFactory {
    private LinkedHashSet<String> importClasses = new LinkedHashSet();
    private LinkedHashSet<String> importPackages = new LinkedHashSet();

    public RhinoJavaScriptTypesFactory(TypeDeclarationFactory typesFactory) {
        super(typesFactory);
    }

    public void addImportClass(String qualifiedClass) {
        this.importClasses.add(qualifiedClass);
    }

    public void addImportPackage(String packageName) {
        this.importPackages.add(packageName);
    }

    public void mergeImports(HashSet<String> packages, HashSet<String> classes) {
        this.mergeImports(packages, this.importPackages, true);
        this.mergeImports(classes, this.importClasses, false);
    }

    private void mergeImports(HashSet<String> newImports, LinkedHashSet<String> oldImports, boolean packages) {
        HashSet<String> remove = new HashSet<String>();
        for (String obj : oldImports) {
            if (newImports.contains(obj)) continue;
            remove.add(obj);
        }
        if (!remove.isEmpty()) {
            HashSet<TypeDeclaration> removeTypes = new HashSet<TypeDeclaration>();
            for (String name : remove) {
                for (TypeDeclaration dec : this.cachedTypes.keySet()) {
                    if ((!packages || !dec.getQualifiedName().startsWith(name)) && (packages || !dec.getQualifiedName().equals(name))) continue;
                    this.removeAllTypes((JavaScriptType)this.cachedTypes.get(dec));
                    removeTypes.add(dec);
                }
            }
            this.cachedTypes.keySet().removeAll(removeTypes);
        }
        if (this.canClearCache(newImports, oldImports)) {
            oldImports.clear();
            this.clearAllImportTypes();
            oldImports.addAll(newImports);
        }
    }

    private boolean canClearCache(HashSet<String> newImports, LinkedHashSet<String> oldImports) {
        if (newImports.size() != oldImports.size()) {
            return true;
        }
        for (String im : oldImports) {
            if (newImports.contains(im)) continue;
            return true;
        }
        return false;
    }

    public void clearImportCache() {
        this.importClasses.clear();
        this.importPackages.clear();
        this.clearAllImportTypes();
    }

    private void clearAllImportTypes() {
        HashSet<TypeDeclaration> removeTypes = new HashSet<TypeDeclaration>();
        for (TypeDeclaration dec : this.cachedTypes.keySet()) {
            if (this.typesFactory.isJavaScriptType(dec) || dec.equals(this.typesFactory.getDefaultTypeDeclaration())) continue;
            this.removeAllTypes((JavaScriptType)this.cachedTypes.get(dec));
            removeTypes.add(dec);
        }
        this.cachedTypes.keySet().removeAll(removeTypes);
    }

    private void removeAllTypes(JavaScriptType type) {
        if (type != null) {
            this.typesFactory.removeType(type.getType().getQualifiedName());
            if (type.getExtendedClasses().size() > 0) {
                for (JavaScriptType extendedType : type.getExtendedClasses()) {
                    this.removeAllTypes(extendedType);
                }
            }
        }
    }

    @Override
    public ClassFile getClassFile(JarManager manager, TypeDeclaration type) {
        String qName = this.removePackagesFromType(type.getQualifiedName());
        ClassFile file = super.getClassFile(manager, JavaScriptHelper.createNewTypeDeclaration(qName));
        if (file == null && (file = this.findFromClasses(manager, qName)) == null) {
            file = this.findFromImport(manager, qName);
        }
        return file;
    }

    private String removePackagesFromType(String type) {
        if (type.startsWith("Packages.")) {
            return RhinoJavaScriptAstParser.removePackages(type);
        }
        return type;
    }

    private ClassFile findFromClasses(JarManager manager, String name) {
        String cls;
        ClassFile file = null;
        Iterator iterator = this.importClasses.iterator();
        while (iterator.hasNext() && (!(cls = (String)iterator.next()).endsWith(name) || (file = manager.getClassEntry(cls)) == null)) {
        }
        return file;
    }

    private ClassFile findFromImport(JarManager manager, String name) {
        ClassFile file = null;
        for (String packageName : this.importPackages) {
            String cls = name.startsWith(".") ? packageName + name : packageName + "." + name;
            file = manager.getClassEntry(cls);
            if (file == null) continue;
            break;
        }
        return file;
    }
}

