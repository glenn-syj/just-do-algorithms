package boj_2263_트리의순회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] inordered;
	static int[] postordered;
	static int[] inaddress;
	static int[] postaddress;
	static boolean[] visited;
	static int N;
	static boolean leftSkewed = true;
	static boolean rightSkewed = true;
	
	public static void main(String[] args) throws IOException {
		
//		FileReader file = new FileReader("./src/boj_2263_트리의순회/input.txt");
//		br = new BufferedReader(file);

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
	
		// inorder와 postorder를 저장
		inordered = new int[N+1];
		postordered = new int[N+1];
		
		// 매번 탐색하면 시간이 걸리기에 inorder와 postorder 순서에 대한 위치값을 저장
		inaddress = new int[N+1];
		postaddress = new int[N+1];
		
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			inordered[i] = Integer.parseInt(st.nextToken());
			inaddress[inordered[i]] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			postordered[i] = Integer.parseInt(st.nextToken());
			postaddress[postordered[i]] = i;
		}
		
		traceNode(postordered[N], 1, N);
		
		bw.close();
		
		
		
	}
	
	public static void traceNode(int root, int leftEnd, int rightEnd) throws IOException {
		
		visited[root] = true;
		bw.write(root + " ");
		
		boolean isLeftSkewed = rightEnd == inaddress[root];
		boolean isRightSkewed = leftEnd == inaddress[root];
		
		
		
		// leftSkewed 검사
		if (isLeftSkewed) {
			for (int i=1; i<=rightEnd-leftEnd-1; i++) {
				if (!isLeftSkewed) {
					break;
				}
				isLeftSkewed = inordered[inaddress[root]-i] == postordered[postaddress[root]-i];
			}
		}
		
		if (isRightSkewed) {
			for (int i=1; i<=rightEnd-leftEnd-1; i++) {
				if (!isRightSkewed) {
					break;
				}
				isRightSkewed = inordered[inaddress[root]+i] == postordered[postaddress[root]-i];
			}
			
		}
		
		if (isLeftSkewed) {
			for (int i=inaddress[root]-1; i >= leftEnd; i--) {
				bw.write(inordered[i] + " ");
			}
			return;
		}
		
		if (isRightSkewed) {
			for (int i=inaddress[root]+1; i <= rightEnd; i++) {
				bw.write(inordered[i] + " ");
			}
			return;
		}
		
		int leftChildIdx, rightChildIdx;
		
		leftChildIdx = 0;
		rightChildIdx = 0;

		for (int i=1; i<inaddress[root]; i++) {
			int val = postordered[inaddress[root]-i];
			if (!visited[val] && inaddress[val] < inaddress[root]) {
				leftChildIdx = val;
				break;
			}
		}
		
		for (int i=1; i<=rightEnd-inaddress[root]; i++) {
			int val = postordered[postaddress[root]-i];
			if (!visited[val]) {
				rightChildIdx = val;
				break;
			}
		}
		
		if (leftChildIdx != 0) {
			traceNode(leftChildIdx, leftEnd, inaddress[root]-1);
		}
		if (rightChildIdx != 0) {
			traceNode(rightChildIdx, inaddress[root]+1, rightEnd);
		}
		
	}
	
}