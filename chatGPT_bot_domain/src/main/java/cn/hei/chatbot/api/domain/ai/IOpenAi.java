package cn.hei.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * chatgpt 接口
 */
public interface IOpenAi {
    String doChatGPT(String question) throws IOException;
}
