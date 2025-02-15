/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.math.BigInteger;
import org.apache.poi.hpsf.Array;
import org.apache.poi.hpsf.Blob;
import org.apache.poi.hpsf.ClipboardData;
import org.apache.poi.hpsf.CodePageString;
import org.apache.poi.hpsf.Currency;
import org.apache.poi.hpsf.Date;
import org.apache.poi.hpsf.Decimal;
import org.apache.poi.hpsf.Filetime;
import org.apache.poi.hpsf.GUID;
import org.apache.poi.hpsf.IndirectPropertyName;
import org.apache.poi.hpsf.UnicodeString;
import org.apache.poi.hpsf.VariantBool;
import org.apache.poi.hpsf.Vector;
import org.apache.poi.hpsf.VersionedStream;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class TypedPropertyValue {
    private static final POILogger LOG = POILogFactory.getLogger(TypedPropertyValue.class);
    private int _type;
    private Object _value;

    public TypedPropertyValue(int type, Object value) {
        this._type = type;
        this._value = value;
    }

    public Object getValue() {
        return this._value;
    }

    public void read(LittleEndianByteArrayInputStream lei) {
        this._type = lei.readShort();
        short padding = lei.readShort();
        if (padding != 0) {
            LOG.log(5, "TypedPropertyValue padding at offset " + lei.getReadIndex() + " MUST be 0, but it's value is " + padding);
        }
        this.readValue(lei);
    }

    public void readValue(LittleEndianByteArrayInputStream lei) {
        switch (this._type) {
            case 0: 
            case 1: {
                this._value = null;
                break;
            }
            case 16: {
                this._value = lei.readByte();
                break;
            }
            case 17: {
                this._value = lei.readUByte();
                break;
            }
            case 2: {
                this._value = lei.readShort();
                break;
            }
            case 18: {
                this._value = lei.readUShort();
                break;
            }
            case 3: 
            case 22: {
                this._value = lei.readInt();
                break;
            }
            case 10: 
            case 19: 
            case 23: {
                this._value = lei.readUInt();
                break;
            }
            case 20: {
                this._value = lei.readLong();
                break;
            }
            case 21: {
                byte[] biBytesLE = new byte[8];
                lei.readFully(biBytesLE);
                byte[] biBytesBE = new byte[9];
                int i = biBytesLE.length;
                for (byte b : biBytesLE) {
                    if (i <= 8) {
                        biBytesBE[i] = b;
                    }
                    --i;
                }
                this._value = new BigInteger(biBytesBE);
                break;
            }
            case 4: {
                this._value = Float.valueOf(Float.intBitsToFloat(lei.readInt()));
                break;
            }
            case 5: {
                this._value = lei.readDouble();
                break;
            }
            case 6: {
                Currency cur = new Currency();
                cur.read(lei);
                this._value = cur;
                break;
            }
            case 7: {
                Date date = new Date();
                date.read(lei);
                this._value = date;
                break;
            }
            case 8: 
            case 30: {
                CodePageString cps = new CodePageString();
                cps.read(lei);
                this._value = cps;
                break;
            }
            case 11: {
                VariantBool vb = new VariantBool();
                vb.read(lei);
                this._value = vb;
                break;
            }
            case 14: {
                Decimal dec = new Decimal();
                dec.read(lei);
                this._value = dec;
                break;
            }
            case 31: {
                UnicodeString us = new UnicodeString();
                us.read(lei);
                this._value = us;
                break;
            }
            case 64: {
                Filetime ft = new Filetime();
                ft.read(lei);
                this._value = ft;
                break;
            }
            case 65: 
            case 70: {
                Blob blob = new Blob();
                blob.read(lei);
                this._value = blob;
                break;
            }
            case 66: 
            case 67: 
            case 68: 
            case 69: {
                IndirectPropertyName ipn = new IndirectPropertyName();
                ipn.read(lei);
                this._value = ipn;
                break;
            }
            case 71: {
                ClipboardData cd = new ClipboardData();
                cd.read(lei);
                this._value = cd;
                break;
            }
            case 72: {
                GUID guid = new GUID();
                guid.read(lei);
                this._value = lei;
                break;
            }
            case 73: {
                VersionedStream vs = new VersionedStream();
                vs.read(lei);
                this._value = vs;
                break;
            }
            case 4098: 
            case 4099: 
            case 4100: 
            case 4101: 
            case 4102: 
            case 4103: 
            case 4104: 
            case 4106: 
            case 4107: 
            case 4108: 
            case 4112: 
            case 4113: 
            case 4114: 
            case 4115: 
            case 4116: 
            case 4117: 
            case 4126: 
            case 4127: 
            case 4160: 
            case 4167: 
            case 4168: {
                Vector vec = new Vector((short)(this._type & 0xFFF));
                vec.read(lei);
                this._value = vec;
                break;
            }
            case 8194: 
            case 8195: 
            case 8196: 
            case 8197: 
            case 8198: 
            case 8199: 
            case 8200: 
            case 8202: 
            case 8203: 
            case 8204: 
            case 8206: 
            case 8208: 
            case 8209: 
            case 8210: 
            case 8211: 
            case 8214: 
            case 8215: {
                Array arr = new Array();
                arr.read(lei);
                this._value = arr;
                break;
            }
            default: {
                String msg = "Unknown (possibly, incorrect) TypedPropertyValue type: " + this._type;
                throw new UnsupportedOperationException(msg);
            }
        }
    }

    static void skipPadding(LittleEndianByteArrayInputStream lei) {
        int offset = lei.getReadIndex();
        int skipBytes = 4 - (offset & 3) & 3;
        for (int i = 0; i < skipBytes; ++i) {
            lei.mark(1);
            int b = lei.read();
            if (b != -1 && b == 0) continue;
            lei.reset();
            break;
        }
    }
}

