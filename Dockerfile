# 基础镜像
FROM openjdk:8-jre-slim

# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
ENV LANG C.UTF-8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD /chatGPT_bot_interfaces/target/chatGPT_bot.jar /chatGPT_bot.jar
# 执行镜像；docker run -e PARAMS=" --chatbot-api.groupId=你的星球ID --chatbot-api.openAiKey=自行申请 --chatbot-api.cookie=登录cookie信息" -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /chatGPT_bot.jar $PARAMS"]

