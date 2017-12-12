package com.epam.trainee.controller.commands;

import com.epam.trainee.model.entities.Ingredient;
import com.epam.trainee.service.IngredientService;
import com.epam.trainee.service.SaladService;
import com.epam.trainee.controller.utils.WebUrl;
import com.epam.trainee.model.entities.dishes.Salad;
import com.epam.trainee.service.ServiceFactory;
import com.epam.trainee.view.Page;
import com.epam.trainee.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebUrl("/create/salad")
public class CreateSaladCommand implements Command {

    private IngredientService ingredientService;
    private SaladService saladService;

    public CreateSaladCommand() {
        saladService = ServiceFactory.getInstance().getSaladService();
        ingredientService = ServiceFactory.getInstance().getIngredientService();
    }

    @Override
    public View executeGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("ingredients", ingredientService.getAllIngredients());
        req.setAttribute("action", "/create/salad");
        req.setAttribute("title", "Create new salad recipe:");
        return Page.CREATE_SALAD;
    }

    @Override
    public View executePost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Set<Integer> ingredientsId = convertToInteger(req.getParameterValues("id"));
            Set<Ingredient> saladIngredients = ingredientService.getIngredientsById(ingredientsId);
            processWeightFromRequest(saladIngredients, req);
            String saladName = req.getParameter("name");
            saladService.createSaladRecipe(saladName, saladIngredients);
            Salad salad = saladService.getSaladByName(saladName);
            req.setAttribute("salad", salad);
            return Page.SINGLE_SALAD;
        } catch (Exception e) {
            req.setAttribute("exception", e.getMessage());
            executeGet(req, resp);
            return Page.CREATE_SALAD;
        }
    }

    private static Set<Integer> convertToInteger(String[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Ingredients ids for recipe can't be null");
        }
        return Stream.of(array)
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    private void processWeightFromRequest(Set<Ingredient> ingredients, HttpServletRequest req) {
        ingredients.forEach(ingredient -> {
            double weight = Double.parseDouble(req.getParameter(ingredient.getId() + "_weight"));
            ingredient.setWeight(weight);
        });
    }
}