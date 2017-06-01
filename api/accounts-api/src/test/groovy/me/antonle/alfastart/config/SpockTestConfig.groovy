package me.antonle.alfastart.config

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil
import me.antonle.alfastart.common.api.AccountAPI
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

@TestConfiguration
@Lazy
class SpockTestConfig {

    @Value('${local.server.port}')
    int port

    @Bean(name = "accountEndpoint")
    JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>()
        try {
            url = new URL("http://localhost:$port/api/accout")
        } catch (Exception e) {
            System.out.println(e.getMessage())
        }
        return new JsonRpcHttpClient(url, map)
    }

    @Bean(name = "accountAPI")
    AccountAPI accountClientRPC(@Qualifier("accountEndpoint") JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), AccountAPI.class, jsonRpcHttpClient)
    }

}
