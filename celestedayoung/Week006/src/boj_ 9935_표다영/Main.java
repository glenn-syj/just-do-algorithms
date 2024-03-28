package APS.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static String input;
    static String danger;
    static int dangerSize;
    static boolean flag;
    static String result;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();

        input = br.readLine();
        danger = br.readLine();
        dangerSize = danger.length();

        for (int i = 0; i < input.length(); i++) {
            char word = input.charAt(i);
            sb.append(word);

            if (sb.length() >= dangerSize) {
                flag = true;

                for (int j = 0; j < dangerSize; j++) {
                    char compareWord = sb.charAt(sb.length() - dangerSize + j);
                    char dangerWord = danger.charAt(j);

                    if (compareWord != dangerWord) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sb.delete(sb.length() - dangerSize, sb.length());
                }
            }
        }

        result = sb.isEmpty() ? "FRULA" : sb.toString();
        System.out.println(result);
    }
}
