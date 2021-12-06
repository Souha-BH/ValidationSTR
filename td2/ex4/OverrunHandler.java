
import javax.realtime.*;

public class OverrunHandler extends BoundAsyncEventHandler {
    public void handleAsyncEvent() {
        System.out.println("Overrunhandler");
        System.exit(1);

    }
}