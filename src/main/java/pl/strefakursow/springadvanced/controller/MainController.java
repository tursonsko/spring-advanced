package pl.strefakursow.springadvanced.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.strefakursow.springadvanced.component.mailer.SingUpMailer;
import pl.strefakursow.springadvanced.entity.Item;
import pl.strefakursow.springadvanced.entity.User;
import pl.strefakursow.springadvanced.repository.UserRepository;
import pl.strefakursow.springadvanced.service.ItemService;

@RestController
public class MainController {

    private static final int PAGE_SIZE = 3;

    private ItemService itemService;
    private SingUpMailer mailer;
    private UserRepository userRepository;

    public MainController(ItemService itemService, SingUpMailer mailer, UserRepository userRepository) {
        this.itemService = itemService;
        this.mailer = mailer;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public List<Item> index(HttpServletResponse response) {
        return itemService.findByQuantityGreaterThanEqualOrderByQuantityDesc(250);
    }

    @RequestMapping("/quantity_treshold")
    public List<Item> quantityTreshold(@RequestParam(name="quantity") Optional<Integer> quantityParam) {
        int quantity = 50;
        if(quantityParam.isPresent()) {
            quantity = quantityParam.get();
        }

        return itemService.getItemsWithQuantityOver(quantity);
    }


    @RequestMapping("/find_by_name")
    public List<Item> findByName() {
        String regexName="s%";
        List<Item> result = itemService.getItemsWithNameLike(regexName);

        return result;
    }

    @RequestMapping("/items")
    public List<Item> items(@RequestParam(defaultValue="0") String page){
        int currentPage = Integer.parseInt(page);
        PageRequest pageRequest = PageRequest.of(currentPage, PAGE_SIZE);

        Page<Item> items = itemService.findAll(pageRequest);

        return items.getContent();
    }

    @RequestMapping("/send_mail")
    public String sendMail() {
        mailer.sendMessage("spring.advanced.sk.1@gmail.com", "Temat maila!", "Treść maila.");
        return "mail sent";
    }

    @RequestMapping("/confirm_email")
    public String confirmEmail(@RequestParam(name="token") String token) {
        Optional<User> optionalUser = userRepository.findByConfirmationToken(token);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            userRepository.save(user);
            return "Your account has been activated";
        } else {
            return "Given token does not exist";
        }
    }

}

