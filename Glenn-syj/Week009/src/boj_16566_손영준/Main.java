package boj_16566_카드게임;

/* B16566. 카드 게임
 * 
 * 1. 조건
 * 	1-1. N개의 빨간 파드와 파란 카드가 있고 나누어 가짐
 * 	1-2. 철수와 민수
 * 		(1) A철수: 본인이 낼 카드를 마음대로 조작 가능
 * 			-> 여기에 대해서는 입력만 고려
 * 		(2) B민수: 철수가 낼 카드를 알 수 있음
 * 2. 풀이
 * 	2-1. 이분 탐색 이용
 * 		-> O(NK) 시간복잡도가 O(K log N)으로 줄어듦
 * 		-> K가 min(M, 10000) 이므로, 지우는 것보다 null이나 0으로 만드는 게 나음
 * 		-> 1 이 계속 반복된다면 O(NK)가 되어버림
 * 	2-2. 그럼 안 빼고 어떻게 이용하지?
 * 		-> 일단 빼봐야 할듯...?
 * 		-> 빼는 경우: 4_000_000 번에 대해서 철수가 계속 1만 고르면
 * 		-> 앞자리에 있는 것들을 하나씩 날려야 함... (비효율적인것 같은데?)
 * 		-> 최악의 경우 거의 4_000_000 * 10_000 = 400 억 번의 연산을 하게 됨...
 * 	2-3. 그러면 받아온 수들에 대해서, 전부 하나의 ArrayList에 넣는게 아니라
 * 		-> K개로 나누면 어떨까?
 * 		-> M/K개 씩 들어가고, 남은건 마지막에 모두 넣기?
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static List<Integer> list;
	static List<List<Integer>> segmentedLists;
	static List<Integer> currSegList;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		segmentedLists = new ArrayList<List<Integer>>();
		
		st = new StringTokenizer(br.readLine());
		
		while (st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list);
		
		segmentedLists.add(new ArrayList<>());
		
		int checkpoint = 0;
		List<Integer> segList;
		
		for (int i=0; i<M; i++) {
			if (i/K > checkpoint) {
				checkpoint++;
				segmentedLists.add(new ArrayList<>());
			}
			
			segList = segmentedLists.get(checkpoint);
			segList.add(list.get(i));
			
		}
		
		st = new StringTokenizer(br.readLine());
		Integer curr, last, foundIdx;
		for (int i=0; i<K; i++) {
			
			curr = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<segmentedLists.size(); j++) {

				segList = segmentedLists.get(j);

				if (segList.size() == 0) {
					continue;
				}
				
				last = segList.get(segList.size()-1);
				if (last <= curr) {
					continue;
				} else {
					currSegList = segList;
					foundIdx = search(0, currSegList.size()-1, curr);
					sb.append(currSegList.get(foundIdx)).append('\n');
					currSegList.remove((int) foundIdx);
					break;
				}
				
			}
			
		}
		
		
		bw.write(sb.toString());
		bw.close();
	}
	
	public static int search(int start, int end, int value) {
		
		int mid = (start+end)/2;
		if (currSegList.get(start) > value) {
			return start;
		}
		
		while (start < end) {
			mid = (start+end)/2;
			if (currSegList.get(mid) <= value) {
				start = mid+1;
			} else {
				end = mid;
			}
		}
		
		return start;
		
	}
	
}