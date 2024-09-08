//Harshitha Mahesh
//3A

import java.util.Arrays;

public class SearchSort{
    
    private int[] nums;
    
    public SearchSort(int[] nums){
        this.nums = nums;
    }
    
    public SearchSort(int size){
        nums = new int[size];
        initArray();
    }
    
    public void initArray(){
        for(int a = 0; a < nums.length; a++){
            nums[a] = (int)(Math.random() * 1000 + 1);
        }
    }
    
    public int[] getNums(){
        return nums;
    }
    
    public int getNumAtIndex(int index){
        return nums[index];
    }
    
    public void sort(){
        Arrays.sort(nums);
    }
    
    private void swap(int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public int linearSearch(int key){
        for(int a = 0; a < nums.length; a++){
            if(nums[a] == key)
                return a;
        }
        return -1;
    }
    
    public int linearSearchRecur(int key){
        return linearSearchRecur(key, 0);
    }
    
    private int linearSearchRecur(int key, int index){
        if(index == nums.length){
            return -1;
        }
        if(nums[index] == key){
            return index;
        }
        return linearSearchRecur(key, index + 1);
    }
    
    public int binarySearch(int key){
        Arrays.sort(nums);
        int high = nums.length - 1;
        int low = 0;
        while(high >= low){
            int mid = (high + low) / 2;
            if(nums[mid] == key){
                return mid;
            }
            else if(nums[mid] > key){
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return -1;
    }
    
    public int binarySearchRecursive(int key){
        Arrays.sort(nums);
        return binarySearchRecursive(key, nums.length - 1, 0);
    }
    
     private int binarySearchRecursive(int key, int high, int low){
        int mid = (high + low) / 2;
        if(low > high){
            return -1;
        }
        else if(nums[mid] == key){
            return mid;
        }
        else if(nums[mid] > key){
            return binarySearchRecursive(key, mid - 1, low);
        }
        else
            return binarySearchRecursive(key, high, mid + 1);
    }
    public void bubbleSort(boolean print){
        boolean isClear = true;
        for(int a = 0; a < nums.length - 1; a++){
            if(nums[a] > nums[a + 1]){
                swap(a, a + 1);
                isClear = false;
                if(print){
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
        if(!isClear){
            bubbleSort(print);
        }
    }
    
    public void selectionSort(boolean print){
        selectionSort(print, 0);
    }
    
    private void selectionSort(boolean print, int index){
        if(index < nums.length - 1){
            int smallest = Integer.MAX_VALUE;
            int smallestIndex = -1;
            for(int a = index; a < nums.length; a++){
                if(nums[a] < smallest){
                    smallest = nums[a];
                    smallestIndex = a;
                }
            }
            swap(index, smallestIndex);
            if(print){
                System.out.println(Arrays.toString(nums));
            }
            selectionSort(print, index + 1);
        }
    }
    
    public void insertionSort(boolean print){
        if(print){
            System.out.println(Arrays.toString(nums));
        }
        for(int a = 0; a < nums.length - 1; a++){
            if(nums[a] > nums[a + 1]){
                swap(a, a + 1);
                a = 0;
                if(print){
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
    }
    
    public void merge(int[] left_arr,int[] right_arr, int[] arr,int left_size, int right_size){
      
      int i=0,l=0,r = 0;
      //The while loops check the conditions for merging
      while(l<left_size && r<right_size){
          
          if(left_arr[l]<right_arr[r]){
              arr[i++] = left_arr[l++];
          }
          else{
              arr[i++] = right_arr[r++];
          }
      }
      while(l<left_size){
          arr[i++] = left_arr[l++];
      }
      while(r<right_size){
        arr[i++] = right_arr[r++];
      }
  }

  public void mergeSort(int [] arr, int len){
      if (len < 2){return;}
      
      int mid = len / 2;
      int [] left_arr = new int[mid];
      int [] right_arr = new int[len-mid];
      int k = 0;
      for(int i = 0;i<len;++i){
          if(i<mid){
              left_arr[i] = arr[i];
          }
          else{
              right_arr[k] = arr[i];
              k = k+1;
          }
      }
      mergeSort(left_arr,mid);
      mergeSort(right_arr,len-mid);
      System.out.println(Arrays.toString(arr));
      merge(left_arr,right_arr,arr,mid,len-mid);
  }
}