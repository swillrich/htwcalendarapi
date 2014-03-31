package de.svenwillrich.htwcalendarapi.api.data;

import java.util.List;

import de.svenwillrich.htwcalendarapi.api.exception.DataAlreadyExistsException;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;

public interface IData {
	public APIData saveLogin(String matrikelNr, String password) throws DataAlreadyExistsException;
	public boolean deleteLogin(String hash) throws LoginDataNotFoundException;
	public APIData readLogin(String hash) throws LoginDataNotFoundException;
	public List<APIData> getAllApiData();
}
