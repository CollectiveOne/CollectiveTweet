package org.collectiveone.ctwitterapi.controllers;

import org.collectiveone.ctwitterapi.dtos.ProposalDto;
import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.model.EditionRankType;
import org.collectiveone.ctwitterapi.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(path = "/{id}",  method = RequestMethod.GET)
    public ProposalDto get(@PathVariable("id") Long id) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth.isAuthenticated()) {
    		return proposalService.get(id, auth.getName());
    	} else {
    		return proposalService.get(id, null);
    	}
    	
    }
    
    @RequestMapping(path = "", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
    public String create(@RequestBody TweetDto tweetDto, @RequestParam Long accountId) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth.isAuthenticated()) {
        	proposalService.create(auth.getName(), accountId, tweetDto);
        	return "success";
    	}
    	
    	return "error";
	}
    
    @RequestMapping(path = "/{id}/edition", method = RequestMethod.POST, produces = { MediaType.TEXT_HTML_VALUE })
    public String addEdition(@RequestBody TweetDto tweetDto, @PathVariable("id") Long proposalId, @RequestParam("parentId") Long parentId) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth.isAuthenticated()) {
        	proposalService.addEdition(auth.getName(), proposalId, parentId, tweetDto);
        	return "success";
    	}
    	
    	return "error";
	}
    
    @RequestMapping(path = "/{pId}/edition/{eId}/rank", method = RequestMethod.PUT, produces = { MediaType.TEXT_HTML_VALUE })
    public String rankEdition(
    		@PathVariable("pId") Long proposalId, 
    		@PathVariable("eId") Long editionId,
    		@RequestParam("myRank") int rank,
    		@RequestParam("myRankType") String rankTypeString
    		) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth.isAuthenticated()) {
    		EditionRankType rankType = EditionRankType.valueOf(rankTypeString);
    		
        	proposalService.rankEdition(auth.getName(), proposalId, editionId, rank, rankType);
        	return "success";
    	}
    	
    	return "error";
	}
}
