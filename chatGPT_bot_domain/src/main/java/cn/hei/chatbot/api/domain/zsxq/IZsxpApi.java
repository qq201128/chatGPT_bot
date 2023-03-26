package cn.hei.chatbot.api.domain.zsxq;

import cn.hei.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxpApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie)throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
