package TP2;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);

        t1.start();
        t2.start();

    }
}