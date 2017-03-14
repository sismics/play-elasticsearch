package helpers.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;

/**
 * ElasticSearch context.
 *
 * @author jtremeaux
 */
public class ES {
    private static TransportClient transportClient;

    private ES() {
    }

    /**
     * Get the Elastic Search transport client.
     *
     * This client is thread safe, one per JVM.
     *
     * @return Transport Client
     */
    synchronized public static TransportClient get() {
        if (transportClient == null) {
            transportClient = ElasticSearchUtil.createTransportClient();
        }
        return transportClient;
    }

    synchronized public static void reset() {
        try {
            if (transportClient != null) {
                transportClient.close();
            }
        } finally {
            transportClient = null;
        }
    }
}
