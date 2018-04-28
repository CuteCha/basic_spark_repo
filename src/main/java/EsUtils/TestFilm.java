package EsUtils;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by cxq on 2018/4/26.
 */
public class TestCon {
    private static String host="10.231.55.250";
    private static int port=9300;
    public static final String CLUSTER_NAME="cxq-application";

    private static Settings clusterSettings = Settings.builder()
                                                    .put("cluster.name", CLUSTER_NAME)
                                                    .put("client.transport.sniff", true)
                                                    .build();

    public static void main(String[] args) throws Exception{
        @SuppressWarnings({ "resource", "unchecked" })
        TransportClient client = new PreBuiltTransportClient(clusterSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
        System.out.println(client);
        GetResponse response=client.prepareGet("blog", "article", "1").get();
        System.out.println(response.getSourceAsString());
        client.close();
    }
}
