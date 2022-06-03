package product;

import base.Log;
import base.helpers.HttpClient;
import base.constant.helpers.Verify;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

public class Products {

    private String installment1 = "test/installment=1";
    private String installment0 = "test/installment=0";
    private String installmentNull = "test/";
    Map<String, Object> query = new HashMap<>();


    @Test
    public void getProductListByInstallment1() {
        query.put("installment", "1");
        Response response = HttpClient.get(installment1);

        Verify.statusCode(response,200);
        Verify.schemaVerify(response,"productList.json");
        Assertions.assertEquals(true,response.jsonPath().get("result.data.products[0].installment"),"installment is not true");
        Assertions.assertEquals("6 x 13,33 TL Taksit Seçeneği",response.jsonPath().get("result.data.products[0].installmentText"),"installmentTexts are not match");
        Assertions.assertEquals(1, (Integer) response.jsonPath().get("result.data.products[0].productGroupId"),"productGroupId are not match");
        Log.testPass();
    }

    @Test
    public void getProductListByInstallment0() {
        query.put("installment", "1");
        Response response = HttpClient.get(installment0);

        Verify.statusCode(response,200);
        Verify.schemaVerify(response,"productList.json");
        Assertions.assertEquals(false,response.jsonPath().get("result.data.products[0].installment"),"installment is not true");
        Assertions.assertEquals("",response.jsonPath().get("result.data.products[0].installmentText"),"installmentTexts are not match");
        Assertions.assertEquals(2, (Integer) response.jsonPath().get("result.data.products[0].productGroupId"),"productGroupId are not match");
        Log.testPass();
    }

    @Test
    public void getProductListByInstallmentNull() {

        query.put("installment", "");
        Response response = HttpClient.get(installmentNull, query);
        Verify.statusCode(response, 500);
        Log.testPass();
    }


}
