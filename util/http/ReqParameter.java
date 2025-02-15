/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util.http;

import core.ui.component.dialog.ShellSuperRequest;
import java.util.Iterator;
import util.functions;
import util.http.Parameter;

public class ReqParameter
extends Parameter {
    public String format() {
        String randomRP = ShellSuperRequest.randomReqParameter();
        if (randomRP != null && randomRP.length() > 1) {
            this.add(functions.getRandomString(5), randomRP);
        }
        Iterator keys = this.hashMap.keySet().iterator();
        StringBuffer buffer = new StringBuffer();
        while (keys.hasNext()) {
            String key = (String)keys.next();
            buffer.append(key);
            buffer.append("=");
            Object valueObject = this.hashMap.get(key);
            if (valueObject.getClass().isAssignableFrom(byte[].class)) {
                buffer.append(functions.base64EncodeToString((byte[])valueObject));
            } else {
                buffer.append(functions.base64EncodeToString(((String)valueObject).getBytes()));
            }
            buffer.append("&");
        }
        String temString = buffer.delete(buffer.length() - 1, buffer.length()).toString();
        return temString;
    }

    public byte[] formatEx() {
        return super.serialize();
    }
}

