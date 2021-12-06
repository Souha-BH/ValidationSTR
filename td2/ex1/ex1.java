
class MyThread1 extends Thread {

    public void run() {
        System.out.println(" this thread implemented by the first method is running ... ");
    }
}

class MyThread2 implements Runnable {

    public void run() {
        System.out.println(" this thread implemented by the second method is running ... ");
    }
}

class ThreadAlphabet implements Runnable {
    public void run() {
        for (char c = 'a'; c <= 'z'; c++)
            System.out.println(c);
    }
}

class ThreadNumbers implements Runnable {
    public void run() {
        for (int i = 1; i <= 26; i++)
            System.out.println(i);
    }
}

class ex1 {
    public static void main(String args[]) {
        MyThread1 t1 = new MyThread1();
        t1.start();
        Thread t2 = new Thread(new MyThread2());
        t2.start();
        /*
         * En Java, il existe deux méthodes pour créer des threads. La première étend
         * la classe Thread et la deuxième méthode implémente l'interface Runnable.
         * Une classe ne peut étendre qu'une seule autre classe. De plus, d'un point
         * de vue conception, il est plus propre de définir des tâches, puis des threads
         * exécutant ces tâches. La deuxième méthode est donc celle à utiliser.
         */
        Thread ta = new Thread(new ThreadAlphabet());
        ta.start();
        Thread tn = new Thread(new ThreadNumbers());
        tn.start();
        /*
         * Selon le système d'exploitation et l'implémentation de la JVM, les threads
         * peuvent être gérés de deux manières :
         * 
         * correspondre à un thread natif du système
         * correspondre à un thread géré par la machine virtuelle
         */

    }
}