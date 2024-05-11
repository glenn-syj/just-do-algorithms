package prgm_258712_가장많이받은선물;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
public int solution(String[] friends, String[] gifts) {
        
        Map<String, Integer> mapper = new HashMap<>();
        int id = 0;
        int N = friends.length;
        int[][] giftRelations = new int[N][N];
        int[] givenGifts = new int[N];
        int[] receivedGifts = new int[N];
        int[] currentGifts = new int[N];
        
        for (String friend: friends) {
            mapper.put(friend, id++);
        }
        
        StringTokenizer st;
        
        String giver;
        String receiver;
        for (String relation: gifts) {
            st = new StringTokenizer(relation);
            giver = st.nextToken();
            receiver = st.nextToken();
            giftRelations[mapper.get(giver)][mapper.get(receiver)]++;
            givenGifts[mapper.get(giver)]++;
            receivedGifts[mapper.get(receiver)]++;
        }
        
        for (int row=0; row<N; row++) {
            for (int col=0; col<N; col++) {
                if (row==col) continue;
                
                if (giftRelations[row][col] > giftRelations[col][row]) {
                    currentGifts[row]++;
                } else if (giftRelations[row][col] == giftRelations[col][row]) {
                    if (givenGifts[row]-receivedGifts[row] > givenGifts[col]-receivedGifts[col]) {
                        currentGifts[row]++;
                    }
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        
        for (int giftCnt: currentGifts) {
            if (max < giftCnt) {
                max = giftCnt;
            }
        }
        
        return max;
    }
}
