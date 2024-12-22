package org.example.replicatedcache.wishlistservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.ReplicatedMapConfig;
import org.example.replicatedcache.wishlistservice.constant.ProductConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config replicatedMapConfig() {
        Config config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("127.0.0.1");
        config.setClusterName("my-hazelcast-cluster");
        config.setInstanceName("replicated-cache-read-instance");
        ReplicatedMapConfig replicatedMapConfig = new ReplicatedMapConfig(ProductConstant.PRODUCTS);
        replicatedMapConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        replicatedMapConfig.setStatisticsEnabled(true);
        config.addReplicatedMapConfig(replicatedMapConfig);
        return config;
    }
}
