package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.project.ProjectViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.projectview.RESTProjectV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

@Dependent
public class ProjectsFilteredResultsAndProjectPresenter
        extends
        BaseSearchAndEditComponent<ProjectFilteredResultsPresenter.Display, ProjectsFilteredResultsAndProjectPresenter.Display, RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1, ProjectViewInterface, ProjectPresenter.Display, RESTProjectV1BasicDetailsEditor>
        implements TemplatePresenter {

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

    }

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "ProjectsFilteredResultsAndProjectView";

    @Inject
    private HandlerManager eventBus;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /** An Errai injected instance of a class that implements ProjectFilteredResultsPresenter.LogicCompnent */
    @Inject
    private ProjectFilteredResultsPresenter filteredResultsComponent;

    /** An Errai injected instance of a class that implements ProjectPresenter.LogicComponent */
    @Inject
    private ProjectPresenter resultComponent;

    @Inject
    private ProjectTagPresenter tagComponent;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    public void go(final HasWidgets container) {


        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTProjectV1> getNewEntityCallback = new GetNewEntityCallback<RESTProjectV1>() {

            @Override
            public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTProjectV1> displayCallback) {
                final RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, BaseTemplateViewInterface>(waitDisplay,
                        new BaseRestCallback.SuccessAction<RESTProjectV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(final RESTProjectV1 retValue, final BaseTemplateViewInterface display) {
                                displayCallback.displayNewEntity(retValue);
                            }
                        });
                RESTCalls.getProject(callback, id);
            }
        };

        clearContainerAndAddTopLevelPanel(container, display);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        filteredResultsComponent.process(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString, display);
        resultComponent.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
        tagComponent.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
        super.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Preferences.PROJECT_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

        /* Bind the logic to add and remove possible children */
        tagComponent.bindPossibleChildrenListButtonClicks(
                new GetExistingCollectionCallback<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>() {

                    @Override
                    public RESTTagCollectionV1 getExistingCollection() {
                        return filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags();
                    }

                }, new AddPossibleChildCallback<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>() {

                    @Override
                    public void createAndAddChild(final RESTTagCollectionItemV1 copy) {
                        final RESTTagV1 newChild = new RESTTagV1();
                        newChild.setId(copy.getItem().getId());
                        newChild.setName(copy.getItem().getName());
                        filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(newChild);
                    }

                }, new UpdateAfterChildModfiedCallback() {

                    @Override
                    public void updateAfterChidModfied() {
                        /*
                         * refresh the list of possible tags
                         */
                        tagComponent.refreshPossibleChildList();
                    }

                });
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("entityIds")) {
                    this.filteredResultsComponent.getDisplay().getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityName")) {
                    this.filteredResultsComponent.getDisplay().getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityDesc")) {
                    this.filteredResultsComponent.getDisplay().getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        /* Get a new collection of tags */
        tagComponent.refreshPossibleChildrenDataAndList();
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to display the project fields view
         */
        final ClickHandler projectDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                switchView(resultComponent.getDisplay());
            }

        };

        /**
         * A click handler used to display the project tags view
         */
        final ClickHandler projectTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                switchView(tagComponent.getDisplay());
            }

        };

        /**
         * A click handler used to save any changes to the project
         */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                resultComponent.getDisplay().getDriver().flush();

                final RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, Display>(display,
                        new BaseRestCallback.SuccessAction<RESTProjectV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTProjectV1 retValue, final Display display) {
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        true);

                                /* This project is no longer a new project */
                                filteredResultsComponent.getProviderData().getDisplayedItem()
                                        .setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                filteredResultsComponent.getDisplay().getProvider().updateRowData(
                                        filteredResultsComponent.getProviderData().getStartRow(),
                                        filteredResultsComponent.getProviderData().getItems());

                                tagComponent.getDisplay().initialize(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        false);
                                tagComponent.refreshPossibleChildrenDataAndList();

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTProjectV1 project = new RESTProjectV1();
                        project.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                        project.explicitSetName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getName());
                        project.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getDescription());
                        project.explicitSetTags(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getTags());

                        if (wasNewEntity) {
                            RESTCalls.createProject(callback, project);
                        } else {
                            RESTCalls.saveProject(callback, project);
                        }
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        for (final ProjectViewInterface view : new ProjectViewInterface[] { resultComponent.getDisplay(), tagComponent.getDisplay()}) {
            view.getDetails().addClickHandler(projectDetailsClickHandler);
            view.getChildren().addClickHandler(projectTagsClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
        }

    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(filteredResultsComponent.getQuery(),
                            GWTUtilities.isEventToOpenNewWindow(event)));
            }
        });

        filteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTProjectV1 selectedEntity = new RESTProjectV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTProjectCollectionItemV1 selectedTagWrapper = new RESTProjectCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTProjectV1 displayedEntity = new RESTProjectV1();
                displayedEntity.setId(Constants.NULL_ID);
                displayedEntity.setTags(new RESTTagCollectionV1());
                final RESTProjectCollectionItemV1 displayedTagWrapper = new RESTProjectCollectionItemV1(displayedEntity,
                        RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? resultComponent.getDisplay() : lastDisplayedView);

                tagComponent.refreshPossibleChildrenDataAndList();
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            resultComponent.getDisplay().getDriver().flush();

            return (unsavedProjectChanges() || unsavedTagChanges());
        }
        return false;
    }

    /**
     * Compare the selected and displayed project, and see if any of the fields have changed
     *
     * @return true if there are unsaved changes, false otherwise
     */
    private boolean unsavedProjectChanges() {
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getProviderData().getSelectedItem().getItem()
                .getName(), filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                filteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription()));
    }

    /**
     * Check to see if there are any added, removed or modified tags in the project
     *
     * @return true if there are modified tags, false otherwise
     */
    private boolean unsavedTagChanges() {
        return !filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags()
                .returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    @Override
    protected void switchView(final ProjectViewInterface displayedView) {

        super.switchView(displayedView);

        lastDisplayedView = displayedView;
    }

    @Override
    protected void initializeViews(final List<ProjectViewInterface> filter) {
        for (final ProjectViewInterface view : new ProjectViewInterface[] {  resultComponent.getDisplay(), tagComponent.getDisplay() }) {
            if (viewIsInFilter(filter, view)) {
                view.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
            }
        }

    }
}