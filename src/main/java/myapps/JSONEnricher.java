package myapps;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class JSONEnricher {

    public static void main(String[] args) throws Exception {
        System.out.println("** STARTING JSONEnricher STREAM APP **");
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-enricher");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        // Here we set the Seder for the values that we are going to process.
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSeder.class);

        final StreamsBuilder builder = new StreamsBuilder();

        // Consume JSON and enriches it
        KStream<String, Person> source = builder.stream("streams-plaintext-input");
        KStream<String, Person> output = source.map((key, person) -> {
            // We will receive a Person Object and wi will set it's location to 'Cadiz'.
            System.out.println("**BEFORE- PERSON: " + person.toString());
            person.setLocation("Cadiz");
            System.out.println("**AFTER- PERSON: " + person.toString());
            // We return the same pair but the location of the object 'person' has been modified.
            return KeyValue.pair(key, person);
        });
        output.to("streams-jsonenricher-output");

        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);

        // Attach shutdown handler to catch control-c
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}