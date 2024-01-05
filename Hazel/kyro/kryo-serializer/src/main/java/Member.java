import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.FileNotFoundException;
import java.util.Map;

public class Member {

    public static void main(String[] args) throws FileNotFoundException {
        // Hazelcast örneğini başlat
        Config config = new ClasspathXmlConfig("hazelcast.xml");
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);


        // Haritayı al
        IMap<String, Person> hazelcastMap = hazelcastInstance.getMap("map1");
        IMap<String, Person> hazelcastMap1 = hazelcastInstance.getMap("map2");


        // Dummy değerlerle haritayı doldur
        for (int i = 0; i < 10000; i++) {
            String key = "Key" + i;
            Person value = new Person("nadire" + i);

            hazelcastMap.put(key, value);
            hazelcastMap1.put(key,value);
        }


        // Dummy değerleri oku
        for (int i = 0; i < 10000; i++) {
            String key = "Key" + i;
            Person retrievedPerson = hazelcastMap.get(key);
            hazelcastMap1.get(key);

            System.out.println("Retrieved Person: " + retrievedPerson);
        }

        // Hazelcast örneğini kapat

    }
}
