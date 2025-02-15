/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.springframework.asm.MethodVisitor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.ConstructorExecutor;
import org.springframework.expression.ConstructorResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypedValue;
import org.springframework.expression.common.ExpressionUtils;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.ast.FormatHelper;
import org.springframework.expression.spel.ast.InlineList;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.TypeCode;
import org.springframework.expression.spel.support.ReflectiveConstructorExecutor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class ConstructorReference
extends SpelNodeImpl {
    private final boolean isArrayConstructor;
    @Nullable
    private SpelNodeImpl[] dimensions;
    @Nullable
    private volatile ConstructorExecutor cachedExecutor;

    public ConstructorReference(int startPos, int endPos, SpelNodeImpl ... arguments) {
        super(startPos, endPos, arguments);
        this.isArrayConstructor = false;
    }

    public ConstructorReference(int startPos, int endPos, SpelNodeImpl[] dimensions, SpelNodeImpl ... arguments) {
        super(startPos, endPos, arguments);
        this.isArrayConstructor = true;
        this.dimensions = dimensions;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        if (this.isArrayConstructor) {
            return this.createArray(state);
        }
        return this.createNewInstance(state);
    }

    private TypedValue createNewInstance(ExpressionState state) throws EvaluationException {
        String typeName;
        Object[] arguments = new Object[this.getChildCount() - 1];
        ArrayList<TypeDescriptor> argumentTypes = new ArrayList<TypeDescriptor>(this.getChildCount() - 1);
        for (int i = 0; i < arguments.length; ++i) {
            Object value;
            TypedValue childValue = this.children[i + 1].getValueInternal(state);
            arguments[i] = value = childValue.getValue();
            argumentTypes.add(TypeDescriptor.forObject(value));
        }
        ConstructorExecutor executorToUse = this.cachedExecutor;
        if (executorToUse != null) {
            try {
                return executorToUse.execute(state.getEvaluationContext(), arguments);
            } catch (AccessException ex) {
                if (ex.getCause() instanceof InvocationTargetException) {
                    Throwable rootCause = ex.getCause().getCause();
                    if (rootCause instanceof RuntimeException) {
                        throw (RuntimeException)rootCause;
                    }
                    String typeName2 = (String)this.children[0].getValueInternal(state).getValue();
                    throw new SpelEvaluationException(this.getStartPosition(), rootCause, SpelMessage.CONSTRUCTOR_INVOCATION_PROBLEM, typeName2, FormatHelper.formatMethodForMessage("", argumentTypes));
                }
                this.cachedExecutor = null;
            }
        }
        Assert.state((typeName = (String)this.children[0].getValueInternal(state).getValue()) != null, "No type name");
        executorToUse = this.findExecutorForConstructor(typeName, argumentTypes, state);
        try {
            this.cachedExecutor = executorToUse;
            if (executorToUse instanceof ReflectiveConstructorExecutor) {
                this.exitTypeDescriptor = CodeFlow.toDescriptor(((ReflectiveConstructorExecutor)executorToUse).getConstructor().getDeclaringClass());
            }
            return executorToUse.execute(state.getEvaluationContext(), arguments);
        } catch (AccessException ex) {
            throw new SpelEvaluationException(this.getStartPosition(), (Throwable)ex, SpelMessage.CONSTRUCTOR_INVOCATION_PROBLEM, typeName, FormatHelper.formatMethodForMessage("", argumentTypes));
        }
    }

    private ConstructorExecutor findExecutorForConstructor(String typeName, List<TypeDescriptor> argumentTypes, ExpressionState state) throws SpelEvaluationException {
        EvaluationContext evalContext = state.getEvaluationContext();
        List<ConstructorResolver> ctorResolvers = evalContext.getConstructorResolvers();
        for (ConstructorResolver ctorResolver : ctorResolvers) {
            try {
                ConstructorExecutor ce = ctorResolver.resolve(state.getEvaluationContext(), typeName, argumentTypes);
                if (ce == null) continue;
                return ce;
            } catch (AccessException ex) {
                throw new SpelEvaluationException(this.getStartPosition(), (Throwable)ex, SpelMessage.CONSTRUCTOR_INVOCATION_PROBLEM, typeName, FormatHelper.formatMethodForMessage("", argumentTypes));
            }
        }
        throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.CONSTRUCTOR_NOT_FOUND, typeName, FormatHelper.formatMethodForMessage("", argumentTypes));
    }

    @Override
    public String toStringAST() {
        StringBuilder sb = new StringBuilder("new ");
        int index = 0;
        sb.append(this.getChild(index++).toStringAST());
        sb.append('(');
        for (int i = index; i < this.getChildCount(); ++i) {
            if (i > index) {
                sb.append(',');
            }
            sb.append(this.getChild(i).toStringAST());
        }
        sb.append(')');
        return sb.toString();
    }

    private TypedValue createArray(ExpressionState state) throws EvaluationException {
        Object intendedArrayType = this.getChild(0).getValue(state);
        if (!(intendedArrayType instanceof String)) {
            throw new SpelEvaluationException(this.getChild(0).getStartPosition(), SpelMessage.TYPE_NAME_EXPECTED_FOR_ARRAY_CONSTRUCTION, FormatHelper.formatClassNameForMessage(intendedArrayType != null ? intendedArrayType.getClass() : null));
        }
        String type = (String)intendedArrayType;
        TypeCode arrayTypeCode = TypeCode.forName(type);
        Class<?> componentType = arrayTypeCode == TypeCode.OBJECT ? state.findType(type) : arrayTypeCode.getType();
        Object newArray = null;
        if (!this.hasInitializer()) {
            if (this.dimensions != null) {
                for (SpelNodeImpl dimension : this.dimensions) {
                    if (dimension != null) continue;
                    throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.MISSING_ARRAY_DIMENSION, new Object[0]);
                }
                TypeConverter typeConverter = state.getEvaluationContext().getTypeConverter();
                if (this.dimensions.length == 1) {
                    TypedValue o = this.dimensions[0].getTypedValue(state);
                    int arraySize = ExpressionUtils.toInt(typeConverter, o);
                    newArray = Array.newInstance(componentType, arraySize);
                } else {
                    int[] dims = new int[this.dimensions.length];
                    for (int d = 0; d < this.dimensions.length; ++d) {
                        TypedValue o = this.dimensions[d].getTypedValue(state);
                        dims[d] = ExpressionUtils.toInt(typeConverter, o);
                    }
                    newArray = Array.newInstance(componentType, dims);
                }
            }
        } else {
            TypedValue dValue;
            int i;
            if (this.dimensions == null || this.dimensions.length > 1) {
                throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.MULTIDIM_ARRAY_INITIALIZER_NOT_SUPPORTED, new Object[0]);
            }
            TypeConverter typeConverter = state.getEvaluationContext().getTypeConverter();
            InlineList initializer = (InlineList)this.getChild(1);
            if (this.dimensions[0] != null && (i = ExpressionUtils.toInt(typeConverter, dValue = this.dimensions[0].getTypedValue(state))) != initializer.getChildCount()) {
                throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.INITIALIZER_LENGTH_INCORRECT, new Object[0]);
            }
            int arraySize = initializer.getChildCount();
            newArray = Array.newInstance(componentType, arraySize);
            if (arrayTypeCode == TypeCode.OBJECT) {
                this.populateReferenceTypeArray(state, newArray, typeConverter, initializer, componentType);
            } else if (arrayTypeCode == TypeCode.BOOLEAN) {
                this.populateBooleanArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.BYTE) {
                this.populateByteArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.CHAR) {
                this.populateCharArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.DOUBLE) {
                this.populateDoubleArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.FLOAT) {
                this.populateFloatArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.INT) {
                this.populateIntArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.LONG) {
                this.populateLongArray(state, newArray, typeConverter, initializer);
            } else if (arrayTypeCode == TypeCode.SHORT) {
                this.populateShortArray(state, newArray, typeConverter, initializer);
            } else {
                throw new IllegalStateException(arrayTypeCode.name());
            }
        }
        return new TypedValue(newArray);
    }

    private void populateReferenceTypeArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer, Class<?> componentType) {
        TypeDescriptor toTypeDescriptor = TypeDescriptor.valueOf(componentType);
        Object[] newObjectArray = (Object[])newArray;
        for (int i = 0; i < newObjectArray.length; ++i) {
            SpelNode elementNode = initializer.getChild(i);
            Object arrayEntry = elementNode.getValue(state);
            newObjectArray[i] = typeConverter.convertValue(arrayEntry, TypeDescriptor.forObject(arrayEntry), toTypeDescriptor);
        }
    }

    private void populateByteArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        byte[] newByteArray = (byte[])newArray;
        for (int i = 0; i < newByteArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newByteArray[i] = ExpressionUtils.toByte(typeConverter, typedValue);
        }
    }

    private void populateFloatArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        float[] newFloatArray = (float[])newArray;
        for (int i = 0; i < newFloatArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newFloatArray[i] = ExpressionUtils.toFloat(typeConverter, typedValue);
        }
    }

    private void populateDoubleArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        double[] newDoubleArray = (double[])newArray;
        for (int i = 0; i < newDoubleArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newDoubleArray[i] = ExpressionUtils.toDouble(typeConverter, typedValue);
        }
    }

    private void populateShortArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        short[] newShortArray = (short[])newArray;
        for (int i = 0; i < newShortArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newShortArray[i] = ExpressionUtils.toShort(typeConverter, typedValue);
        }
    }

    private void populateLongArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        long[] newLongArray = (long[])newArray;
        for (int i = 0; i < newLongArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newLongArray[i] = ExpressionUtils.toLong(typeConverter, typedValue);
        }
    }

    private void populateCharArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        char[] newCharArray = (char[])newArray;
        for (int i = 0; i < newCharArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newCharArray[i] = ExpressionUtils.toChar(typeConverter, typedValue);
        }
    }

    private void populateBooleanArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        boolean[] newBooleanArray = (boolean[])newArray;
        for (int i = 0; i < newBooleanArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newBooleanArray[i] = ExpressionUtils.toBoolean(typeConverter, typedValue);
        }
    }

    private void populateIntArray(ExpressionState state, Object newArray, TypeConverter typeConverter, InlineList initializer) {
        int[] newIntArray = (int[])newArray;
        for (int i = 0; i < newIntArray.length; ++i) {
            TypedValue typedValue = initializer.getChild(i).getTypedValue(state);
            newIntArray[i] = ExpressionUtils.toInt(typeConverter, typedValue);
        }
    }

    private boolean hasInitializer() {
        return this.getChildCount() > 1;
    }

    @Override
    public boolean isCompilable() {
        ReflectiveConstructorExecutor executor;
        if (!(this.cachedExecutor instanceof ReflectiveConstructorExecutor) || this.exitTypeDescriptor == null) {
            return false;
        }
        if (this.getChildCount() > 1) {
            int max = this.getChildCount();
            for (int c = 1; c < max; ++c) {
                if (this.children[c].isCompilable()) continue;
                return false;
            }
        }
        if ((executor = (ReflectiveConstructorExecutor)this.cachedExecutor) == null) {
            return false;
        }
        Constructor<?> constructor = executor.getConstructor();
        return Modifier.isPublic(constructor.getModifiers()) && Modifier.isPublic(constructor.getDeclaringClass().getModifiers());
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        ReflectiveConstructorExecutor executor = (ReflectiveConstructorExecutor)this.cachedExecutor;
        Assert.state(executor != null, "No cached executor");
        Constructor<?> constructor = executor.getConstructor();
        String classDesc = constructor.getDeclaringClass().getName().replace('.', '/');
        mv.visitTypeInsn(187, classDesc);
        mv.visitInsn(89);
        SpelNodeImpl[] arguments = new SpelNodeImpl[this.children.length - 1];
        System.arraycopy(this.children, 1, arguments, 0, this.children.length - 1);
        ConstructorReference.generateCodeForArguments(mv, cf, constructor, arguments);
        mv.visitMethodInsn(183, classDesc, "<init>", CodeFlow.createSignatureDescriptor(constructor), false);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

