import java.util.Scanner;

public class Week001_SWEA_1940_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int v = 0;
			int d = 0;

			for (int i = 0; i < n; i++) {
				int option = sc.nextInt();
				int dv;

				switch (option) {
				case 0:
					break;
				case 1:
					dv = sc.nextInt();
					v += dv;
					break;
				case 2:
					dv = sc.nextInt();
					v -= dv;
					if (v < 0) {
						v = 0;
					}
					break;
				}

				d += v;

			}

			System.out.printf("#%d %d%n", t, d);
		}

	}
}