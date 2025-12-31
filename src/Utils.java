import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Utils {
    public static final Font TITLE_FONT = new Font("Georgia", Font.BOLD, 36);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 22);

    public static final Color PURPLE_START = new Color(120, 60, 200);
    public static final Color PURPLE_END   = new Color(180, 100, 255);

    public static void applyPurpleTheme() {
        UIManager.put("Panel.background", PURPLE_START);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextField.background", new Color(240, 230, 250));
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("PasswordField.background", new Color(240, 230, 250));
        UIManager.put("PasswordField.foreground", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);

        // Dialog customization
        UIManager.put("OptionPane.background", PURPLE_START);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.font", BODY_FONT);
        UIManager.put("Button.background", PURPLE_END);
        UIManager.put("Button.foreground", Color.WHITE);
    }

    public static JPanel sectionPanel() {
        JPanel p = new JPanel(new BorderLayout(16, 16));
        p.setBackground(PURPLE_START);
        p.setBorder(new EmptyBorder(20, 24, 20, 24));
        return p;
    }

    public static JTextField roundedTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setFont(BODY_FONT);
        tf.setPreferredSize(new Dimension(400, 40));
        tf.setBorder(new RoundedBorder(PURPLE_END, 12));
        return tf;
    }

    public static JPasswordField roundedPasswordField(int cols) {
        JPasswordField pf = new JPasswordField(cols);
        pf.setFont(BODY_FONT);
        pf.setPreferredSize(new Dimension(400, 40));
        pf.setBorder(new RoundedBorder(PURPLE_END, 12));
        return pf;
    }

    public static JButton purpleButton(String text) {
        GradientButton b = new GradientButton(text, PURPLE_START, PURPLE_END);
        b.setFont(new Font("Segoe UI", Font.BOLD, 22));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(14, 24, 14, 24));
        return b;
    }

    public static JLabel titleLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(TITLE_FONT);
        l.setForeground(Color.WHITE);
        return l;
    }

    // Themed dialogs
    public static void showMessage(Component parent, String message, String title, int type) {
        JLabel label = new JLabel("<html><div style='color:white; font-size:18px;'>" + message + "</div></html>");
        JOptionPane.showMessageDialog(parent, label, title, type);
    }

    public static int showConfirm(Component parent, String message, String title) {
        JLabel label = new JLabel("<html><div style='color:white; font-size:18px;'>" + message + "</div></html>");
        return JOptionPane.showConfirmDialog(parent, label, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    // Rounded border
    public static class RoundedBorder extends LineBorder {
        private final int radius;
        public RoundedBorder(Color color, int radius) {
            super(color, 1, true);
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }
    }

    // Gradient button
    public static class GradientButton extends JButton {
        private final Color start;
        private final Color end;
        public GradientButton(String text, Color start, Color end) {
            super(text);
            this.start = start;
            this.end = end;
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, start, 0, h, end);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, w, h, 16, 16);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}