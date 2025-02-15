/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.util;

import java.util.Comparator;
import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.util.ParallelSorterEmitter;
import org.springframework.cglib.util.SorterTemplate;

public abstract class ParallelSorter
extends SorterTemplate {
    protected Object[] a;
    private Comparer comparer;

    protected ParallelSorter() {
    }

    public abstract ParallelSorter newInstance(Object[] var1);

    public static ParallelSorter create(Object[] arrays) {
        Generator gen = new Generator();
        gen.setArrays(arrays);
        return gen.create();
    }

    private int len() {
        return ((Object[])this.a[0]).length;
    }

    public void quickSort(int index) {
        this.quickSort(index, 0, this.len(), null);
    }

    public void quickSort(int index, int lo, int hi) {
        this.quickSort(index, lo, hi, null);
    }

    public void quickSort(int index, Comparator cmp) {
        this.quickSort(index, 0, this.len(), cmp);
    }

    public void quickSort(int index, int lo, int hi, Comparator cmp) {
        this.chooseComparer(index, cmp);
        super.quickSort(lo, hi - 1);
    }

    public void mergeSort(int index) {
        this.mergeSort(index, 0, this.len(), null);
    }

    public void mergeSort(int index, int lo, int hi) {
        this.mergeSort(index, lo, hi, null);
    }

    public void mergeSort(int index, Comparator cmp) {
        this.mergeSort(index, 0, this.len(), cmp);
    }

    public void mergeSort(int index, int lo, int hi, Comparator cmp) {
        this.chooseComparer(index, cmp);
        super.mergeSort(lo, hi - 1);
    }

    private void chooseComparer(int index, Comparator cmp) {
        Object array = this.a[index];
        Class<?> type = array.getClass().getComponentType();
        this.comparer = type.equals(Integer.TYPE) ? new IntComparer((int[])array) : (type.equals(Long.TYPE) ? new LongComparer((long[])array) : (type.equals(Double.TYPE) ? new DoubleComparer((double[])array) : (type.equals(Float.TYPE) ? new FloatComparer((float[])array) : (type.equals(Short.TYPE) ? new ShortComparer((short[])array) : (type.equals(Byte.TYPE) ? new ByteComparer((byte[])array) : (cmp != null ? new ComparatorComparer((Object[])array, cmp) : new ObjectComparer((Object[])array)))))));
    }

    protected int compare(int i, int j) {
        return this.comparer.compare(i, j);
    }

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(ParallelSorter.class.getName());
        private Object[] arrays;

        public Generator() {
            super(SOURCE);
        }

        protected ClassLoader getDefaultClassLoader() {
            return null;
        }

        public void setArrays(Object[] arrays) {
            this.arrays = arrays;
        }

        public ParallelSorter create() {
            return (ParallelSorter)super.create(ClassesKey.create(this.arrays));
        }

        public void generateClass(ClassVisitor v) throws Exception {
            if (this.arrays.length == 0) {
                throw new IllegalArgumentException("No arrays specified to sort");
            }
            for (int i = 0; i < this.arrays.length; ++i) {
                if (this.arrays[i].getClass().isArray()) continue;
                throw new IllegalArgumentException(this.arrays[i].getClass() + " is not an array");
            }
            new ParallelSorterEmitter(v, this.getClassName(), this.arrays);
        }

        protected Object firstInstance(Class type) {
            return ((ParallelSorter)ReflectUtils.newInstance(type)).newInstance(this.arrays);
        }

        protected Object nextInstance(Object instance) {
            return ((ParallelSorter)instance).newInstance(this.arrays);
        }
    }

    static class ByteComparer
    implements Comparer {
        private byte[] a;

        public ByteComparer(byte[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            return this.a[i] - this.a[j];
        }
    }

    static class ShortComparer
    implements Comparer {
        private short[] a;

        public ShortComparer(short[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            return this.a[i] - this.a[j];
        }
    }

    static class DoubleComparer
    implements Comparer {
        private double[] a;

        public DoubleComparer(double[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            double vi = this.a[i];
            double vj = this.a[j];
            return vi == vj ? 0 : (vi > vj ? 1 : -1);
        }
    }

    static class FloatComparer
    implements Comparer {
        private float[] a;

        public FloatComparer(float[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            float vi = this.a[i];
            float vj = this.a[j];
            return vi == vj ? 0 : (vi > vj ? 1 : -1);
        }
    }

    static class LongComparer
    implements Comparer {
        private long[] a;

        public LongComparer(long[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            long vi = this.a[i];
            long vj = this.a[j];
            return vi == vj ? 0 : (vi > vj ? 1 : -1);
        }
    }

    static class IntComparer
    implements Comparer {
        private int[] a;

        public IntComparer(int[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            return this.a[i] - this.a[j];
        }
    }

    static class ObjectComparer
    implements Comparer {
        private Object[] a;

        public ObjectComparer(Object[] a) {
            this.a = a;
        }

        public int compare(int i, int j) {
            return ((Comparable)this.a[i]).compareTo(this.a[j]);
        }
    }

    static class ComparatorComparer
    implements Comparer {
        private Object[] a;
        private Comparator cmp;

        public ComparatorComparer(Object[] a, Comparator cmp) {
            this.a = a;
            this.cmp = cmp;
        }

        public int compare(int i, int j) {
            return this.cmp.compare(this.a[i], this.a[j]);
        }
    }

    static interface Comparer {
        public int compare(int var1, int var2);
    }
}

