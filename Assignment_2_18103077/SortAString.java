import java.util.Arrays;
public class SortAString {
    
    private void merge(String[] arr, int lo, int mid, int hi){  
        int l = mid - lo + 1;  
        int r = hi - mid;  

        String[] LArray = new String[l];  
        String[] RArray = new String[r];  

        for (int i=0; i<l; ++i)  LArray[i] = arr[lo + i];  

        for (int j=0; j<r; ++j)  RArray[j] = arr[mid + 1+ j];  

        int i = 0, j = 0, k = lo;  
        
        while (i<l&&j<r)  {  
            if (LArray[i].compareTo(RArray[j]) <= 0){  
                arr[k] = LArray[i];  
                i++;  
            }else{  
                arr[k] = RArray[j];  
                j++;  
            }  
            k++;  
        }
        
        while (i<l){  
            arr[k] = LArray[i];  
            i++;  
            k++;  
        }  

        while (j<r){  
            arr[k] = RArray[j];  
            j++;  
            k++;  
        }  
    }  

    public void sort(String arr[], int lo, int hi)  
    {  
        if (lo<hi)  
        {  
            int mid = (lo+hi)/2;  
            sort(arr, lo, mid);  
            sort(arr , mid+1, hi);  
            merge(arr, lo, mid, hi);  
        }  
    } 
    
    public void sort(String[] arr){
        sort(arr, 0, arr.length-1);
    }

    public static void main(String[] args) {
        String[] arr = {"b", "a", "d", "c", "g", "f", "e"};
        (new SortAString()).sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
}
