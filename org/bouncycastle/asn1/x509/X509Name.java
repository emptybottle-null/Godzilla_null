/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERUniversalString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509DefaultEntryConverter;
import org.bouncycastle.asn1.x509.X509NameEntryConverter;
import org.bouncycastle.asn1.x509.X509NameTokenizer;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class X509Name
extends ASN1Object {
    public static final ASN1ObjectIdentifier C = new ASN1ObjectIdentifier("2.5.4.6");
    public static final ASN1ObjectIdentifier O = new ASN1ObjectIdentifier("2.5.4.10");
    public static final ASN1ObjectIdentifier OU = new ASN1ObjectIdentifier("2.5.4.11");
    public static final ASN1ObjectIdentifier T = new ASN1ObjectIdentifier("2.5.4.12");
    public static final ASN1ObjectIdentifier CN = new ASN1ObjectIdentifier("2.5.4.3");
    public static final ASN1ObjectIdentifier SN = new ASN1ObjectIdentifier("2.5.4.5");
    public static final ASN1ObjectIdentifier STREET = new ASN1ObjectIdentifier("2.5.4.9");
    public static final ASN1ObjectIdentifier SERIALNUMBER = SN;
    public static final ASN1ObjectIdentifier L = new ASN1ObjectIdentifier("2.5.4.7");
    public static final ASN1ObjectIdentifier ST = new ASN1ObjectIdentifier("2.5.4.8");
    public static final ASN1ObjectIdentifier SURNAME = new ASN1ObjectIdentifier("2.5.4.4");
    public static final ASN1ObjectIdentifier GIVENNAME = new ASN1ObjectIdentifier("2.5.4.42");
    public static final ASN1ObjectIdentifier INITIALS = new ASN1ObjectIdentifier("2.5.4.43");
    public static final ASN1ObjectIdentifier GENERATION = new ASN1ObjectIdentifier("2.5.4.44");
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = new ASN1ObjectIdentifier("2.5.4.45");
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY = new ASN1ObjectIdentifier("2.5.4.15");
    public static final ASN1ObjectIdentifier POSTAL_CODE = new ASN1ObjectIdentifier("2.5.4.17");
    public static final ASN1ObjectIdentifier DN_QUALIFIER = new ASN1ObjectIdentifier("2.5.4.46");
    public static final ASN1ObjectIdentifier PSEUDONYM = new ASN1ObjectIdentifier("2.5.4.65");
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1");
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2");
    public static final ASN1ObjectIdentifier GENDER = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3");
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4");
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5");
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH = new ASN1ObjectIdentifier("1.3.36.8.3.14");
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS = new ASN1ObjectIdentifier("2.5.4.16");
    public static final ASN1ObjectIdentifier DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54");
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER = X509ObjectIdentifiers.id_at_telephoneNumber;
    public static final ASN1ObjectIdentifier NAME = X509ObjectIdentifiers.id_at_name;
    public static final ASN1ObjectIdentifier EmailAddress = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier UnstructuredName = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
    public static final ASN1ObjectIdentifier UnstructuredAddress = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier E = EmailAddress;
    public static final ASN1ObjectIdentifier DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    public static boolean DefaultReverse = false;
    public static final Hashtable DefaultSymbols = new Hashtable();
    public static final Hashtable RFC2253Symbols = new Hashtable();
    public static final Hashtable RFC1779Symbols = new Hashtable();
    public static final Hashtable DefaultLookUp = new Hashtable();
    public static final Hashtable OIDLookUp = DefaultSymbols;
    public static final Hashtable SymbolLookUp = DefaultLookUp;
    private static final Boolean TRUE = new Boolean(true);
    private static final Boolean FALSE = new Boolean(false);
    private X509NameEntryConverter converter = null;
    private Vector ordering = new Vector();
    private Vector values = new Vector();
    private Vector added = new Vector();
    private ASN1Sequence seq;
    private boolean isHashCodeCalculated;
    private int hashCodeValue;

    public static X509Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return X509Name.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static X509Name getInstance(Object object) {
        if (object == null || object instanceof X509Name) {
            return (X509Name)object;
        }
        if (object instanceof X500Name) {
            return new X509Name(ASN1Sequence.getInstance(((X500Name)object).toASN1Primitive()));
        }
        if (object != null) {
            return new X509Name(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    protected X509Name() {
    }

    public X509Name(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Set aSN1Set = ASN1Set.getInstance(((ASN1Encodable)enumeration.nextElement()).toASN1Primitive());
            for (int i = 0; i < aSN1Set.size(); ++i) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Set.getObjectAt(i).toASN1Primitive());
                if (aSN1Sequence2.size() != 2) {
                    throw new IllegalArgumentException("badly sized pair");
                }
                this.ordering.addElement(ASN1ObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(0)));
                ASN1Encodable aSN1Encodable = aSN1Sequence2.getObjectAt(1);
                if (aSN1Encodable instanceof ASN1String && !(aSN1Encodable instanceof DERUniversalString)) {
                    String string = ((ASN1String)((Object)aSN1Encodable)).getString();
                    if (string.length() > 0 && string.charAt(0) == '#') {
                        this.values.addElement("\\" + string);
                    } else {
                        this.values.addElement(string);
                    }
                } else {
                    try {
                        this.values.addElement("#" + this.bytesToString(Hex.encode(aSN1Encodable.toASN1Primitive().getEncoded("DER"))));
                    } catch (IOException iOException) {
                        throw new IllegalArgumentException("cannot encode value");
                    }
                }
                this.added.addElement(i != 0 ? TRUE : FALSE);
            }
        }
    }

    public X509Name(Hashtable hashtable) {
        this(null, hashtable);
    }

    public X509Name(Vector vector, Hashtable hashtable) {
        this(vector, hashtable, (X509NameEntryConverter)new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Hashtable hashtable, X509NameEntryConverter x509NameEntryConverter) {
        int n;
        this.converter = x509NameEntryConverter;
        if (vector != null) {
            for (n = 0; n != vector.size(); ++n) {
                this.ordering.addElement(vector.elementAt(n));
                this.added.addElement(FALSE);
            }
        } else {
            Enumeration enumeration = hashtable.keys();
            while (enumeration.hasMoreElements()) {
                this.ordering.addElement(enumeration.nextElement());
                this.added.addElement(FALSE);
            }
        }
        for (n = 0; n != this.ordering.size(); ++n) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)this.ordering.elementAt(n);
            if (hashtable.get(aSN1ObjectIdentifier) == null) {
                throw new IllegalArgumentException("No attribute for object id - " + aSN1ObjectIdentifier.getId() + " - passed to distinguished name");
            }
            this.values.addElement(hashtable.get(aSN1ObjectIdentifier));
        }
    }

    public X509Name(Vector vector, Vector vector2) {
        this(vector, vector2, (X509NameEntryConverter)new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Vector vector2, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = x509NameEntryConverter;
        if (vector.size() != vector2.size()) {
            throw new IllegalArgumentException("oids vector must be same length as values.");
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.ordering.addElement(vector.elementAt(i));
            this.values.addElement(vector2.elementAt(i));
            this.added.addElement(FALSE);
        }
    }

    public X509Name(String string) {
        this(DefaultReverse, DefaultLookUp, string);
    }

    public X509Name(String string, X509NameEntryConverter x509NameEntryConverter) {
        this(DefaultReverse, DefaultLookUp, string, x509NameEntryConverter);
    }

    public X509Name(boolean bl, String string) {
        this(bl, DefaultLookUp, string);
    }

    public X509Name(boolean bl, String string, X509NameEntryConverter x509NameEntryConverter) {
        this(bl, DefaultLookUp, string, x509NameEntryConverter);
    }

    public X509Name(boolean bl, Hashtable hashtable, String string) {
        this(bl, hashtable, string, new X509DefaultEntryConverter());
    }

    private ASN1ObjectIdentifier decodeOID(String string, Hashtable hashtable) {
        if (Strings.toUpperCase(string = string.trim()).startsWith("OID.")) {
            return new ASN1ObjectIdentifier(string.substring(4));
        }
        if (string.charAt(0) >= '0' && string.charAt(0) <= '9') {
            return new ASN1ObjectIdentifier(string);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)hashtable.get(Strings.toLowerCase(string));
        if (aSN1ObjectIdentifier == null) {
            throw new IllegalArgumentException("Unknown object id - " + string + " - passed to distinguished name");
        }
        return aSN1ObjectIdentifier;
    }

    private String unescape(String string) {
        if (string.length() == 0 || string.indexOf(92) < 0 && string.indexOf(34) < 0) {
            return string.trim();
        }
        char[] cArray = string.toCharArray();
        boolean bl = false;
        boolean bl2 = false;
        StringBuffer stringBuffer = new StringBuffer(string.length());
        int n = 0;
        if (cArray[0] == '\\' && cArray[1] == '#') {
            n = 2;
            stringBuffer.append("\\#");
        }
        boolean bl3 = false;
        int n2 = 0;
        for (int i = n; i != cArray.length; ++i) {
            char c = cArray[i];
            if (c != ' ') {
                bl3 = true;
            }
            if (c == '\"') {
                if (!bl) {
                    bl2 = !bl2;
                } else {
                    stringBuffer.append(c);
                }
                bl = false;
                continue;
            }
            if (c == '\\' && !bl && !bl2) {
                bl = true;
                n2 = stringBuffer.length();
                continue;
            }
            if (c == ' ' && !bl && !bl3) continue;
            stringBuffer.append(c);
            bl = false;
        }
        if (stringBuffer.length() > 0) {
            while (stringBuffer.charAt(stringBuffer.length() - 1) == ' ' && n2 != stringBuffer.length() - 1) {
                stringBuffer.setLength(stringBuffer.length() - 1);
            }
        }
        return stringBuffer.toString();
    }

    public X509Name(boolean bl, Hashtable hashtable, String string, X509NameEntryConverter x509NameEntryConverter) {
        Object object;
        Object object2;
        this.converter = x509NameEntryConverter;
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(string);
        while (x509NameTokenizer.hasMoreTokens()) {
            object2 = x509NameTokenizer.nextToken();
            if (((String)object2).indexOf(43) > 0) {
                object = new X509NameTokenizer((String)object2, '+');
                this.addEntry(hashtable, ((X509NameTokenizer)object).nextToken(), FALSE);
                while (((X509NameTokenizer)object).hasMoreTokens()) {
                    this.addEntry(hashtable, ((X509NameTokenizer)object).nextToken(), TRUE);
                }
                continue;
            }
            this.addEntry(hashtable, (String)object2, FALSE);
        }
        if (bl) {
            object2 = new Vector();
            object = new Vector();
            Vector vector = new Vector();
            int n = 1;
            for (int i = 0; i < this.ordering.size(); ++i) {
                if (((Boolean)this.added.elementAt(i)).booleanValue()) {
                    ((Vector)object2).insertElementAt(this.ordering.elementAt(i), n);
                    ((Vector)object).insertElementAt(this.values.elementAt(i), n);
                    vector.insertElementAt(this.added.elementAt(i), n);
                    ++n;
                    continue;
                }
                ((Vector)object2).insertElementAt(this.ordering.elementAt(i), 0);
                ((Vector)object).insertElementAt(this.values.elementAt(i), 0);
                vector.insertElementAt(this.added.elementAt(i), 0);
                n = 1;
            }
            this.ordering = object2;
            this.values = object;
            this.added = vector;
        }
    }

    private void addEntry(Hashtable hashtable, String string, Boolean bl) {
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(string, '=');
        String string2 = x509NameTokenizer.nextToken();
        if (!x509NameTokenizer.hasMoreTokens()) {
            throw new IllegalArgumentException("badly formatted directory string");
        }
        String string3 = x509NameTokenizer.nextToken();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.decodeOID(string2, hashtable);
        this.ordering.addElement(aSN1ObjectIdentifier);
        this.values.addElement(this.unescape(string3));
        this.added.addElement(bl);
    }

    public Vector getOIDs() {
        Vector vector = new Vector();
        for (int i = 0; i != this.ordering.size(); ++i) {
            vector.addElement(this.ordering.elementAt(i));
        }
        return vector;
    }

    public Vector getValues() {
        Vector vector = new Vector();
        for (int i = 0; i != this.values.size(); ++i) {
            vector.addElement(this.values.elementAt(i));
        }
        return vector;
    }

    public Vector getValues(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Vector<String> vector = new Vector<String>();
        for (int i = 0; i != this.values.size(); ++i) {
            if (!this.ordering.elementAt(i).equals(aSN1ObjectIdentifier)) continue;
            String string = (String)this.values.elementAt(i);
            if (string.length() > 2 && string.charAt(0) == '\\' && string.charAt(1) == '#') {
                vector.addElement(string.substring(1));
                continue;
            }
            vector.addElement(string);
        }
        return vector;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.seq == null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = null;
            for (int i = 0; i != this.ordering.size(); ++i) {
                ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier)this.ordering.elementAt(i);
                aSN1EncodableVector3.add(aSN1ObjectIdentifier2);
                String string = (String)this.values.elementAt(i);
                aSN1EncodableVector3.add(this.converter.getConvertedValue(aSN1ObjectIdentifier2, string));
                if (aSN1ObjectIdentifier == null || ((Boolean)this.added.elementAt(i)).booleanValue()) {
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                } else {
                    aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                    aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                }
                aSN1ObjectIdentifier = aSN1ObjectIdentifier2;
            }
            aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
            this.seq = new DERSequence(aSN1EncodableVector);
        }
        return this.seq;
    }

    public boolean equals(Object object, boolean bl) {
        X509Name x509Name;
        if (!bl) {
            return this.equals(object);
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509Name) && !(object instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
        if (this.toASN1Primitive().equals(aSN1Primitive)) {
            return true;
        }
        try {
            x509Name = X509Name.getInstance(object);
        } catch (IllegalArgumentException illegalArgumentException) {
            return false;
        }
        int n = this.ordering.size();
        if (n != x509Name.ordering.size()) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier;
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier)this.ordering.elementAt(i);
            if (aSN1ObjectIdentifier2.equals(aSN1ObjectIdentifier = (ASN1ObjectIdentifier)x509Name.ordering.elementAt(i))) {
                String string;
                String string2 = (String)this.values.elementAt(i);
                if (this.equivalentStrings(string2, string = (String)x509Name.values.elementAt(i))) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.isHashCodeCalculated) {
            return this.hashCodeValue;
        }
        this.isHashCodeCalculated = true;
        for (int i = 0; i != this.ordering.size(); ++i) {
            String string = (String)this.values.elementAt(i);
            string = this.canonicalize(string);
            string = this.stripInternalSpaces(string);
            this.hashCodeValue ^= this.ordering.elementAt(i).hashCode();
            this.hashCodeValue ^= string.hashCode();
        }
        return this.hashCodeValue;
    }

    public boolean equals(Object object) {
        int n;
        int n2;
        int n3;
        X509Name x509Name;
        if (object == this) {
            return true;
        }
        if (!(object instanceof X509Name) && !(object instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Primitive aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive();
        if (this.toASN1Primitive().equals(aSN1Primitive)) {
            return true;
        }
        try {
            x509Name = X509Name.getInstance(object);
        } catch (IllegalArgumentException illegalArgumentException) {
            return false;
        }
        int n4 = this.ordering.size();
        if (n4 != x509Name.ordering.size()) {
            return false;
        }
        boolean[] blArray = new boolean[n4];
        if (this.ordering.elementAt(0).equals(x509Name.ordering.elementAt(0))) {
            n3 = 0;
            n2 = n4;
            n = 1;
        } else {
            n3 = n4 - 1;
            n2 = -1;
            n = -1;
        }
        for (int i = n3; i != n2; i += n) {
            boolean bl = false;
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)this.ordering.elementAt(i);
            String string = (String)this.values.elementAt(i);
            for (int j = 0; j < n4; ++j) {
                String string2;
                ASN1ObjectIdentifier aSN1ObjectIdentifier2;
                if (blArray[j] || !aSN1ObjectIdentifier.equals(aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier)x509Name.ordering.elementAt(j)) || !this.equivalentStrings(string, string2 = (String)x509Name.values.elementAt(j))) continue;
                blArray[j] = true;
                bl = true;
                break;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }

    private boolean equivalentStrings(String string, String string2) {
        String string3;
        String string4 = this.canonicalize(string);
        return string4.equals(string3 = this.canonicalize(string2)) || (string4 = this.stripInternalSpaces(string4)).equals(string3 = this.stripInternalSpaces(string3));
    }

    private String canonicalize(String string) {
        ASN1Primitive aSN1Primitive;
        String string2 = Strings.toLowerCase(string.trim());
        if (string2.length() > 0 && string2.charAt(0) == '#' && (aSN1Primitive = this.decodeObject(string2)) instanceof ASN1String) {
            string2 = Strings.toLowerCase(((ASN1String)((Object)aSN1Primitive)).getString().trim());
        }
        return string2;
    }

    private ASN1Primitive decodeObject(String string) {
        try {
            return ASN1Primitive.fromByteArray(Hex.decode(string.substring(1)));
        } catch (IOException iOException) {
            throw new IllegalStateException("unknown encoding in name: " + iOException);
        }
    }

    private String stripInternalSpaces(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        if (string.length() != 0) {
            char c = string.charAt(0);
            stringBuffer.append(c);
            for (int i = 1; i < string.length(); ++i) {
                char c2 = string.charAt(i);
                if (c != ' ' || c2 != ' ') {
                    stringBuffer.append(c2);
                }
                c = c2;
            }
        }
        return stringBuffer.toString();
    }

    private void appendValue(StringBuffer stringBuffer, Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        String string2 = (String)hashtable.get(aSN1ObjectIdentifier);
        if (string2 != null) {
            stringBuffer.append(string2);
        } else {
            stringBuffer.append(aSN1ObjectIdentifier.getId());
        }
        stringBuffer.append('=');
        int n = stringBuffer.length();
        stringBuffer.append(string);
        int n2 = stringBuffer.length();
        if (string.length() >= 2 && string.charAt(0) == '\\' && string.charAt(1) == '#') {
            n += 2;
        }
        while (n < n2 && stringBuffer.charAt(n) == ' ') {
            stringBuffer.insert(n, "\\");
            n += 2;
            ++n2;
        }
        while (--n2 > n && stringBuffer.charAt(n2) == ' ') {
            stringBuffer.insert(n2, '\\');
        }
        block5: while (n <= n2) {
            switch (stringBuffer.charAt(n)) {
                case '\"': 
                case '+': 
                case ',': 
                case ';': 
                case '<': 
                case '=': 
                case '>': 
                case '\\': {
                    stringBuffer.insert(n, "\\");
                    n += 2;
                    ++n2;
                    continue block5;
                }
            }
            ++n;
        }
    }

    public String toString(boolean bl, Hashtable hashtable) {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        Vector<StringBuffer> vector = new Vector<StringBuffer>();
        boolean bl2 = true;
        StringBuffer stringBuffer2 = null;
        for (n = 0; n < this.ordering.size(); ++n) {
            if (((Boolean)this.added.elementAt(n)).booleanValue()) {
                stringBuffer2.append('+');
                this.appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier)this.ordering.elementAt(n), (String)this.values.elementAt(n));
                continue;
            }
            stringBuffer2 = new StringBuffer();
            this.appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier)this.ordering.elementAt(n), (String)this.values.elementAt(n));
            vector.addElement(stringBuffer2);
        }
        if (bl) {
            for (n = vector.size() - 1; n >= 0; --n) {
                if (bl2) {
                    bl2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(n).toString());
            }
        } else {
            for (n = 0; n < vector.size(); ++n) {
                if (bl2) {
                    bl2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(n).toString());
            }
        }
        return stringBuffer.toString();
    }

    private String bytesToString(byte[] byArray) {
        char[] cArray = new char[byArray.length];
        for (int i = 0; i != cArray.length; ++i) {
            cArray[i] = (char)(byArray[i] & 0xFF);
        }
        return new String(cArray);
    }

    public String toString() {
        return this.toString(DefaultReverse, DefaultSymbols);
    }

    static {
        DefaultSymbols.put(C, "C");
        DefaultSymbols.put(O, "O");
        DefaultSymbols.put(T, "T");
        DefaultSymbols.put(OU, "OU");
        DefaultSymbols.put(CN, "CN");
        DefaultSymbols.put(L, "L");
        DefaultSymbols.put(ST, "ST");
        DefaultSymbols.put(SN, "SERIALNUMBER");
        DefaultSymbols.put(EmailAddress, "E");
        DefaultSymbols.put(DC, "DC");
        DefaultSymbols.put(UID, "UID");
        DefaultSymbols.put(STREET, "STREET");
        DefaultSymbols.put(SURNAME, "SURNAME");
        DefaultSymbols.put(GIVENNAME, "GIVENNAME");
        DefaultSymbols.put(INITIALS, "INITIALS");
        DefaultSymbols.put(GENERATION, "GENERATION");
        DefaultSymbols.put(UnstructuredAddress, "unstructuredAddress");
        DefaultSymbols.put(UnstructuredName, "unstructuredName");
        DefaultSymbols.put(UNIQUE_IDENTIFIER, "UniqueIdentifier");
        DefaultSymbols.put(DN_QUALIFIER, "DN");
        DefaultSymbols.put(PSEUDONYM, "Pseudonym");
        DefaultSymbols.put(POSTAL_ADDRESS, "PostalAddress");
        DefaultSymbols.put(NAME_AT_BIRTH, "NameAtBirth");
        DefaultSymbols.put(COUNTRY_OF_CITIZENSHIP, "CountryOfCitizenship");
        DefaultSymbols.put(COUNTRY_OF_RESIDENCE, "CountryOfResidence");
        DefaultSymbols.put(GENDER, "Gender");
        DefaultSymbols.put(PLACE_OF_BIRTH, "PlaceOfBirth");
        DefaultSymbols.put(DATE_OF_BIRTH, "DateOfBirth");
        DefaultSymbols.put(POSTAL_CODE, "PostalCode");
        DefaultSymbols.put(BUSINESS_CATEGORY, "BusinessCategory");
        DefaultSymbols.put(TELEPHONE_NUMBER, "TelephoneNumber");
        DefaultSymbols.put(NAME, "Name");
        RFC2253Symbols.put(C, "C");
        RFC2253Symbols.put(O, "O");
        RFC2253Symbols.put(OU, "OU");
        RFC2253Symbols.put(CN, "CN");
        RFC2253Symbols.put(L, "L");
        RFC2253Symbols.put(ST, "ST");
        RFC2253Symbols.put(STREET, "STREET");
        RFC2253Symbols.put(DC, "DC");
        RFC2253Symbols.put(UID, "UID");
        RFC1779Symbols.put(C, "C");
        RFC1779Symbols.put(O, "O");
        RFC1779Symbols.put(OU, "OU");
        RFC1779Symbols.put(CN, "CN");
        RFC1779Symbols.put(L, "L");
        RFC1779Symbols.put(ST, "ST");
        RFC1779Symbols.put(STREET, "STREET");
        DefaultLookUp.put("c", C);
        DefaultLookUp.put("o", O);
        DefaultLookUp.put("t", T);
        DefaultLookUp.put("ou", OU);
        DefaultLookUp.put("cn", CN);
        DefaultLookUp.put("l", L);
        DefaultLookUp.put("st", ST);
        DefaultLookUp.put("sn", SN);
        DefaultLookUp.put("serialnumber", SN);
        DefaultLookUp.put("street", STREET);
        DefaultLookUp.put("emailaddress", E);
        DefaultLookUp.put("dc", DC);
        DefaultLookUp.put("e", E);
        DefaultLookUp.put("uid", UID);
        DefaultLookUp.put("surname", SURNAME);
        DefaultLookUp.put("givenname", GIVENNAME);
        DefaultLookUp.put("initials", INITIALS);
        DefaultLookUp.put("generation", GENERATION);
        DefaultLookUp.put("unstructuredaddress", UnstructuredAddress);
        DefaultLookUp.put("unstructuredname", UnstructuredName);
        DefaultLookUp.put("uniqueidentifier", UNIQUE_IDENTIFIER);
        DefaultLookUp.put("dn", DN_QUALIFIER);
        DefaultLookUp.put("pseudonym", PSEUDONYM);
        DefaultLookUp.put("postaladdress", POSTAL_ADDRESS);
        DefaultLookUp.put("nameofbirth", NAME_AT_BIRTH);
        DefaultLookUp.put("countryofcitizenship", COUNTRY_OF_CITIZENSHIP);
        DefaultLookUp.put("countryofresidence", COUNTRY_OF_RESIDENCE);
        DefaultLookUp.put("gender", GENDER);
        DefaultLookUp.put("placeofbirth", PLACE_OF_BIRTH);
        DefaultLookUp.put("dateofbirth", DATE_OF_BIRTH);
        DefaultLookUp.put("postalcode", POSTAL_CODE);
        DefaultLookUp.put("businesscategory", BUSINESS_CATEGORY);
        DefaultLookUp.put("telephonenumber", TELEPHONE_NUMBER);
        DefaultLookUp.put("name", NAME);
    }
}

