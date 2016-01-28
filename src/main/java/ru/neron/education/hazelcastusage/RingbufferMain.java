package ru.neron.education.hazelcastusage;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.ringbuffer.Ringbuffer;

public class RingbufferMain {

    public static void main(String[] args){
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        final Ringbuffer<Integer> rb = hz.getRingbuffer("rb");

        new MyThread(rb).start();

        for(int k=0;k<100;k++){
            rb.add(k);
        }
    }

    private static class MyThread extends Thread {
        private final Ringbuffer<Integer> rb;

        public MyThread(Ringbuffer<Integer> rb) {
            this.rb = rb;
        }

        @Override
        public void run(){
            try {
                long seq = -1;
                for (; seq == -1; seq = rb.tailSequence()) System.out.println("wait");
                for (; ; ) {
                    System.out.println(rb.readOne(seq));
                    seq++;
                }
            }catch (InterruptedException e){
            }
        }
    }
}