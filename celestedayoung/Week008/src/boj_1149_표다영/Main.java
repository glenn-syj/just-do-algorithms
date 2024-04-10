package boj_1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N; 

	static int[][] d = new int[1001][3];
	static int[] red = new int[1001];
	static int[] green = new int[1001];
	static int[] blue = new int [1001];
	
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		
		for (int i = 1; i <= N; i++) {
			
			st = new StringTokenizer(br.readLine());

			red[i] = Integer.parseInt(st.nextToken());
			green[i] = Integer.parseInt(st.nextToken());
			blue[i] = Integer.parseInt(st.nextToken());
			
		}
		
		d[1][0] = red[1];
		d[1][1] = green[1];
		d[1][2] = blue[1];
		
		for (int i = 2; i <= N; i++) {
			
			d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + red[i];
			d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + green[i];
			d[i][2] = Math.min(d[i - 1][0], d[i - 1][1]) + blue[i];
			
		}
		
		Arrays.sort(d[N]);
		
		System.out.println(d[N][0]);
		
	}

}
