package cn.hei.chatbot.api.test;

import cn.hei.chatbot.api.domain.zsxq.IZsxpApi;
import cn.hei.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.hei.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatGPT_bot.groupId}")
    private String groupId;
    @Value("${chatGPT_bot.cookie}")
    private String cookie;

    @Resource
    private IZsxpApi zsxpApi;

    @Test
    public void  test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxpApi.queryUnAnsweredQuestionsTopicId(groupId,cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for(Topics topic :topics){
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            //回答
            zsxpApi.answer(groupId,cookie,topicId,text,false);
        }
    }
}
