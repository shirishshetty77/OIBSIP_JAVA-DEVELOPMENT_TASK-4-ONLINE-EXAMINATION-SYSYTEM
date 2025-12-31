import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;

public class ExamFrame extends JFrame {

    private final User user;
    private final Runnable onLogout;
    private final List<Question> questions;
    private final Integer[] selectedIndices;

    private int current = 0;
    private int timeLeftSeconds = 30 * 60; // 30 minutes
    private Timer timer;

    private JLabel timerLabel;
    private JTextArea questionArea;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;
    private JButton prevBtn, nextBtn, submitBtn;

    public ExamFrame(User user, Runnable onLogout) {
        super("Online Examination - Exam");
        this.user = user;
        this.onLogout = onLogout;

        // ✅ Correct method
        this.questions = QuestionBank.getExamQuestions();
        this.selectedIndices = new Integer[questions.size()];

        setupUI();
        loadQuestion(0);
        startTimer();
    }

    private void setupUI() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Utils.applyPurpleTheme();

        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(Utils.PURPLE_START);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Utils.PURPLE_START);

        JLabel title = Utils.titleLabel("MCQ Examination");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        timerLabel = Utils.titleLabel(formatTime(timeLeftSeconds));
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        header.add(timerLabel, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        // ================= QUESTION PANEL =================
        JPanel qPanel = Utils.sectionPanel();

        questionArea = new JTextArea();
        questionArea.setFont(Utils.BODY_FONT);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        questionArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        qPanel.add(questionArea, BorderLayout.NORTH);

        // ================= OPTIONS =================
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionButtons = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < optionButtons.length; i++) {
            final int index = i;
            JRadioButton rb = new JRadioButton();
            rb.setFont(Utils.BODY_FONT);
            rb.setOpaque(false);

            // ✅ Save answer instantly
            rb.addActionListener(e -> selectedIndices[current] = index);

            optionButtons[i] = rb;
            group.add(rb);
            optionsPanel.add(rb);
            optionsPanel.add(Box.createVerticalStrut(12));
        }

        qPanel.add(optionsPanel, BorderLayout.CENTER);

        // ================= NAVIGATION =================
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        nav.setBackground(Utils.PURPLE_START);

        prevBtn = Utils.purpleButton("Previous");
        nextBtn = Utils.purpleButton("Next");
        submitBtn = Utils.purpleButton("Submit");

        prevBtn.addActionListener(e -> {
            saveSelection();
            if (current > 0) loadQuestion(current - 1);
        });

        nextBtn.addActionListener(e -> {
            saveSelection();
            if (current < questions.size() - 1) loadQuestion(current + 1);
        });

        submitBtn.addActionListener(e -> submitExam(true));

        nav.add(prevBtn);
        nav.add(nextBtn);
        nav.add(submitBtn);

        root.add(qPanel, BorderLayout.CENTER);
        root.add(nav, BorderLayout.SOUTH);

        setContentPane(root);
    }

    private void loadQuestion(int index) {
        current = index;
        Question q = questions.get(index);

        questionArea.setText("Q" + (index + 1) + ". " + q.getText());

        String[] opts = q.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText((char) ('A' + i) + ". " + opts[i]);
        }

        group.clearSelection();
        if (selectedIndices[index] != null) {
            optionButtons[selectedIndices[index]].setSelected(true);
        }

        prevBtn.setEnabled(index > 0);
        nextBtn.setEnabled(index < questions.size() - 1);
        submitBtn.setEnabled(index == questions.size() - 1);
    }

    private void saveSelection() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedIndices[current] = i;
                return;
            }
        }
        selectedIndices[current] = null;
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeftSeconds--;
            timerLabel.setText(formatTime(timeLeftSeconds));

            if (timeLeftSeconds <= 0) {
                timer.stop();
                submitExam(false);
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private String formatTime(int totalSeconds) {
        int m = totalSeconds / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    private void submitExam(boolean confirm) {
        saveSelection();

        if (confirm) {
            int ans = Utils.showConfirm(this,
                    "Submit your answers?",
                    "Confirm Submit");

            if (ans != JOptionPane.YES_OPTION) return;
        }

        stopTimer();

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Integer sel = selectedIndices[i];
            if (sel != null && sel == questions.get(i).getCorrectIndex()) {
                score++;
            }
        }

        user.setLastResult(score, questions.size());

        ResultFrame rf = new ResultFrame(user, score, questions.size(), onLogout);
        rf.setVisible(true);
        dispose();
    }
}
