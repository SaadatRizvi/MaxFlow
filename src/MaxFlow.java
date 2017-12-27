import java.util.LinkedList;

public class MaxFlow {
	private static final int size =7;

	//return true if a path exists
	public static boolean bfs(int[][] graph, int[] parent,final int source,final int sink){
		 LinkedList<Integer> queue = new  LinkedList<Integer>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			int a = queue.poll();
			for(int i=0;i<size;i++){
				if(graph[a][i]> 0){
					queue.add(i);
					parent[i]=a;
					if(i == sink) return true;
				}
			}
		}
		return false;
	}
	
	public static int fordFulkerson(int graph[][],final int source,final int sink){
		int maxFlow =0;
		int[][] residualG = new int[size][size];
		residualG = graph.clone();
        int parent[] = new int[size];
		for(int i=0;i<parent.length;i++){
	          parent[i]=-9999; // Initialise with random negative value;
	          }
				
		while(bfs(residualG,parent,source,sink)){
			int pathFlow= Integer.MAX_VALUE;
			for(int i= sink; i!=source;i=parent[i]){
				int j= parent[i];
				pathFlow = pathFlow < residualG[j][i]? pathFlow: residualG[j][i];
			}
			for(int i= sink; i!=source;i=parent[i]){
				int j= parent[i];
				residualG[j][i]-=pathFlow;
				residualG[i][j]+=pathFlow;
			
			}
			maxFlow+=pathFlow;
		}
		return maxFlow;
	}

	public static void main(String[] args) {
		int graph[][] = new int[][]{ 			
	//     2  0  1  4  3  7  0
		  {0, 5, 0, 6, 0, 0, 0},
          {0, 0, 3, 0, 4, 0, 0},
          {0, 0, 0, 2, 0, 8, 0},
          {0, 0, 0, 0, 0, 6, 0},
          {0, 0, 0, 0, 0, 0, 12},
          {0, 0, 0, 0, 0, 0, 13},
          {0, 0, 0, 0, 0, 0, 0}
          };
                   
//         System.out.println("BFS: "+ bfs(graph,parent,0,6));
//         for(int i=0;i<parent.length;i++){
//        	 System.out.println(i+": "+parent[i]);
//         }
          
          System.out.println("MaxFlow = " + fordFulkerson(graph, 0, 6));
          
          
	}

}
