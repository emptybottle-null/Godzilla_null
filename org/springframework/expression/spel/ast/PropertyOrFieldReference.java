/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.CompilablePropertyAccessor;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.FormatHelper;
import org.springframework.expression.spel.ast.Indexer;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class PropertyOrFieldReference
extends SpelNodeImpl {
    private final boolean nullSafe;
    private final String name;
    @Nullable
    private String originalPrimitiveExitTypeDescriptor;
    @Nullable
    private volatile PropertyAccessor cachedReadAccessor;
    @Nullable
    private volatile PropertyAccessor cachedWriteAccessor;

    public PropertyOrFieldReference(boolean nullSafe, String propertyOrFieldName, int startPos, int endPos) {
        super(startPos, endPos, new SpelNodeImpl[0]);
        this.nullSafe = nullSafe;
        this.name = propertyOrFieldName;
    }

    public boolean isNullSafe() {
        return this.nullSafe;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public ValueRef getValueRef(ExpressionState state) throws EvaluationException {
        return new AccessorLValue(this, state.getActiveContextObject(), state.getEvaluationContext(), state.getConfiguration().isAutoGrowNullReferences());
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        TypedValue tv = this.getValueInternal(state.getActiveContextObject(), state.getEvaluationContext(), state.getConfiguration().isAutoGrowNullReferences());
        PropertyAccessor accessorToUse = this.cachedReadAccessor;
        if (accessorToUse instanceof CompilablePropertyAccessor) {
            CompilablePropertyAccessor accessor = (CompilablePropertyAccessor)accessorToUse;
            this.setExitTypeDescriptor(CodeFlow.toDescriptor(accessor.getPropertyType()));
        }
        return tv;
    }

    private TypedValue getValueInternal(TypedValue contextObject, EvaluationContext evalContext, boolean isAutoGrowNullReferences) throws EvaluationException {
        TypedValue result = this.readProperty(contextObject, evalContext, this.name);
        if (result.getValue() == null && isAutoGrowNullReferences && this.nextChildIs(Indexer.class, PropertyOrFieldReference.class)) {
            TypeDescriptor resultDescriptor = result.getTypeDescriptor();
            Assert.state(resultDescriptor != null, "No result type");
            if (List.class == resultDescriptor.getType()) {
                if (this.isWritableProperty(this.name, contextObject, evalContext)) {
                    ArrayList newList = new ArrayList();
                    this.writeProperty(contextObject, evalContext, this.name, newList);
                    result = this.readProperty(contextObject, evalContext, this.name);
                }
            } else if (Map.class == resultDescriptor.getType()) {
                if (this.isWritableProperty(this.name, contextObject, evalContext)) {
                    HashMap newMap = new HashMap();
                    this.writeProperty(contextObject, evalContext, this.name, newMap);
                    result = this.readProperty(contextObject, evalContext, this.name);
                }
            } else {
                try {
                    if (this.isWritableProperty(this.name, contextObject, evalContext)) {
                        Class<?> clazz = result.getTypeDescriptor().getType();
                        Object newObject = ReflectionUtils.accessibleConstructor(clazz, new Class[0]).newInstance(new Object[0]);
                        this.writeProperty(contextObject, evalContext, this.name, newObject);
                        result = this.readProperty(contextObject, evalContext, this.name);
                    }
                } catch (InvocationTargetException ex) {
                    throw new SpelEvaluationException(this.getStartPosition(), ex.getTargetException(), SpelMessage.UNABLE_TO_DYNAMICALLY_CREATE_OBJECT, result.getTypeDescriptor().getType());
                } catch (Throwable ex) {
                    throw new SpelEvaluationException(this.getStartPosition(), ex, SpelMessage.UNABLE_TO_DYNAMICALLY_CREATE_OBJECT, result.getTypeDescriptor().getType());
                }
            }
        }
        return result;
    }

    @Override
    public void setValue(ExpressionState state, @Nullable Object newValue) throws EvaluationException {
        this.writeProperty(state.getActiveContextObject(), state.getEvaluationContext(), this.name, newValue);
    }

    @Override
    public boolean isWritable(ExpressionState state) throws EvaluationException {
        return this.isWritableProperty(this.name, state.getActiveContextObject(), state.getEvaluationContext());
    }

    @Override
    public String toStringAST() {
        return this.name;
    }

    private TypedValue readProperty(TypedValue contextObject, EvaluationContext evalContext, String name) throws EvaluationException {
        Object targetObject = contextObject.getValue();
        if (targetObject == null && this.nullSafe) {
            return TypedValue.NULL;
        }
        PropertyAccessor accessorToUse = this.cachedReadAccessor;
        if (accessorToUse != null) {
            if (evalContext.getPropertyAccessors().contains(accessorToUse)) {
                try {
                    return accessorToUse.read(evalContext, contextObject.getValue(), name);
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            this.cachedReadAccessor = null;
        }
        List<PropertyAccessor> accessorsToTry = this.getPropertyAccessorsToTry(contextObject.getValue(), evalContext.getPropertyAccessors());
        try {
            for (PropertyAccessor accessor : accessorsToTry) {
                if (!accessor.canRead(evalContext, contextObject.getValue(), name)) continue;
                if (accessor instanceof ReflectivePropertyAccessor) {
                    accessor = ((ReflectivePropertyAccessor)accessor).createOptimalAccessor(evalContext, contextObject.getValue(), name);
                }
                this.cachedReadAccessor = accessor;
                return accessor.read(evalContext, contextObject.getValue(), name);
            }
        } catch (Exception ex) {
            throw new SpelEvaluationException(ex, SpelMessage.EXCEPTION_DURING_PROPERTY_READ, name, ex.getMessage());
        }
        if (contextObject.getValue() == null) {
            throw new SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL, name);
        }
        throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE, name, FormatHelper.formatClassNameForMessage(this.getObjectClass(contextObject.getValue())));
    }

    private void writeProperty(TypedValue contextObject, EvaluationContext evalContext, String name, @Nullable Object newValue) throws EvaluationException {
        if (contextObject.getValue() == null && this.nullSafe) {
            return;
        }
        if (contextObject.getValue() == null) {
            throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE_ON_NULL, name);
        }
        PropertyAccessor accessorToUse = this.cachedWriteAccessor;
        if (accessorToUse != null) {
            if (evalContext.getPropertyAccessors().contains(accessorToUse)) {
                try {
                    accessorToUse.write(evalContext, contextObject.getValue(), name, newValue);
                    return;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            this.cachedWriteAccessor = null;
        }
        List<PropertyAccessor> accessorsToTry = this.getPropertyAccessorsToTry(contextObject.getValue(), evalContext.getPropertyAccessors());
        try {
            for (PropertyAccessor accessor : accessorsToTry) {
                if (!accessor.canWrite(evalContext, contextObject.getValue(), name)) continue;
                this.cachedWriteAccessor = accessor;
                accessor.write(evalContext, contextObject.getValue(), name, newValue);
                return;
            }
        } catch (AccessException ex) {
            throw new SpelEvaluationException(this.getStartPosition(), (Throwable)ex, SpelMessage.EXCEPTION_DURING_PROPERTY_WRITE, name, ex.getMessage());
        }
        throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE, name, FormatHelper.formatClassNameForMessage(this.getObjectClass(contextObject.getValue())));
    }

    public boolean isWritableProperty(String name, TypedValue contextObject, EvaluationContext evalContext) throws EvaluationException {
        Object value = contextObject.getValue();
        if (value != null) {
            List<PropertyAccessor> accessorsToTry = this.getPropertyAccessorsToTry(contextObject.getValue(), evalContext.getPropertyAccessors());
            for (PropertyAccessor accessor : accessorsToTry) {
                try {
                    if (!accessor.canWrite(evalContext, value, name)) continue;
                    return true;
                } catch (AccessException accessException) {
                }
            }
        }
        return false;
    }

    private List<PropertyAccessor> getPropertyAccessorsToTry(@Nullable Object contextObject, List<PropertyAccessor> propertyAccessors) {
        Class<?> targetType = contextObject != null ? contextObject.getClass() : null;
        ArrayList<PropertyAccessor> specificAccessors = new ArrayList<PropertyAccessor>();
        ArrayList<PropertyAccessor> generalAccessors = new ArrayList<PropertyAccessor>();
        block0: for (PropertyAccessor resolver : propertyAccessors) {
            Class<?>[] targets = resolver.getSpecificTargetClasses();
            if (targets == null) {
                generalAccessors.add(resolver);
                continue;
            }
            if (targetType == null) continue;
            for (Class<?> clazz : targets) {
                if (clazz == targetType) {
                    specificAccessors.add(resolver);
                    continue block0;
                }
                if (!clazz.isAssignableFrom(targetType)) continue;
                generalAccessors.add(resolver);
            }
        }
        ArrayList<PropertyAccessor> resolvers = new ArrayList<PropertyAccessor>(specificAccessors);
        generalAccessors.removeAll(specificAccessors);
        resolvers.addAll(generalAccessors);
        return resolvers;
    }

    @Override
    public boolean isCompilable() {
        PropertyAccessor accessorToUse = this.cachedReadAccessor;
        return accessorToUse instanceof CompilablePropertyAccessor && ((CompilablePropertyAccessor)accessorToUse).isCompilable();
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        PropertyAccessor accessorToUse = this.cachedReadAccessor;
        if (!(accessorToUse instanceof CompilablePropertyAccessor)) {
            throw new IllegalStateException("Property accessor is not compilable: " + accessorToUse);
        }
        Label skipIfNull = null;
        if (this.nullSafe) {
            mv.visitInsn(89);
            skipIfNull = new Label();
            Label continueLabel = new Label();
            mv.visitJumpInsn(199, continueLabel);
            CodeFlow.insertCheckCast(mv, this.exitTypeDescriptor);
            mv.visitJumpInsn(167, skipIfNull);
            mv.visitLabel(continueLabel);
        }
        ((CompilablePropertyAccessor)accessorToUse).generateCode(this.name, mv, cf);
        cf.pushDescriptor(this.exitTypeDescriptor);
        if (this.originalPrimitiveExitTypeDescriptor != null) {
            CodeFlow.insertBoxIfNecessary(mv, this.originalPrimitiveExitTypeDescriptor);
        }
        if (skipIfNull != null) {
            mv.visitLabel(skipIfNull);
        }
    }

    void setExitTypeDescriptor(String descriptor) {
        if (this.nullSafe && CodeFlow.isPrimitive(descriptor)) {
            this.originalPrimitiveExitTypeDescriptor = descriptor;
            this.exitTypeDescriptor = CodeFlow.toBoxedDescriptor(descriptor);
        } else {
            this.exitTypeDescriptor = descriptor;
        }
    }

    private static class AccessorLValue
    implements ValueRef {
        private final PropertyOrFieldReference ref;
        private final TypedValue contextObject;
        private final EvaluationContext evalContext;
        private final boolean autoGrowNullReferences;

        public AccessorLValue(PropertyOrFieldReference propertyOrFieldReference, TypedValue activeContextObject, EvaluationContext evalContext, boolean autoGrowNullReferences) {
            this.ref = propertyOrFieldReference;
            this.contextObject = activeContextObject;
            this.evalContext = evalContext;
            this.autoGrowNullReferences = autoGrowNullReferences;
        }

        @Override
        public TypedValue getValue() {
            TypedValue value = this.ref.getValueInternal(this.contextObject, this.evalContext, this.autoGrowNullReferences);
            PropertyAccessor accessorToUse = this.ref.cachedReadAccessor;
            if (accessorToUse instanceof CompilablePropertyAccessor) {
                this.ref.setExitTypeDescriptor(CodeFlow.toDescriptor(((CompilablePropertyAccessor)accessorToUse).getPropertyType()));
            }
            return value;
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            this.ref.writeProperty(this.contextObject, this.evalContext, this.ref.name, newValue);
        }

        @Override
        public boolean isWritable() {
            return this.ref.isWritableProperty(this.ref.name, this.contextObject, this.evalContext);
        }
    }
}

