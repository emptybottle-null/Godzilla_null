/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.BadLocationException;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;

class JsHinter {
    private JavaScriptParser parser;
    private DefaultParseResult result;
    private static final Map<String, MarkStrategy> MARK_STRATEGIES = new HashMap<String, MarkStrategy>();

    private JsHinter(JavaScriptParser parser, RSyntaxDocument doc, DefaultParseResult result) {
        this.parser = parser;
        this.result = result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void parse(JavaScriptParser parser, RSyntaxTextArea textArea, DefaultParseResult result) throws IOException {
        String stdout = null;
        RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
        ArrayList<String> command = new ArrayList<String>();
        if (File.separatorChar == '\\') {
            command.add("cmd.exe");
            command.add("/c");
        } else {
            command.add("/bin/sh");
            command.add("-c");
        }
        command.add("jshint");
        File jshintrc = parser.getJsHintRCFile(textArea);
        if (jshintrc != null) {
            command.add("--config");
            command.add(jshintrc.getAbsolutePath());
        }
        command.add("--verbose");
        command.add("-");
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream();
        Process p = pb.start();
        PrintWriter w = new PrintWriter(p.getOutputStream());
        InputStream outStream = p.getInputStream();
        InputStream errStream = p.getErrorStream();
        StreamReaderThread stdoutThread = new StreamReaderThread(outStream);
        StreamReaderThread stderrThread = new StreamReaderThread(errStream);
        stdoutThread.start();
        try {
            String text = doc.getText(0, doc.getLength());
            w.print(text);
            w.flush();
            w.close();
            p.waitFor();
            p = null;
            stdoutThread.join();
            stderrThread.join();
            stdout = stdoutThread.getStreamOutput();
        } catch (InterruptedException ie) {
            stdoutThread.interrupt();
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        } finally {
            if (outStream != null) {
                outStream.close();
            }
            w.close();
            if (p != null) {
                p.destroy();
            }
        }
        JsHinter hinter = new JsHinter(parser, doc, result);
        hinter.parseOutput(doc, stdout);
    }

    private void parseOutput(RSyntaxDocument doc, String output) {
        String[] lines;
        String[] stringArray = lines = output.split("\r?\n");
        int n = stringArray.length;
        for (int i = 0; i < n; ++i) {
            String line;
            String origLine = line = stringArray[i];
            if (!line.startsWith("stdin: line ")) continue;
            line = line.substring("stdin: line ".length());
            int end = 0;
            while (Character.isDigit(line.charAt(end))) {
                ++end;
            }
            int lineNum = Integer.parseInt(line.substring(0, end)) - 1;
            if (lineNum == -1) {
                DefaultParserNotice dpn = new DefaultParserNotice(this.parser, origLine, 0);
                this.result.addNotice(dpn);
                continue;
            }
            if (!(line = line.substring(end)).startsWith(", col ")) continue;
            line = line.substring(", col ".length());
            end = 0;
            while (Character.isDigit(line.charAt(end))) {
                ++end;
            }
            if (!(line = line.substring(end)).startsWith(", ")) continue;
            String msg = line.substring(", ".length());
            String errorCode = null;
            ParserNotice.Level noticeType = ParserNotice.Level.ERROR;
            if (msg.charAt(msg.length() - 1) == ')') {
                int openParen = msg.lastIndexOf(40);
                errorCode = msg.substring(openParen + 1, msg.length() - 1);
                if (msg.charAt(openParen + 1) == 'W') {
                    noticeType = ParserNotice.Level.WARNING;
                }
                msg = msg.substring(0, openParen - 1);
            }
            MarkStrategy markStrategy = JsHinter.getMarkStrategy(errorCode);
            switch (markStrategy) {
                default: 
            }
            DefaultParserNotice dpn = new DefaultParserNotice(this.parser, msg, lineNum);
            dpn.setLevel(noticeType);
            this.result.addNotice(dpn);
        }
    }

    private static final MarkStrategy getMarkStrategy(String msgCode) {
        MarkStrategy strategy = MARK_STRATEGIES.get(msgCode);
        return strategy != null ? strategy : MarkStrategy.MARK_LINE;
    }

    static {
        MARK_STRATEGIES.put("E015", MarkStrategy.MARK_CUR_TOKEN);
        MARK_STRATEGIES.put("E019", MarkStrategy.MARK_CUR_TOKEN);
        MARK_STRATEGIES.put("E030", MarkStrategy.MARK_CUR_TOKEN);
        MARK_STRATEGIES.put("E041", MarkStrategy.STOP_PARSING);
        MARK_STRATEGIES.put("E042", MarkStrategy.STOP_PARSING);
        MARK_STRATEGIES.put("E043", MarkStrategy.STOP_PARSING);
        MARK_STRATEGIES.put("W004", MarkStrategy.MARK_PREV_NON_WS_TOKEN);
        MARK_STRATEGIES.put("W015", MarkStrategy.MARK_CUR_TOKEN);
        MARK_STRATEGIES.put("W032", MarkStrategy.MARK_PREV_TOKEN);
        MARK_STRATEGIES.put("W033", MarkStrategy.MARK_PREV_TOKEN);
        MARK_STRATEGIES.put("W060", MarkStrategy.MARK_CUR_TOKEN);
        MARK_STRATEGIES.put("W098", MarkStrategy.MARK_PREV_TOKEN);
        MARK_STRATEGIES.put("W116", MarkStrategy.MARK_PREV_TOKEN);
        MARK_STRATEGIES.put("W117", MarkStrategy.MARK_CUR_TOKEN);
    }

    private static enum MarkStrategy {
        MARK_LINE,
        MARK_CUR_TOKEN,
        MARK_PREV_TOKEN,
        MARK_PREV_NON_WS_TOKEN,
        IGNORE,
        STOP_PARSING;

    }

    static class StreamReaderThread
    extends Thread {
        private BufferedReader r;
        private StringBuilder buffer;

        public StreamReaderThread(InputStream in) {
            this.r = new BufferedReader(new InputStreamReader(in));
            this.buffer = new StringBuilder();
        }

        public String getStreamOutput() {
            return this.buffer.toString();
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = this.r.readLine()) != null) {
                    this.buffer.append(line).append('\n');
                }
            } catch (IOException ioe) {
                this.buffer.append("IOException occurred: " + ioe.getMessage());
            }
        }
    }
}

