package dev.hlab.twitterino.pl;

import dev.hlab.twitterino.bll.service.HashtagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hashtag")
public class HashtagController {
    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("trends")
    public ResponseEntity<List<String>> getTrends() {
        return ResponseEntity.ok(hashtagService.getTrends());
    }
}
