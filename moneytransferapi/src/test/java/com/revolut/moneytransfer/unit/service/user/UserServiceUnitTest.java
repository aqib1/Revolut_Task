package com.revolut.moneytransfer.unit.service.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dto.requests.UserRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.exception.BadRequestParamsException;
import com.revolut.moneytransfer.exception.InvalidRequestException;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/15/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

	@Mock
	private UserService userService;

	@Before
	public void init() {
		mockGetAll();
		mockGetById();
		mockCreate();
		mockUpdate();
		mockExists();
		mockdelete();
	}

	private void mockGetAll() {
		Mockito.when(userService.getAll()).thenReturn(TestHelper.getResponse());
	}

	private void mockdelete() {
		Mockito.when(userService.delete(Mockito.anyString())).thenReturn(TestHelper.getResponse());
	}

	private void mockExists() {
		Mockito.when(userService.exists(Mockito.anyString()))
				.thenReturn(TestHelper.getIsExistsResponse());
	}

	private void mockUpdate() {
		Mockito.when(userService.update(Mockito.any(UserRequestDto.class)))
				.thenReturn(TestHelper.getResponse());
	}

	private void mockCreate() {
		Mockito.when(userService.create(Mockito.any(UserRequestDto.class)))
				.thenReturn(TestHelper.getResponse());
	}

	private void mockGetById() {
		Mockito.when(userService.getById(Mockito.anyString())).thenReturn(TestHelper.getResponse());
	}

	@Test
	public void testGetById() {
		ResponseDto response = userService.getById("1");
		Mockito.verify(userService, Mockito.times(1)).getById(Mockito.anyString());
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testCreate() {
		ResponseDto response = userService.create(TestHelper.getUserRequest());
		Mockito.verify(userService, Mockito.times(1)).create(Mockito.any(UserRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testUpdate() {
		ResponseDto response = userService.update(TestHelper.getUserRequest());
		Mockito.verify(userService, Mockito.times(1)).update(Mockito.any(UserRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testExists() {
		ResponseDto response = userService.exists("1");
		Mockito.verify(userService, Mockito.times(1)).exists(Mockito.anyString());
		assertEquals(response.getData().getAsString(), "true");
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testDelete() {
		ResponseDto response = userService.delete("1");
		Mockito.verify(userService, Mockito.times(1)).delete(Mockito.anyString());
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockDeleteIllegalArgumentException() {
		Mockito.when(userService.delete(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteIllegalArgumentException() {
		mockDeleteIllegalArgumentException();
		userService.delete(null);
		Mockito.verify(userService, Mockito.times(1)).delete(null);
	}

	private void mockExistsIllegalArgumentException() {
		Mockito.when(userService.exists(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExistsIllegalArgumentException() {
		mockExistsIllegalArgumentException();
		userService.exists(null);
		Mockito.verify(userService, Mockito.times(1)).exists(null);
	}

	private void mockCreateInvalidRequestException() {
		Mockito.when(userService.create(Mockito.any(UserRequestDto.class)))
				.thenThrow(new InvalidRequestException());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCreateInvalidRequestException() {
		mockCreateInvalidRequestException();
		userService.create(null);
		Mockito.verify(userService, Mockito.times(1)).create(null);
	}

	private void mockCreateBadRequestParamsException() {
		Mockito.when(userService.create(Mockito.any(UserRequestDto.class)))
				.thenThrow(new BadRequestParamsException());
	}

	@Test(expected = BadRequestParamsException.class)
	public void testCreateBadRequestParamsException() {
		mockCreateBadRequestParamsException();
		userService.create(null);
		Mockito.verify(userService, Mockito.times(1)).create(null);
	}

	private void mockGetByIdIllegalArgumentException() {
		Mockito.when(userService.getById(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetByIdIllegalArgumentException() {
		mockGetByIdIllegalArgumentException();
		userService.getById(null);
		Mockito.verify(userService, Mockito.times(1)).getById(null);
	}
}
