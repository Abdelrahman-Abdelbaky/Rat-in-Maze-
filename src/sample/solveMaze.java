package sample;

import java.util.concurrent.Semaphore;

public class solveMaze implements  Runnable {
    private int N;
    private int x;
    private int y;
    private int maze[][];
    private int sol[][];
    private Semaphore sem;
    {
        sem = new Semaphore(1);
    }


    public solveMaze(int n, int[][] maze, int[][] sol, int x, int y) {
        this.N = n;
        this.maze = maze;
        this.sol = sol;
        this.x = x;
        this.y = y;
    }

    boolean Safe(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1) {
            return true;
        }
        else
            return false;
    }

    boolean solveMazeBYSearch(int x, int y) throws InterruptedException {
        if (x == N - 1 && y == N - 1 && maze[x][y] == 1) {  // the rat now can out from maze
            sem.acquire();
            sol[x][y] = 1;
            sem.release();
            return true;
        }
        if (Safe(x, y) == true) {
            if (sol[x][y] == 1) return false;//tha node one use it before
            sem.acquire();
            sol[x][y] = 1;
            sem.release();
            if (Safe(x + 1, y) == true && (Safe(x, y + 1) == true)) {
                solveMaze solveMaze = new solveMaze(N, maze, sol, x, y + 1);
                Thread t1 = new Thread(solveMaze);
                t1.start();
//                t1.join();
            }
            if (solveMazeBYSearch(x + 1, y)) return true;
            if (solveMazeBYSearch(x, y + 1)) return true;
            if (Safe(x + 1, y) == false && (Safe(x, y + 1) == false)){
                return false;
            }
            return false;  //now the rat not found any path
        }
        return false;
    }

    @Override
    public void run() {
        try {
            if (solveMazeBYSearch(x, y) == false) {
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }


    public int[][] getSol() {
        System.out.print("" + "the all paths the rat goes to arrive to end path" + "\n--------------------\n");
        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                System.out.print(sol[i][m] + " ");
            }
            System.out.print("\n");
        }
        int x[][]=sol;

        if (sol[N-1][N-1]==0){
            System.out.print("So sad that you designed a maze so poorly\n");}
        else{
            System.out.print("erorr\n");

        }
        return x;
    }

    public int[][] getSolop(){
        toGetOptimalPath s=new toGetOptimalPath(sol,N);
        s.getOpPath();
       int a[][]=s.setOpPath();
        return a;
    }
    }
