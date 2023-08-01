package dev.hlab.twitterino.pl;

import dev.hlab.twitterino.bll.service.TweetService;
import dev.hlab.twitterino.dal.dto.DeleteTweetDTO;
import dev.hlab.twitterino.dal.dto.GetTweetsByHashtagDto;
import dev.hlab.twitterino.dal.dto.SaveTweetDtoRequest;
import dev.hlab.twitterino.dal.dto.TweetDto;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tweet")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PutMapping("new")
    public ResponseEntity<Void> addNewTweet(@RequestBody SaveTweetDtoRequest saveTweetDtoRequest) {
        try {
            tweetService.saveTweet(saveTweetDtoRequest);
            return ResponseEntity.ok().build();
        } catch (FieldNotValidException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("get-by-hashtag")
    public ResponseEntity<List<TweetDto>> getByHashtag(@RequestBody GetTweetsByHashtagDto getTweetsByHashtagDto) {
        List<TweetDto> list = tweetService.getTweetsByHashtag(getTweetsByHashtagDto);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteTweet(@RequestBody DeleteTweetDTO dto) {
        try {
            tweetService.deleteTweet(dto);
            return ResponseEntity.ok().build();
        } catch (IllegalCallerException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<TweetDto>> getAll() {
        return ResponseEntity.ok(tweetService.getTweets());
    }

    @PostMapping("like")
    public ResponseEntity<Void> like(@RequestBody DeleteTweetDTO dto) {
        try {
            tweetService.like(dto);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
