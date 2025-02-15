/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Frequency
implements Serializable {
    private static final long serialVersionUID = -3845586908418844111L;
    private final SortedMap<Comparable<?>, Long> freqTable;

    public Frequency() {
        this.freqTable = new TreeMap();
    }

    public Frequency(Comparator<?> comparator) {
        this.freqTable = new TreeMap(comparator);
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getPercentInstance();
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
        for (Comparable<?> value : this.freqTable.keySet()) {
            outBuffer.append(value);
            outBuffer.append('\t');
            outBuffer.append(this.getCount(value));
            outBuffer.append('\t');
            outBuffer.append(nf.format(this.getPct(value)));
            outBuffer.append('\t');
            outBuffer.append(nf.format(this.getCumPct(value)));
            outBuffer.append('\n');
        }
        return outBuffer.toString();
    }

    public void addValue(Comparable<?> v) throws MathIllegalArgumentException {
        this.incrementValue(v, 1L);
    }

    public void addValue(int v) throws MathIllegalArgumentException {
        this.addValue(Long.valueOf(v));
    }

    public void addValue(long v) throws MathIllegalArgumentException {
        this.addValue(Long.valueOf(v));
    }

    public void addValue(char v) throws MathIllegalArgumentException {
        this.addValue(Character.valueOf(v));
    }

    public void incrementValue(Comparable<?> v, long increment) throws MathIllegalArgumentException {
        Long obj = v;
        if (v instanceof Integer) {
            obj = ((Integer)((Object)v)).longValue();
        }
        try {
            Long count = (Long)this.freqTable.get(obj);
            if (count == null) {
                this.freqTable.put(obj, increment);
            } else {
                this.freqTable.put(obj, count + increment);
            }
        } catch (ClassCastException ex) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES, v.getClass().getName());
        }
    }

    public void incrementValue(int v, long increment) throws MathIllegalArgumentException {
        this.incrementValue(Long.valueOf(v), increment);
    }

    public void incrementValue(long v, long increment) throws MathIllegalArgumentException {
        this.incrementValue(Long.valueOf(v), increment);
    }

    public void incrementValue(char v, long increment) throws MathIllegalArgumentException {
        this.incrementValue(Character.valueOf(v), increment);
    }

    public void clear() {
        this.freqTable.clear();
    }

    public Iterator<Comparable<?>> valuesIterator() {
        return this.freqTable.keySet().iterator();
    }

    public Iterator<Map.Entry<Comparable<?>, Long>> entrySetIterator() {
        return this.freqTable.entrySet().iterator();
    }

    public long getSumFreq() {
        long result = 0L;
        Iterator<Long> iterator = this.freqTable.values().iterator();
        while (iterator.hasNext()) {
            result += iterator.next().longValue();
        }
        return result;
    }

    public long getCount(Comparable<?> v) {
        if (v instanceof Integer) {
            return this.getCount(((Integer)v).longValue());
        }
        long result = 0L;
        try {
            Long count = (Long)this.freqTable.get(v);
            if (count != null) {
                result = count;
            }
        } catch (ClassCastException classCastException) {
            // empty catch block
        }
        return result;
    }

    public long getCount(int v) {
        return this.getCount(Long.valueOf(v));
    }

    public long getCount(long v) {
        return this.getCount(Long.valueOf(v));
    }

    public long getCount(char v) {
        return this.getCount(Character.valueOf(v));
    }

    public int getUniqueCount() {
        return this.freqTable.keySet().size();
    }

    public double getPct(Comparable<?> v) {
        long sumFreq = this.getSumFreq();
        if (sumFreq == 0L) {
            return Double.NaN;
        }
        return (double)this.getCount(v) / (double)sumFreq;
    }

    public double getPct(int v) {
        return this.getPct(Long.valueOf(v));
    }

    public double getPct(long v) {
        return this.getPct(Long.valueOf(v));
    }

    public double getPct(char v) {
        return this.getPct(Character.valueOf(v));
    }

    public long getCumFreq(Comparable<?> v) {
        if (this.getSumFreq() == 0L) {
            return 0L;
        }
        if (v instanceof Integer) {
            return this.getCumFreq(((Integer)v).longValue());
        }
        Comparator<Comparable<?>> c = this.freqTable.comparator();
        if (c == null) {
            c = new NaturalComparator();
        }
        long result = 0L;
        try {
            Long value = (Long)this.freqTable.get(v);
            if (value != null) {
                result = value;
            }
        } catch (ClassCastException ex) {
            return result;
        }
        if (c.compare(v, this.freqTable.firstKey()) < 0) {
            return 0L;
        }
        if (c.compare(v, this.freqTable.lastKey()) >= 0) {
            return this.getSumFreq();
        }
        Iterator<Comparable<?>> values = this.valuesIterator();
        while (values.hasNext()) {
            Comparable<?> nextValue = values.next();
            if (c.compare(v, nextValue) > 0) {
                result += this.getCount(nextValue);
                continue;
            }
            return result;
        }
        return result;
    }

    public long getCumFreq(int v) {
        return this.getCumFreq(Long.valueOf(v));
    }

    public long getCumFreq(long v) {
        return this.getCumFreq(Long.valueOf(v));
    }

    public long getCumFreq(char v) {
        return this.getCumFreq(Character.valueOf(v));
    }

    public double getCumPct(Comparable<?> v) {
        long sumFreq = this.getSumFreq();
        if (sumFreq == 0L) {
            return Double.NaN;
        }
        return (double)this.getCumFreq(v) / (double)sumFreq;
    }

    public double getCumPct(int v) {
        return this.getCumPct(Long.valueOf(v));
    }

    public double getCumPct(long v) {
        return this.getCumPct(Long.valueOf(v));
    }

    public double getCumPct(char v) {
        return this.getCumPct(Character.valueOf(v));
    }

    public List<Comparable<?>> getMode() {
        long mostPopular = 0L;
        for (Long l : this.freqTable.values()) {
            long frequency = l;
            if (frequency <= mostPopular) continue;
            mostPopular = frequency;
        }
        ArrayList modeList = new ArrayList();
        for (Map.Entry<Comparable<?>, Long> ent : this.freqTable.entrySet()) {
            long frequency = ent.getValue();
            if (frequency != mostPopular) continue;
            modeList.add(ent.getKey());
        }
        return modeList;
    }

    public void merge(Frequency other) throws NullArgumentException {
        MathUtils.checkNotNull(other, LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        Iterator<Map.Entry<Comparable<?>, Long>> iter = other.entrySetIterator();
        while (iter.hasNext()) {
            Map.Entry<Comparable<?>, Long> entry = iter.next();
            this.incrementValue(entry.getKey(), (long)entry.getValue());
        }
    }

    public void merge(Collection<Frequency> others) throws NullArgumentException {
        MathUtils.checkNotNull(others, LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        for (Frequency freq : others) {
            this.merge(freq);
        }
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.freqTable == null ? 0 : this.freqTable.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Frequency)) {
            return false;
        }
        Frequency other = (Frequency)obj;
        return !(this.freqTable == null ? other.freqTable != null : !this.freqTable.equals(other.freqTable));
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class NaturalComparator<T extends Comparable<T>>
    implements Comparator<Comparable<T>>,
    Serializable {
        private static final long serialVersionUID = -3852193713161395148L;

        private NaturalComparator() {
        }

        @Override
        public int compare(Comparable<T> o1, Comparable<T> o2) {
            return o1.compareTo(o2);
        }
    }
}

