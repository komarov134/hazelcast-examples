package ru.neron.education.hazelcastusage;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import java.util.Collection;

public class DistributedMultiMap {
    public static void main(String[] args) {
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        MultiMap<String, String> multiMap = h.getMultiMap("my-distributed-multimap");
        multiMap.put("key", "value1");
        multiMap.put("key", "value2");
        multiMap.put("key", "value3");

        Collection<String> values = multiMap.get("key");

        // remove specific key/value pair
        multiMap.remove("key", "value2");
    }
}