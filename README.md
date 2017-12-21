# Kafka Streams Custom Seder

Just an example of how to use a custom Seder in Kafka Streams Applications.


## Install

1. Clone the repository.
2. Open the project with your favorite IDE (In my case IntelliJ).
3. The IDE will read the dependencies from ``pom.xml`` and will prepare all for you.

## Run

The whole code is in ``src/main/java/myapps``. There you can find several files:

* ``JSONEnricher.java`` is the main Java class. It contains all the logic of the Kafka Stream App coded.
* ``Person.java`` it is the object that we want to represent in our Kafka Topic.
* ``PersonSerializer.java`` it is the class which will serialize our Person object.
* ``PersonDeserializar.java`` it is the class which will deserialize our Person object.
* ``PersonSeder.java`` it is the class which wraps the serializer and the deserializer to be used on ``JSONEnricher.java``.

In order to run this code, you have to:

1. Start Zookeeper and Kafka servers.
2. Create two topics:
    1. ``streams-plaintext-input`` as input pipe.
    2. ``streams-jsonenricher-output`` as output pipe.
3. Run the Kafka Stream App coded:
    1. Right click on ``JSONEnricher.java`` and ``Run``.
4. Start a producer (e.g. kafka-console-producer) and start writing JSON messages to the ``streams-plaintext-input`` topic, like:
    * ``{ "name" : "David Corral", "age" : 23 }``
    * ``{ "name" : "David Corral", "age" : 23, "location" : "Seville" }``
    * ``{ "name" : "David Corral", "age" : 23, "location" : "Seville", "salary" : 23000 }``


At this point you can see the transformations performs by the Kafka Stream App in the Run Terminal of your IDE. But also you can start a consumer (e.g. kafka-console-consumer) and retrieve messages from the ``streams-jsonenricher-output`` topic. 
    
    