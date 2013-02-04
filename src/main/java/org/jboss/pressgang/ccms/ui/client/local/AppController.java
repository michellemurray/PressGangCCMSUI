package org.jboss.pressgang.ccms.ui.client.local;

import com.google.common.base.Optional;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class watches the event bus for page change requests, and instructs the appropriate presenters
 * to display when a page change event is received.
 */
@ApplicationScoped
public class AppController implements PresenterInterface, ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;

    @Inject
    private HandlerManager eventBus;

    private HasWidgets container;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AppController.class.getName());

    /**
     * Default constructor. Does nothing.
     */
    public AppController() {

    }

    /**
     * Adds event handlers to the event bus.
     */
    public void bind() {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.bind()");

            History.addValueChangeHandler(this);

            this.eventBus.addHandler(WelcomeViewEvent.TYPE, new ViewOpenEventHandler(WelcomePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchViewEvent.TYPE, new ViewOpenEventHandler(SearchPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchResultsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(SearchResultsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchResultsAndTopicViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    SearchResultsAndTopicPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesViewEvent.TYPE, new ViewOpenEventHandler(ImagePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesFilteredResultsAndImageViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TagsFilteredResultsAndTagViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(CategoriesFilteredResultsAndCategoryViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    CategoriesFilteredResultsAndCategoryPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchTagsFieldsAndFiltersViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    SearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ProjectsFilteredResultsAndProjectViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                    ProjectsFilteredResultsAndProjectPresenter.HISTORY_TOKEN));
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.bind()");
        }
    }

    @Override
    public void go(final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.go()");

            this.container = container;
            this.bind();

            if ("".equals(History.getToken())) {
                LOGGER.log(Level.INFO, "Setting default history token");
                History.newItem(WelcomePresenter.HISTORY_TOKEN);
            } else {
                LOGGER.log(Level.INFO, "Firing current history token");
                History.fireCurrentHistoryState();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.go()");
        }
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {

        final String token = event.getValue();

        if (token != null) {
            Optional<BaseTemplatePresenterInterface> presenter = Optional.absent();

            if (token.startsWith(WelcomePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(WelcomePresenter.class);
            } else if (token.startsWith(SearchPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchPresenter.class);
            } else if (token.startsWith(SearchResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchResultsPresenter.class);
            } else if (token.startsWith(TopicPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TopicPresenter.class);
            } else if (token.startsWith(TopicXMLPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TopicXMLPresenter.class);
            } else if (token.startsWith(TagPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagPresenter.class);
            } else if (token.startsWith(SearchResultsAndTopicPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchResultsAndTopicPresenter.class);
            } else if (token.startsWith(ImagePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImagePresenter.class);
            } else if (token.startsWith(ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImagesFilteredResultsAndImagePresenter.class);
            } else if (token.startsWith(TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagsFilteredResultsAndTagPresenter.class);
            } else if (token.startsWith(ImageFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImageFilteredResultsPresenter.class);
            } else if (token.startsWith(CategoryFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoryFilteredResultsPresenter.class);
            } else if (token.startsWith(CategoryPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoryPresenter.class);
            } else if (token.startsWith(CategoriesFilteredResultsAndCategoryPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoriesFilteredResultsAndCategoryPresenter.class);
            } else if (token.startsWith(TagFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagFilteredResultsPresenter.class);
            } else if (token.startsWith(SearchFieldPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchFieldPresenter.class);
            } else if (token.startsWith(SearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchTagsFieldsAndFiltersPresenter.class);
            } else if (token.startsWith(ProjectsFilteredResultsAndProjectPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ProjectsFilteredResultsAndProjectPresenter.class);
            }

            if (presenter.isPresent()) {
                presenter.get().parseToken(token);
                presenter.get().go(this.container);
            }
        }
    }

    private <T extends BaseTemplatePresenterInterface> Optional<BaseTemplatePresenterInterface> getBeanInstance(final Class<T> presenterType) {
        final IOCBeanDef<T> bean = this.manager.lookupBean(presenterType);
        if (bean != null) {
            final BaseTemplatePresenterInterface presenter = bean.getInstance();
            return Optional.of(presenter);
        }
        return Optional.absent();
    }
}
