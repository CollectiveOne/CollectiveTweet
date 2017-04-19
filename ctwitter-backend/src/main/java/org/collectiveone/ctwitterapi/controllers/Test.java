package org.collectiveone.ctwitterapi.controllers;

import java.util.List;

import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("1/account")
@RestController
public class Test {
	
	@Value("${twitter.consumer.key}")
    String consumerKey;

    @Value("${twitter.consumer.key.secret}")
    String consumerSecret;
    
    @Autowired
    AccountRepository accountRepository;
	
	@RequestMapping(path = "{id}/recentTweets", method = RequestMethod.GET)
    public @ResponseBody List<Tweet> recentTweets(@PathVariable("id") Long id) {
		
		Account account = accountRepository.findById(id);

		TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, account.getAccessToken(), account.getAccessTokenSecret());
        List<Tweet> tweets = twitterTemplate.timelineOperations().getHomeTimeline();
        
//        List<TweetDto> tweetDtos = new ArrayList<TweetDto>();
//        for(Tweet tweet : tweets) {
//        	TweetDto tweetDto = new TweetDto();
//        	tweetDto.setText(tweet.getText());
//        	tweetDtos.add(tweetDto);
//        }
        
        return tweets;
	}

}
