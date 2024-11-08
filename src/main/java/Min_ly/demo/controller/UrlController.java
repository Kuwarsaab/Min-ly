package Min_ly.demo.controller;

import Min_ly.demo.entity.Url;
import Min_ly.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping
public class UrlController {
    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestParam String originalUrl) {
        return ResponseEntity.ok(service.shortener(originalUrl));
    }

    @GetMapping("/{urlCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String urlCode) {

        // Ensure the urlCode is handled properly
        Optional<Url> urlOptional = service.getOriginalUrl(urlCode);

        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            return ResponseEntity
                    .status(302)
                    .location(URI.create(url.getOriginalUrl()))
                    .build();
        } else {
            // Return 404 if not found
            return ResponseEntity.status(404).body("URL not found for the provided code: " + urlCode);
        }
    }

}
