/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.config;

import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.config.BindingConfigImpl;
import org.apache.xmlbeans.impl.config.NameSet;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JParameter;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

public class InterfaceExtensionImpl
implements InterfaceExtension {
    private NameSet _xbeanSet;
    private String _interfaceClassName;
    private String _delegateToClassName;
    private MethodSignatureImpl[] _methods;

    static InterfaceExtensionImpl newInstance(JamClassLoader loader, NameSet xbeanSet, Extensionconfig.Interface intfXO) {
        InterfaceExtensionImpl result = new InterfaceExtensionImpl();
        result._xbeanSet = xbeanSet;
        JClass interfaceJClass = InterfaceExtensionImpl.validateInterface(loader, intfXO.getName(), intfXO);
        if (interfaceJClass == null) {
            BindingConfigImpl.error("Interface '" + intfXO.getStaticHandler() + "' not found.", intfXO);
            return null;
        }
        result._interfaceClassName = interfaceJClass.getQualifiedName();
        result._delegateToClassName = intfXO.getStaticHandler();
        JClass delegateJClass = InterfaceExtensionImpl.validateClass(loader, result._delegateToClassName, intfXO);
        if (delegateJClass == null) {
            BindingConfigImpl.warning("Handler class '" + intfXO.getStaticHandler() + "' not found on classpath, skip validation.", intfXO);
            return result;
        }
        if (!result.validateMethods(interfaceJClass, delegateJClass, intfXO)) {
            return null;
        }
        return result;
    }

    private static JClass validateInterface(JamClassLoader loader, String intfStr, XmlObject loc) {
        return InterfaceExtensionImpl.validateJava(loader, intfStr, true, loc);
    }

    static JClass validateClass(JamClassLoader loader, String clsStr, XmlObject loc) {
        return InterfaceExtensionImpl.validateJava(loader, clsStr, false, loc);
    }

    static JClass validateJava(JamClassLoader loader, String clsStr, boolean isInterface, XmlObject loc) {
        if (loader == null) {
            return null;
        }
        String ent = isInterface ? "Interface" : "Class";
        JClass cls = loader.loadClass(clsStr);
        if (cls == null || cls.isUnresolvedType()) {
            BindingConfigImpl.error(ent + " '" + clsStr + "' not found.", loc);
            return null;
        }
        if (isInterface && !cls.isInterface() || !isInterface && cls.isInterface()) {
            BindingConfigImpl.error("'" + clsStr + "' must be " + (isInterface ? "an interface" : "a class") + ".", loc);
        }
        if (!cls.isPublic()) {
            BindingConfigImpl.error(ent + " '" + clsStr + "' is not public.", loc);
        }
        return cls;
    }

    private boolean validateMethods(JClass interfaceJClass, JClass delegateJClass, XmlObject loc) {
        boolean valid = true;
        JMethod[] interfaceMethods = interfaceJClass.getMethods();
        this._methods = new MethodSignatureImpl[interfaceMethods.length];
        for (int i = 0; i < interfaceMethods.length; ++i) {
            JMethod method = this.validateMethod(interfaceJClass, delegateJClass, interfaceMethods[i], loc);
            if (method != null) {
                this._methods[i] = new MethodSignatureImpl(this.getStaticHandler(), method);
                continue;
            }
            valid = false;
        }
        return valid;
    }

    private JMethod validateMethod(JClass interfaceJClass, JClass delegateJClass, JMethod method, XmlObject loc) {
        String methodName = method.getSimpleName();
        JParameter[] params = method.getParameters();
        JClass returnType = method.getReturnType();
        JClass[] delegateParams = new JClass[params.length + 1];
        delegateParams[0] = returnType.forName("org.apache.xmlbeans.XmlObject");
        for (int i = 1; i < delegateParams.length; ++i) {
            delegateParams[i] = params[i - 1].getType();
        }
        JMethod handlerMethod = null;
        handlerMethod = InterfaceExtensionImpl.getMethod(delegateJClass, methodName, delegateParams);
        if (handlerMethod == null) {
            BindingConfigImpl.error("Handler class '" + delegateJClass.getQualifiedName() + "' does not contain method " + methodName + "(" + InterfaceExtensionImpl.listTypes(delegateParams) + ")", loc);
            return null;
        }
        JClass[] intfExceptions = method.getExceptionTypes();
        JClass[] delegateExceptions = handlerMethod.getExceptionTypes();
        if (delegateExceptions.length != intfExceptions.length) {
            BindingConfigImpl.error("Handler method '" + delegateJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(delegateParams) + ")' must declare the same exceptions as the interface method '" + interfaceJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(params), loc);
            return null;
        }
        for (int i = 0; i < delegateExceptions.length; ++i) {
            if (delegateExceptions[i] == intfExceptions[i]) continue;
            BindingConfigImpl.error("Handler method '" + delegateJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(delegateParams) + ")' must declare the same exceptions as the interface method '" + interfaceJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(params), loc);
            return null;
        }
        if (!handlerMethod.isPublic() || !handlerMethod.isStatic()) {
            BindingConfigImpl.error("Method '" + delegateJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(delegateParams) + ")' must be declared public and static.", loc);
            return null;
        }
        if (!((Object)returnType).equals(handlerMethod.getReturnType())) {
            BindingConfigImpl.error("Return type for method '" + handlerMethod.getReturnType() + " " + delegateJClass.getQualifiedName() + "." + methodName + "(" + InterfaceExtensionImpl.listTypes(delegateParams) + ")' does not match the return type of the interface method :'" + returnType + "'.", loc);
            return null;
        }
        return method;
    }

    static JMethod getMethod(JClass cls, String name, JClass[] paramTypes) {
        JMethod[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            JParameter[] mParams;
            JMethod method = methods[i];
            if (!name.equals(method.getSimpleName()) || (mParams = method.getParameters()).length != paramTypes.length) continue;
            for (int j = 0; j < mParams.length; ++j) {
                JParameter mParam = mParams[j];
                if (((Object)mParam.getType()).equals(paramTypes[j])) continue;
            }
            return method;
        }
        return null;
    }

    private static String listTypes(JClass[] types) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < types.length; ++i) {
            JClass type = types[i];
            if (i > 0) {
                result.append(", ");
            }
            result.append(InterfaceExtensionImpl.emitType(type));
        }
        return result.toString();
    }

    private static String listTypes(JParameter[] params) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < params.length; ++i) {
            JClass type = params[i].getType();
            if (i > 0) {
                result.append(", ");
            }
            result.append(InterfaceExtensionImpl.emitType(type));
        }
        return result.toString();
    }

    public static String emitType(JClass cls) {
        if (cls.isArrayType()) {
            return InterfaceExtensionImpl.emitType(cls.getArrayComponentType()) + "[]";
        }
        return cls.getQualifiedName().replace('$', '.');
    }

    public boolean contains(String fullJavaName) {
        return this._xbeanSet.contains(fullJavaName);
    }

    @Override
    public String getStaticHandler() {
        return this._delegateToClassName;
    }

    @Override
    public String getInterface() {
        return this._interfaceClassName;
    }

    @Override
    public InterfaceExtension.MethodSignature[] getMethods() {
        return this._methods;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("  static handler: ").append(this._delegateToClassName).append("\n");
        buf.append("  interface: ").append(this._interfaceClassName).append("\n");
        buf.append("  name set: ").append(this._xbeanSet).append("\n");
        for (int i = 0; i < this._methods.length; ++i) {
            buf.append("  method[").append(i).append("]=").append(this._methods[i]).append("\n");
        }
        return buf.toString();
    }

    static class MethodSignatureImpl
    implements InterfaceExtension.MethodSignature {
        private String _intfName;
        private final int NOTINITIALIZED = -1;
        private int _hashCode = -1;
        private String _signature;
        private String _name;
        private String _return;
        private String[] _params;
        private String[] _exceptions;

        MethodSignatureImpl(String intfName, JMethod method) {
            if (intfName == null || method == null) {
                throw new IllegalArgumentException("Interface: " + intfName + " method: " + method);
            }
            this._intfName = intfName;
            this._hashCode = -1;
            this._signature = null;
            this._name = method.getSimpleName();
            this._return = method.getReturnType().getQualifiedName().replace('$', '.');
            JParameter[] paramTypes = method.getParameters();
            this._params = new String[paramTypes.length];
            for (int i = 0; i < paramTypes.length; ++i) {
                this._params[i] = paramTypes[i].getType().getQualifiedName().replace('$', '.');
            }
            JClass[] exceptionTypes = method.getExceptionTypes();
            this._exceptions = new String[exceptionTypes.length];
            for (int i = 0; i < exceptionTypes.length; ++i) {
                this._exceptions[i] = exceptionTypes[i].getQualifiedName().replace('$', '.');
            }
        }

        String getInterfaceName() {
            return this._intfName;
        }

        @Override
        public String getName() {
            return this._name;
        }

        @Override
        public String getReturnType() {
            return this._return;
        }

        @Override
        public String[] getParameterTypes() {
            return this._params;
        }

        @Override
        public String[] getExceptionTypes() {
            return this._exceptions;
        }

        public boolean equals(Object o) {
            if (!(o instanceof MethodSignatureImpl)) {
                return false;
            }
            MethodSignatureImpl ms = (MethodSignatureImpl)o;
            if (!ms.getName().equals(this.getName())) {
                return false;
            }
            String[] params = this.getParameterTypes();
            String[] msParams = ms.getParameterTypes();
            if (msParams.length != params.length) {
                return false;
            }
            for (int i = 0; i < params.length; ++i) {
                if (msParams[i].equals(params[i])) continue;
                return false;
            }
            return this._intfName.equals(ms._intfName);
        }

        public int hashCode() {
            if (this._hashCode != -1) {
                return this._hashCode;
            }
            int hash = this.getName().hashCode();
            String[] params = this.getParameterTypes();
            for (int i = 0; i < params.length; ++i) {
                hash *= 19;
                hash += params[i].hashCode();
            }
            this._hashCode = hash += 21 * this._intfName.hashCode();
            return this._hashCode;
        }

        String getSignature() {
            if (this._signature != null) {
                return this._signature;
            }
            StringBuffer sb = new StringBuffer(60);
            sb.append(this._name).append("(");
            for (int i = 0; i < this._params.length; ++i) {
                sb.append(i == 0 ? "" : " ,").append(this._params[i]);
            }
            sb.append(")");
            this._signature = sb.toString();
            return this._signature;
        }

        public String toString() {
            StringBuffer buf = new StringBuffer();
            buf.append(this.getReturnType()).append(" ").append(this.getSignature());
            return buf.toString();
        }
    }
}

