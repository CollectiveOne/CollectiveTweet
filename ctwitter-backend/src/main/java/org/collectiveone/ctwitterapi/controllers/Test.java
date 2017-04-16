package org.collectiveone.ctwitterapi.controllers;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public Test(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping(path="/getTweets", method=RequestMethod.GET)
    public CursoredList<TwitterProfile> getTweets() {
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        return friends;
    }
    
    @RequestMapping(path="/sendTweet", method=RequestMethod.GET)
    public boolean sendTweet(@RequestParam String text) {
        twitter.timelineOperations().updateStatus(text);
        return true;
    }

}