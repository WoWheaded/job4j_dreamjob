package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IndexControllerTest {

    private final IndexController indexController = new IndexController();

    @Test
    public void whenRequestIndexPageThenGetPageIndex() {
        var view = indexController.getIndex();
        assertThat(view).isEqualTo("index");
    }
}