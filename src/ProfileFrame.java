import java.awt.*;
import javax.swing.*;

public class ProfileFrame extends JFrame {
    private final User user;
    private final Runnable onLogout;

    private JTextField nameField;
    private JPasswordField oldPassField;
    private JPasswordField newPassField;
    private JPasswordField confirmPassField;

    public ProfileFrame(User user, Runnable onLogout) {
        super("Online Examination - Profile");
        this.user = user;
        this.onLogout = onLogout;
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        Utils.applyPurpleTheme();

        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(Utils.PURPLE_START);

        JLabel title = Utils.titleLabel("Profile & Settings");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(title, BorderLayout.NORTH);

        // Profile panel
        JPanel profilePanel = Utils.sectionPanel();
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Full Name"), gbc);
        gbc.gridx = 1;
        nameField = Utils.roundedTextField(40);
        nameField.setText(user.getFullName());
        form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Username"), gbc);
        gbc.gridx = 1;
        JTextField username = Utils.roundedTextField(40);
        username.setText(user.getUsername());
        username.setEditable(false);
        form.add(username, gbc);

        JButton saveProfile = Utils.purpleButton("Save Profile");
        saveProfile.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                Utils.showMessage(this, "Name cannot be empty", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            user.setFullName(name);
            Utils.showMessage(this, "Profile updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        profilePanel.add(form, BorderLayout.CENTER);
        profilePanel.add(saveProfile, BorderLayout.SOUTH);

        // Password panel
        JPanel passPanel = Utils.sectionPanel();
        JPanel pform = new JPanel(new GridBagLayout());
        pform.setOpaque(false);
        GridBagConstraints pgbc = new GridBagConstraints();
        pgbc.insets = new Insets(20, 20, 20, 20);
        pgbc.fill = GridBagConstraints.HORIZONTAL;
        pgbc.weightx = 1.0;

        pgbc.gridx = 0; pgbc.gridy = 0;
        pform.add(new JLabel("Current Password"), pgbc);
        pgbc.gridx = 1;
        oldPassField = Utils.roundedPasswordField(40);
        pform.add(oldPassField, pgbc);

        pgbc.gridx = 0; pgbc.gridy++;
        pform.add(new JLabel("New Password"), pgbc);
        pgbc.gridx = 1;
        newPassField = Utils.roundedPasswordField(40);
        pform.add(newPassField, pgbc);

        pgbc.gridx = 0; pgbc.gridy++;
        pform.add(new JLabel("Confirm Password"), pgbc);
        pgbc.gridx = 1;
        confirmPassField = Utils.roundedPasswordField(40);
        pform.add(confirmPassField, pgbc);

        JButton savePass = Utils.purpleButton("Change Password");
        savePass.addActionListener(e -> changePassword());

        passPanel.add(pform, BorderLayout.CENTER);
        passPanel.add(savePass, BorderLayout.SOUTH);

        // Footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Utils.PURPLE_START);
        JButton backBtn = Utils.purpleButton("Back to Dashboard");
        backBtn.addActionListener(e -> {
            DashboardFrame df = new DashboardFrame(user, onLogout);
            df.setVisible(true);
            setVisible(false);
        });
        footer.add(backBtn);

        root.add(profilePanel, BorderLayout.WEST);
        root.add(passPanel, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
        setContentPane(root);
    }

    private void changePassword() {
        String oldPwd = new String(oldPassField.getPassword());
        String newPwd = new String(newPassField.getPassword());
        String confirmPwd = new String(confirmPassField.getPassword());

        if (!user.getPassword().equals(oldPwd)) {
            Utils.showMessage(this, "Current password is incorrect", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (newPwd.length() < 6) {
            Utils.showMessage(this, "Password should be at least 6 characters", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!newPwd.equals(confirmPwd)) {
            Utils.showMessage(this, "New password and confirmation do not match", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        user.setPassword(newPwd);
        Utils.showMessage(this, "Password updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
        oldPassField.setText("");
        newPassField.setText("");
        confirmPassField.setText("");
    }
}