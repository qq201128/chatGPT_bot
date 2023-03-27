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
        get.addHeader("cookie","zsxq_access_token=76A8ABB6-7CAE-19E3-FC54-8650EFD9E7A7_8679CB13E766A424; zsxqsessionid=5bd98d99629917e7007019e973df52ce");
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

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181428884525882/answer");

        post.addHeader("cookie","zsxq_access_token=76A8ABB6-7CAE-19E3-FC54-8650EFD9E7A7_8679CB13E766A424; zsxqsessionid=5bd98d99629917e7007019e973df52ce");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"啊啊啊\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("http://45.136.14.30/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer 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");

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"请写一个冒泡排序\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


}