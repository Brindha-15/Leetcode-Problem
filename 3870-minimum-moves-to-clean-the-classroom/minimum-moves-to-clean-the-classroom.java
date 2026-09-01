import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        int litterCount = 0;

        // Give each litter cell a bit number
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        // visited[row][col][mask][energy]
        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        Queue<int[]> queue = new LinkedList<>();

        // row, col, collectedMask, remainingEnergy, moves
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int en = cur[3];
            int moves = cur[4];

            // All litter collected
            if (mask == allCollected) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Need 1 energy to make a move
                if (en == 0) {
                    continue;
                }

                int newEnergy = en - 1;
                int newMask = mask;

                // Collect litter
                if (classroom[nr].charAt(nc) == 'L') {
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }

                // Reset energy
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nr][nc][newMask][newEnergy]) {
                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.offer(new int[]{
                        nr, nc, newMask, newEnergy, moves + 1
                    });
                }
            }
        }

        return -1;
    }
}