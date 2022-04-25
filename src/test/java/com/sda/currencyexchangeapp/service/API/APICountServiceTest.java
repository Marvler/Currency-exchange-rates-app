package com.sda.currencyexchangeapp.service.API;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.configuration.GsonConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {APICountService.class, ConfigProperties.class, GsonConfig.class})
@ExtendWith(SpringExtension.class)
class APICountServiceTest {
    @MockBean
    private APIConnectionService aPIConnectionService;

    @Autowired
    private APICountService aPICountService;

    @Autowired
    private ConfigProperties configProperties;

    /**
     * Method under test: {@link APICountService#getTotalNumberOfApiCalls(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTotalNumberOfApiCalls() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.google.gson.JsonObject.getAsJsonObject()" because "jsonObject" is null
        //       at com.sda.currencyexchangeapp.service.API.APICountService.getTotalNumberOfApiCalls(APICountService.java:26)
        //   In order to prevent getTotalNumberOfApiCalls(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTotalNumberOfApiCalls(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        this.aPICountService.getTotalNumberOfApiCalls("https://config.us-east-2.amazonaws.com");
    }

    /**
     * Method under test: {@link APICountService#getTotalNumberOfApiCalls(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTotalNumberOfApiCalls2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.sda.currencyexchangeapp.service.API.APICountService.getTotalNumberOfApiCalls(APICountService.java:25)
        //   In order to prevent getTotalNumberOfApiCalls(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTotalNumberOfApiCalls(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.aPICountService.getTotalNumberOfApiCalls("https://config.us-east-2.amazonaws.com");
    }

    /**
     * Method under test: {@link APICountService#getTotalNumberOfApiCalls(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTotalNumberOfApiCalls3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: Expected a com.google.gson.JsonObject but was com.google.gson.JsonPrimitive; at path $
        //       at com.google.gson.internal.bind.TypeAdapters$34$1.read(TypeAdapters.java:920)
        //       at com.google.gson.Gson.fromJson(Gson.java:991)
        //       at com.google.gson.Gson.fromJson(Gson.java:956)
        //       at com.google.gson.Gson.fromJson(Gson.java:905)
        //       at com.google.gson.Gson.fromJson(Gson.java:876)
        //       at com.sda.currencyexchangeapp.service.API.APICountService.getTotalNumberOfApiCalls(APICountService.java:25)
        //   In order to prevent getTotalNumberOfApiCalls(String)
        //   from throwing JsonSyntaxException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTotalNumberOfApiCalls(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>("https://example.org/example", HttpStatus.CONTINUE));
        this.aPICountService.getTotalNumberOfApiCalls("https://config.us-east-2.amazonaws.com");
    }

    /**
     * Method under test: {@link APICountService#getTotalNumberOfApiCalls(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTotalNumberOfApiCalls4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: Expected a com.google.gson.JsonObject but was com.google.gson.JsonPrimitive; at path $
        //       at com.google.gson.internal.bind.TypeAdapters$34$1.read(TypeAdapters.java:920)
        //       at com.google.gson.Gson.fromJson(Gson.java:991)
        //       at com.google.gson.Gson.fromJson(Gson.java:956)
        //       at com.google.gson.Gson.fromJson(Gson.java:905)
        //       at com.google.gson.Gson.fromJson(Gson.java:876)
        //       at com.sda.currencyexchangeapp.service.API.APICountService.getTotalNumberOfApiCalls(APICountService.java:25)
        //   In order to prevent getTotalNumberOfApiCalls(String)
        //   from throwing JsonSyntaxException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTotalNumberOfApiCalls(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn((ResponseEntity<String>) mock(ResponseEntity.class));
        this.aPICountService.getTotalNumberOfApiCalls("https://config.us-east-2.amazonaws.com");
    }
}

