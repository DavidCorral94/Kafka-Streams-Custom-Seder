package myapps;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Map;

public class PersonSerializer implements Closeable, AutoCloseable, Serializer<Person> {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    static private Gson gson = new Gson();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, Person person) {
        // Transform the Person object to String
        String line = gson.toJson(person);
        // Return the bytes from the String 'line'
        return line.getBytes(CHARSET);
    }

    @Override
    public void close() {

    }
}
