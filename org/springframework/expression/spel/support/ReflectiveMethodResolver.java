/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.MethodExecutor;
import org.springframework.expression.MethodFilter;
import org.springframework.expression.MethodResolver;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.support.ReflectionHelper;
import org.springframework.expression.spel.support.ReflectiveMethodExecutor;
import org.springframework.lang.Nullable;

public class ReflectiveMethodResolver
implements MethodResolver {
    private final boolean useDistance;
    @Nullable
    private Map<Class<?>, MethodFilter> filters;

    public ReflectiveMethodResolver() {
        this.useDistance = true;
    }

    public ReflectiveMethodResolver(boolean useDistance) {
        this.useDistance = useDistance;
    }

    public void registerMethodFilter(Class<?> type, @Nullable MethodFilter filter) {
        if (this.filters == null) {
            this.filters = new HashMap();
        }
        if (filter != null) {
            this.filters.put(type, filter);
        } else {
            this.filters.remove(type);
        }
    }

    @Override
    @Nullable
    public MethodExecutor resolve(EvaluationContext context, Object targetObject, String name, List<TypeDescriptor> argumentTypes) throws AccessException {
        try {
            MethodFilter filter;
            TypeConverter typeConverter = context.getTypeConverter();
            Class<?> type = targetObject instanceof Class ? (Class<?>)targetObject : targetObject.getClass();
            ArrayList<Object> methods = new ArrayList(this.getMethods(type, targetObject));
            MethodFilter methodFilter = filter = this.filters != null ? this.filters.get(type) : null;
            if (filter != null) {
                List<Method> filtered = filter.filter(methods);
                ArrayList<Object> arrayList = methods = filtered instanceof ArrayList ? (ArrayList<Object>)filtered : new ArrayList<Method>(filtered);
            }
            if (methods.size() > 1) {
                methods.sort((m1, m2) -> {
                    int m2pl;
                    int m1pl = m1.getParameterCount();
                    if (m1pl == (m2pl = m2.getParameterCount())) {
                        if (!m1.isVarArgs() && m2.isVarArgs()) {
                            return -1;
                        }
                        if (m1.isVarArgs() && !m2.isVarArgs()) {
                            return 1;
                        }
                        return 0;
                    }
                    return Integer.compare(m1pl, m2pl);
                });
            }
            for (int i = 0; i < methods.size(); ++i) {
                methods.set(i, BridgeMethodResolver.findBridgedMethod((Method)methods.get(i)));
            }
            LinkedHashSet methodsToIterate = new LinkedHashSet(methods);
            Method closeMatch = null;
            int closeMatchDistance = Integer.MAX_VALUE;
            Method matchRequiringConversion = null;
            boolean multipleOptions = false;
            for (Method method : methodsToIterate) {
                if (!method.getName().equals(name)) continue;
                int paramCount = method.getParameterCount();
                ArrayList<TypeDescriptor> paramDescriptors = new ArrayList<TypeDescriptor>(paramCount);
                for (int i = 0; i < paramCount; ++i) {
                    paramDescriptors.add(new TypeDescriptor(new MethodParameter(method, i)));
                }
                ReflectionHelper.ArgumentsMatchInfo matchInfo = null;
                if (method.isVarArgs() && argumentTypes.size() >= paramCount - 1) {
                    matchInfo = ReflectionHelper.compareArgumentsVarargs(paramDescriptors, argumentTypes, typeConverter);
                } else if (paramCount == argumentTypes.size()) {
                    matchInfo = ReflectionHelper.compareArguments(paramDescriptors, argumentTypes, typeConverter);
                }
                if (matchInfo == null) continue;
                if (matchInfo.isExactMatch()) {
                    return new ReflectiveMethodExecutor(method);
                }
                if (matchInfo.isCloseMatch()) {
                    if (this.useDistance) {
                        int matchDistance = ReflectionHelper.getTypeDifferenceWeight(paramDescriptors, argumentTypes);
                        if (closeMatch != null && matchDistance >= closeMatchDistance) continue;
                        closeMatch = method;
                        closeMatchDistance = matchDistance;
                        continue;
                    }
                    if (closeMatch != null) continue;
                    closeMatch = method;
                    continue;
                }
                if (!matchInfo.isMatchRequiringConversion()) continue;
                if (matchRequiringConversion != null) {
                    multipleOptions = true;
                }
                matchRequiringConversion = method;
            }
            if (closeMatch != null) {
                return new ReflectiveMethodExecutor(closeMatch);
            }
            if (matchRequiringConversion != null) {
                if (multipleOptions) {
                    throw new SpelEvaluationException(SpelMessage.MULTIPLE_POSSIBLE_METHODS, name);
                }
                return new ReflectiveMethodExecutor(matchRequiringConversion);
            }
            return null;
        } catch (EvaluationException ex) {
            throw new AccessException("Failed to resolve method", ex);
        }
    }

    private Set<Method> getMethods(Class<?> type, Object targetObject) {
        Method[] methods;
        if (targetObject instanceof Class) {
            Method[] methods2;
            LinkedHashSet<Method> result = new LinkedHashSet<Method>();
            for (Method method : methods2 = this.getMethods(type)) {
                if (!Modifier.isStatic(method.getModifiers())) continue;
                result.add(method);
            }
            Collections.addAll(result, this.getMethods(Class.class));
            return result;
        }
        if (Proxy.isProxyClass(type)) {
            LinkedHashSet<Method> result = new LinkedHashSet<Method>();
            for (Class<?> ifc : type.getInterfaces()) {
                Method[] methods3;
                for (Method method : methods3 = this.getMethods(ifc)) {
                    if (!this.isCandidateForInvocation(method, type)) continue;
                    result.add(method);
                }
            }
            return result;
        }
        LinkedHashSet<Method> result = new LinkedHashSet<Method>();
        for (Method method : methods = this.getMethods(type)) {
            if (!this.isCandidateForInvocation(method, type)) continue;
            result.add(method);
        }
        return result;
    }

    protected Method[] getMethods(Class<?> type) {
        return type.getMethods();
    }

    protected boolean isCandidateForInvocation(Method method, Class<?> targetClass) {
        return true;
    }
}

