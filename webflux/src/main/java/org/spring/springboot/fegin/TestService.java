package org.spring.springboot.fegin;

import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RequestSpecificRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import feign.RequestLine;
import org.spring.springboot.domain.City;
import org.springframework.stereotype.Service;
import reactivefeign.cloud.CloudReactiveFeign;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactivefeign.ReactiveRetryers.retry;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/16/9:40 下午
 * @Description:
 */
@Service
public class TestService {

    public Mono<String> findAllCity() {
        RetryHandler retryHandler = new RequestSpecificRetryHandler(true, true,
                new DefaultLoadBalancerRetryHandler(0, 2, true), null);

        String serviceName = "EUREKA-SERVER";


        TestInterface client = CloudReactiveFeign.<TestInterface>builder()
                .retryWhen(retry(3))
                .enableLoadBalancer(retryHandler)
                .setHystrixCommandSetterFactory(getSetterFactoryWithTimeoutDisabled())
                .target(TestInterface.class, "http://EUREKA-SERVER");



        return client.get();
    }



    private static CloudReactiveFeign.SetterFactory getSetterFactoryWithTimeoutDisabled() {
        return (target, methodMetadata) -> {
            String groupKey = target.name();
            HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey(methodMetadata.configKey());
            return HystrixObservableCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(commandKey)
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionTimeoutEnabled(false)
                    );
        };
    }

    interface TestInterface {
        @RequestLine("GET /get")
        Mono<String> get();
    }
}
