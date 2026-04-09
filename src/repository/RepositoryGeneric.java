package repository;

import model.Identifiable;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RepositoryGeneric<T extends Identifiable>{

    private List<T> list = new ArrayList<>();

    public RepositoryGeneric() {}

    public void add(T t){
        T item = findItem(t.getId());
        if (item == null){
            list.add(t);
        }
    }

    public void remove(T t){
        T item = findItem(t.getId());
        if (item != null){
            list.remove(t);
        }
    }

    public void sprintList(){
        for (T item : list){
            System.out.println(item);
        }
    }

    public T findItem(String id){
        for (T item : list){
            if (item.getId().equalsIgnoreCase(id)){
                return item;
            }
        }
        return null;
    }

    public List<T> getList() {
        return list;
    }
}
