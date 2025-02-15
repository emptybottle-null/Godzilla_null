/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.payloads.java;

import core.Db;
import core.ui.component.RTextArea;
import core.ui.component.dialog.AppSeting;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.fife.ui.rtextarea.RTextScrollPane;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class DynamicUpdateClass
extends JPanel {
    public static final String ENVNAME = "DynamicClassNames";
    private final RTextArea classNameTextArea = new RTextArea();
    private final JButton updateHeaderButton = new JButton("\u4fee\u6539");

    public DynamicUpdateClass() {
        super(new BorderLayout(1, 1));
        this.classNameTextArea.setText(Db.getSetingValue(ENVNAME, DynamicUpdateClass.readDefaultClassName()));
        Dimension dimension = new Dimension();
        dimension.height = 30;
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(0);
        JPanel bottomPanel = new JPanel();
        splitPane.setTopComponent(new RTextScrollPane(this.classNameTextArea, true));
        bottomPanel.add(this.updateHeaderButton);
        bottomPanel.setMaximumSize(dimension);
        bottomPanel.setMinimumSize(dimension);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setResizeWeight(0.9);
        automaticBindClick.bindJButtonClick(this, this);
        this.add(splitPane);
    }

    private static String readDefaultClassName() {
        byte[] data = null;
        try {
            InputStream fileInputStream = DynamicUpdateClass.class.getResourceAsStream("assets/classNames.txt");
            data = functions.readInputStream(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return new String(data);
    }

    public static HashSet getAllDynamicClassName() {
        String classNameString = Db.getSetingValue(ENVNAME, DynamicUpdateClass.readDefaultClassName());
        String[] classNames = classNameString.split("\n");
        HashSet classNameSet = new HashSet();
        Arrays.stream(classNames).forEach(name -> {
            if (name.trim().length() > 0) {
                classNameSet.add(name.trim());
            }
        });
        return classNameSet;
    }

    private void updateHeaderButtonClick(ActionEvent actionEvent) {
        String classNameString = this.classNameTextArea.getText();
        String[] classNames = classNameString.split("\n");
        HashSet classNameSet = new HashSet();
        Arrays.stream(classNames).forEach(name -> {
            if (name.trim().length() > 0) {
                classNameSet.add(name.trim());
            }
        });
        if (classNameSet.size() > 50) {
            Db.updateSetingKV(ENVNAME, classNameString);
            GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(null, "ClassName \u5c11\u4e8e50\u4e2a", "\u9519\u8bef\u63d0\u793a", 1);
        }
    }

    static {
        AppSeting.registerPluginSeting("Java\u52a8\u6001Class\u540d\u5b57", DynamicUpdateClass.class);
    }
}

