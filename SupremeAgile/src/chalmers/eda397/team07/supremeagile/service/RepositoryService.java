package chalmers.eda397.team07.supremeagile.service;

import com.google.gson.Gson;

import chalmers.eda397.team07.supremeagile.common.Constants;
import chalmers.eda397.team07.supremeagile.common.Repository;
import chalmers.eda397.team07.supremeagile.common.SAContext;
import chalmers.eda397.team07.supremeagile.serviceInterface.IRepositoryService;

public class RepositoryService implements IRepositoryService {
	private static String OWN_REPOS_URL = "/users/:user/repos";

	public Repository[] getOwnRepositories() {
		String result = RequestHandler.executeHttpRequest(
				Constants.DEFAULT_URL
						+ OWN_REPOS_URL.replace(
								Constants.USER_CODE,
								SAContext.username),
				RequestHandler.RequestMethod.GET);
		Gson gson = new Gson();
		Repository[] repos = gson.fromJson(result, Repository[].class);
		return repos;
	}
}
