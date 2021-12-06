
import javax.realtime.*;

public class MultiThreads extends RealtimeThread {
    private int val = 0;
    private boolean error = false;
    private String name;

    public static void main(String[] args) {
        PriorityScheduler ps = (PriorityScheduler) Scheduler.getDefaultScheduler();
        int priority = ps.getMaxPriority();
        AbsoluteTime start = Clock.getRealtimeClock().getTime().add(1000, 0);
        RelativeTime period = new RelativeTime(1000, 0);
        RelativeTime cost = new RelativeTime(140, 0);
        RelativeTime deadline = new RelativeTime(150, 0);
        MultiThreads thread1 = new MultiThreads("Thread1", priority, 1, start, period, cost, deadline);
        if (!thread1.addToFeasibility()) {
            System.out.println("Feasibility error");
        }

        priority = ps.getMinPriority();
        MultiThreads thread2 = new MultiThreads("Thread2", priority, 1, start, period, cost, deadline);
        if (!thread2.addToFeasibility()) {
            System.out.println("Feasibility error");
        }

        System.out.println("Start Time:" + Clock.getRealtimeClock().getTime().toString());
        thread1.start();
        thread2.start();
    }

    public MultiThreads(String name, int priority, int important, HighResolutionTime start,
            RelativeTime period, RelativeTime cost, RelativeTime deadline)

    {
        super(new ImportanceParameters(priority, important),
                new PeriodicParameters(start, period, cost, deadline,
                        new OverrunHandler(), new MissHandler()));
        this.name = name;
    }

    public void run() {
        while (val < 10 && !error) {
            val++;
            System.out.println(name + " executed at:" + Clock.getRealtimeClock().getTime().toString());
            if (!waitForNextPeriod()) {
                error = true;
            }

        }

    }
}