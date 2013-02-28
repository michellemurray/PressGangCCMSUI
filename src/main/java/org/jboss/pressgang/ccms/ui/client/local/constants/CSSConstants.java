package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This interface lists all of the CSS class names used by the application. The nested interfaces are just used to group the
 * styles assigned to particular views.
 *
 * @author Matthew Casperson
 */
public interface CSSConstants {

    /**
     * Styles applied to the base template.
     */
    interface Template {
        /**
         * Style applied to the label that identifies the ui as working with a production server.
         */
        String SERVER_TYPE_PRODUCTION = "ServerTypeProduction";
        /**
         * Style applied to the label that identifies the ui as working with a development server.
         */
        String SERVER_TYPE_DEVELOPMENT = "ServerTypeDevelopment";
        /**
         * Assigned to the panel that holds the shortcut panels.
         */
        String SHORTCUT_PANEL_PARENT = "ShortcutPanelParent";
        /**
         * Assigned to the vertical panel holding the shortcut buttons on the left of the screen.
         */
        String SHORTCUT_PANEL = "ShortcutPanel";
        /**
         * Assigned to the panel down the bottom of the screen that serves as the footer.
         */
        String FOOTER_PANEL = "FooterPanel";
        /**
         * The class assigned to the top level DockLayoutPanel.
         */
        String TOP_LEVEL_LAYOUT_PANEL = "TopLevelLayoutPanel";
        /**
         * Assigned to the doc layout panel below the page heading.
         */
        String SECOND_LEVEL_LAYOUT_PANEL = "SecondLevelLayoutPanel";
        /**
         * The class assigned to the page title label.
         */
        String PAGE_TITLE = "PageTitle";
        /**
         * Assigned to the panel that holds the quick search controls.
         */
        String QUICK_SEARCH_PARENT_PANEL = "QuickSearchParentPanel";
        /**
         * Assigned to the panel that holds the page title and quick search buttons
         */
        String PAGE_TITLE_PARENT_LAYOUT_PANEL = "PageTitleParentLayoutPanel";
        /**
         * Assigend to the panel that holds the top action buttons and page content.
         */
        String THIRD_LEVEL_LAYOUT_PANEL = "ThirdLevelLayoutPanel";
        /**
         * Assigned to the panel that holds the parent of the  action buttons panel.
         */
        String TOP_ACTION_GRANDPARENT_PANEL = "TopActionGrandParentPanel";
        /**
         * Assigned to the panel that holds the action buttons panel.
         */
        String TOP_ACTION_PARENT_PANEL = "TopActionParentPanel";
        /**
         * Assigned to the panel that holds the action buttons.
         */
        String TOP_ACTION_PANEL = "TopActionPanel";
        /**
         * Assigned to the panel that holds the page content.
         */
        String CONTENT_LAYOUT_PANEL = "ContentLayoutPanel";
        /**
         * Assigned to a padding element that pushing the remaining buttons to the right.
         */
        String RIGHT_ALIGNED_ACTION_BUTTONS = "RightAlignedActionButtons";
    }

    interface HelpDialog {
        String HELP_CONTENTS = "HelpContents";
    }

    /**
     * These styles are applied to the topic and translated topic search views.
     */
    interface SearchView {
        /**
         * The style applied to the cell that appears above the
         * category logic options.
         */
        String LOGIC_HEADER_CELL = "LogicHeaderCell";
        /**
         * The style applied to the cell that holds the table holding the logic details radio buttons.
         */
        String LOGIC_DETAILS_CELL = "LogicDetailsCell";
        /**
         * The style applied to the table holding the logic details radio buttons.
         */
        String LOGIC_DETAILS_TABLE = "LogicDetailsTable";
    }

