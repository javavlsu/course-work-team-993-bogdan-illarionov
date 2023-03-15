package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class MainController {

    @GetMapping("/index")
    public String getIndex(Model model) {

        System.out.println(123);

        return "/index";
    }

/*


    @GetMapping("/lots")
    public String getLots(Model model)
    {
        return "/lots";
    }


    @GetMapping("/hello")
    public String handle(Model model) throws SQLException, ClassNotFoundException, ParseException {
        model.addAttribute("message", "Hello World!");

        //var df = new SimpleDateFormat("yyy-mm-d");

        var history = new History(2L, (short) 1, "SOME MY NEW STR");

        model.addAttribute(history);

        var histories = dao.getHistories();

        model.addAttribute(histories);

        return "index";
    }

    @GetMapping("/product")
    public String productAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/product")
    public String productAddPost(@ModelAttribute Product product, Model model) {
        productDao.AddProduct(product);

        return "products";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute(productDao.getProducts());

        return "products";
    }

    @GetMapping("/histories")
    public String getAllHistories(Model model) throws SQLException {
        model.addAttribute(dao.getHistories());

        return "histories";
    }

    @GetMapping("/history")
    public String linkForm(Model model) {
        model.addAttribute("history", new History(1,(short) 1,null));
        return "history";
    }

    @PostMapping("/history")
    public String linkPost(@Valid @ModelAttribute("history") History history, BindingResult result, Model model)
            throws SQLException, ClassNotFoundException, ParseException {
        System.out.println(history.getExternalId());
        System.out.println(history.getProviderId());

        System.out.println(result.hasErrors());
        System.out.println(((History)result.getTarget()).getProductMetadata());

        if (result.hasErrors()){

            return "history";
        }

        dao.Add(history);

        return "histories";
    }

    @GetMapping("/delete/history")
    public String linkFormDelete(Model model) {
        return "history";
    }

    @PostMapping("/delete/history")
    public String linkPostDelete(@ModelAttribute History history, Model model) throws SQLException, ClassNotFoundException, ParseException {
        System.out.println(history.getExternalId());
        System.out.println(history.getProviderId());

        dao.Delete(history);

        return "histories";
    }

    @GetMapping("/delete/history/{externalId}/{providerId}")
    public String linkGetDeleteById(@PathVariable long externalId, @PathVariable short providerId, Model model)
            throws SQLException, ClassNotFoundException, ParseException {
        var history = new History(externalId, providerId, null);

        System.out.println(history.getExternalId());
        System.out.println(history.getProviderId());

        dao.Delete(history);

        return "histories";
    }

    @GetMapping("/update/history")
    public String linkPostUpdate(
            @RequestParam(name = "externalId") long externalId,
            @RequestParam(name = "providerId") short providerId,
            @RequestParam(name = "productMetadata") String meta,
            Model model) throws SQLException, ClassNotFoundException, ParseException {

        var history = new History(externalId, providerId, meta);
        model.addAttribute("history", history);

        return "update";
    }

    @PostMapping("/update/history")
    public String linkFormUpdate(@ModelAttribute History history, Model model) throws SQLException {

        dao.Update(history);

        return "histories";
    }

*/
}