package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import com.google.gwt.i18n.client.DateTimeFormat;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseSearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * The backing object for the search fields view. Instance of this class will be manipulated by a GWT Editor
 *
 * @author Matthew Casperson
 */
public class ContentSpecSearchUIFields extends BaseSearchUIFields {
    private static final boolean MATCH_ALL_DEFAULT = true;

    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    @Nullable
    private Date editedAfter;
    @Nullable
    private Date editedBefore;
    @Nullable
    private Integer editedInLastXDays;
    @Nullable
    private Integer notEditedInLastXDays;
    private String ids;
    private String title;
    private String product;
    private String version;
    private boolean matchAll = MATCH_ALL_DEFAULT;

    @Nullable
    public Date getEditedAfter() {
        return GWTUtilities.createDateCopy(editedAfter);
    }

    public void setEditedAfter(@Nullable final Date editedAfter) {
        this.editedAfter = GWTUtilities.createDateCopy(editedAfter);
    }

    @Nullable
    public Date getEditedBefore() {
        return GWTUtilities.createDateCopy(editedBefore);
    }

    public void setEditedBefore(@Nullable final Date editedBefore) {
        this.editedBefore = GWTUtilities.createDateCopy(editedBefore);
    }

    @Nullable
    public Integer getEditedInLastXDays() {
        return editedInLastXDays;
    }

    public void setEditedInLastXDays(@Nullable final Integer editedInLastXDays) {
        this.editedInLastXDays = editedInLastXDays;
    }

    @Nullable
    public String getIds() {
        return ids;
    }

    public void setIds(@Nullable final String ids) {
        this.ids = ids;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable final String title) {
        this.title = title;
    }

    @Nullable
    public String getProduct() {
        return product;
    }

