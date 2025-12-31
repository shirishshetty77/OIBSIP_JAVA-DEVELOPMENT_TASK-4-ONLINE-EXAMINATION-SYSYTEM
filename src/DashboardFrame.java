import java.awt.*;
import javax.swing.*;

public class DashboardFrame extends JFrame {
    private final User user;
    private final Runnable onLogout;

    public DashboardFrame(User user, Runnable onLogout) {
        super("Online Examination - Dashboard");
        this.user = user;
        this.onLogout = onLogout;
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Utils.applyPurpleTheme();

        JPanel root = new JPanel(new BorderLayout(16, 16));
        root.setBackground(Utils.PURPLE_START);

        JLabel title = Utils.titleLabel("Dashboard");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(4, 1, 20, 20));
        buttons.setBackground(Utils.PURPLE_START);
        buttons.setBorder(new javax.swing.border.EmptyBorder(40, 200, 40, 200));

        // Profile button
        JButton profileBtn = Utils.purpleButton("Update Profile");
        profileBtn.addActionListener(e -> {
            ProfileFrame pf = new ProfileFrame(user, onLogout);
            pf.setVisible(true);
            setVisible(false);
        });

        // Exam button
        JButton examBtn = Utils.purpleButton("Start Exam");
        examBtn.addActionListener(e -> {
            ExamFrame exam = new ExamFrame(user, onLogout);
            exam.setVisible(true);
            setVisible(false);
        });

        // Results button
        JButton resultBtn = Utils.purpleButton("View Results");
        resultBtn.addActionListener(e -> {
            if (user.getLastScore() == null) {
                Utils.showMessage(this, "No exam taken yet!", "Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ResultFrame rf = new ResultFrame(user, user.getLastScore(), user.getLastTotal(), onLogout);
                rf.setVisible(true);
                setVisible(false);
            }
        });

        // Logout button
        JButton logoutBtn = Utils.purpleButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose();
            onLogout.run();
        });

        buttons.add(profileBtn);
        buttons.add(examBtn);
        buttons.add(resultBtn);
        buttons.add(logoutBtn);

        root.add(buttons, BorderLayout.CENTER);
        setContentPane(root);
    }
}