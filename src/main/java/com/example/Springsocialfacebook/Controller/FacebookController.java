package com.example.Springsocialfacebook.Controller;

import com.example.Springsocialfacebook.Repository.UserDetailsRepo;
import com.example.Springsocialfacebook.UserProfile.UserPojoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookController {

    private Facebook facebook;

    private ConnectionRepository connectionRepository;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }


    @GetMapping
    public String getfacebookFeeds(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        PagedList<Post> posts = facebook.feedOperations().getPosts();
//        User profile = facebook.userOperations().getUserProfile();
        model.addAttribute("profileName", posts.get(0).getFrom().getName());
//        model.addAttribute("dob",profile.getEmail());
        model.addAttribute("posts", posts);
        return "profile";
    }

//    public String userDetails(@PathVariable String id)
//    {
//        UserPojoDetails userPojoDetails;
//        User profile = facebook.userOperations().getUserProfile();
//        if(profile.getId() == id)
//        {
//            userPojoDetails = userDetailsRepo.save(profile);
//        }
//    }

}