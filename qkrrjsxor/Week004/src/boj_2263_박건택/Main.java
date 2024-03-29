package boj_2263_박건택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N;
	static int[] inOrder;
	static int[] postOrder;
	static int[] preOrder;
//	static List<Integer> inOrder = new ArrayList<>();
//	static List<Integer> postOrder = new ArrayList<>();
//	static List<Integer> preOrder = new ArrayList<>();
	static int root;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		String str = br.readLine();
		N = Integer.parseInt(str);

		inOrder = new int[N + 1];
		postOrder = new int[N + 1];
		preOrder = new int[N + 1];

		str = br.readLine();
		st = new StringTokenizer(str);

		for (int i = 1; i < N + 1; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		str = br.readLine();
		st = new StringTokenizer(str);

		for (int i = 1; i < N + 1; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		root = postOrder[N];
		
		leftSub(root);

	}

	static void leftSub(int root) {
		int len = N;

		System.out.println(root);
		
		
		int idx = 0;
				
		for(int i = 1; i<N+1; i++) {
			if(inOrder[i] == root) {
				idx = i;
			}
		}
		inOrder[idx] = 0;
		System.out.println("idx :" + idx);
		System.out.println(Arrays.toString(inOrder));
		
		int subRoot = postOrder[idx-1];

		
		//재귀 기저조건
		if(idx ==1 || inOrder[idx-1] ==0) {

			subRoot = 
			return ;
		}

		leftSub(subRoot);
		
		
		return;
	}
	
}
