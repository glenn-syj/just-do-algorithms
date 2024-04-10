package boj_20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, start, end;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		
		for (int i = 0; i < n; i++) {
			makeSet(i);
		}
		
		for (int i = 1; i <= m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			if (findSet(start) != findSet(end)) {
				union(findSet(start), findSet(end));
			} else if (findSet(start) == findSet(end)) {
				System.out.println(i);
				break;
			}
			
			if (i == m) {
				System.out.println(0);
			}
	    }

	}
	
	public static void makeSet(int x) {
		parent[x] = x;
	}
	
	public static int findSet(int x) {
	    if (x != parent[x]) {
	        parent[x] = findSet(parent[x]);
	    } else if (x == parent[x]) {
	    	return x;
	    }
	   
	      return parent[x];
	}
	
	public static void union(int x, int y) {
		parent[y] = x;
	}
	 
}
