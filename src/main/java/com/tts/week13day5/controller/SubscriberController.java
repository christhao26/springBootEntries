package com.tts.week13day5.controller;

import com.tts.week13day5.Model.Subscriber;
import com.tts.week13day5.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubscriberController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping(value = "/")
    public String index(Subscriber subscriber) {
        //this is the specified template
        //it will be utilized by thymeleaf
        return "subscriber/index";

    }

    private Subscriber subscriber;

    @PostMapping(value = "/")
    public String addNewSubscriber(Subscriber subscriber, Model model) {
        subscriberRepository.save(new Subscriber(subscriber.getFirstName(),
                subscriber.getLastName(), subscriber.getUserName(), subscriber.getSignedUp()));
        model.addAttribute("firstName", subscriber.getFirstName());
        model.addAttribute("lastName", subscriber.getLastName());
        model.addAttribute("userName", subscriber.getUserName());
        return "subscriber/result";
    }

    @GetMapping("/listSubscribers")
    public String getSubscribers(Model model, String keyword) {
        List<Subscriber> subscriberList = (List<Subscriber>) subscriberRepository.findAll();
        model.addAttribute("subscribers", subscriberList);
        return "subscriber/listSubscribers";
    }
}