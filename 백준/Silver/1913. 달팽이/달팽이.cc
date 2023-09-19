#include <iostream>

using namespace std;

int main()
{
    
    
    int N, num;
    cin >> N >> num;
    
    int ** arr = new int*[N];
    for(int i =0; i<N ; i++)
    {
        arr[i] = new int[N];
    }
    
    int count = N*N;
    arr[0][0] = count;
    
    int dr[] = {1, 0, -1, 0};
    int dc[] = {0, 1, 0, -1};
        
    int row = 0;
    int col = 0;
    int dir = 0;
    while(count !=1)
    {
        int nrow = row+dr[dir];
        int ncol = col+dc[dir];
        
        if(nrow<0 || ncol<0 || nrow==N || ncol ==N || arr[nrow][ncol] != 0)
        {
            dir++;
            dir = dir%4;
            continue;
        }
    
        row = nrow;
        col = ncol;
        arr[row][col] = --count;
    }
    
    
    for(int i =0; i<N; i++)
    {
         for(int j =0; j<N; j++)
        {
            printf("%d ",arr[i][j]);
            
            if(arr[i][j] == num)
            {
                row= i+1;
                col= j+1;
            }
        }
        printf("\n");
    }   
    printf("%d %d",row,col);
    

    return 0;
}
