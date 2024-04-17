package boj_12920_평범한배낭2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/* B12920. 평범한 배낭2
 * 
 * 1. 조건
 * 	1-1. 기본적으로 0-1 Knapsack 과 동일
 * 		-> 같은 V, C 가 나열되는 대신 K로 응축되어 있다고 생각하면 편함
 * 	1-2. 물건 종류가 N개 이지만, K 가 최대 10000개 있으므로 총 NK개의 개수
 * 		-> 기존처럼 모두 하나씩 할 시에는
 * 		-> O(K^2 N^2) 이므로 시간초과가 날 가능성이 높음
 *	1-3. '같은 종류'의 물건에 대해서는 한 row에 동시적으로 작업하면됨
 *
 * 2. 풀이
 * 	2-1. 풀이에서 가장 중요한 점은 같은 종류에 대해서는 한 행에만 적으면 된다는 것
 * 		(1) currWeight % V = 0 이고, 횟수를 추가한 count가 K보다 작거나 같을 때
 * 			-> V, C를 바로 사용하지 않음
 * 		(2)	전체 M에서 currWeight를 뺀 남은 자리 leftWeight에 대해서	
 * 			maxVal을 만들어둔다. 
 * 			-> dp[i][currWeight]는
 * 				A. dp[i-1][currWeight-(cnt*V)] + cnt*C '들' 중 최댓값 (cnt를 기준으로 반복문)
 * 				B. dp[i-1][currWeight]
 * 			-> A과 B중 더 큰 값을!
 */

public class Main {
	
	static class Item implements Comparable<Item> {
		
		int V, C, K;
		
		public Item(int V, int C, int K) {
			this.V = V;
			this.C = C;
			this.K = K;
		}

		@Override
		public int compareTo(Item o) {
			if (this.V > o.V) {
				return -1;
			} else if (this.V == o.V) {
				if (this.C > o.C) {
					return -1;
				} else if (this.C == o.C) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long startTime = System.currentTimeMillis();
//		System.out.println("startTime: " + startTime);
		
		int V, C, K;
		
		int[] counts = new int[M+1];
		ArrayList<Item> itemList = new ArrayList<>();
		Map<Integer, int[]> queueMap = new HashMap<>();
		
		for (int idx=1; idx<=N; idx++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			itemList.add(new Item(V, C, K));
			
		}
		
		Collections.sort(itemList);
		
		Item item;
		int initPtr = 1;
		int kCount = 0;
		for (int idx=0; idx<N; idx++) {
			item = itemList.get(idx);
			kCount = 0;
			
			if (queueMap.get(item.V) == null) {
				initPtr = 1;
				queueMap.put(item.V, new int[M/item.V+1]);
			}
			
			while (kCount < item.K && queueMap.get(item.V)[M/item.V] == 0) {
				queueMap.get(item.V)[initPtr] = queueMap.get(item.V)[initPtr-1] + item.C;
				initPtr++;
				kCount++;
			}
			
			
		}
		
		
		int[] currArr;
		int mapSize = queueMap.size();
		int[][] dp = new int[mapSize+1][M+1];
		
		int ptr = 1;
		int innerPtr;
		
		int possibleK, localMax, localVal;
		
		for (int key: queueMap.keySet()) {
			
			currArr = (int[]) queueMap.get(key);
			for (int currWeight=1; currWeight<=M; currWeight++) {
				
				possibleK = currWeight / key;
				localMax = Integer.MIN_VALUE;
				
				if (possibleK > currArr.length) {
					possibleK = currArr.length;
				}
				
				for (int cnt=0; cnt <= possibleK; cnt++) {
					localVal = dp[ptr-1][currWeight-(cnt*key)] + currArr[cnt];
					if (localMax < localVal) {
						localMax = localVal;
					}
				}
				
				dp[ptr][currWeight] = localMax;
			}
			
			ptr++;
		}
		
//		long endTime = System.currentTimeMillis();
//		System.out.println("endTime: " + endTime);
//		System.out.println("elapsedTime: " + (endTime-startTime));
		
		sb.append(dp[mapSize][M]);
		bw.write(sb.toString());
		bw.close();
		sb.setLength(0);
		
		
		
		
		
		
		
	}
	
}