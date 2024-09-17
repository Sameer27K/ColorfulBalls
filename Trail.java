import java.awt.*;

public class Trail {
    double x, y;
    int size;
    Color color;
    float alpha = 0.5f;

    public Trail(double x, double y, int size, Color color) {
        this.x = x - size / 2;
        this.y = y - size / 2;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
        alpha -= 0.015f;
        if (alpha < 0) alpha = 0;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(color);
        g2d.fillOval((int) x, (int) y, size, size);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
