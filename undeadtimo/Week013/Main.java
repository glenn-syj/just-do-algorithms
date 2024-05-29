package boj_1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// index를 사람 번호와 매칭시켜서 알고있는지 아닌지를 체크해준다.
		// 알고 있다면 true, 모르고 있다면 false
		boolean[] people_know = new boolean[n + 1];
		
		
		HashSet<Integer>[] parties = new HashSet[m + 1];
		
		for(int i = 1; i <= m; i++) {
			parties[i] = new HashSet<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int know_num = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람을 넣어준다.
		for(int i = 1; i <= know_num; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			people_know[tmp] = true;
		}
		
		parent = new int[n + 1];
		
		// 각각의 부모를 자기 자신으로 지정해준다.
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for(int p = 1; p <= m; p++) { // party number
			
			String[] inputs = br.readLine().split(" ");
			
			int party_num = Integer.parseInt(inputs[0]);
			
			if(party_num <= 1) {
				parties[p].add(Integer.parseInt(inputs[1]));
				continue;
			}
			
			for(int j = 1; j < party_num; j++) {
				int a = Integer.parseInt(inputs[j]);
				int b = Integer.parseInt(inputs[j + 1]);
				
				if(find(a) != find(b)) {
					union(a, b);
				}
				
				parties[p].add(a);
				parties[p].add(b);
			}
			
		}
		
		boolean[] visited = new boolean[n + 1];
		for(int i = 1; i <= n; i++) {
			if(people_know[i] && !visited[i]) {
				
				int root = find(i);
				
				for(int j = 1; j <= n; j++) {
					if(find(j) == root) {
						people_know[j] = true;
						visited[j] = true;
					}
				}
			}
		}
		
		int result = 0;
		for(int i = 1; i <= m; i++) {
			boolean flag = false;
			for(int person : parties[i]) {
				if(people_know[person]) {
					flag = true;
					break;
				}
			}
			if(!flag)result++;
		}
		
		System.out.println(result);
		
	}
	
	public static int find(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}
		parent[idx] = find(parent[idx]);
		return parent[idx];
	}
	
	public static void union(int a, int b) {
		int parent_b = find(b);
		parent[parent_b] = a;
	}
	
}