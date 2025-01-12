package com.packages.controller;

import com.packages.model.Model;
import com.packages.view.View;

public abstract class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {return model;}
    public View getView() {return view;}
}
