package boj_24511_박건택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;


/*
 * 자료구조 배열 중에서 스택에 있는 것들은 출력될(pop될) 기회가 없다!!!
 * -> 결과적으로 큐에 있는 원소들과 추가 입력 data들이 역순으로 출력된다.
 * 
 * 큐에 있는 애들과 추가 입력들을 넣을 수 있는 배열을 만들자
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = bf.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		
		
		int N = Integer.parseInt(st.nextToken());

		int[] A = new int[N];
		int[] B = new int[N];
		int count = 0;
		
		String inputA = bf.readLine();
		st = new StringTokenizer(inputA);
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());

		}
		
		String inputB = bf.readLine();
		st = new StringTokenizer(inputB);
		
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(bf.readLine());

		int[] data = new int[M]; // 입력받을 C 수열
		int[] result = new int[M]; // 출력할 결과물들 담을 배열

		
		String inputData = bf.readLine();
		st = new StringTokenizer(inputData);
		
		for (int i = 0; i < M; i++) {
			data[i] = Integer.parseInt(st.nextToken());

		}

		int idx = 0;
		
		for (int i = N - 1; i >= 0; i--) { // 기존에 있던 큐-스택 자료구조들에서 큐에 있는 원소들만 배열 역순으로 가져오기

			if (A[i] == 0 && idx <= (M - 1)) { // 큐 자료형이면 해당 자리에 있는 B[i] 원소값을 result 배열에 저장
				result[idx] = B[i];
				idx++;
			}
		}



		for (int i = 0; i < M && idx <= M-1; i++) {	//큐에 미리 있던 원소들 다 넣은 뒤에 자리 남으면 추가로 입력받은 C 수열 입력하기 
			result[idx] = data[i];
			idx++;
		}
		
		
		//출력
		for(int i = 0; i<M; i++) {
			bw.write(result[i] + " ");;
		}
		bw.flush();

	}

}

