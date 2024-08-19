package book.book.service;

import book.book.dao.Review;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReviewService {

    private static final String FILE_PATH = "reviews.txt"; // File path for storing reviews

    public ReviewService() {
        // Ensure the file exists
        try {
            File file = new File("reviews.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("reviews.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2); // Assumes reviews are stored as "name;content"
                if (parts.length == 2) {
                    reviews.add(new Review(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void saveReview(Review review) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt", true))) {
            writer.write(review.getName() + ";" + review.getContent());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void deleteReview(String reviewName) {
//        Iterator<Review> iterator = reviews.iterator();
//        while (iterator.hasNext()) {
//            Review review = iterator.next();
//            if (review.getName().equals(reviewName)) {
//                iterator.remove();
//                break; // Assuming names are unique and only one review will be deleted
//            }
//        }
//    }
}