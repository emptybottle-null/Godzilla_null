/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.lang.reflect.Field;
import java.security.AccessControlException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.classreading.RecursiveAnnotationArrayVisitor;
import org.springframework.core.type.classreading.RecursiveAnnotationAttributesVisitor;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

@Deprecated
abstract class AbstractRecursiveAnnotationVisitor
extends AnnotationVisitor {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected final AnnotationAttributes attributes;
    @Nullable
    protected final ClassLoader classLoader;

    public AbstractRecursiveAnnotationVisitor(@Nullable ClassLoader classLoader, AnnotationAttributes attributes) {
        super(0x10A0000);
        this.classLoader = classLoader;
        this.attributes = attributes;
    }

    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String attributeName, String asmTypeDescriptor) {
        String annotationType = Type.getType(asmTypeDescriptor).getClassName();
        AnnotationAttributes nestedAttributes = new AnnotationAttributes(annotationType, this.classLoader);
        this.attributes.put(attributeName, nestedAttributes);
        return new RecursiveAnnotationAttributesVisitor(annotationType, nestedAttributes, this.classLoader);
    }

    @Override
    public AnnotationVisitor visitArray(String attributeName) {
        return new RecursiveAnnotationArrayVisitor(attributeName, this.attributes, this.classLoader);
    }

    @Override
    public void visitEnum(String attributeName, String asmTypeDescriptor, String attributeValue) {
        Object newValue = this.getEnumValue(asmTypeDescriptor, attributeValue);
        this.visit(attributeName, newValue);
    }

    protected Object getEnumValue(String asmTypeDescriptor, String attributeValue) {
        Object valueToUse = attributeValue;
        try {
            Class<?> enumType = ClassUtils.forName(Type.getType(asmTypeDescriptor).getClassName(), this.classLoader);
            Field enumConstant = ReflectionUtils.findField(enumType, attributeValue);
            if (enumConstant != null) {
                ReflectionUtils.makeAccessible(enumConstant);
                valueToUse = enumConstant.get(null);
            }
        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            this.logger.debug("Failed to classload enum type while reading annotation metadata", ex);
        } catch (IllegalAccessException | AccessControlException ex) {
            this.logger.debug("Could not access enum value while reading annotation metadata", ex);
        }
        return valueToUse;
    }
}