    /**
     * The CSS style names applied to the search filter editor
     */
    interface FilterEditor {
        /**
         * The style name of the filter's parent panel.
         */
        String FILTER_VIEW_PANEL = "FilterViewPanel";
        /**
         * The style name of the filter's name textbox.
         */
        String FILTER_VIEW_NAME_FIELD = "FilterViewNameField";
        /**
         * The style name of the filter's description textarea.
         */
        String FILTER_VIEW_DESCRIPTION_FIELD = "FilterViewDescriptionField";
        /**
         * The style name of the cell that holds the label next to the filter's details.
         */
        String FILTER_VIEW_LABEL = "FilterViewLabel";
        /**
         * The style name of the cell that holds the filter's details.
         */
        String FILTER_VIEW_DETAIL = "FilterViewDetail";
        /**
         * The style name of the cell that holds the filter's description textarea.
         */
        String FILTER_VIEW_DESCRIPTION_DETAIL = "FilterViewDescriptionDetail";
    }

    interface PlainTextXMLDialog {
        String PLAIN_TEXT_XML_DIALOG_TEXTAREA = "PlainTextXMLDialogTextArea";
    }

    interface FilteredResultsView {
        /**
         * Assigned to the panel that holds the list of images.
         */
        String FILTERED_RESULTS_PANEL = "FilteredResultsPanel";
        /**
         * Assigned to the panel that holds the filter options for images.
         */
        String FILTERED_OPTIONS_PANEL = "FilteredOptionsPanel";
    }

    interface OrderedChildrenResultsView {
        String ORDERED_CHILDREN_LIST_PANEL = "OrderedChildrenListPanel";
        String ORDERED_CHILDREN_EXISTING_LIST_PANEL = "OrderedChildrenExistingListPanel";
        String ORDERED_CHILDREN_SPLIT_PANEL = "OrderedChildrenSplitPanel";
    }

    interface BaseSearchAndEditView {
        String RESULTS_VIEW_LAYOUT_PANEL = "ResultsViewLayoutPanel";
        String ENTITY_VIEW_LAYOUT_PANEL = "EntityViewLayoutPanel";
        /**
         * Assigned to the panel that holds the action buttons above the entity search results lists
         */
        String ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL = "EntitySearchTagsResultButtonsPanel";
        String ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PARENT_PANEL = "EntitySearchTagsResultButtonsParentPanel";
        /**
         * Assigned to the panel that holds the action buttons above the entity search result view
         */
        String ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL = "EntitySearchTagViewButtonsPanel";
        String ENTITY_SEARCH_TAG_VIEW_BUTTONS_PARENT_PANEL = "EntitySearchTagViewButtonsParentPanel";
        String ENTITY_SEARCH_TOPIC_VIEW_DETAILS_PANEL = "EntitySearchTopicViewDetailsPanel";
        String ENTITY_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL = "EntitySearchResultsAndViewParentPanel";
    }

    interface StringConstantView {
        String STRING_CONSTANT_VIEW_PANEL = "StringConstantViewPanel";
        String STRING_CONSTANT_VIEW_ID_FIELD = "StringConstantViewIdField";
        String STRING_CONSTANT_VIEW_NAME_FIELD = "StringConstantViewNameField";
        String STRING_CONSTANT_VIEW_VALUE_FIELD = "StringConstantViewValueField";
    }

    interface PropertyTagView {
        String PROPERTY_TAG_VIEW_PANEL = "PropertyTagViewPanel";
        String PROPERTY_TAG_VIEW_ID_FIELD = "PropertyTagViewIdField";
        String PROPERTY_TAG_VIEW_NAME_FIELD = "PropertyTagViewNameField";
        String PROPERTY_TAG_VIEW_REGEX_FIELD = "PropertyTagViewRegexField";
        String PROPERTY_TAG_VIEW_DESCRIPTION_FIELD = "PropertyTagViewDescriptionField";
        String PROPERTY_TAG_VIEW_UNIQUE_FIELD = "PropertyTagViewUniqueField";
        String PROPERTY_TAG_VIEW_CANBENULL_FIELD = "PropertyTagViewCanBeNullField";
    }

    interface PropertyCategoryView {
        String PROPERTY_CATEGORY_VIEW_PANEL = "PropertyCategoryViewPanel";
        String PROPERTY_CATEGORY_VIEW_ID_FIELD = "PropertyCategoryViewIdField";
        String PROPERTY_CATEGORY_VIEW_NAME_FIELD = "PropertyCategoryViewNameField";
        String PROPERTY_CATEGORY_VIEW_DESCRIPTION_FIELD = "PropertyCategoryViewDescriptionField";
    }

