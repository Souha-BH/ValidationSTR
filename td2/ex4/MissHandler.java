import javax.realtime.*;

public class MissHandler extends BoundAsyncEventHandler {
    public void handleAsyncEvent() {
        System.out.println("Misshandler");
        RealtimeThread.currentRealtimeThread().schedulePeriodic();

    }
}