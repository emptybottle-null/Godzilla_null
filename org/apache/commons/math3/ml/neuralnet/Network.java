/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.ml.neuralnet.Neuron;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Network
implements Iterable<Neuron>,
Serializable {
    private static final long serialVersionUID = 20130207L;
    private final ConcurrentHashMap<Long, Neuron> neuronMap = new ConcurrentHashMap();
    private final AtomicLong nextId;
    private final int featureSize;
    private final ConcurrentHashMap<Long, Set<Long>> linkMap = new ConcurrentHashMap();

    Network(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
        int i;
        int numNeurons = neuronList.length;
        if (numNeurons != neighbourIdList.length) {
            throw new MathIllegalStateException();
        }
        for (i = 0; i < numNeurons; ++i) {
            Neuron n = neuronList[i];
            long id = n.getIdentifier();
            if (id >= nextId) {
                throw new MathIllegalStateException();
            }
            this.neuronMap.put(id, n);
            this.linkMap.put(id, new HashSet());
        }
        for (i = 0; i < numNeurons; ++i) {
            long aId = neuronList[i].getIdentifier();
            Set<Long> aLinks = this.linkMap.get(aId);
            long[] arr$ = neighbourIdList[i];
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; ++i$) {
                Long bId = arr$[i$];
                if (this.neuronMap.get(bId) == null) {
                    throw new MathIllegalStateException();
                }
                this.addLinkToLinkSet(aLinks, bId);
            }
        }
        this.nextId = new AtomicLong(nextId);
        this.featureSize = featureSize;
    }

    public Network(long initialIdentifier, int featureSize) {
        this.nextId = new AtomicLong(initialIdentifier);
        this.featureSize = featureSize;
    }

    public synchronized Network copy() {
        Network copy = new Network(this.nextId.get(), this.featureSize);
        for (Map.Entry<Long, Neuron> entry : this.neuronMap.entrySet()) {
            copy.neuronMap.put(entry.getKey(), entry.getValue().copy());
        }
        for (Map.Entry<Long, Object> entry : this.linkMap.entrySet()) {
            copy.linkMap.put(entry.getKey(), new HashSet((Collection)entry.getValue()));
        }
        return copy;
    }

    @Override
    public Iterator<Neuron> iterator() {
        return this.neuronMap.values().iterator();
    }

    public Collection<Neuron> getNeurons(Comparator<Neuron> comparator) {
        ArrayList<Neuron> neurons = new ArrayList<Neuron>();
        neurons.addAll(this.neuronMap.values());
        Collections.sort(neurons, comparator);
        return neurons;
    }

    public long createNeuron(double[] features) {
        if (features.length != this.featureSize) {
            throw new DimensionMismatchException(features.length, this.featureSize);
        }
        long id = this.createNextId();
        this.neuronMap.put(id, new Neuron(id, features));
        this.linkMap.put(id, new HashSet());
        return id;
    }

    public void deleteNeuron(Neuron neuron) {
        Collection<Neuron> neighbours = this.getNeighbours(neuron);
        for (Neuron n : neighbours) {
            this.deleteLink(n, neuron);
        }
        this.neuronMap.remove(neuron.getIdentifier());
    }

    public int getFeaturesSize() {
        return this.featureSize;
    }

    public void addLink(Neuron a, Neuron b) {
        long aId = a.getIdentifier();
        long bId = b.getIdentifier();
        if (a != this.getNeuron(aId)) {
            throw new NoSuchElementException(Long.toString(aId));
        }
        if (b != this.getNeuron(bId)) {
            throw new NoSuchElementException(Long.toString(bId));
        }
        this.addLinkToLinkSet(this.linkMap.get(aId), bId);
    }

    private void addLinkToLinkSet(Set<Long> linkSet, long id) {
        linkSet.add(id);
    }

    public void deleteLink(Neuron a, Neuron b) {
        long aId = a.getIdentifier();
        long bId = b.getIdentifier();
        if (a != this.getNeuron(aId)) {
            throw new NoSuchElementException(Long.toString(aId));
        }
        if (b != this.getNeuron(bId)) {
            throw new NoSuchElementException(Long.toString(bId));
        }
        this.deleteLinkFromLinkSet(this.linkMap.get(aId), bId);
    }

    private void deleteLinkFromLinkSet(Set<Long> linkSet, long id) {
        linkSet.remove(id);
    }

    public Neuron getNeuron(long id) {
        Neuron n = this.neuronMap.get(id);
        if (n == null) {
            throw new NoSuchElementException(Long.toString(id));
        }
        return n;
    }

    public Collection<Neuron> getNeighbours(Iterable<Neuron> neurons) {
        return this.getNeighbours(neurons, null);
    }

    public Collection<Neuron> getNeighbours(Iterable<Neuron> neurons, Iterable<Neuron> exclude) {
        HashSet idList = new HashSet();
        for (Neuron n : neurons) {
            idList.addAll(this.linkMap.get(n.getIdentifier()));
        }
        if (exclude != null) {
            for (Neuron n : exclude) {
                idList.remove(n.getIdentifier());
            }
        }
        ArrayList<Neuron> neuronList = new ArrayList<Neuron>();
        for (Long id : idList) {
            neuronList.add(this.getNeuron(id));
        }
        return neuronList;
    }

    public Collection<Neuron> getNeighbours(Neuron neuron) {
        return this.getNeighbours(neuron, null);
    }

    public Collection<Neuron> getNeighbours(Neuron neuron, Iterable<Neuron> exclude) {
        Set<Long> idList = this.linkMap.get(neuron.getIdentifier());
        if (exclude != null) {
            for (Neuron n : exclude) {
                idList.remove(n.getIdentifier());
            }
        }
        ArrayList<Neuron> neuronList = new ArrayList<Neuron>();
        for (Long id : idList) {
            neuronList.add(this.getNeuron(id));
        }
        return neuronList;
    }

    private Long createNextId() {
        return this.nextId.getAndIncrement();
    }

    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        Neuron[] neuronList = this.neuronMap.values().toArray(new Neuron[0]);
        long[][] neighbourIdList = new long[neuronList.length][];
        for (int i = 0; i < neuronList.length; ++i) {
            Collection<Neuron> neighbours = this.getNeighbours(neuronList[i]);
            long[] neighboursId = new long[neighbours.size()];
            int count = 0;
            for (Neuron n : neighbours) {
                neighboursId[count] = n.getIdentifier();
                ++count;
            }
            neighbourIdList[i] = neighboursId;
        }
        return new SerializationProxy(this.nextId.get(), this.featureSize, neuronList, neighbourIdList);
    }

    private static class SerializationProxy
    implements Serializable {
        private static final long serialVersionUID = 20130207L;
        private final long nextId;
        private final int featureSize;
        private final Neuron[] neuronList;
        private final long[][] neighbourIdList;

        SerializationProxy(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
            this.nextId = nextId;
            this.featureSize = featureSize;
            this.neuronList = neuronList;
            this.neighbourIdList = neighbourIdList;
        }

        private Object readResolve() {
            return new Network(this.nextId, this.featureSize, this.neuronList, this.neighbourIdList);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class NeuronIdentifierComparator
    implements Comparator<Neuron>,
    Serializable {
        private static final long serialVersionUID = 20130207L;

        @Override
        public int compare(Neuron a, Neuron b) {
            long bId;
            long aId = a.getIdentifier();
            return aId < (bId = b.getIdentifier()) ? -1 : (aId > bId ? 1 : 0);
        }
    }
}

