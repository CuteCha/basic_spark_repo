package EsUtils;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by cxq on 2018/4/23.
 */
public class EsCon {
    private static String host = "10.231.55.250";
    private static int port = 9300;

    public static void main(String[] args) throws Exception {
        @SuppressWarnings({"resource", "unchecked"})
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        System.out.println(client);
        GetResponse response=client.prepareGet("blog", "article", "1").get();
        System.out.println(response.getSourceAsString());
        client.close();
    }


}
