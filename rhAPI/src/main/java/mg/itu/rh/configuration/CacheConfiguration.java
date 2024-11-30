package mg.itu.rh.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("jours_feries");
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(365, TimeUnit.DAYS)
            .maximumSize(100)
        );

        return caffeineCacheManager;
    }
}
