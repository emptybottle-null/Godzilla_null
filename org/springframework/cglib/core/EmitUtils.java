/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.cglib.core.Block;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.Customizer;
import org.springframework.cglib.core.HashCodeCustomizer;
import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ObjectSwitchCallback;
import org.springframework.cglib.core.ProcessArrayCallback;
import org.springframework.cglib.core.ProcessSwitchCallback;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.Transformer;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.core.internal.CustomizerRegistry;

public class EmitUtils {
    private static final Signature CSTRUCT_NULL = TypeUtils.parseConstructor("");
    private static final Signature CSTRUCT_THROWABLE = TypeUtils.parseConstructor("Throwable");
    private static final Signature GET_NAME = TypeUtils.parseSignature("String getName()");
    private static final Signature HASH_CODE = TypeUtils.parseSignature("int hashCode()");
    private static final Signature EQUALS = TypeUtils.parseSignature("boolean equals(Object)");
    private static final Signature STRING_LENGTH = TypeUtils.parseSignature("int length()");
    private static final Signature STRING_CHAR_AT = TypeUtils.parseSignature("char charAt(int)");
    private static final Signature FOR_NAME = TypeUtils.parseSignature("Class forName(String)");
    private static final Signature DOUBLE_TO_LONG_BITS = TypeUtils.parseSignature("long doubleToLongBits(double)");
    private static final Signature FLOAT_TO_INT_BITS = TypeUtils.parseSignature("int floatToIntBits(float)");
    private static final Signature TO_STRING = TypeUtils.parseSignature("String toString()");
    private static final Signature APPEND_STRING = TypeUtils.parseSignature("StringBuffer append(String)");
    private static final Signature APPEND_INT = TypeUtils.parseSignature("StringBuffer append(int)");
    private static final Signature APPEND_DOUBLE = TypeUtils.parseSignature("StringBuffer append(double)");
    private static final Signature APPEND_FLOAT = TypeUtils.parseSignature("StringBuffer append(float)");
    private static final Signature APPEND_CHAR = TypeUtils.parseSignature("StringBuffer append(char)");
    private static final Signature APPEND_LONG = TypeUtils.parseSignature("StringBuffer append(long)");
    private static final Signature APPEND_BOOLEAN = TypeUtils.parseSignature("StringBuffer append(boolean)");
    private static final Signature LENGTH = TypeUtils.parseSignature("int length()");
    private static final Signature SET_LENGTH = TypeUtils.parseSignature("void setLength(int)");
    private static final Signature GET_DECLARED_METHOD = TypeUtils.parseSignature("java.lang.reflect.Method getDeclaredMethod(String, Class[])");
    public static final ArrayDelimiters DEFAULT_DELIMITERS = new ArrayDelimiters("{", ", ", "}");

    private EmitUtils() {
    }

    public static void factory_method(ClassEmitter ce, Signature sig) {
        CodeEmitter e = ce.begin_method(1, sig, null);
        e.new_instance_this();
        e.dup();
        e.load_args();
        e.invoke_constructor_this(TypeUtils.parseConstructor(sig.getArgumentTypes()));
        e.return_value();
        e.end_method();
    }

    public static void null_constructor(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(1, CSTRUCT_NULL, null);
        e.load_this();
        e.super_invoke_constructor();
        e.return_value();
        e.end_method();
    }

    public static void process_array(CodeEmitter e, Type type, ProcessArrayCallback callback) {
        Type componentType = TypeUtils.getComponentType(type);
        Local array = e.make_local();
        Local loopvar = e.make_local(Type.INT_TYPE);
        Label loopbody = e.make_label();
        Label checkloop = e.make_label();
        e.store_local(array);
        e.push(0);
        e.store_local(loopvar);
        e.goTo(checkloop);
        e.mark(loopbody);
        e.load_local(array);
        e.load_local(loopvar);
        e.array_load(componentType);
        callback.processElement(componentType);
        e.iinc(loopvar, 1);
        e.mark(checkloop);
        e.load_local(loopvar);
        e.load_local(array);
        e.arraylength();
        e.if_icmp(155, loopbody);
    }

