package com.ntk.controllers;

import com.ntk.pojos.*;
import com.ntk.services.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class PlanFeatureController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountReportService accountReportService;

    @RequestMapping(path = "/plan")
    public String planFeature() {
        return "plan-feature";
    }

    @RequestMapping(path = "/plan/route")
    public String routeFeature(Model model) {
        model.addAttribute("products", productService.getProductCurrentUser());
        return "route";
    }

    @RequestMapping(path = "/plan/route", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public String routeMap(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        List<List<String>> productIds = UtilsController.splitStr(httpServletRequest.getParameter("products"), ",");
        User user = userService.getUserObj(userService.getCurrentUser().getUserId(), "userProducts");
        user.getUserProducts().forEach(e -> userProductService.deleteUserProduct(e));
        productIds.forEach(e -> e.forEach(e1 -> {
            int productId = Integer.parseInt(UtilsController.decodeBase64(e1));
            UserProductId userProductId = new UserProductId();
            userProductId.setUserId(user.getUserId());
            userProductId.setProductId(productId);
            UserProduct userProduct = new UserProduct();
            userProduct.setUserProductId(userProductId);
            userProductService.addUserProduct(userProduct);
        }));
        return "redirect:/plan/route";
    }

    @PostMapping("/plan/api/productNameHint")
    public ResponseEntity<List<JSONObject>> getHint(
            @RequestBody Map<String, String> params) {
        String keyword = params.get("keyword");
        return new ResponseEntity<>(productService.getProductNames(keyword), HttpStatus.OK);
    }

    @PostMapping("/plan/api/searchResult")
    public ResponseEntity<List<JSONObject>> getSearchResult(
            @RequestBody Map<String, String> params) {
        String keyword = params.get("keyword");
        int offSet = Integer.parseInt(params.get("offSet"));
        int limit = Integer.parseInt(params.get("limit"));
        return new ResponseEntity<>(
                productService.getProductDetails(
                        keyword, offSet, limit, "category", "stall", "productUnits"),
                HttpStatus.OK);
    }

    @PostMapping("/plan/api/amountSearchResult")
    public ResponseEntity<JSONObject> getAmountSearchResult(
            @RequestBody Map<String, String> params) {
        String keyword = params.get("keyword");
        return new ResponseEntity<>(
                productService.getAmountProductDetails(keyword),
                HttpStatus.OK);
    }

    @GetMapping("/plan/route/api/productLocation")
    public ResponseEntity<Set<JSONObject>> getProductLocations() {
        return new ResponseEntity<>(
                locationService.getProductLocationCurrentUser(),
                HttpStatus.OK);

    }

    @PostMapping("/plan/route/api/productName")
    public ResponseEntity<List<JSONObject>> getProductNames(
            @RequestBody Map<String, String> params) {
        String locationId = params.get("locationIds");
        List<List<String>> lIds = UtilsController.splitStr(locationId, ",");
        List<Integer> lIdRe = new ArrayList<>();
        lIds.forEach(e -> e.forEach(e1 -> lIdRe.add(Integer.parseInt(UtilsController.decodeBase64(e1)))));
        return new ResponseEntity<>(
                locationService.getProductsOfLocation(lIdRe),
                HttpStatus.OK);
    }

    @GetMapping("/plan/api/reportInfo")
    public ResponseEntity<List<JSONObject>> getReports() {
        return new ResponseEntity<>(
                reportService.getReports(),
                HttpStatus.OK);
    }

    @PostMapping("/plan/api/addReport")
    public ResponseEntity<Boolean> addReport(
            @RequestBody Map<String, String> params) {
        List<List<String>> reportIds = UtilsController.splitStr(params.get("reportIds"), ",");
        AtomicBoolean result = new AtomicBoolean(true);
        int accountId = accountService.getAccountObj(UtilsController.getCurrentUsername()).getAccountId();
        reportIds.forEach(e -> e.forEach(e1 -> {
            int reportId = Integer.parseInt(UtilsController.decodeBase64(e1));
            AccountReportId accountReportId = new AccountReportId();
            accountReportId.setReportId(reportId);
            accountReportId.setAccountId(accountId);
            AccountReport accountReport = accountReportService.getAccountReport(accountReportId);
            boolean res;

            if (accountReport == null) {
                accountReport = new AccountReport();
                accountReport.setAccountReportId(accountReportId);
                res = accountReportService.addAccountReport(accountReport);
            } else {
                accountReport.setAmount(accountReport.getAmount() + 1);
                res = accountReportService.updateAccountReport(accountReport);
            }

            if (res) {
                Account account = accountService.getAccountObj(accountId);
                if (accountReportService.countAccountReport(account) > 5000 &&
                        account.getIsActive() == 1) {
                    account.setIsActive((byte) 0);
                    accountService.updateAccount(account);
                }
            }

            if (!res)
                result.set(false);
        }));
        return new ResponseEntity<>(
                result.get(),
                HttpStatus.OK);
    }
}
