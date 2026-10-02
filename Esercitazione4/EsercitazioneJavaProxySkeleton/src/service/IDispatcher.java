package service;

public interface IDispatcher {
    public void sendCmd(int cmd);
    public int getCmd();
    public void runSkeleton();
}
