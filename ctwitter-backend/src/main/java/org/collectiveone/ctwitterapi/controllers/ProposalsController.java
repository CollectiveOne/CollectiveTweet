package org.collectiveone.ctwitterapi.controllers;

import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("1/secured/proposal")
@RestController
public class ProposalsController {

    @Autowired
    ProposalService proposalService;
    
    @RequestMapping(path = "", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
    public String tweet(@RequestBody TweetDto tweetDto, @RequestParam Long accountId) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth.isAuthenticated()) {
        	proposalService.create(auth.getName(), accountId, tweetDto);
    	}
    	
    	return "error";
	}

}
