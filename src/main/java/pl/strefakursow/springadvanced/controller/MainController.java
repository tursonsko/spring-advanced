package pl.strefakursow.springadvanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.strefakursow.springadvanced.component.SignUpMailer;
import pl.strefakursow.springadvanced.entity.Item;
import pl.strefakursow.springadvanced.service.ItemService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private static final int PAGE_SIZE = 3;

    private ItemService itemService;
    private SignUpMailer mailer;

    @Autowired
    public MainController(ItemService itemService, SignUpMailer mailer) {
        this.itemService = itemService;
        this.mailer = mailer;
    }

    @RequestMapping("/")
    public List<Item> index(HttpServletResponse response) {
        return itemService.findAllByQuantityGreaterThanEqualOrderByQuantityDesc(180);
    }

    @RequestMapping("/quantity_threshold")
    public List<Item> quantityThreshold(@RequestParam(name = "quantity") Optional<Integer> quantityParam) {
        int quantity = 50;
        if (quantityParam.isPresent())
            quantity = quantityParam.get();
        return itemService.getItemWithQuantityOver(quantity);
    }

    @RequestMapping("/find_by_name")
    public List<Item> findByName() {
        String regexName="s%";
        List<Item> result = itemService.getItemWithNameLike(regexName);
        return result;
    }

    @RequestMapping("/items")
    public List<Item> items(@RequestParam(defaultValue = "0") String page) {
        int currentPage = Integer.parseInt(page);
        PageRequest pageRequest = PageRequest.of(currentPage, PAGE_SIZE);

        Page<Item> items = itemService.findAll(pageRequest);

        return items.getContent();
    }

    @RequestMapping("/send_mail")
    public String sendMail() {
        mailer.sendMessage("tursonsko@gmail.com", "Temat maila testowy", "Siema, witam mailem ze sptinga");
        return "mail sent";
    }
}
