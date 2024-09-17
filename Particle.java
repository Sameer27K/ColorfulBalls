import java.awt.*;

public class Particle {
    double x, y;
    double dx, dy;
    float alpha;
    Color color;
    int size;

    public Particle(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.dx = -2 + Math.random() * 4;
        this.dy = -2 + Math.random() * 4;
        this.alpha = 1.0f;
        this.color = color;
        this.size = 5 + (int)(Math.random() * 5);
    }

    public void update() {
        x += dx;
        y += dy;
        dy += 0.1; // Gravity effect on particles
        alpha -= 0.03f;
    }

    public boolean isAlive() {
        return alpha > 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, size, size);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
