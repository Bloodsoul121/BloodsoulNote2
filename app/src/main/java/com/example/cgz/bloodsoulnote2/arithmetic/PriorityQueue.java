package com.example.cgz.bloodsoulnote2.arithmetic;

/**
 * Created by cgz on 18-4-19.
 */

public class PriorityQueue {

//    public  Segment [] pq = new Segment[40];
//    //pq 为线段类的一个对象数组
//    private int N = 0;
//    //队列元素个数
//
//    public PriorityQueue(int maxN){
//        //初始化队列
//        for(int i= 0; i<maxN+1; i++){
//            pq[i] = new Segment() ;
//        }
//    }
//
//    public boolean isEmpty(){
//        return N==0 ;
//    }
//
//    public int size(){
//        return N;
//    }
//
//    public void insert(int v,int i,int j){
//        //插入队列元素，并按递减排序
//        pq[++N].weight = v;
//        pq[N].x = i ;
//        pq[N].y = j ;
//        swim(N);  //上浮操作
//    }
//
//    public int delMin(){
//        //由于要为最小生成树服务，这里删除的是最小值
//        int min = pq[1].weight;
//        exch(1, N--);
//        pq[N+1].weight = 9999;
//        //防止末尾元素为空，随便指定一个较大值
//        sink(1);
//        return min;
//    }
//    //小的游上去
//    private  void swim(int k){
//        while(k > 1 && pq[k/2].weight > pq[k].weight){
//            exch(k/2,k);
//            k = k/2;
//        }
//    }
//    //大的沉下去
//    private void sink(int k){
//        while(2*k <= N){
//            int j = 2*k;
//            if(j<N && pq[j].weight > pq[j+1].weight)
//                j++;
//            if(pq[k].weight < pq[j].weight)
//                break;
//            exch(k, j);
//            k = j ;
//        }
//    }
//
//    private void exch(int i,int j){
//        //线段对象交换，开始时我只交换线段的权重，后来修正
//        Segment temp = pq[i] ;
//        pq[i] = pq[j] ;
//        pq[j] = temp;
//    }

}
