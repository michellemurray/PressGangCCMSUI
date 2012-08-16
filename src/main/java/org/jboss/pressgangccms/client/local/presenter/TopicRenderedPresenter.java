package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicRenderedPresenter extends TemplatePresenter
{
	private String topicId;

	public interface Display extends TopicViewInterface
	{
		@Override
		void initialize(final RESTTopicV1 topic);
	}

	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseToken(String historyToken)
	{
		// TODO Auto-generated method stub		
	}
}
