import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.lang.reflect.Array;

public class APITest {

    private String email ="admin_partner_portal@cloudally1.onmicrosoft.com",
            password="1Q2w3e4r",
            clientId="fe582a70-14c9-4235-8a6f-3b6909773d18",
            clientSecret="uCfrnqpsrbj8Wga#";

    @Test
    public void authPartnerTest() throws IOException {

        Response resp = Request
                .Post("https://api.cloudrein.com/auth/partner")
                .addHeader("client-id", clientId)
                .addHeader("client-secret", clientSecret)
                .bodyString("{\n" +
                        "  \"email\": \""+email+"\",\n" +
                        "  \"password\": \""+password+"\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        String respJson=resp.returnContent().toString();
        System.out.println(respJson);

        JsonElement jsonElement = JsonParser.parseString(respJson);
        String token = jsonElement.getAsJsonObject().get("accessToken").getAsString();
        System.out.println(token);
        Assert.assertTrue(token.length()>0);
        Assert.assertEquals(resp.returnResponse().getStatusLine().getStatusCode(),200);

    }
    @Test
    public void partnersResellersTest() throws IOException{
        Response resp = Request
                .Post("https://api.cloudrein.com/auth/partner")
                .addHeader("client-id", clientId)
                .addHeader("client-secret", clientSecret)
                .bodyString("{\n" +
                        "  \"email\": \""+email+"\",\n" +
                        "  \"password\": \""+password+"\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();
        String respJson=resp.returnContent().toString();



        JsonElement jsonElement = JsonParser.parseString(respJson);
        String token = jsonElement.getAsJsonObject().get("accessToken").getAsString();

        Response resp2 = Request
                .Get("https://api.cloudrein.com/v1/partners/resellers")
                .addHeader("Authorization","Bearer " + token)
                .addHeader("client-id", clientId)
                .addHeader("client-secret", clientSecret)
                .execute();
        Assert.assertEquals(resp2.returnResponse().getStatusLine().getStatusCode(),200);

    }
}

