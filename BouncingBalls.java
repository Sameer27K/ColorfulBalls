import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BouncingBalls extends JPanel implements ActionListener, MouseListener {
    Timer timer;
    ArrayList<Ball> balls;
    ArrayList<Particle> particles;
    boolean isPaused = false;
    JButton pauseButton, resumeButton, resetButton;
    MusicPlayer musicPlayer;

    // Control variables
    double gravity = 0.2; // Default gravity
    int particleIntensity = 10; // Default number of particles generated
    JSlider gravitySlider, particleSlider;
    JLabel gravityLabel, particleLabel;

    public BouncingBalls() {
        setLayout(null); // Use absolute positioning
        balls = new ArrayList<>();
        particles = new ArrayList<>();
        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();
        addMouseListener(this);

        // Initialize buttons
        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        resetButton = new JButton("Reset");

        // Initialize sliders
        gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 20); // Gravity from 0.0 to 0.5
        particleSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10); // Particle intensity from 0 to 50

        // Initialize labels
        gravityLabel = new JLabel("Gravity");
        particleLabel = new JLabel("Particle Intensity");

        // Set slider labels
        gravitySlider.setMajorTickSpacing(10);
        gravitySlider.setMinorTickSpacing(1);
        gravitySlider.setPaintTicks(true);
        gravitySlider.setPaintLabels(true);
        gravitySlider.setBounds(100, 60, 200, 50);
        gravitySlider.addChangeListener(e -> {
            gravity = gravitySlider.getValue() / 100.0; // Scale to 0.0 - 0.5
        });

        particleSlider.setMajorTickSpacing(10);
        particleSlider.setMinorTickSpacing(1);
        particleSlider.setPaintTicks(true);
        particleSlider.setPaintLabels(true);
        particleSlider.setBounds(400, 60, 200, 50);
        particleSlider.addChangeListener(e -> {
            particleIntensity = particleSlider.getValue();
        });

        // Set label bounds
        gravityLabel.setBounds(160, 40, 80, 20);
        gravityLabel.setForeground(Color.WHITE);
        particleLabel.setBounds(450, 40, 120, 20);
        particleLabel.setForeground(Color.WHITE);

        // Set button bounds
        pauseButton.setBounds(10, 10, 80, 30);
        resumeButton.setBounds(100, 10, 80, 30);
        resetButton.setBounds(190, 10, 80, 30);

        // Add action listeners
        pauseButton.addActionListener(e -> pause());
        resumeButton.addActionListener(e -> resume());
        resetButton.addActionListener(e -> reset());

        // Add components to panel
        add(pauseButton);
        add(resumeButton);
        add(resetButton);
        add(gravitySlider);
        add(particleSlider);
        add(gravityLabel);
        add(particleLabel);

        // Start background music
        musicPlayer = new MusicPlayer("resources/background_music.wav");
        musicPlayer.playLoop();
    }

    public void pause() {
        isPaused = true;
        timer.stop();
        musicPlayer.pause();
    }

    public void resume() {
        isPaused = false;
        timer.start();
        musicPlayer.resume();
    }

    public void reset() {
        isPaused = false;
        balls.clear();
        particles.clear();
        timer.restart();
        musicPlayer.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused) {
            // Update balls
            for (Ball ball : balls) {
                ball.move(getWidth(), getHeight(), balls, particles, gravity, particleIntensity);
            }
            // Update particles
            particles.removeIf(p -> !p.isAlive());
            for (Particle p : particles) {
                p.update();
            }
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw custom gradient background
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
            0, 0, new Color(30, 30, 60), // Start color
            getWidth(), getHeight(), new Color(0, 0, 0) // End color
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Enable anti-aliasing for smoother visuals
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw trails
        for (Ball ball : balls) {
            ball.drawTrails(g2d);
        }

        // Draw particles
        for (Particle p : particles) {
            p.draw(g2d);
        }

        // Draw balls
        for (Ball ball : balls) {
            ball.draw(g2d);
        }

        // Draw components
        paintComponents(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        balls.add(new Ball(e.getX(), e.getY()));
    }

    // Unused mouse events
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}    
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Enhanced Bouncing Balls with Controls");
        BouncingBalls panel = new BouncingBalls();
        frame.add(panel);
        frame.setSize(800, 700); // Increased height to accommodate sliders
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
