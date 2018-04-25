package EsUtils;

import com.google.gson.JsonObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by cxq on 2018/4/23.
 */
public class EsOperation {
    private static String host="10.231.55.250";
    private static int port=9300;

    private TransportClient client=null;

    @SuppressWarnings({ "resource", "unchecked" })
    @Before
    public void getClient()throws Exception{
        Settings clusterSettings = Settings.builder()
                .put("cluster.name", "cxq-application")
                .put("client.transport.sniff", true)
                .build();
        client = new PreBuiltTransportClient(clusterSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
    }

    @After
    public void close(){
        if(client!=null){
            client.close();
        }
    }

    /**
     * 创建索引 添加文档
     */
    @Test
    public void testIndex()throws Exception{
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name", "java_book1");
        jsonObject.addProperty("publishDate", "2012-11-11");
        jsonObject.addProperty("price", 100);

        IndexResponse response=client.prepareIndex("book", "java", "1")
                .setSource(jsonObject.toString(), XContentType.JSON).get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId());
        System.out.println("当前实例状态："+response.status());
    }

    /**
     * 根据id获取文档
     */
    @Test
    public void testGet()throws Exception{
        GetResponse response=client.prepareGet("book", "java", "1").get();
        System.out.println(response.getSourceAsString());
    }

    /**
     * 根据id修改文档
     */
    @Test
    public void testUpdate()throws Exception{
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name", "java_book2");
        jsonObject.addProperty("publishDate", "2012-11-12");
        jsonObject.addProperty("price", 102);

        UpdateResponse response=client.prepareUpdate("book", "java", "1").setDoc(jsonObject.toString(), XContentType.JSON).get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId());
        System.out.println("当前实例状态："+response.status());
    }

    /**
     * 根据id删除文档
     */
    @Test
    public void testDelete()throws Exception{
        DeleteResponse response=client.prepareDelete("book", "java", "1").get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId());
        System.out.println("当前实例状态："+response.status());
    }


    public void run(){
        System.out.println("hello world");
    }
}
