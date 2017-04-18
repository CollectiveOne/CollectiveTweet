package org.collectiveone.ctwitterapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * A demo controller for twitter API intergration. For simplicity I have saved the token into System properties.
 * You might want to persist them in a database mapped to individual user.
 * @author dhrubajyotib
 *
 */
@RequestMapping("twitter")
@RestController
public class TwitterHandleController {

    @Value("${twitter.consumer.key}")
    String consumerKey;

    @Value("${twitter.consumer.key.secret}")
    String consumerSecret;

    
    /**
     * This API once hit will fetch request token from twitter, save it and 
     * returns authorise url to the frontend. Render that url and display to the user to 
     * authorise the app. Once Authorised, it will redirect to the callback url. Which in this case is the second API of the flow
     * <em>/twitter/access/token</em>
     * @return
     */
    @RequestMapping(path = "authorise/url", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
    public String requestToken() {
        //The call back URL
        String callBackUrl="http://127.0.0.1:8080/twitter/access/token";
        TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(consumerKey, consumerSecret);
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuthToken requestToken = oauthOperations.fetchRequestToken(callBackUrl, null);
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);

        System.setProperty("requestToken", requestToken.getValue());// Store them in database
        System.setProperty("requestTokenSecret", requestToken.getSecret());// Store them in database
        return authorizeUrl;

    }

    
    /**
     * Once the call back url it being by twitter after the authorisation, twitter appends the oauth_token and oauth_verifier
     * as path param. Using this oauth_verifier and earlier received requestToken, we generate accessToken object and save it.
     * And later use this for any operation ion the user account.
     * @param oauthToken
     * @param verifier
     * @return
     */
    @RequestMapping(path = "access/token", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
    public String getAccessToken(@RequestParam(value = "oauth_token") String oauthToken, @RequestParam(value = "oauth_verifier") String verifier) {

        System.out.println(System.getProperty("requestToken"));
        System.out.println(System.getProperty("requestTokenSecret"));
        TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(consumerKey, consumerSecret);
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuthToken oToken = new OAuthToken(System.getProperty("requestToken"), System.getProperty("requestTokenSecret"));
        OAuthToken accessToken = oauthOperations.exchangeForAccessToken(new AuthorizedRequestToken(oToken, verifier), null);

        System.setProperty("accessToken", accessToken.getValue());// Store them in database
        System.setProperty("accessTokenSecret", accessToken.getSecret());// Store them in database
        return "Success";
    }

    
    /**
     * A simple api to tweet.
     * @return
     */
    @RequestMapping(path = "tweet", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
    public String tweet() {

        TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, System.getProperty("accessToken"), System.getProperty("accessTokenSecret"));

        twitterTemplate.timelineOperations()
            .updateStatus("hello");
        return "done";

    }

}
