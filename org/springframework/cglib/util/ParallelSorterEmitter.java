/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.util;

import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

class ParallelSorterEmitter
extends ClassEmitter {
    private static final Type PARALLEL_SORTER = TypeUtils.parseType("org.springframework.cglib.util.ParallelSorter");
    private static final Signature CSTRUCT_OBJECT_ARRAY = TypeUtils.parseConstructor("Object[]");
    private static final Signature NEW_INSTANCE = new Signature("newInstance", PARALLEL_SORTER, new Type[]{Constants.TYPE_OBJECT_ARRAY});
    private static final Signature SWAP = TypeUtils.parseSignature("void swap(int, int)");

    public ParallelSorterEmitter(ClassVisitor v, String className, Object[] arrays) {
        super(v);
        this.begin_class(52, 1, className, PARALLEL_SORTER, null, "<generated>");
        EmitUtils.null_constructor(this);
        EmitUtils.factory_method(this, NEW_INSTANCE);
        this.generateConstructor(arrays);
        this.generateSwap(arrays);
        this.end_class();
    }

    private String getFieldName(int index) {
        return "FIELD_" + index;
    }

    private void generateConstructor(Object[] arrays) {
        CodeEmitter e = this.begin_method(1, CSTRUCT_OBJECT_ARRAY, null);
        e.load_this();
        e.super_invoke_constructor();
        e.load_this();
        e.load_arg(0);
        e.super_putfield("a", Constants.TYPE_OBJECT_ARRAY);
        for (int i = 0; i < arrays.length; ++i) {
            Type type = Type.getType(arrays[i].getClass());
            this.declare_field(2, this.getFieldName(i), type, null);
            e.load_this();
            e.load_arg(0);
            e.push(i);
            e.aaload();
            e.checkcast(type);
            e.putfield(this.getFieldName(i));
        }
        e.return_value();
        e.end_method();
    }

    private void generateSwap(Object[] arrays) {
        CodeEmitter e = this.begin_method(1, SWAP, null);
        for (int i = 0; i < arrays.length; ++i) {
            Type type = Type.getType(arrays[i].getClass());
            Type component = TypeUtils.getComponentType(type);
            Local T2 = e.make_local(type);
            e.load_this();
            e.getfield(this.getFieldName(i));
            e.store_local(T2);
            e.load_local(T2);
            e.load_arg(0);
            e.load_local(T2);
            e.load_arg(1);
            e.array_load(component);
            e.load_local(T2);
            e.load_arg(1);
            e.load_local(T2);
            e.load_arg(0);
            e.array_load(component);
            e.array_store(component);
            e.array_store(component);
        }
        e.return_value();
        e.end_method();
    }
}

