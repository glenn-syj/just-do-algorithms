import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word = sc.next();

		String[] cro = { "dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z=" };

		int len = word.length();

		int cnt = 0;

		wc: for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < cro.length; j++) {
				if (i < len - 2 && word.substring(i, i + 3).equals(cro[j])) {
					cnt += 2;
					i++;
					continue wc;
				}
				if (word.substring(i, i + 2).equals(cro[j])) {
					cnt += 1;
					continue wc;
				}
			}
		}
		System.out.println(len - cnt);
	}
}