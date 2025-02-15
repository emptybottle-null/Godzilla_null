/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.annotation.DisplayName;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@DisplayName(DisplayName="\u57fa\u7840\u4fe1\u606f")
public class ShellBasicsInfo
extends JPanel {
    private final ShellEntity shellEntity;
    private final RTextArea basicsInfoTextArea;

    public ShellBasicsInfo(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        super.setLayout(new BorderLayout(1, 1));
        this.basicsInfoTextArea = new RTextArea();
        this.basicsInfoTextArea.setEditable(false);
        super.add(new JScrollPane(this.basicsInfoTextArea));
        this.basicsInfoTextArea.setText(shellEntity.getPayloadModule().getBasicsInfo());
    }
}

