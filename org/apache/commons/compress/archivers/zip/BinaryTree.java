/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.BitStream;
import org.apache.commons.compress.utils.IOUtils;

class BinaryTree {
    private static final int UNDEFINED = -1;
    private static final int NODE = -2;
    private final int[] tree;

    public BinaryTree(int depth) {
        if (depth < 0 || depth > 30) {
            throw new IllegalArgumentException("depth must be bigger than 0 and not bigger than 30 but is " + depth);
        }
        this.tree = new int[(int)((1L << depth + 1) - 1L)];
        Arrays.fill(this.tree, -1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void addLeaf(int node, int path, int depth, int value) {
        if (depth == 0) {
            if (this.tree[node] != -1) throw new IllegalArgumentException("Tree value at index " + node + " has already been assigned (" + this.tree[node] + ")");
            this.tree[node] = value;
            return;
        } else {
            this.tree[node] = -2;
            int nextChild = 2 * node + 1 + (path & 1);
            this.addLeaf(nextChild, path >>> 1, depth - 1, value);
        }
    }

    public int read(BitStream stream) throws IOException {
        int value;
        int bit;
        int currentIndex = 0;
        while (true) {
            if ((bit = stream.nextBit()) == -1) {
                return -1;
            }
            int childIndex = 2 * currentIndex + 1 + bit;
            value = this.tree[childIndex];
            if (value != -2) break;
            currentIndex = childIndex;
        }
        if (value != -1) {
            return value;
        }
        throw new IOException("The child " + bit + " of node at index " + currentIndex + " is not defined");
    }

    static BinaryTree decode(InputStream in, int totalNumberOfValues) throws IOException {
        if (totalNumberOfValues < 0) {
            throw new IllegalArgumentException("totalNumberOfValues must be bigger than 0, is " + totalNumberOfValues);
        }
        int size = in.read() + 1;
        if (size == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
        }
        byte[] encodedTree = new byte[size];
        int read = IOUtils.readFully(in, encodedTree);
        if (read != size) {
            throw new EOFException();
        }
        int maxLength = 0;
        int[] originalBitLengths = new int[totalNumberOfValues];
        int pos = 0;
        for (byte b : encodedTree) {
            int numberOfValues = ((b & 0xF0) >> 4) + 1;
            if (pos + numberOfValues > totalNumberOfValues) {
                throw new IOException("Number of values exceeds given total number of values");
            }
            int bitLength = (b & 0xF) + 1;
            for (int j = 0; j < numberOfValues; ++j) {
                originalBitLengths[pos++] = bitLength;
            }
            maxLength = Math.max(maxLength, bitLength);
        }
        int[] permutation = new int[originalBitLengths.length];
        for (int k = 0; k < permutation.length; ++k) {
            permutation[k] = k;
        }
        int c = 0;
        int[] sortedBitLengths = new int[originalBitLengths.length];
        for (int k = 0; k < originalBitLengths.length; ++k) {
            for (int l = 0; l < originalBitLengths.length; ++l) {
                if (originalBitLengths[l] != k) continue;
                sortedBitLengths[c] = k;
                permutation[c] = l;
                ++c;
            }
        }
        int code = 0;
        int codeIncrement = 0;
        int lastBitLength = 0;
        int[] codes = new int[totalNumberOfValues];
        for (int i = totalNumberOfValues - 1; i >= 0; --i) {
            code += codeIncrement;
            if (sortedBitLengths[i] != lastBitLength) {
                lastBitLength = sortedBitLengths[i];
                codeIncrement = 1 << 16 - lastBitLength;
            }
            codes[permutation[i]] = code;
        }
        BinaryTree tree = new BinaryTree(maxLength);
        for (int k = 0; k < codes.length; ++k) {
            int bitLength = originalBitLengths[k];
            if (bitLength <= 0) continue;
            tree.addLeaf(0, Integer.reverse(codes[k] << 16), bitLength, k);
        }
        return tree;
    }
}