    public static void process_arrays(CodeEmitter e, Type type, ProcessArrayCallback callback) {
        Type componentType = TypeUtils.getComponentType(type);
        Local array1 = e.make_local();
        Local array2 = e.make_local();
        Local loopvar = e.make_local(Type.INT_TYPE);
        Label loopbody = e.make_label();
        Label checkloop = e.make_label();
        e.store_local(array1);
        e.store_local(array2);
        e.push(0);
        e.store_local(loopvar);
        e.goTo(checkloop);
        e.mark(loopbody);
        e.load_local(array1);
        e.load_local(loopvar);
        e.array_load(componentType);
        e.load_local(array2);
        e.load_local(loopvar);
        e.array_load(componentType);
        callback.processElement(componentType);
        e.iinc(loopvar, 1);
        e.mark(checkloop);
        e.load_local(loopvar);
        e.load_local(array1);
        e.arraylength();
        e.if_icmp(155, loopbody);
    }

    public static void string_switch(CodeEmitter e, String[] strings, int switchStyle, ObjectSwitchCallback callback) {
        try {
            switch (switchStyle) {
                case 0: {
                    EmitUtils.string_switch_trie(e, strings, callback);
                    break;
                }
                case 1: {
                    EmitUtils.string_switch_hash(e, strings, callback, false);
                    break;
                }
                case 2: {
                    EmitUtils.string_switch_hash(e, strings, callback, true);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("unknown switch style " + switchStyle);
                }
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodeGenerationException(ex);
        }
    }

    private static void string_switch_trie(final CodeEmitter e, String[] strings, final ObjectSwitchCallback callback) throws Exception {
        final Label def = e.make_label();
        final Label end = e.make_label();
        final Map buckets = CollectionUtils.bucket(Arrays.asList(strings), new Transformer(){

            public Object transform(Object value) {
                return new Integer(((String)value).length());
            }
        });
        e.dup();
        e.invoke_virtual(Constants.TYPE_STRING, STRING_LENGTH);
        e.process_switch(EmitUtils.getSwitchKeys(buckets), new ProcessSwitchCallback(){

            public void processCase(int key, Label ignore_end) throws Exception {
                List bucket = (List)buckets.get(new Integer(key));
                EmitUtils.stringSwitchHelper(e, bucket, callback, def, end, 0);
            }

            public void processDefault() {
                e.goTo(def);
            }
        });
        e.mark(def);
        e.pop();
        callback.processDefault();
        e.mark(end);
    }

    private static void stringSwitchHelper(final CodeEmitter e, List strings, final ObjectSwitchCallback callback, final Label def, final Label end, final int index) throws Exception {
        final int len = ((String)strings.get(0)).length();
        final Map buckets = CollectionUtils.bucket(strings, new Transformer(){

            public Object transform(Object value) {
                return new Integer(((String)value).charAt(index));
            }
        });
        e.dup();
        e.push(index);
        e.invoke_virtual(Constants.TYPE_STRING, STRING_CHAR_AT);
        e.process_switch(EmitUtils.getSwitchKeys(buckets), new ProcessSwitchCallback(){

            public void processCase(int key, Label ignore_end) throws Exception {
                List bucket = (List)buckets.get(new Integer(key));
                if (index + 1 == len) {
                    e.pop();
                    callback.processCase(bucket.get(0), end);
                } else {
                    EmitUtils.stringSwitchHelper(e, bucket, callback, def, end, index + 1);
                }
            }

            public void processDefault() {
                e.goTo(def);
            }
        });
    }

    static int[] getSwitchKeys(Map buckets) {
        int[] keys = new int[buckets.size()];
        int index = 0;
        Iterator it = buckets.keySet().iterator();
        while (it.hasNext()) {
            keys[index++] = (Integer)it.next();
        }
        Arrays.sort(keys);
        return keys;
    }

    private static void string_switch_hash(final CodeEmitter e, String[] strings, final ObjectSwitchCallback callback, final boolean skipEquals) throws Exception {
        final Map buckets = CollectionUtils.bucket(Arrays.asList(strings), new Transformer(){

            public Object transform(Object value) {
                return new Integer(value.hashCode());
            }
        });
        final Label def = e.make_label();
        final Label end = e.make_label();
        e.dup();
        e.invoke_virtual(Constants.TYPE_OBJECT, HASH_CODE);
        e.process_switch(EmitUtils.getSwitchKeys(buckets), new ProcessSwitchCallback(){

            public void processCase(int key, Label ignore_end) throws Exception {
                List bucket = (List)buckets.get(new Integer(key));
                Label next = null;
                if (skipEquals && bucket.size() == 1) {
                    if (skipEquals) {
                        e.pop();
                    }
                    callback.processCase((String)bucket.get(0), end);
                } else {
                    Iterator it = bucket.iterator();
                    while (it.hasNext()) {
                        String string = (String)it.next();
                        if (next != null) {
                            e.mark(next);
                        }
                        if (it.hasNext()) {
                            e.dup();
                        }
                        e.push(string);
                        e.invoke_virtual(Constants.TYPE_OBJECT, EQUALS);
                        if (it.hasNext()) {
                            next = e.make_label();
                            e.if_jump(153, next);
                            e.pop();
                        } else {
                            e.if_jump(153, def);
                        }
                        callback.processCase(string, end);
                    }
                }
            }

            public void processDefault() {
                e.pop();
            }
        });
        e.mark(def);
        callback.processDefault();
        e.mark(end);
    }

    public static void load_class_this(CodeEmitter e) {
        EmitUtils.load_class_helper(e, e.getClassEmitter().getClassType());
    }

    public static void load_class(CodeEmitter e, Type type) {
        if (TypeUtils.isPrimitive(type)) {
            if (type == Type.VOID_TYPE) {
                throw new IllegalArgumentException("cannot load void type");
            }
            e.getstatic(TypeUtils.getBoxedType(type), "TYPE", Constants.TYPE_CLASS);
        } else {
            EmitUtils.load_class_helper(e, type);
        }
    }

    private static void load_class_helper(CodeEmitter e, Type type) {
        if (e.isStaticHook()) {
            e.push(TypeUtils.emulateClassGetName(type));
            e.invoke_static(Constants.TYPE_CLASS, FOR_NAME);
        } else {
            ClassEmitter ce = e.getClassEmitter();
            String typeName = TypeUtils.emulateClassGetName(type);
            String fieldName = "CGLIB$load_class$" + TypeUtils.escapeType(typeName);
            if (!ce.isFieldDeclared(fieldName)) {
                ce.declare_field(26, fieldName, Constants.TYPE_CLASS, null);
                CodeEmitter hook = ce.getStaticHook();
                hook.push(typeName);
                hook.invoke_static(Constants.TYPE_CLASS, FOR_NAME);
                hook.putstatic(ce.getClassType(), fieldName, Constants.TYPE_CLASS);
            }
            e.getfield(fieldName);
        }
    }

    public static void push_array(CodeEmitter e, Object[] array) {
        e.push(array.length);
        e.newarray(Type.getType(EmitUtils.remapComponentType(array.getClass().getComponentType())));
        for (int i = 0; i < array.length; ++i) {
            e.dup();
            e.push(i);
            EmitUtils.push_object(e, array[i]);
            e.aastore();
        }
    }

    private static Class remapComponentType(Class componentType) {
        if (componentType.equals(Type.class)) {
            return Class.class;
        }
        return componentType;
    }

    public static void push_object(CodeEmitter e, Object obj) {
        if (obj == null) {
            e.aconst_null();
        } else {
            Class<?> type = obj.getClass();
            if (type.isArray()) {
                EmitUtils.push_array(e, (Object[])obj);
            } else if (obj instanceof String) {
                e.push((String)obj);
            } else if (obj instanceof Type) {
                EmitUtils.load_class(e, (Type)obj);
            } else if (obj instanceof Class) {
                EmitUtils.load_class(e, Type.getType((Class)obj));
            } else if (obj instanceof BigInteger) {
                e.new_instance(Constants.TYPE_BIG_INTEGER);
                e.dup();
                e.push(obj.toString());
                e.invoke_constructor(Constants.TYPE_BIG_INTEGER);
            } else if (obj instanceof BigDecimal) {
                e.new_instance(Constants.TYPE_BIG_DECIMAL);
                e.dup();
                e.push(obj.toString());
                e.invoke_constructor(Constants.TYPE_BIG_DECIMAL);
            } else {
                throw new IllegalArgumentException("unknown type: " + obj.getClass());
            }
        }
    }

    @Deprecated
    public static void hash_code(CodeEmitter e, Type type, int multiplier, Customizer customizer) {
        EmitUtils.hash_code(e, type, multiplier, CustomizerRegistry.singleton(customizer));
    }

    public static void hash_code(CodeEmitter e, Type type, int multiplier, CustomizerRegistry registry) {
        if (TypeUtils.isArray(type)) {
            EmitUtils.hash_array(e, type, multiplier, registry);
        } else {
            e.swap(Type.INT_TYPE, type);
            e.push(multiplier);
            e.math(104, Type.INT_TYPE);
            e.swap(type, Type.INT_TYPE);
            if (TypeUtils.isPrimitive(type)) {
                EmitUtils.hash_primitive(e, type);
            } else {
                EmitUtils.hash_object(e, type, registry);
            }
            e.math(96, Type.INT_TYPE);
        }
    }

    private static void hash_array(final CodeEmitter e, Type type, final int multiplier, final CustomizerRegistry registry) {
        Label skip = e.make_label();
        Label end = e.make_label();
        e.dup();
        e.ifnull(skip);
        EmitUtils.process_array(e, type, new ProcessArrayCallback(){

            public void processElement(Type type) {
                EmitUtils.hash_code(e, type, multiplier, registry);
            }
        });
        e.goTo(end);
        e.mark(skip);
        e.pop();
        e.mark(end);
    }

    private static void hash_object(CodeEmitter e, Type type, CustomizerRegistry registry) {
        Label skip = e.make_label();
        Label end = e.make_label();
        e.dup();
        e.ifnull(skip);
        boolean customHashCode = false;
        for (HashCodeCustomizer hashCodeCustomizer : registry.get(HashCodeCustomizer.class)) {
            if (!hashCodeCustomizer.customize(e, type)) continue;
            customHashCode = true;
            break;
        }
        if (!customHashCode) {
            for (Customizer customizer : registry.get(Customizer.class)) {
                customizer.customize(e, type);
            }
            e.invoke_virtual(Constants.TYPE_OBJECT, HASH_CODE);
        }
        e.goTo(end);
        e.mark(skip);
        e.pop();
        e.push(0);
        e.mark(end);
    }

    private static void hash_primitive(CodeEmitter e, Type type) {
        switch (type.getSort()) {
            case 1: {
                e.push(1);
                e.math(130, Type.INT_TYPE);
                break;
            }
            case 6: {
                e.invoke_static(Constants.TYPE_FLOAT, FLOAT_TO_INT_BITS);
                break;
            }
            case 8: {
                e.invoke_static(Constants.TYPE_DOUBLE, DOUBLE_TO_LONG_BITS);
            }
            case 7: {
                EmitUtils.hash_long(e);
            }
        }
    }

    private static void hash_long(CodeEmitter e) {
        e.dup2();
        e.push(32);
        e.math(124, Type.LONG_TYPE);
        e.math(130, Type.LONG_TYPE);
        e.cast_numeric(Type.LONG_TYPE, Type.INT_TYPE);
    }

    @Deprecated
    public static void not_equals(CodeEmitter e, Type type, Label notEquals, Customizer customizer) {
        EmitUtils.not_equals(e, type, notEquals, CustomizerRegistry.singleton(customizer));
    }

    public static void not_equals(final CodeEmitter e, Type type, final Label notEquals, final CustomizerRegistry registry) {
        new ProcessArrayCallback(){

            public void processElement(Type type) {
                EmitUtils.not_equals_helper(e, type, notEquals, registry, this);
            }
        }.processElement(type);
    }

    private static void not_equals_helper(CodeEmitter e, Type type, Label notEquals, CustomizerRegistry registry, ProcessArrayCallback callback) {
        if (TypeUtils.isPrimitive(type)) {
            e.if_cmp(type, 154, notEquals);
        } else {
            Label end = e.make_label();
            EmitUtils.nullcmp(e, notEquals, end);
            if (TypeUtils.isArray(type)) {
                Label checkContents = e.make_label();
                e.dup2();
                e.arraylength();
                e.swap();
                e.arraylength();
                e.if_icmp(153, checkContents);
                e.pop2();
                e.goTo(notEquals);
                e.mark(checkContents);
                EmitUtils.process_arrays(e, type, callback);
            } else {
                List<Customizer> customizers = registry.get(Customizer.class);
                if (!customizers.isEmpty()) {
                    for (Customizer customizer : customizers) {
                        customizer.customize(e, type);
                    }
                    e.swap();
                    for (Customizer customizer : customizers) {
                        customizer.customize(e, type);
                    }
                }
                e.invoke_virtual(Constants.TYPE_OBJECT, EQUALS);
                e.if_jump(153, notEquals);
            }
            e.mark(end);
        }
    }

    private static void nullcmp(CodeEmitter e, Label oneNull, Label bothNull) {
        e.dup2();
        Label nonNull = e.make_label();
        Label oneNullHelper = e.make_label();
        Label end = e.make_label();
        e.ifnonnull(nonNull);
        e.ifnonnull(oneNullHelper);
        e.pop2();
        e.goTo(bothNull);
        e.mark(nonNull);
        e.ifnull(oneNullHelper);
        e.goTo(end);
        e.mark(oneNullHelper);
        e.pop2();
        e.goTo(oneNull);
        e.mark(end);
    }

    @Deprecated
    public static void append_string(CodeEmitter e, Type type, ArrayDelimiters delims, Customizer customizer) {
        EmitUtils.append_string(e, type, delims, CustomizerRegistry.singleton(customizer));
    }

    public static void append_string(final CodeEmitter e, Type type, ArrayDelimiters delims, final CustomizerRegistry registry) {
        final ArrayDelimiters d = delims != null ? delims : DEFAULT_DELIMITERS;
        ProcessArrayCallback callback = new ProcessArrayCallback(){

            public void processElement(Type type) {
                EmitUtils.append_string_helper(e, type, d, registry, this);
                e.push(d.inside);
                e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
            }
        };
        EmitUtils.append_string_helper(e, type, d, registry, callback);
    }

    private static void append_string_helper(CodeEmitter e, Type type, ArrayDelimiters delims, CustomizerRegistry registry, ProcessArrayCallback callback) {
        Label skip = e.make_label();
        Label end = e.make_label();
        if (TypeUtils.isPrimitive(type)) {
            switch (type.getSort()) {
                case 3: 
                case 4: 
                case 5: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_INT);
                    break;
                }
                case 8: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_DOUBLE);
                    break;
                }
                case 6: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_FLOAT);
                    break;
                }
                case 7: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_LONG);
                    break;
                }
                case 1: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_BOOLEAN);
                    break;
                }
                case 2: {
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_CHAR);
                }
            }
        } else if (TypeUtils.isArray(type)) {
            e.dup();
            e.ifnull(skip);
            e.swap();
            if (delims != null && delims.before != null && !"".equals(delims.before)) {
                e.push(delims.before);
                e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
                e.swap();
            }
            EmitUtils.process_array(e, type, callback);
            EmitUtils.shrinkStringBuffer(e, 2);
            if (delims != null && delims.after != null && !"".equals(delims.after)) {
                e.push(delims.after);
                e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
            }
        } else {
            e.dup();
            e.ifnull(skip);
            for (Customizer customizer : registry.get(Customizer.class)) {
                customizer.customize(e, type);
            }
            e.invoke_virtual(Constants.TYPE_OBJECT, TO_STRING);
            e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
        }
        e.goTo(end);
        e.mark(skip);
        e.pop();
        e.push("null");
        e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
        e.mark(end);
    }

    private static void shrinkStringBuffer(CodeEmitter e, int amt) {
        e.dup();
        e.dup();
        e.invoke_virtual(Constants.TYPE_STRING_BUFFER, LENGTH);
        e.push(amt);
        e.math(100, Type.INT_TYPE);
        e.invoke_virtual(Constants.TYPE_STRING_BUFFER, SET_LENGTH);
    }

    public static void load_method(CodeEmitter e, MethodInfo method) {
        EmitUtils.load_class(e, method.getClassInfo().getType());
        e.push(method.getSignature().getName());
        EmitUtils.push_object(e, method.getSignature().getArgumentTypes());
        e.invoke_virtual(Constants.TYPE_CLASS, GET_DECLARED_METHOD);
    }

    public static void method_switch(CodeEmitter e, List methods, ObjectSwitchCallback callback) {
        EmitUtils.member_switch_helper(e, methods, callback, true);
    }

    public static void constructor_switch(CodeEmitter e, List constructors, ObjectSwitchCallback callback) {
        EmitUtils.member_switch_helper(e, constructors, callback, false);
    }

    private static void member_switch_helper(final CodeEmitter e, List members, final ObjectSwitchCallback callback, boolean useName) {
        try {
            final HashMap cache = new HashMap();
            final ParameterTyper cached = new ParameterTyper(){

                public Type[] getParameterTypes(MethodInfo member) {
                    Type[] types = (Type[])cache.get(member);
                    if (types == null) {
                        types = member.getSignature().getArgumentTypes();
                        cache.put(member, types);
                    }
                    return types;
                }
            };
            final Label def = e.make_label();
            final Label end = e.make_label();
            if (useName) {
                e.swap();
                final Map buckets = CollectionUtils.bucket(members, new Transformer(){

                    public Object transform(Object value) {
                        return ((MethodInfo)value).getSignature().getName();
                    }
                });
                String[] names = buckets.keySet().toArray(new String[buckets.size()]);
                EmitUtils.string_switch(e, names, 1, new ObjectSwitchCallback(){

                    public void processCase(Object key, Label dontUseEnd) throws Exception {
                        EmitUtils.member_helper_size(e, (List)buckets.get(key), callback, cached, def, end);
                    }

                    public void processDefault() throws Exception {
                        e.goTo(def);
                    }
                });
            } else {
                EmitUtils.member_helper_size(e, members, callback, cached, def, end);
            }
            e.mark(def);
            e.pop();
            callback.processDefault();
            e.mark(end);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodeGenerationException(ex);
        }
    }

    private static void member_helper_size(final CodeEmitter e, List members, final ObjectSwitchCallback callback, final ParameterTyper typer, final Label def, final Label end) throws Exception {
        final Map buckets = CollectionUtils.bucket(members, new Transformer(){

            public Object transform(Object value) {
                return new Integer(typer.getParameterTypes((MethodInfo)value).length);
            }
        });
        e.dup();
        e.arraylength();
        e.process_switch(EmitUtils.getSwitchKeys(buckets), new ProcessSwitchCallback(){

            public void processCase(int key, Label dontUseEnd) throws Exception {
                List bucket = (List)buckets.get(new Integer(key));
                EmitUtils.member_helper_type(e, bucket, callback, typer, def, end, new BitSet());
            }

            public void processDefault() throws Exception {
                e.goTo(def);
            }
        });
    }

    private static void member_helper_type(final CodeEmitter e, List members, final ObjectSwitchCallback callback, final ParameterTyper typer, final Label def, final Label end, final BitSet checked) throws Exception {
        if (members.size() == 1) {
            MethodInfo member = (MethodInfo)members.get(0);
            Type[] types = typer.getParameterTypes(member);
            for (int i = 0; i < types.length; ++i) {
                if (checked != null && checked.get(i)) continue;
                e.dup();
                e.aaload(i);
                e.invoke_virtual(Constants.TYPE_CLASS, GET_NAME);
                e.push(TypeUtils.emulateClassGetName(types[i]));
                e.invoke_virtual(Constants.TYPE_OBJECT, EQUALS);
                e.if_jump(153, def);
            }
            e.pop();
            callback.processCase(member, end);
        } else {
            Type[] example = typer.getParameterTypes((MethodInfo)members.get(0));
            Map buckets = null;
            int index = -1;
            for (int i = 0; i < example.length; ++i) {
                final int j = i;
                Map test = CollectionUtils.bucket(members, new Transformer(){

                    public Object transform(Object value) {
                        return TypeUtils.emulateClassGetName(typer.getParameterTypes((MethodInfo)value)[j]);
                    }
                });
                if (buckets != null && test.size() <= buckets.size()) continue;
                buckets = test;
                index = i;
            }
            if (buckets == null || buckets.size() == 1) {
                e.goTo(def);
            } else {
                checked.set(index);
                e.dup();
                e.aaload(index);
                e.invoke_virtual(Constants.TYPE_CLASS, GET_NAME);
                final Map fbuckets = buckets;
                String[] names = buckets.keySet().toArray(new String[buckets.size()]);
                EmitUtils.string_switch(e, names, 1, new ObjectSwitchCallback(){

                    public void processCase(Object key, Label dontUseEnd) throws Exception {
                        EmitUtils.member_helper_type(e, (List)fbuckets.get(key), callback, typer, def, end, checked);
                    }

                    public void processDefault() throws Exception {
                        e.goTo(def);
                    }
                });
            }
        }
    }

    public static void wrap_throwable(Block block, Type wrapper) {
        CodeEmitter e = block.getCodeEmitter();
        e.catch_exception(block, Constants.TYPE_THROWABLE);
        e.new_instance(wrapper);
        e.dup_x1();
        e.swap();
        e.invoke_constructor(wrapper, CSTRUCT_THROWABLE);
        e.athrow();
    }

    public static void add_properties(ClassEmitter ce, String[] names, Type[] types) {
        for (int i = 0; i < names.length; ++i) {
            String fieldName = "$cglib_prop_" + names[i];
            ce.declare_field(2, fieldName, types[i], null);
            EmitUtils.add_property(ce, names[i], types[i], fieldName);
        }
    }

    public static void add_property(ClassEmitter ce, String name, Type type, String fieldName) {
        String property = TypeUtils.upperFirst(name);
        CodeEmitter e = ce.begin_method(1, new Signature("get" + property, type, Constants.TYPES_EMPTY), null);
        e.load_this();
        e.getfield(fieldName);
        e.return_value();
        e.end_method();
        e = ce.begin_method(1, new Signature("set" + property, Type.VOID_TYPE, new Type[]{type}), null);
        e.load_this();
        e.load_arg(0);
        e.putfield(fieldName);
        e.return_value();
        e.end_method();
    }

    public static void wrap_undeclared_throwable(CodeEmitter e, Block handler, Type[] exceptions, Type wrapper) {
        boolean needThrow;
        Set<Object> set;
        Set<Object> set2 = set = exceptions == null ? Collections.EMPTY_SET : new HashSet<Type>(Arrays.asList(exceptions));
        if (set.contains(Constants.TYPE_THROWABLE)) {
            return;
        }
        boolean bl = needThrow = exceptions != null;
        if (!set.contains(Constants.TYPE_RUNTIME_EXCEPTION)) {
            e.catch_exception(handler, Constants.TYPE_RUNTIME_EXCEPTION);
            needThrow = true;
        }
        if (!set.contains(Constants.TYPE_ERROR)) {
            e.catch_exception(handler, Constants.TYPE_ERROR);
            needThrow = true;
        }
        if (exceptions != null) {
            for (int i = 0; i < exceptions.length; ++i) {
                e.catch_exception(handler, exceptions[i]);
            }
        }
        if (needThrow) {
            e.athrow();
        }
        e.catch_exception(handler, Constants.TYPE_THROWABLE);
        e.new_instance(wrapper);
        e.dup_x1();
        e.swap();
        e.invoke_constructor(wrapper, CSTRUCT_THROWABLE);
        e.athrow();
    }

    public static CodeEmitter begin_method(ClassEmitter e, MethodInfo method) {
        return EmitUtils.begin_method(e, method, method.getModifiers());
    }

    public static CodeEmitter begin_method(ClassEmitter e, MethodInfo method, int access) {
        return e.begin_method(access, method.getSignature(), method.getExceptionTypes());
    }

    private static interface ParameterTyper {
        public Type[] getParameterTypes(MethodInfo var1);
    }

    public static class ArrayDelimiters {
        private String before;
        private String inside;
        private String after;

        public ArrayDelimiters(String before, String inside, String after) {
            this.before = before;
            this.inside = inside;
            this.after = after;
        }
    }
}

