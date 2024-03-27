package com.gagi.swdc.global.feign.clova.dto.req;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ClovaChatCompletionsRequest {
    public static String data(String question) throws IOException {
        File file = new File("src/main/resources/data_aa.jsonl");
        String jsonlData = FileUtils.readFileToString(file, "UTF-8");

        String[] lines = jsonlData.split("\n");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayNode messages = mapper.createArrayNode();

        ObjectNode systemMessage = mapper.createObjectNode();
        systemMessage.put("role", "system");
        systemMessage.put("content", "이것은 어려운 문장을 쉬운 문장으로 번역해주는 서비스 입니다\n\n- 출력은 변환된 문장과 바뀐 단어들을 출력 합니다\n- 어려운 단어들을 쉬운 단어로 변환해 쉬운 문장으로 변환합니다\n- \n\n입력 : 체육관 수리 때문에 완공이 지연되고 있습니다\n출력 : 체육관 수리 때문에 완공이 지연되고 있습니다\n\n하자보수 (瑕疵補修) - 공사 따위에서 잘못되거나 부족한 부분을 고치어 바로 잡음\n준공(竣工) - 공사를 마침");
        messages.add(systemMessage);

        for (String line : lines) {
            ObjectNode node = mapper.readValue(line, ObjectNode.class);

            String text = node.get("text").asText();
            String completion = node.get("completion").asText();

            ObjectNode userMessage = mapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", text);

            ObjectNode assistantMessage = mapper.createObjectNode();
            assistantMessage.put("role", "assistant");
            assistantMessage.put("content", completion);

            messages.add(userMessage);
            messages.add(assistantMessage);
        }

        ObjectNode userMessage = mapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", question);
        messages.add(userMessage);

        ObjectNode root = mapper.createObjectNode();
        root.set("messages", messages);

        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String json = writer.writeValueAsString(root);
        System.out.println(json);
        return json;
    }
}
