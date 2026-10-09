package sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class StrutturaMem {

    private HashMap<String, List<Reading>> mem;

    private ReentrantLock lock;

    public StrutturaMem(){
        mem = new HashMap<>();

        lock = new ReentrantLock();
    }

    public void add_element(Reading reading){

        lock.lock();
        try {

            List<Reading> lista = mem.get(reading.getSensorId());
            
            if(lista == null){
                lista = new ArrayList<>();
                mem.put(reading.getSensorId(), lista);
            }

            lista.add(reading);

        } finally {
            lock.unlock();
        }
    }

    public List<Reading> get_element(String sensor_id){

        List<Reading> list_return = null;

        lock.lock();
        try{

            list_return = mem.get(sensor_id);

        } finally {
            lock.unlock();
        }

        return  list_return;
    }
}
