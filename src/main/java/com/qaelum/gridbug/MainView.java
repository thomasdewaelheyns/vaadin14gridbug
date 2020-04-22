package com.qaelum.gridbug;

import com.qaelum.gridbug.pojo.Detail;
import com.qaelum.gridbug.pojo.Item;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends FlexLayout {

    public MainView() {
        setSizeFull();
        getStyle().set("flex-direction", "row");
        setWrapMode(WrapMode.WRAP);
        setJustifyContentMode(JustifyContentMode.START);
        setAlignItems(Alignment.START);

        VerticalLayout block = createBlock();
        add(block);
        setFlexGrow(1,block);
    }

    private VerticalLayout createBlock(){
        VerticalLayout block = new VerticalLayout();
        block.setSizeUndefined();
        block.setWidth("100%");
        block.setMinWidth("500px");
        block.setMaxWidth("600px");

        Span title = new Span("Header");
        title.addClassName("h1");
        title.setWidth("100%");

        block.add(title);
        block.add(new Hr());

        Grid<Item> grid = createParentGrid();
        block.setHorizontalComponentAlignment(Alignment.START);
        block.add(grid);
        return block;
    }

    private Grid<Item> createParentGrid(){
        Grid<Item> grid = new Grid<>();
        grid.setId("grid-parent");
        grid.setWidth("100%");
        grid.setHeightByRows(true);

        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(Item::getName).setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(new ComponentRenderer<>(this::createMenuButtons)).setAutoWidth(true).setFlexGrow(0);

        grid.setItemDetailsRenderer(new ComponentRenderer<>(this::createDetailLayout));
        grid.setDetailsVisibleOnClick(true);

        grid.setDataProvider(DataProvider.ofCollection(createItems()));
        return grid;
    }

    private HorizontalLayout createMenuButtons(Item item){
        HorizontalLayout menu = new HorizontalLayout();
        menu.setSizeUndefined();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE_O));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setSizeUndefined();
        Button button2 = new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O));
        button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button2.setSizeUndefined();
        menu.add(button);
        menu.setFlexGrow(0,button);
        menu.add(button2);
        menu.setFlexGrow(0,button2);
        return menu;
    }

    private ArrayList<Item> createItems(){
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("Item 1");
        item1.getDetails().add(new Detail("Item one property one is quite long.","Item one property two is also quite long"));
        item1.getDetails().add(new Detail("Again Item one property one is quite long.","once more Item one property two is also quite long"));
        items.add(item1);
        Item item2 = new Item("Item 2");
        item2.getDetails().add(new Detail("Item two property one is quite long.","Item two property two is also quite long"));
        item2.getDetails().add(new Detail("Again Item two property one is quite long.","once more Item two property two is also quite long"));
        items.add(item2);
        return items;
    }

    private VerticalLayout createDetailLayout(Item item){
        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.setSizeUndefined();
        detailsLayout.setWidth("100%");

        Grid<Detail> grid = createDetailsGrid(item.getDetails());

        detailsLayout.add(grid);
        return detailsLayout;
    }

    private Grid<Detail> createDetailsGrid(List<Detail> details){
        Grid<Detail> grid = new Grid<>();
        grid.setId("grid-child");
        grid.setWidth("100%");
        grid.setHeightByRows(true);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setDetailsVisibleOnClick(false);

        grid.addColumn(Detail::getProperty1).setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(Detail::getProperty2).setAutoWidth(true).setFlexGrow(1);

        grid.setDataProvider(DataProvider.ofCollection(details));
        return grid;
    }
}
