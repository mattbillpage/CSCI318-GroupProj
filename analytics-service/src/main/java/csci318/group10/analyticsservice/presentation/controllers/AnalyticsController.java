package csci318.group10.analyticsservice.presentation.controllers;

import csci318.group10.analyticsservice.applicationservice.InteractiveQuery;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final InteractiveQuery interactiveQuery;

    public AnalyticsController(InteractiveQuery interactiveQuery) {
        this.interactiveQuery = interactiveQuery;
    }

    @GetMapping("/totalCarts")
    @ResponseBody
    public long getTotalCarts() {
        return interactiveQuery.getNonEmptyCartCount();
    }
}
