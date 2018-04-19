package com.example.cgz.bloodsoulnote2.arithmetic;

/**
 * Created by cgz on 18-4-19.
 */

public class UnionFind {

    private int [] id;
    //每个节点组号

    private int [] sz;
    //每个组的大小

    private int count ;
    //联通分量数目

    //初始化
    public UnionFind(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;  //初始情况下每个节点的组号就是该节点的序号
            sz[i] = 1;  //初始时每个组大小都为1
        }
    }

    //返回联通分量数目
    public int count(){
        return count;
    }

    //检查是否联通，如果是，返回true
    public boolean connected(int p,int q){
        return find(q)==find(p);
    }

    //查找组号,压缩路径，使得树更加扁平化，提高了find的效率
    public int find(int p){
        while(p != id[p]){
            //找到它的根节点
            //将p节点的父节点设置为它的爷爷节点
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    //组合Weighted Quick-Union
    public void union(int p,int q) {
        int pID = find(p);
        int qID = find(q);
        //如果在同一组直接返回
        if (pID == qID)
            return;
            //不是就组合,将小树作为大树的子树
        else {
            if (sz[pID] < sz[qID]) {
                id[pID] = qID;
                sz[qID] += sz[pID];
            } else {
                id[qID] = pID;
                sz[pID] += sz[qID];
            }
            count--;     //联通分量数目减少1
        }
    }
}
