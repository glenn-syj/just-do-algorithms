package prgm_2023K_미로찾기;

/* P2023K. 미로 탈출 명령어
*
* 1. 풀이
*   1-0. 조합/순열으로 풀면 4^2500이므로 절대 안됨
    1-1. 출발 지점에서 탈출 지점까지 가기 위한 dCount, lCount, rCount, uCount 계산
        -> 이미 가장 사전순으로 빠른 경로를 저장
    1-2. 출발 지점에서 탈출 지점까지의 거리가 주어진 횟수보다 크면 impossible
        -> (횟수-거리)가 홀수라면 impossible
    1-3. 따라서 dist >= k 이고, (dist-k) % 2 == 0인 경우만 이후에만 고려
 2. 조건
    2-1. d, l, r, u에 대해서 두 점 사이의 가로/세로 거리만큼 해당 Count 증가
    2-2. 이후 k에서 dist만큼 빼줌: (k-dist) 만큼의 횟수가 남아있다
        -> int leftover = k-dist; 에서
        { 여부: 해당 방향에 대해서 rangeCheck != false이거나  }
        { 각 과정에서 leftover가 0이 되면 언제나 종료! }
        (1) down 할 수 있는지 여부: down할 수 있으면 최대한 down하고 횟수만큼 uCount 증가
            -> 해당 과정에서 leftover 감소 필요
        (2) left 할 수 있는지 여부: left할 수 있으면 최대한 left하고 횟수만큼 rCount 증가
            -> left가 불가능할 때까지 l추가 rCount 증가, leftover--;
        (3) right 할 수 있는지 여부: (r,l) 반복
        (4) up 할 수 있는지 여부: (u, d) 반복

*/

class Solution {
    
    static int N, M, R, C;
    static StringBuilder sb = new StringBuilder();
    // d l r u 순으로 deltas 만들기
    static int[][] deltas = new int[][] { {1, 0}, {0, -1}, {0, 1}, {-1, 0} };
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        N = n;
        M = m;
        R = r;
        C = c;
        
        // initializing
        int dCount, lCount, rCount, uCount;
        dCount = 0;
        lCount = 0;
        rCount = 0;
        uCount = 0;
        
        int curRow = x;
        int curCol = y;
        int distance = 0;
        
        // horizontal
        if (x-r < 0) {
            dCount = r-x;
        } else {
            uCount = x-r;
        }
        
        if (y-c < 0) {
            rCount = c-y;
        } else {
            lCount = y-c;
        }
        
        distance = dCount + lCount + rCount + uCount;
        if ((k-distance < 0)||((k-distance)%2!=0)) {
            return "impossible";
        }
        
        int leftover = k-distance;
        int check = leftover / 2;
        
        for (int i=0; i<dCount; i++) {
            sb.append('d');
            curRow++;
        }
        
        while (rangeCheck(curRow, curCol, 'd') && leftover > 0) {
            sb.append('d');
            uCount++;
            curRow++;
            leftover -= 2;
        }
        
        for (int i=0; i<lCount; i++) {
            sb.append('l');
            curCol--;
        }
        
        while (rangeCheck(curRow, curCol, 'l') && leftover > 0) {
            sb.append('l');
            rCount++;
            curCol--;
            leftover -= 2;
        }
        
        while (rangeCheck(curRow, curCol, 'r') && leftover > 0) {
            sb.append('r');
            sb.append('l');
            leftover -= 2;
        }
        
        for (int i=0; i<rCount; i++) {
            sb.append('r');
        }
        
        while (rangeCheck(curRow, curCol, 'u') && leftover > 0) {
            sb.append('u');
            sb.append('d');
            leftover -= 2;
        }
        
        for (int i=0; i<uCount; i++) {
            sb.append('u');
        }
        
        
        return sb.toString();
    }
    
    public static boolean rangeCheck(int row, int col, char dir) {
        
        switch (dir) {
            case 'd':
                return (row >= 1 && row < N && col >= 1 && col <= M);
            case 'l':
                return (row >= 1 && row <= N && col > 1 && col <= M);
            case 'r':
                return (row >= 1 && row <= N && col >= 1 && col < M);
            case 'u':
                return (row > 1 && row <= N && col >= 1 && col <= M);
        }
        
        return false;
    }
    
}