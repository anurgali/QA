import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

public class APIAccessTokenTest {
    private String email = "admin_partner_portal@cloudally1.onmicrosoft.com",
            password = "1Q2w3e4r",
            clientId = "fe582a70-14c9-4235-8a6f-3b6909773d18",
            clientSecret = "uCfrnqpsrbj8Wga#";

    @Test
    public void AutoPartnersAccessTokenTest() throws IOException{
        Response resp = Request
                .Post("https://api.cloudrein.com/auth/partner")
                .addHeader("client-id", clientId)
                .addHeader("client-secret", clientSecret)
                .bodyString("{\n" +
                        "  \"email\": \""+email+"\",\n" +
                        "  \"password\": \""+password+"\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();// in Postman button "Sent"
        String respJson=resp.returnContent().toString();
        System.out.println(respJson);

        JsonElement jsonElement= JsonParser.parseString(respJson);
        String accessToken= jsonElement.getAsJsonObject().get("accessToken").getAsString();
        System.out.println(accessToken);
    }
}
// TEL-39. API testing: Implement positive sign in this AccessToken