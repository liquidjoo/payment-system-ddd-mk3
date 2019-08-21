package io.bluemoon.paymentsystem.generic.http.domain;

import io.bluemoon.paymentsystem.iamport.subscribe.domain.CustomerCard;
import lombok.Getter;
import okhttp3.*;

import java.io.IOException;

public class APIRequest {

    public interface IRequestExecutor {
        ResponseWrapper getCustomerId(CustomerCard customerCard, String token, String projectId) throws IOException;
    }

    private static IRequestExecutor iRequestExecutor = new DefaultRequestExecutor();

    public static IRequestExecutor getIRequestExecutor() {
        return iRequestExecutor;
    }

    public static class DefaultRequestExecutor implements IRequestExecutor {
        static OkHttpClient client = null;
        static void init() {
            client = new OkHttpClient();
        }
        static {
            init();
        }

        @Override
        public ResponseWrapper getCustomerId(CustomerCard customerCard, String token, String projectId) throws IOException {
            RequestBody formBody = new FormBody.Builder()
                    .add("", customerCard.getCardNumber())
                    .add("", customerCard.getExpiry())
                    .add("", customerCard.getBirth())
                    .add("", customerCard.getPwd2Digit())
                    .build();

            Request request = new Request.Builder()
                    .url("https://api.iamport.kr/subscribe/customers/"+projectId)
                    .addHeader("Authorization", token)
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);

            Response response = call.execute();

            return null;
        }
    }

    @Getter
    public static class ResponseWrapper {
        private String body;
        private String header;

        public ResponseWrapper(String body, String header) {
            this.body = body;
            this.header = header;
        }
    }
}
