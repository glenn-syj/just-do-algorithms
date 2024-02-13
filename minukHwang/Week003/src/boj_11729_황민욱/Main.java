package boj_11729_황민욱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int count = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 25);

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		hanoiTower(N, 1, 3, 2);
		System.out.println(count);
		bw.flush();
	}

	public static void hanoiTower(int n, int from, int to, int other) throws IOException {
		count++;
		if (n == 1) {
			bw.write(from + " " + to + "\n");
			return;
		}

		hanoiTower(n - 1, from, other, to);
		bw.write(from + " " + to + "\n");
		hanoiTower(n - 1, other, to, from);
	}
}
