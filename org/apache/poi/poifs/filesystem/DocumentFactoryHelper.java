/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

@Internal
public final class DocumentFactoryHelper {
    private DocumentFactoryHelper() {
    }

    public static InputStream getDecryptedStream(final POIFSFileSystem fs, String password) throws IOException {
        return new FilterInputStream(DocumentFactoryHelper.getDecryptedStream(fs.getRoot(), password)){

            @Override
            public void close() throws IOException {
                fs.close();
                super.close();
            }
        };
    }

    public static InputStream getDecryptedStream(DirectoryNode root, String password) throws IOException {
        EncryptionInfo info = new EncryptionInfo(root);
        Decryptor d = Decryptor.getInstance(info);
        try {
            boolean passwordCorrect = false;
            if (password != null && d.verifyPassword(password)) {
                passwordCorrect = true;
            }
            if (!passwordCorrect && d.verifyPassword("VelvetSweatshop")) {
                passwordCorrect = true;
            }
            if (passwordCorrect) {
                return d.getDataStream(root);
            }
            if (password != null) {
                throw new EncryptedDocumentException("Password incorrect");
            }
            throw new EncryptedDocumentException("The supplied spreadsheet is protected, but no password was supplied");
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    @Deprecated
    @Removal(version="4.0")
    public static boolean hasOOXMLHeader(InputStream inp) throws IOException {
        return FileMagic.valueOf(inp) == FileMagic.OOXML;
    }
}

