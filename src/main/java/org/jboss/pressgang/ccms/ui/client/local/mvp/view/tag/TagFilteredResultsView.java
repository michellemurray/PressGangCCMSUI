package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TagFilteredResultsView extends BaseFilteredResultsView<RESTTagCollectionItemV1> implements
        TagFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private final TextColumn<RESTTagCollectionItemV1> idColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                /* don't display the null ID for new tags */
                if (object.getItem().getId() != null && object.getItem().getId().equals(Constants.NULL_ID)) {
                    return "";
                } else {
                    return object.getItem().getId() + "";
                }
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTTagCollectionItemV1> nameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    @NotNull
    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @NotNull
    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @NotNull
    @Override
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public TagFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags(), PressGangCCMSUI.INSTANCE.CreateTag());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TagID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.TagName());

        this.addFilterField(PressGangCCMSUI.INSTANCE.TagIDs(), idFilter);
        this.addFilterField(PressGangCCMSUI.INSTANCE.TagName(), nameFilter);
        this.addFilterField(PressGangCCMSUI.INSTANCE.TagDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);

    }
}
