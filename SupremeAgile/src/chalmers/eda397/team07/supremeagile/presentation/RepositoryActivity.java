package chalmers.eda397.team07.supremeagile.presentation;

import java.util.ArrayList;
import java.util.List;

import chalmers.eda397.team07.supremeagile.common.NetworkUtils;
import chalmers.eda397.team07.supremeagile.common.Repository;
import chalmers.eda397.team07.supremeagile.serviceInterface.IRepositoryService;
import chalmers.eda397.team07.supremeagile.serviceInterface.ServiceLocator;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class RepositoryActivity extends ListActivity {
	private IRepositoryService repoService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Avoid android.os.NetworkOnMainThreadException
		NetworkUtils.permitAllThreadPolicy();
		
		// Setup internal services
		try {
			repoService = ServiceLocator.getService(IRepositoryService.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Repository[] repos = repoService.getOwnRepositories();
			List<String> repoNames = new ArrayList<String>();
			for (Repository repo : repos) {
				repoNames.add(repo.name);
			}
			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, repoNames));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
