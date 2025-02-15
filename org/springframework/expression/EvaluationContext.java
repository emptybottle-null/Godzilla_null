/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import java.util.List;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ConstructorResolver;
import org.springframework.expression.MethodResolver;
import org.springframework.expression.OperatorOverloader;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypeComparator;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypeLocator;
import org.springframework.expression.TypedValue;
import org.springframework.lang.Nullable;

public interface EvaluationContext {
    public TypedValue getRootObject();

    public List<PropertyAccessor> getPropertyAccessors();

    public List<ConstructorResolver> getConstructorResolvers();

    public List<MethodResolver> getMethodResolvers();

    @Nullable
    public BeanResolver getBeanResolver();

    public TypeLocator getTypeLocator();

    public TypeConverter getTypeConverter();

    public TypeComparator getTypeComparator();

    public OperatorOverloader getOperatorOverloader();

    public void setVariable(String var1, @Nullable Object var2);

    @Nullable
    public Object lookupVariable(String var1);
}

