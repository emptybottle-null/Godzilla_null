/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.SequencesComparator;

public class ListUtils {
    private ListUtils() {
    }

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    public static <T> List<T> defaultIfNull(List<T> list, List<T> defaultList) {
        return list == null ? defaultList : list;
    }

    public static <E> List<E> intersection(List<? extends E> list1, List<? extends E> list2) {
        ArrayList<E> result = new ArrayList<E>();
        List<E> smaller = list1;
        List<E> larger = list2;
        if (list1.size() > list2.size()) {
            smaller = list2;
            larger = list1;
        }
        HashSet<E> hashSet = new HashSet<E>(smaller);
        for (E e : larger) {
            if (!hashSet.contains(e)) continue;
            result.add(e);
            hashSet.remove(e);
        }
        return result;
    }

    public static <E> List<E> subtract(List<E> list1, List<? extends E> list2) {
        ArrayList<E> result = new ArrayList<E>();
        HashBag<E> bag = new HashBag<E>(list2);
        for (E e : list1) {
            if (bag.remove(e, 1)) continue;
            result.add(e);
        }
        return result;
    }

    public static <E> List<E> sum(List<? extends E> list1, List<? extends E> list2) {
        return ListUtils.subtract(ListUtils.union(list1, list2), ListUtils.intersection(list1, list2));
    }

    public static <E> List<E> union(List<? extends E> list1, List<? extends E> list2) {
        ArrayList<E> result = new ArrayList<E>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <E> List<E> select(Collection<? extends E> inputCollection, Predicate<? super E> predicate) {
        return CollectionUtils.select(inputCollection, predicate, new ArrayList(inputCollection.size()));
    }

    public static <E> List<E> selectRejected(Collection<? extends E> inputCollection, Predicate<? super E> predicate) {
        return CollectionUtils.selectRejected(inputCollection, predicate, new ArrayList(inputCollection.size()));
    }

    public static boolean isEqualList(Collection<?> list1, Collection<?> list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        Iterator<?> it1 = list1.iterator();
        Iterator<?> it2 = list2.iterator();
        Object obj1 = null;
        Object obj2 = null;
        while (it1.hasNext() && it2.hasNext()) {
            obj1 = it1.next();
            obj2 = it2.next();
            if (obj1 != null ? obj1.equals(obj2) : obj2 == null) continue;
            return false;
        }
        return !it1.hasNext() && !it2.hasNext();
    }

    public static int hashCodeForList(Collection<?> list) {
        if (list == null) {
            return 0;
        }
        int hashCode = 1;
        for (Object obj : list) {
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    public static <E> List<E> retainAll(Collection<E> collection, Collection<?> retain) {
        ArrayList<E> list = new ArrayList<E>(Math.min(collection.size(), retain.size()));
        for (E obj : collection) {
            if (!retain.contains(obj)) continue;
            list.add(obj);
        }
        return list;
    }

    public static <E> List<E> removeAll(Collection<E> collection, Collection<?> remove) {
        ArrayList<E> list = new ArrayList<E>();
        for (E obj : collection) {
            if (remove.contains(obj)) continue;
            list.add(obj);
        }
        return list;
    }

    public static <E> List<E> synchronizedList(List<E> list) {
        return Collections.synchronizedList(list);
    }

    public static <E> List<E> unmodifiableList(List<? extends E> list) {
        return UnmodifiableList.unmodifiableList(list);
    }

    public static <E> List<E> predicatedList(List<E> list, Predicate<E> predicate) {
        return PredicatedList.predicatedList(list, predicate);
    }

    public static <E> List<E> transformedList(List<E> list, Transformer<? super E, ? extends E> transformer) {
        return TransformedList.transformingList(list, transformer);
    }

    public static <E> List<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return LazyList.lazyList(list, factory);
    }

    public static <E> List<E> lazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        return LazyList.lazyList(list, transformer);
    }

    public static <E> List<E> fixedSizeList(List<E> list) {
        return FixedSizeList.fixedSizeList(list);
    }

    public static <E> int indexOf(List<E> list, Predicate<E> predicate) {
        if (list != null && predicate != null) {
            for (int i = 0; i < list.size(); ++i) {
                E item = list.get(i);
                if (!predicate.evaluate(item)) continue;
                return i;
            }
        }
        return -1;
    }

    public static <E> List<E> longestCommonSubsequence(List<E> a, List<E> b) {
        return ListUtils.longestCommonSubsequence(a, b, DefaultEquator.defaultEquator());
    }

    public static <E> List<E> longestCommonSubsequence(List<E> a, List<E> b, Equator<? super E> equator) {
        if (a == null || b == null) {
            throw new NullPointerException("List must not be null");
        }
        if (equator == null) {
            throw new NullPointerException("Equator must not be null");
        }
        SequencesComparator<E> comparator = new SequencesComparator<E>(a, b, equator);
        EditScript<E> script = comparator.getScript();
        LcsVisitor visitor = new LcsVisitor();
        script.visit(visitor);
        return visitor.getSubSequence();
    }

    public static String longestCommonSubsequence(CharSequence a, CharSequence b) {
        if (a == null || b == null) {
            throw new NullPointerException("CharSequence must not be null");
        }
        List<Character> lcs = ListUtils.longestCommonSubsequence(new CharSequenceAsList(a), new CharSequenceAsList(b));
        StringBuilder sb = new StringBuilder();
        for (Character ch : lcs) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        return new Partition(list, size);
    }

    private static class Partition<T>
    extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        private Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            int listSize = this.size();
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            }
            if (index >= listSize) {
                throw new IndexOutOfBoundsException("Index " + index + " must be less than size " + listSize);
            }
            int start = index * this.size;
            int end = Math.min(start + this.size, this.list.size());
            return this.list.subList(start, end);
        }

        @Override
        public int size() {
            return (int)Math.ceil((double)this.list.size() / (double)this.size);
        }

        @Override
        public boolean isEmpty() {
            return this.list.isEmpty();
        }
    }

    private static final class CharSequenceAsList
    extends AbstractList<Character> {
        private final CharSequence sequence;

        public CharSequenceAsList(CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override
        public Character get(int index) {
            return Character.valueOf(this.sequence.charAt(index));
        }

        @Override
        public int size() {
            return this.sequence.length();
        }
    }

    private static final class LcsVisitor<E>
    implements CommandVisitor<E> {
        private final ArrayList<E> sequence = new ArrayList();

        @Override
        public void visitInsertCommand(E object) {
        }

        @Override
        public void visitDeleteCommand(E object) {
        }

        @Override
        public void visitKeepCommand(E object) {
            this.sequence.add(object);
        }

        public List<E> getSubSequence() {
            return this.sequence;
        }
    }
}

