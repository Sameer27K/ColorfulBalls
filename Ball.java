import java.awt.*;
import java.util.ArrayList;

public class Ball {
    double x, y;
    int size;
    double dx, dy;
    Color color;
    ArrayList<Trail> trails;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        size = 40; // Fixed size for better smiley face visibility
        dx = -5 + Math.random() * 10; // Random speed between -5 and 5
        dy = -5 + Math.random() * 10;
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        trails = new ArrayList<>();
    }

    public void move(int width, int height, ArrayList<Ball> balls, ArrayList<Particle> particles, double gravity, int particleIntensity) {
        x += dx;
        y += dy;
        dy += gravity; // Use gravity from control panel

        // Collision with walls
        boolean collisionOccurred = false;
        if (x < 0) {
            x = 0;
            dx = -dx;
            collisionOccurred = true;
        }
        if (x + size > width) {
            x = width - size;
            dx = -dx;
            collisionOccurred = true;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
            collisionOccurred = true;
        }
        if (y + size > height) {
            y = height - size;
            dy = -dy * 0.8; // Lose some energy on bounce
            if (Math.abs(dy) < 1) dy = 0;
            collisionOccurred = true;
        }
        if (collisionOccurred) {
            generateParticles(particles, particleIntensity);
        }

        // Add trail
        trails.add(new Trail(x + size / 2, y + size / 2, size, color));
        if (trails.size() > 30) {
            trails.remove(0); // Limit trail length
        }

        // Collision with other balls
        for (Ball other : balls) {
            if (other != this) {
                double dxCollide = (x + size / 2) - (other.x + other.size / 2);
                double dyCollide = (y + size / 2) - (other.y + other.size / 2);
                double distance = Math.hypot(dxCollide, dyCollide);
                if (distance < (size / 2 + other.size / 2)) {
                    // Generate particles on collision
                    generateParticles(particles, particleIntensity);

                    // Simple elastic collision response
                    double angle = Math.atan2(dyCollide, dxCollide);
                    double totalMass = size + other.size;
                    double newDx = (dx * (size - other.size) + (2 * other.size * other.dx)) / totalMass;
                    double newDy = (dy * (size - other.size) + (2 * other.size * other.dy)) / totalMass;
                    double otherNewDx = (other.dx * (other.size - size) + (2 * size * dx)) / totalMass;
                    double otherNewDy = (other.dy * (other.size - size) + (2 * size * dy)) / totalMass;

                    dx = newDx;
                    dy = newDy;
                    other.dx = otherNewDx;
                    other.dy = otherNewDy;

                    // Adjust positions to prevent sticking
                    double overlap = 0.5 * (distance - size / 2 - other.size / 2);
                    x -= overlap * Math.cos(angle);
                    y -= overlap * Math.sin(angle);
                    other.x += overlap * Math.cos(angle);
                    other.y += overlap * Math.sin(angle);
                }
            }
        }
    }

    public void generateParticles(ArrayList<Particle> particles, int particleIntensity) {
        for (int i = 0; i < particleIntensity; i++) {
            particles.add(new Particle(x + size / 2, y + size / 2, color));
        }
    }

    public void drawTrails(Graphics2D g2d) {
        // Draw trails
        for (Trail trail : trails) {
            trail.draw(g2d);
        }
    }

    public void draw(Graphics2D g2d) {
        // Draw ball (face outline)
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, size, size);

        // Draw smiley face
        g2d.setColor(Color.BLACK);

        // Eyes
        int eyeWidth = size / 5;
        int eyeHeight = size / 5;
        int eyeXOffset = size / 5;
        int eyeYOffset = size / 4;
        g2d.fillOval((int) x + eyeXOffset, (int) y + eyeYOffset, eyeWidth, eyeHeight);
        g2d.fillOval((int) x + size - eyeXOffset - eyeWidth, (int) y + eyeYOffset, eyeWidth, eyeHeight);

        // Mouth
        int mouthWidth = size / 2;
        int mouthHeight = size / 4;
        int mouthX = (int) x + (size - mouthWidth) / 2;
        int mouthY = (int) y + size / 2;
        g2d.drawArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, -180);
    }
}
