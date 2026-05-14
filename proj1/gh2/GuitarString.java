package gh2;

import deque.Deque;
import deque.ArrayDeque;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAY = .996;
    private Deque<Double> buffer;
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<Double>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    public void pluck() {
        int currentSize = buffer.size();
        for (int i = 0; i < currentSize; i++) {
            buffer.removeFirst();
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    public void tic() {
        double front = buffer.removeFirst();
        double next = buffer.get(0);
        double newDouble = ((front + next) / 2.0) * DECAY;
        buffer.addLast(newDouble);
    }

    public double sample() {
        return buffer.get(0);
    }
}
