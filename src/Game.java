import java.awt.*;
import java.net.ServerSocket;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 432;
    public static final int HEIGHT = 768;
    public boolean running;
    Thread thread;
    ServerSocket serverSocket;
    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "Game", new Game());

    }
    public synchronized void start() {
        running = true;
        thread = new Thread();
        thread.start();
        run();

    }
    public void init() {

    }
    public void tick() {

    }
    public void render() {

    }
    @Override
    public void run() {
        init();
        this.requestFocus();
        long PastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - PastTime) / ns;
            PastTime = now;
            while (delta > 0) {
                tick();
                updates++;
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + "| Ticks: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

}