    interface IntegerConstantView {
        String INTEGER_CONSTANT_VIEW_PANEL = "IntegerConstantViewPanel";
        String INTEGER_CONSTANT_VIEW_ID_FIELD = "IntegerConstantViewIdField";
        String INTEGER_CONSTANT_VIEW_NAME_FIELD = "IntegerConstantViewNameField";
        String INTEGER_CONSTANT_VIEW_VALUE_FIELD = "IntegerConstantViewValueField";
    }

    interface BlobConstantView {
        String BLOB_CONSTANT_VIEW_PANEL = "BlobConstantViewPanel";
        String BLOB_CONSTANT_VIEW_ID_FIELD = "BlobConstantViewIdField";
        String BLOB_CONSTANT_VIEW_NAME_FIELD = "BlobConstantViewNameField";
    }

    interface TagView {
        String TAG_VIEW_PANEL = "TagViewPanel";
        String TAG_VIEW_ID_LABEL = "TagViewIDLabel";
        String TAG_VIEW_ID_TEXT = "TagViewIDText";
        String TAG_VIEW_NAME_LABEL = "TagViewNameLabel";
        String TAG_VIEW_NAME_TEXT = "TagViewNameText";
        String TAG_VIEW_DESCRIPTION_LABEL = "TagViewDescriptionLabel";
        String TAG_VIEW_DESCRIPTION_TEXT = "TagViewDescriptionText";

        String TAG_VIEW_ID_LABEL_CELL = "TagViewIDLabelCell";
        String TAG_VIEW_ID_TEXT_CELL = "TagViewIDTextCell";
        String TAG_VIEW_NAME_LABEL_CELL = "TagViewNameLabelCell";
        String TAG_VIEW_NAME_TEXT_CELL = "TagViewNameTextCell";
        String TAG_VIEW_DESCRIPTION_LABEL_CELL = "TagViewDescriptionLabelCell";
        String TAG_VIEW_DESCRIPTION_TEXT_CELL = "TagViewDescriptionTextCell";
    }

    interface CategoryView {
        String CATEGORY_VIEW_PANEL = "CategoryViewPanel";
        String CATEGORY_VIEW_ID_LABEL = "CategoryViewIDLabel";
        String CATEGORY_VIEW_ID_TEXT = "CategoryViewIDText";
        String CATEGORY_VIEW_NAME_LABEL = "CategoryViewNameLabel";
        String CATEGORY_VIEW_NAME_TEXT = "CategoryViewNameText";
        String CATEGORY_VIEW_DESCRIPTION_LABEL = "CategoryViewDescriptionLabel";
        String CATEGORY_VIEW_DESCRIPTION_TEXT = "CategoryViewDescriptionText";

        String CATEGORY_VIEW_ID_LABEL_CELL = "CategoryViewIDLabelCell";
        String CATEGORY_VIEW_ID_TEXT_CELL = "CategoryViewIDTextCell";
        String CATEGORY_VIEW_NAME_LABEL_CELL = "CategoryViewNameLabelCell";
        String CATEGORY_VIEW_NAME_TEXT_CELL = "CategoryViewNameTextCell";
        String CATEGORY_VIEW_DESCRIPTION_LABEL_CELL = "CategoryViewDescriptionLabelCell";
        String CATEGORY_VIEW_DESCRIPTION_TEXT_CELL = "CategoryViewDescriptionTextCell";
    }

    interface ProjectView {
        String PROJECT_VIEW_PANEL = "ProjectViewPanel";
        String PROJECT_VIEW_ID_LABEL = "ProjectViewIDLabel";
        String PROJECT_VIEW_ID_TEXT = "ProjectViewIDText";
        String PROJECT_VIEW_NAME_LABEL = "ProjectViewNameLabel";
        String PROJECT_VIEW_NAME_TEXT = "ProjectViewNameText";
        String PROJECT_VIEW_DESCRIPTION_LABEL = "ProjectViewDescriptionLabel";
        String PROJECT_VIEW_DESCRIPTION_TEXT = "ProjectViewDescriptionText";

