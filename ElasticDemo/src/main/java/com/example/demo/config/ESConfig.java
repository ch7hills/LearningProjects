/*
 * package com.example.demo.config;
 * 
 * import java.net.InetAddress; import java.net.InetSocketAddress;
 * 
 * import org.elasticsearch.client.transport.TransportClient; import
 * org.elasticsearch.common.settings.Settings; import
 * org.elasticsearch.common.transport.InetSocketTransportAddress; import
 * org.elasticsearch.transport.client.PreBuiltTransportClient; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Configuration public class ESConfig {
 * 
 * @Configuration public class EsConfig {
 * 
 * 
 * @Value("${elasticsearch.clustername}") String clusterName;
 * 
 * @Value("${elasticsearch.host}") String hostName;
 * 
 * 
 * @Bean public TransportClient client() throws Exception { Settings settings =
 * Settings.builder().put("cluster.name", clusterName).build(); TransportClient
 * client = new PreBuiltTransportClient(settings);
 * client.addTransportAddress(new InetSocketTransportAddress(new
 * InetSocketAddress(InetAddress.getByName(hostName), 9300))); return client; }
 * } }
 * 
 * 
 * import java.net.InetAddress;
 * 
 * import org.elasticsearch.client.Client; import
 * org.elasticsearch.client.transport.TransportClient; //import
 * org.elasticsearch.common.settings.Settings; //import
 * org.elasticsearch.common.transport.InetSocketTransportAddress; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.elasticsearch.core.ElasticsearchOperations; import
 * org.springframework.data.elasticsearch.core.ElasticsearchTemplate; import
 * org.springframework.data.elasticsearch.repository.config.
 * EnableElasticsearchRepositories;
 * 
 * @Configuration
 * 
 * @EnableElasticsearchRepositories(basePackages = "com.mkyong.book.repository")
 * public class ESConfig {
 * 
 * @Value("${elasticsearch.host}") private String EsHost;
 * 
 * @Value("${elasticsearch.port}") private int EsPort;
 * 
 * @Value("${elasticsearch.clustername}") private String EsClusterName;
 * 
 * @Bean public Client client() throws Exception {
 * 
 * Settings esSettings = Settings.settingsBuilder() .put("cluster.name",
 * EsClusterName) .build();
 * 
 * //https://www.elastic.co/guide/en/elasticsearch/guide/current/
 * _transport_client_versus_node_client.html return TransportClient.builder()
 * .settings(esSettings) .build() .addTransportAddress( new
 * InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort)); }
 * 
 * @Bean public ElasticsearchOperations elasticsearchTemplate() throws Exception
 * { return new ElasticsearchTemplate(client()); }
 * 
 * //Embedded Elasticsearch Server
 * 
 * @Bean public ElasticsearchOperations elasticsearchTemplate() { return new
 * ElasticsearchTemplate(nodeBuilder().local(true).node().client()); }
 * 
 * }
 */