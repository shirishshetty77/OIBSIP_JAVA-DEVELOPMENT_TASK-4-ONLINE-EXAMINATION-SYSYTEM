import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Utils.applyPurpleTheme();
            new LoginFrame().setVisible(true);
        });
    }
}