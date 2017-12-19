package com.epam.trainee.controller.commands.find;

import com.epam.trainee.service.SaladService;
import com.epam.trainee.service.ServiceFactory;
import com.epam.trainee.controller.commands.Command;
import com.epam.trainee.controller.utils.WebUrl;
import com.epam.trainee.model.entities.dishes.Salad;
import com.epam.trainee.view.Page;
import com.epam.trainee.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@WebUrl("salads")
public class FindAllSaladsCommand implements Command {

    @Override
    public View executeGet(HttpServletRequest req, HttpServletResponse resp) {
        SaladService saladService = ServiceFactory.getInstance().getSaladService();
        Set<Salad> salads = saladService.getAllSalads();
        req.setAttribute("dishes", salads);
        return Page.ALL_SALADS;
    }
}