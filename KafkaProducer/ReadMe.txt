Notes:

Broker 1 configuration:
broker.id=0
port=9092
host.name=localhost
log.dirs=/tmp/kafka-logs

Broker 2 configuration:
broker.id=1
port=9093
host.name=localhost
log.dirs=/tmp/kafka-logs

#Start zookeeper server
zookeeper-server-start.bat ../../config/zookeeper.properties
#starting kafka server (broker) 1
kafka-server-start.bat ../../config/server.properties
#starting kafka server (broker) 2
kafka-server-start.bat ../../config/server1.properties
#create topic
#Replication factor should not be more than kafka brokers(server instances)
kafka-topics.bat --create     --zookeeper localhost:2181     --replication-factor 2    --partitions 4    --topic test-replication-topic

kafka-topics.bat --describe --topic test-replication-topic --bootstrap-server localhost:9092
kafka-console-producer.bat --topic test-replication-topic --bootstrap-server localhost:9092
kafka-console-consumer.bat --topic test-replication-topic --from-beginning --bootstrap-server localhost:9092

URL:
https://coralogix.com/log-analytics-blog/a-complete-introduction-to-apache-kafka/
