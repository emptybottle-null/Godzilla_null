/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  kotlin.Unit
 *  kotlin.coroutines.Continuation
 *  kotlin.coroutines.CoroutineContext
 *  kotlin.jvm.JvmClassMappingKt
 *  kotlin.reflect.KCallable
 *  kotlin.reflect.KClassifier
 *  kotlin.reflect.KFunction
 *  kotlin.reflect.full.KCallables
 *  kotlin.reflect.jvm.ReflectJvmMapping
 *  kotlinx.coroutines.BuildersKt
 *  kotlinx.coroutines.CoroutineScope
 *  kotlinx.coroutines.CoroutineStart
 *  kotlinx.coroutines.Deferred
 *  kotlinx.coroutines.Dispatchers
 *  kotlinx.coroutines.GlobalScope
 *  kotlinx.coroutines.flow.Flow
 *  kotlinx.coroutines.reactor.MonoKt
 *  kotlinx.coroutines.reactor.ReactorFlowKt
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package org.springframework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.reactor.MonoKt;
import kotlinx.coroutines.reactor.ReactorFlowKt;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CoroutinesUtils {
    public static <T> Mono<T> deferredToMono(Deferred<T> source) {
        return MonoKt.mono((CoroutineContext)Dispatchers.getUnconfined(), (scope, continuation) -> source.await(continuation));
    }

    public static <T> Deferred<T> monoToDeferred(Mono<T> source) {
        return BuildersKt.async((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)Dispatchers.getUnconfined(), (CoroutineStart)CoroutineStart.DEFAULT, (scope, continuation) -> MonoKt.awaitSingleOrNull((Mono)source, (Continuation)continuation));
    }

    public static Publisher<?> invokeSuspendingFunction(Method method, Object target, Object ... args) {
        KFunction function = Objects.requireNonNull(ReflectJvmMapping.getKotlinFunction((Method)method));
        KClassifier classifier = function.getReturnType().getClassifier();
        Mono mono = MonoKt.mono((CoroutineContext)Dispatchers.getUnconfined(), (scope, continuation) -> KCallables.callSuspend((KCallable)function, (Object[])CoroutinesUtils.getSuspendedFunctionArgs(target, args), (Continuation)continuation)).filter(result -> !Objects.equals(result, Unit.INSTANCE)).onErrorMap(InvocationTargetException.class, InvocationTargetException::getTargetException);
        if (classifier != null && classifier.equals(JvmClassMappingKt.getKotlinClass(Flow.class))) {
            return mono.flatMapMany(CoroutinesUtils::asFlux);
        }
        return mono;
    }

    private static Object[] getSuspendedFunctionArgs(Object target, Object ... args) {
        Object[] functionArgs = new Object[args.length];
        functionArgs[0] = target;
        System.arraycopy(args, 0, functionArgs, 1, args.length - 1);
        return functionArgs;
    }

    private static Flux<?> asFlux(Object flow) {
        return ReactorFlowKt.asFlux((Flow)((Flow)flow));
    }
}

