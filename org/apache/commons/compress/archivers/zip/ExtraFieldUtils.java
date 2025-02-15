/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.AsiExtraField;
import org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField;
import org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField;
import org.apache.commons.compress.archivers.zip.UnicodePathExtraField;
import org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior;
import org.apache.commons.compress.archivers.zip.UnparseableExtraFieldData;
import org.apache.commons.compress.archivers.zip.UnrecognizedExtraField;
import org.apache.commons.compress.archivers.zip.X000A_NTFS;
import org.apache.commons.compress.archivers.zip.X0014_X509Certificates;
import org.apache.commons.compress.archivers.zip.X0015_CertificateIdForFile;
import org.apache.commons.compress.archivers.zip.X0016_CertificateIdForCentralDirectory;
import org.apache.commons.compress.archivers.zip.X0017_StrongEncryptionHeader;
import org.apache.commons.compress.archivers.zip.X0019_EncryptionRecipientCertificateList;
import org.apache.commons.compress.archivers.zip.X5455_ExtendedTimestamp;
import org.apache.commons.compress.archivers.zip.X7875_NewUnix;
import org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class ExtraFieldUtils {
    private static final int WORD = 4;
    private static final Map<ZipShort, Class<?>> implementations = new ConcurrentHashMap();

    public static void register(Class<?> c) {
        try {
            ZipExtraField ze = (ZipExtraField)c.newInstance();
            implementations.put(ze.getHeaderId(), c);
        } catch (ClassCastException cc) {
            throw new RuntimeException(c + " doesn't implement ZipExtraField");
        } catch (InstantiationException ie) {
            throw new RuntimeException(c + " is not a concrete class");
        } catch (IllegalAccessException ie) {
            throw new RuntimeException(c + "'s no-arg constructor is not public");
        }
    }

    public static ZipExtraField createExtraField(ZipShort headerId) throws InstantiationException, IllegalAccessException {
        ZipExtraField field = ExtraFieldUtils.createExtraFieldNoDefault(headerId);
        if (field != null) {
            return field;
        }
        UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(headerId);
        return u;
    }

    public static ZipExtraField createExtraFieldNoDefault(ZipShort headerId) throws InstantiationException, IllegalAccessException {
        Class<?> c = implementations.get(headerId);
        if (c != null) {
            return (ZipExtraField)c.newInstance();
        }
        return null;
    }

    public static ZipExtraField[] parse(byte[] data) throws ZipException {
        return ExtraFieldUtils.parse(data, true, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] data, boolean local) throws ZipException {
        return ExtraFieldUtils.parse(data, local, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] data, boolean local, final UnparseableExtraField onUnparseableData) throws ZipException {
        return ExtraFieldUtils.parse(data, local, new ExtraFieldParsingBehavior(){

            @Override
            public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
                return onUnparseableData.onUnparseableExtraField(data, off, len, local, claimedLength);
            }

            @Override
            public ZipExtraField createExtraField(ZipShort headerId) throws ZipException, InstantiationException, IllegalAccessException {
                return ExtraFieldUtils.createExtraField(headerId);
            }

            @Override
            public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
                return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
            }
        });
    }

    public static ZipExtraField[] parse(byte[] data, boolean local, ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
        int length;
        ArrayList<ZipExtraField> v = new ArrayList<ZipExtraField>();
        for (int start = 0; start <= data.length - 4; start += length + 4) {
            ZipShort headerId = new ZipShort(data, start);
            length = new ZipShort(data, start + 2).getValue();
            if (start + 4 + length > data.length) {
                ZipExtraField field = parsingBehavior.onUnparseableExtraField(data, start, data.length - start, local, length);
                if (field == null) break;
                v.add(field);
                break;
            }
            try {
                ZipExtraField ze = Objects.requireNonNull(parsingBehavior.createExtraField(headerId), "createExtraField must not return null");
                v.add(Objects.requireNonNull(parsingBehavior.fill(ze, data, start + 4, length, local), "fill must not return null"));
                continue;
            } catch (IllegalAccessException | InstantiationException ie) {
                throw (ZipException)new ZipException(ie.getMessage()).initCause(ie);
            }
        }
        ZipExtraField[] result = new ZipExtraField[v.size()];
        return v.toArray(result);
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] data) {
        byte[] local;
        boolean lastIsUnparseableHolder = data.length > 0 && data[data.length - 1] instanceof UnparseableExtraFieldData;
        int regularExtraFieldCount = lastIsUnparseableHolder ? data.length - 1 : data.length;
        int sum = 4 * regularExtraFieldCount;
        for (ZipExtraField element : data) {
            sum += element.getLocalFileDataLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < regularExtraFieldCount; ++i) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
            start += 4;
            byte[] local2 = data[i].getLocalFileDataData();
            if (local2 == null) continue;
            System.arraycopy(local2, 0, result, start, local2.length);
            start += local2.length;
        }
        if (lastIsUnparseableHolder && (local = data[data.length - 1].getLocalFileDataData()) != null) {
            System.arraycopy(local, 0, result, start, local.length);
        }
        return result;
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] data) {
        byte[] central;
        boolean lastIsUnparseableHolder = data.length > 0 && data[data.length - 1] instanceof UnparseableExtraFieldData;
        int regularExtraFieldCount = lastIsUnparseableHolder ? data.length - 1 : data.length;
        int sum = 4 * regularExtraFieldCount;
        for (ZipExtraField element : data) {
            sum += element.getCentralDirectoryLength().getValue();
        }
        byte[] result = new byte[sum];
        int start = 0;
        for (int i = 0; i < regularExtraFieldCount; ++i) {
            System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
            System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
            start += 4;
            byte[] central2 = data[i].getCentralDirectoryData();
            if (central2 == null) continue;
            System.arraycopy(central2, 0, result, start, central2.length);
            start += central2.length;
        }
        if (lastIsUnparseableHolder && (central = data[data.length - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(central, 0, result, start, central.length);
        }
        return result;
    }

    public static ZipExtraField fillExtraField(ZipExtraField ze, byte[] data, int off, int len, boolean local) throws ZipException {
        try {
            if (local) {
                ze.parseFromLocalFileData(data, off, len);
            } else {
                ze.parseFromCentralDirectoryData(data, off, len);
            }
            return ze;
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw (ZipException)new ZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(ze.getHeaderId().getValue())).initCause(aiobe);
        }
    }

    static {
        ExtraFieldUtils.register(AsiExtraField.class);
        ExtraFieldUtils.register(X5455_ExtendedTimestamp.class);
        ExtraFieldUtils.register(X7875_NewUnix.class);
        ExtraFieldUtils.register(JarMarker.class);
        ExtraFieldUtils.register(UnicodePathExtraField.class);
        ExtraFieldUtils.register(UnicodeCommentExtraField.class);
        ExtraFieldUtils.register(Zip64ExtendedInformationExtraField.class);
        ExtraFieldUtils.register(X000A_NTFS.class);
        ExtraFieldUtils.register(X0014_X509Certificates.class);
        ExtraFieldUtils.register(X0015_CertificateIdForFile.class);
        ExtraFieldUtils.register(X0016_CertificateIdForCentralDirectory.class);
        ExtraFieldUtils.register(X0017_StrongEncryptionHeader.class);
        ExtraFieldUtils.register(X0019_EncryptionRecipientCertificateList.class);
        ExtraFieldUtils.register(ResourceAlignmentExtraField.class);
    }

    public static final class UnparseableExtraField
    implements UnparseableExtraFieldBehavior {
        public static final int THROW_KEY = 0;
        public static final int SKIP_KEY = 1;
        public static final int READ_KEY = 2;
        public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
        public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
        public static final UnparseableExtraField READ = new UnparseableExtraField(2);
        private final int key;

        private UnparseableExtraField(int k) {
            this.key = k;
        }

        public int getKey() {
            return this.key;
        }

        @Override
        public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            switch (this.key) {
                case 0: {
                    throw new ZipException("Bad extra field starting at " + off + ".  Block length of " + claimedLength + " bytes exceeds remaining data of " + (len - 4) + " bytes.");
                }
                case 2: {
                    UnparseableExtraFieldData field = new UnparseableExtraFieldData();
                    if (local) {
                        field.parseFromLocalFileData(data, off, len);
                    } else {
                        field.parseFromCentralDirectoryData(data, off, len);
                    }
                    return field;
                }
                case 1: {
                    return null;
                }
            }
            throw new ZipException("Unknown UnparseableExtraField key: " + this.key);
        }
    }
}

