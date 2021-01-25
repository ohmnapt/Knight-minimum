package knightminimum;

import java.util.*;

// Java program to find minimum steps to reach to  
// specific cell in minimum moves by Knight 
public class KnightMinimum {

    static Scanner scan = new Scanner(System.in);
    private static int[][] board;
    private static int n;

    // Class for storing path's data
    static class position {

        int x, y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Class for storing a cell's data 
    static class cell {

        int x, y;
        int dis;
        ArrayList<position> path;

        public cell(int x, int y, int dis, ArrayList path) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.path = path;
        }
    }

    // Utility method returns true if (x, y) lies 
    // inside Board 
    static boolean isInside(int x, int y, int N) {
        if (x >= 1 && x <= N && y >= 1 && y <= N) {
            return true;
        }
        return false;
    }

    // Method returns minimum step 
    // to reach target position 
    static ArrayList<position> KnightToFlagPathfinder(int knightPos[], int targetPos[], int N) {
        // x and y direction, where a knight can move 
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        // queue for storing states of knight in board 
        ArrayList<position> aListPt = new ArrayList<>();
        Vector<cell> q = new Vector<>();

        //pt.add(new position(knightPos[0],knightPos[1]));
        // push starting position of knight with 0 distance 
        q.add(new cell(knightPos[0], knightPos[1], 0, aListPt));

        cell t;
        int x, y;
        boolean visit[][] = new boolean[N + 1][N + 1];

        // make all cell unvisited 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                visit[i][j] = false;
            }
        }

        // visit starting state 
        visit[knightPos[0]][knightPos[1]] = true;

        // loop untill we have one element in queue 
        while (!q.isEmpty()) {
            t = q.firstElement();
            q.remove(0);

            aListPt = t.path; //เก็บ path เดิม
            // if current cell is equal to target cell, 
            // return its distance 
            if (t.x == targetPos[0] && t.y == targetPos[1]) //return t.dis; 
            {
                return aListPt;
            }

            // loop for all reachable states 
            for (int i = 0; i < 8; i++) {
                x = t.x + dx[i];
                y = t.y + dy[i];

                // If reachable state is not yet visited and 
                // inside board, push that state into queue 
                if (isInside(x, y, N) && !visit[x][y]) {
                    visit[x][y] = true;
                    ArrayList<position> aListPt_clone = (ArrayList<position>) aListPt.clone(); //Copy aListPt_clone = aListPt
                    aListPt_clone.add(new position(x, y)); //เพิ่ม path ใหม่เข้าไป
                    q.add(new cell(x, y, t.dis + 1, aListPt_clone));
                }
            }
        }
        //return Integer.MAX_VALUE;
        aListPt.clear();
        aListPt.add(new position(-1, -1));
        return aListPt;
    }

    static void ShowSolution(int board[][], int intMode) {

        // พิมพ์รายละเอียด
        if (intMode == 0) {
            System.out.print("Initial \n\n");
        } else if (intMode == 1) {
            System.out.print("Initial \n\n");
        } else if (intMode == n) {          
        } else {
            System.out.print("No solution !! \n");
        }
        //แสดงผลลัพท์
        for (int r = 0; r < n + 1; r++) {
            for (int c = 0; c < n + 1; c++) {
                if (r == 0) {
                    if (c == 0) {
                        System.out.printf("%s  ", "  ");

                    } else if (c == 1) {
                        System.out.printf("%3d   ", c);

                    } else if (c < n) {
                        System.out.printf("%3d   ", c);

                    } else {
                        System.out.printf("%3d   \n", c);
                        for (int i = 0; i < 6 * n + 4; i++) {
                            if (i < 3) {
                                System.out.printf(" ");
                            } else {
                                System.out.printf("-");
                            }
                        }

                    }
                } else {
                    if (c == 0) {
                        System.out.printf("%2d | ", r);
                    } else {
                        if (board[r - 1][c - 1] == 1||board[r - 1][c - 1] == 2||board[r - 1][c - 1] == 3) {
                            if (c < n) {
                                if(board[r - 1][c - 1] == 1)
                                    System.out.printf("%s | ", " K ");
                                if(board[r - 1][c - 1] == 2)
                                    System.out.printf("%s | ", " F ");
                                if(board[r - 1][c - 1] == 3)
                                    System.out.printf("%s | ", " K*");
                            } else {             
                                if(board[r - 1][c - 1] == 1)
                                    System.out.printf("%s | \n", " K ");
                                if(board[r - 1][c - 1] == 2)
                                    System.out.printf("%s | \n", " F ");
                                if(board[r - 1][c - 1] == 3)
                                    System.out.printf("%s | \n", " K*");
                                for (int i = 0; i < 6 * n + 4; i++) {
                                    if (i < 3) {
                                        System.out.printf(" ");
                                    } else {
                                        System.out.printf("-");
                                    }
                                }
                            }

                        } else if (c < n) {
                            System.out.printf("%s | ", " - ");

                        }  else {
                            System.out.printf("%s | \n", " - ");
                            for (int i = 0; i < 6 * n + 4; i++) {
                                if (i < 3) {
                                    System.out.printf(" ");
                                } else {
                                    System.out.printf("-");
                                }
                            }
                        }
                    }
                }

            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }

    static int GetAnInteger() {
        while (true) {
            try {

                int input = scan.nextInt();
                if (input < 5) {
                    throw new InvalidNumberException("Invalid Number! Try Again! : ");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("That’s not an integer! Try again! : ");
            } catch (InvalidNumberException e) {
                System.out.printf(e.getMessage());
            }

        }
    }

    static int GetAnIndex(int n) {
        while (true) {
            try {
                int input = scan.nextInt();
                if (input <= 0 || input > n) {
                    throw new InvalidNumberException("That's not in range(1.." + String.valueOf(n) + "). Try again: ");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("That’s not an integer! Try again! : ");
            } catch (InvalidNumberException e) {
                System.out.printf(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
// Enter N=?
        System.out.print("Enter Baord Size (at least 5) = ");
        n = GetAnInteger();
        int N = n;
        // Create board array.
        board = new int[n][n];
        // Display board layout.
        ShowSolution(board, 0);
        ArrayList<position> aListAns;

        //p = new QueenPosition[n];
        String strContinue;//ควบคุมให้วนจนกว่าจะได้ Solution
        do {
            System.out.print("Enter knight position (row) = ");
            int intRow1K = GetAnIndex(n);
            System.out.print("Enter knight position (col) = ");
            int intCol1K = GetAnIndex(n);
            System.out.println();
            System.out.print("Enter flag position (row) = ");
            int intRow1F = GetAnIndex(n);
            System.out.print("Enter flag position (col) = ");
            int intCol1F = GetAnIndex(n);
            System.out.println();

            int knightPos[] = {intRow1K, intCol1K};
            int targetPos[] = {intRow1F, intCol1F};
            // Clear board.           
            board = new int[n][n];
            board[intRow1K - 1][intCol1K - 1] = 1;
            board[intRow1F - 1][intCol1F - 1] = 2;
            // Display first queen.
            ShowSolution(board, 1);
            //clear board
            board[intRow1K - 1][intCol1K - 1] = 0;
            board[intRow1F - 1][intCol1F - 1] = 0;

            //System.out.format("(%d,%d)\n", knightPos[0], knightPos[1]);
            aListAns = KnightToFlagPathfinder(knightPos, targetPos, N);
            if(aListAns.size()!=0)
                System.out.printf("Minimum move to get the flag = %d\n", aListAns.size());
            for (int i = 0; i < aListAns.size(); i++) {
                //System.out.format("(%d,%d)\n", aListAns.get(i).x, aListAns.get(i).y);
                System.out.printf("Move %d\n\n", i+1);             
                if (i+1 == aListAns.size()) {
                    board = new int[n][n];                 
                    board[aListAns.get(i).x - 1][aListAns.get(i).y - 1] = 3;
                } else {
                    board = new int[n][n];
                    board[aListAns.get(i).x - 1][aListAns.get(i).y - 1] = 1;
                    board[intRow1F - 1][intCol1F - 1] = 2;
                }
                ShowSolution(board, n);
            }
            if(aListAns.size()==0){
                board = new int[n][n];
                ShowSolution(board, -1);
            }
            //ถามว่าต้องการให้วนหรือไม่
            System.out.print("Enter y to continue with the same N = ");
            strContinue = scan.next();
        } while (strContinue.equals("y"));

    }

}

class InvalidNumberException extends Exception {

    public InvalidNumberException(String msg) {
        super(msg);
    }

}
