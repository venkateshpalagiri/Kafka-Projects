============================== Kafka CLI Commands ==============================

>To start zookeeper server

	C:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

>To start kafka server

	C:\kafka> .\bin\windows\kafka-server-start.bat .\config\server.properties

>To create partitions

	C:\kafka> .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic test-topic --partitions 3 --replication-factor 1

>To list all topics

	C:\kafka> .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list
	
>To view description

	C:\kafka> .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic test-topic

>To run producer

	C:\kafka> .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
	
>To run consumer to read form beginning

	C:\kafka> .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
	