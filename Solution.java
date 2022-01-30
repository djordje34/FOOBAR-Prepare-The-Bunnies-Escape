
import java.util.Queue;
import java.util.LinkedList;
public class Solution {
	public static int solution(int map[][])
    {
	    int[][] temp=duplicate(map);
    	int min=shortestPathBinaryMatrix(map);
    	for(int i=0;i<map.length;i++)
    		for(int j=0;j<map[0].length;j++)
    		{
    	    	if(temp[i][j]==1)
    	    	{
    	    		temp[i][j]=0;
    	    		int val=shortestPathBinaryMatrix(temp);
    	    		if(min>0)
    	    		{
    	    		if(val<min && val>0)
    	    			min=val;
    	    		printer(temp);
    	    		System.out.println(val);
    	    		temp[i][j]=1;
        	    	reset(temp);
    	    		}
    	    		else
    	    		{
    	    			min=val;
        	    		printer(temp);
        	    		temp[i][j]=1;
    	    			reset(temp);
    	    		}
    	    	}
    		}
    	return min;
    }
    public static int shortestPathBinaryMatrix(int[][] grid) {
        
        if(grid[0][0] == 1) return -1;		//simple checker for the beginning of path
        int R = grid.length;
        int C = grid[0].length;
        Queue<Integer> rq = new LinkedList<>();	//setting up BFS
        Queue<Integer> cq = new LinkedList<>();
        int[] dr = {1, -1, 0, 0};		//movable directions ->up,down,left,right
        int[] dc = {0, 0, -1, 1};		//movable directions ->up,down,left,right
 
        rq.offer(0);	//first node
        cq.offer(0);	//first node

        boolean reachedTarget = false;
        int nodesLeft = 1;
        int nodesNext = 0;
        int count = 0;        
        
        while(!rq.isEmpty()){	
            
            int r = rq.poll();
            int c = cq.poll();
            
            if(r == R - 1 && c == C - 1){	//check if destination is reached, R-1 and C-1 destination coordinates, r and c current coordinates from queues
                
                reachedTarget = true;
                break;
                
            }
            
            for(int i = 0; i < 4; i++)
            {		//moving to another available node
                int rr = r + dr[i];
                int cc = c + dc[i];
                if(rr < 0 || cc < 0) continue;
                if(rr >= R || cc >= C) continue;
                if(grid[rr][cc] == 1) continue;
                if(grid[rr][cc] == 2) continue;	//if we already moved to the node marked with 2, we cannot move on it again(path can't be shortest if we move 
                									//multiple times to the same node
                rq.offer(rr);				//change x and y
                cq.offer(cc);                //
                grid[rr][cc] = 2;                //marked as visited
                nodesNext++;
                
            }
            
            nodesLeft--;
            if(nodesLeft == 0){
                nodesLeft = nodesNext;
                nodesNext = 0;
                count++;
            }
            
        }
        
        return reachedTarget ? count +1 : -1;

    }
    public static int[][] duplicate(int map[][])
    {
    	int[][] temp=new int[map.length][map[0].length];
    	for(int i=0;i<temp.length;i++)
    		for(int j=0;j<temp[0].length;j++)
    		{
    			if(map[i][j]!=2)
    			temp[i][j]=map[i][j];
    			else
    			if(map[i][j]==2)
    				map[i][j]=0;
    		}
    	return temp;
    }
    public static void reset(int[][] arr)
    {
    	for(int i=0;i<arr.length;i++)
    	{
    		for(int j=0;j<arr[0].length;j++)
    		{
    			if(arr[i][j]==2)
    			{
    				arr[i][j]=0;
    			}
    		}
    	}
    }
    public static void printer(int[][] arr)
    {
    	for(int i=0;i<arr.length;i++)
    	{
    		System.out.println();
    		for(int j=0;j<arr[0].length;j++)
    		{
    			System.out.print(" " + arr[i][j]+" ");
    		}
    		}
    	System.out.println();
    }
}