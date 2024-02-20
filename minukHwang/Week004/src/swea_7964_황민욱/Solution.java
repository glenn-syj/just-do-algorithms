package swea_7964_황민욱;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int cityNum = sc.nextInt();
			int limit = sc.nextInt();
			
			// 지도 정보 받아오기
			int[] map = new int[cityNum];
			
			for(int i = 0; i < cityNum; i++) {
				map[i] = sc.nextInt();
			}
			
			int index = 0;
			int count = 0;
			
			while(index < cityNum) {
				boolean isConnected = false;
				
				if(map[index] == 0) {
					map[index] = 1;
					count++;
				}
				
				
				for(int i = limit; i >= 1; i--) {
					if(index + i < cityNum && map[index + i] == 1) {
						index += i;
						isConnected = true;
						break;
					}
				}
				
				if(isConnected) {
					continue;
				}
				
				index += limit;
			}
			
			
			
			System.out.printf("#%d %d\n", t , count);
		}
	}
}
