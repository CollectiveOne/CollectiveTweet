package org.collectiveone.ctwitterapi.controllers;

import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("1/account/action")
@RestController
public class AccountActions {

	@Value("${twitter.consumer.key}")
    String consumerKey;

    @Value("${twitter.consumer.key.secret}")
    String consumerSecret;
    
    @Autowired
    AccountRepository accountRepository;
	
	@RequestMapping(path = "{id}/tweet", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
    public String tweet(@PathVariable("id") Long id, @RequestBody TweetDto tweetDto) {
		
		Account account = accountRepository.findById(id);
		
		TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, account.getAccessToken(), account.getAccessTokenSecret());

        twitterTemplate.timelineOperations()
            .updateStatus(tweetDto.getText());
        
        return "done";
	}
}
