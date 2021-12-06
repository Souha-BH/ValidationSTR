package ex3;

import javax.realtime.*;
import javax.realtime.MemoryArea;
import javax.realtime.ScopedMemory;
import javax.realtime.LTMemory;

public class EssaiScope {
    static public long c1, c2, r1, r2;
    static public LTMemory area = new LTMemory(4096, 4096);

    public static void main(String[] arg) {
        Runnable logic = new Runnable() {
            public void run() {
                c1 = area.memoryConsumed();
                r1 = area.memoryRemaining();
                Integer i = new Integer(1);
                c2 = area.memoryConsumed();
                r2 = area.memoryRemaining();
            }
        };
        RealtimeThread rt = new RealtimeThread(null, null, null, area, null, logic);
        rt.start();
        try {
            rt.join();
        } catch (InterruptedException e) {
        }
        System.out.println("Avant création de l'objet Integer : espace consomme: "
                + c1 + ", espace restant : " + r1);
        System.out.println("Apres création de l'objet Integer : espace consomme: "
                + c2 + " espace restant: " + r2);
        System.out.println("A la fin de la thread TR, espace consomme: " +
                area.memoryConsumed() + " espace restant : " + area.memoryRemaining());

    }

}