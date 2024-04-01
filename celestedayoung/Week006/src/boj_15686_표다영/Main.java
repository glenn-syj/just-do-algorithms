import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, min;
    static List<int[]> stores, houses, returnList;
    static int[][] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stores = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (token == 2) {
                    stores.add(new int[]{i, j});
                } else if (token == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[stores.size()];
        result = new int[stores.size()][1];
        returnList = new ArrayList<>();
        min = Integer.MAX_VALUE;
        
        pickStores(0, 0);
        
        System.out.println(min);
        
    }

    public static void pickStores(int start, int depth) {

        if (depth == M) {
        	
        	int sum = 0;
        	
        	for (int h = 0 ; h < houses.size(); h++) {
        		
        		int chickenLength = Integer.MAX_VALUE;
        		int houseR = houses.get(h)[0];
        		int houseC = houses.get(h)[1];
        		
        		for (int c = 0; c < M; c++) {
        			int storeR = result[c][0];
        			int storeC = result[c][1];
        			
        			int length = Math.abs(houseR - storeR) + Math.abs(houseC - storeC);
        			
        			if (chickenLength > length) {
        				chickenLength = length;
        			}
        		}
        		
        		sum += chickenLength;
        		
        	}
        	if (min > sum) {
        		min = sum;
        	}
        	
        	return;
        }
        
        for (int i = start; i < stores.size(); i++) {
            if (!visited[i]) {
                result[depth] = stores.get(i);
                visited[i] = true;
                pickStores(i + 1, depth + 1);
                visited[i] = false;
            }
        }

    }

}