    public void setProduct(@Nullable final String product) {
        this.product = product;
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    public void setVersion(@Nullable final String version) {
        this.version = version;
    }

    @Nullable
    public Integer getNotEditedInLastXDays() {
        return notEditedInLastXDays;
    }

    public void setNotEditedInLastXDays(@Nullable final Integer notEditedInLastXDays) {
        this.notEditedInLastXDays = notEditedInLastXDays;
    }

    public final boolean isMatchAll() {
        return matchAll;
    }

    public void setMatchAll(final boolean matchAll) {
        this.matchAll = matchAll;
    }

    public ContentSpecSearchUIFields() {

    }

    /**
     * @param filter The filter that defines the state of the tags
     */
    public ContentSpecSearchUIFields(@Nullable final RESTFilterV1 filter) {
        super(filter);
    }

    public void populateFilter(@NotNull final RESTFilterV1 filter) {

        if (!GWTUtilities.isStringNullOrEmpty(getIds())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR, getIds()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getTitle())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.CONTENT_SPEC_TITLE_FILTER_VAR, getTitle()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getProduct())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.CONTENT_SPEC_PRODUCT_FILTER_VAR, getProduct()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getVersion())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.CONTENT_SPEC_VERSION_FILTER_VAR, getVersion()));
        }

        if (getEditedInLastXDays() != null) {
            filter.getFilterFields_OTM().addNewItem(
                    createFilterField(CommonFilterConstants.EDITED_IN_LAST_DAYS, getEditedInLastXDays().toString()));
        }

        if (getNotEditedInLastXDays() != null) {
            filter.getFilterFields_OTM().addNewItem(
                    createFilterField(CommonFilterConstants.NOT_EDITED_IN_LAST_DAYS, getNotEditedInLastXDays().toString()));
        }

        if (this.isMatchAll() != MATCH_ALL_DEFAULT) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.LOGIC_FILTER_VAR, isMatchAll() + ""));
        }

        if (getEditedBefore() != null) {
            filter.getFilterFields_OTM().addNewItem(
                    createFilterField(CommonFilterConstants.ENDEDITDATE_FILTER_VAR, dateformat.format(getEditedBefore())));
        }

        if (getEditedAfter() != null) {
            filter.getFilterFields_OTM().addNewItem(
                    createFilterField(CommonFilterConstants.STARTEDITDATE_FILTER_VAR, dateformat.format(getEditedAfter())));
        }
    }

    public void initialize(@Nullable final RESTFilterV1 filter) {
        if (filter != null) {

            editedAfter = null;
            editedBefore = null;
            editedInLastXDays = null;
            notEditedInLastXDays = null;
            ids = "";
            title = "";
            product = "";
            version = "";
            matchAll = true;

            if (filter.getFilterFields_OTM() != null) {

                for (@NotNull final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR)) {
                        setIds(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.CONTENT_SPEC_TITLE_FILTER_VAR)) {
                        setTitle(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.CONTENT_SPEC_PRODUCT_FILTER_VAR)) {
                        setProduct(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.CONTENT_SPEC_VERSION_FILTER_VAR)) {
                        setVersion(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.EDITED_IN_LAST_DAYS)) {
                        try {
                            setEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (@NotNull final NumberFormatException ex) {
                            // do nothing
                        }
                    } else if (fieldItem.getName().equals(CommonFilterConstants.NOT_EDITED_IN_LAST_DAYS)) {
                        try {
                            setNotEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (@NotNull final NumberFormatException ex) {
                            // do nothing
                        }
                    } else if (fieldItem.getName().equals(CommonFilterConstants.LOGIC_FILTER_VAR)) {
                        setMatchAll(Boolean.parseBoolean(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.ENDEDITDATE_FILTER_VAR)) {
                        setEditedBefore(dateformat.parse(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.STARTEDITDATE_FILTER_VAR)) {
                        setEditedAfter(dateformat.parse(fieldItem.getValue()));
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {

        @NotNull final StringBuilder retValue = new StringBuilder(
                includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        if (!GWTUtilities.isStringNullOrEmpty(ids)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_IDS_FILTER_VAR).append("=").append(encodeQueryParameter(ids));
        }
        if (!GWTUtilities.isStringNullOrEmpty(title)) {
            retValue.append(";").append(CommonFilterConstants.CONTENT_SPEC_TITLE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(title));
        }
        if (!GWTUtilities.isStringNullOrEmpty(product)) {
            retValue.append(";").append(CommonFilterConstants.CONTENT_SPEC_PRODUCT_FILTER_VAR).append("=").append(
                    encodeQueryParameter(product));
        }
        if (!GWTUtilities.isStringNullOrEmpty(version)) {
            retValue.append(";").append(CommonFilterConstants.CONTENT_SPEC_VERSION_FILTER_VAR).append("=").append(
                    encodeQueryParameter(version));
        }
        if (editedInLastXDays != null) {
            retValue.append(";").append(CommonFilterConstants.EDITED_IN_LAST_DAYS).append("=").append(
                    encodeQueryParameter(editedInLastXDays.toString()));
        }
        if (notEditedInLastXDays != null) {
            retValue.append(";").append(CommonFilterConstants.NOT_EDITED_IN_LAST_DAYS).append("=").append(
                    encodeQueryParameter(notEditedInLastXDays.toString()));
        }
        if (editedBefore != null) {
            retValue.append(";").append(CommonFilterConstants.ENDEDITDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(editedBefore)));
        }
        if (editedAfter != null) {
            retValue.append(";").append(CommonFilterConstants.STARTEDITDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(editedAfter)));
        }

        if (matchAll) {
            retValue.append(";").append(CommonFilterConstants.LOGIC_FILTER_VAR).append("=").append(
                    encodeQueryParameter(CommonFilterConstants.AND_LOGIC));
        } else {
            retValue.append(";").append(CommonFilterConstants.LOGIC_FILTER_VAR).append("=").append(
                    encodeQueryParameter(CommonFilterConstants.OR_LOGIC));
        }

        return retValue.toString();
    }
}