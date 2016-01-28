package ru.neron.education.hazelcastusage;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IFunction;

public class AtomicLongMain {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IAtomicLong counter = hz.getAtomicLong("counter");
        counter.addAndGet(3); // value is now 3
        counter.alter(new MultiplyByTwo());//value is now 6
        System.out.println("counter: "+counter.get());
    }

    public static class MultiplyByTwo implements IFunction<Long,Long> {
        @Override
        public Long apply(Long input) {
            return input*2;
        }
    }
}
