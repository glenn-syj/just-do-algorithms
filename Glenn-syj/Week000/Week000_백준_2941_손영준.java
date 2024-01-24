import java.util.*;
import java.io.*;

/* 2941. 크로아티아 알파벳
 * 1. 조건
 * 	1-1. croatian을 이루는 string의 length를 1개로 간주해야 함
 * 2. 풀이
 * 	2-1. 크로아티아 문자에 대한 배열 생성 후 교체 -- 확인 필요 X
 * 	2-2. 가장 쉬운 방법: String.replace(CharSequence target, CharSequence replacement)
 */

public class Week000_백준_2941_손영준 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String alphabets = br.readLine().trim();
		// dz= 는 z=보다 무조건 먼저와야 함
		// -> 이미 안에 z=를 포함하고 있기에 그렇지 않으면 개수 달라짐
		String[] croatians = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		for (String croatian : croatians) {
			alphabets = alphabets.replace(croatian, "?");
		}
		
		bw.write(String.valueOf(alphabets.length()));
		bw.close();
	}
}
