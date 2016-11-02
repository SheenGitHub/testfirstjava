package cpij;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/28.
 */
class ParticleCanvas extends Canvas {
    private Particle[] particles = new Particle[0];

    /**
     * Constructs a new Canvas.
     */
    public ParticleCanvas(int size) {
        setSize(new Dimension(size, size));
    }

    public synchronized void setParticles(Particle[] particles) {
        if (particles == null)
            throw new IllegalArgumentException("Cannot set null");
        this.particles = particles;
    }

    public Particle[] getParticles() {
        return particles;
    }

    @Override
    public void paint(Graphics g) {
        Particle[] ps = getParticles();
        for (int i = 0; i < ps.length; i++) {
            ps[i].draw(g);
        }
    }
}

class Particle{
    protected int x;
    protected int y;
    protected final Random rng = new Random();

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void move() {
        x += rng.nextInt(10)-5;
        y +=rng.nextInt(20)-10;
    }

    public void draw(Graphics graphics) {
        int lx, ly;
        synchronized (this){lx=x;ly=y;}
        graphics.drawRect(lx,ly,10,10);
    }
}
public class PrticleApplet extends Applet {
    protected Thread[] threads = null;
    protected final ParticleCanvas canvas = new ParticleCanvas(100);

    public void init() {
        add(canvas);
    }

    protected Thread makeThread(final Particle p) {
        Runnable runloop = new Runnable() {
            @Override
            public void run() {
                try {
                    for(;;) {
                        p.move();
                        canvas.repaint();

                            Thread.sleep(100);
                        }
                    }
                catch (InterruptedException e) {
                    return;
                }
            }
        };
        return new Thread(runloop);
    }

    public synchronized void start() {
        int n = 10;
        if (threads == null) {
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                particles[i] = new Particle(50, 50);
            }
            threads = new Thread[n];
            for (int i = 0; i < n; i++) {
                threads[i] = makeThread(particles[i]);
                threads[i].start();
            }
        }
    }

    @Override
    public synchronized void stop() {
        if (threads != null) {
            for (int i = 0; i < threads.length; i++) {
                threads[i].interrupt();
            }
            threads = null;
        }
    }
}
