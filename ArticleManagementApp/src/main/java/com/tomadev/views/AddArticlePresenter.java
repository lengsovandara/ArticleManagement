package com.tomadev.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.converter.InputStreamInputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import com.tomadev.GluonApplication;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tomadev.converter.SingleInputConverter;
import com.tomadev.model.Article;
import com.tomadev.model.Response;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class AddArticlePresenter extends GluonPresenter<GluonApplication> {
    private static final Logger LOGGER = Logger.getLogger(AddArticlePresenter.class.getName());

    @FXML
    private View addarticle;

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;
    
    public void initialize() {
        addarticle.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = getApp().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        getApp().getDrawer().open()));
                appBar.setTitleText("AddArticle");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
            }
        });
    }
    
    @FXML
    void buttonClick() {
//        label.setText(resources.getString("label.text.2"));
        Article article = new Article();
        article.setTitle("GLuon Mobile Posting Article");
        postArticle(article);
    }
    public void postArticle(Article article) {
        RestClient client = RestClient.create()
                .method("POST")
                .host("http://api-ams.me/")
                .path("/v1/api/articles")
                .header("accept", "application/json")
                .header("Authorization", "Basic QU1TQVBJQURNSU46QU1TQVBJUEBTU1dPUkQ=")
                .dataString(article.toJsonString())
                .contentType("application/json");

        InputStreamInputConverter<Response> converter = new SingleInputConverter<>(Response.class);

        GluonObservableObject<Response> model = DataProvider.retrieveObject(client.createObjectDataReader(converter));
        model.exceptionProperty().addListener((obs, ov, nv) -> LOGGER.log(Level.WARNING, "Error: " + nv));
        model.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv) {
                LOGGER.log(Level.INFO, "Response: " + model.get().getData().toJsonString());
            }
        });
    }
}
