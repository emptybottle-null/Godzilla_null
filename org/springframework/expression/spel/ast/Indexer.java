/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import org.springframework.asm.MethodVisitor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.AstUtils;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.StringLiteral;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class Indexer
extends SpelNodeImpl {
    @Nullable
    private String cachedReadName;
    @Nullable
    private Class<?> cachedReadTargetType;
    @Nullable
    private PropertyAccessor cachedReadAccessor;
    @Nullable
    private String cachedWriteName;
    @Nullable
    private Class<?> cachedWriteTargetType;
    @Nullable
    private PropertyAccessor cachedWriteAccessor;
    @Nullable
    private IndexedType indexedType;

    public Indexer(int startPos, int endPos, SpelNodeImpl expr) {
        super(startPos, endPos, expr);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        return this.getValueRef(state).getValue();
    }

    @Override
    public void setValue(ExpressionState state, @Nullable Object newValue) throws EvaluationException {
        this.getValueRef(state).setValue(newValue);
    }

    @Override
    public boolean isWritable(ExpressionState expressionState) throws SpelEvaluationException {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected ValueRef getValueRef(ExpressionState state) throws EvaluationException {
        TypedValue indexValue;
        Object index;
        TypedValue context = state.getActiveContextObject();
        Object target = context.getValue();
        TypeDescriptor targetDescriptor = context.getTypeDescriptor();
        if (target instanceof Map && this.children[0] instanceof PropertyOrFieldReference) {
            PropertyOrFieldReference reference = (PropertyOrFieldReference)this.children[0];
            index = reference.getName();
            indexValue = new TypedValue(index);
        } else {
            try {
                state.pushActiveContextObject(state.getRootContextObject());
                indexValue = this.children[0].getValueInternal(state);
                index = indexValue.getValue();
                Assert.state(index != null, "No index");
            } finally {
                state.popActiveContextObject();
            }
        }
        if (target == null) {
            throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.CANNOT_INDEX_INTO_NULL_VALUE, new Object[0]);
        }
        Assert.state(targetDescriptor != null, "No type descriptor");
        if (target instanceof Map) {
            Object key = index;
            if (targetDescriptor.getMapKeyTypeDescriptor() != null) {
                key = state.convertValue(key, targetDescriptor.getMapKeyTypeDescriptor());
            }
            this.indexedType = IndexedType.MAP;
            return new MapIndexingValueRef(state.getTypeConverter(), (Map)target, key, targetDescriptor);
        }
        if (target.getClass().isArray() || target instanceof Collection || target instanceof String) {
            int idx = (Integer)state.convertValue(index, TypeDescriptor.valueOf(Integer.class));
            if (target.getClass().isArray()) {
                this.indexedType = IndexedType.ARRAY;
                return new ArrayIndexingValueRef(state.getTypeConverter(), target, idx, targetDescriptor);
            }
            if (target instanceof Collection) {
                if (target instanceof List) {
                    this.indexedType = IndexedType.LIST;
                }
                return new CollectionIndexingValueRef((Collection)target, idx, targetDescriptor, state.getTypeConverter(), state.getConfiguration().isAutoGrowCollections(), state.getConfiguration().getMaximumAutoGrowSize());
            }
            this.indexedType = IndexedType.STRING;
            return new StringIndexingLValue((String)target, idx, targetDescriptor);
        }
        TypeDescriptor valueType = indexValue.getTypeDescriptor();
        if (valueType != null && String.class == valueType.getType()) {
            this.indexedType = IndexedType.OBJECT;
            return new PropertyIndexingValueRef(target, (String)index, state.getEvaluationContext(), targetDescriptor);
        }
        throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE, targetDescriptor);
    }

    @Override
    public boolean isCompilable() {
        if (this.indexedType == IndexedType.ARRAY) {
            return this.exitTypeDescriptor != null;
        }
        if (this.indexedType == IndexedType.LIST) {
            return this.children[0].isCompilable();
        }
        if (this.indexedType == IndexedType.MAP) {
            return this.children[0] instanceof PropertyOrFieldReference || this.children[0].isCompilable();
        }
        if (this.indexedType == IndexedType.OBJECT) {
            return this.cachedReadAccessor != null && this.cachedReadAccessor instanceof ReflectivePropertyAccessor.OptimalPropertyAccessor && this.getChild(0) instanceof StringLiteral;
        }
        return false;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        String descriptor = cf.lastDescriptor();
        if (descriptor == null) {
            cf.loadTarget(mv);
        }
        if (this.indexedType == IndexedType.ARRAY) {
            int insn;
            if ("D".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[D");
                insn = 49;
            } else if ("F".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[F");
                insn = 48;
            } else if ("J".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[J");
                insn = 47;
            } else if ("I".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[I");
                insn = 46;
            } else if ("S".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[S");
                insn = 53;
            } else if ("B".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[B");
                insn = 51;
            } else if ("C".equals(this.exitTypeDescriptor)) {
                mv.visitTypeInsn(192, "[C");
                insn = 52;
            } else {
                mv.visitTypeInsn(192, "[" + this.exitTypeDescriptor + (CodeFlow.isPrimitiveArray(this.exitTypeDescriptor) ? "" : ";"));
                insn = 50;
            }
            SpelNodeImpl index = this.children[0];
            cf.enterCompilationScope();
            index.generateCode(mv, cf);
            cf.exitCompilationScope();
            mv.visitInsn(insn);
        } else if (this.indexedType == IndexedType.LIST) {
            mv.visitTypeInsn(192, "java/util/List");
            cf.enterCompilationScope();
            this.children[0].generateCode(mv, cf);
            cf.exitCompilationScope();
            mv.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;", true);
        } else if (this.indexedType == IndexedType.MAP) {
            mv.visitTypeInsn(192, "java/util/Map");
            if (this.children[0] instanceof PropertyOrFieldReference) {
                PropertyOrFieldReference reference = (PropertyOrFieldReference)this.children[0];
                String mapKeyName = reference.getName();
                mv.visitLdcInsn(mapKeyName);
            } else {
                cf.enterCompilationScope();
                this.children[0].generateCode(mv, cf);
                cf.exitCompilationScope();
            }
            mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        } else if (this.indexedType == IndexedType.OBJECT) {
            ReflectivePropertyAccessor.OptimalPropertyAccessor accessor = (ReflectivePropertyAccessor.OptimalPropertyAccessor)this.cachedReadAccessor;
            Assert.state(accessor != null, "No cached read accessor");
            Member member = accessor.member;
            boolean isStatic = Modifier.isStatic(member.getModifiers());
            String classDesc = member.getDeclaringClass().getName().replace('.', '/');
            if (!isStatic) {
                if (descriptor == null) {
                    cf.loadTarget(mv);
                }
                if (descriptor == null || !classDesc.equals(descriptor.substring(1))) {
                    mv.visitTypeInsn(192, classDesc);
                }
            }
            if (member instanceof Method) {
                mv.visitMethodInsn(isStatic ? 184 : 182, classDesc, member.getName(), CodeFlow.createSignatureDescriptor((Method)member), false);
            } else {
                mv.visitFieldInsn(isStatic ? 178 : 180, classDesc, member.getName(), CodeFlow.toJvmDescriptor(((Field)member).getType()));
            }
        }
        cf.pushDescriptor(this.exitTypeDescriptor);
    }

    @Override
    public String toStringAST() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (int i = 0; i < this.getChildCount(); ++i) {
            sj.add(this.getChild(i).toStringAST());
        }
        return sj.toString();
    }

    private void setArrayElement(TypeConverter converter, Object ctx, int idx, @Nullable Object newValue, Class<?> arrayComponentType) throws EvaluationException {
        if (arrayComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Boolean.class);
        } else if (arrayComponentType == Byte.TYPE) {
            byte[] array = (byte[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Byte.class);
        } else if (arrayComponentType == Character.TYPE) {
            char[] array = (char[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Character.class).charValue();
        } else if (arrayComponentType == Double.TYPE) {
            double[] array = (double[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Double.class);
        } else if (arrayComponentType == Float.TYPE) {
            float[] array = (float[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Float.class).floatValue();
        } else if (arrayComponentType == Integer.TYPE) {
            int[] array = (int[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Integer.class);
        } else if (arrayComponentType == Long.TYPE) {
            long[] array = (long[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Long.class);
        } else if (arrayComponentType == Short.TYPE) {
            short[] array = (short[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, Short.class);
        } else {
            Object[] array = (Object[])ctx;
            this.checkAccess(array.length, idx);
            array[idx] = this.convertValue(converter, newValue, arrayComponentType);
        }
    }

    private Object accessArrayElement(Object ctx, int idx) throws SpelEvaluationException {
        Class<?> arrayComponentType = ctx.getClass().getComponentType();
        if (arrayComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "Z";
            return array[idx];
        }
        if (arrayComponentType == Byte.TYPE) {
            byte[] array = (byte[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "B";
            return array[idx];
        }
        if (arrayComponentType == Character.TYPE) {
            char[] array = (char[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "C";
            return Character.valueOf(array[idx]);
        }
        if (arrayComponentType == Double.TYPE) {
            double[] array = (double[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "D";
            return array[idx];
        }
        if (arrayComponentType == Float.TYPE) {
            float[] array = (float[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "F";
            return Float.valueOf(array[idx]);
        }
        if (arrayComponentType == Integer.TYPE) {
            int[] array = (int[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "I";
            return array[idx];
        }
        if (arrayComponentType == Long.TYPE) {
            long[] array = (long[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "J";
            return array[idx];
        }
        if (arrayComponentType == Short.TYPE) {
            short[] array = (short[])ctx;
            this.checkAccess(array.length, idx);
            this.exitTypeDescriptor = "S";
            return array[idx];
        }
        Object[] array = (Object[])ctx;
        this.checkAccess(array.length, idx);
        Object retValue = array[idx];
        this.exitTypeDescriptor = CodeFlow.toDescriptor(arrayComponentType);
        return retValue;
    }

    private void checkAccess(int arrayLength, int index) throws SpelEvaluationException {
        if (index >= arrayLength) {
            throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.ARRAY_INDEX_OUT_OF_BOUNDS, arrayLength, index);
        }
    }

    private <T> T convertValue(TypeConverter converter, @Nullable Object value, Class<T> targetType) {
        Object result = converter.convertValue(value, TypeDescriptor.forObject(value), TypeDescriptor.valueOf(targetType));
        if (result == null) {
            throw new IllegalStateException("Null conversion result for index [" + value + "]");
        }
        return (T)result;
    }

    private class StringIndexingLValue
    implements ValueRef {
        private final String target;
        private final int index;
        private final TypeDescriptor typeDescriptor;

        public StringIndexingLValue(String target, int index, TypeDescriptor typeDescriptor) {
            this.target = target;
            this.index = index;
            this.typeDescriptor = typeDescriptor;
        }

        @Override
        public TypedValue getValue() {
            if (this.index >= this.target.length()) {
                throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.STRING_INDEX_OUT_OF_BOUNDS, this.target.length(), this.index);
            }
            return new TypedValue(String.valueOf(this.target.charAt(this.index)));
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE, this.typeDescriptor.toString());
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }

    private class CollectionIndexingValueRef
    implements ValueRef {
        private final Collection collection;
        private final int index;
        private final TypeDescriptor collectionEntryDescriptor;
        private final TypeConverter typeConverter;
        private final boolean growCollection;
        private final int maximumSize;

        public CollectionIndexingValueRef(Collection collection, int index, TypeDescriptor collectionEntryDescriptor, TypeConverter typeConverter, boolean growCollection, int maximumSize) {
            this.collection = collection;
            this.index = index;
            this.collectionEntryDescriptor = collectionEntryDescriptor;
            this.typeConverter = typeConverter;
            this.growCollection = growCollection;
            this.maximumSize = maximumSize;
        }

        @Override
        public TypedValue getValue() {
            this.growCollectionIfNecessary();
            if (this.collection instanceof List) {
                Object o = ((List)this.collection).get(this.index);
                Indexer.this.exitTypeDescriptor = CodeFlow.toDescriptor(Object.class);
                return new TypedValue(o, this.collectionEntryDescriptor.elementTypeDescriptor(o));
            }
            int pos = 0;
            for (Object o : this.collection) {
                if (pos == this.index) {
                    return new TypedValue(o, this.collectionEntryDescriptor.elementTypeDescriptor(o));
                }
                ++pos;
            }
            throw new IllegalStateException("Failed to find indexed element " + this.index + ": " + this.collection);
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            List list;
            this.growCollectionIfNecessary();
            if (this.collection instanceof List) {
                list = (List)this.collection;
                if (this.collectionEntryDescriptor.getElementTypeDescriptor() != null) {
                    newValue = this.typeConverter.convertValue(newValue, TypeDescriptor.forObject(newValue), this.collectionEntryDescriptor.getElementTypeDescriptor());
                }
            } else {
                throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE, this.collectionEntryDescriptor.toString());
            }
            list.set(this.index, newValue);
        }

        private void growCollectionIfNecessary() {
            if (this.index >= this.collection.size()) {
                if (!this.growCollection) {
                    throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.COLLECTION_INDEX_OUT_OF_BOUNDS, this.collection.size(), this.index);
                }
                if (this.index >= this.maximumSize) {
                    throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.UNABLE_TO_GROW_COLLECTION, new Object[0]);
                }
                if (this.collectionEntryDescriptor.getElementTypeDescriptor() == null) {
                    throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.UNABLE_TO_GROW_COLLECTION_UNKNOWN_ELEMENT_TYPE, new Object[0]);
                }
                TypeDescriptor elementType = this.collectionEntryDescriptor.getElementTypeDescriptor();
                try {
                    Constructor<?> ctor = this.getDefaultConstructor(elementType.getType());
                    for (int newElements = this.index - this.collection.size(); newElements >= 0; --newElements) {
                        this.collection.add(ctor != null ? (Object)ctor.newInstance(new Object[0]) : null);
                    }
                } catch (Throwable ex) {
                    throw new SpelEvaluationException(Indexer.this.getStartPosition(), ex, SpelMessage.UNABLE_TO_GROW_COLLECTION, new Object[0]);
                }
            }
        }

        @Nullable
        private Constructor<?> getDefaultConstructor(Class<?> type) {
            try {
                return ReflectionUtils.accessibleConstructor(type, new Class[0]);
            } catch (Throwable ex) {
                return null;
            }
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }

    private class PropertyIndexingValueRef
    implements ValueRef {
        private final Object targetObject;
        private final String name;
        private final EvaluationContext evaluationContext;
        private final TypeDescriptor targetObjectTypeDescriptor;

        public PropertyIndexingValueRef(Object targetObject, String value, EvaluationContext evaluationContext, TypeDescriptor targetObjectTypeDescriptor) {
            this.targetObject = targetObject;
            this.name = value;
            this.evaluationContext = evaluationContext;
            this.targetObjectTypeDescriptor = targetObjectTypeDescriptor;
        }

        @Override
        public TypedValue getValue() {
            Class<?> targetObjectRuntimeClass = Indexer.this.getObjectClass(this.targetObject);
            try {
                if (Indexer.this.cachedReadName != null && Indexer.this.cachedReadName.equals(this.name) && Indexer.this.cachedReadTargetType != null && Indexer.this.cachedReadTargetType.equals(targetObjectRuntimeClass)) {
                    PropertyAccessor accessor = Indexer.this.cachedReadAccessor;
                    Assert.state(accessor != null, "No cached read accessor");
                    return accessor.read(this.evaluationContext, this.targetObject, this.name);
                }
                List<PropertyAccessor> accessorsToTry = AstUtils.getPropertyAccessorsToTry(targetObjectRuntimeClass, this.evaluationContext.getPropertyAccessors());
                for (PropertyAccessor accessor : accessorsToTry) {
                    if (!accessor.canRead(this.evaluationContext, this.targetObject, this.name)) continue;
                    if (accessor instanceof ReflectivePropertyAccessor) {
                        accessor = ((ReflectivePropertyAccessor)accessor).createOptimalAccessor(this.evaluationContext, this.targetObject, this.name);
                    }
                    Indexer.this.cachedReadAccessor = accessor;
                    Indexer.this.cachedReadName = this.name;
                    Indexer.this.cachedReadTargetType = targetObjectRuntimeClass;
                    if (accessor instanceof ReflectivePropertyAccessor.OptimalPropertyAccessor) {
                        ReflectivePropertyAccessor.OptimalPropertyAccessor optimalAccessor = (ReflectivePropertyAccessor.OptimalPropertyAccessor)accessor;
                        Member member = optimalAccessor.member;
                        Indexer.this.exitTypeDescriptor = CodeFlow.toDescriptor(member instanceof Method ? ((Method)member).getReturnType() : ((Field)member).getType());
                    }
                    return accessor.read(this.evaluationContext, this.targetObject, this.name);
                }
            } catch (AccessException ex) {
                throw new SpelEvaluationException(Indexer.this.getStartPosition(), (Throwable)ex, SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE, this.targetObjectTypeDescriptor.toString());
            }
            throw new SpelEvaluationException(Indexer.this.getStartPosition(), SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE, this.targetObjectTypeDescriptor.toString());
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            Class<?> contextObjectClass = Indexer.this.getObjectClass(this.targetObject);
            try {
                if (Indexer.this.cachedWriteName != null && Indexer.this.cachedWriteName.equals(this.name) && Indexer.this.cachedWriteTargetType != null && Indexer.this.cachedWriteTargetType.equals(contextObjectClass)) {
                    PropertyAccessor accessor = Indexer.this.cachedWriteAccessor;
                    Assert.state(accessor != null, "No cached write accessor");
                    accessor.write(this.evaluationContext, this.targetObject, this.name, newValue);
                    return;
                }
                List<PropertyAccessor> accessorsToTry = AstUtils.getPropertyAccessorsToTry(contextObjectClass, this.evaluationContext.getPropertyAccessors());
                for (PropertyAccessor accessor : accessorsToTry) {
                    if (!accessor.canWrite(this.evaluationContext, this.targetObject, this.name)) continue;
                    Indexer.this.cachedWriteName = this.name;
                    Indexer.this.cachedWriteTargetType = contextObjectClass;
                    Indexer.this.cachedWriteAccessor = accessor;
                    accessor.write(this.evaluationContext, this.targetObject, this.name, newValue);
                    return;
                }
            } catch (AccessException ex) {
                throw new SpelEvaluationException(Indexer.this.getStartPosition(), (Throwable)ex, SpelMessage.EXCEPTION_DURING_PROPERTY_WRITE, this.name, ex.getMessage());
            }
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }

    private class MapIndexingValueRef
    implements ValueRef {
        private final TypeConverter typeConverter;
        private final Map map;
        @Nullable
        private final Object key;
        private final TypeDescriptor mapEntryDescriptor;

        public MapIndexingValueRef(TypeConverter typeConverter, @Nullable Map map, Object key, TypeDescriptor mapEntryDescriptor) {
            this.typeConverter = typeConverter;
            this.map = map;
            this.key = key;
            this.mapEntryDescriptor = mapEntryDescriptor;
        }

        @Override
        public TypedValue getValue() {
            Object value = this.map.get(this.key);
            Indexer.this.exitTypeDescriptor = CodeFlow.toDescriptor(Object.class);
            return new TypedValue(value, this.mapEntryDescriptor.getMapValueTypeDescriptor(value));
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            if (this.mapEntryDescriptor.getMapValueTypeDescriptor() != null) {
                newValue = this.typeConverter.convertValue(newValue, TypeDescriptor.forObject(newValue), this.mapEntryDescriptor.getMapValueTypeDescriptor());
            }
            this.map.put(this.key, newValue);
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }

    private class ArrayIndexingValueRef
    implements ValueRef {
        private final TypeConverter typeConverter;
        private final Object array;
        private final int index;
        private final TypeDescriptor typeDescriptor;

        ArrayIndexingValueRef(TypeConverter typeConverter, Object array, int index, TypeDescriptor typeDescriptor) {
            this.typeConverter = typeConverter;
            this.array = array;
            this.index = index;
            this.typeDescriptor = typeDescriptor;
        }

        @Override
        public TypedValue getValue() {
            Object arrayElement = Indexer.this.accessArrayElement(this.array, this.index);
            return new TypedValue(arrayElement, this.typeDescriptor.elementTypeDescriptor(arrayElement));
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            TypeDescriptor elementType = this.typeDescriptor.getElementTypeDescriptor();
            Assert.state(elementType != null, "No element type");
            Indexer.this.setArrayElement(this.typeConverter, this.array, this.index, newValue, elementType.getType());
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }

    private static enum IndexedType {
        ARRAY,
        LIST,
        MAP,
        STRING,
        OBJECT;

    }
}

