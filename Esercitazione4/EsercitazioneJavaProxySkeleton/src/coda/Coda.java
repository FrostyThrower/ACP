package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Coda {

    // Variabili per la gestione della coda
    private int queue[];
    private int testa;
    private int coda;
    private int size;
    private int elems;

    // Variabili per la sincronizzazione
    private Lock lock;
    private Condition prod;
    private Condition cons;

    public Coda(int size){

        this.queue = new int[size];
        this.testa = 0;
        this.coda = 0;
        this.size = size;
        this.elems = 0;

        this.lock = new ReentrantLock();
        this.prod = lock.newCondition();
        this.prod = lock.newCondition();
    }


    public void inserisci(int command){

        
    }

    public int preleva(){

        return 0;
    }
}
