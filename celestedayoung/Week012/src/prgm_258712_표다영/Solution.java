import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {

        int answer = 0;
        int friendsNum = friends.length;
        
        HashMap<String, Integer> friendsInfo = new HashMap<>();
        
        int[] giftDegree = new int[friendsNum];
        int[][] giftGraph = new int[friendsNum][friendsNum];
        
        for ( int i = 0; i < friendsNum; i++ ) {
            friendsInfo.put(friends[i], i);
        }
        
        for ( String gift : gifts ) {
            String[] giftName = gift.split(" ");
            giftDegree[friendsInfo.get(giftName[0])]++;
            giftDegree[friendsInfo.get(giftName[1])]--;
            giftGraph[friendsInfo.get(giftName[0])][friendsInfo.get(giftName[1])]++;    
        }
        
        for ( int i = 0; i < friendsNum; i++) {
            int num = 0;
            for ( int j = 0; j < friendsNum; j++) {
                if (i == j) continue;       
                
                if (giftGraph[i][j] > giftGraph[j][i] ||
                     (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                        num++;
                    }
            }
            
            if (answer < num) {
                answer = num;
            }
            
        }
        
        return answer;
    }
}