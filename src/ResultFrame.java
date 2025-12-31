import java.awt.*;
import javax.swing.*;

public class ResultFrame extends JFrame {
    private final User user;
    private final int score;
    private final int total;
    private final Runnable onLogout;

    public ResultFrame(User user, int score, int total, Runnable onLogout) {
        super("Online Examination - Result");
        this.user = user;
        this.score = score;
        this.total = total;
        this.onLogout = onLogout;
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        Utils.applyPurpleTheme();

        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(Utils.PURPLE_START);

        // Title
        JLabel title = Utils.titleLabel("Exam Result");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(title, BorderLayout.NORTH);

        // Result details
        JPanel center = Utils.sectionPanel();
        String message = "<html><div style='font-size:22px; color:white;'>" +
                "Candidate: <b>" + user.getFullName() + "</b> (" + user.getUsername() + ")<br><br>" +
                "Score: <b>" + score + " / " + total + "</b><br>" +
                "Percentage: <b>" + (int) Math.round((score * 100.0) / total) + "%</b><br><br>" +
                (score >= Math.ceil(total * 0.6)
                        ? "Status: <b style='color:#6ecb63'>PASS</b>"
                        : "Status: <b style='color:#ff6b6b'>FAIL</b>") +
                "</div></html>";

        JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
        resultLabel.setFont(Utils.BODY_FONT);
        center.add(resultLabel, BorderLayout.CENTER);

        // Action buttons
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        actions.setBackground(Utils.PURPLE_START);

        JButton dashboardBtn = Utils.purpleButton("Back to Dashboard");
        dashboardBtn.addActionListener(e -> {
            DashboardFrame df = new DashboardFrame(user, onLogout);
            df.setVisible(true);
            dispose();
        });

        JButton retakeBtn = Utils.purpleButton("Retake Exam");
        retakeBtn.addActionListener(e -> {
            ExamFrame exam = new ExamFrame(user, onLogout);
            exam.setVisible(true);
            dispose();
        });

        JButton logoutBtn = Utils.purpleButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose();
            onLogout.run();
        });

        actions.add(dashboardBtn);
        actions.add(retakeBtn);
        actions.add(logoutBtn);

        root.add(center, BorderLayout.CENTER);
        root.add(actions, BorderLayout.SOUTH);

        setContentPane(root);
    }
}