package com.dannicleo.cielo.desafio2.queue;

import com.dannicleo.cielo.desafio2.entity.Client;
import com.dannicleo.cielo.desafio2.exception.QueueEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyQueue {

    private static int INITIAL = 2; //BUG
    private Client[] queue;
    public int size;
    private int front;
    private int rear;

    public MyQueue() {
        this.queue = new Client[INITIAL];
        this.front = -1;
        this.rear = 0;
        this.size = 0;
    }
    public boolean isFull(){
        return this.size == this.queue.length;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    private void resize() {
        int newCapacity = this.queue.length * 2;
        Client[] newQueue = new Client[newCapacity];

        for (int i = 0; i < this.queue.length; i++) {
            newQueue[i] = this.queue[i];
        }

        int newRear = this.queue.length - 1;
        this.queue = newQueue;
        this.front = 0;
        this.rear = newRear;

        System.out.println("resize");
        System.out.println(this.queue.length);
    }

    public void enqueue(Client client){

        if (isFull()) {
            resize();
        }

        this.queue[this.rear] = client;
        this.rear++;
        this.size++;

        System.out.println(this.queue.length);
        System.out.println(this.size);
//        System.out.println(this.queue);

    }

    public void dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Não há pessoas na fila");
        }

        Client client = queue[front];
        front = (front + 1) % queue.length;
        size--;
    }

    public List<Client> list() {

//      System.out.println(queue[0]);

        if (isEmpty()) {
            throw new QueueEmptyException("Não há pessoas na fila");
        }
        
        List<Client> clientList = new ArrayList<>();

        for (int i = 0; i < this.queue.length; i++) {
            clientList.add(this.queue[i]);
        }

        return clientList;
    }

}
