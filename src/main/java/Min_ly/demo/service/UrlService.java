package Min_ly.demo.service;

import Min_ly.demo.entity.Url;
import Min_ly.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int URL_CODE_LENGTH = 6;

    private String generateUrlCode() {
        StringBuilder urlCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < URL_CODE_LENGTH; i++) {
            int idx = random.nextInt(ALPHANUMERIC_STRING.length());
            urlCode.append(ALPHANUMERIC_STRING.charAt(idx));
        }

        return urlCode.toString();
    }

    @Autowired
    private UrlRepository repository;

    public Url shortener(String originalUrl){
        String urlCode = generateUrlCode();
        return repository.save(Url.builder().originalUrl(originalUrl).urlCode(urlCode).build());
    }

    public Optional<Url> getOriginalUrl(String urlCode) {
        return repository.findByUrlCode(urlCode);
    }
}
