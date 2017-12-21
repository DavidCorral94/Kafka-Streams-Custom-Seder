package myapps;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PersonSeder implements Serde<Person> {
    private PersonSerializer serializer = new PersonSerializer();
    private PersonDeserializer deserializer = new PersonDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

    @Override
    public Serializer<Person> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<Person> deserializer() {
        return deserializer;
    }
}