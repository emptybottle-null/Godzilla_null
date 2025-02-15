/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.jsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.classreader.MemberInfo;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptType;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclarationFactory;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;
import org.fife.rsta.ac.js.completion.JSBeanCompletion;
import org.fife.rsta.ac.js.completion.JSClassCompletion;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.rsta.ac.js.completion.JSConstructorCompletion;
import org.fife.rsta.ac.js.completion.JSFieldCompletion;
import org.fife.rsta.ac.js.completion.JSFunctionCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public abstract class JavaScriptTypesFactory {
    protected HashMap<TypeDeclaration, JavaScriptType> cachedTypes = new HashMap();
    private boolean useBeanproperties;
    protected TypeDeclarationFactory typesFactory;
    private static final List<String> UNSUPPORTED_COMPLETIONS;
    private static String SPECIAL_METHOD;

    public JavaScriptTypesFactory(TypeDeclarationFactory typesFactory) {
        this.typesFactory = typesFactory;
    }

    public static JavaScriptTypesFactory getDefaultJavaScriptTypesFactory(TypeDeclarationFactory typesFactory) {
        return new DefaultJavaScriptTypeFactory(typesFactory);
    }

    public void setUseBeanProperties(boolean useBeanproperties) {
        this.useBeanproperties = useBeanproperties;
    }

    public boolean isUseBeanProperties() {
        return this.useBeanproperties;
    }

    public JavaScriptType getCachedType(TypeDeclaration type, JarManager manager, DefaultCompletionProvider provider, String text) {
        if (manager == null || type == null) {
            return null;
        }
        if (this.cachedTypes.containsKey(type)) {
            return this.cachedTypes.get(type);
        }
        ClassFile cf = this.getClassFile(manager, type);
        JavaScriptType cachedType = this.makeJavaScriptType(type);
        this.cachedTypes.put(type, cachedType);
        this.readClassFile(cachedType, cf, provider, manager, type);
        return cachedType;
    }

    public ClassFile getClassFile(JarManager manager, TypeDeclaration type) {
        return manager != null ? manager.getClassEntry(type.getQualifiedName()) : null;
    }

    private void readClassFile(JavaScriptType cachedType, ClassFile cf, DefaultCompletionProvider provider, JarManager manager, TypeDeclaration type) {
        if (cf != null) {
            this.readMethodsAndFieldsFromTypeDeclaration(cachedType, provider, manager, cf);
        }
    }

    private boolean isBeanProperty(MethodInfo method) {
        return method.getParameterCount() == 0 && (method.getName().startsWith("get") || method.getName().startsWith("is"));
    }

    private void readMethodsAndFieldsFromTypeDeclaration(JavaScriptType cachedType, DefaultCompletionProvider provider, JarManager jarManager, ClassFile cf) {
        boolean staticOnly = cachedType.getType().isStaticsOnly();
        boolean supportsBeanProperties = cachedType.getType().supportsBeanProperties();
        boolean isJSType = this.typesFactory.isJavaScriptType(cachedType.getType());
        if (isJSType) {
            cachedType.setClassTypeCompletion(new JSClassCompletion((CompletionProvider)provider, cf, false));
        }
        int methodCount = cf.getMethodCount();
        for (int i = 0; i < methodCount; ++i) {
            JSFunctionCompletion completion;
            MethodInfo info = cf.getMethodInfo(i);
            if (!info.isConstructor() && !SPECIAL_METHOD.equals(info.getName())) {
                if (this.isAccessible(info.getAccessFlags(), staticOnly, isJSType) && (staticOnly && info.isStatic() || !staticOnly)) {
                    completion = new JSFunctionCompletion((CompletionProvider)provider, info, true);
                    cachedType.addCompletion(completion);
                }
                if (!staticOnly && this.useBeanproperties && supportsBeanProperties && this.isBeanProperty(info)) {
                    JSBeanCompletion beanCompletion = new JSBeanCompletion((CompletionProvider)provider, info, jarManager);
                    cachedType.addCompletion(beanCompletion);
                }
            }
            if (!isJSType || !info.isConstructor() || SPECIAL_METHOD.equals(info.getName()) || !this.typesFactory.canJavaScriptBeInstantiated(cachedType.getType().getQualifiedName())) continue;
            completion = new JSConstructorCompletion((CompletionProvider)provider, info);
            cachedType.addConstructor(completion);
        }
        int fieldCount = cf.getFieldCount();
        for (int i = 0; i < fieldCount; ++i) {
            FieldInfo info = cf.getFieldInfo(i);
            if (!this.isAccessible(info, staticOnly, isJSType)) continue;
            JSFieldCompletion completion = new JSFieldCompletion((CompletionProvider)provider, info);
            cachedType.addCompletion(completion);
        }
        String superClassName = cf.getSuperClassName(true);
        ClassFile superClass = this.getClassFileFor(cf, superClassName, jarManager);
        if (superClass != null && !JavaScriptTypesFactory.ignoreClass(superClassName)) {
            TypeDeclaration type = this.createNewTypeDeclaration(superClass, staticOnly, false);
            JavaScriptType extendedType = this.makeJavaScriptType(type);
            cachedType.addExtension(extendedType);
            this.readClassFile(extendedType, superClass, provider, jarManager, type);
        }
        for (int i = 0; i < cf.getImplementedInterfaceCount(); ++i) {
            String inter = cf.getImplementedInterfaceName(i, true);
            ClassFile intf = this.getClassFileFor(cf, inter, jarManager);
            if (intf == null || JavaScriptTypesFactory.ignoreClass(inter)) continue;
            TypeDeclaration type = this.createNewTypeDeclaration(intf, staticOnly, false);
            JavaScriptType extendedType = new JavaScriptType(type);
            cachedType.addExtension(extendedType);
            this.readClassFile(extendedType, intf, provider, jarManager, type);
        }
    }

    public static boolean ignoreClass(String className) {
        return UNSUPPORTED_COMPLETIONS.contains(className);
    }

    private boolean isAccessible(MemberInfo info, boolean staticOnly, boolean isJJType) {
        int access = info.getAccessFlags();
        boolean accessible = this.isAccessible(access, staticOnly, isJJType);
        return !staticOnly && accessible || staticOnly && info.isStatic() && accessible;
    }

    private boolean isAccessible(int access, boolean staticsOnly, boolean isJSType) {
        boolean accessible = false;
        if (staticsOnly && org.fife.rsta.ac.java.classreader.Util.isPublic(access) || !staticsOnly && org.fife.rsta.ac.java.classreader.Util.isPublic(access) || isJSType && org.fife.rsta.ac.java.classreader.Util.isProtected(access)) {
            accessible = true;
        }
        return accessible;
    }

    public TypeDeclaration createNewTypeDeclaration(ClassFile cf, boolean staticOnly) {
        return this.createNewTypeDeclaration(cf, staticOnly, true);
    }

    public TypeDeclaration createNewTypeDeclaration(ClassFile cf, boolean staticOnly, boolean addToCache) {
        String className = cf.getClassName(false);
        String packageName = cf.getPackageName();
        if (staticOnly && !addToCache) {
            return new TypeDeclaration(packageName, className, cf.getClassName(true), staticOnly);
        }
        String qualified = cf.getClassName(true);
        TypeDeclaration td = this.typesFactory.getTypeDeclaration(qualified);
        if (td == null) {
            td = new TypeDeclaration(packageName, className, cf.getClassName(true), staticOnly);
            if (addToCache) {
                this.typesFactory.addType(qualified, td);
            }
        }
        return td;
    }

    private ClassFile getClassFileFor(ClassFile cf, String className, JarManager jarManager) {
        if (className == null) {
            return null;
        }
        ClassFile superClass = null;
        if (!Util.isFullyQualified(className)) {
            String pkg = cf.getPackageName();
            if (pkg != null) {
                String temp = pkg + "." + className;
                superClass = jarManager.getClassEntry(temp);
            }
        } else {
            superClass = jarManager.getClassEntry(className);
        }
        return superClass;
    }

    public void populateCompletionsForType(JavaScriptType cachedType, Set<Completion> completions) {
        if (cachedType != null) {
            HashMap<String, JSCompletion> completionsForType = cachedType.getMethodFieldCompletions();
            for (JSCompletion completion : completionsForType.values()) {
                completions.add(completion);
            }
            List<JavaScriptType> extendedClasses = cachedType.getExtendedClasses();
            for (JavaScriptType extendedType : extendedClasses) {
                this.populateCompletionsForType(extendedType, completions);
            }
        }
    }

    public void removeCachedType(TypeDeclaration typeDef) {
        this.cachedTypes.remove(typeDef);
    }

    public void clearCache() {
        this.cachedTypes.clear();
    }

    public JavaScriptType makeJavaScriptType(TypeDeclaration type) {
        return new JavaScriptType(type);
    }

    public List<JavaScriptType> getECMAObjectTypes(SourceCompletionProvider provider) {
        ArrayList<JavaScriptType> constructors = new ArrayList<JavaScriptType>();
        Set<TypeDeclarations.JavaScriptObject> types = this.typesFactory.getECMAScriptObjects();
        JarManager manager = provider.getJarManager();
        for (TypeDeclarations.JavaScriptObject object : types) {
            TypeDeclaration type = this.typesFactory.getTypeDeclaration(object.getName());
            JavaScriptType js = this.getCachedType(type, manager, provider, null);
            if (js == null) continue;
            constructors.add(js);
        }
        return constructors;
    }

    static {
        SPECIAL_METHOD = "<clinit>";
        UNSUPPORTED_COMPLETIONS = new ArrayList<String>();
        UNSUPPORTED_COMPLETIONS.add("java.lang.Object");
    }

    private static class DefaultJavaScriptTypeFactory
    extends JavaScriptTypesFactory {
        public DefaultJavaScriptTypeFactory(TypeDeclarationFactory typesFactory) {
            super(typesFactory);
        }
    }
}

