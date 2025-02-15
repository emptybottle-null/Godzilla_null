/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.jsType;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclarationFactory;
import org.mozilla.javascript.Kit;

public class JavaScriptFunctionType {
    public static int CONVERSION_NONE = 999;
    public static int CONVERSION_JS = 99;
    public static Class<?> BooleanClass = Kit.classOrNull("java.lang.Boolean");
    public static Class<?> ByteClass = Kit.classOrNull("java.lang.Byte");
    public static Class<?> CharacterClass = Kit.classOrNull("java.lang.Character");
    public static Class<?> ClassClass = Kit.classOrNull("java.lang.Class");
    public static Class<?> DoubleClass = Kit.classOrNull("java.lang.Double");
    public static Class<?> FloatClass = Kit.classOrNull("java.lang.Float");
    public static Class<?> IntegerClass = Kit.classOrNull("java.lang.Integer");
    public static Class<?> LongClass = Kit.classOrNull("java.lang.Long");
    public static Class<?> NumberClass = Kit.classOrNull("java.lang.Number");
    public static Class<?> ObjectClass = Kit.classOrNull("java.lang.Object");
    public static Class<?> ShortClass = Kit.classOrNull("java.lang.Short");
    public static Class<?> StringClass = Kit.classOrNull("java.lang.String");
    public static Class<?> DateClass = Kit.classOrNull("java.util.Date");
    public static Class<?> JSBooleanClass = null;
    public static Class<?> JSStringClass = null;
    public static Class<?> JSNumberClass = null;
    public static Class<?> JSObjectClass = null;
    public static Class<?> JSDateClass = null;
    public static Class<?> JSArray = null;
    private String name;
    private List<TypeDeclaration> arguments;
    private static final int JSTYPE_UNDEFINED = 0;
    private static final int JSTYPE_BOOLEAN = 1;
    private static final int JSTYPE_NUMBER = 2;
    private static final int JSTYPE_STRING = 3;
    private static final int JSTYPE_ARRAY = 4;
    private static final int JSTYPE_OBJECT = 5;

    private JavaScriptFunctionType(String name, SourceCompletionProvider provider) {
        this(name, new ArrayList<TypeDeclaration>(), provider);
    }

    private JavaScriptFunctionType(String name, List<TypeDeclaration> arguments, SourceCompletionProvider provider) {
        this.name = name;
        this.arguments = arguments;
        JSBooleanClass = Kit.classOrNull(provider.getTypesFactory().getClassName("JSBoolean"));
        JSStringClass = Kit.classOrNull(provider.getTypesFactory().getClassName("JSString"));
        JSNumberClass = Kit.classOrNull(provider.getTypesFactory().getClassName("JSNumber"));
        JSObjectClass = Kit.classOrNull(provider.getTypesFactory().getClassName("JSObject"));
        JSDateClass = Kit.classOrNull(provider.getTypesFactory().getClassName("JSDate"));
        JSArray = Kit.classOrNull(provider.getTypesFactory().getClassName("JSArray"));
    }

    public String getName() {
        return this.name;
    }

    public List<TypeDeclaration> getArguments() {
        return this.arguments;
    }

    public void addArgument(TypeDeclaration type) {
        if (this.arguments == null) {
            this.arguments = new ArrayList<TypeDeclaration>();
        }
        this.arguments.add(type);
    }

    public int getArgumentCount() {
        return this.arguments != null ? this.arguments.size() : 0;
    }

    public TypeDeclaration getArgument(int index) {
        return this.arguments != null ? this.arguments.get(index) : null;
    }

    public int compare(JavaScriptFunctionType compareType, SourceCompletionProvider provider, boolean isJavaScriptType) {
        TypeDeclaration compareParam;
        TypeDeclaration param;
        boolean argsMatch;
        if (!compareType.getName().equals(this.getName())) {
            return CONVERSION_NONE;
        }
        boolean bl = argsMatch = compareType.getArgumentCount() == this.getArgumentCount();
        if (!isJavaScriptType && !argsMatch) {
            return CONVERSION_NONE;
        }
        if (isJavaScriptType && !argsMatch) {
            return CONVERSION_JS;
        }
        int weight = 0;
        for (int i = 0; i < this.getArgumentCount() && (weight += this.compareParameters(param = this.getArgument(i), compareParam = compareType.getArgument(i), provider)) < CONVERSION_NONE; ++i) {
        }
        return weight;
    }

    private TypeDeclaration convertParamType(TypeDeclaration type, SourceCompletionProvider provider) {
        ClassFile cf = provider.getJavaScriptTypesFactory().getClassFile(provider.getJarManager(), type);
        if (cf != null) {
            return provider.getJavaScriptTypesFactory().createNewTypeDeclaration(cf, type.isStaticsOnly(), false);
        }
        return type;
    }

