package boj_9935_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] charArr = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();
		List<Character> list = new ArrayList<>();

		int charLen = charArr.length;
		int bombLen = bomb.length;
		int idx = 0 ;
		int k = bombLen;
		for (int i = 0; i < charLen; i++) {
			list.add(charArr[i]);
//			System.out.println(idx);
			
			if(idx >= bombLen-1 && list.get(idx) == bomb[bombLen-1]) {
				k= bombLen-1;
//				System.out.println("k : " + k);
				for(int t = idx; t > idx-bombLen; t--) {
//					System.out.println("te : " + t);
					if(list.get(t)==bomb[k]) {
						k--;
					}
				}
//				System.out.println("kk :" + k);
				if(k==-1) {
					for(int t = idx; t > idx-bombLen; t--) {
						list.remove(t);
					}
					idx = idx-bombLen;
				}
			}
			
			idx++;
		}



		if (list.size() == 0) {
			System.out.println("FRULA");
		} else {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				sb.append(list.get(i));
			}
			System.out.println(sb.toString());
		}
	}

}
