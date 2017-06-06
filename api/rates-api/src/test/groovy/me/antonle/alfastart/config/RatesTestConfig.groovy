package me.antonle.alfastart.config

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil
import me.antonle.alfastart.common.Routes
import me.antonle.alfastart.common.api.RateAPI
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

@TestConfiguration
@Lazy
class RatesTestConfig {

    @Value('${local.server.port}')
    int port

    @Bean(name = "rateEndpoint")
    JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>()
        try {
            url = new URL("http://localhost:$port" + Routes.RATES_API)
        } catch (Exception e) {
            System.out.println(e.getMessage())
        }
        return new JsonRpcHttpClient(url, map)
    }

    @Bean(name = "rateAPI")
    RateAPI rateClientRPC(@Qualifier("rateEndpoint") JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), RateAPI.class, jsonRpcHttpClient)
    }

}
