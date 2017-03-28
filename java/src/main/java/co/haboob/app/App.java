package co.haboob.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Hello haboob!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String env = "development";

            if (args.length> 0) {
                env = args[0];
            }

            System.out.println("Runnning on env: "+ env);

            HttpPost httpPost = new HttpPost("https://send.haboob.co/v1/hooks/r1i-hzShe/send/"+env);

            StringEntity params = new StringEntity("{ \"list\": [ { \"title\": \"Java example\", \"value\": \"Java emails made easy\" } ], \"user\": { \"email\": \"john@doe-main.com\", \"name\": \"John Doe\" }, \"lang\": \"Java\", \"confirmUrl\": \"http://haboob.co\" }");
            httpPost.addHeader("content-type", "application/json");

            httpPost.setEntity(params);
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
