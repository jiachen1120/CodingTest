import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Search {

    @RequestMapping("/")
    String home() {
        return "Please input keywords after /";
    }

    @RequestMapping("/{keyword}")
    String search(@PathVariable String keyword) throws Exception {
        SearchString se = new SearchString();
        return se.Find(keyword.toLowerCase());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Search.class, args);
    }
}