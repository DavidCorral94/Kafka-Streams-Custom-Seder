package myapps;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Map;

public class PersonDeserializer implements Closeable, AutoCloseable, Deserializer<Person> {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    static private Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public Person deserialize(String topic, byte[] bytes) {
        try {
            // Transform the bytes to String
            String person = new String(bytes, CHARSET);
            // Return the Person object created from the String 'person'
            return gson.fromJson(person, Person.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading bytes", e);
        }
    }

    @Override
    public void close() {

    }
}
