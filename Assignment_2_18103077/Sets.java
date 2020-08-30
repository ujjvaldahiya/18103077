import java.util.HashSet;

class ArraySet {
    public static ArraySet UNIVERSAL_SET = new ArraySet(new int[]{0,1,2,3,4,5,6,7,8,9,10});
    boolean[] set;
    
    public ArraySet() {
        this.set = new boolean[11];
    }
    
    public ArraySet(int[] set) {
        if(set == null || set.length < 1)
            throw new IllegalArgumentException("Set can't be null or of zero length");
        
        this.set = new boolean[11];
        for(int i: set)
            this.set[i] = true;
    }
    
    public void add(int e){
        this.set[e] = true;
    }
    
    public void remove(int e){
        this.set[e] = false;
    }
    
    public void union(ArraySet aSet){
        for(int i=0; i<11; ++i){
            if(aSet.set[i]) this.set[i] = true;
        }
    }
    
    public void intersetion(ArraySet aSet){
        for(int i=0; i<11; ++i){
            if(!this.set[i] || !aSet.set[i])
                this.set[i] = false;
        }
    }
    
    public void complement(){
        for(int i=0; i<11; ++i){
            this.set[i] = !this.set[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        s.append("{ ");
        
        for(int i=0; i<11; ++i)
            if(this.set[i]){
                s.append(i); s.append(", ");
            }
        
        if(s.length() > 3 && s.substring(s.length()-2).equals(", "))
            s.delete(s.length()-2, s.length());
        
        s.append(" }");
        
        return s.toString();
    }
    
}

class libraryDsSet{
    public static libraryDsSet UNIVERSAL_SET =  new libraryDsSet(new int[]{0,1,2,3,4,5,6,7,8,9,10});
    HashSet<Integer> set;

    public libraryDsSet() {
        set = new HashSet<>(11);
    }

    public libraryDsSet(int[] set) {
        if(set == null || set.length < 1)
            throw new IllegalArgumentException("Set can't be null or of zero length");
        
        this.set = new HashSet<>(11);
        for(int i: set)
            this.set.add(i);
    }
    
    public void add(int e){
        this.set.add(e);
    }
    
    public void remove(int e){
        this.set.remove(e);
    }
    
    public void union(libraryDsSet aSet){
        aSet.set.forEach(i -> {
            this.set.add(i);
        });
    }
    
    public void intersetion(libraryDsSet aSet){
        for(int i=0; i<11; ++i){
            if(!aSet.set.contains(i) || !this.set.contains(i))
                this.set.remove(i);
        }
    }
    
    public void complement(){
        for(int i=0; i<11; ++i){
            if(this.set.contains(i))
                this.set.remove(i);
            else
                this.set.add(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        s.append("{ ");
        
        for(int i=0; i<11; ++i)
            if(this.set.contains(i)){
                s.append(i); s.append(", ");
            }
        
        if(s.length() > 3 && s.substring(s.length()-2).equals(", "))
            s.delete(s.length()-2, s.length());
        
        s.append(" }");
        
        return s.toString();
    }
    
    
}

public class Sets {
    public static void main(String[] args) {
        ArraySet set = new ArraySet(new int[]{1, 4, 7, 5, 6});
        System.out.println(set);
        set.add(0);
        System.out.println(set);
        set.complement();
        System.out.println(set);
        set.intersetion(new ArraySet(new int[]{2, 5, 6, 9}));
        System.out.println(set);
        set.union(ArraySet.UNIVERSAL_SET);
        System.out.println(set);
        
        libraryDsSet set2 = new libraryDsSet(new int[]{1, 4, 7, 5, 6});
        System.out.println(set2);
        set2.add(0);
        System.out.println(set2);
        set2.complement();
        System.out.println(set2);
        set2.intersetion(new libraryDsSet(new int[]{2, 5, 6, 9}));
        System.out.println(set2);
        set2.union(libraryDsSet.UNIVERSAL_SET);
        System.out.println(set2);
        
        System.out.println("Time Complexity comparision: ");
        System.out.println("operation       Array Implementation      Efficient DS implementation");
        System.out.println("Union               O(N)                        O(N)");
        System.out.println("Intersection        O(N)                        O(N)");
        System.out.println("Complement          O(N)                        O(N)");
        System.out.println("Add                 O(1)                        O(1)");
        System.out.println("Delete              O(1)                        O(1)");
    }
    
}
