package edu.txstate.cs3354.kasten.hw4;

public class Signal {
    private boolean signalled = false;
    public synchronized void setSignal() { signalled = true;  notifyAll(); }
    public synchronized void waitForSignal() throws InterruptedException { while (!signalled) wait(); }
}