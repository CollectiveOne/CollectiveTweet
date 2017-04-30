package org.collectiveone.ctwitterapi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.AccountState;
import org.collectiveone.ctwitterapi.repositories.AccountRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


@RequestMapping("1")
@RestController
public class TwitterAuthorization {

    @Value("${twitter.consumer.key}")
    String consumerKey;

    @Value("${twitter.consumer.key.secret}")
    String consumerSecret;
    
    @Autowired
    AccountRepositoryIf accountRepository;

    
    /**
     * This API once hit will fetch request token from twitter, save it and 
     * returns authorise url to the frontend. Render that url and display to the user to 
     * authorise the app. Once Authorised, it will redirect to the callback url. Which in this case is the second API of the flow
     * <em>/twitter/access/token</em>
     * @return
     */
    @RequestMapping(path = "/secured/accountAuth/url", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
    public String requestToken(final HttpServletRequest request) {
        //The call back URL
    	/* support */
    	String callBackUrl = getAppUrl(request) + "/1/public/accountAuth/getToken";
        TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(consumerKey, consumerSecret);
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuthToken requestToken = oauthOperations.fetchRequestToken(callBackUrl, null);
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
        Account account = new Account();
        account.setCreatorId(auth.getName());
        account.setState(AccountState.PENDINGAUTHORIZATION);
        account.setRequestToken(requestToken.getValue());
        account.setRequestTokenSecret(requestToken.getSecret());

        account.getMembersIds().add(auth.getName());
        
        accountRepository.save(account);
        
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
    @RequestMapping(path = "/public/accountAuth/getToken", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
    public String getAccessToken(@RequestParam(value = "oauth_token") String oauthToken, @RequestParam(value = "oauth_verifier") String verifier) {

    	Account account = accountRepository.findByRequestToken(oauthToken);
    	
        TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(consumerKey, consumerSecret);
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuthToken oToken = new OAuthToken(account.getRequestToken(), account.getAccessTokenSecret());
        OAuthToken accessToken = oauthOperations.exchangeForAccessToken(new AuthorizedRequestToken(oToken, verifier), null);

        account.setAccessToken(accessToken.getValue());
        account.setAccessTokenSecret(accessToken.getSecret());
        account.setState(AccountState.AUTHORIZED);
        
        /* connect and get account data to be persisted by us */
        TwitterTemplate twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken.getValue(), accessToken.getSecret());
        account.setTwitterHandle(twitter.userOperations().getScreenName());
        
        accountRepository.save(account);

        return "Success";
    }
    
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


}
