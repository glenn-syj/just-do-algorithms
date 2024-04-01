package boj_12865_박건택;

/*
 * 수업시간에 했던 조합을 이용해서 풀어보겠습니다.
 * 재귀함수를 이용한 조합
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
	static int num;
	static int limit;
//	static List<Integer> weightSum;
	static int weightSum;
	static int valueSum;
	static int cnt;
	static boolean[] select;
	static int[] valueArray;
	static int[] weightArray;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		num = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		select = new boolean[num];
		valueArray = new int[num];
		weightArray = new int[num];
		cnt=0;
//		weightSum = new ArrayList<>();
		max = -1;
		
		for(int i =0; i<num; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			weightArray[i] = Integer.parseInt(st.nextToken());
			valueArray[i] = Integer.parseInt(st.nextToken());
		}
		
		powerSet(0);
		
//		System.out.println(Arrays.toString(weightArray));
//		System.out.println(weightSum.toString());
		System.out.println(max);
	}
	
	public static void powerSet(int idx) {
		

		// base case
//		if(sum > limit) {
//			return;
//		}
		if(idx >= num) {
			weightSum =0;
			valueSum =0;
			for(int i =0; i < num; i++) {
				
				if(select[i]) {
					weightSum += weightArray[i];
					valueSum += valueArray[i];
				}
			}
			
			if(weightSum <= limit) {
				
//				weightSum.add(weightSum);
				if(valueSum>=max) {
					max = valueSum;
				}
			}
			
			return;
		}
		
		// recursive case
		select[idx] = true;
		powerSet(idx+1);
		
		select[idx] = false;
		powerSet(idx+1);
		
	}
}
