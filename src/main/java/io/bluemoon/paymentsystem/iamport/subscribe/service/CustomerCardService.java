package io.bluemoon.paymentsystem.iamport.subscribe.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.uneedcomms.payment.generic.http.domain.APIRequest;
import com.uneedcomms.payment.iamport.subscribe.domain.CustomerCard;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerCardService {

    public String getToken() {
        IamportClient client = new IamportClient("", "");
        try {
            IamportResponse<AccessToken> iamPortResponse = client.getAuth();
            return iamPortResponse.getResponse().getToken();
        } catch (IamportResponseException e) {
            System.out.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401 :
                    //
                    break;
                case 500 :
                    //
                    break;
            }
        } catch (IOException e) {
            // 서버 연결 실패
            e.printStackTrace();
        }

        return null;
    }

    public String getCustomerId(CustomerCard customerCard) {
        String token = getToken();
        APIRequest.ResponseWrapper responseWrapper = APIRequest.getIRequestExecutor().getCustomerId();



    }
}
