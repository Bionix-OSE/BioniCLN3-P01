
import javax.swing.SwingUtilities;

import view.LogonUI;

public static void main() {
    SwingUtilities.invokeLater(() -> {
        new LogonUI().setVisible(true);
    });
}
