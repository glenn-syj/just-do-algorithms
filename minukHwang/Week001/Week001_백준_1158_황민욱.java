import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Week001_백준_1158_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		List<Integer> arr = new ArrayList<Integer>();
		List<Integer> answer = new ArrayList<Integer>();

		for (int i = 1; i <= n; i++) {
			arr.add(i);
		}

		int index = k-1;

		while (arr.size() > 1) {
			answer.add(arr.get(index));
			arr.remove(index);
			
			index += k-1;
			if(index > arr.size()-1) {
				index %= arr.size();
			}
		}
		
		System.out.print("<");
		for(int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i)+", ");
		}
		System.out.print(arr.get(0)+">");
	}
}
