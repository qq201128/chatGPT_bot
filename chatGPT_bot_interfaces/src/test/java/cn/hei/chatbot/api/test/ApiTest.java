package cn.hei.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sun.security.jca.GetInstance;

import java.io.IOException;

/**
 * 单元测试
 */
public class ApiTest {

    @Test
    public  void query_unanswered_questions() throws IOException {
        //todo CloseableHttpClient是什么？
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //todo HttpGet是什么？
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885828412441/topics?scope=unanswered_questions&count=20");
        get.addHeader("Content-Type", "application/json;charset=utf8");
        get.addHeader("cookie","sajssdk_2015_cross_new_user=1; zsxq_access_token=E799E913-8223-07AD-04BD-84C771EC5618_8679CB13E766A424; zsxqsessionid=eeb8f3272f9c9f87e119ff9a8e0c9b55; abtest_env=product; UM_distinctid=18717c43d39291-0e58d944610df-65075b53-1fa400-18717c43d3a76e; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184254818845452%22%2C%22first_id%22%3A%2218717b1aa83ad-05e106f3fd78bb-65075b53-2073600-18717b1aa842a4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MTdiMWFhODNhZC0wNWUxMDZmM2ZkNzhiYi02NTA3NWI1My0yMDczNjAwLTE4NzE3YjFhYTg0MmE0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg0MjU0ODE4ODQ1NDUyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184254818845452%22%7D%2C%22%24device_id%22%3A%2218717b1aa83ad-05e106f3fd78bb-65075b53-2073600-18717b1aa842a4%22%7D");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/584528818458824/answer");

        post.addHeader("cookie","sajssdk_2015_cross_new_user=1; zsxq_access_token=E799E913-8223-07AD-04BD-84C771EC5618_8679CB13E766A424; zsxqsessionid=eeb8f3272f9c9f87e119ff9a8e0c9b55; abtest_env=product; UM_distinctid=18717c43d39291-0e58d944610df-65075b53-1fa400-18717c43d3a76e; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184254818845452%22%2C%22first_id%22%3A%2218717b1aa83ad-05e106f3fd78bb-65075b53-2073600-18717b1aa842a4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MTdiMWFhODNhZC0wNWUxMDZmM2ZkNzhiYi02NTA3NWI1My0yMDczNjAwLTE4NzE3YjFhYTg0MmE0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg0MjU0ODE4ODQ1NDUyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184254818845452%22%7D%2C%22%24device_id%22%3A%2218717b1aa83ad-05e106f3fd78bb-65075b53-2073600-18717b1aa842a4%22%7D");
        post.addHeader("Content-Type", "application/json");

        String paramJson = "{\n" +
                "  \"text\": \"啊啊啊\\n\",\n" +
                "  \"image_ids\": []\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res.);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }



}