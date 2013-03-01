package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CategoryTagView
        extends BaseOrderedChildrenView<
        RESTCategoryV1,
        RESTCategoryV1,
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements CategoryTagPresenter.Display {

    @NotNull
    private final TextColumn<RESTTagCollectionItemV1> tagsIdColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTTagCollectionItemV1> tagsNameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    @NotNull
    private final Column<RESTTagCollectionItemV1, String> tagsButtonColumn = new Column<RESTTagCollectionItemV1, String>(
            new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                if (ComponentCategoryV1.containsTag(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagIdColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";

        }
    };

    @NotNull
    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @Nullable
    @Override
    public Column<RESTTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    @Override
    public void display(@NotNull final RESTCategoryV1 originalEntity, final boolean readOnly) {
        super.displayChildren(originalEntity, readOnly);
    }

    public CategoryTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());
        tagsButtonColumn.setSortable(true);

        /*
            Allow the table to be sorted.
         */
        @NotNull final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);
        getPossibleChildrenResults().getColumnSortList().push(tagsButtonColumn);

        getExistingChildrenResults().addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.ChildTagID());
        getExistingChildrenResults().addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.ChildTagName());
        getExistingChildrenResults().addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        getExistingChildrenResults().addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

        addExistingChildrenPanel();
    }

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildDownButtonColumn() {
        return tagDownButtonColumn;
    }

}
