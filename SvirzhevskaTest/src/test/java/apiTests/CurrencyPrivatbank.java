package apiTests;

import api.CurrencyDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.GET_CURRENCY;
import static io.restassured.RestAssured.given;

public class CurrencyPrivatbank {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeCours(){
        CurrencyDTO[] responceBody = given()
                .log().all()
        .when()
                .get(GET_CURRENCY)
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response().as(CurrencyDTO[].class)
                ;
        logger.info(responceBody.length);

        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("EUR","UAH"),
                new CurrencyDTO("USD","UAH")
        };
        Assert.assertEquals(responceBody.length, expectedCurrencyDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responceBody[i],"buy","sale");
        }
        softAssertions.assertAll();
    }
}
