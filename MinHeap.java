public class MinHeap {
    private int n=0;
    private int arr[];
    
    
    public MinHeap(int input[]) throws Exception{
	n = input.length;
        arr = new int[n];
        for(int i=0;i<input.length;i++){
            arr[i] = input[i];
        }
        
        //build heap.
        if(!buildHeap()) throw new Exception("Error occured");
    }
    
    // overall complexity O(n).
    private boolean buildHeap(){
        
       //start from height-1 and perform heapify on all indexes in bottom to top manner.
       for(int i=n/2; i>=0;i--){
		   
           if(!heapify(i)) return false;
       } 
       
       return true;
    }
    
    
    private boolean heapify(int i){
        
		
        //if invalid index.
        if(i >= n)return false;
		
        //find left and right children.
        int l = 2*i + 1;
        int r = 2*i + 2;
        int min = i;
        
        
        //if left child has less value.
        if(l < n && arr[min] > arr[l]){
            min = l;
        }
        
        //if right child has less value.
        if(r < n && arr[min] > arr[r]){
            min = r;
        }
        
		
        //if we find min value at any chile, update and recurse.
        if(min != i){
            
            //swap i and min.
            arr[min] ^=arr[i];
            arr[i]  ^=arr[min];
            arr[min] ^=arr[i];
            
            //perform heapify at newly disturbed index.
            return heapify(min);
        }
        
        return true;
    }
    
    
    private boolean changePriority(int i){
        
        //if invalid index.
        if(i>=n || i<0)return false;
        
        
        while(i>0){
            int p = i/2 + i%2 -1;
            
            //if parent has bigger element then swap.
            if(arr[p] > arr[i]){
                arr[p] ^= arr[i];
                arr[i] = arr[p]^arr[i];
                arr[p] = arr[p]^arr[i];
                i=p;
            }
            
            else break;
        }
        
        return true;
    }
    
    // overall complexity O(logn).
    public int top(){
        
        //If heap is empty.
        if(n==0) return -1;
        
        //get the top element.
        int result = arr[0];
        
        //if heap has more than 1 element.
        if(n>1){
            arr[0] = arr[n-1];    
        }
        
        //perform heapify on 0th index. Check the status as well.
        if(heapify(0)){
			
		//if success, reduce the heap size by 1 and return the value.
		n-=1;
		return result;
	} 
        
        
        //if there is some problem during minHeapify, revert the changes.
	arr[n-1] = arr[0];
        arr[0] = result;
        return -1;
        
    }
    
    // overall complexity O(logn).
    public boolean insert(int x){
        
        // if heap is full.
        if(n == arr.length) return false;
        
        //place element at the last position.
        arr[n] = x;
		
	//increase the heap size by 1.
	n+=1;
        
        //If changePriority() fails, revert the changes.
        if(!changePriority(n-1)){
			
		n--;
		return false;
	}
		
	return true;
        
        
    }
    
    // overall complexity O(1).
    public int len()
    {
        return n;
    }
	
}

