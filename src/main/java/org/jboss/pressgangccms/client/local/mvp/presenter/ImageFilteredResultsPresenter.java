package org.jboss.pressgangccms.client.local.mvp.presenter;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.ImageFilteredResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class ImageFilteredResultsPresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		AsyncDataProvider<RESTImageV1> getProvider();

		void setProvider(final AsyncDataProvider<RESTImageV1> provider);

		CellTable<RESTImageV1> getResults();

		SimplePager getPager();
	}

	@Inject
	private Display display;
	
	private String queryString;
	
	/** Keeps a reference to the start row */
	private Integer tableStartRow;
	
	/** Keeps a reference to the list of topics being displayed */
	private List<RESTImageV1> currentList;

	@Override
	public void parseToken(final String searchToken)
	{
		queryString = searchToken.replace(ImageFilteredResultsView.HISTORY_TOKEN + ";", "");
	}

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		bind();
	}

	private void bind()
	{
		super.bind(display);

		final AsyncDataProvider<RESTImageV1> provider = new AsyncDataProvider<RESTImageV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTImageV1> display)
			{
				final int start = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = start + length;

				final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>()
				{
					@Override
					public void begin()
					{
						startProcessing();
					}

					@Override
					public void generalException(final Exception ex)
					{
						stopProcessing();
					}

					@Override
					public void success(final RESTImageCollectionV1 retValue)
					{
						try
						{
							updateRowData(start, retValue.getItems());
							updateRowCount(retValue.getSize(), true);
						}
						finally
						{
							stopProcessing();
						}
					}	

					@Override
					public void failed()
					{
						stopProcessing();
					}
				};
				
				RESTCalls.getImagesFromQuery(callback, queryString, start, end);
			}
		};

		display.setProvider(provider);
	}
	
	/**
	 * @return A provider to be used for the topic display list
	 */
	private AsyncDataProvider<RESTImageV1> generateListProvider()
	{
		final AsyncDataProvider<RESTImageV1> provider = new AsyncDataProvider<RESTImageV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTImageV1> display)
			{
				tableStartRow = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = tableStartRow + length;

				final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>()
				{
					@Override
					public void begin()
					{
						startProcessing();
					}

					@Override
					public void generalException(final Exception ex)
					{
						Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
						stopProcessing();
					}

					@Override
					public void success(final RESTImageCollectionV1 retValue)
					{
						try
						{
							currentList = retValue.getItems();
							updateRowData(tableStartRow, currentList);
							updateRowCount(retValue.getSize(), true);
						}
						finally
						{
							stopProcessing();
						}
					}

					@Override
					public void failed()
					{
						stopProcessing();
						Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
					}
				};

				RESTCalls.getImagesFromQuery(callback, queryString, tableStartRow, end);
			}
		};
		return provider;
	}

	private void stopProcessing()
	{
		display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		display.setSpinnerVisible(true);
	}
}
