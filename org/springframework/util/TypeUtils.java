/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public abstract class TypeUtils {
    public static boolean isAssignable(Type lhsType, Type rhsType) {
        Assert.notNull((Object)lhsType, "Left-hand side type must not be null");
        Assert.notNull((Object)rhsType, "Right-hand side type must not be null");
        if (lhsType.equals(rhsType) || Object.class == lhsType) {
            return true;
        }
        if (lhsType instanceof Class) {
            Class lhsClass = (Class)lhsType;
            if (rhsType instanceof Class) {
                return ClassUtils.isAssignable(lhsClass, (Class)rhsType);
            }
            if (rhsType instanceof ParameterizedType) {
                Type rhsRaw = ((ParameterizedType)rhsType).getRawType();
                if (rhsRaw instanceof Class) {
                    return ClassUtils.isAssignable(lhsClass, (Class)rhsRaw);
                }
            } else if (lhsClass.isArray() && rhsType instanceof GenericArrayType) {
                Type rhsComponent = ((GenericArrayType)rhsType).getGenericComponentType();
                return TypeUtils.isAssignable(lhsClass.getComponentType(), rhsComponent);
            }
        }
        if (lhsType instanceof ParameterizedType) {
            if (rhsType instanceof Class) {
                Type lhsRaw = ((ParameterizedType)lhsType).getRawType();
                if (lhsRaw instanceof Class) {
                    return ClassUtils.isAssignable((Class)lhsRaw, (Class)rhsType);
                }
            } else if (rhsType instanceof ParameterizedType) {
                return TypeUtils.isAssignable((ParameterizedType)lhsType, (ParameterizedType)rhsType);
            }
        }
        if (lhsType instanceof GenericArrayType) {
            Type lhsComponent = ((GenericArrayType)lhsType).getGenericComponentType();
            if (rhsType instanceof Class) {
                Class rhsClass = (Class)rhsType;
                if (rhsClass.isArray()) {
                    return TypeUtils.isAssignable(lhsComponent, rhsClass.getComponentType());
                }
            } else if (rhsType instanceof GenericArrayType) {
                Type rhsComponent = ((GenericArrayType)rhsType).getGenericComponentType();
                return TypeUtils.isAssignable(lhsComponent, rhsComponent);
            }
        }
        if (lhsType instanceof WildcardType) {
            return TypeUtils.isAssignable((WildcardType)lhsType, rhsType);
        }
        return false;
    }

    private static boolean isAssignable(ParameterizedType lhsType, ParameterizedType rhsType) {
        Type[] rhsTypeArguments;
        if (lhsType.equals(rhsType)) {
            return true;
        }
        Type[] lhsTypeArguments = lhsType.getActualTypeArguments();
        if (lhsTypeArguments.length != (rhsTypeArguments = rhsType.getActualTypeArguments()).length) {
            return false;
        }
        int size = lhsTypeArguments.length;
        for (int i = 0; i < size; ++i) {
            Type lhsArg = lhsTypeArguments[i];
            Type rhsArg = rhsTypeArguments[i];
            if (lhsArg.equals(rhsArg) || lhsArg instanceof WildcardType && TypeUtils.isAssignable((WildcardType)lhsArg, rhsArg)) continue;
            return false;
        }
        return true;
    }

    private static boolean isAssignable(WildcardType lhsType, Type rhsType) {
        Type[] lLowerBounds;
        Type[] lUpperBounds = lhsType.getUpperBounds();
        if (lUpperBounds.length == 0) {
            lUpperBounds = new Type[]{Object.class};
        }
        if ((lLowerBounds = lhsType.getLowerBounds()).length == 0) {
            lLowerBounds = new Type[]{null};
        }
        if (rhsType instanceof WildcardType) {
            Type[] rLowerBounds;
            WildcardType rhsWcType = (WildcardType)rhsType;
            Type[] rUpperBounds = rhsWcType.getUpperBounds();
            if (rUpperBounds.length == 0) {
                rUpperBounds = new Type[]{Object.class};
            }
            if ((rLowerBounds = rhsWcType.getLowerBounds()).length == 0) {
                rLowerBounds = new Type[]{null};
            }
            for (Type lBound : lUpperBounds) {
                for (Type rBound : rUpperBounds) {
                    if (TypeUtils.isAssignableBound(lBound, rBound)) continue;
                    return false;
                }
                for (Type rBound : rLowerBounds) {
                    if (TypeUtils.isAssignableBound(lBound, rBound)) continue;
                    return false;
                }
            }
            for (Type lBound : lLowerBounds) {
                for (Type rBound : rUpperBounds) {
                    if (TypeUtils.isAssignableBound(rBound, lBound)) continue;
                    return false;
                }
                for (Type rBound : rLowerBounds) {
                    if (TypeUtils.isAssignableBound(rBound, lBound)) continue;
                    return false;
                }
            }
        } else {
            for (Type lBound : lUpperBounds) {
                if (TypeUtils.isAssignableBound(lBound, rhsType)) continue;
                return false;
            }
            for (Type lBound : lLowerBounds) {
                if (TypeUtils.isAssignableBound(rhsType, lBound)) continue;
                return false;
            }
        }
        return true;
    }

    public static boolean isAssignableBound(@Nullable Type lhsType, @Nullable Type rhsType) {
        if (rhsType == null) {
            return true;
        }
        if (lhsType == null) {
            return false;
        }
        return TypeUtils.isAssignable(lhsType, rhsType);
    }
}

