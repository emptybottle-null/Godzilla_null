/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.util.Vector;

class DTLSReassembler {
    private short msg_type;
    private byte[] body;
    private Vector missing = new Vector();

    DTLSReassembler(short s, int n) {
        this.msg_type = s;
        this.body = new byte[n];
        this.missing.addElement(new Range(0, n));
    }

    short getMsgType() {
        return this.msg_type;
    }

    byte[] getBodyIfComplete() {
        return this.missing.isEmpty() ? this.body : null;
    }

    void contributeFragment(short s, int n, byte[] byArray, int n2, int n3, int n4) {
        Range range;
        int n5 = n3 + n4;
        if (this.msg_type != s || this.body.length != n || n5 > n) {
            return;
        }
        if (n4 == 0) {
            Range range2;
            if (n3 == 0 && !this.missing.isEmpty() && (range2 = (Range)this.missing.firstElement()).getEnd() == 0) {
                this.missing.removeElementAt(0);
            }
            return;
        }
        for (int i = 0; i < this.missing.size() && (range = (Range)this.missing.elementAt(i)).getStart() < n5; ++i) {
            if (range.getEnd() <= n3) continue;
            int n6 = Math.max(range.getStart(), n3);
            int n7 = Math.min(range.getEnd(), n5);
            int n8 = n7 - n6;
            System.arraycopy(byArray, n2 + n6 - n3, this.body, n6, n8);
            if (n6 == range.getStart()) {
                if (n7 == range.getEnd()) {
                    this.missing.removeElementAt(i--);
                    continue;
                }
                range.setStart(n7);
                continue;
            }
            if (n7 != range.getEnd()) {
                this.missing.insertElementAt(new Range(n7, range.getEnd()), ++i);
            }
            range.setEnd(n6);
        }
    }

    void reset() {
        this.missing.removeAllElements();
        this.missing.addElement(new Range(0, this.body.length));
    }

    private static class Range {
        private int start;
        private int end;

        Range(int n, int n2) {
            this.start = n;
            this.end = n2;
        }

        public int getStart() {
            return this.start;
        }

        public void setStart(int n) {
            this.start = n;
        }

        public int getEnd() {
            return this.end;
        }

        public void setEnd(int n) {
            this.end = n;
        }
    }
}