        String PROJECT_VIEW_ID_LABEL_CELL = "ProjectViewIDLabelCell";
        String PROJECT_VIEW_ID_TEXT_CELL = "ProjectViewIDTextCell";
        String PROJECT_VIEW_NAME_LABEL_CELL = "ProjectViewNameLabelCell";
        String PROJECT_VIEW_NAME_TEXT_CELL = "ProjectViewNameTextCell";
        String PROJECT_VIEW_DESCRIPTION_LABEL_CELL = "ProjectViewDescriptionLabelCell";
        String PROJECT_VIEW_DESCRIPTION_TEXT_CELL = "ProjectViewDescriptionTextCell";
    }

    String TOPIC_VIEW_LAYOUT_PANEL = "TopicViewLayoutPanel";
    String TOPIC_RENDERED_VIEW_DIV = "TopicRenderedViewDiv";
    String SEARCH_RESULTS_PANEL = "SearchResultsPanel";
    String TOPIC_VIEW_PANEL = "TopicViewPanel";
    String TOPIC_VIEW_ID_FIELD = "TopicViewIDField";
    String TOPIC_VIEW_REVISION_NUMBER_FIELD = "TopicViewRevisionNumberField";
    String TOPIC_VIEW_TITLE_FIELD = "TopicViewTitleField";
    String TOPIC_VIEW_LOCALE_FIELD = "TopicViewLocaleField";
    String TOPIC_VIEW_DESCRIPTION_FIELD = "TopicViewDescriptionField";
    String TOPIC_VIEW_LABEL = "TopicViewLabel";
    String TOPIC_VIEW_DETAIL = "TopicViewDetail";
    String TOPIC_VIEW_DESCRIPTION_DETAIL = "TopicViewDescriptionDetail";
    String PROJECTS_LAYOUT = "ProjectsLayout";
    String PROJECTS_BUTTONS_LAYOUT = "ProjectsButtonsLayout";
    String PROJECTS_SCROLL_PANEL = "ProjectsScrollPanel";
    String CUSTOM_BUTTON = "CustomButton";
    String CUSTOM_BUTTON_DOWN = "CustomButtonDown";
    String CATEGORIES_BUTTONS_LAYOUT = "CategoriesButtonsLayout";
    String CATEGORIES_LAYOUT = "CategoriesLayout";
    String CATEGORIES_SCROLL_PANEL = "CategoriesScrollPanel";
    String CATEGORY_TAG_LAYOUT = "CategoryTagLayout";
    String CATEGORY_TAG_SCROLL = "CategoryTagScroll";
    String TAG_LABEL = "TagLabel";
    String CUSTOM_BUTTON_TEXT = "CustomButtonText";
    String CUSTOM_BUTTON_TAGS_INCLUDED = "CustomButtonTagsInclued";
    String CUSTOM_BUTTON_TAGS_EXCLUDED = "CustomButtonTagsExcluded";
    String CUSTOM_BUTTON_TEXT_BOLD = "CustomButtonTextBold";
    String TOPIC_XML_VIEW_PANEL = "TopicXMLViewPanel";
    String TOPIC_XML_VIEW_ACE_PANEL = "TopicXMLViewACEPanel";
    String TOPIC_XML_VIEW_XML_FIELD = "TopicXMLViewXMLField";
    String TOPIC_XML_ERRORS_VIEW_PANEL = "TopicXMLErrorsViewPanel";
    String TOPIC_XML_ERRORS_VIEW_FIELD = "TopicXMLErrorsViewField";
    String TOPIC_VIEW_TAG_LABEL = "TopicViewTagLabel";
    /**
     * Assigned to the FlexTable rows that list a category in the topic tags view.
     */
    String TOPIC_TAG_VIEW_CATEGORY_ROW = "TopicTagViewCategoryRow";
    /**
     * Assigned to the FlexTable rows that list a project in the topic tags view.
     */
    String TOPIC_TAG_VIEW_PROJECT_ROW = "TopicTagViewProjectRow";
    /**
     * Assigned to the FlexTable rows that list a tag in the topic tags view.
     */
    String TOPIC_TAG_VIEW_TAG_ROW = "TopicTagViewTagRow";
    /**
     * Assigned to the panel that holds all the TOPIC_TAG_VIEW_PROJECT_STABLE element in the topic tags view.
     */
    String TOPIC_TAG_VIEW_PARENT_PROJECT_STABLE = "TopicTagViewParentProjectsTable";
    /**
     * Assigned to the table that holds all the projects, categories and tags in the topic tags view.
     */
    String TOPIC_TAG_VIEW_PROJECT_STABLE = "TopicTagViewProjectsTable";
    /**
     * Assigned to the table that holds a project in the topic tags view.
     */
    String TOPIC_TAG_VIEW_PROJECT_TABLE = "TopicTagViewProjectTable";
    /**
     * Assigned to the table that holds multiple categories in the topic tags view.
     */
    String TOPIC_TAG_VIEW_CATEGORIES_TABLE = "TopicTagViewCategoriesTable";
    /**
     * Assigned to the table that holds a category in the topic tags view.
     */
    String TOPIC_TAG_VIEW_CATEGORY_TABLE = "TopicTagViewCategoryTable";
    /**
     * Assigned to the table that holds multiple tags in the topic tags view.
     */
    String TOPIC_TAG_VIEW_TAG_STABLE = "TopicTagViewTagsTable";
    /**
     * Assigned to the table that holds the new tag project, category and tag lists.
     */
    String TOPIC_TAG_VIEW_NEW_TAG_TABLE = "TopicTagViewNewTagTable";
    /**
     * Assigned to the panel that holds the topic tag view.
     */
    String TOPIC_TAG_VIEW_CONTENT_PANEL = "TopicTagViewContentPanel";
    /**
     * Assigned to the list that holds the available projects in the topic tags view.
     */
    String TOPIC_TAG_VIEW_NEW_TAG_PROJECTS_LIST = "TopicTagViewNewTagProjectsList";
    /**
     * Assigned to the list that holds the available categories in the topic tags view.
     */
    String TOPIC_TAG_VIEW_NEW_TAG_CATEGORIES_LIST = "TopicTagViewNewTagCategoriesList";
    /**
     * Assigned to the list that holds the available tags in the topic tags view.
     */
    String TOPIC_TAG_VIEW_NEW_TAG_TAGS_LIST = "TopicTagViewNewTagTagsList";
    /**
     * Assigned to the panel that holds the new tag ui elements.
     */
    String TOPIC_TAG_VIEW_NEW_TAG_PARENT_PANEL = "TopicTagViewNewTagParentPanel";
    /**
     * Assigned to the dock panel that hosts the image description and the tab view of specific language images.
     */
    String IMAGE_VIEW_PARENT_DOCK_PANEL = "ImageViewParentDockPanel";
    /**
     * Assigned to the tab panel of specific language images.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_TAB_PANEL = "ImageViewLanguageImageTabPanel";
    /**
     * Assigned to the tab that shows a specific language image.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_TAB = "ImageViewLanguageImageTab";
    /**
     * Assigned to the cell that holds the language file name label.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL = "ImageViewLanguageImageFilenameLabel";
    /**
     * Assigned to the cell that holds the language file name text.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT = "ImageViewLanguageImageFilenameText";
    /**
     * Assigned to the cell that holds the language display label.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL = "ImageViewLanguageImageDisplayLabel";
    /**
     * Assigned to the cell that holds the language display image.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE = "ImageViewLanguageImageDisplayImage";
    /**
     * Assigned to the cell that holds the language file name label cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL_CELL = "ImageViewLanguageImageFilenameLabelCell";
    /**
     * Assigned to the cell that holds the language file name text cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT_CELL = "ImageViewLanguageImageFilenameTextCell";
    /**
     * Assigned to the cell that holds the language display label cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL_CELL = "ImageViewLanguageImageDisplayLabelCell";
    /**
     * Assigned to the cell that holds the language display image cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE_CELL = "ImageViewLanguageImageDisplayImageCell";
    /**
     * Assigned to the cell that holds the language display label cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DIMENSION_LABEL_CELL = "ImageViewLanguageImageDimensionLabelCell";
    /**
     * Assigned to the cell that holds the language display image cell.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_DIMENSION_TEXT_CELL = "ImageViewLanguageImageDimensionTextCell";
    /**
     * Assigned to the image description label.
     */
    String IMAGE_VIEW_DESCRIPTION_LABEL = "ImageViewDescriptionLabel";
    /**
     * Assigned to the image description text.
     */
    String IMAGE_VIEW_DESCRIPTION_TEXT = "ImageViewDescriptionText";
    /**
     * Assigned to the image template text.
     */
    String IMAGE_VIEW_TEMPLATE_TEXT = "ImageViewTemplateText";
    /**
     * Assigned to the image description label cell.
     */
    String IMAGE_VIEW_DESCRIPTION_LABEL_CELL = "ImageViewDescriptionLabelCell";
    /**
     * Assigned to the image description text cell.
     */
    String IMAGE_VIEW_DESCRIPTION_TEXT_CELL = "ImageViewDescriptionTextCell";
    /**
     * Assigned to the cell that holds the image template label.
     */
    String IMAGE_VIEW_TEMPLATE_LABEL_CELL = "ImageViewTemplateLabelCell";
    /**
     * Assigned to the cell that holds the image template text.
     */
    String IMAGE_VIEW_TEMPLATE_TEXT_CELL = "ImageViewTemplateTextCell";
    /**
     * Assigned to the image docbook file name label cell.
     */
    String IMAGE_VIEW_DOCBOOK_FILENAME_LABEL_CELL = "ImageViewDocbookFileNameLabelCell";
    /**
     * Assigned to the image docbook file name text cell.
     */
    String IMAGE_VIEW_DOCBOOK_FILENAME_TEXT_CELL = "ImageViewDocbookFileNameTextCell";
    /**
     * Assigned to the table that holds the image details.
     */
    String IMAGE_VIEW_DETAILS_TABLE = "ImageViewDetailsTable";
    /**
     * Assigned to image buttons.
     */
    String TEXT_BUTTON = "TextButton";
    /**
     * Assigned to image buttons that are top tabs
     */
    String TOP_TAB_BUTTON = "TopTab";
    /**
     * Assigned to image buttons that are top tabs
     */
    String LEFT_TAB_BUTTON = "LeftTab";
    /**
     * Assigned to image buttons that open external links
     */
    String EXTERNAL_BUTTON = "ExternalLink";
    /**
     * Assigned to the table that holds the split view menu.
     */
    String RENDERED_SPLIT_VIEW_MENU_TABLE = "RenderedSplitViewMenuTable";
    /**
     * Assigned to the table that holds the split view menu.
     */
    String FILTERED_RESULTS_TAB_MENU_TABLE = "FilteredResultsTabMenu";
    /**
     * Assigned to labels that represent down pushbuttons.
     */
    String DOWN_LABEL = "DownLabel";
    /**
     * Assigned to push buttons that are sub menus.
     */
    String SUB_MENU = "SubMenu";
    /**
     * Assigned to push buttons that have an error state.
     */
    String ERROR = "Error";
    /**
     * Assigned to buttons that need to show some kind of alert status.
     */
    String ALERT_BUTTON = "AlertButton";
    /**
     * Assigned to the panel that holds the OK and Cancel buttons in a popup dialog box.
     */
    String DIALOG_BOX_OK_CANCEL_PANEL = "DialogBoxOKCancelPanel";
    /**
     * Assigned to the cell that holds the image upload label.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_LABEL_CELL = "ImageViewLanguageImageUploadLabelCell";
    /**
     * Assigned to the cell that holds the image upload buttons.
     */
    String IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_BUTTONS_CELL = "ImageViewLanguageImageUploadButtonsCell";

}
