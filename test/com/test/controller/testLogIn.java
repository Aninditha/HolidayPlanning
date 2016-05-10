package com.test.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.ssdi.POJO.userbean;
import com.ssdi.controller.LogIn;
import com.ssdi.controller.Register;
import com.ssdi.model.ServicesDao;

public class testLogIn extends Mockito{

	/** Servlet under test. */
	private LogIn servlet;

	/** Mock request. */
	private HttpServletRequest request;

	/** Mock response. */
	private HttpServletResponse response;

	/** Mock session. */
	private HttpSession session;

	/** Session's attribute map. */
	private Map attributes;

	/** Request's parameter map. */
	private Map parameters;

	/**
	 * Launches Mockito configuration from annotations.
	 */
	
	private userbean user;
	private RequestDispatcher rd;
	private ServicesDao serviceDao;

	@Before
	public void setUp() throws ServletException, IOException {
		rd = mock(RequestDispatcher.class);
		user = mock(userbean.class);
		attributes = new HashMap();
		parameters = new HashMap();
		serviceDao = mock(ServicesDao.class);
		session = mock(HttpSession.class);
		servlet = new LogIn(serviceDao, rd, session, user);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getParameterMap()).thenReturn(parameters);
		when(request.getRequestDispatcher(anyString())).thenAnswer(new Answer() {
			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return rd;
			}
		});

		when(request.getParameter(anyString())).thenAnswer(new Answer() {
			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				String key = (String) aInvocation.getArguments()[0];

				return parameters.get(key);
			}
		});

		when(session.getAttribute(anyString())).thenAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				String key = (String) aInvocation.getArguments()[0];

				return attributes.get(key);
			}
		});

		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				String key = (String) aInvocation.getArguments()[0];
				Object value = aInvocation.getArguments()[1];
				attributes.put(key, value);

				return null;
			}

		}).when(session).setAttribute(anyString(), anyObject());

		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				String key = (String) aInvocation.getArguments()[0];
				Object value = aInvocation.getArguments()[1];
				attributes.put(key, value);

				return null;
			}

		}).when(request).setAttribute(anyString(), anyObject());

		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(rd).include(anyObject(), anyObject());
	}

	/**
	 * Test method for
	 * {@link SessionServlet#doGet(HttpServletRequest, HttpServletResponse)} .
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testValidLogIn() throws ServletException, IOException {
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(user).setUsername(anyString());
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(user).setPassword(anyString());
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}
		}).when(user).setEmail(anyString());

		when(user.getEmail()).thenReturn("aninditha@gmail.com");
		when(serviceDao.checkEmail(anyString())).thenReturn(false);
		when(serviceDao.registerUser(anyObject())).thenReturn(anyObject());
		parameters.put("username", "Aninditha");
		parameters.put("password1", "aninditha");
		parameters.put("email", "aninditha@gmail.com");
		servlet.doPost(request, response);

		Object object = attributes.get("username");

		String message = (String) object;
		assertEquals("aninditha@gmail.com", message);
	}

	/**
	 * Test method for
	 * {@link SessionServlet#doGet(HttpServletRequest, HttpServletResponse)} .
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testInvalidRegister() throws ServletException, IOException {
		
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(user).setUsername(anyString());
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(user).setPassword(anyString());
		Mockito.doAnswer(new Answer() {

			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {

				return "";
			}

		}).when(user).setEmail(anyString());

		when(user.getEmail()).thenReturn("anandita@gmail.com");
		when(serviceDao.checkEmail(anyString())).thenReturn(true);
		
		servlet.doPost(request, response);
	}
}