    private int compareParameters(TypeDeclaration param, TypeDeclaration compareParam, SourceCompletionProvider provider) {
        if (compareParam.equals(param)) {
            return 0;
        }
        param = this.convertParamType(param, provider);
        compareParam = this.convertParamType(compareParam, provider);
        try {
            int fromCode = JavaScriptFunctionType.getJSTypeCode(param.getQualifiedName(), provider.getTypesFactory());
            Class<?> to = this.convertClassToJavaClass(compareParam.getQualifiedName(), provider.getTypesFactory());
            Class<?> from = this.convertClassToJavaClass(param.getQualifiedName(), provider.getTypesFactory());
            switch (fromCode) {
                case 0: {
                    if (to != StringClass && to != ObjectClass) break;
                    return 1;
                }
                case 1: {
                    if (to == Boolean.TYPE) {
                        return 1;
                    }
                    if (to == BooleanClass) {
                        return 2;
                    }
                    if (to == ObjectClass) {
                        return 3;
                    }
                    if (to != StringClass) break;
                    return 4;
                }
                case 2: {
                    if (to.isPrimitive()) {
                        if (to == Double.TYPE) {
                            return 1;
                        }
                        if (to == Boolean.TYPE) break;
                        return 1 + JavaScriptFunctionType.getSizeRank(to);
                    }
                    if (to == StringClass) {
                        return 9;
                    }
                    if (to == ObjectClass) {
                        return 10;
                    }
                    if (!NumberClass.isAssignableFrom(to)) break;
                    return 2;
                }
                case 3: {
                    if (to == StringClass) {
                        return 1;
                    }
                    if (!to.isPrimitive()) break;
                    if (to == Character.TYPE) {
                        return 3;
                    }
                    if (to == Boolean.TYPE) break;
                    return 4;
                }
                case 4: {
                    if (to == JSArray) {
                        return 1;
                    }
                    if (to == StringClass) {
                        return 2;
                    }
                    if (!to.isPrimitive() || to == Boolean.TYPE) break;
                    return fromCode == 4 ? CONVERSION_NONE : 2 + JavaScriptFunctionType.getSizeRank(to);
                }
                case 5: {
                    if (to != ObjectClass && from.isAssignableFrom(to)) {
                        return 1;
                    }
                    if (to.isArray()) {
                        if (from != JSArray && !from.isArray()) break;
                        return 1;
                    }
                    if (to == ObjectClass) {
                        return 2;
                    }
                    if (to == StringClass) {
                        return 3;
                    }
                    if (to == DateClass) {
                        if (from != DateClass) break;
                        return 1;
                    }
                    if (!from.isPrimitive() || to == Boolean.TYPE) break;
                    return 3 + JavaScriptFunctionType.getSizeRank(from);
                }
            }
        } catch (ClassNotFoundException fromCode) {
            // empty catch block
        }
        TypeDeclarationFactory typesFactory = provider.getTypesFactory();
        String paramJSType = typesFactory.convertJavaScriptType(param.getQualifiedName(), true);
        String compareParamJSType = typesFactory.convertJavaScriptType(compareParam.getQualifiedName(), true);
        try {
            Class<?> paramClzz = Class.forName(paramJSType);
            Class<?> compareParamClzz = Class.forName(compareParamJSType);
            if (compareParamClzz.isAssignableFrom(paramClzz)) {
                return 3;
            }
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        if (compareParam.equals(typesFactory.getDefaultTypeDeclaration())) {
            return 4;
        }
        return CONVERSION_NONE;
    }

    private Class<?> convertClassToJavaClass(String name, TypeDeclarationFactory typesFactory) throws ClassNotFoundException {
        if (name.equals("any")) {
            return ObjectClass;
        }
        TypeDeclaration type = typesFactory.getTypeDeclaration(name);
        String clsName = type != null ? type.getQualifiedName() : name;
        Class<?> cls = Class.forName(clsName);
        if (cls == JSStringClass) {
            cls = StringClass;
        } else if (cls == JSBooleanClass) {
            cls = BooleanClass;
        } else if (cls == JSNumberClass) {
            cls = NumberClass;
        } else if (cls == JSDateClass) {
            cls = DateClass;
        } else if (cls == JSObjectClass) {
            cls = ObjectClass;
        }
        return cls;
    }

    public static JavaScriptFunctionType parseFunction(String function, SourceCompletionProvider provider) {
        String paramsStr;
        int paramStartIndex = function.indexOf(40);
        int paramEndIndex = function.indexOf(41);
        JavaScriptFunctionType functionType = new JavaScriptFunctionType(function.substring(0, paramStartIndex), provider);
        if (paramStartIndex > -1 && paramEndIndex > -1 && (paramsStr = function.substring(paramStartIndex + 1, paramEndIndex).trim()).length() > 0) {
            String[] params = paramsStr.split(",");
            for (int i = 0; i < params.length; ++i) {
                String param = provider.getTypesFactory().convertJavaScriptType(params[i], true);
                TypeDeclaration type = provider.getTypesFactory().getTypeDeclaration(param);
                if (type != null) {
                    functionType.addArgument(type);
                    continue;
                }
                functionType.addArgument(JavaScriptHelper.createNewTypeDeclaration(param));
            }
        }
        return functionType;
    }

    private static int getJSTypeCode(String clsName, TypeDeclarationFactory typesFactory) throws ClassNotFoundException {
        if (clsName.equals("any")) {
            return 0;
        }
        TypeDeclaration dec = typesFactory.getTypeDeclaration(clsName);
        Class<?> cls = Class.forName(clsName = dec != null ? dec.getQualifiedName() : clsName);
        if (cls == BooleanClass || cls == JSBooleanClass) {
            return 1;
        }
        if (NumberClass.isAssignableFrom(cls) || cls == JSNumberClass) {
            return 2;
        }
        if (StringClass.isAssignableFrom(cls) || cls == JSStringClass) {
            return 3;
        }
        if (cls.isArray() || cls == JSArray) {
            return 4;
        }
        return 5;
    }

    static int getSizeRank(Class<?> aType) {
        if (aType == Double.TYPE) {
            return 1;
        }
        if (aType == Float.TYPE) {
            return 2;
        }
        if (aType == Long.TYPE) {
            return 3;
        }
        if (aType == Integer.TYPE) {
            return 4;
        }
        if (aType == Short.TYPE) {
            return 5;
        }
        if (aType == Character.TYPE) {
            return 6;
        }
        if (aType == Byte.TYPE) {
            return 7;
        }
        if (aType == Boolean.TYPE) {
            return CONVERSION_NONE;
        }
        return 8;
    }
}

