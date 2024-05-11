
public class Ex11_20210808043 {
	   
    public static int numOfTriplets(int[] arr, int sum) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] < sum) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

   
    public static int kthSmallest(int[] arr, int k) {
    	sorter(arr, 0, arr.length - 1);
        return arr[k - 1];
    }

    
    private static void sorter(int[] arr, int low, int high) {
        if (low < high) {
        	int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            int temp = arr[i+1];
            arr[i+1] = arr[high];
            arr[high] = temp;
            int index = i + 1;
            sorter(arr, low, index - 1);
            sorter(arr, index + 1, high);
        }
    }

    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
    public static String subSequence(String str) {
    	StringBuilder subSeq = new StringBuilder();
    	subSeq.append(str.charAt(0));
    	for (int i = 1;i < str.length();i++) {
    		if (str.charAt(i) > str.charAt(0)) subSeq.append(str.charAt(i));
    	}
    	return subSeq.toString();
    }

    
    public static int isSubString(String str1, String str2) {
    	for (int i = 0;i <= str1.length()-str2.length();i++) {
    		if ((str1.charAt(i) == str2.charAt(0)) && (str1.charAt(i+1) == str2.charAt(1))) {
    			if ((str1.charAt(i+2) == str1.charAt(2)) && (str1.charAt(i+3) == str2.charAt(3))) {
    				return i;
    			}
    		}
    	}
    	return -1;
    }

    
    public static void findRepeats(int[] arr, int n) {
    	int[] arr2 = new int[0];
    	for (int i = 0;i < arr.length;i++) {
    		int count = 0;
    		for (int j = 0;j < arr.length;j++) {
    			if (arr[i] == arr[j]) {
    				count++;
    			}
    			if (count > n) arr2 = addToArray(arr2, arr[i]);
    		}
    	}
    	String repeatedElements = "";
    	for (int i : arr2) {
    		repeatedElements += i + " ";
    	}
    	System.out.println(repeatedElements.trim());
    }
    
    public static int[] addToArray(int[] arr, int i) {
    	int[] arr2 = new int[arr.length];
    	arr2[arr2.length-1] = i;
    	return arr2;
    }

}

