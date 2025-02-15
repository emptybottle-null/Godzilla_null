/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.aspectj.bridge.IMessageHandler
 *  org.aspectj.weaver.ResolvedType
 *  org.aspectj.weaver.World
 *  org.aspectj.weaver.bcel.BcelWorld
 *  org.aspectj.weaver.patterns.Bindings
 *  org.aspectj.weaver.patterns.FormalBinding
 *  org.aspectj.weaver.patterns.IScope
 *  org.aspectj.weaver.patterns.PatternParser
 *  org.aspectj.weaver.patterns.SimpleScope
 *  org.aspectj.weaver.patterns.TypePattern
 */
package org.springframework.core.type.filter;

import java.io.IOException;
import org.aspectj.bridge.IMessageHandler;
import org.aspectj.weaver.ResolvedType;
import org.aspectj.weaver.World;
import org.aspectj.weaver.bcel.BcelWorld;
import org.aspectj.weaver.patterns.Bindings;
import org.aspectj.weaver.patterns.FormalBinding;
import org.aspectj.weaver.patterns.IScope;
import org.aspectj.weaver.patterns.PatternParser;
import org.aspectj.weaver.patterns.SimpleScope;
import org.aspectj.weaver.patterns.TypePattern;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;

public class AspectJTypeFilter
implements TypeFilter {
    private final World world;
    private final TypePattern typePattern;

    public AspectJTypeFilter(String typePatternExpression, @Nullable ClassLoader classLoader) {
        this.world = new BcelWorld(classLoader, IMessageHandler.THROW, null);
        this.world.setBehaveInJava5Way(true);
        PatternParser patternParser = new PatternParser(typePatternExpression);
        TypePattern typePattern = patternParser.parseTypePattern();
        typePattern.resolve(this.world);
        SimpleScope scope = new SimpleScope(this.world, new FormalBinding[0]);
        this.typePattern = typePattern.resolveBindings((IScope)scope, Bindings.NONE, false, false);
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        String className = metadataReader.getClassMetadata().getClassName();
        ResolvedType resolvedType = this.world.resolve(className);
        return this.typePattern.matchesStatically(resolvedType);
    }
}

