package book.book.controller;

import book.book.dao.Review;
import book.book.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @GetMapping("/")
    public String loadIndexPage(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        model.addAttribute("newReview", new Review());
        return "index";
    }


    @PostMapping("/reviews")
    public String submitReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/";
    }
}
