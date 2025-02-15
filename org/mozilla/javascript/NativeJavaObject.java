/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.FieldAndMethods;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.InterfaceAdapter;
import org.mozilla.javascript.JavaMembers;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeDate;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

public class NativeJavaObject
implements Scriptable,
Wrapper,
Serializable {
    static final long serialVersionUID = -6948590651130498591L;
    private static final int JSTYPE_UNDEFINED = 0;
    private static final int JSTYPE_NULL = 1;
    private static final int JSTYPE_BOOLEAN = 2;
    private static final int JSTYPE_NUMBER = 3;
    private static final int JSTYPE_STRING = 4;
    private static final int JSTYPE_JAVA_CLASS = 5;
    private static final int JSTYPE_JAVA_OBJECT = 6;
    private static final int JSTYPE_JAVA_ARRAY = 7;
    private static final int JSTYPE_OBJECT = 8;
    static final byte CONVERSION_TRIVIAL = 1;
    static final byte CONVERSION_NONTRIVIAL = 0;
    static final byte CONVERSION_NONE = 99;
    protected Scriptable prototype;
    protected Scriptable parent;
    protected transient Object javaObject;
    protected transient Class<?> staticType;
    protected transient JavaMembers members;
    private transient Map<String, FieldAndMethods> fieldAndMethods;
    protected transient boolean isAdapter;
    private static final Object COERCED_INTERFACE_KEY = "Coerced Interface";
    private static Method adapter_writeAdapterObject;
    private static Method adapter_readAdapterObject;

    public NativeJavaObject() {
    }

    public NativeJavaObject(Scriptable scope, Object javaObject, Class<?> staticType) {
        this(scope, javaObject, staticType, false);
    }

    public NativeJavaObject(Scriptable scope, Object javaObject, Class<?> staticType, boolean isAdapter) {
        this.parent = scope;
        this.javaObject = javaObject;
        this.staticType = staticType;
        this.isAdapter = isAdapter;
        this.initMembers();
    }

    protected void initMembers() {
        Class<?> dynamicType = this.javaObject != null ? this.javaObject.getClass() : this.staticType;
        this.members = JavaMembers.lookupClass(this.parent, dynamicType, this.staticType, this.isAdapter);
        this.fieldAndMethods = this.members.getFieldAndMethodsObjects(this, this.javaObject, false);
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return this.members.has(name, false);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return false;
    }

    @Override
    public Object get(String name, Scriptable start) {
        FieldAndMethods result;
        if (this.fieldAndMethods != null && (result = this.fieldAndMethods.get(name)) != null) {
            return result;
        }
        return this.members.get(this, name, this.javaObject, false);
    }

    @Override
    public Object get(int index, Scriptable start) {
        throw this.members.reportMemberNotFound(Integer.toString(index));
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        if (this.prototype == null || this.members.has(name, false)) {
            this.members.put(this, name, this.javaObject, value, false);
        } else {
            this.prototype.put(name, this.prototype, value);
        }
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        throw this.members.reportMemberNotFound(Integer.toString(index));
    }

    @Override
    public boolean hasInstance(Scriptable value) {
        return false;
    }

    @Override
    public void delete(String name) {
    }

    @Override
    public void delete(int index) {
    }

    @Override
    public Scriptable getPrototype() {
        if (this.prototype == null && this.javaObject instanceof String) {
            return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(this.parent), TopLevel.Builtins.String);
        }
        return this.prototype;
    }

    @Override
    public void setPrototype(Scriptable m) {
        this.prototype = m;
    }

    @Override
    public Scriptable getParentScope() {
        return this.parent;
    }

    @Override
    public void setParentScope(Scriptable m) {
        this.parent = m;
    }

    @Override
    public Object[] getIds() {
        return this.members.getIds(false);
    }

    @Deprecated
    public static Object wrap(Scriptable scope, Object obj, Class<?> staticType) {
        Context cx = Context.getContext();
        return cx.getWrapFactory().wrap(cx, scope, obj, staticType);
    }

    @Override
    public Object unwrap() {
        return this.javaObject;
    }

    @Override
    public String getClassName() {
        return "JavaObject";
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        Object value;
        if (hint == null && this.javaObject instanceof Boolean) {
            hint = ScriptRuntime.BooleanClass;
        }
        if (hint == null || hint == ScriptRuntime.StringClass) {
            value = this.javaObject.toString();
        } else {
            String converterName;
            if (hint == ScriptRuntime.BooleanClass) {
                converterName = "booleanValue";
            } else if (hint == ScriptRuntime.NumberClass) {
                converterName = "doubleValue";
            } else {
                throw Context.reportRuntimeError0("msg.default.value");
            }
            Object converterObject = this.get(converterName, (Scriptable)this);
            if (converterObject instanceof Function) {
                Function f = (Function)converterObject;
                value = f.call(Context.getContext(), f.getParentScope(), this, ScriptRuntime.emptyArgs);
            } else {
                boolean b;
                value = hint == ScriptRuntime.NumberClass && this.javaObject instanceof Boolean ? ScriptRuntime.wrapNumber((b = ((Boolean)this.javaObject).booleanValue()) ? 1.0 : 0.0) : this.javaObject.toString();
            }
        }
        return value;
    }

    public static boolean canConvert(Object fromObj, Class<?> to) {
        int weight = NativeJavaObject.getConversionWeight(fromObj, to);
        return weight < 99;
    }

    static int getConversionWeight(Object fromObj, Class<?> to) {
        int fromCode = NativeJavaObject.getJSTypeCode(fromObj);
        switch (fromCode) {
            case 0: {
                if (to != ScriptRuntime.StringClass && to != ScriptRuntime.ObjectClass) break;
                return 1;
            }
            case 1: {
                if (to.isPrimitive()) break;
                return 1;
            }
            case 2: {
                if (to == Boolean.TYPE) {
                    return 1;
                }
                if (to == ScriptRuntime.BooleanClass) {
                    return 2;
                }
                if (to == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (to != ScriptRuntime.StringClass) break;
                return 4;
            }
            case 3: {
                if (to.isPrimitive()) {
                    if (to == Double.TYPE) {
                        return 1;
                    }
                    if (to == Boolean.TYPE) break;
                    return 1 + NativeJavaObject.getSizeRank(to);
                }
                if (to == ScriptRuntime.StringClass) {
                    return 9;
                }
                if (to == ScriptRuntime.ObjectClass) {
                    return 10;
                }
                if (!ScriptRuntime.NumberClass.isAssignableFrom(to)) break;
                return 2;
            }
            case 4: {
                if (to == ScriptRuntime.StringClass) {
                    return 1;
                }
                if (to.isInstance(fromObj)) {
                    return 2;
                }
                if (!to.isPrimitive()) break;
                if (to == Character.TYPE) {
                    return 3;
                }
                if (to == Boolean.TYPE) break;
                return 4;
            }
            case 5: {
                if (to == ScriptRuntime.ClassClass) {
                    return 1;
                }
                if (to == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (to != ScriptRuntime.StringClass) break;
                return 4;
            }
            case 6: 
            case 7: {
                Object javaObj = fromObj;
                if (javaObj instanceof Wrapper) {
                    javaObj = ((Wrapper)javaObj).unwrap();
                }
                if (to.isInstance(javaObj)) {
                    return 0;
                }
                if (to == ScriptRuntime.StringClass) {
                    return 2;
                }
                if (!to.isPrimitive() || to == Boolean.TYPE) break;
                return fromCode == 7 ? 99 : 2 + NativeJavaObject.getSizeRank(to);
            }
            case 8: {
                if (to != ScriptRuntime.ObjectClass && to.isInstance(fromObj)) {
                    return 1;
                }
                if (to.isArray()) {
                    if (!(fromObj instanceof NativeArray)) break;
                    return 2;
                }
                if (to == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (to == ScriptRuntime.StringClass) {
                    return 4;
                }
                if (to == ScriptRuntime.DateClass) {
                    if (!(fromObj instanceof NativeDate)) break;
                    return 1;
                }
                if (to.isInterface()) {
                    if (fromObj instanceof NativeObject || fromObj instanceof NativeFunction) {
                        return 1;
                    }
                    return 12;
                }
                if (!to.isPrimitive() || to == Boolean.TYPE) break;
                return 4 + NativeJavaObject.getSizeRank(to);
            }
        }
        return 99;
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
            return 99;
        }
        return 8;
    }

    private static int getJSTypeCode(Object value) {
        if (value == null) {
            return 1;
        }
        if (value == Undefined.instance) {
            return 0;
        }
        if (value instanceof CharSequence) {
            return 4;
        }
        if (value instanceof Number) {
            return 3;
        }
        if (value instanceof Boolean) {
            return 2;
        }
        if (value instanceof Scriptable) {
            if (value instanceof NativeJavaClass) {
                return 5;
            }
            if (value instanceof NativeJavaArray) {
                return 7;
            }
            if (value instanceof Wrapper) {
                return 6;
            }
            return 8;
        }
        if (value instanceof Class) {
            return 5;
        }
        Class<?> valueClass = value.getClass();
        if (valueClass.isArray()) {
            return 7;
        }
        return 6;
    }

    @Deprecated
    public static Object coerceType(Class<?> type, Object value) {
        return NativeJavaObject.coerceTypeImpl(type, value);
    }

    static Object coerceTypeImpl(Class<?> type, Object value) {
        if (value != null && value.getClass() == type) {
            return value;
        }
        switch (NativeJavaObject.getJSTypeCode(value)) {
            case 1: {
                if (type.isPrimitive()) {
                    NativeJavaObject.reportConversionError(value, type);
                }
                return null;
            }
            case 0: {
                if (type == ScriptRuntime.StringClass || type == ScriptRuntime.ObjectClass) {
                    return "undefined";
                }
                NativeJavaObject.reportConversionError("undefined", type);
                break;
            }
            case 2: {
                if (type == Boolean.TYPE || type == ScriptRuntime.BooleanClass || type == ScriptRuntime.ObjectClass) {
                    return value;
                }
                if (type == ScriptRuntime.StringClass) {
                    return value.toString();
                }
                NativeJavaObject.reportConversionError(value, type);
                break;
            }
            case 3: {
                if (type == ScriptRuntime.StringClass) {
                    return ScriptRuntime.toString(value);
                }
                if (type == ScriptRuntime.ObjectClass) {
                    return NativeJavaObject.coerceToNumber(Double.TYPE, value);
                }
                if (type.isPrimitive() && type != Boolean.TYPE || ScriptRuntime.NumberClass.isAssignableFrom(type)) {
                    return NativeJavaObject.coerceToNumber(type, value);
                }
                NativeJavaObject.reportConversionError(value, type);
                break;
            }
            case 4: {
                if (type == ScriptRuntime.StringClass || type.isInstance(value)) {
                    return value.toString();
                }
                if (type == Character.TYPE || type == ScriptRuntime.CharacterClass) {
                    if (((CharSequence)value).length() == 1) {
                        return Character.valueOf(((CharSequence)value).charAt(0));
                    }
                    return NativeJavaObject.coerceToNumber(type, value);
                }
                if (type.isPrimitive() && type != Boolean.TYPE || ScriptRuntime.NumberClass.isAssignableFrom(type)) {
                    return NativeJavaObject.coerceToNumber(type, value);
                }
                NativeJavaObject.reportConversionError(value, type);
                break;
            }
            case 5: {
                if (value instanceof Wrapper) {
                    value = ((Wrapper)value).unwrap();
                }
                if (type == ScriptRuntime.ClassClass || type == ScriptRuntime.ObjectClass) {
                    return value;
                }
                if (type == ScriptRuntime.StringClass) {
                    return value.toString();
                }
                NativeJavaObject.reportConversionError(value, type);
                break;
            }
            case 6: 
            case 7: {
                if (value instanceof Wrapper) {
                    value = ((Wrapper)value).unwrap();
                }
                if (type.isPrimitive()) {
                    if (type == Boolean.TYPE) {
                        NativeJavaObject.reportConversionError(value, type);
                    }
                    return NativeJavaObject.coerceToNumber(type, value);
                }
                if (type == ScriptRuntime.StringClass) {
                    return value.toString();
                }
                if (type.isInstance(value)) {
                    return value;
                }
                NativeJavaObject.reportConversionError(value, type);
                break;
            }
            case 8: {
                if (type == ScriptRuntime.StringClass) {
                    return ScriptRuntime.toString(value);
                }
                if (type.isPrimitive()) {
                    if (type == Boolean.TYPE) {
                        NativeJavaObject.reportConversionError(value, type);
                    }
                    return NativeJavaObject.coerceToNumber(type, value);
                }
                if (type.isInstance(value)) {
                    return value;
                }
                if (type == ScriptRuntime.DateClass && value instanceof NativeDate) {
                    double time = ((NativeDate)value).getJSTimeValue();
                    return new Date((long)time);
                }
                if (type.isArray() && value instanceof NativeArray) {
                    NativeArray array = (NativeArray)value;
                    long length = array.getLength();
                    Class<?> arrayType = type.getComponentType();
                    Object Result2 = Array.newInstance(arrayType, (int)length);
                    int i = 0;
                    while ((long)i < length) {
                        try {
                            Array.set(Result2, i, NativeJavaObject.coerceTypeImpl(arrayType, array.get(i, (Scriptable)array)));
                        } catch (EvaluatorException ee) {
                            NativeJavaObject.reportConversionError(value, type);
                        }
                        ++i;
                    }
                    return Result2;
                }
                if (value instanceof Wrapper) {
                    if (type.isInstance(value = ((Wrapper)value).unwrap())) {
                        return value;
                    }
                    NativeJavaObject.reportConversionError(value, type);
                    break;
                }
                if (type.isInterface() && (value instanceof NativeObject || value instanceof NativeFunction)) {
                    return NativeJavaObject.createInterfaceAdapter(type, (ScriptableObject)value);
                }
                NativeJavaObject.reportConversionError(value, type);
            }
        }
        return value;
    }

    protected static Object createInterfaceAdapter(Class<?> type, ScriptableObject so) {
        Object key = Kit.makeHashKeyFromPair(COERCED_INTERFACE_KEY, type);
        Object old = so.getAssociatedValue(key);
        if (old != null) {
            return old;
        }
        Context cx = Context.getContext();
        Object glue = InterfaceAdapter.create(cx, type, so);
        glue = so.associateValue(key, glue);
        return glue;
    }

    private static Object coerceToNumber(Class<?> type, Object value) {
        Class<?> valueClass = value.getClass();
        if (type == Character.TYPE || type == ScriptRuntime.CharacterClass) {
            if (valueClass == ScriptRuntime.CharacterClass) {
                return value;
            }
            return Character.valueOf((char)NativeJavaObject.toInteger(value, ScriptRuntime.CharacterClass, 0.0, 65535.0));
        }
        if (type == ScriptRuntime.ObjectClass || type == ScriptRuntime.DoubleClass || type == Double.TYPE) {
            return valueClass == ScriptRuntime.DoubleClass ? value : new Double(NativeJavaObject.toDouble(value));
        }
        if (type == ScriptRuntime.FloatClass || type == Float.TYPE) {
            if (valueClass == ScriptRuntime.FloatClass) {
                return value;
            }
            double number = NativeJavaObject.toDouble(value);
            if (Double.isInfinite(number) || Double.isNaN(number) || number == 0.0) {
                return new Float((float)number);
            }
            double absNumber = Math.abs(number);
            if (absNumber < (double)1.4E-45f) {
                return new Float(number > 0.0 ? 0.0 : -0.0);
            }
            if (absNumber > 3.4028234663852886E38) {
                return new Float(number > 0.0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);
            }
            return new Float((float)number);
        }
        if (type == ScriptRuntime.IntegerClass || type == Integer.TYPE) {
            if (valueClass == ScriptRuntime.IntegerClass) {
                return value;
            }
            return (int)NativeJavaObject.toInteger(value, ScriptRuntime.IntegerClass, -2.147483648E9, 2.147483647E9);
        }
        if (type == ScriptRuntime.LongClass || type == Long.TYPE) {
            if (valueClass == ScriptRuntime.LongClass) {
                return value;
            }
            double max = Double.longBitsToDouble(4890909195324358655L);
            double min = Double.longBitsToDouble(-4332462841530417152L);
            return NativeJavaObject.toInteger(value, ScriptRuntime.LongClass, min, max);
        }
        if (type == ScriptRuntime.ShortClass || type == Short.TYPE) {
            if (valueClass == ScriptRuntime.ShortClass) {
                return value;
            }
            return (short)NativeJavaObject.toInteger(value, ScriptRuntime.ShortClass, -32768.0, 32767.0);
        }
        if (type == ScriptRuntime.ByteClass || type == Byte.TYPE) {
            if (valueClass == ScriptRuntime.ByteClass) {
                return value;
            }
            return (byte)NativeJavaObject.toInteger(value, ScriptRuntime.ByteClass, -128.0, 127.0);
        }
        return new Double(NativeJavaObject.toDouble(value));
    }

    private static double toDouble(Object value) {
        Method meth;
        if (value instanceof Number) {
            return ((Number)value).doubleValue();
        }
        if (value instanceof String) {
            return ScriptRuntime.toNumber((String)value);
        }
        if (value instanceof Scriptable) {
            if (value instanceof Wrapper) {
                return NativeJavaObject.toDouble(((Wrapper)value).unwrap());
            }
            return ScriptRuntime.toNumber(value);
        }
        try {
            meth = value.getClass().getMethod("doubleValue", null);
        } catch (NoSuchMethodException e) {
            meth = null;
        } catch (SecurityException e) {
            meth = null;
        }
        if (meth != null) {
            try {
                return ((Number)meth.invoke(value, null)).doubleValue();
            } catch (IllegalAccessException e) {
                NativeJavaObject.reportConversionError(value, Double.TYPE);
            } catch (InvocationTargetException e) {
                NativeJavaObject.reportConversionError(value, Double.TYPE);
            }
        }
        return ScriptRuntime.toNumber(value.toString());
    }

    private static long toInteger(Object value, Class<?> type, double min, double max) {
        double d = NativeJavaObject.toDouble(value);
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            NativeJavaObject.reportConversionError(ScriptRuntime.toString(value), type);
        }
        if ((d = d > 0.0 ? Math.floor(d) : Math.ceil(d)) < min || d > max) {
            NativeJavaObject.reportConversionError(ScriptRuntime.toString(value), type);
        }
        return (long)d;
    }

    static void reportConversionError(Object value, Class<?> type) {
        throw Context.reportRuntimeError2("msg.conversion.not.allowed", String.valueOf(value), JavaMembers.javaSignature(type));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeBoolean(this.isAdapter);
        if (this.isAdapter) {
            if (adapter_writeAdapterObject == null) {
                throw new IOException();
            }
            Object[] args = new Object[]{this.javaObject, out};
            try {
                adapter_writeAdapterObject.invoke(null, args);
            } catch (Exception ex) {
                throw new IOException();
            }
        } else {
            out.writeObject(this.javaObject);
        }
        if (this.staticType != null) {
            out.writeObject(this.staticType.getClass().getName());
        } else {
            out.writeObject(null);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String className;
        in.defaultReadObject();
        this.isAdapter = in.readBoolean();
        if (this.isAdapter) {
            if (adapter_readAdapterObject == null) {
                throw new ClassNotFoundException();
            }
            Object[] args = new Object[]{this, in};
            try {
                this.javaObject = adapter_readAdapterObject.invoke(null, args);
            } catch (Exception ex) {
                throw new IOException();
            }
        } else {
            this.javaObject = in.readObject();
        }
        this.staticType = (className = (String)in.readObject()) != null ? Class.forName(className) : null;
        this.initMembers();
    }

    static {
        Class[] sig2 = new Class[2];
        Class<?> cl = Kit.classOrNull("org.mozilla.javascript.JavaAdapter");
        if (cl != null) {
            try {
                sig2[0] = ScriptRuntime.ObjectClass;
                sig2[1] = Kit.classOrNull("java.io.ObjectOutputStream");
                adapter_writeAdapterObject = cl.getMethod("writeAdapterObject", sig2);
                sig2[0] = ScriptRuntime.ScriptableClass;
                sig2[1] = Kit.classOrNull("java.io.ObjectInputStream");
                adapter_readAdapterObject = cl.getMethod("readAdapterObject", sig2);
            } catch (NoSuchMethodException e) {
                adapter_writeAdapterObject = null;
                adapter_readAdapterObject = null;
            }
        }
    }
}

