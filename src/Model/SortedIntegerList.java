package Model;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortedIntegerList {
    private boolean listType;
    private LinkedList<Integer> list;

    public SortedIntegerList(boolean Type){
        this.listType = Type;
        list = new LinkedList<>();

    }

    public void add(int value){
        ListIterator<Integer> itlist = list.listIterator();
        if(this.listType == false){
            while(itlist.hasNext()) {
                if (itlist.next() == value) {
                    return;
                }
            }
            while(itlist.hasPrevious()){
                if(itlist.previous() > value){
                    itlist.next();
                    break;
                }
            }
            itlist.add(value);
        }
        else{
            while(itlist.hasNext()){
                if(itlist.next() < value){
                    itlist.previous();
                    break;
                }
            }
            itlist.add(value);
        }
    }

    public String toString(){
        StringBuilder buff = new StringBuilder();
        ListIterator<Integer> itlist = list.listIterator();
        while(itlist.hasNext()){
            buff.append(itlist.next());
            buff.append(" ");
        }
        buff.append("\n");
        return buff.toString();
    }

    public String toStringBest(){
        int k=0;
        StringBuilder buff = new StringBuilder();
        ListIterator<Integer> itlist = list.listIterator();
        if (list.size()<3) {
            while (itlist.hasNext()) {
                buff.append(itlist.next());
                buff.append(" ");
            }
        }
        else {
            while (itlist.hasNext()) {
                k++;
                buff.append(itlist.next());
                buff.append(" ");
                if (k==3) break;
            }
        }
        return buff.toString();
    }
}