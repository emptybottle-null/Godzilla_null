/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

final class Item {
    int a;
    int b;
    int c;
    long d;
    float e;
    double f;
    String g;
    String h;
    String i;
    int j;
    Item k;

    Item() {
    }

    Item(int n) {
        this.a = n;
    }

    Item(int n, Item item) {
        this.a = n;
        this.b = item.b;
        this.c = item.c;
        this.d = item.d;
        this.e = item.e;
        this.f = item.f;
        this.g = item.g;
        this.h = item.h;
        this.i = item.i;
        this.j = item.j;
    }

    void a(int n) {
        this.b = 3;
        this.c = n;
        this.j = Integer.MAX_VALUE & this.b + n;
    }

    void a(long l) {
        this.b = 5;
        this.d = l;
        this.j = Integer.MAX_VALUE & this.b + (int)l;
    }

    void a(float f) {
        this.b = 4;
        this.e = f;
        this.j = Integer.MAX_VALUE & this.b + (int)f;
    }

    void a(double d) {
        this.b = 6;
        this.f = d;
        this.j = Integer.MAX_VALUE & this.b + (int)d;
    }

    void a(int n, String string, String string2, String string3) {
        this.b = n;
        this.g = string;
        this.h = string2;
        this.i = string3;
        switch (n) {
            case 1: 
            case 7: 
            case 8: {
                this.j = Integer.MAX_VALUE & n + string.hashCode();
                return;
            }
            case 12: {
                this.j = Integer.MAX_VALUE & n + string.hashCode() * string2.hashCode();
                return;
            }
        }
        this.j = Integer.MAX_VALUE & n + string.hashCode() * string2.hashCode() * string3.hashCode();
    }

    boolean a(Item item) {
        if (item.b == this.b) {
            switch (this.b) {
                case 3: {
                    return item.c == this.c;
                }
                case 5: {
                    return item.d == this.d;
                }
                case 4: {
                    return item.e == this.e;
                }
                case 6: {
                    return item.f == this.f;
                }
                case 1: 
                case 7: 
                case 8: {
                    return item.g.equals(this.g);
                }
                case 12: {
                    return item.g.equals(this.g) && item.h.equals(this.h);
                }
            }
            return item.g.equals(this.g) && item.h.equals(this.h) && item.i.equals(this.i);
        }
        return false;
    }
}

