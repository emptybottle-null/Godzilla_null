/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DurationUtils;

public class ThreadUtils {
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();

    public static Thread findThreadById(long threadId) {
        Collection<Thread> result = ThreadUtils.findThreads(new ThreadIdPredicate(threadId));
        return result.isEmpty() ? null : result.iterator().next();
    }

    public static Thread findThreadById(long threadId, String threadGroupName) {
        Validate.notNull(threadGroupName, "threadGroupName", new Object[0]);
        Thread thread = ThreadUtils.findThreadById(threadId);
        if (thread != null && thread.getThreadGroup() != null && thread.getThreadGroup().getName().equals(threadGroupName)) {
            return thread;
        }
        return null;
    }

    public static Thread findThreadById(long threadId, ThreadGroup threadGroup) {
        Validate.notNull(threadGroup, "threadGroup", new Object[0]);
        Thread thread = ThreadUtils.findThreadById(threadId);
        if (thread != null && threadGroup.equals(thread.getThreadGroup())) {
            return thread;
        }
        return null;
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup group, boolean recurse, ThreadGroupPredicate predicate) {
        ThreadGroup[] threadGroups;
        Validate.notNull(group, "group", new Object[0]);
        Validate.notNull(predicate, "predicate", new Object[0]);
        int count = group.activeGroupCount();
        while ((count = group.enumerate(threadGroups = new ThreadGroup[count + count / 2 + 1], recurse)) >= threadGroups.length) {
        }
        ArrayList<ThreadGroup> result = new ArrayList<ThreadGroup>(count);
        for (int i = 0; i < count; ++i) {
            if (!predicate.test(threadGroups[i])) continue;
            result.add(threadGroups[i]);
        }
        return Collections.unmodifiableCollection(result);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate predicate) {
        return ThreadUtils.findThreadGroups(ThreadUtils.getSystemThreadGroup(), true, predicate);
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String threadGroupName) {
        return ThreadUtils.findThreadGroups(new NamePredicate(threadGroupName));
    }

    public static Collection<Thread> findThreads(ThreadGroup group, boolean recurse, ThreadPredicate predicate) {
        Thread[] threads;
        Validate.notNull(group, "The group must not be null", new Object[0]);
        Validate.notNull(predicate, "The predicate must not be null", new Object[0]);
        int count = group.activeCount();
        while ((count = group.enumerate(threads = new Thread[count + count / 2 + 1], recurse)) >= threads.length) {
        }
        ArrayList<Thread> result = new ArrayList<Thread>(count);
        for (int i = 0; i < count; ++i) {
            if (!predicate.test(threads[i])) continue;
            result.add(threads[i]);
        }
        return Collections.unmodifiableCollection(result);
    }

    public static Collection<Thread> findThreads(ThreadPredicate predicate) {
        return ThreadUtils.findThreads(ThreadUtils.getSystemThreadGroup(), true, predicate);
    }

    public static Collection<Thread> findThreadsByName(String threadName) {
        return ThreadUtils.findThreads(new NamePredicate(threadName));
    }

    public static Collection<Thread> findThreadsByName(String threadName, String threadGroupName) {
        Validate.notNull(threadName, "threadName", new Object[0]);
        Validate.notNull(threadGroupName, "threadGroupName", new Object[0]);
        Collection<ThreadGroup> threadGroups = ThreadUtils.findThreadGroups(new NamePredicate(threadGroupName));
        if (threadGroups.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Thread> result = new ArrayList<Thread>();
        NamePredicate threadNamePredicate = new NamePredicate(threadName);
        for (ThreadGroup group : threadGroups) {
            result.addAll(ThreadUtils.findThreads(group, false, threadNamePredicate));
        }
        return Collections.unmodifiableCollection(result);
    }

    public static Collection<Thread> findThreadsByName(String threadName, ThreadGroup threadGroup) {
        return ThreadUtils.findThreads(threadGroup, false, new NamePredicate(threadName));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return ThreadUtils.findThreadGroups(ALWAYS_TRUE_PREDICATE);
    }

    public static Collection<Thread> getAllThreads() {
        return ThreadUtils.findThreads(ALWAYS_TRUE_PREDICATE);
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static void join(Thread thread, Duration duration) throws InterruptedException {
        DurationUtils.accept(thread::join, duration);
    }

    public static void sleep(Duration duration) throws InterruptedException {
        DurationUtils.accept(Thread::sleep, duration);
    }

    @FunctionalInterface
    public static interface ThreadPredicate {
        public boolean test(Thread var1);
    }

    public static class ThreadIdPredicate
    implements ThreadPredicate {
        private final long threadId;

        public ThreadIdPredicate(long threadId) {
            if (threadId <= 0L) {
                throw new IllegalArgumentException("The thread id must be greater than zero");
            }
            this.threadId = threadId;
        }

        @Override
        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    @FunctionalInterface
    public static interface ThreadGroupPredicate {
        public boolean test(ThreadGroup var1);
    }

    public static class NamePredicate
    implements ThreadPredicate,
    ThreadGroupPredicate {
        private final String name;

        public NamePredicate(String name) {
            Validate.notNull(name, "name", new Object[0]);
            this.name = name;
        }

        @Override
        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }

        @Override
        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }
    }

    private static final class AlwaysTruePredicate
    implements ThreadPredicate,
    ThreadGroupPredicate {
        private AlwaysTruePredicate() {
        }

        @Override
        public boolean test(Thread thread) {
            return true;
        }

        @Override
        public boolean test(ThreadGroup threadGroup) {
            return true;
        }
    }
}

