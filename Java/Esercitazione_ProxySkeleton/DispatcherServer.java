package Java.Esercitazione_ProxySkeleton;

public class DispatcherServer {
    
    public static void main(String[] args) {
        
        DispatcherImpl disp = new DispatcherImpl(5);
        DispatcherSkeleton sk = new DispatcherSkeleton(disp);
        sk.runSkeleton();
    }
}
