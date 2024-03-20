package boj_12865_황민욱;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 1. 물건에 대한 클래스를 만든다.
 * 2. 물건 N개를 배열에 넣는다.
 * 3. 부분 집합을 구하는 방식으로 배낭에 넣을 수 있는 경우의 수를 환산한다. (그냥 부분 집합 구해버리면 2^100)
 * 4. 경우의 수를 구하는 과정에서 실시간으로 배낭에 물건을 넣는데, 여기서 만약에 무게 제한에 넘어가면 바로 가지치기
 * - 메모이제이션 활용해서 dp 해야할 수도?
 * 5. 최종적으로 구해진 배낭안의 물건들에 대해서 가치를 더하고 최대값을 갱신한다.
 */

class product {
	int weight;
	int value;

	product() {
	}

	product(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "product [weight=" + weight + ", value=" + value + "]";
	}
}

public class Main {
	static product[] products;
	static boolean selected[];
	static int N;
	static int K;
	static int[] maxValue;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		products = new product[N];
		maxValue = new int[K + 1];

		int minWeight = Integer.MAX_VALUE;
		int minIndex = 0;

		for (int i = 0; i < N; i++) {
			int weight = sc.nextInt();
			int value = sc.nextInt();

			if (weight < minWeight) {
				minWeight = weight;
				minIndex = i;
			}

			products[i] = new product(weight, value);
		}

		System.out.println(minWeight);
		System.out.println(minIndex);

		System.out.println(Arrays.toString(products));

	}

}
