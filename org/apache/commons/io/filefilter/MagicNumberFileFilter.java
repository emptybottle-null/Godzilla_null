/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class MagicNumberFileFilter
extends AbstractFileFilter
implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final byte[] magicNumbers;
    private final long byteOffset;

    public MagicNumberFileFilter(byte[] magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(byte[] magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (magicNumber.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (offset < 0L) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = IOUtils.byteArray(magicNumber.length);
        System.arraycopy(magicNumber, 0, this.magicNumbers, 0, magicNumber.length);
        this.byteOffset = offset;
    }

    public MagicNumberFileFilter(String magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(String magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (magicNumber.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (offset < 0L) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
        this.byteOffset = offset;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean accept(File file) {
        if (file == null) return false;
        if (!file.isFile()) return false;
        if (!file.canRead()) return false;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");){
            byte[] fileBytes = IOUtils.byteArray(this.magicNumbers.length);
            randomAccessFile.seek(this.byteOffset);
            int read = randomAccessFile.read(fileBytes);
            if (read != this.magicNumbers.length) {
                boolean bl2 = false;
                return bl2;
            }
            boolean bl = Arrays.equals(this.magicNumbers, fileBytes);
            return bl;
        } catch (IOException iOException) {
            // empty catch block
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        if (file == null) return FileVisitResult.TERMINATE;
        if (!Files.isRegularFile(file, new LinkOption[0])) return FileVisitResult.TERMINATE;
        if (!Files.isReadable(file)) return FileVisitResult.TERMINATE;
        try (FileChannel fileChannel = FileChannel.open(file, new OpenOption[0]);){
            ByteBuffer byteBuffer = ByteBuffer.allocate(this.magicNumbers.length);
            int read = fileChannel.read(byteBuffer);
            if (read != this.magicNumbers.length) {
                FileVisitResult fileVisitResult2 = FileVisitResult.TERMINATE;
                return fileVisitResult2;
            }
            FileVisitResult fileVisitResult = MagicNumberFileFilter.toFileVisitResult(Arrays.equals(this.magicNumbers, byteBuffer.array()), file);
            return fileVisitResult;
        } catch (IOException iOException) {
            // empty catch block
        }
        return FileVisitResult.TERMINATE;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("(");
        builder.append(new String(this.magicNumbers, Charset.defaultCharset()));
        builder.append(",");
        builder.append(this.byteOffset);
        builder.append(")");
        return builder.toString();
    }
}

