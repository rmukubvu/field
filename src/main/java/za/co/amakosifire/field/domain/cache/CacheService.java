package za.co.amakosifire.field.domain.cache;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CacheService {
    private final Jedis jedis;
    private final Gson gson;

    public <T> void add(String key, T item) {
        jedis.set(key, gson.toJson(item));
    }

    public <T> void addWithExpiry(String key,int expire, T item) {
        jedis.setex(key, expire, gson.toJson(item));
    }

    public Optional<String> get(String key) {
       return Optional.of(jedis.get(key));
    }

    public <T> Optional<T> get(String key,Class<T> classOfT) {
        var cacheValue = Optional.of(jedis.get(key));
        if (cacheValue.isPresent()) {
            return Optional.of(gson.fromJson(cacheValue.get(), classOfT));
        }
        return Optional.empty();
    }

    public void deleteKey(String key) {
        jedis.del(key);
    }
}
