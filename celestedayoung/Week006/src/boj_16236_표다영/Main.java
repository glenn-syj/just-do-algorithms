import java.util.*;

class Fish {
    int x;
    int y;
    int dist;

    public Fish(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {

    static int n;
    static int[][] board;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        board = new int[n][n];
        Queue<Fish> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = scan.nextInt();
                if(board[i][j] == 9){
                    q.add(new Fish(i, j, 0));
                    board[i][j] = 0;
                }
            }
        }

        int eat = 0;
        int time = 0;
        int age = 2;

        while(true){
            LinkedList<Fish> fish = new LinkedList<>();
            int[][] dist = new int[n][n];

            while (!q.isEmpty()) {
                Fish current = q.poll();

                for(int i=0; i<4; i++){
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny]==0 && board[nx][ny] <= age){
                        dist[nx][ny] = dist[current.x][current.y] + 1;
                        q.add(new Fish(nx, ny, dist[nx][ny]));
                        if(1 <= board[nx][ny] && board[nx][ny] <= 6 && board[nx][ny] < age) {
                            fish.add(new Fish(nx, ny, dist[nx][ny]));
                        }
                    }
                }
            }

            if(fish.size() == 0){
                System.out.println(time);
                return;
            }

            Fish currentFish = fish.get(0);
            for(int i = 1; i < fish.size(); i++){
                if(currentFish.dist > fish.get(i).dist) {
                    currentFish = fish.get(i);
                }
                else if(currentFish.dist == fish.get(i).dist) {
                    if(currentFish.x > fish.get(i).x) currentFish = fish.get(i);
                    else if(currentFish.x == fish.get(i).x){
                        if(currentFish.y > fish.get(i).y) currentFish = fish.get(i);
                    }
                }
            }

            time += currentFish.dist;
            eat++;
            board[currentFish.x][currentFish.y] = 0;
            
            if(eat == age) {
                age++;
                eat = 0;
            }
            q.add(new Fish(currentFish.x, currentFish.y, 0));
        }
    }
}
