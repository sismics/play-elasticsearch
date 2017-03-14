package helpers.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import play.Play;

import java.net.InetAddress;

/**
 * ELASTICSEARCH utilities.
 *
 * @author jtremeaux
 */
public class ElasticSearchUtil {
    public final static int DEFAULT_PORT = 9300;

    /**
     * Create a new Transport Client from Play! parameters.
     *
     * @return New transport Client
     */
    public static TransportClient createTransportClient() {
        try {
            Settings.Builder builder = Settings.builder();
            String clusterName = Play.configuration.getProperty("elasticsearch.clusterName");
            if (clusterName != null) {
                builder.put("cluster.name", clusterName);
            }
            String[] hosts = Play.configuration.getProperty("elasticsearch.host").split(",");
            TransportClient client = new PreBuiltTransportClient(builder.build());
            for (String host : hosts) {
                String[] hostWithPort = host.split(":");
                String hostName = hostWithPort[0];
                int port = DEFAULT_PORT;
                if (hostWithPort.length > 1) {
                    port = Integer.valueOf(hostWithPort[1]);
                }
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostWithPort[0]), port));
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException("Cannot create ElasticSearch transport", e);
        }
    }
}
