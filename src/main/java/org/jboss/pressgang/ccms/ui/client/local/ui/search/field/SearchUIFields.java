package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import java.util.Date;

import com.google.gwt.http.client.URL;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;

/**
 * The backing object for the search fields view. Instance of this class will be manipulated by a GWT Editor
 * 
 * @author Matthew Casperson
 * 
 */
public class SearchUIFields implements SearchViewBase {
    private Date createdAfter;
    private Date createdBefore;
    private Date editedAfter;
    private Date editedBefore;
    private Integer editedInLastXDays;
    private Integer notEditedInLastXDays;
    private String ids;
    private String notIds;
    private String title;
    private String contents;
    private String notContents;
    private String notTitle;
    private String description;
    private String notDescription;
    private String includedInContentSpecs;
    private String notIncludedInContentSpecs;
    private String freeTextSearch;
    private TriStateSelectionState hasBugzillaBugs = TriStateSelectionState.NONE;
    private TriStateSelectionState hasOpenBugzillaBugs = TriStateSelectionState.NONE;
    private boolean matchAll = true;
    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    public final Date getCreatedAfter() {
        return this.createdAfter;
    }

    public final void setCreatedAfter(final Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public final Date getCreatedBefore() {
        return this.createdBefore;
    }

    public final void setCreatedBefore(final Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public final Date getEditedAfter() {
        return this.editedAfter;
    }

    public final void setEditedAfter(final Date editedAfter) {
        this.editedAfter = editedAfter;
    }

    public final Date getEditedBefore() {
        return this.editedBefore;
    }

    public final void setEditedBefore(final Date editedBefore) {
        this.editedBefore = editedBefore;
    }

    public final Integer getEditedInLastXDays() {
        return this.editedInLastXDays;
    }

    public final void setEditedInLastXDays(final Integer editedInLastXDays) {
        this.editedInLastXDays = editedInLastXDays;
    }

    public final String getIds() {
        return this.ids;
    }

    public final void setIds(final String ids) {
        this.ids = ids;
    }

    public final String getNotIds() {
        return this.notIds;
    }

    public final void setNotIds(final String notIds) {
        this.notIds = notIds;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(final String title) {
        this.title = title;
    }

    public final String getNotTitle() {
        return this.notTitle;
    }

    public final void setNotTitle(final String notTitle) {
        this.notTitle = notTitle;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final String getNotDescription() {
        return this.notDescription;
    }

    public final void setNotDescription(final String notDescription) {
        this.notDescription = notDescription;
    }

    public final String getIncludedInContentSpecs() {
        return this.includedInContentSpecs;
    }

    public final void setIncludedInContentSpecs(final String includedInContentSpecs) {
        this.includedInContentSpecs = includedInContentSpecs;
    }

    public final String getNotIncludedInContentSpecs() {
        return this.notIncludedInContentSpecs;
    }

    public final void setNotIncludedInContentSpecs(final String notIncludedInContentSpecs) {
        this.notIncludedInContentSpecs = notIncludedInContentSpecs;
    }

    public final String getFreeTextSearch() {
        return this.freeTextSearch;
    }

    public final void setFreeTextSearch(final String freeTextSearch) {
        this.freeTextSearch = freeTextSearch;
    }

    public final String getContents() {
        return this.contents;
    }

    public final void setContents(final String contents) {
        this.contents = contents;
    }

    public final Integer getNotEditedInLastXDays() {
        return this.notEditedInLastXDays;
    }

    public final void setNotEditedInLastXDays(final Integer notEditedInLastXDays) {
        this.notEditedInLastXDays = notEditedInLastXDays;
    }

    public final String getNotContents() {
        return this.notContents;
    }

    public final void setNotContents(final String notContents) {
        this.notContents = notContents;
    }

    public final TriStateSelectionState getHasBugzillaBugs() {
        return this.hasBugzillaBugs;
    }

    public final void setHasBugzillaBugs(final TriStateSelectionState hasBugzillaBugs) {
        this.hasBugzillaBugs = hasBugzillaBugs;
    }

    public final TriStateSelectionState getHasOpenBugzillaBugs() {
        return this.hasOpenBugzillaBugs;
    }

    public final void setHasOpenBugzillaBugs(final TriStateSelectionState hasOpenBugzillaBugs) {
        this.hasOpenBugzillaBugs = hasOpenBugzillaBugs;
    }

    public final boolean isMatchAll() {
        return this.matchAll;
    }

    public final void setMatchAll(final boolean matchAll) {
        this.matchAll = matchAll;
    }

    @Override
    public final String getSearchQuery(final boolean includeQueryPrefix) {

        final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON
                : "");

        if (!GWTUtilities.isStringNullOrEmpty(this.ids)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.ids) : this.ids));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notIds)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notIds) : this.notIds));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.description)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.description) : this.description));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notDescription)) {
            retValue.append(";"
                    + CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notDescription) : this.notDescription));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.title)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.title) : this.title));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notTitle)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notTitle) : this.notTitle));
        }
        if (this.editedInLastXDays != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.editedInLastXDays.toString()) : this.editedInLastXDays.toString()));
        }
        if (this.notEditedInLastXDays != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notEditedInLastXDays.toString()) : this.notEditedInLastXDays.toString()));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.contents)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.contents) : this.contents));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notContents)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notContents) : this.notContents));
        }
        if (this.createdBefore != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.dateformat.format(this.createdBefore)) : this.dateformat.format(this.createdBefore)));
        }
        if (this.editedBefore != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.dateformat.format(this.editedBefore)) : this.dateformat.format(this.editedBefore)));
        }
        if (this.editedAfter != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.dateformat.format(this.editedAfter)) : this.dateformat.format(this.editedAfter)));
        }
        if (this.createdAfter != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.dateformat.format(this.createdAfter)) : this.dateformat.format(this.createdAfter)));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.includedInContentSpecs)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.includedInContentSpecs) : this.includedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notIncludedInContentSpecs)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notIncludedInContentSpecs) : this.notIncludedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(freeTextSearch)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.freeTextSearch) : this.freeTextSearch));
        }
        if (this.hasBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        } else if (this.hasBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_NOT_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        }
        if (this.hasOpenBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_OPEN_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        } else if (this.hasOpenBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";"
                    + CommonFilterConstants.TOPIC_HAS_NOT_OPEN_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        }

        if (this.matchAll) {
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(CommonFilterConstants.AND_LOGIC) : CommonFilterConstants.AND_LOGIC));
        } else {
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(CommonFilterConstants.OR_LOGIC) : CommonFilterConstants.OR_LOGIC));
        }

        return retValue.toString();
    }

}
