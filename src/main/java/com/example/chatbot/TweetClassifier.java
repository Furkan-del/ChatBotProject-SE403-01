/*
package com.example.chatbot;


import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class TweetClassifier {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";

    public static String classifyTweet(String tweet) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(API_ENDPOINT);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + API_KEY);

        JSONObject requestBody = new JSONObject();
        requestBody.put("You are fucking idiot damn bitch", tweet);
        post.setEntity(new StringEntity(requestBody.toString()));

        String response = EntityUtils.toString(client.execute(post).getEntity());
        JSONObject jsonResponse = new JSONObject(response);

        return jsonResponse.getJSONArray("choices").getString(0);
    }

}
*/
