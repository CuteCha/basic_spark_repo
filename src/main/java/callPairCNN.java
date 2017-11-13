import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import javafx.util.Pair;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;


/**
 * Created by cxq on 2017/9/4.
 */
public class callPairCNN {

    public static void main(String[] args) {


        String zz = "{\"question\": [\"资治通鉴\", \"的\", \"作者\", \"是\", \"谁\"], \"evidences\": [[\"资治通鉴\", \"：\", \"e\", \" \", \"M\", \"irr\", \"or\", \" \", \"for\", \" \", \"A\", \"id\", \"作品名称\", \"是\", \"资治\", \" \", \"in\", \" \", \"G\", \"over\", \"nme\", \"nt\", \",\", \" \", \"作品\", \"别名\", \"是\", \"通鉴\", \",\", \" \", \"创作\", \"年代\", \"是\", \"北宋\", \",\", \" \", \"文学体裁\", \"是\", \"编年体\", \"史书\", \",\", \" \", \"作者\", \"是\", \"司马光\", \"主编\"], [\"资治通鉴\", \"纲目\", \"：\", \"书名\", \"是\", \"资治通鉴\", \"书名\", \"纲目\", \",\", \" \", \"作者\", \"是\", \"朱熹\", \",\", \" \", \"类别\", \"是\", \"史学\", \"巨著\"], [\"沉重\", \"的\", \"投影\", \"：\", \"品读\", \"《\", \"资治通鉴\", \"》\", \"：\", \"书名\", \"是\", \"沉重\", \"的\", \"投影\", \"：\", \"品读\", \"书名\", \"是\", \"沉重\", \"的\", \"投影\", \"：\", \"品读\", \"《\", \"资治通鉴\", \"》\", \",\", \" \", \"作者\", \"是\", \"苏焕镛\", \",\", \" \", \"ISBN\", \"是\", \"9787\", \"53\", \"263\", \"19\", \"02\", \",\", \" \", \"出版社\", \"是\", \"上海辞书出版社\", \",\", \" \", \"出版\", \"时间\", \"是\", \"2011年\", \"3月1日\", \",\", \" \", \"开本\", \"是\", \"16\"], [\"这样\", \"读\", \"资治通鉴\", \"·\", \"帝国\", \"落日\", \"：\", \"从\", \"汉明帝\", \"到\", \"汉献帝\", \"：\", \"10年\", \"河南\", \"文艺\", \"《\", \"这\", \"出版社\", \";\", \"出版\", \"的\", \"图书\", \"，\", \"作者\", \"是\", \"锐圆\"], [\"资治通鉴\", \" \", \"（\", \"陈纪\", \"）\", \"：\", \"书名\", \"是\", \"资治通鉴\", \" \", \"书名\", \"(\", \"陈纪\", \")\", \",\", \" \", \"作者\", \"是\", \"司马光\", \",\", \" \", \"类别\", \"是\", \"中国史\", \",\", \" \", \"版权\", \"方是\", \"公共\", \"版权\"], [\"资治通鉴\", \" \", \"（\", \"后\", \"周纪\", \" \", \"）\", \"：\", \"书名\", \"是\", \"资治通鉴\", \" \", \"(\", \"后\", \"周纪\", \"书名\", \"是\", \"资治通鉴\", \" \", \"(\", \"后\", \"周纪\", \" \", \")\", \",\", \" \", \"作者\", \"是\", \"司马光\", \",\", \" \", \"类别\", \"是\", \"中国史\", \",\", \" \", \"版权\", \"方是\", \"公共\", \"版权\"], [\"张居正\", \"讲评\", \"资治通鉴\", \"：\", \"书名\", \"是\", \"张居正\", \"讲评\", \"书名\", \"是\", \"张居正\", \"讲评\", \"资治通鉴\", \",\", \" \", \"作者\", \"是\", \"张居正\", \",\", \" \", \"ISBN\", \"是\", \"9787\", \"561\", \"34\", \"93\", \"59\", \",\", \" \", \"定价\", \"是\", \"29\", \".\", \"80\", \"元\", \",\", \" \", \"出版社\", \"是\", \"陕西师范大学出版社\", \",\", \" \", \"出版\", \"时间\", \"是\", \"2010年\", \"7月1日\", \",\", \" \", \"开本\", \"是\", \"16\", \"开\"], [\"资治通鉴\", \"/\", \"典藏\", \"：\", \"书名\", \"是\", \"资治通鉴\", \"鉴\", \"书名\", \"/\", \"典藏\", \",\", \" \", \"出版社\", \"是\", \"万卷\", \" \", \"(\", \"2014年\", \"7月1日\", \")\", \",\", \" \", \"ISBN\", \"是\", \"75\", \"47\", \"02\", \"60\", \"28\", \",\", \" \", \"作者\", \"是\", \"(\", \"北宋\", \")\", \"司马光\", \"|\", \"主编\", \":\", \"吴昊\", \"|\", \"译者\", \":\", \"夏华\", \",\", \" \", \"出版日期\", \"是\", \"2014年\", \"7月1日\", \",\", \" \", \"品牌\", \"是\", \"博库\"]]}";
        String xx=java.net.URLEncoder.encode(zz);
        System.out.println(xx);

        for (int i=0; i< 10; i++){
            if ( i>= 5) {
                break;
            }
            System.out.println("for: "+i);
        }

        String[] x= new String[]{"a","b","c","c"};
        Double[] y= new Double[]{32.1,32.0,31.9,31.9};
        List<Pair<String,Double>> xy= new ArrayList<>();

        for(int i=0;i<x.length;i++){
            Pair<String,Double> tmp = new Pair<>(x[i],y[i]);
            xy.add(tmp);
        }

        Collections.sort(xy,new Comparator<Pair<String,Double>>() {
            //升序排序
            public int compare(Pair<String,Double> o1, Pair<String,Double> o2) {
                if(o2.getValue() > o1.getValue()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        for(Pair<String,Double> t:xy){
            System.out.println(t.getKey()+":"+t.getValue());
        }


        Map<String, Double> unsortMap = new HashMap<>();
        unsortMap.put("z", 10.1);
        unsortMap.put("b", 6.3);
        unsortMap.put("a", 6.4);
        unsortMap.put("c", 6.5);
        unsortMap.put("d", 1.0);
        unsortMap.put("e", 7.0);
        unsortMap.put("y", 8.3);
        unsortMap.put("n", 99.0);
        unsortMap.put("g", 50.9);
        unsortMap.put("m", 2.0);
        unsortMap.put("f", 9.0);

        System.out.println("Original...");
        System.out.println(unsortMap);

        List<Map.Entry<String,Double>> listMap=new ArrayList<>(unsortMap.entrySet());

        Collections.sort(listMap,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o2.getValue() > o1.getValue()){
                    return 1;
                }else {
                    return -1;
                }
//                return (int)(o2.getValue() - o1.getValue());
            }

        });

        for(Map.Entry<String,Double> mapping:listMap){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }

//        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(unsortMap.entrySet());
//
//        java.util.Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
//            int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
//                return e2.getValue() - e1.getValue(); // reverse order sort
//            }
//        });



        Map<String, String> map = new TreeMap<>();
        map.put("d", "ddddd");
        map.put("b", "bbbbb");
        map.put("a", "aaaaa");
        map.put("c", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        for(Map.Entry<String,String> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }

//        String[] s={"1","2","3","5","6"};
//        List<String> listA = Arrays.asList(s);
//        List<String> listB = new ArrayList<String>(listA);
//        listB.add(3,"4");
//        for(String temp:listB){
//            System.out.println(temp);
//        }




//        Gson param= new Gson();
//        Map<String, Object> pMap = new  HashMap<String, Object>();
//        List<String> qustion = new ArrayList<String>();
//        qustion.add("abc");
//        qustion.add("xxz");
//
//        List<Object> envidences = new ArrayList<Object>();
//        List<String> env1 = new ArrayList<String>();
//        env1.add("sss");
//        env1.add("slkj");
//        env1.add("yay");
//        List<String> env2 = new ArrayList<String>();
//        env2.add("2sss");
//        env2.add("2slkj");
//        env2.add("2yay");
//        envidences.add(env1);
//        envidences.add(env2);
//
//        pMap.put("qustion", qustion);
//        pMap.put("envidences", envidences);
//
//        String sParam = param.toJson(pMap);
//        System.out.println(sParam);
//
//
//        Gson gson = new Gson();
////        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
//        Double[] ints2 = gson.fromJson("[1,2,3,4,5]", Double[].class);
//
////        Double[] scores = gson.fromJson("[1.0,2.0,3.0,4.0,5.0]", Double[].class);
//
//        for(double i : ints2){
//            System.out.println(i);
//        }
//
////        for(Double d : scores){
////            System.out.println(d);
////        }
//
//        String json = "{\"id\":11,\"name\":\"zhagnsan\"}";
//        System.out.println(json);
//
//
//
//
//        Gson g = new Gson();
//        Map<String,Object> map = new HashMap<String,Object>();
//        //添加两个普通节点
//        map.put("a","1");
//        map.put("b",2);
//
//        //添加一个list1 => "list1":[{"c":"3","d":4}]
//        Map<String,Object> m5 = new HashMap<String,Object>();
//        List<Object> l = new ArrayList<Object>();
//        m5.put("c","3");
//        m5.put("d",4);
//        l.add(m5);
//        map.put("list1",l);
//
//        //添加一个map1 =>"map1":{"e":"5","f":"6"}
//        Map<String,Object> m2 = new HashMap<String,Object>();
//        m2.put("e","5");
//        m2.put("f","6");
//        map.put("map1",m2);
//
//        //添加一个list2 => "list2":[{"g":"7","h":8},{"g":"9","h":10}]
//        List<Object> l2 = new ArrayList<Object>();
//        Map<String,Object> m3 = new HashMap<String,Object>();
//        Map<String,Object> m4 = new HashMap<String,Object>();
//        m3.put("g","7");
//        m3.put("h",8);
//        m4.put("g","9");
//        m4.put("h",10);
//        l2.add(m3);
//        l2.add(m4);
//        map.put("list2",l2);
//
//
//        //添加一个map2 => "map2":{"i":"5","j":[{"j":"5","k":"5"}],"l":{"j":"5","k":"5"}}
//        Map<String,Object> m6 = new HashMap<String,Object>();
//        List<Object> l3 = new ArrayList<Object>();
//        Map<String,Object> m7 = new HashMap<String,Object>();
//        m6.put("i","5");
//
//        m7.put("j","5");
//        m7.put("k","5");
//        m6.put("l",m7);
//
//        l3.add(m7);
//        m6.put("j",l3);
//        map.put("map2",m6);
//
//
//        //转换成JSON格式内容
//        String s = g.toJson(map);
//        System.out.println(s);

    }

//    private Double[] callPairCNN(Question question, Evidence[] evidences){
//        Gson param = new Gson();
//        Map<String,Object> parameter = new HashMap<>();
//        List<Word> qustionWords = WordParser.parse(question.getQuestion());
//        List<List<Word>> evidencesWords = new ArrayList<>();
//        for(Evidence evidence:evidences){
//            List<Word> evidenceWords = WordParser.parse(evidence.getTitle() + ":" + evidence.getSnippet());
//            evidencesWords.add(evidenceWords);
//        }
//
//        parameter.put("qustion", qustionWords);
//        parameter.put("envidences", evidencesWords);
//
//        String strParam = param.toJson(param);
//        String retJson = null;
//        Gson gson = new Gson();
//        HttpUriRequest httpRequest;
//        try {
//            httpRequest = new HttpGet(new URIBuilder().setScheme("http")
//                    .setHost("10.231.55.224")
//                    .setPort(12306)
//                    .setPath("/sentence_selection")
//                    .setParameter("param", strParam)
////                    .setHost("qabot-dev.ai.xiaomi.com")
////                    .setPath("/ask")
////                    .setParameter("query", strParam)
//                    .build());
//        }
//        catch (Throwable e) {
//            LOGGER.error(String.format("Failed on initializing http request, query=%s", strParam), e);
//            retJson = "[]" ;
//            return gson.fromJson(retJson, Double[].class);
//        }
//        Future<HttpResponse> future = Constant.HTTP_CLIENT.execute(httpRequest, null);
//        HttpResponse response = null;
//        try {
//            response = future.get(3000, TimeUnit.MILLISECONDS);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == 200) {
//                retJson = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
//            }else {
//                LOGGER.info(String.format("Failed on calling, query=%s", strParam));
//                retJson ="[]";
//            }
//        } catch (InterruptedException e) {
//            LOGGER.error("Fatal error! Should never happen!", e);
//            retJson ="[]";
//        } catch (ExecutionException e) {
//            LOGGER.warn(String.format("Failed on calling, query=%s", strParam), e);
//            retJson ="[]";
//        } catch (TimeoutException e) {
//            LOGGER.info(String.format("Timeout on calling, query=%s", strParam), e);
//            future.cancel(true);
//            retJson ="[]";
//        }catch (IOException e) {
//            LOGGER.warn(String.format("Failed on getting response, query=%s", strParam), e);
//            retJson ="[]";
//        }
//
//        Double[] scores = gson.fromJson(retJson, Double[].class);
//
//        return scores;
//
////        String url = "http://10.136.12.21:12301/sentence_selection?param=" + strParam;
////        LOGGER.info(String.format("call pairCNN的url： " + url));
////        String retJson = null;
////        Connection.Response response = null;
////        try {
////            response = Jsoup.connect(url).ignoreContentType(true).execute();
////            if (response.statusCode() == 200) {
////                retJson = new String(response.bodyAsBytes(), StandardCharsets.UTF_8);
////            }else {
////                LOGGER.info(String.format("O_O返回内容转化失败！！！"));
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////            LOGGER.info(String.format("请求pairCNN服务失败，返回状态码为："+response.statusCode()));
////            retJson = "[]";
////        }
////
////        Gson gson = new Gson();
////        Double[] scores = gson.fromJson(retJson, Double[].class);
////        return scores;
//    }
//
//    private Evidence[] getTopEvidences(Double[] scores, Evidence[] evidences, int topN){
//        List<Pair<Evidence, Double>> evidScoreList = new ArrayList<>();
//        for(int i=0; i< scores.length; i++){
//            Pair<Evidence, Double> tmp = new Pair<>(evidences[i],scores[i]);
//            evidScoreList.add(tmp);
//        }
//        Collections.sort(evidScoreList,new Comparator<Pair<Evidence, Double>>() {
//            public int compare(Pair<Evidence, Double> o1, Pair<Evidence, Double> o2) {
//                if(o2.getValue() > o1.getValue()){
//                    return 1; //降序排
//                }else {
//                    return -1;
//                }
//            }
//        });
//
//        Evidence[] retEvidences = new Evidence[topN];
//        int i=0;
//        for(Pair<Evidence, Double> sortMap:evidScoreList){
//            if (i>= topN){
//                break;
//            }
//            retEvidences[i] = sortMap.getKey();
//            i++;
//        }
//
////        Map<Evidence, Double> evidScoreMap = new HashMap<>();
////        for(int i=0;i< scores.length;i++){
////            evidScoreMap.put(evidences[i],scores[i]);
////        }
////        List<Map.Entry<Evidence,Double>> evidScoreList=new ArrayList<Map.Entry<Evidence,Double>>(evidScoreMap.entrySet());
////        Collections.sort(evidScoreList,new Comparator<Map.Entry<Evidence,Double>>() {
////            public int compare(Map.Entry<Evidence, Double> o1, Map.Entry<Evidence, Double> o2) {
////                if(o2.getValue() > o1.getValue()){
////                    return 1;
////                }else {
////                    return -1;
////                }
////            }
////        });
////
////        Evidence[] retEvidences = new Evidence[topN];
////        int i=0;
////        for(Map.Entry<Evidence,Double> sortMap:evidScoreList){
////            if (i>= topN){
////                break;
////            }
////            retEvidences[i] = sortMap.getKey();
////            i++;
////        }
//
//        return retEvidences;
//
//    }

}
