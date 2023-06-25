package sample;
import java.util.*;
import java.util.concurrent.*;
public class makeMaze {
    private int [][]maze;
    private int N;
    public makeMaze(int n) {
        this.N=n;
    }
    public void setMaze(int[][] maze){
        this.maze=maze;
    }
    public void getMaze(){
        for (int i=0;i<N;i++){
            for (int m=0;m<N;m++){
                 System.out.print(maze[i][m]+" ");
                }
            System.out.print("\n");
            }
        }
    }