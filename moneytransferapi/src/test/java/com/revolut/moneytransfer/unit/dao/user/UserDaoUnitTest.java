package com.revolut.moneytransfer.unit.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/14/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoUnitTest {
	@Mock
	private UserDao userDao;

	@Before
	public void init() {
		mockGetUserById();
		mockGetAllUsers();
		mockCreateUser();
		mockUpdateUser();
		mockTestExists();
		mockTestDelete();
	}

	// when-then
	private void mockGetUserById() {
		Mockito.when(userDao.getById(Mockito.anyString())).thenReturn(TestHelper.getTestUser());

	}

	@Test
	public void testGetById() {
		UserModel userModel = userDao.getById("1");
		Mockito.verify(userDao, Mockito.times(1)).getById(Mockito.anyString());
		assertEquals(userModel.getId(), "1");
		assertEquals(userModel.getCNIC(), "1234-12212-11");
		assertEquals(userModel.getFirstName(), "aqib");
		assertEquals(userModel.getLastName(), "javed");
		assertEquals(userModel.getEmail(), "aqib_javed@gmail.com");
		assertEquals(userModel.getContactNumber(), "+32145777789");
	}

	private void mockGetUserByIdDataNotFound() {
		Mockito.when(userDao.getById(Mockito.anyString())).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetByIdDataNotFound() {
		mockGetUserByIdDataNotFound();
		userDao.getById("1");
		Mockito.verify(userDao, Mockito.times(1)).getById(Mockito.anyString());
	}

	private void mockGetAllUsers() {
		Mockito.when(userDao.getAll()).thenReturn(TestHelper.getTestAllUsers());
	}

	@Test
	public void testGetAll() {
		Iterator<UserModel> users = userDao.getAll().iterator();
		Mockito.verify(userDao, Mockito.times(1)).getAll();
		UserModel user = users.next();
		assertEquals(user.getId(), "1");
		assertEquals(user.getCNIC(), "1234-12212-11");
		assertEquals(user.getFirstName(), "aqib");
		assertEquals(user.getLastName(), "javed");
		assertEquals(user.getEmail(), "aqib_javed@gmail.com");
		assertEquals(user.getContactNumber(), "+32145777789");
	}

	private void mockGetAllDataNotFoundException() {
		Mockito.when(userDao.getAll()).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetAllDataNotFoundException() {
		mockGetAllDataNotFoundException();
		userDao.getAll();
		Mockito.verify(userDao, Mockito.times(1)).getAll();
	}

	private void mockCreateUser() {
		Mockito.when(userDao.create(Mockito.any(UserModel.class)))
				.thenReturn(TestHelper.getTestUser());
	}

	@Test
	public void testCreateUser() {
		UserModel userModel = userDao.create(TestHelper.getTestUser());
		Mockito.verify(userDao, Mockito.times(1)).create(Mockito.any(UserModel.class));
		assertEquals(userModel.getId(), "1");
		assertEquals(userModel.getCNIC(), "1234-12212-11");
		assertEquals(userModel.getFirstName(), "aqib");
		assertEquals(userModel.getLastName(), "javed");
		assertEquals(userModel.getEmail(), "aqib_javed@gmail.com");
		assertEquals(userModel.getContactNumber(), "+32145777789");
	}

	private void mockCreateUserForDataDuplication() {
		Mockito.when(userDao.create(Mockito.any(UserModel.class)))
				.thenThrow(new DataDuplicationException());
	}

	@Test(expected = DataDuplicationException.class)
	public void testCreateUserForDataDuplication() {
		mockCreateUserForDataDuplication();
		userDao.create(TestHelper.getTestUser());
		Mockito.verify(userDao, Mockito.times(1)).create(Mockito.any(UserModel.class));
	}

	private void mockUpdateUser() {
		Mockito.when(userDao.update(Mockito.any(UserModel.class)))
				.thenReturn(TestHelper.getTestUserUpdate());
	}

	@Test
	public void testUpdateUser() {
		UserModel userModel = userDao.update(TestHelper.getTestUser());
		Mockito.verify(userDao, Mockito.times(1)).update(Mockito.any(UserModel.class));
		assertEquals(userModel.getId(), "1");
		assertEquals(userModel.getCNIC(), "1234-12212-11");
		assertEquals(userModel.getFirstName(), "aqib");
		assertEquals(userModel.getLastName(), "javed");
		assertEquals(userModel.getEmail(), "aqib_javed@gmail.com");
		assertEquals(userModel.getContactNumber(), "+321477818177");

	}

	private void mockUpdateUserDataNotFound() {
		Mockito.when(userDao.update(Mockito.any(UserModel.class)))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testUpdateUserDataNotFound() {
		mockUpdateUserDataNotFound();
		userDao.update(TestHelper.getTestUser());
		Mockito.verify(userDao, Mockito.times(1)).update(Mockito.any(UserModel.class));
	}

	private void mockTestExists() {
		Mockito.when(userDao.exists(Mockito.anyString())).thenReturn(Boolean.TRUE);
	}

	@Test
	public void testExists() {
		assertTrue(userDao.exists("1"));
		Mockito.verify(userDao, Mockito.times(1)).exists(Mockito.anyString());
	}

	private void mockTestExistsDataNotFound() {
		Mockito.when(userDao.exists(Mockito.anyString())).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testExistsDataNotFound() {
		mockTestExistsDataNotFound();
		userDao.exists("1");
		Mockito.verify(userDao, Mockito.times(1)).exists(Mockito.anyString());
	}

	private void mockTestDelete() {
		Mockito.when(userDao.delete(Mockito.anyString())).thenReturn(TestHelper.getDeletedUser());
	}

	// delete API will delete and send deleted object
	@Test
	public void testDelete() {
		UserModel userModel = userDao.delete("1");
		Mockito.verify(userDao, Mockito.times(1)).delete(Mockito.anyString());
		assertEquals(userModel.getId(), "1");
		assertEquals(userModel.getCNIC(), "1234-12212-11");
		assertEquals(userModel.getFirstName(), "aqib");
		assertEquals(userModel.getLastName(), "javed");
		assertEquals(userModel.getEmail(), "aqib_javed@gmail.com");
		assertEquals(userModel.getContactNumber(), "+32145777789");
	}

	private void mockTestDeleteDataNotFound() {
		Mockito.when(userDao.delete(Mockito.anyString())).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testDeleteDataNotFound() {
		mockTestDeleteDataNotFound();
		userDao.delete("1");
		Mockito.verify(userDao, Mockito.times(1)).delete(Mockito.anyString());
	}

}