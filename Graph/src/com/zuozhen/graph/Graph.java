package com.zuozhen.graph;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
无向图
 */
public class Graph {
    private ArrayList<String> vertexList;//顶点集合
    private int[][] edges;//邻接矩阵
    private int numOfEdges;//表示边的数目
    //用于DFS的标记数组
    private boolean isVisited[];

    public static void main(String[] args) {
        int n = 5;//节点数
        String[] vertexVal = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        //添加点
        for (String val : vertexVal) {
            graph.insertVertex(val);
        }
        //添加边
        //A-B,A-C,B-C,B-D,B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.bfs();

    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
        numOfEdges = 0;
    }

    /**
     * 返回第一个邻接节点
     *
     * @param index
     * @return index or -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * dfs（depth first search）
     */
    public void dfs(boolean[] isVisited, int i) {
        //访问节点
        System.out.println(getValueByIndex(i));
        //将该节点标志为已访问
        isVisited[i] = true;
        //查找结点的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            //邻接节点存在并且未被访问
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //已经被访问
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 重载dfs
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * bfs(board first search)
     *
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited, int i) {
        int u;//队列头结点
        int w;//邻接节点
        LinkedList queue = new LinkedList();
        //访问当前节点
        System.out.println(getValueByIndex(i));
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = (int) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(w));
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //some methods
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumofEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
