
class Compte {
    private float solde = 100;

    void retirer(int montant) {
        this.solde -= montant;
    }

    float getSolde() {
        return this.solde;
    }
}

class JobSylvieEtBruno implements Runnable {
    Compte compte = new Compte();

    void effectuerRetrait(int montant) throws InterruptedException {
        String nom = Thread.currentThread().getName();
        if (compte.getSolde() >= montant) {
            System.out.println(nom + " va retirer");
            System.out.println(nom + " va dormir");
            Thread.sleep(500);
            System.out.println(nom + " se réveille");
            compte.retirer(montant);
            System.out.println(nom + " a effectué son retrait");

        }

    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                effectuerRetrait(10);
                if (compte.getSolde() < 0)
                    System.out.println("Solde insuffisant");
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
    }

}

public class ex2 {
    public static void main(String args[]) {
        JobSylvieEtBruno job = new JobSylvieEtBruno();
        Thread Sylvie = new Thread(job);
        Thread Bruno = new Thread(job);
        Sylvie.setName("Sylvie");
        Bruno.setName("Bruno");
        Sylvie.start();
        Bruno.start();
    }
}
