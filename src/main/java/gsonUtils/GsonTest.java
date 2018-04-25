package gsonUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

/**
 * Created by cxq on 2018/4/25.
 */
public class GsonTest {
    @Test
    public void testCreateJs() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "java_book1");
        jsonObject.addProperty("publishDate", "2012-11-11");
        jsonObject.addProperty("price", 100);
        System.out.println(jsonObject.toString());
    }

    @Test
    public void testParseJsStr() throws Exception {
        Gson gson = new Gson();
        String jsonInString = "{\"userId\":\"1\",\"userName\":\"Yasir\"}";
        User user = gson.fromJson(jsonInString, User.class);
        System.out.println(user.userId + ":" + user.userName);

        System.out.println("=============");
        String jsStr = "{\"userId\":\"1\",\"userName\":\"Yasir\",\"scoreList\":[10.0,9.9]}";
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsStr).getAsJsonObject();
        System.out.println(o.get("userId") + ":" + o.get("userName")+"\t"+o.get("scoreList"));
        System.out.println(o.get("scoreList").getClass().getName());
        System.out.println(o.getClass().getName());
        System.out.println("===================");
        String ret="{\n" +
                "  \"took\" : 44,\n" +
                "  \"timed_out\" : false,\n" +
                "  \"_shards\" : {\n" +
                "    \"total\" : 5,\n" +
                "    \"successful\" : 5,\n" +
                "    \"failed\" : 0\n" +
                "  },\n" +
                "  \"hits\" : {\n" +
                "    \"total\" : 1,\n" +
                "    \"max_score\" : 1.0,\n" +
                "    \"hits\" : [\n" +
                "      {\n" +
                "        \"_index\" : \"blog\",\n" +
                "        \"_type\" : \"article\",\n" +
                "        \"_id\" : \"1\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"title\" : \"hello elasticsearch\",\n" +
                "          \"tags\" : [\n" +
                "            \"elasticsearch\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
//        System.out.println(ret);
        JsonObject retOb = parser.parse(ret).getAsJsonObject();
        System.out.println(retOb.toString());
        System.out.println(retOb.get("_shards").getAsJsonObject().get("total")+"\t"+retOb.get("_shards").getClass());

    }
}

class User {
    int userId;
    String userName;
}
