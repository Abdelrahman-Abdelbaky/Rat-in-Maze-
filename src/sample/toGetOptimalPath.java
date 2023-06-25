package sample;

public class toGetOptimalPath {
    private int N;
    private int x=0;
    private int y=0;
    private int[][] sol=new int[N][N];
    Boolean Flag=false;
    public  toGetOptimalPath(int sol[][],int N) {
        this.sol=sol;
        this.N=N;
     }
    public void getOpPath(){
        Flag = false;
        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                if (sol[m][i] == 1 ) {
                    if ((m!=N-1&&i!=N-1)&&sol[m + 1][i] == 0 && sol[m][i + 1] == 0) {
                        sol[m][i]=0;
                        Flag = true;
                    }
                    else if(m==N-1||i==N-1&&sol[m][i]==1){
                        if(m==N-1&&i!=N-1&&sol[m][i+1]==0){ sol[m][i]=0;Flag = true;
                        }
                        else if(i==N-1&&m!=N-1&&sol[m+1][i]==0){ sol[m][i]=0;Flag = true;}
                    }

                }
            }
            if (Flag == true) getOpPath();
        }

    }
//            if (!(x==N-1&&y==N-1)){
//            if(sol[x][y]==1){
//                if(sol[x+1][y]==0&&sol[x][y+1]==0){
//                    sol[x][y]=0;
//                    getOpPath(0,0);
//                }else  getOpPath(x+1,y);
//            }else  getOpPath(x++,y++);
//     }


            public int[][] setOpPath () {

                return sol;
            }


        }
